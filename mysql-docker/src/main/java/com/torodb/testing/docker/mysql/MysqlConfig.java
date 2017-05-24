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
package com.torodb.testing.docker.mysql;

import com.google.common.base.Preconditions;
import com.torodb.testing.docker.sql.SqlConfig;

/**
 *
 */
public class MysqlConfig implements SqlConfig {

  private final String username;
  private final String password;
  private final String host;
  private final String db;
  private final MysqlVersion version;

  public MysqlConfig(String username, String password, String host, String db,
      MysqlVersion version) {
    this.username = username;
    this.password = password;
    this.host = host;
    this.db = db;
    this.version = version;
  }

  public static MysqlConfig getDefaultConfig(MysqlVersion version) {
    return new Builder(version).build();
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getHost() {
    return host;
  }

  @Override
  public String getDb() {
    return db;
  }

  public MysqlVersion getVersion() {
    return version;
  }

  public static class Builder {
    private String username = "test";
    private String password = "test";
    private String host = "0.0.0.0";
    private String db = "test";
    private MysqlVersion version;

    public Builder(MysqlVersion version) {
      this.version = version;
    }

    public Builder setUsername(String username) {
      this.username = username;
      return this;
    }

    public Builder setPassword(String password) {
      this.password = password;
      return this;
    }

    public Builder setHost(String host) {
      this.host = host;
      return this;
    }

    public Builder setDb(String db) {
      this.db = db;
      return this;
    }

    public Builder setVersion(MysqlVersion version) {
      this.version = version;
      return this;
    }

    public MysqlConfig build() {
      Preconditions.checkState(version != null, "A postgresql version must be specified");

      return new MysqlConfig(username, password, host, db, version);
    }

  }

}
