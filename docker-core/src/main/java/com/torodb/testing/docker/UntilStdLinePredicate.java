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

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Spliterators.AbstractSpliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.StreamSupport;

/**
 *
 */
@FunctionalInterface
public interface UntilStdLinePredicate extends StdLogReaderWaitCondition, Predicate<String> {

  @Override
  public boolean test(String line);

  @Override
  public default boolean lookForStartCondition(BufferedReader stdReader, BufferedReader errReader) {
    return StreamSupport.stream(new AbstractSpliterator<String>(Long.MAX_VALUE, 0) {
      @Override
      public boolean tryAdvance(Consumer<? super String> action) {
        try {
          if (errReader.ready()) {
            errReader.readLine();
          }
          
          if (stdReader.ready()) {
            String line = stdReader.readLine();
            if (line != null) {
              action.accept(line);
              return true;
            }
            return false;
          }
          
          return true;
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
    }, false)
        .filter(this)
        .findAny()
        .isPresent();
  }

}
