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

package com.torodb.testing.mongodb.junit;

import com.torodb.testing.mongodb.docker.EnumVersion;
import com.torodb.testing.mongodb.docker.ShardedCluster;
import com.torodb.testing.mongodb.junit.Rs.DataNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RequiresShardedCluster(
    value = @Cluster({
      @Rs(@DataNode(EnumVersion.LATEST)),
      @Rs(@DataNode(EnumVersion.v3_2)),
      @Rs(@DataNode(EnumVersion.v3_0))
    })
)
@RunWith(JUnitPlatform.class)
public class RequiresShardedClusterTest {

  @Test
  public void isInjectedRunning(ShardedCluster cluster) {
    Assertions.assertTrue(cluster.isRunning(), "The cluster should be injected on running state");
  }

}
