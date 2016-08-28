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
import io.appium.java_client.android.AndroidElement;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.atanas.kanchev.testframework.appium.accessors.AppiumAccessors.$appium;
import static org.junit.Assert.*;

public class AndroidElementTest extends BaseTest {

    @Before
    public void setup() throws Exception {

        $appium().android().activity().startActivity("io.appium.android.apis", ".ApiDemos");

    }

    @Test
    public void findByAccessibilityIdTest() {

        assertNotEquals($appium().android()
                .find()
                .findElementById("android:id/content")
                .findElement(MobileBy.AccessibilityId("Graphics"))
                .getText(), null);
        assertEquals($appium().android()
                .find()
                .findElementById("android:id/content")
                .findElements(MobileBy.AccessibilityId("Graphics"))
                .size(), 1);
    }

    @Test
    public void findByAndroidUIAutomatorTest() {

        assertNotEquals($appium().android()
                .find()
                .findElementById("android:id/content")
                .findElement(MobileBy.AndroidUIAutomator("new UiSelector().clickable(true)")).getText(), null);
        assertNotEquals($appium().android()
                .find()
                .findElementById("android:id/content")
                .findElements(MobileBy.AndroidUIAutomator("new UiSelector().clickable(true)")).size(), 0);
        assertNotEquals($appium().android()
                .find().findElementById("android:id/content")
                .findElements(MobileBy.AndroidUIAutomator("new UiSelector().clickable(true)")).size(), 1);
    }

    @Test
    public void replaceValueTest() {

        String originalValue = "original value";

        $appium().android()
                .activity()
                .startActivity("io.appium.android.apis", ".view.Controls1");
        AndroidElement editElement =
                $appium().android()
                        .find()
                        .findElementByAndroidUIAutomator("resourceId(\"io.appium.android.apis:id/edit\")");
        editElement.sendKeys(originalValue);
        assertEquals(originalValue, editElement.getText());
        String replacedValue = "replaced value";
        editElement.replaceValue(replacedValue);
        assertEquals(replacedValue, editElement.getText());
    }

    @Test
    public void scrollingToSubElement() {

        $appium().android()
                .find()
                .findElementByAccessibilityId("Views").click();
        AndroidElement list = $appium().android()
                .find()
                .findElement(By.id("android:id/list"));
        MobileElement radioGroup = list
                .findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("
                        + "new UiSelector().text(\"Radio Group\"));"));
        assertNotNull(radioGroup.getLocation());
    }

    @Test
    public void setValueTest() {

        String value = "new value";

        $appium().android().activity().startActivity("io.appium.android.apis", ".view.Controls1");
        AndroidElement editElement = $appium().android().find()
                .findElementByAndroidUIAutomator("resourceId(\"io.appium.android.apis:id/edit\")");
        editElement.setValue(value);
        assertEquals(value, editElement.getText());
    }
}
