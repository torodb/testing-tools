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

package com.torodb.testing.docker.oracle;

public interface Edition {
  
  public String sid();
  
  public String suffix();

  public enum Value implements Edition {
    xe, xe_fast("xe", "xe-fast"), se, se_fast("se", "se-fast"), ee,  ee_fast("ee", "ee-fast");
    
    private final String sid;
    private final String suffix;
    
    private Value() {
      this.sid = name();
      this.suffix = name();
    }
    
    private Value(String sid, String suffix) {
      this.sid = sid;
      this.suffix = suffix;
    }

    @Override
    public String sid() {
      return sid;
    }

    @Override
    public String suffix() {
      return suffix;
    }
  }
}
