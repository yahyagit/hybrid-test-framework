package com.atanas.kanchev.testframework.appium.tests.android.native_app_tests;

import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

public class OpenNotificationsTest extends BaseTest {

    @Test
    public void openNotification() throws Exception {
        android().appiumDriverMethods().openNotifications();
        assertNotEquals(0, android().find().findElementsById("com.android.systemui:id/time_view").size());
        android().appiumDriverMethods().openNotifications();
    }
}
