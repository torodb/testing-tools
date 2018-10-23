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

import com.google.common.base.Charsets;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.exceptions.DockerException;
import org.jooq.lambda.Blocking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.CompletableFuture;

/**
 *
 */
@FunctionalInterface
public interface LogReaderWaitCondition extends WaitCondition {
  public boolean lookForStartCondition(BufferedReader stdReader);

  @Override
  public default boolean lookForStartCondition(DockerClient docker, String cointainerId) {
    //Beware: BufferedReades must be declared before PipedOputputStreams to be closed properly!
    try (PipedInputStream outInPipe = new PipedInputStream(10_240);
        BufferedReader stdReader = new BufferedReader(
            new InputStreamReader(outInPipe, Charsets.UTF_8));
        PipedOutputStream outOutPipe = new PipedOutputStream(outInPipe);
    ) {
      CompletableFuture<Void> attachFuture = CompletableFuture.runAsync(Blocking.runnable(() -> {
        attach(docker, cointainerId, outOutPipe);
      }));

      CompletableFuture<Boolean> readLogsFuture = CompletableFuture.supplyAsync(Blocking.supplier(
          () -> readLogs(stdReader))
      );

      waitUntilFinish(attachFuture, readLogsFuture);

      if (!readLogsFuture.isDone()) {
        throw new AssertionError("The read log future must be finished");
      }

      return readLogsFuture.join();
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

  static void attach(DockerClient docker, String containerId,
      PipedOutputStream outOutPipe) {
    try {
      docker.attachContainer(containerId,
          DockerClient.AttachParameter.LOGS, DockerClient.AttachParameter.STDOUT,
          DockerClient.AttachParameter.STDERR, DockerClient.AttachParameter.STREAM)
          .attach(outOutPipe, outOutPipe, false);
    } catch (IOException | DockerException | InterruptedException ex) {
      throw new RuntimeException(ex);
    }
  }

  default boolean readLogs(BufferedReader stdReader) {
    return lookForStartCondition(stdReader);
  }

  static void waitUntilFinish(
      CompletableFuture<Void> attachFuture, CompletableFuture<Boolean> readLogsFuture) {
    CompletableFuture.anyOf(attachFuture, readLogsFuture)
        .join();
    if (!readLogsFuture.isDone()) { //the attachFuture finished before
      throw new RuntimeException("The logs finished before the condition was fulfilled");
    }
  }
}
