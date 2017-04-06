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

import java.lang.annotation.Annotation;

/**
 *
 */
public class AnnotationFinder {

  public static <A extends Annotation> A resolveOnClass(Class<?> testClass, Class<A> clazz) {
    return testClass.getAnnotation(clazz);
  }

  public static  <A extends Annotation> A resolve(ExtensionContext context, Class<A> clazz) {
    Class<?> testClass = context.getTestClass().get();
    
    return resolveOnClass(testClass, clazz);
  }

}
