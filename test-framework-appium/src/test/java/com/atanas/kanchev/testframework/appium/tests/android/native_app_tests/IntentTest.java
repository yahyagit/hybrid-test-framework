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

import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Platform;

import java.io.File;

import static com.atanas.kanchev.testframework.appium.accessors.AppiumAccessors.$appium;
import static org.junit.Assert.assertEquals;


public class IntentTest {

    @BeforeClass public static void beforeClass() throws Exception {

        File appDir = new File(
            "src/test/java/com/atanas/kanchev/testframework/appium/tests/$androidNative/native_app_tests");
        File app = new File(appDir, "IntentExample.apk");

        $appium().$conf().buildDefaultService().startServer();

        $appium().$conf().setCap(MobileCapabilityType.APP, app.getAbsoluteFile())
            .setCap(MobileCapabilityType.DEVICE_NAME, "8adea98f")
            .setCap(MobileCapabilityType.PLATFORM, Platform.ANDROID);
    }

    @AfterClass public static void tearDown() throws Exception {

        $appium().$conf().stopServer();
        //        CONTEXT_FACTORY.tearDownContexts();

    }

    @Test public void startActivityWithIntent() {

        $appium().$androidNative().activity()
            .startActivity("com.$androidNative.mms", ".ui.ComposeMessageActivity", null, null,
                "$androidNative.intent.action.SEND", "$androidNative.intent.category.DEFAULT", "0x4000000",
                "-d \"TestIntent\" -t \"text/plain\"");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test public void startActivityWithDefaultIntentAndDefaultCategoryWithOptionalArgs() {

        $appium().$androidNative().activity()
            .startActivity("com.prgguru.$androidNative", ".GreetingActivity", null, null,
                "$androidNative.intent.action.MAIN", "$androidNative.intent.category.DEFAULT", "0x4000000",
                "--es \"USERNAME\" \"AppiumIntentTest\" -t \"text/plain\"");
        assertEquals($appium().$androidNative().find().findElementById("com.prgguru.$androidNative:id/textView1")
            .getText(), "Welcome AppiumIntentTest");
    }
}
