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

package com.atanas.kanchev.testframework.selenium.interactions.element.interactions;

import org.openqa.selenium.WebDriverException;

/**
 * @author Atanas Kanchev
 */
public class IsDisplayed extends AbstractElementInteraction {

    private boolean result;

    public ElementExecutor<Boolean> isDisplayed() {
        return new ElementExecutor<Boolean>() {

            @Override public Boolean execute() {
                try {
                    return result = element.isDisplayed();
                } catch (WebDriverException e) {
                    throwEx(e);
                    return result;
                }
            }
        };
    }
}
