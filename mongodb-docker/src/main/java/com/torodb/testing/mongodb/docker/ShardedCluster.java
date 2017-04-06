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
import com.torodb.testing.core.CloseableService;
import org.bson.conversions.Bson;

import java.util.Collection;


/**
 *
 */
public interface ShardedCluster extends CloseableService {

  Collection<ReplicaSet> getShards() throws IllegalStateException;

  ReplicaSet getShard(int index) throws IllegalStateException, IndexOutOfBoundsException;

  MongoClient getClient() throws IllegalStateException;

  MongoClient getConfigClient() throws IllegalStateException;

  void enableSharding(String dbName) throws MongoServerException;

  void shardCollection(String dbName, String collection) throws MongoServerException;

  void shardCollection(String dbName, String colName, Bson key) throws MongoServerException;

  void setChunckSize(int megas) throws MongoServerException;

}
