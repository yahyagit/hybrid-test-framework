package com.atanas.kanchev.testframework.appium.tests.android.native_app_tests;

import io.appium.java_client.android.Connection;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AndroidConnectionTest extends BaseTest {

    @Test
    public void setWiFi() {
        android()
                .networkConnection()
                .setConnection(Connection.WIFI);
        assertEquals(Connection.WIFI,
                android()
                        .networkConnection()
                        .getConnection());
    }

    @Test
    public void setNoneAndAirplane() {
        android()
                .networkConnection()
                .setConnection(Connection.NONE);
        assertEquals(Connection.NONE,
                android()
                        .networkConnection()
                        .getConnection());
        android()
                .networkConnection()
                .setConnection(Connection.AIRPLANE);
        assertEquals(Connection.AIRPLANE,
                android()
                        .networkConnection()
                        .getConnection());
    }

    @Test
    public void setAll() {
        android()
                .networkConnection()
                .setConnection(Connection.ALL);
        assertEquals(Connection.ALL,
                android()
                        .networkConnection()
                        .getConnection());
    }
}
