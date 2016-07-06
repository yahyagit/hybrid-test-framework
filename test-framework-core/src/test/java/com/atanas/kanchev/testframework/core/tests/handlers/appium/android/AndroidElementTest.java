package com.atanas.kanchev.testframework.core.tests.handlers.appium.android;

import com.atanas.kanchev.testframework.appium.driverfactory.AppiumDeviceTypesEnum;
import com.atanas.kanchev.testframework.core.handlers.wrappers.IWrapper;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import java.io.File;

import static org.junit.Assert.*;

public class AndroidElementTest implements IWrapper {

    @Before
    public void setUp() throws Exception {

        File appDir = new File("src\\test\\java\\com\\atanas\\kanchev\\testframework\\core\\tests\\handlers\\appium\\android");
        File app = new File(appDir, "ApiDemos-debug.apk");

        setup()
                .setupDevice()
                .setApp(app.getAbsolutePath())
                .setDeviceType(AppiumDeviceTypesEnum.ANDROID_DEVICE)
                .setDeviceName("ZY22398GL7")
                .setPlatformVersion("6.0.1");

        setup()
                .setupDeviceServer()
                .setNewCommandTimeout(10)
                .setFullReset(false)
                .setAutoLaunch(false);

        setup()
                .setupAndroidDriver()
                .setAndroidDeviceReadyTimeout(10)
                .setEnablePerformanceLogging(true);

        setup()
                .initAndroidDriver("http://127.0.0.1:4723/wd/hub");

        appium().methods().startActivity().startActivity("io.appium.android.apis", ".ApiDemos");

    }


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