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
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;
import com.torodb.testing.core.CloseableService;
import com.torodb.testing.mongodb.docker.ReplicaSetConfig.SecondaryConfig;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

/**
 *
 */
public interface ReplicaSet extends CloseableService {

  public static ReplicaSet create(ReplicaSetConfig config) {
    return new DockerReplicaSet(config);
  }

  public String getReplSetName();

  /**
   * Returns the primary mongo, if any.
   *
   * @throws IllegalStateException if the service is not running
   */
  public Optional<ReplMongod> getPrimary() throws IllegalStateException;

  /**
   * Returns a not specified {@link ReplMongod} contained by this replica set.
   *
   * @throws IllegalStateException if the service is not running
   */
  public default ReplMongod getAnyMongod() throws IllegalStateException {
    return getMongos().stream().findAny()
        .orElseThrow(() -> new AssertionError("At least one mongod is required"));
  }

  /**
   * Returns the mongos.
   *
   * @throws IllegalStateException if the service is not running
   */
  public default Collection<ReplMongod> getMongos() throws IllegalStateException {
    return getMongosByAddress().values();
  }

  /**
   * Returns the mongos indexed by {@link HostAndPort address}.
   *
   * @throws IllegalStateException if the service is not running
   */
  public Map<HostAndPort, ReplMongod> getMongosByAddress() throws IllegalStateException;

  /**
   * Returns the mongos indexed by {@link SecondaryConfig config}.
   *
   * @throws IllegalStateException if the service is not running
   */
  public Map<SecondaryConfig, ReplMongod> getMongosByConfig() throws IllegalStateException;

  /**
   * Returns a {@link MongoClient} configured to see all mongos.
   *
   * @throws IllegalStateException if the service is not running
   */
  public MongoClient getClient() throws IllegalStateException;

  /**
   * Returns a {@link WriteConcern} that guarantees that the write will be seen by all nodes before
   * returning.
   *
   * @throws IllegalStateException if the service is not running
   */
  public default WriteConcern getTotalWriteConcern() throws IllegalStateException {
    return new WriteConcern(getMongos().size());
  }


}
