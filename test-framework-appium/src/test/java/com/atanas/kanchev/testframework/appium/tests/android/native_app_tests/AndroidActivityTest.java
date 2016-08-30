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

import io.appium.java_client.android.AndroidKeyCode;
import org.junit.Before;
import org.junit.Test;

import static com.atanas.kanchev.testframework.appium.accessors.AppiumAccessors.$appium;
import static org.junit.Assert.assertEquals;

public class AndroidActivityTest extends BaseTest {

    @Before public void setUp() throws Exception {
        $appium().$androidNative().activity().startActivity("io.appium.$androidNative.apis", ".ApiDemos");
    }

    @Test public void startActivityInThisAppTestCase() {

        $appium().$androidNative().activity().startActivity("io.appium.$androidNative.apis",
            ".accessibility.AccessibilityNodeProviderActivity");
        assertEquals($appium().$androidNative().appiumDriverMethods().currentActivity(),
            ".accessibility.AccessibilityNodeProviderActivity");
        $appium().$androidNative().interactsWithApps().closeApp();
    }

    @Test public void startActivityWithWaitingAppTestCase() {

        $appium().$androidNative().activity().startActivity("io.appium.$androidNative.apis",
            ".accessibility.AccessibilityNodeProviderActivity", "io.appium.$androidNative.apis",
            ".accessibility.AccessibilityNodeProviderActivity");
        assertEquals($appium().$androidNative().appiumDriverMethods().currentActivity(),
            ".accessibility.AccessibilityNodeProviderActivity");
    }

    @Test public void startActivityInNewAppTestCase() {

        $appium().$androidNative().
            activity().startActivity("com.$androidNative.contacts", ".ContactsListActivity");
        assertEquals($appium().$androidNative().appiumDriverMethods().currentActivity(),
            ".ContactsListActivity");
        $appium().$androidNative().actionShortcuts().pressKeyCode(AndroidKeyCode.BACK);
        assertEquals($appium().$androidNative().appiumDriverMethods().currentActivity(),
            ".ContactsListActivity");
    }

    @Test public void startActivityInNewAppTestCaseWithoutClosingApp() {
        $appium().$androidNative().
            activity().startActivity("io.appium.$androidNative.apis",
            ".accessibility.AccessibilityNodeProviderActivity");
        assertEquals($appium().$androidNative().
                appiumDriverMethods().currentActivity(),
            ".accessibility.AccessibilityNodeProviderActivity");
        $appium().$androidNative().
            activity()
            .startActivity("com.$androidNative.contacts", ".ContactsListActivity", "com.$androidNative.contacts",
                ".ContactsListActivity", false);
        assertEquals($appium().$androidNative().
            appiumDriverMethods().currentActivity(), ".ContactsListActivity");
        $appium().$androidNative().
            actionShortcuts().pressKeyCode(AndroidKeyCode.BACK);
        assertEquals($appium().$androidNative().
                appiumDriverMethods().currentActivity(),
            ".accessibility.AccessibilityNodeProviderActivity");
    }
}
