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

package com.atanas.kanchev.testframework.selenium.element.interactions.element;

import com.atanas.kanchev.testframework.commons.context.ContextKey;
import com.atanas.kanchev.testframework.selenium.context.SeleniumContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.atanas.kanchev.testframework.commons.accessors.ContextsAccessor.$context;

/**
 * Created by atanas on 27/09/16.
 */
public class FindElement {

    ContextKey<SeleniumContext> currentContextKey;

    public FindElement(ContextKey<SeleniumContext> currentContextKey) {
        this.currentContextKey = currentContextKey;
    }

    public WebElement findElement(By by) {

        $context().getContext(currentContextKey).setCurrentElement(
            $context().getContext(currentContextKey).getDriver().findElement(by));

        return $context().getContext(currentContextKey).getCurrentElement();
    }

    public List<WebElement> findElements(By by) {

        $context().getContext(currentContextKey).setWebElementsList(
            $context().getContext(currentContextKey).getDriver().findElements(by));

        return $context().getContext(currentContextKey).getWebElementsList();
    }
}