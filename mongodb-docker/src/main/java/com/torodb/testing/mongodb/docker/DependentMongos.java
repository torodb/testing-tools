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

import com.google.common.net.HostAndPort;
import com.mongodb.MongoClientOptions;
import com.torodb.testing.docker.ImageInstaller;
import com.torodb.testing.docker.WaitCondition;

import java.util.stream.Stream;

/**
 * A {@link Mongos} that depends on a {@link ConfigReplicaSet config replica set}.
 */
public class DependentMongos extends Mongos {

  private final ConfigReplicaSet configRs;

  public DependentMongos(ConfigReplicaSet configRs, Version version,
      MongoClientOptions clientOptions, WaitCondition waitCondition,
      ImageInstaller imageInstaller) {
    super(version, clientOptions, waitCondition, imageInstaller);
    this.configRs = configRs;
  }
  
  public DependentMongos(ConfigReplicaSet configRs, Version version,
      MongoClientOptions clientOptions) {
    super(version, clientOptions);
    this.configRs = configRs;
  }

  public DependentMongos(ConfigReplicaSet configRs, Version version) {
    super(version);
    this.configRs = configRs;
  }

  @Override
  protected void startUp() throws Exception {
    configRs.awaitRunning();
    super.startUp();
  }

  @Override
  protected String getConfigReplSetName() {
    return configRs.getReplSetName();
  }

  @Override
  protected Stream<HostAndPort> streamConfigNodesAddresses() {
    return configRs.getMongosByAddress().keySet().stream();
  }
}
