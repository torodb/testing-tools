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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

/**
 *
 */
@RunWith(JUnitPlatform.class)
public class SimplifiedParameterResolverTest {

  static int state = 0;

  @Nested
  @DisplayName("when state is conserved")
  @RequiresSimplifiedParameterResolver(cleanAfterTest = false)
  public class PreservesState {

    @Test
    void aRequest(Integer i) {
      Assertions.assertNotEquals(state, i);
      state = i;
    }

    @Test
    void aRequest2(Integer i) {
      Assertions.assertNotEquals(state, i);
      state = i;
    }

  }

  @Nested
  @DisplayName("when state is not conserved")
  @RequiresSimplifiedParameterResolver(cleanAfterTest = true)
  public class CleanAfterTest {

    @Test
    void aRequest(Integer i) {
      Assertions.assertEquals(1, i.intValue());
    }

    @Test
    void aRequest2(Integer i) {
      Assertions.assertEquals(1, i.intValue());
    }

  }


}
