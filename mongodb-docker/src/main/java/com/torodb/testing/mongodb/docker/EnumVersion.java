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

/**
 *
 */
public enum EnumVersion implements Version {
  v3_0(3,0),
  v3_2(3,2),
  v3_4(3,4),
  
  LATEST(3,4);

  private final int major;
  private final int minor;

  private EnumVersion(int major, int minor) {
    this.major = major;
    this.minor = minor;
  }

  @Override
  public int getMajorVersion() {
    return major;
  }

  @Override
  public int getMinorVersion() {
    return minor;
  }

  @Override
  public String getDockerImageRef() {
    return "mongo:" + major + "." + minor;
  }
}
