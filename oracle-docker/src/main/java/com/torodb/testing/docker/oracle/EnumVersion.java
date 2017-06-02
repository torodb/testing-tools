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

package com.torodb.testing.docker.oracle;

import org.jooq.SQLDialect;

public enum EnumVersion implements OracleVersion {
  
  v11_2_0_2_xe(11, 2, 0, 2, Edition.Value.xe),
  v11_2_0_2_xe_fast(11, 2, 0, 2, Edition.Value.xe_fast),
  
  LATEST(11, 2, 0, 2, Edition.Value.xe_fast);

  private final int major;
  private final int minor;
  private final int subMajor;
  private final int subMinor;
  private final Edition edition;
  private final SQLDialect dialect;

  private EnumVersion(int major, int minor, int subMajor, int subMinor, 
      Edition edition) {
    this.major = major;
    this.minor = minor;
    this.subMajor = subMajor;
    this.subMinor = subMinor;
    this.edition = edition;
    this.dialect = SQLDialect.DEFAULT;
  }

  @Override
  public String getSid() {
    return edition.sid();
  }

  @Override
  public String getDockerImageRef() {
    return "oracle/database:" + major + "." + minor 
        + "." + subMajor + "." + subMinor + "-" + edition.suffix();
  }

  @Override
  public String toString() {
    return major + "." + minor + "." + subMajor + "." + subMinor;
  }

  @Override
  public SQLDialect getDialect() {
    return dialect;
  }
}
