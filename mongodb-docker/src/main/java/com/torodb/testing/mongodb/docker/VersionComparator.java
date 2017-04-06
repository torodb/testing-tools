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

import java.io.Serializable;
import java.util.Comparator;

/**
 *
 */
public class VersionComparator implements Comparator<Version>, Serializable {

  private static final long serialVersionUID = 538245283845912L;

  private VersionComparator() {
  }

  public static VersionComparator getInstance() {
    return MongoDbVersionComparatorHolder.INSTANCE;
  }


  @Override
  public int compare(Version o1, Version o2) {
    int diff;

    diff = o1.getMajorVersion() - o2.getMajorVersion();
    if (diff != 0) {
      return diff;
    }

    return o1.getMinorVersion() - o2.getMinorVersion();
  }

  private static class MongoDbVersionComparatorHolder {

    private static final VersionComparator INSTANCE = new VersionComparator();
  }

  //@edu.umd.cs.findbugs.annotations.SuppressFBWarnings(value = "UPM_UNCALLED_PRIVATE_METHOD")
  private Object readResolve() {
    return VersionComparator.getInstance();
  }
}
