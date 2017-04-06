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

import com.mongodb.MongoClient;
import com.mongodb.MongoServerException;
import com.mongodb.client.model.UpdateOptions;
import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.BsonString;
import org.bson.conversions.Bson;

/**
 *
 */
public interface ShardedClusterMixin extends ShardedCluster {
  
  @Override
  public default void enableSharding(String dbName) {
    getClient().getDatabase("admin")
        .runCommand(new BsonDocument("enableSharding", new BsonString(dbName)));
  }

  @Override
  public default void shardCollection(String dbName, String collection) {
    shardCollection(dbName, collection, new BsonDocument("_id", new BsonInt32(1)));
  }

  @Override
  public default void shardCollection(String dbName, String colName, Bson key) {
    BsonDocument keyAsBsonDoc = key.toBsonDocument(
        BsonDocument.class,
        MongoClient.getDefaultCodecRegistry()
    );
    getClient().getDatabase("admin")
        .runCommand(new BsonDocument()
            .append("shardCollection", new BsonString(dbName + "." + colName))
            .append("key", keyAsBsonDoc));
  }

  @Override
  public default void setChunckSize(int megas) throws MongoServerException {
    getClient().getDatabase("config")
        .getCollection("settings")
        .updateOne(
            new BsonDocument("_id", new BsonString("chunksize")),
            new BsonDocument("$set", new BsonDocument("value", new BsonInt32(megas))),
            new UpdateOptions()
                .upsert(true));
  }
}
