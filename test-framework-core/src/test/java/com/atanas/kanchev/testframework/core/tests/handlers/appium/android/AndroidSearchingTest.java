/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.atanas.kanchev.testframework.core.tests.handlers.appium.android;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class AndroidSearchingTest extends BaseTest {

    @Before
    public void before() throws Exception {
        appium().methods().startActivity().startActivity("io.appium.android.apis", ".ApiDemos");
    }

    @Test
    public void findByAccessibilityIdTest() {
        assertNotEquals(appium().methods().find().findElement(MobileBy.AccessibilityId("Graphics")).getText(), null);
        assertEquals(appium().methods().find().findElements(MobileBy.AccessibilityId("Graphics")).size(), 1);
    }

    @Test
    public void findByAndroidUIAutomatorTest() {
        assertNotEquals(appium().methods().find()
                .findElement(MobileBy
                        .AndroidUIAutomator("new UiSelector().clickable(true)")).getText(), null);
        assertNotEquals(appium().methods().find()
                .findElements(MobileBy
                        .AndroidUIAutomator("new UiSelector().clickable(true)")).size(), 0);
        assertNotEquals(appium().methods().find()
                .findElements(MobileBy
                        .AndroidUIAutomator("new UiSelector().clickable(true)")).size(), 1);
    }

    @Test
    public void findByXPathTest() {
        String byXPath = "//android.widget.TextView[contains(@text, 'Animat')]";
        assertNotNull(appium().methods().find().findElement(By.xpath(byXPath)).getText());
        assertEquals(appium().methods().find().findElements(By.xpath(byXPath)).size(), 1);
    }

    @Test
    public void findScrollable() {
        appium().methods().find().findElementByAccessibilityId("Views").click();
        MobileElement radioGroup = appium().methods().find()
                .findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()"
                        + ".resourceId(\"android:id/list\")).scrollIntoView("
                        + "new UiSelector().text(\"Radio Group\"));");
        assertNotNull(radioGroup.getLocation());
    }
}
