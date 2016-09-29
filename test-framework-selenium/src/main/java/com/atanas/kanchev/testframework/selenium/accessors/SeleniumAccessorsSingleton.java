/*
 * Copyright 2016 Atanas Stoychev Kanchev
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.atanas.kanchev.testframework.selenium.accessors;

import com.atanas.kanchev.testframework.commons.context.ContextKey;
import com.atanas.kanchev.testframework.selenium.context.SeleniumContext;
import com.atanas.kanchev.testframework.selenium.driverfactory.DriverFactory;
import com.atanas.kanchev.testframework.selenium.element.OmniaWebElement;
import com.atanas.kanchev.testframework.selenium.element.proxy.ElementProxy;
import com.atanas.kanchev.testframework.selenium.handlers.Finder;
import com.atanas.kanchev.testframework.selenium.handlers.Navigates;
import com.atanas.kanchev.testframework.selenium.handlers.Probes;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.atanas.kanchev.testframework.commons.accessors.ContextsAccessor.$context;

/**
 * @author Atanas Kanchev
 */
public class SeleniumAccessorsSingleton {

    private static final DriverFactory driverFactory = new DriverFactory();
    public static ContextKey<SeleniumContext> currentContextKey;
    private static SeleniumAccessorsSingleton instance = null;

    private SeleniumAccessorsSingleton() {
    }

    static SeleniumAccessorsSingleton getInstance() {
        if (instance == null) {
            instance = new SeleniumAccessorsSingleton();
        }
        return instance;
    }

    public DriverFactory conf() {
        return driverFactory;
    }

    public Navigates goTo(final String url) {

        if (currentContextKey == null) {
            SeleniumContext<WebDriver> c = new SeleniumContext<>(conf().getDriver());
            currentContextKey = $context().addContext(c.getContextKey(), c);
        }

        return new Navigates().getPage(url);
    }

    public Finder find() {
        return new Finder();
    }

    public Finder find(By locator) {
        return new Finder(locator);
    }

    public Finder find(WebElement e) {
        return new Finder(e);
    }

    public Finder find(Class<?> clasz) {
        return new Finder(clasz);
    }

    public Probes probe(By locator) {
        return new Probes(locator);
    }

//    public Waiting waitFor() {
//        return new Waiting();
//    }

    public OmniaWebElement x(){
        return ElementProxy.wrap(OmniaWebElement.class, currentContextKey);
    }


}
