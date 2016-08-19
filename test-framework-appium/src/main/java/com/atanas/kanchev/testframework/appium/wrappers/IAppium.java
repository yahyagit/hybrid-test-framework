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

package com.atanas.kanchev.testframework.appium.wrappers;

import com.atanas.kanchev.testframework.appium.driverfactory.AppiumDriverFactory;
import com.atanas.kanchev.testframework.appium.handlers.AndroidNativeHandler;

/**
 * The interface Appium.
 */
public interface IAppium {

    /**
     * The constant APPIUM_DRIVER_FACTORY.
     */
    AppiumDriverFactory APPIUM_DRIVER_FACTORY = new AppiumDriverFactory();

    /**
     * Appium init driver factory.
     *
     * @return the appium driver factory
     */
    default AppiumDriverFactory appiumInit() {
        return APPIUM_DRIVER_FACTORY;
    }

    /**
     * Android android native handler.
     *
     * @return the android native handler
     */
    default AndroidNativeHandler android() {
        return new AndroidNativeHandler();
    }
}
