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
        $appium().android().activity().startActivity("io.appium.android.apis", ".ApiDemos");
    }

    @Test public void startActivityInThisAppTestCase() {

        $appium().android().activity().startActivity("io.appium.android.apis",
            ".accessibility.AccessibilityNodeProviderActivity");
        assertEquals($appium().android().appiumDriverMethods().currentActivity(),
            ".accessibility.AccessibilityNodeProviderActivity");
        $appium().android().interactsWithApps().closeApp();
    }

    @Test public void startActivityWithWaitingAppTestCase() {

        $appium().android().activity().startActivity("io.appium.android.apis",
            ".accessibility.AccessibilityNodeProviderActivity", "io.appium.android.apis",
            ".accessibility.AccessibilityNodeProviderActivity");
        assertEquals($appium().android().appiumDriverMethods().currentActivity(),
            ".accessibility.AccessibilityNodeProviderActivity");
    }

    @Test public void startActivityInNewAppTestCase() {

        $appium().android().
            activity().startActivity("com.android.contacts", ".ContactsListActivity");
        assertEquals($appium().android().appiumDriverMethods().currentActivity(),
            ".ContactsListActivity");
        $appium().android().actionShortcuts().pressKeyCode(AndroidKeyCode.BACK);
        assertEquals($appium().android().appiumDriverMethods().currentActivity(),
            ".ContactsListActivity");
    }

    @Test public void startActivityInNewAppTestCaseWithoutClosingApp() {
        $appium().android().
            activity().startActivity("io.appium.android.apis",
            ".accessibility.AccessibilityNodeProviderActivity");
        assertEquals($appium().android().
                appiumDriverMethods().currentActivity(),
            ".accessibility.AccessibilityNodeProviderActivity");
        $appium().android().
            activity()
            .startActivity("com.android.contacts", ".ContactsListActivity", "com.android.contacts",
                ".ContactsListActivity", false);
        assertEquals($appium().android().
            appiumDriverMethods().currentActivity(), ".ContactsListActivity");
        $appium().android().
            actionShortcuts().pressKeyCode(AndroidKeyCode.BACK);
        assertEquals($appium().android().
                appiumDriverMethods().currentActivity(),
            ".accessibility.AccessibilityNodeProviderActivity");
    }
}
