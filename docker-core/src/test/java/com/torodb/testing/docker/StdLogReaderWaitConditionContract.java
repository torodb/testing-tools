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
import java.io.StringReader;
import java.util.stream.Stream;

public interface StdLogReaderWaitConditionContract {

  public abstract StdLogReaderWaitCondition getStdLogReaderWaitCondition();

  public abstract Stream<OutputMessage> getCorrectMessages();

  public abstract Stream<OutputMessage> getIncorrectMessages();

  @Test
  public default void onCorrectMessages() throws
      IOException {

    StdLogReaderWaitCondition toTest = getStdLogReaderWaitCondition();

    getCorrectMessages().forEach(Unchecked.consumer((text) -> {
      try (BufferedReader stdReader = createBufferedReader(text.getStdOutput());
          BufferedReader errReader = createBufferedReader(text.getErrOutput())) {
        Assertions.assertTrue(
            toTest.lookForStartCondition(stdReader, errReader),
            "text " + text + " should be recognized as a correct text"
        );
      }
    }));
  }

  @Test
  public default void whenPredicateDoesntMatch() throws
      IOException {
    StdLogReaderWaitCondition toTest = getStdLogReaderWaitCondition();

    getCorrectMessages().forEach(Unchecked.consumer((text) -> {
      try (BufferedReader stdReader = createBufferedReader(text.getStdOutput());
          BufferedReader errReader = createBufferedReader(text.getErrOutput())) {
        Assertions.assertFalse(
            toTest.lookForStartCondition(stdReader, errReader),
            "text " + text + " should not be recognized as a correct text"
        );
      }
    }));
  }

  public default BufferedReader createBufferedReader(String text) {
    return new BufferedReader(new StringReader(text));
  }

  public static interface OutputMessage {
    String getStdOutput();

    String getErrOutput();
  }

}
