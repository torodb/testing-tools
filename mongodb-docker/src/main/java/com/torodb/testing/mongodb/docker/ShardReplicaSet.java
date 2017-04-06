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

package com.torodb.testing.mongodb.docker;

import com.torodb.testing.mongodb.docker.ReplicaSetConfig.SecondaryConfig;

/**
 *
 */
public class ShardReplicaSet extends AbstractReplicaSet {

  public ShardReplicaSet(ReplicaSetConfig config) {
    super(config, ShardReplicaSet::createMongo);
  }

  private static ReplMongod createMongo(String replSetName, SecondaryConfig secondary) {
    if (secondary.isArbiter()) {
      throw new UnsupportedOperationException("Arbiters are not supported right now");
    }
    ReplMongod.Config mongodConfig = new ReplMongod.Config(replSetName, secondary.getOplogSize());

    return new ShardedReplMongod(mongodConfig, secondary.getVersion());
  }
}
