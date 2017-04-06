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

import com.torodb.testing.core.junit5.AnnotationFinder;
import com.torodb.testing.core.junit5.SimplifiedParameterResolver;
import com.torodb.testing.mongodb.docker.ReplicaSet;
import com.torodb.testing.mongodb.docker.ReplicaSetConfig;
import com.torodb.testing.mongodb.docker.ReplicaSetConfig.SecondaryConfig;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 *
 */
public class ReplicaSetExtension extends SimplifiedParameterResolver<ReplicaSet> {

  @Override
  protected Class<ReplicaSet> getParameterClass() {
    return ReplicaSet.class;
  }

  @Override
  protected ReplicaSet createParamValue(ExtensionContext context) {
    RequiresReplicaSet annotation = findInjectorAnnotation(context);

    ReplicaSetConfig config = transform(annotation);

    ReplicaSet instance = ReplicaSet.create(config);
    
    if (annotation.started()) {
      instance.startAsync();
      instance.awaitRunning();
    }
    return instance;
  }

  @Override
  protected boolean cleanAfterTest(ExtensionContext context) {
    return findInjectorAnnotation(context).newForEachCase();
  }

  private RequiresReplicaSet findInjectorAnnotation(ExtensionContext context) {
    return AnnotationFinder.resolve(context, RequiresReplicaSet.class);
  }

  static ReplicaSetConfig transform(RequiresReplicaSet annotation) {
    return transform(annotation.value());
  }

  static ReplicaSetConfig transform(Rs rs) {

    ReplicaSetConfig.Builder builder = ReplicaSetConfig.builder(rs.replSetName());

    if (rs.protocolVersion() >= 0) {
      builder.setProtocolVersion(rs.protocolVersion());
    }

    for (Rs.DataNode dataNode : rs.value()) {
      builder.addSecondary(transform(dataNode));
    }

    return builder.build();
  }

  static SecondaryConfig transform(Rs.DataNode dataNode) {
    return SecondaryConfig.builder(dataNode.value())
        .setBuildIndexes(dataNode.buildIndexes())
        .setHidden(dataNode.hidden())
        .setOplogSize(dataNode.oplogSize())
        .setPriority(dataNode.priority())
        .setSecondsDelay(dataNode.secondsDelay())
        .setVotes(dataNode.votes())
        .build();
  }
  
}
