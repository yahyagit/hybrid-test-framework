package com.atanas.kanchev.testframework.core.tests.handlers.appium.android.native_app_tests;

import io.appium.java_client.android.Connection;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AndroidConnectionTest extends BaseTest {

    @Test
    public void setWiFi() {
        android()
                .hasNetworkConnection()
                .setConnection(Connection.WIFI);
        assertEquals(Connection.WIFI,
                android()
                        .hasNetworkConnection()
                        .getConnection());
    }

    @Test
    public void setNoneAndAirplane() {
        android()
                .hasNetworkConnection()
                .setConnection(Connection.NONE);
        assertEquals(Connection.NONE,
                android()
                        .hasNetworkConnection()
                        .getConnection());
        android()
                .hasNetworkConnection()
                .setConnection(Connection.AIRPLANE);
        assertEquals(Connection.AIRPLANE,
                android()
                        .hasNetworkConnection()
                        .getConnection());
    }

    @Test
    public void setAll() {
        android()
                .hasNetworkConnection()
                .setConnection(Connection.ALL);
        assertEquals(Connection.ALL,
                android()
                        .hasNetworkConnection()
                        .getConnection());
    }
}
