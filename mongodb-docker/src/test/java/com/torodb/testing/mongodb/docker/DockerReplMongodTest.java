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

import com.google.common.collect.Lists;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.torodb.testing.mongodb.docker.ReplMongod.Config;
import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.BsonString;
import org.bson.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class DockerReplMongodTest {

  public DockerReplMongodTest() {
  }

  @ParameterizedTest
  @EnumSource(EnumVersion.class)
  public void startAndStop(EnumVersion version) {
    if (version == EnumVersion.LATEST) {
      return;
    }

    try (ReplMongod mongoDbService = createService(version)) {
      mongoDbService.startAsync();
      mongoDbService.awaitRunning();

      replInitiate(mongoDbService);

      MongoClient client = mongoDbService.getMongoClient();
      
      MongoDatabase db = client.getDatabase("testDb");
      MongoCollection<Document> col = db.getCollection("testCol");
      for (int i = 0; i < 10; i++) {
        col.insertOne(new Document());
      }

      Assertions.assertEquals(10, col.count());

      long oplogCount = client.getDatabase("local")
          .getCollection("rs.oplog")
          .count();

      Assertions.assertNotEquals(0, oplogCount);
    }

  }

  private ReplMongod createService(EnumVersion version) {
    return new ReplMongod(new Config("rs0", 50), version);
  }

  private void replInitiate(ReplMongod service) {
    BsonDocument replInitiateCommand = new BsonDocument()
        .append("replSetInitiate", new BsonDocument()
            .append("_id", new BsonString("rs0"))
            .append("members", new BsonArray(Lists.newArrayList(
                new BsonDocument("_id", new BsonInt32(0))
                .append("host", new BsonString(service.getAddress().toString()))
            )))
        );

    service.getMongoClient().getDatabase("admin")
        .runCommand(replInitiateCommand);
  }
}
