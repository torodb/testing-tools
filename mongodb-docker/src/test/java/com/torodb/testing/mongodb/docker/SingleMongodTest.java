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
import org.bson.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class SingleMongodTest {

  @ParameterizedTest
  @EnumSource(EnumVersion.class)
  public void startAndStop(EnumVersion version) {

    try (SingleMongod mongoDbService = new SingleMongod(version)) {
      mongoDbService.startAsync();
      mongoDbService.awaitRunning();

      MongoDatabase db = mongoDbService.getMongoClient().getDatabase("testDb");
      MongoCollection<Document> col = db.getCollection("testCol");
      for (int i = 0; i < 10; i++) {
        col.insertOne(new Document());
      }

      Assertions.assertEquals(10, col.count());

      long oplogCount = mongoDbService.getMongoClient()
          .getDatabase("local")
          .getCollection("rs.oplog")
          .count();

      Assertions.assertEquals(0, oplogCount);
    }

  }

}
