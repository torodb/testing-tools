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
import com.google.common.net.HostAndPort;
import com.mongodb.MongoClientOptions;
import com.torodb.testing.docker.ImageInstaller;
import com.torodb.testing.docker.WaitCondition;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A {@link AbstractMongod} designed to be used as a Mongos.
 */
public abstract class Mongos extends AbstractMongod {

  public Mongos(Version version, MongoClientOptions clientOptions,
      WaitCondition waitCondition, ImageInstaller imageInstaller) {
    super(version, clientOptions, waitCondition, imageInstaller);
  }

  public Mongos(Version version, MongoClientOptions clientOptions) {
    super(version, clientOptions);
  }

  public Mongos(Version version) {
    super(version);
  }

  protected abstract String getConfigReplSetName();

  protected abstract Stream<HostAndPort> streamConfigNodesAddresses();

  @Override
  protected List<String> getCmd() {
    List<String> cmdList = Lists.newArrayList(
        "mongos",
        "--configdb",
        getConfigDbArgValue()
    );
    return cmdList;
  }

  private String getConfigDbArgValue() {
    String addresses = streamConfigNodesAddresses()
        .map(HostAndPort::toString)
        .collect(Collectors.joining(","));
    return getConfigReplSetName() + "/" + addresses;
  }

}
