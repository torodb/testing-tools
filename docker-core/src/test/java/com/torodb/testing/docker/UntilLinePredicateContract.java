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


import org.jooq.lambda.Unchecked;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Stream;


public interface UntilLinePredicateContract extends LogReaderWaitConditionContract {

  public abstract UntilLinePredicate getUntilLinePredicate();

  public abstract Stream<String> getCorrectTexts();

  public abstract Stream<String> getIncorrectTexts();

  @Override
  public default LogReaderWaitCondition getLogReaderWaitCondition() {
    return getUntilLinePredicate();
  }

  @Override
  public default Stream<OutputMessage> getCorrectMessages() {
    return getCorrectTexts().map(EmptyErrorOutputMessage::new);
  }

  @Override
  public default Stream<OutputMessage> getIncorrectMessages() {
    return getIncorrectTexts().map(EmptyErrorOutputMessage::new);
  }

  @Test
  public default void whenPredicateMatchs() throws
      IOException {

    UntilLinePredicate toTest = getUntilLinePredicate();
    
    getCorrectTexts().forEach(Unchecked.consumer((text) -> {
      try (BufferedReader inReader = createBufferedReader(text)) {
        Assertions.assertTrue(
            toTest.lookForStartCondition(inReader),
            "text " + text + " should be recognized as a correct text"
        );
      }
    }));
  }

  @Test
  public default void whenPredicateDoesntMatch() throws
      IOException {
    UntilLinePredicate toTest = getUntilLinePredicate();

    getIncorrectTexts().forEach(Unchecked.consumer((text) -> {
      try (BufferedReader inReader = createBufferedReader(text)) {
        Assertions.assertFalse(
            toTest.lookForStartCondition(inReader),
            "text " + text + " should not be recognized as a correct text"
        );
      }
    }));
  }

  public static class EmptyErrorOutputMessage implements OutputMessage {
    private final String stdOutput;

    public EmptyErrorOutputMessage(String stdOutput) {
      this.stdOutput = stdOutput;
    }

    @Override
    public String getOutput() {
      return stdOutput;
    }
  }
}
