package com.atanas.kanchev.testframework.core.tests.handlers.appium.android.native_app_tests;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import static org.junit.Assert.*;

public class AndroidSearchingTest extends BaseTest {

    @Before
    public void before() throws Exception {
        android()
                .activity()
                .startActivity("io.APPIUM_INIT.android.apis", ".ApiDemos");
    }

    @Test
    public void findByAccessibilityIdTest() {
        assertNotEquals(android().find().findElement(MobileBy.AccessibilityId("Graphics")).getText(), null);
        assertEquals(android().find().findElements(MobileBy.AccessibilityId("Graphics")).size(), 1);
    }

    @Test
    public void findByAndroidUIAutomatorTest() {
        assertNotEquals(android().find()
                .findElement(MobileBy.AndroidUIAutomator("new UiSelector().clickable(true)")).getText(), null);
        assertNotEquals(android().find()
                .findElements(MobileBy.AndroidUIAutomator("new UiSelector().clickable(true)")).size(), 0);
        assertNotEquals(android().find()
                .findElements(MobileBy.AndroidUIAutomator("new UiSelector().clickable(true)")).size(), 1);
    }

    @Test
    public void findByXPathTest() {
        String byXPath = "//android.widget.TextView[contains(@text, 'Animat')]";
        assertNotNull(android().find().findElement(By.xpath(byXPath)).getText());
        assertEquals(android().find().findElements(By.xpath(byXPath)).size(), 1);
    }

    @Test
    public void findScrollable() {
        android().find().findElementByAccessibilityId("Views").click();
        MobileElement radioGroup = android().find()
                .findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()"
                        + ".resourceId(\"android:id/list\")).scrollIntoView("
                        + "new UiSelector().text(\"Radio Group\"));");
        assertNotNull(radioGroup.getLocation());
    }
}
