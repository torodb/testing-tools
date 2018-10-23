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

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 */
public class ShardedClusterConfig {

  private final int routerCardinallity;
  private final Version routersVersion;
  private final int configMongodCardinallity;
  private final Version configsVersion;
  private final int configOplogSize;
  private final List<ShardReplicaSet> shards;

  public ShardedClusterConfig(int routerCardinallity, Version routersVersion,
      int configMongodCardinallity, Version configsVersion, int configOplogSize,
      List<ShardReplicaSet> shards) {
    Preconditions.checkArgument(!shards.isEmpty(), "At least one shard is required");
    Preconditions.checkArgument(routerCardinallity > 0, "At least one router is required");
    Preconditions.checkArgument(configMongodCardinallity > 0,
        "At least one config node is " + "required");
    this.routerCardinallity = routerCardinallity;
    this.routersVersion = routersVersion;
    this.configMongodCardinallity = configMongodCardinallity;
    this.configsVersion = configsVersion;
    this.configOplogSize = configOplogSize;
    this.shards = shards;
  }

  public static Builder builder() {
    return new Builder();
  }

  public int getRouterCardinallity() {
    return routerCardinallity;
  }

  public int getConfigMongodCardinallity() {
    return configMongodCardinallity;
  }

  public List<ShardReplicaSet> getShards() {
    return Collections.unmodifiableList(shards);
  }

  public Version getRoutersVersion() {
    return routersVersion;
  }

  public Version getConfigsVersion() {
    return configsVersion;
  }

  public int getConfigOplogSize() {
    return configOplogSize;
  }

  public static class Builder {

    private int routerCardinallity = 1;
    private Version routersVersion = EnumVersion.LATEST;
    private int configMongodCardinallity = 3;
    private Version configVersion = EnumVersion.LATEST;
    private int configOplogSize = 50;
    private final List<ShardReplicaSet> shards = new ArrayList<>();

    public Builder setRouter(int cardinallity, Version version) {
      this.routerCardinallity = cardinallity;
      this.routersVersion = version;
      return this;
    }

    public Builder setConfigRs(int cardinallity, Version version, int oplogSize) {
      this.configMongodCardinallity = cardinallity;
      this.configVersion = version;
      this.configOplogSize = oplogSize;
      return this;
    }

    public Builder addShard(ShardReplicaSet shard) {
      shards.add(shard);
      return this;
    }

    public Builder addShard(ReplicaSetConfig replSetConfig) {
      shards.add(new ShardReplicaSet(replSetConfig));
      return this;
    }

    public ShardedClusterConfig build() {
      return new ShardedClusterConfig(routerCardinallity, routersVersion, configMongodCardinallity,
          configVersion, configOplogSize,
          new ArrayList<>(shards));
    }
  }

}
