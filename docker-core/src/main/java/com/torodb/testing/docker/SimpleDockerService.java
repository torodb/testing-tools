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

import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.ContainerConfig;
import com.spotify.docker.client.messages.ContainerCreation;
import com.spotify.docker.client.messages.HostConfig;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 *
 */
public abstract class SimpleDockerService extends AbstractDockerService {

  public SimpleDockerService(WaitCondition waitCondition,
      ImageInstaller imageInstaller) {
    super(waitCondition, imageInstaller);
  }

  protected abstract List<String> getCmd();

  protected abstract String getImageRef();

  protected Optional<String> getContainerName() {
    return Optional.empty();
  }
  
  protected List<String> getEnv() {
    return Collections.emptyList();
  }

  protected void additionalHostConfiguration(DockerClient client, HostConfig.Builder builder) 
      throws DockerException, InterruptedException {
  }

  protected void additionalContainerConfiguration(DockerClient client,
      ContainerConfig.Builder builder) throws DockerException, InterruptedException {
  }

  @Override
  protected ContainerCreation createContainer(DockerClient client) throws DockerException,
      InterruptedException {
    try {
      HostConfig.Builder hostConfigBuilder = HostConfig.builder();
      additionalHostConfiguration(client, hostConfigBuilder);
      HostConfig hostConfig = hostConfigBuilder.build();

      ContainerConfig.Builder containerConfBuilder = ContainerConfig.builder()
          .hostConfig(hostConfig)
          .image(getImageRef())
          .cmd(getCmd())
          .env(getEnv());
      additionalContainerConfiguration(client, containerConfBuilder);
      ContainerConfig containerConf = containerConfBuilder.build();
      
      return client.createContainer(containerConf, getContainerName().orElse(null));
    } catch (DockerException | InterruptedException ex) {
      throw new RuntimeException(ex);
    }
  }

}
