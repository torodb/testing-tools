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
import static com.torodb.testing.mongodb.docker.ServerAddressConverter.convert;

import com.google.common.net.HostAndPort;
import com.google.common.util.concurrent.AbstractIdleService;
import com.google.common.util.concurrent.ServiceManager;
import com.mongodb.MongoClient;
import com.mongodb.MongoServerException;
import com.mongodb.ServerAddress;
import com.torodb.testing.mongodb.docker.ReplicaSetConfig.SecondaryConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.BsonArray;
import org.bson.BsonBoolean;
import org.bson.BsonDocument;
import org.bson.BsonDouble;
import org.bson.BsonInt32;
import org.bson.BsonInt64;
import org.bson.BsonString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 *
 */
abstract class AbstractReplicaSet extends AbstractIdleService implements ReplicaSet {
  private static final Logger LOGGER = LogManager.getLogger(AbstractReplicaSet.class);
  private final ReplicaSetConfig config;
  private final ServiceManager serviceManager;
  private Map<HostAndPort, ReplMongod> mongosByAddress;
  private final Map<SecondaryConfig, ReplMongod> mongosByConfig;
  private MongoClient replClient;

  AbstractReplicaSet(ReplicaSetConfig config, ReplMongodFactory nodeFactory) {
    this.config = config;
    this.mongosByConfig = createMongoByConfig(config, nodeFactory);
    this.serviceManager = new ServiceManager(mongosByConfig.values());
  }

  @Override
  public String getReplSetName() {
    return config.getReplSetName();
  }

