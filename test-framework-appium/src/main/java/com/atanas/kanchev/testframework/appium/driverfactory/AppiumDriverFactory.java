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

package com.atanas.kanchev.testframework.appium.driverfactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

/**
 * <p>AppiumDriverFactory class.</p>
 *
 * @author Atanas Kanchev
 */
public class AppiumDriverFactory extends AppiumLocalServiceBuilder {

    private final DesiredCapabilities caps = new DesiredCapabilities();

    /**
     * <p>setCap.</p>
     *
     * @param capabilityType  a {@link java.lang.String} object.
     * @param capabilityValue a {@link java.lang.Object} object.
     * @return a {@link com.atanas.kanchev.testframework.appium.driverfactory.AppiumDriverFactory} object.
     */
    public AppiumDriverFactory setCap(final String capabilityType, final Object capabilityValue) {
        caps.setCapability(capabilityType, capabilityValue);
        return this;
    }

    /**
     * <p>Getter for the field <code>caps</code>.</p>
     *
     * @return a {@link org.openqa.selenium.remote.DesiredCapabilities} object.
     */
    public DesiredCapabilities getCaps() {
        return caps;
    }

    /**
     * <p>get IOSDriver.</p>
     *
     * @return a {@link io.appium.java_client.ios.IOSDriver} object.
     */
    public IOSDriver<IOSElement> getIOSDriver() {
        return new IOSDriver<>(service, caps);
    }

    /**
     * <p>get AndroidDriver.</p>
     *
     * @return a {@link io.appium.java_client.android.AndroidDriver} object.
     */
    public AndroidDriver<AndroidElement> getAndroidDriver() {
        return new AndroidDriver<>(service, this.caps);
    }

    /**
     * <p>get AndroidDriver Vanilla.</p>
     *
     * @param node a {@link java.net.URL} object.
     * @return a {@link io.appium.java_client.android.AndroidDriver} object.
     */
    public AndroidDriver getAndroidDriverVanilla(URL node) {
        return new AndroidDriver(node, this.caps);
    }
}
