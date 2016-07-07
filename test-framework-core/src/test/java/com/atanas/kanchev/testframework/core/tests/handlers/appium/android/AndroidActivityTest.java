package com.atanas.kanchev.testframework.core.tests.handlers.appium.android;

import com.atanas.kanchev.testframework.appium.driverfactory.AppiumDeviceTypesEnum;
import com.atanas.kanchev.testframework.core.handlers.wrappers.IWrapper;
import io.appium.java_client.android.AndroidKeyCode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static junit.framework.Assert.assertEquals;

public class AndroidActivityTest implements IWrapper {

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
    public void startActivityInThisAppTestCase() {
        appium().methods().startActivity().startActivity("io.appium.android.apis",
                ".accessibility.AccessibilityNodeProviderActivity");
        assertEquals(appium().methods().appiumDriverMethods().currentActivity(),
                ".accessibility.AccessibilityNodeProviderActivity");
    }

    @Test
    public void startActivityWithWaitingAppTestCase() {
        appium().methods().startActivity().startActivity("io.appium.android.apis",
                ".accessibility.AccessibilityNodeProviderActivity",
                "io.appium.android.apis", ".accessibility.AccessibilityNodeProviderActivity");
        assertEquals(appium().methods().appiumDriverMethods().currentActivity(),
                ".accessibility.AccessibilityNodeProviderActivity");
    }

    @Test
    public void startActivityInNewAppTestCase() {
        appium().methods().startActivity().startActivity("com.android.contacts", ".ContactsListActivity");
        assertEquals(appium().methods().appiumDriverMethods().currentActivity(), ".ContactsListActivity");
        appium().methods().actionShortcuts().pressKeyCode(AndroidKeyCode.BACK);
        assertEquals(appium().methods().appiumDriverMethods().currentActivity(), ".ContactsListActivity");
    }

    @Test
    public void startActivityInNewAppTestCaseWithoutClosingApp() {
        appium().methods().startActivity().startActivity("io.appium.android.apis",
                ".accessibility.AccessibilityNodeProviderActivity");
        Assert.assertEquals(appium().methods().appiumDriverMethods().currentActivity(), ".accessibility.AccessibilityNodeProviderActivity");
        appium().methods().startActivity().startActivity("com.android.contacts", ".ContactsListActivity",
                "com.android.contacts", ".ContactsListActivity", false);
        Assert.assertEquals(appium().methods().appiumDriverMethods().currentActivity(), ".ContactsListActivity");
        appium().methods().actionShortcuts().pressKeyCode(AndroidKeyCode.BACK);
        Assert.assertEquals(appium().methods().appiumDriverMethods().currentActivity(),
                ".accessibility.AccessibilityNodeProviderActivity");
    }
}
