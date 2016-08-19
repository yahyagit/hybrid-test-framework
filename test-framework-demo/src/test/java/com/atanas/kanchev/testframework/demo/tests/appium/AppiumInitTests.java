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

package com.atanas.kanchev.testframework.demo.tests.appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.junit.After;

public class AppiumInitTests  {

    private AppiumDriver<AndroidElement> d;
    private String AppiumHub = "http://127.0.0.1:4723/wd/hub";

    @After
    public void tearDown() throws Exception {
        d.quit();
        d = null;
    }

//    @Test
//    public void androidNativeAppTest() throws Exception {
//
//        File app = new File(".\\src\\test\\resources\\ContactManager.apk");
//
//        setupDevice()
//                .setDeviceType(AppiumDeviceTypesEnum.ANDROID_DEVICE)
//                .setDeviceName("a1001")
//                .setPlatformName("Android")
//                .setPlatformVersion("6.0.1")
//                .setApp(app.getAbsolutePath());
//
//        setupDeviceServer()
//                .setFullReset(false)
//                .setAutoLaunch(false);
//
//        setupAndroidDriver()
//                .setAppPackage("com.example.android.contactmanager")
//                .setAndroidDeviceReadyTimeout(60)
//                .setEnablePerformanceLogging(true);
//
//        d = initAndroidDriver(AppiumHub);
//
//    }
//
//    @Test
//    public void androidEmulatorNativeBrowserTest() throws Exception {
//
//        setupDevice()
//                .setDeviceType(AppiumDeviceTypesEnum.ANDROID_EMULATOR)
//                .setDeviceUDID("emulator-5554")
//                .setDeviceName("a1001")
//                .setPlatformName("Android");
//
//        setupDeviceServer()
//                .setNewCommandTimeout(60)
//                .setBrowserName("browser");
//
//        setupAndroidDriver()
//                .setAppPackage("com.android.browser.BrowserActivity")
//                .setAvdReadyTimeout(60)
//                .setEnablePerformanceLogging(true);
//
//        d = initAndroidDriver(AppiumHub);
//        d.get("https://www.bbc.co.uk");
//
//    }
}
