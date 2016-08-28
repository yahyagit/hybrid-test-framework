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
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Platform;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class IntentTest implements IAppium {

    @BeforeClass public static void beforeClass() throws Exception {

        File appDir = new File(
            "src/test/java/com/atanas/kanchev/testframework/appium/tests/android/native_app_tests");
        File app = new File(appDir, "IntentExample.apk");

        APPIUM_DRIVER_FACTORY.buildDefaultService().startServer();

        APPIUM_DRIVER_FACTORY.setCap(MobileCapabilityType.APP, app.getAbsoluteFile())
            .setCap(MobileCapabilityType.DEVICE_NAME, "8adea98f")
            .setCap(MobileCapabilityType.PLATFORM, Platform.ANDROID);
    }

    @AfterClass public static void tearDown() throws Exception {

        APPIUM_DRIVER_FACTORY.stopServer();
        //        CONTEXT_FACTORY.tearDownContexts();

    }

    @Test public void startActivityWithIntent() {

        android().activity()
            .startActivity("com.android.mms", ".ui.ComposeMessageActivity", null, null,
                "android.intent.action.SEND", "android.intent.category.DEFAULT", "0x4000000",
                "-d \"TestIntent\" -t \"text/plain\"");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test public void startActivityWithDefaultIntentAndDefaultCategoryWithOptionalArgs() {

        android().activity().startActivity("com.prgguru.android", ".GreetingActivity", null, null,
            "android.intent.action.MAIN", "android.intent.category.DEFAULT", "0x4000000",
            "--es \"USERNAME\" \"AppiumIntentTest\" -t \"text/plain\"");
        assertEquals(android().find().findElementById("com.prgguru.android:id/textView1").getText(),
            "Welcome AppiumIntentTest");
    }
}
