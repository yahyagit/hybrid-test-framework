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

import com.atanas.kanchev.testframework.appium.wrappers.IAppium;
import com.atanas.kanchev.testframework.commons.wrappers.IContext;
import io.appium.java_client.NoSuchContextException;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Platform;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class AndroidContextTest implements IAppium, IContext {

    @BeforeClass
    public static void beforeClass() throws Exception {
        File appDir = new File("src/test/java/com/atanas/kanchev/testframework/appium/tests/android/native_app_tests");
        File app = new File(appDir, "ApiDemos-debug.apk");

        APPIUM_DRIVER_FACTORY
                .buildDefaultService()
                .startServer();

        APPIUM_DRIVER_FACTORY
                .setCap(MobileCapabilityType.BROWSER_NAME, "")
                .setCap(MobileCapabilityType.APP, app.getAbsoluteFile())
                .setCap(MobileCapabilityType.DEVICE_NAME, "8adea98f")
                .setCap(MobileCapabilityType.PLATFORM_VERSION, "6.0.1")
                .setCap(MobileCapabilityType.PLATFORM, Platform.ANDROID)

                .setCap(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 120)
                .setCap(MobileCapabilityType.FULL_RESET, false)
                .setCap(MobileCapabilityType.NO_RESET, true)
                .setCap(AndroidMobileCapabilityType.ANDROID_DEVICE_READY_TIMEOUT, 10)
                .setCap(AndroidMobileCapabilityType.ENABLE_PERFORMANCE_LOGGING, true);
    }

    @Before
    public void setUp() throws Exception {
        android().activity().startActivity("io.appium.android.apis", ".view.WebView1");
        Thread.sleep(5000);

    }

    @AfterClass
    public static void tearDown() throws Exception {
        APPIUM_DRIVER_FACTORY.stopServer();
//        CONTEXT_FACTORY.tearDownContexts();

    }

    @Test
    public void testGetContext() {
        assertEquals("NATIVE_APP", android()
                .contextAware()
                .getContext());
    }

    @Test
    public void testGetContextHandles() {
        assertEquals(android()
                .contextAware()
                .getContextHandles().size(), 2);
    }

    @Test
    public void testSwitchContext() {

        android()
                .contextAware()
                .getContextHandles();
        android()
                .contextAware()
                .context("WEBVIEW_io.appium.android.apis");
        assertEquals(android()
                .contextAware()
                .getContext(), "WEBVIEW_io.appium.android.apis");
        android()
                .contextAware()
                .context("NATIVE_APP");
    }

    @Test(expected = NoSuchContextException.class)
    public void testContextError() {
        android()
                .contextAware()
                .context("Planet of the Ape-ium");
    }

}
