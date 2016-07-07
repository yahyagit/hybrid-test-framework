package com.atanas.kanchev.testframework.core.tests.handlers.appium.android.native_app_tests;

import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

public class OpenNotificationsTest extends BaseTest {

    @Test
    public void openNotification() throws Exception {
        appium().methods().appiumDriverMethods().openNotifications();
        assertNotEquals(0, appium().methods().find().findElementsById("com.android.systemui:id/time_view").size());
        appium().methods().appiumDriverMethods().openNotifications();
    }
}
