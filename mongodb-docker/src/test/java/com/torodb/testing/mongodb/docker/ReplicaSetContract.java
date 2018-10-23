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

/**
 *
 */
public interface ReplicaSetContract {

  Logger getLogger();

  ReplicaSet getReplicaSet(ReplicaSetConfig config);

  @Test
  default void genericTest() {
    int docsToInsert = 100;

    try (ReplicaSet replicaSet = getReplicaSet(simpleReplConfig())) {
      replicaSet.startAsync();
      replicaSet.awaitRunning();

      getLogger().debug("Inserting {} documents on {}", docsToInsert, replicaSet.getReplSetName());
      MongoDatabase db = replicaSet.getClient().getDatabase("testDb");
      MongoCollection<Document> col = db.getCollection("testCol").withWriteConcern(
          replicaSet.getTotalWriteConcern()
      );
      for (int i = 0; i < docsToInsert; i++) {
        col.insertOne(new Document());
      }

      Assertions.assertEquals(docsToInsert, col.count());
      
      for (ReplMongod mongo : replicaSet.getMongos()) {
        getLogger().debug("Assertions on node {}", mongo);

        long oplogCount = mongo.getMongoClient()
            .getDatabase("local")
            .getCollection("rs.oplog")
            .count();

        Assertions.assertNotEquals(0, oplogCount, "Oplog on node " + mongo + " is empty");

        long colCount = mongo.getMongoClient()
            .getDatabase("testDb")
            .getCollection("testCol")
            .count();

        Assertions.assertEquals(docsToInsert, colCount, "Incorrect number of documents replicated");
      }
    }
  }

  public default ReplicaSetConfig simpleReplConfig() {
    return ReplicaSetConfig.builder("rs0")
        .addSecondary(EnumVersion.LATEST)
        .addSecondary(EnumVersion.LATEST)
        .addSecondary(EnumVersion.v3_2)
        .build();
  }

}
