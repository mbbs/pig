/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.pig.tools.grunt;

import org.apache.pig.PigException;

public class Utils {
    static Exception getPermissionException(Exception top){
        Throwable current = top;

        while (current != null && (current.getMessage() == null || current.getMessage().indexOf("Permission denied") == -1)){
            current = current.getCause();
        }
        return (Exception)current;
    }
    
    public static PigException getPigException(Throwable top) {
        Throwable current = top;
        Throwable pigException = top;

        while (current != null && current.getCause() != null){
            current = current.getCause();
            if(current instanceof PigException) {
                pigException = current;
            }
        }
        return (pigException instanceof PigException? (PigException)pigException : null);
        
    }

}
