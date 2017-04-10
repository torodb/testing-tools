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

package com.torodb.testing.core.junit5;

import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 */
public class MySimplifiedParameterResolver extends SimplifiedParameterResolver<Integer> {

  private static final AtomicInteger intGenerator = new AtomicInteger(0);

  @Override
  protected Class<Integer> getParameterClass() {
    return Integer.class;
  }

  @Override
  protected Integer createParamValue(ExtensionContext context) {
    return intGenerator.incrementAndGet();
  }

  @Override
  protected boolean cleanAfterTest(ExtensionContext context) {
    return findInjectorAnnotation(context).cleanAfterTest();
  }

  @Override
  protected void cleanCallback(Optional<Integer> param) {
    intGenerator.set(0);
  }

  private RequiresSimplifiedParameterResolver findInjectorAnnotation(ExtensionContext context) {
    return AnnotationFinder.resolve(context, RequiresSimplifiedParameterResolver.class);
  }

}
