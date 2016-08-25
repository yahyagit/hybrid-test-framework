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

package com.atanas.kanchev.testframework.selenium.interactions.wait.interactions;

import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * @author Atanas Kanchev
 */
public class Url extends AbstractWaitInteraction {

    public WaitExecutor urlToBe(String input) {
        return new WaitExecutor<Boolean>() {
            @Override public Boolean execute() {
                return exec(ExpectedConditions.urlToBe(input));
            }
        };
    }

    public WaitExecutor urlContains(String input) {
        return new WaitExecutor<Boolean>() {
            @Override public Boolean execute() {
                return exec(ExpectedConditions.urlContains(input));
            }
        };
    }

    public WaitExecutor urlMatches(String input) {
        return new WaitExecutor<Boolean>() {
            @Override public Boolean execute() {
                return exec(ExpectedConditions.urlMatches(input));
            }
        };
    }

}
