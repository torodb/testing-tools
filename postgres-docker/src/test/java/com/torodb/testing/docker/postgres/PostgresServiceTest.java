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
package com.torodb.testing.docker.postgres;

import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class PostgresServiceTest {

  @ParameterizedTest
  @EnumSource(EnumVersion.class)
  public void startAndStop(EnumVersion version) {

    try (PostgresService service = PostgresService.defaultService(version)) {
      service.startAsync();
      service.awaitRunning();

      assert service.isRunning();

      try (DSLContext dslContext = service.getDslContext()) {
        dslContext.createTable("test")
            .column("aint", SQLDataType.INTEGER)
            .execute();

        for (int i = 0; i < 10; i++) {
          dslContext.insertInto(DSL.table("test"))
              .columns(DSL.field("aint"))
              .values(i)
              .execute();
        }

        Record1<Integer> countRecord = dslContext.selectCount()
            .from(DSL.table("test"))
            .fetchAny();
        Assertions.assertNotNull(countRecord);
        Assertions.assertEquals(10, countRecord.value1().intValue());
      }
    }

  }

}
