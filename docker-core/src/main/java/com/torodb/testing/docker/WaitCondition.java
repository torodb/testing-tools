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
import org.jooq.lambda.Blocking;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@FunctionalInterface
public interface WaitCondition {

  boolean lookForStartCondition(DockerClient docker, String containerId);

  default boolean lookForStartCondition(DockerClient docker, String containerId, Duration timeout)
      throws InterruptedException, TimeoutException, ExecutionException {
    return asFuture(docker, containerId, timeout)
        .get(timeout.toMillis(), TimeUnit.MILLISECONDS)
        .booleanValue();
  }

  /**
   * Returns this waiter as a {@link CompletableFuture}.
   *
   * <p/>The future runs on the default executor (usually {@link ForkJoinPool#commonPool()}) as a
   * {@link Blocking blocking runnable}.
   */
  default CompletableFuture<Boolean> asFuture(
      DockerClient docker,
      String containerId,
      Duration timeout) {
    return CompletableFuture.supplyAsync(Blocking.supplier(() ->
        this.lookForStartCondition(docker, containerId)
    ));
  }

}
