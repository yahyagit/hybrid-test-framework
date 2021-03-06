/*
 * Copyright 2016 Atanas Stoychev Kanchev
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.atanas.kanchev.testframework.selenium.interactions;

import com.atanas.kanchev.testframework.commons.exceptions.CustomExceptions;

/**
 * @param <T> method return type
 * @author Atanas Kanchev
 */
public interface Executor<T> {

    T execute();

    default void valNotNull(Object arg) {
        if (arg == null)
            throw new CustomExceptions.Common.NullArgumentException("The argument is null");
    }

}