  @Override
  protected void startUp() throws Exception {
    boolean correctStartup = false;

    try {
      serviceManager.startAsync();
      try {
        serviceManager.awaitHealthy();
      } catch (IllegalStateException ex) {
        serviceManager.servicesByState().get(State.FAILED).stream()
            .forEach(service -> LOGGER.error("Service " + service + " failed", failureCause()));
        throw ex;
      }

      this.mongosByAddress = mongosByConfig.values().stream()
          .collect(Collectors.toMap(
              ReplMongod::getAddress,
              Function.identity())
          );

      initiateReplicas();

      LOGGER.debug("Getting the replica set mongo client");

      replClient = new MongoClient(mongosByAddress.keySet().stream()
          .map(ServerAddressConverter::convert)
          .collect(Collectors.toList())
      );
      correctStartup = true;

      LOGGER.debug("The replication set with name {} has been started", getReplSetName());

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
    replClient.close();

    serviceManager.stopAsync();
    serviceManager.awaitStopped();
  }

  @Override
  public Optional<ReplMongod> getPrimary() {
    checkRunning();
    ServerAddress primaryAddress = replClient.getReplicaSetStatus().getMaster();
    if (primaryAddress == null) {
      return Optional.empty();
    }
    HostAndPort primaryHostAndPort = convert(primaryAddress);

    ReplMongod primary = mongosByAddress.get(primaryHostAndPort);
    if (primary == null) {
      throw new AssertionError("Primary has the address " + primaryHostAndPort + " that is not "
          + "found on the nodes map");
    }
    return Optional.of(primary);
  }

  @Override
  public Map<HostAndPort, ReplMongod> getMongosByAddress() {
    return Collections.unmodifiableMap(mongosByAddress);
  }

  @Override
  public Map<SecondaryConfig, ReplMongod> getMongosByConfig() {
    return Collections.unmodifiableMap(mongosByConfig);
  }

  @Override
  public MongoClient getClient() {
    checkRunning();
    return replClient;
  }

  private void checkRunning() {
    if (!isRunning()) {
      throw new IllegalStateException("The replica set service is not running");
    }
  }

  private void initiateReplicas() throws InterruptedException {
    ReplMongod aMongo = mongosByAddress.values().stream().findAny().orElseThrow(
        () -> new AssertionError("At least one mongo is required")
    );

    Supplier<Void> task = () -> {
      aMongo.getMongoClient()
          .getDatabase("admin")
          .runCommand(createReplSetInitiateRequest());
      return null;
    };

    BiPredicate<MongoServerException, Integer> stopPredicate = (ex, attempt) -> {
      try {
        LOGGER.trace("Error while trying to initiate the replica set", ex);
        Thread.sleep(1000);
      } catch (InterruptedException ex1) {
        Thread.interrupted();
      }
      return attempt > 10;
    };

    retry(task, stopPredicate);
    
    waitForStableConfig();
  }
  
  private BsonDocument createReplSetInitiateRequest() {
    BsonDocument subDoc = new BsonDocument()
        .append("_id", new BsonString(config.getReplSetName()))
        .append("members", createMembersRequest());
    
    config.getProtocolVersion()
        .ifPresent(version -> subDoc.append("protocolVersion", new BsonInt64(version)));
    
    return new BsonDocument("replSetInitiate", subDoc);
  }

  private BsonArray createMembersRequest() {
    BsonArray array = new BsonArray();
    AtomicInteger counter = new AtomicInteger();
    IntSupplier idSupplier = counter::getAndIncrement;
    
    for (Map.Entry<SecondaryConfig, ReplMongod> entry : mongosByConfig.entrySet()) {
      array.add(toMemberConfig(entry.getKey(), entry.getValue(), idSupplier));
    }

    return array;
  }

  private BsonDocument toMemberConfig(SecondaryConfig config, ReplMongod mongo,
      IntSupplier idSupplier) {
    BsonDocument memberConfig = new BsonDocument()
        .append("_id", new BsonInt32(idSupplier.getAsInt()))
        .append("host", new BsonString(mongo.getAddress().toString()));

    if (config.isArbiter()) {
      memberConfig.append("arbiterOnly", BsonBoolean.TRUE);
    } else {
      memberConfig.append("priority", new BsonDouble(config.getPriority()))
          .append("hidden", BsonBoolean.valueOf(config.isHidden()))
          .append("slaveDelay", new BsonInt64(config.getSecondsDelay()))
          .append("buildIndexes", BsonBoolean.valueOf(config.isBuildIndexes()))
          .append("votes", new BsonInt32(config.getVotes()));
    }
    return memberConfig;
  }

  private void waitForStableConfig() throws InterruptedException {
    int maxConfigVersion = 0;
    List<ReplMongod> staleMongos = new ArrayList<>(mongosByAddress.values());
    while (!staleMongos.isEmpty()) {
      ReplMongod choosedMongo = staleMongos.get(0);
      BsonDocument currentConfig = getReplicaSetConfig(choosedMongo);

      if (!currentConfig.containsKey("version")) {
        Thread.sleep(1000);
        continue;
      }
      
      int currentConfigVer = currentConfig.getNumber("version").intValue();
      if (currentConfigVer < maxConfigVersion) {
        LOGGER.debug("Mongo {} is still using the old config v{}", choosedMongo, currentConfigVer);
        Thread.sleep(1000);
        continue;
      }
      if (currentConfigVer == maxConfigVersion) {
        staleMongos.remove(0);
      } else {
        //We found a fresher repl config, so we have to start again
        LOGGER.debug("Mongo {} has a fresher repl config (v{}), asking all nodes again ",
            choosedMongo, currentConfigVer);
        assert currentConfigVer > maxConfigVersion;
        maxConfigVersion = currentConfigVer;
        staleMongos.clear();
        staleMongos.addAll(mongosByAddress.values());
      }
    }
    LOGGER.debug("Replica is using the stable config with version {}", maxConfigVersion);
  }
  
  private BsonDocument getReplicaSetConfig(ReplMongod mongo) {
    BsonDocument request = new BsonDocument("replSetGetConfig", new BsonDouble(1));

    Supplier<BsonDocument> requestExecution = () -> {
      BsonDocument reply = mongo.getMongoClient().getDatabase("admin")
          .runCommand(request, BsonDocument.class);
      return reply.getDocument("config");
    };

    BiPredicate<MongoServerException, Integer> stopPredicate = (ex, attempt) -> {
      LOGGER.trace("Error while trying to get the replica set config from "
          + mongo.getAddress() + " for " + attempt + "st time", ex);
      try {
        Thread.sleep(1000);

      } catch (InterruptedException ex1) {
        Thread.interrupted();
      }
      return attempt > 10;
    };

    return retry(requestExecution, stopPredicate);
  }

  private static Map<SecondaryConfig, ReplMongod> createMongoByConfig(
      ReplicaSetConfig config, ReplMongodFactory nodeFactory) {
    return config.getSecondaries().stream()
        .collect(Collectors.toMap(
            Function.identity(),
            secondary -> nodeFactory.apply(config.getReplSetName(), secondary)
        ));
  }

  @FunctionalInterface
  public static interface ReplMongodFactory extends
      BiFunction<String, SecondaryConfig, ReplMongod> {

    @Override
    public ReplMongod apply(String replSetName, SecondaryConfig config);

  }
}
