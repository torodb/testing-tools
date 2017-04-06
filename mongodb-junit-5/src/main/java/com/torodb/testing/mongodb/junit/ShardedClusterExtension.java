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
import com.torodb.testing.mongodb.docker.DockerShardedCluster;
import com.torodb.testing.mongodb.docker.ShardedCluster;
import com.torodb.testing.mongodb.docker.ShardedClusterConfig;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 *
 */
public class ShardedClusterExtension extends SimplifiedParameterResolver<ShardedCluster> {

  @Override
  protected Class<ShardedCluster> getParameterClass() {
    return ShardedCluster.class;
  }

  @Override
  protected ShardedCluster createParamValue(ExtensionContext context) {
    RequiresShardedCluster annotation = findInjectorAnnotation(context);

    ShardedClusterConfig config = transform(annotation);

    ShardedCluster instance = new DockerShardedCluster(config);
    
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

  private RequiresShardedCluster findInjectorAnnotation(ExtensionContext context) {
    return AnnotationFinder.resolve(context, RequiresShardedCluster.class);
  }

  static ShardedClusterConfig transform(RequiresShardedCluster annotation) {
    Cluster cluster = annotation.value();
    ShardedClusterConfig.Builder builder = ShardedClusterConfig.builder();

    builder.setRouter(cluster.router().cardinallity(), cluster.router().version());
    builder.setConfigRs(
        cluster.configRs().cardinallity(),
        cluster.configRs().version(),
        cluster.configRs().oplogSize()
    );
    for (Rs shard : cluster.value()) {
      builder.addShard(ReplicaSetExtension.transform(shard));
    }

    return builder.build();
  }
  
}
