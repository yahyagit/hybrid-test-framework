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

package com.atanas.kanchev.testframework.appium.accessors;

import com.atanas.kanchev.testframework.appium.driverfactory.AppiumDriverFactory;
import com.atanas.kanchev.testframework.appium.handlers.AndroidNativeHandler;
import com.atanas.kanchev.testframework.selenium.accessors.SeleniumAccessorsSingleton;
import com.atanas.kanchev.testframework.selenium.context.SeleniumContext;
import com.atanas.kanchev.testframework.selenium.handlers.Navigates;
import io.appium.java_client.android.AndroidDriver;

import static com.atanas.kanchev.testframework.appium.accessors.AppiumAccessors.$appium;
import static com.atanas.kanchev.testframework.commons.accessors.ContextsAccessor.context;

/**
 * @author Atanas Kanchev
 */
public class AppiumAccessorsSingleton {

    private static final AppiumDriverFactory APPIUM_DRIVER_FACTORY = new AppiumDriverFactory();
    private static AppiumAccessorsSingleton instance = null;

    private AppiumAccessorsSingleton() {
    }

    static AppiumAccessorsSingleton getInstance() {
        if (instance == null) {
            instance = new AppiumAccessorsSingleton();
        }
        return instance;
    }

    public AppiumDriverFactory conf() {
        return APPIUM_DRIVER_FACTORY;
    }

    public Navigates goTo(final String url) {
        SeleniumContext<AndroidDriver> c = new SeleniumContext<>($appium().conf().getAndroidDriver());
        SeleniumAccessorsSingleton.currentContextKey = context().addContext(c.getContextKey(), c);


        return new Navigates().getPage(url);
    }

    public AndroidNativeHandler android() {
        return new AndroidNativeHandler();
    }

}
