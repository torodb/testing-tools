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
package com.torodb.testing.docker.postgres.junit5;

import static com.torodb.testing.docker.postgres.EnumVersion.LATEST;
import static org.junit.jupiter.api.Assertions.*;

import com.google.common.util.concurrent.Service;
import com.torodb.testing.docker.postgres.PostgresService;
import org.jooq.Record1;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@RequiresPostgres(version = LATEST)
public class PostgresTest {

  @Test
  public void simpleTest(PostgresService service) {
    assertEquals(Service.State.NEW, service.state(), "The injected service must start on "
        + Service.State.NEW + " state");

    service.startAsync();
    service.awaitRunning();

    testConnection(service);
  }

  private void testConnection(PostgresService service) {
    assert service.isRunning();

    service.getDslContext().createTable("test")
        .column("aint", SQLDataType.INTEGER)
        .execute();

    for (int i = 0; i < 10; i++) {
      service.getDslContext().insertInto(DSL.table("test"))
          .columns(DSL.field("aint"))
          .values(i)
          .execute();
    }

    Record1<Integer> countRecord = service.getDslContext().selectCount()
        .from(DSL.table("test"))
        .fetchAny();
    Assertions.assertNotNull(countRecord);
    Assertions.assertEquals(10, countRecord.value1().intValue());
  }
}
