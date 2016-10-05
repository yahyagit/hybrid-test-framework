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

package com.atanas.kanchev.testframework.core.accessors;

import com.atanas.kanchev.testframework.appium.accessors.AppiumAccessors;
import com.atanas.kanchev.testframework.appium.accessors.AppiumAccessorsSingleton;
import com.atanas.kanchev.testframework.dataservices.accessors.DataServicesAccessors;
import com.atanas.kanchev.testframework.dataservices.accessors.DataServicesAccessorsSingleton;
import com.atanas.kanchev.testframework.selenium.accessors.SeleniumAccessors;
import com.atanas.kanchev.testframework.selenium.element.OmniaWebElement;
import com.atanas.kanchev.testframework.sikuli.accessors.SikuliXAccessors;
import com.atanas.kanchev.testframework.sikuli.accessors.SikuliXAccessorsSingleton;

/**
 * @author Atanas Kanchev
 */
public class OmniaAccessorsSingleton {

    private static OmniaAccessorsSingleton instance = null;

    private OmniaAccessorsSingleton() {}

    static OmniaAccessorsSingleton getInstance() {
        if (instance == null) {
            instance = new OmniaAccessorsSingleton();
        }
        return instance;
    }

    public AppiumAccessorsSingleton $appium() {
        return AppiumAccessors.$appium();
    }

    public DataServicesAccessorsSingleton $data() {
        return DataServicesAccessors.$data();
    }

    public OmniaWebElement $selenium() {
        return SeleniumAccessors.$selenium();
    }

    public SikuliXAccessorsSingleton $sikuli() {
        return SikuliXAccessors.$sikuli();
    }

}
