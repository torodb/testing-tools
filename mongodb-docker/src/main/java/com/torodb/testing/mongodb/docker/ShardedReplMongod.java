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

import com.mongodb.MongoClientOptions;
import com.torodb.testing.docker.ImageInstaller;
import com.torodb.testing.docker.WaitCondition;

import java.util.List;

/**
 * A {@link ReplMongod} that plays the role of a shard node.
 */
public class ShardedReplMongod extends ReplMongod {

  public ShardedReplMongod(Config config, Version version, MongoClientOptions clientOptions,
      WaitCondition waitCondition, ImageInstaller imageInstaller) {
    super(config, version, clientOptions, waitCondition, imageInstaller);
  }

  public ShardedReplMongod(Config config, Version version,
      MongoClientOptions clientOptions) {
    super(config, version, clientOptions);
  }

  public ShardedReplMongod(Config config, Version version) {
    super(config, version);
  }

  @Override
  protected List<String> getCmd() {
    List<String> cmd = super.getCmd();
    if (!cmd.contains("--port")) {
      cmd.add("--port");
      cmd.add(Integer.toString(MONGOD_PORT));
    }

    cmd.add("--shardsvr");

    return cmd;
  }

}
