package com.atanas.kanchev.testframework.appium.tests.android.native_app_tests;

import io.appium.java_client.android.AndroidKeyCode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AndroidActivityTest extends BaseTest {

    @Before
    public void setUp() throws Exception {
        android()
                .activity()
                .startActivity("io.appium.android.apis", ".ApiDemos");
    }

    @Test
    public void startActivityInThisAppTestCase() {

        android().activity().startActivity("io.appium.android.apis",
                ".accessibility.AccessibilityNodeProviderActivity");
        assertEquals(android().appiumDriverMethods().currentActivity(),
                ".accessibility.AccessibilityNodeProviderActivity");
    }

    @Test
    public void startActivityWithWaitingAppTestCase() {

        android()
                .activity()
                .startActivity("io.appium.android.apis", ".accessibility.AccessibilityNodeProviderActivity",
                        "io.appium.android.apis", ".accessibility.AccessibilityNodeProviderActivity");
        assertEquals(android()
                .appiumDriverMethods()
                .currentActivity(), ".accessibility.AccessibilityNodeProviderActivity");
    }

    @Test
    public void startActivityInNewAppTestCase() {

        android().
                activity().startActivity("com.android.contacts", ".ContactsListActivity");
        assertEquals(android()
                .appiumDriverMethods()
                .currentActivity(), ".ContactsListActivity");
        android()
                .actionShortcuts()
                .pressKeyCode(AndroidKeyCode.BACK);
        assertEquals(android()
                .appiumDriverMethods()
                .currentActivity(), ".ContactsListActivity");
    }

    @Test
    public void startActivityInNewAppTestCaseWithoutClosingApp() {
        android().
                activity()
                .startActivity("io.appium.android.apis",
                        ".accessibility.AccessibilityNodeProviderActivity");
        assertEquals(android().
                appiumDriverMethods()
                .currentActivity(), ".accessibility.AccessibilityNodeProviderActivity");
        android().
                activity()
                .startActivity("com.android.contacts", ".ContactsListActivity",
                        "com.android.contacts", ".ContactsListActivity", false);
        assertEquals(android().
                appiumDriverMethods()
                .currentActivity(), ".ContactsListActivity");
        android().
                actionShortcuts()
                .pressKeyCode(AndroidKeyCode.BACK);
        assertEquals(android().
                appiumDriverMethods()
                .currentActivity(), ".accessibility.AccessibilityNodeProviderActivity");
    }
}
