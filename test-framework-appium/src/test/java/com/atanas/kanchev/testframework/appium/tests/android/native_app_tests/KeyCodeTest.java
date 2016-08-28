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
import io.appium.java_client.android.AndroidKeyMetastate;
import org.junit.Before;
import org.junit.Test;

import static com.atanas.kanchev.testframework.appium.accessors.AppiumAccessors.$appium;

public class KeyCodeTest extends BaseTest {

    @Before
    public void before() throws Exception {
        $appium().android()
                .interactsWithApps()
                .resetApp();
    }

    @Test
    public void pressKeyCodeTest() {
        $appium().android()
                .actionShortcuts()
                .pressKeyCode(AndroidKeyCode.HOME);
    }

    @Test
    public void pressKeyCodeWithMetastateTest() {
        $appium().android()
                .actionShortcuts()
                .pressKeyCode(AndroidKeyCode.SPACE, AndroidKeyMetastate.META_SHIFT_ON);
    }

    @Test
    public void longPressKeyCodeTest() {
        $appium().android()
                .actionShortcuts()
                .longPressKeyCode(AndroidKeyCode.HOME);
    }

    @Test
    public void longPressKeyCodeWithMetastateTest() {
        $appium().android()
                .actionShortcuts()
                .longPressKeyCode(AndroidKeyCode.HOME, AndroidKeyMetastate.META_SHIFT_ON);
    }
}
