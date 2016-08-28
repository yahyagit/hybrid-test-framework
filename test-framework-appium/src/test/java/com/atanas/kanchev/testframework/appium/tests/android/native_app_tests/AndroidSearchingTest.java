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

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.atanas.kanchev.testframework.appium.accessors.AppiumAccessors.$appium;
import static org.junit.Assert.*;

public class AndroidSearchingTest extends BaseTest {

    @Before
    public void before() throws Exception {
        $appium().android()
                .activity()
                .startActivity("io.appium.android.apis", ".ApiDemos");
    }

    @Test
    public void findByAccessibilityIdTest() {
        assertNotEquals($appium().android().find().findElement(MobileBy.AccessibilityId("Graphics")).getText(), null);
        assertEquals($appium().android().find().findElements(MobileBy.AccessibilityId("Graphics")).size(), 1);
    }

    @Test
    public void findByAndroidUIAutomatorTest() {
        assertNotEquals($appium().android().find()
                .findElement(MobileBy.AndroidUIAutomator("new UiSelector().clickable(true)")).getText(), null);
        assertNotEquals($appium().android().find()
                .findElements(MobileBy.AndroidUIAutomator("new UiSelector().clickable(true)")).size(), 0);
        assertNotEquals($appium().android().find()
                .findElements(MobileBy.AndroidUIAutomator("new UiSelector().clickable(true)")).size(), 1);
    }

    @Test
    public void findByXPathTest() {
        String byXPath = "//android.widget.TextView[contains(@text, 'Animat')]";
        assertNotNull($appium().android().find().findElement(By.xpath(byXPath)).getText());
        assertEquals($appium().android().find().findElements(By.xpath(byXPath)).size(), 1);
    }

    @Test
    public void findScrollable() {
        $appium().android().find().findElementByAccessibilityId("Views").click();
        MobileElement radioGroup = $appium().android().find()
                .findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()"
                        + ".resourceId(\"android:id/list\")).scrollIntoView("
                        + "new UiSelector().text(\"Radio Group\"));");
        assertNotNull(radioGroup.getLocation());
    }
}
