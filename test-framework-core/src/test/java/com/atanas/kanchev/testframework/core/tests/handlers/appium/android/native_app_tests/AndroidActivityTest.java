package com.atanas.kanchev.testframework.core.tests.handlers.appium.android.native_app_tests;

import com.atanas.kanchev.testframework.core.handlers.wrappers.IWrapper;
import io.appium.java_client.android.AndroidKeyCode;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AndroidActivityTest extends BaseTest implements IWrapper {

    @Test
    public void startActivityInThisAppTestCase() {

        appium()
                .methods()
                .startActivity()
                .startActivity("io.appium.android.apis", ".accessibility.AccessibilityNodeProviderActivity");
        assertEquals(appium()
                .methods()
                .appiumDriverMethods()
                .currentActivity(), ".accessibility.AccessibilityNodeProviderActivity");
    }

    @Test
    public void startActivityWithWaitingAppTestCase() {

        appium()
                .methods()
                .startActivity()
                .startActivity("io.appium.android.apis", ".accessibility.AccessibilityNodeProviderActivity",
                        "io.appium.android.apis", ".accessibility.AccessibilityNodeProviderActivity");
        assertEquals(appium()
                .methods()
                .appiumDriverMethods()
                .currentActivity(), ".accessibility.AccessibilityNodeProviderActivity");
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
        assertEquals(appium().methods().appiumDriverMethods().currentActivity(), ".accessibility.AccessibilityNodeProviderActivity");
        appium().methods().startActivity().startActivity("com.android.contacts", ".ContactsListActivity",
                "com.android.contacts", ".ContactsListActivity", false);
        assertEquals(appium().methods().appiumDriverMethods().currentActivity(), ".ContactsListActivity");
        appium().methods().actionShortcuts().pressKeyCode(AndroidKeyCode.BACK);
        assertEquals(appium().methods().appiumDriverMethods().currentActivity(),
                ".accessibility.AccessibilityNodeProviderActivity");
    }
}
