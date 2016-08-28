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

package com.atanas.kanchev.testframework.appium.tests.android.browser_tests;


import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.ScreenOrientation;

import static com.atanas.kanchev.testframework.appium.accessors.AppiumAccessors.$appium;
import static com.atanas.kanchev.testframework.selenium.accessors.SeleniumAccessors.$selenium;

public class ChromeTest {

    @Test public void androidChromeTest() throws Exception {

        $appium().init().buildDefaultService();
        $appium().init().startServer();

        $appium().init()

            .setCap(MobileCapabilityType.BROWSER_NAME, MobileBrowserType.CHROME)
            .setCap(MobileCapabilityType.PLATFORM, Platform.ANDROID)
            .setCap(MobileCapabilityType.DEVICE_NAME, "ZY22398GL7")
            .setCap(MobileCapabilityType.PLATFORM_VERSION, "6.0.1")

            .setCap(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60)
            .setCap(MobileCapabilityType.FULL_RESET, false)
            .setCap(AndroidMobileCapabilityType.ANDROID_DEVICE_READY_TIMEOUT, 60)
            .setCap(AndroidMobileCapabilityType.ENABLE_PERFORMANCE_LOGGING, true);

        $appium().init().getAndroidDriver();

        $selenium().goTo("https://bbc.co.uk");

        $selenium().find().elementBy(By.id("idcta-link"));

        $appium().android().orientation().rotate(ScreenOrientation.LANDSCAPE);

    }
}
