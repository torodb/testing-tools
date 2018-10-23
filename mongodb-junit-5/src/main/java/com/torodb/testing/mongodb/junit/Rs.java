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

package com.torodb.testing.mongodb.junit;

import com.torodb.testing.mongodb.docker.EnumVersion;

/**
 *
 */
public @interface Rs {

  String replSetName() default "rs0";

  /**
   * The protocol version the replica set must use.
   *
   * This is an optional attribute. If no explicit value is supplied, the protocol version will be
   * choosed by the replica set.
   * @return
   */
  long protocolVersion() default -1;

  DataNode[] value();

  public static @interface DataNode {
    EnumVersion value();
    int oplogSize() default 50;
    double priority() default 1;
    int votes() default 1;
    long secondsDelay() default 0;
    boolean hidden() default false;
    boolean buildIndexes() default true;
  }
}
