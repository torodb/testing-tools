/*
 * Copyright 2017 8Kdata Technology
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.torodb.testing.docker;

import com.google.common.base.Preconditions;
import com.google.common.net.HostAndPort;
import com.google.common.util.concurrent.AbstractIdleService;
import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.exceptions.ContainerNotFoundException;
import com.spotify.docker.client.exceptions.DockerCertificateException;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.ContainerCreation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.lambda.UncheckedException;

import java.time.Duration;
import java.util.Optional;
import java.util.stream.Stream;

/**
 *
 */
public abstract class AbstractDockerService extends AbstractIdleService implements DockerService {

  private final Logger logger;
  private final DockerClient client;
  private ContainerCreation container;
  private final WaitCondition waitCondition;
  private final ImageInstaller imageInstaller;

  public AbstractDockerService(WaitCondition waitCondition, ImageInstaller imageInstaller) {
    logger = LogManager.getLogger(this.getClass());
    try {
      this.waitCondition = waitCondition;
      this.imageInstaller = imageInstaller;
      client = createDockerClient();
    } catch (DockerCertificateException ex) {
      throw new RuntimeException(ex);
    }
  }

  protected abstract ContainerCreation createContainer(DockerClient client) throws DockerException,
      InterruptedException;

  protected void afterStarted(DockerClient client, String containerId) throws DockerException,
      InterruptedException {
  }
  
  protected Duration getTimeToWait() {
    return Duration.ofMinutes(2);
  }

  protected Stream<String> streamVolumesNamesToRemove() {
    return Stream.empty();
  }

  @Override
  protected void startUp() throws Exception {
    logger.trace("Preparing the image");
    imageInstaller.installImage(client);

    logger.debug("Creating an instance...");
    container = createContainer(client);
    Runtime.getRuntime().addShutdownHook(new Thread(this::emergencyShutDown));
    
    logger.debug("The instance has been created, starting it...");
    client.startContainer(container.id());
    logger.debug("The instance has been started");

    boolean correctStartup = false;

    try {
      logger.debug("Waiting until start state is reached...");
      boolean found = waitCondition.lookForStartCondition(client, container.id(), getTimeToWait());

      if (!found) {
        throw new AssertionError("It is impossible to detect when the container is started");
      }

      logger.debug("Start state has been reached");

      afterStarted(client, container.id());
      correctStartup = true;
    } finally {
      if (!correctStartup) {
        logger.debug("Shutting the docker instance after a failure at startup time");
        shutDown();
      }
    }

    logger.debug("Docker service startup finished");
  }

  @Override
  protected void shutDown() throws Exception {
    if (container != null) {
      try {
        client.stopContainer(container.id(), 2);
      } finally {
        client.removeContainer(container.id());
        streamVolumesNamesToRemove().forEach(this::removeVolume);
      }
    }
  }

  private void removeVolume(String volumeName) {
    try {
      client.removeVolume(volumeName);
    } catch (DockerException | InterruptedException ex) {
      logger.error("Error while removing volume " + volumeName, ex);
    }
  }

  @Override
  public void pause() {
    checkRunning();
    assert container != null;
    try {
      client.pauseContainer(container.id());
    } catch (DockerException | InterruptedException ex) {
      throw new RuntimeException("Error while pausing the container " + container.id(), ex);
    }
  }

  @Override
  public void unpause() {
    checkRunning();
    assert container != null;
    try {
      client.unpauseContainer(container.id());
    } catch (DockerException | InterruptedException ex) {
      throw new RuntimeException("Error while pausing the container " + container.id(), ex);
    }
  }
  
  protected void checkRunning() {
    Preconditions.checkState(this.isRunning(), "The service is not running");
  }

  protected Optional<HostAndPort> getBindingTcpPort(int port)
      throws DockerException, InterruptedException {
    return getBindingTcpPort(Integer.toString(port));
  }

  protected Optional<HostAndPort> getBindingTcpPort(String port)
      throws DockerException, InterruptedException {
    Preconditions.checkState(container != null, "The container hasn't been created yet");
    return client.inspectContainer(container.id())
        .networkSettings()
        .ports()
        .get(port + "/tcp")
        .stream()
        .findAny()
        .map(binding -> HostAndPort.fromParts(
            binding.hostIp(),
            Integer.parseInt(binding.hostPort())
        ));
  }

  protected String getIp() throws DockerException, InterruptedException {
    Preconditions.checkState(container != null, "The container hasn't been created yet");
    return client.inspectContainer(container.id())
        .networkSettings()
        .ipAddress();
  }

  private void emergencyShutDown() {
    if (container != null) {
      try {
        try {
          client.inspectContainer(container.id());
        } catch (ContainerNotFoundException ignore) {
          return ;
        }
        logger.error("The shutdown method was not called before the JVM stops. Killing {}",
            container.id());
        try {
          client.killContainer(container.id());
        } catch (DockerException ex) {
          logger.error("Impossible to kill the container", ex);
        }
        client.removeContainer(container.id());
        streamVolumesNamesToRemove().forEach(this::removeVolume);
      } catch (InterruptedException | DockerException ex) {
        throw new UncheckedException(ex);
      }
    }
  }

  private static DockerClient createDockerClient() throws DockerCertificateException {
    return DefaultDockerClient.fromEnv().build();
  }
  

}
