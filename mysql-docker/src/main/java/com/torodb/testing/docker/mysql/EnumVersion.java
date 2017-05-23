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

import org.jooq.SQLDialect;

/**
 *
 */
public enum EnumVersion implements MysqlVersion {
  v5_5(5,5, SQLDialect.MYSQL),
  v5_6(5,6, SQLDialect.MYSQL),
  v5_7(5,7, SQLDialect.MYSQL),
  v8_0(8,0, SQLDialect.MYSQL),
  
  LATEST(5,7, SQLDialect.MYSQL);

  private final int major;
  private final int minor;
  private final SQLDialect dialect;

  private EnumVersion(int major, int minor, SQLDialect dialect) {
    this.major = major;
    this.minor = minor;
    this.dialect = dialect;
  }

  @Override
  public String getDockerImageRef() {
    return "mysql:" + major + "." + minor;
  }

  @Override
  public String toString() {
    return major + "." + minor;
  }

  @Override
  public SQLDialect getDialect() {
    return dialect;
  }
}
