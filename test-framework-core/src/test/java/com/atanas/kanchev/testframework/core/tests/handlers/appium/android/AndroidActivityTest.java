package com.atanas.kanchev.testframework.core.tests.handlers.appium.android;

import com.atanas.kanchev.testframework.core.handlers.wrappers.IWrapper;
import io.appium.java_client.android.AndroidKeyCode;
import org.junit.Assert;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class AndroidActivityTest extends BaseTest implements IWrapper {

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
