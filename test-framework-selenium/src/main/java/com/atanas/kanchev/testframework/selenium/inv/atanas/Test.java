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

package com.atanas.kanchev.testframework.selenium.inv.atanas;

import com.atanas.kanchev.testframework.commons.context.ContextKey;
import com.atanas.kanchev.testframework.selenium.accessors.SeleniumAccessorsSingleton;
import com.atanas.kanchev.testframework.selenium.context.SeleniumContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.atanas.kanchev.testframework.commons.accessors.ContextsAccessor.$context;
import static com.atanas.kanchev.testframework.selenium.accessors.SeleniumAccessors.$selenium;
import static com.atanas.kanchev.testframework.selenium.inv.atanas.ElementProxy.newInstance;

/**
 * @author Atanas Kanchev
 */
public class Test {

    public static ContextKey<SeleniumContext> currentContextKey;

    public static void main(String[] args) {

        $selenium().conf().setBrowser("chrome");
        SeleniumContext<WebDriver> c = new SeleniumContext<>($selenium().conf().getDriver());
        currentContextKey = $context().addContext(c.getContextKey(), c);
        SeleniumAccessorsSingleton.currentContextKey = currentContextKey;
        $selenium().goTo("https://www.google.co.uk");
        $selenium().find(By.className("gb_P"));

        IElement iElement = (IElement) newInstance(new ElementImpl(currentContextKey));
        iElement.find(By.className("gb_P"));

        $context().tearDownContexts();

    }
}
