package com.atanas.kanchev.testframework.core.tests.handlers.appium.android;

import com.atanas.kanchev.testframework.core.handlers.wrappers.IWrapper;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import org.junit.Test;
import org.openqa.selenium.By;

import static org.junit.Assert.*;

public class AndroidElementTest extends BaseTest implements IWrapper {

    @Test
    public void findByAccessibilityIdTest() {
        assertNotEquals(appium().methods().find().findElementById("android:id/content")
                .findElement(MobileBy.AccessibilityId("Graphics")).getText(), null);
        assertEquals(appium().methods().find().findElementById("android:id/content")
                .findElements(MobileBy.AccessibilityId("Graphics")).size(), 1);
    }

    @Test
    public void findByAndroidUIAutomatorTest() {
        assertNotEquals(appium().methods().find().findElementById("android:id/content")
                .findElement(MobileBy
                        .AndroidUIAutomator("new UiSelector().clickable(true)")).getText(), null);
        assertNotEquals(appium().methods().find().findElementById("android:id/content")
                .findElements(MobileBy
                        .AndroidUIAutomator("new UiSelector().clickable(true)")).size(), 0);
        assertNotEquals(appium().methods().find().findElementById("android:id/content")
                .findElements(MobileBy
                        .AndroidUIAutomator("new UiSelector().clickable(true)")).size(), 1);
    }

    @Test
    public void replaceValueTest() {
        String originalValue = "original value";

        appium().methods().startActivity().startActivity("io.appium.android.apis", ".view.Controls1");
        AndroidElement editElement = appium().methods()
                .find().findElementByAndroidUIAutomator("resourceId(\"io.appium.android.apis:id/edit\")");
        editElement.sendKeys(originalValue);
        assertEquals(originalValue, editElement.getText());
        String replacedValue = "replaced value";
        editElement.replaceValue(replacedValue);
        assertEquals(replacedValue, editElement.getText());
    }

    @Test
    public void scrollingToSubElement() {
        appium().methods().find().findElementByAccessibilityId("Views").click();
        AndroidElement list = appium().methods().find().findElement(By.id("android:id/list"));
        MobileElement radioGroup = list
                .findElement(MobileBy
                        .AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("
                                + "new UiSelector().text(\"Radio Group\"));"));
        assertNotNull(radioGroup.getLocation());
    }
}