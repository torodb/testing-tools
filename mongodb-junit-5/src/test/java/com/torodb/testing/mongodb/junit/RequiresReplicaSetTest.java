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

package com.torodb.testing.mongodb.junit;

import static java.time.Duration.ofMinutes;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.torodb.testing.mongodb.docker.EnumVersion;
import com.torodb.testing.mongodb.docker.ReplicaSet;
import com.torodb.testing.mongodb.junit.Rs.DataNode;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@RequiresReplicaSet(
    @Rs(
        value = {
          @DataNode(EnumVersion.LATEST),
          @DataNode(EnumVersion.LATEST),
          @DataNode(EnumVersion.LATEST)
        },
        protocolVersion = 0
    )
)
public class RequiresReplicaSetTest {

  @Test
  public void startAndStop(ReplicaSet rs) {
    assertTimeout(ofMinutes(2), () -> {
      MongoDatabase db = rs.getClient().getDatabase("testDb");
      MongoCollection<Document> col = db.getCollection("testCol").withWriteConcern(
          rs.getTotalWriteConcern()
      );
      for (int i = 0; i < 10; i++) {
        col.insertOne(new Document());
      }
    });
    
  }
}
