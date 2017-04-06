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

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.IntFunction;
import java.util.stream.IntStream;

public interface ShardedClusterContract {

  Logger getLogger();

  ShardedCluster getShardedCluster(ShardedClusterConfig config);

  @Test
  default void startAndStop() {

    try (ShardedCluster cluster = getShardedCluster(smallShardedClusterConfig())) {
      getLogger().debug("Starting cluster...");
      cluster.startAsync();
      cluster.awaitRunning();

      getLogger().debug("Cluster has been started");
      getLogger().debug("Configuring cluster");
  
      cluster.enableSharding("testDb");
      cluster.shardCollection("testDb", "testCol");
      cluster.setChunckSize(1);

      getLogger().debug("Sharding has been configured");

      getLogger().debug("Inserting documents");

      int docsToInsert = 10_000;

      MongoDatabase db = cluster.getClient().getDatabase("testDb");
      MongoCollection<Document> col = db.getCollection("testCol");
      for (int i = 0; i < docsToInsert; i++) {
        col.insertOne(new Document());
      }

      getLogger().debug("Documents have been inserted");

      Assertions.assertEquals(docsToInsert, col.count());
    }
  }

  public default ShardedClusterConfig smallShardedClusterConfig() {

    IntFunction<ReplicaSetConfig> replConfigFactory = i -> ReplicaSetConfig.builder("rs" + i)
        .addSecondary(EnumVersion.LATEST)
        .build();

    ShardedClusterConfig.Builder builder = ShardedClusterConfig.builder()
        .setConfigRs(1, EnumVersion.LATEST, 50)
        .setRouter(1, EnumVersion.LATEST);

    IntStream.range(0, 3)
        .mapToObj(replConfigFactory)
        .forEach(builder::addShard);

    return builder.build();
  }

}
