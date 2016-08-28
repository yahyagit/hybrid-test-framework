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

import org.junit.Test;

import static com.atanas.kanchev.testframework.appium.accessors.AppiumAccessors.$appium;
import static org.junit.Assert.assertNotEquals;

public class OpenNotificationsTest extends BaseTest {

    @Test
    public void openNotification() throws Exception {
        $appium().android().appiumDriverMethods().openNotifications();
        assertNotEquals(0, $appium().android().find().findElementsById("com.android.systemui:id/time_view").size());
        $appium().android().appiumDriverMethods().openNotifications();
    }
}
