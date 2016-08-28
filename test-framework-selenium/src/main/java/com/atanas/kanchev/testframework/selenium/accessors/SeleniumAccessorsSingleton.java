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

package com.atanas.kanchev.testframework.selenium.accessors;

import com.atanas.kanchev.testframework.selenium.driverfactory.DriverFactory;
import com.atanas.kanchev.testframework.selenium.handlers.Finds;
import com.atanas.kanchev.testframework.selenium.handlers.Navigates;
import com.atanas.kanchev.testframework.selenium.handlers.Probes;
import com.atanas.kanchev.testframework.selenium.interactions.element.OmniaElement;
import com.atanas.kanchev.testframework.selenium.interactions.wait.Waiting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * @author Atanas Kanchev
 */
public class SeleniumAccessorsSingleton {

    private static SeleniumAccessorsSingleton instance = null;
    private static final DriverFactory driverFactory = new DriverFactory();

    private SeleniumAccessorsSingleton() {
    }

    static SeleniumAccessorsSingleton getInstance() {
        if (instance == null) {
            instance = new SeleniumAccessorsSingleton();
        }
        return instance;
    }

    public DriverFactory driverSetup() {
        return driverFactory;
    }

    public Navigates goTo(final String url) {

        return new Navigates().getPage(url);
    }

    public Finds find() {
        return new Finds();
    }

    public Finds find(WebElement e) {
        return new Finds(e);
    }

    public Finds find(Class<?> clasz) {
        return new Finds(clasz);
    }

    public Probes probe(By locator) {
        return new Probes(locator);
    }

    public Waiting waitFor() {
        return new Waiting();
    }

    public OmniaElement element() {
        return new OmniaElement();
    }

    public OmniaElement element(By by) {

        return new OmniaElement(by);
    }

}
