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
package com.torodb.testing.docker.postgres;

import org.jooq.SQLDialect;

/**
 *
 */
public enum EnumVersion implements PostgresVersion {
  v9_0(9,0, SQLDialect.POSTGRES),
  v9_1(9,1, SQLDialect.POSTGRES),
  v9_2(9,2, SQLDialect.POSTGRES),
  v9_3(9,3, SQLDialect.POSTGRES_9_3),
  v9_4(9,4, SQLDialect.POSTGRES_9_4),
  v9_5(9,5, SQLDialect.POSTGRES_9_5),
  v9_6(9,6, SQLDialect.POSTGRES_9_5),
  
  LATEST(9,6, SQLDialect.POSTGRES_9_5);

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
    return "postgres:" + major + "." + minor;
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
