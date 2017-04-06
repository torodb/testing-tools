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

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalLong;

/**
 *
 */
public class ReplicaSetConfig {

  private final String replSetName;
  private final OptionalLong protocolVersion;
  private final List<SecondaryConfig> secondaries;

  public ReplicaSetConfig(String replSetName, OptionalLong protocolVersion,
      List<SecondaryConfig> secondaries) {
    this.replSetName = replSetName;
    this.protocolVersion = protocolVersion;
    this.secondaries = secondaries;
  }

  public ReplicaSetConfig(String replSetName, List<SecondaryConfig> secondaries, boolean shard) {
    this(replSetName, OptionalLong.empty(), secondaries);
  }

  public String getReplSetName() {
    return replSetName;
  }

  public List<SecondaryConfig> getSecondaries() {
    return secondaries;
  }

  public OptionalLong getProtocolVersion() {
    return protocolVersion;
  }

  public static Builder builder(String replSetName) {
    return new Builder(replSetName);
  }

  public static class Builder {

    private final String replSetName;
    private OptionalLong protocolVersion = OptionalLong.empty();
    private final List<SecondaryConfig> secondaries = new ArrayList<>();

    public Builder(String replSetName) {
      this.replSetName = replSetName;
    }

    public Builder setProtocolVersion(long protocolVersion) {
      return setProtocolVersion(OptionalLong.of(protocolVersion));
    }

    public Builder setProtocolVersion(OptionalLong protocolVersion) {
      this.protocolVersion = protocolVersion;
      return this;
    }

    public Builder addSecondary(SecondaryConfig config) {
      this.secondaries.add(config);
      return this;
    }

    public Builder addSecondary(
        SecondaryConfig.Builder configBuilder) {
      this.secondaries.add(configBuilder.build());
      return this;
    }

    public Builder addSecondary(Version version) {
      return addSecondary(SecondaryConfig.builder(EnumVersion.LATEST));
    }

    public ReplicaSetConfig build() {
      return new ReplicaSetConfig(replSetName, protocolVersion,
          new ArrayList<>(secondaries));
    }
  }

  public static class SecondaryConfig {

    private final Version version;
    private final int oplogSize;
    private final double priority;
    private final int votes;
    private final boolean arbiter;
    private final long secondsDelay;
    private final boolean hidden;
    private final boolean buildIndexes;

    public SecondaryConfig(Version version, int oplogSize, double priority, int votes,
        boolean arbiter, long secondsDelay, boolean hidden, boolean buildIndexes) {
      this.version = version;
      this.oplogSize = oplogSize;
      this.priority = priority;
      this.votes = votes;
      this.arbiter = arbiter;
      this.secondsDelay = secondsDelay;
      this.hidden = hidden;
      this.buildIndexes = buildIndexes;
    }

    public static Builder builder(Version version) {
      return new Builder(version);
    }

    public static SecondaryConfig createStandardSecondary(Version version, int oplogSize) {
      return builder(version).setOplogSize(oplogSize).build();
    }

    public static SecondaryConfig createArbiter(Version version) {
      return createArbiter(version, 1);
    }

    public static SecondaryConfig createArbiter(Version version, int votes) {
      return new SecondaryConfig(version, 0, 0, votes, true, 0, false, false);
    }

    public Version getVersion() {
      return version;
    }

    public int getOplogSize() {
      return oplogSize;
    }

    public double getPriority() {
      return priority;
    }

    public int getVotes() {
      return votes;
    }

    public boolean isArbiter() {
      return arbiter;
    }

    public long getSecondsDelay() {
      return secondsDelay;
    }

    public boolean isHidden() {
      return hidden;
    }

    public boolean isBuildIndexes() {
      return buildIndexes;
    }

    public static class Builder {

      private Version version;
      private double priority = 1.0;
      private int votes = 1;
      private long secondsDelay = 0;
      private boolean hidden = false;
      private boolean buildIndexes = true;
      private int oplogSize = 50;

      private Builder(Version version) {
        this.version = version;
      }

      public Builder setVersion(Version version) {
        this.version = version;
        return this;
      }

      public Builder setPriority(double priority) {
        this.priority = priority;
        return this;
      }

      public Builder setVotes(int votes) {
        this.votes = votes;
        return this;
      }

      public Builder setSecondsDelay(long secondsDelay) {
        this.secondsDelay = secondsDelay;
        return this;
      }

      public Builder setHidden(boolean hidden) {
        this.hidden = hidden;
        return this;
      }

      public Builder setBuildIndexes(boolean buildIndexes) {
        this.buildIndexes = buildIndexes;
        return this;
      }

      public Builder setOplogSize(int oplogSize) {
        this.oplogSize = oplogSize;
        return this;
      }

      public SecondaryConfig build() {
        return new SecondaryConfig(version, oplogSize, priority, votes, false, secondsDelay, hidden,
            buildIndexes);
      }
    }
  }

}
