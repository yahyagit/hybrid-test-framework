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

package com.atanas.kanchev.testframework.appium.tests.android.native_app_tests;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.Platform;

import java.io.File;

import static com.atanas.kanchev.testframework.appium.accessors.AppiumAccessors.$appium;

public class BaseTest  {

    @BeforeClass public static void beforeClass() throws Exception {
        File appDir = new File(
            "src/test/java/com/atanas/kanchev/testframework/appium/tests/android/native_app_tests");
        File app = new File(appDir, "ApiDemos-debug.apk");

        $appium().init().buildDefaultService().startServer();

        $appium().init().setCap(MobileCapabilityType.APP, app.getAbsolutePath())
            .setCap(MobileCapabilityType.DEVICE_NAME, "8adea98f")
            .setCap(MobileCapabilityType.PLATFORM_VERSION, "6.0.1")
            .setCap(MobileCapabilityType.PLATFORM, Platform.ANDROID)

            .setCap(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 10)
            .setCap(MobileCapabilityType.FULL_RESET, false)
            .setCap(MobileCapabilityType.NO_RESET, true)
            .setCap(AndroidMobileCapabilityType.ANDROID_DEVICE_READY_TIMEOUT, 10)
            .setCap(AndroidMobileCapabilityType.ENABLE_PERFORMANCE_LOGGING, true);

    }

    @AfterClass public static void tearDown() throws Exception {
        //        CONTEXT_FACTORY.tearDownContexts();
        $appium().init().stopServer();
    }
}
