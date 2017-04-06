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
import com.mongodb.MongoClientOptions;
import com.torodb.testing.docker.ImageInstaller;
import com.torodb.testing.docker.WaitCondition;

import java.util.List;

public class ReplMongod extends AbstractMongod {

  private final Config config;

  public ReplMongod(Config config, Version version, MongoClientOptions clientOptions,
      WaitCondition waitCondition, ImageInstaller imageInstaller) {
    super(version, clientOptions, waitCondition, imageInstaller);
    this.config = config;
  }

  public ReplMongod(Config config, Version version,
      MongoClientOptions clientOptions) {
    super(version, clientOptions);
    this.config = config;
  }

  public ReplMongod(Config config, Version version) {
    super(version, new MongoClientOptions.Builder().build());
    this.config = config;
  }

  @Override
  protected List<String> getCmd() {
    List<String> cmdList = Lists.newArrayList(
        "mongod",
        "--replSet",
        config.getReplSetName(),
        "--smallfiles",
        "--oplogSize",
        Integer.toString(config.getOplogSize())
    );
    return cmdList;
  }

  public static class Config {
    private final String replSetName;
    private final int oplogSize;

    public Config(String replSetName, int oplogSize) {
      this.replSetName = replSetName;
      this.oplogSize = oplogSize;
    }

    public String getReplSetName() {
      return replSetName;
    }

    public int getOplogSize() {
      return oplogSize;
    }
  }

}
