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

package com.torodb.testing.mongodb.docker;

import com.mongodb.MongoServerException;

import java.util.function.BiPredicate;
import java.util.function.Supplier;

/**
 *
 */
public class MongoRequestRetrier {

  private MongoRequestRetrier() {
  }

  /**
   * Executes the given supplier until it doesn't throw an exception or the stop predicate is
   * evaluated to true, whichever comes first.
   * 
   * @param <R>           The returned result.
   * @param task          the task to be executed.
   * @param stopPredicate a {@link BiPredicate} that is executed each time the task throws a
   *                      {@link MongoServerException}. The first argument is the catched exception
   *                      and the second the number of times the task has been executed (starting
   *                      from 1).
   * @return the result of the task, if it doesn't fail
   */
  public static <R> R retry(
      Supplier<R> task,
      BiPredicate<MongoServerException, Integer> stopPredicate) {

    int attempts = 0;
    while (true) {
      try {
        attempts++;
        return task.get();
      } catch (MongoServerException ex) {
        if (stopPredicate.test(ex, attempts)) {
          throw ex;
        }
      }
    }
  }

}
