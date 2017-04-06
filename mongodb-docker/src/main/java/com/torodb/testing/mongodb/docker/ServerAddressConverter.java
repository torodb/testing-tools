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
import com.mongodb.ServerAddress;

/**
 * A utility class that converts from/to {@link ServerAddress} to/from {@link HostAndPort}.
 */
public class ServerAddressConverter {

  private ServerAddressConverter() {}

  public static HostAndPort convert(ServerAddress address) {
    return HostAndPort.fromParts(address.getHost(), address.getPort());
  }

  public static ServerAddress convert(HostAndPort hostAndPort) {
    return new ServerAddress(hostAndPort.getHost(), hostAndPort.getPort());
  }

}
