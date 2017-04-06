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
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;


@RunWith(JUnitPlatform.class)
public class BusyBoxContainsLineTest {

  @Test
  public void startAndStop() {
    try (BusyBoxDockerService dockerService = new BusyBoxDockerService()) {
      dockerService.startAsync();
      dockerService.awaitRunning();
    }
  }

  private static class BusyBoxDockerService extends AbstractDockerService {

    public BusyBoxDockerService() {
      super(new UntilStdLineContains("3"), new ImagePuller("busybox:latest"));
    }

    @Override
    protected ContainerCreation createContainer(DockerClient client) throws DockerException,
        InterruptedException {
      HostConfig hostConfig = HostConfig.builder().build();

      ContainerConfig containerConfig = ContainerConfig.builder()
          .hostConfig(hostConfig)
          .image("busybox")
          .cmd("sh", "-c", "ACCUM=1; while :; echo $ACCUM; ACCUM=$((ACCUM+1)); do sleep 1; done")
          .build();

      return client.createContainer(containerConfig);
    }

  }

}
