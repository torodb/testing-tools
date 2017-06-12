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

package com.torodb.testing.docker.mysql.junit5;

import com.torodb.testing.core.junit5.AnnotationFinder;
import com.torodb.testing.core.junit5.CloseableParameterResolver;
import com.torodb.testing.docker.mysql.MysqlConfig;
import com.torodb.testing.docker.mysql.MysqlService;
import org.junit.jupiter.api.extension.ExtensionContext;

public class MysqlServiceExtension extends CloseableParameterResolver<MysqlService> {

  @Override
  protected Class<MysqlService> getParameterClass() {
    return MysqlService.class;
  }

  @Override
  protected MysqlService createParamValue(ExtensionContext context) {
    RequiresMysql annotation = findInjectorAnnotation(context);

    MysqlConfig config = transformAnnotation(annotation);

    return MysqlService.defaultService(config);
  }

  @Override
  protected boolean cleanAfterTest(ExtensionContext context) {
    return findInjectorAnnotation(context).newForEachCase();
  }

  private RequiresMysql findInjectorAnnotation(ExtensionContext context) {
    return AnnotationFinder.resolve(context, RequiresMysql.class);
  }

  public static MysqlConfig transformAnnotation(RequiresMysql annotation) {
    return new MysqlConfig.Builder(annotation.version())
        .build();
  }

}
