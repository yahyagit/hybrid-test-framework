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

package com.atanas.kanchev.testframework.core.tests.handlers;

import com.atanas.kanchev.testframework.core.handlers.wrappers.IWrapper;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;

import static com.atanas.kanchev.testframework.selenium.init.SeleniumInit.$;

public class AppiumInitTest implements IWrapper {


    @Test
    public void androidChromeTest() throws Exception {

//        appiumInit()
//                .setupDevice()
//                .setDeviceType(AppiumDeviceTypesEnum.ANDROID_DEVICE)
//                .setDeviceName("ZY22398GL7")
//                .setPlatformVersion("6.0.1");
//
//        appiumInit()
//                .setupDeviceServer()
//                .setBrowserName(MobileBrowserType.CHROME)
//                .setFullReset(false)
//                .setAutoLaunch(false);
//
//        appiumInit()
//                .setupAndroidDriver()
//                .setAndroidDeviceReadyTimeout(60)
//                .setEnablePerformanceLogging(true);
//
//        appiumInit()
//                .initAndroidDriver("http://127.0.0.1:4723/wd/hub");
        $().goTo("https://bbc.co.uk");
        find().elementBy(By.id("idcta-link"));




        Thread.sleep(10000);

        android().orientation().rotate(ScreenOrientation.PORTRAIT);


    }

    @Test
    public void setupDeviceTest() throws Exception {

//        File classpathRoot = new File(System.getProp("user.dir"));
//        //File appDir = new File(classpathRoot, "D:\\IdeaProjects\\personal\\test-framework\\test-framework-APPIUM_INIT\\src\\test\\java\\com\\atanas\\kanchev\\testframework\\APPIUM_INIT\\tests\\ContactManager.apk");
//        File app = new File("D:\\IdeaProjects\\personal\\test-framework\\test-framework-APPIUM_INIT\\src\\test\\java\\com\\atanas\\kanchev\\testframework\\APPIUM_INIT\\tests\\ContactManager.apk");
//
//        setupDevice()
//                .setDeviceType(AppiumDeviceTypesEnum.ANDROID_DEVICE)
//                .setDeviceName("a1001")
//                .setPlatformName("Android")
//                .setPlatformVersion("6.0.1")
//                .setApp("Chrome");
//
//        setupDeviceServer()
//                .setFullReset(false)
//                .setAutoLaunch(false);
//
//
//        setupAndroidDriver()
//                //.setAppPackage("com.example.android.contactmanager")
//                .setAppPackage("com.android.chrome")
//                .setAndroidDeviceReadyTimeout(60)
//                .setEnablePerformanceLogging(true);
//
//        AndroidDriver<AndroidElement> d = initAndroidDriver("http://127.0.0.1:4723/wd/hub");
//        d.get("https://bbc.co.uk");
//        Thread.sleep(10000);
//        d.quit();
    }
}
