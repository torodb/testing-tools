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

import static com.torodb.testing.mongodb.docker.MongoRequestRetrier.retry;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.util.concurrent.AbstractIdleService;
import com.google.common.util.concurrent.ServiceManager;
import com.mongodb.MongoClient;
import com.mongodb.MongoServerException;
import com.torodb.testing.mongodb.docker.ReplicaSetConfig.SecondaryConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.BsonDocument;
import org.bson.BsonString;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 */
public class DockerShardedCluster extends AbstractIdleService implements ShardedCluster,
    ShardedClusterMixin {

  private static final Logger LOGGER = LogManager.getLogger(DockerShardedCluster.class);
  private static final String CONFIG_REPL_SET_NAME = "cfg";
  private final ServiceManager serviceManager;
  private final List<ShardReplicaSet> shards;
  private final List<Mongos> routers;
  private final ConfigReplicaSet configRs;
  private MongoClient routerClient;

  public DockerShardedCluster(ShardedClusterConfig config) {
    this.configRs = createConfigRs(config);
    this.routers = createMongos(config, configRs);
    this.shards = config.getShards();
    this.serviceManager = new ServiceManager(
        Iterables.concat(Collections.singleton(configRs), shards, routers)
    );
  }

  @Override
  protected void startUp() throws Exception {
    boolean correctStartup = false;

    try {
      serviceManager.startAsync();
      serviceManager.awaitHealthy();

    
      routerClient = new MongoClient(routers.stream()
          .map(Mongos::getAddress)
          .map(ServerAddressConverter::convert)
          .collect(Collectors.toList())
      );

      initiateSharding();
      
      correctStartup = true;
    } finally {
      if (!correctStartup) {
        LOGGER.debug("Shutting down sub services after a failure at startup time");
        try {
          shutDown();
        } catch (Exception ex) {
          LOGGER.error("Ignored catched exception emergency shutdown");
        }
      }
    }
  }

  @Override
  protected void shutDown() throws Exception {
    if (routerClient != null) {
      routerClient.close();
    }

    serviceManager.stopAsync();
    serviceManager.awaitStopped();
  }

  @Override
  public Collection<ReplicaSet> getShards() throws IllegalStateException {
    checkRunning();
    return Collections.unmodifiableCollection(shards);
  }

  @Override
  public ReplicaSet getShard(int index) {
    checkRunning();
    return shards.get(index);
  }

  @Override
  public MongoClient getClient() {
    checkRunning();
    return routerClient;
  }

  @Override
  public MongoClient getConfigClient() {
    checkRunning();
    return configRs.getClient();
  }

  private void checkRunning() {
    Preconditions.checkState(isRunning(), "The sharded cluster is not running");
  }

  private static ConfigReplicaSet createConfigRs(ShardedClusterConfig config) {
    ReplicaSetConfig.Builder replSetConfigBuilder = ReplicaSetConfig
        .builder(CONFIG_REPL_SET_NAME);
    SecondaryConfig secondaryConfig = SecondaryConfig.createStandardSecondary(
        config.getConfigsVersion(), config.getConfigOplogSize());

    for (int i = 0; i < config.getRouterCardinallity(); i++) {
      replSetConfigBuilder.addSecondary(secondaryConfig);
    }

    return new ConfigReplicaSet(replSetConfigBuilder.build());
  }

  private static List<Mongos> createMongos(ShardedClusterConfig config,
      ConfigReplicaSet configRs) {

    return IntStream.range(0, config.getRouterCardinallity())
        .mapToObj(ignore -> new DependentMongos(
            configRs,
            config.getRoutersVersion()
        ))
        .collect(Collectors.toList());
  }

  private void initiateSharding() {
    for (ShardReplicaSet shard : shards) {
      executeAddShard(shard);
    }
  }

  private BsonDocument createAddShardRequest(ShardReplicaSet shard) {
    ReplMongod anyMongod = shard.getAnyMongod();
    String shardReference = shard.getReplSetName() + "/" + anyMongod.getAddress().toString();

    return new BsonDocument("addShard", new BsonString(shardReference));
  }

  private void executeAddShard(ShardReplicaSet shard) {
    BsonDocument request = createAddShardRequest(shard);

    Supplier<?> requestExecution = () -> {
      return routerClient.getDatabase("admin")
          .runCommand(request);
    };

    BiPredicate<MongoServerException, Integer> stopPredicate = (ex, attempt) -> {
      LOGGER.debug("Error while trying to add the shard " + shard + " for " + attempt + "st time",
          ex);
      try {
        Thread.sleep(500);
      } catch (InterruptedException ex1) {
        Thread.interrupted();
      }
      return attempt > 10;
    };

    retry(requestExecution, stopPredicate);
  }

}
