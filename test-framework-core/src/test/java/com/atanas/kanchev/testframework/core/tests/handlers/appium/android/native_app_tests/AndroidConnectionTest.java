package com.atanas.kanchev.testframework.core.tests.handlers.appium.android.native_app_tests;

import io.appium.java_client.android.Connection;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AndroidConnectionTest extends BaseTest {

    @Test
    public void setWiFi() {
        appium().methods().hasNetworkConnection().setConnection(Connection.WIFI);
        assertEquals(Connection.WIFI,
                appium().methods().hasNetworkConnection().getConnection());
    }

    @Test
    public void setNoneAndAirplane() {
        appium().methods().hasNetworkConnection().setConnection(Connection.NONE);
        assertEquals(Connection.NONE,
                appium().methods().hasNetworkConnection().getConnection());
        appium().methods().hasNetworkConnection().setConnection(Connection.AIRPLANE);
        assertEquals(Connection.AIRPLANE,
                appium().methods().hasNetworkConnection().getConnection());
    }

    @Test
    public void setAll() {
        appium().methods().hasNetworkConnection().setConnection(Connection.ALL);
        assertEquals(Connection.ALL,
                appium().methods().hasNetworkConnection().getConnection());
    }
}
