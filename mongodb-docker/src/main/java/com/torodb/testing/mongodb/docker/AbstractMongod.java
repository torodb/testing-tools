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
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.HostConfig;
import com.spotify.docker.client.messages.Volume;
import com.torodb.testing.docker.ImageInstaller;
import com.torodb.testing.docker.ImagePuller;
import com.torodb.testing.docker.SimpleDockerService;
import com.torodb.testing.docker.UntilStdLineContains;
import com.torodb.testing.docker.WaitCondition;

import java.util.stream.Stream;



/**
 *
 */
public abstract class AbstractMongod extends SimpleDockerService implements Mongod {

  static final int MONGOD_PORT = 27017;
  private final Version version;
  private final MongoClientOptions clientOptions;
  private MongoClient mongoClient;
  private HostAndPort address;

  public AbstractMongod(Version version, MongoClientOptions clientOptions,
      WaitCondition waitCondition, ImageInstaller imageInstaller) {
    super(waitCondition, imageInstaller);
    this.version = version;
    this.clientOptions = clientOptions;
  }

  public AbstractMongod(Version version, MongoClientOptions clientOptions) {
    this(
        version,
        clientOptions,
        new UntilStdLineContains("waiting for connections on port"),
        new ImagePuller(version.getDockerImageRef())
    );
  }

  public AbstractMongod(Version version) {
    this(
        version,
        MongoClientOptions.builder().build(),
        new UntilStdLineContains("waiting for connections on port"),
        new ImagePuller(version.getDockerImageRef())
    );
  }

  @Override
  public String toString() {
    if (isRunning()) {
      return getAddress().toString();
    }
    return super.toString();
  }

  @Override
  protected String getImageRef() {
    return version.getDockerImageRef();
  }

  @Override
  protected void afterStarted(DockerClient client, String containerId) throws DockerException,
      InterruptedException {
    this.address = HostAndPort.fromParts(getIp(), MONGOD_PORT);
    mongoClient = new MongoClient(
        new ServerAddress(address.getHost(), address.getPort()),
        clientOptions
    );
  }

  @Override
  public HostAndPort getAddress() {
    checkRunning();
    return address;
  }
  
  @Override
  public MongoClient getMongoClient() {
    checkRunning();
    return mongoClient;
  }

  private String getDbVolumeName() {
    return "mongo-db-" + System.identityHashCode(this);
  }

  private String getConfigdbVolumeName() {
    return "mongo-configdb-" + System.identityHashCode(this);
  }

  @Override
  protected void additionalHostConfiguration(DockerClient client,
      HostConfig.Builder builder) throws DockerException, InterruptedException {
    Volume dbData = client.createVolume(Volume.builder()
        .name(getDbVolumeName())
        .build()
    );
    Volume configData = client.createVolume(Volume.builder()
        .name(getConfigdbVolumeName())
        .build()
    );
    builder.appendBinds(
        HostConfig.Bind.from(dbData).to("/data/db").build(),
        HostConfig.Bind.from(configData).to("/data/configdb").build()
    );
  }

  @Override
  protected Stream<String> streamVolumesNamesToRemove() {
    return Stream.of(
        getDbVolumeName(),
        getConfigdbVolumeName()
    );
  }
}
