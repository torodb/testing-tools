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
package com.torodb.testing.docker;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.stream.Stream;

@RunWith(JUnitPlatform.class)
public class UntilStdLineContainsTest implements UntilStdLinePredicateContract {

  private final String[] corrects = new String[]{
    "A correct line",
    "a fjasg adfglsd fglsdfg\nalsdgkadf glskd flg\nA correct line",
    "A correct line\nkgreogzldfasdf\nikafa ksdflaksdflasdlf",
    "skdlaidqwodasd\nA correct line\nasdfjasdkfasd fkasdjfaksdf"
  };
  private final String[] incorrects = new String[]{
    "",
    "a fjasg adfglsd fglsdfg\nalsdgkadf glskd flg",
    "greogzldfasdf\nikafa ksdflaksdflasdlf"
  };
  private final String targetText = "correct";
  private final UntilStdLineContains instance = new UntilStdLineContains(targetText);

  @Override
  public UntilStdLinePredicate getUntilStdLinePredicate() {
    return instance;
  }

  @Override
  public Stream<String> getCorrectTexts() {
    return Arrays.stream(corrects);
  }

  @Override
  public Stream<String> getIncorrectTexts() {
    return Arrays.stream(incorrects);
  }

  @Test
  public void testEqual() {
    assertFalse(instance.test("random text"));

    assertTrue(instance.test(targetText));
  }

  @Nested
  @DisplayName("when not equal")
  public class NotEqual {
    @Test
    public void notContained() {
      assertFalse(instance.test("random text"));
    }

    @Test
    public void withPrefix() {
      assertTrue(instance.test("random text" + targetText));
    }

    @Test
    public void withSufix() {
      assertTrue(instance.test(targetText + "random text"));
    }

    @Test
    public void withPrefixAndSufix() {
      assertTrue(instance.test("random text" + targetText + "random text"));
    }
  }

}
