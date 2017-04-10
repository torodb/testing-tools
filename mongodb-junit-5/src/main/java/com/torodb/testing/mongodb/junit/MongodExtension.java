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
import com.torodb.testing.core.junit5.CloseableParameterResolver;
import com.torodb.testing.mongodb.docker.Mongod;
import com.torodb.testing.mongodb.docker.SingleMongod;
import org.junit.jupiter.api.extension.ExtensionContext;


/**
 *
 */
public class MongodExtension extends CloseableParameterResolver<Mongod> {

  @Override
  protected Class<Mongod> getParameterClass() {
    return Mongod.class;
  }

  @Override
  protected Mongod createParamValue(ExtensionContext context) {
    RequiresMongod annotation = findInjectorAnnotation(context);

    SingleMongod instance = new SingleMongod(annotation.version());
    
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

  private RequiresMongod findInjectorAnnotation(ExtensionContext context) {
    return AnnotationFinder.resolve(context, RequiresMongod.class);
  }
  
}
