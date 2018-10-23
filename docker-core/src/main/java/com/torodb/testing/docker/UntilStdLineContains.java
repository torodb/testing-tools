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

/**
 * A {@link WaitCondition} that iterates on the log lines looking for the given string on
 * each line.
 */
public class UntilStdLineContains implements UntilLinePredicate {
  private final String containedString;

  public UntilStdLineContains(String containedString) {
    this.containedString = containedString;
  }

  @Override
  public boolean test(String line) {
    return line.contains(containedString);
  }

}
