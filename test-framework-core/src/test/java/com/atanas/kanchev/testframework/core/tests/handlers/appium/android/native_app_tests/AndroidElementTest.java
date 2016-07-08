package com.atanas.kanchev.testframework.core.tests.handlers.appium.android.native_app_tests;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import org.junit.Test;
import org.openqa.selenium.By;

import static org.junit.Assert.*;

public class AndroidElementTest extends BaseTest {

    @Test
    public void findByAccessibilityIdTest() {
        assertNotEquals(android()
                .find()
                .findElementById("android:id/content")
                .findElement(MobileBy.AccessibilityId("Graphics"))
                .getText(), null);
        assertEquals(android()
                .find()
                .findElementById("android:id/content")
                .findElements(MobileBy.AccessibilityId("Graphics"))
                .size(), 1);
    }

    @Test
    public void findByAndroidUIAutomatorTest() {
        assertNotEquals(android()
                .find()
                .findElementById("android:id/content")
                .findElement(MobileBy.AndroidUIAutomator("new UiSelector().clickable(true)")).getText(), null);
        assertNotEquals(android()
                .find()
                .findElementById("android:id/content")
                .findElements(MobileBy.AndroidUIAutomator("new UiSelector().clickable(true)")).size(), 0);
        assertNotEquals(android()
                .find().findElementById("android:id/content")
                .findElements(MobileBy.AndroidUIAutomator("new UiSelector().clickable(true)")).size(), 1);
    }

    @Test
    public void replaceValueTest() {
        String originalValue = "original value";

        android()
                .activity()
                .startActivity("io.APPIUM_INIT.android.apis", ".view.Controls1");
        AndroidElement editElement =
                android()
                        .find()
                        .findElementByAndroidUIAutomator("resourceId(\"io.APPIUM_INIT.android.apis:id/edit\")");
        editElement.sendKeys(originalValue);
        assertEquals(originalValue, editElement.getText());
        String replacedValue = "replaced value";
        editElement.replaceValue(replacedValue);
        assertEquals(replacedValue, editElement.getText());
    }

    @Test
    public void scrollingToSubElement() {
        android()
                .find()
                .findElementByAccessibilityId("Views").click();
        AndroidElement list = android()
                .find()
                .findElement(By.id("android:id/list"));
        MobileElement radioGroup = list
                .findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("
                        + "new UiSelector().text(\"Radio Group\"));"));
        assertNotNull(radioGroup.getLocation());
    }
}