package com.atanas.kanchev.testframework.core.tests.handlers.appium.android.native_app_tests;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.Connection;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class AndroidConnectionTest extends BaseTest{

    private static AppiumDriverLocalService service;
    private static AndroidDriver<MobileElement> driver;

    /**
     * initialization.
     */
    @BeforeClass
    public static void beforeClass() throws Exception {
        service = AppiumDriverLocalService.buildDefaultService();
        service.start();

        if (service == null || !service.isRunning()) {
            throw new RuntimeException("An appium server node is not started!");
        }

        File appDir = new File("src/test/java/io/appium/java_client");
        File app = new File(appDir, "ApiDemos-debug.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        driver = new AndroidDriver<>(service.getUrl(), capabilities);
    }

    /**
     * finishing.
     */
    @AfterClass
    public static void afterClass() {
        if (driver != null) {
            try {
                driver.setConnection(Connection.DATA);
            } finally {
                driver.quit();
            }
        }
        if (service != null) {
            service.stop();
        }
    }

    @Test
    public void setWiFi() {
        driver.setConnection(Connection.WIFI);
        assertEquals(Connection.WIFI,
            driver.getConnection());
    }

    @Test
    public void setNoneAndAirplane() {
        driver.setConnection(Connection.NONE);
        assertEquals(Connection.NONE,
            driver.getConnection());
        driver.setConnection(Connection.AIRPLANE);
        assertEquals(Connection.AIRPLANE,
            driver.getConnection());
    }

    @Test
    public void setAll() {
        driver.setConnection(Connection.ALL);
        assertEquals(Connection.ALL,
            driver.getConnection());
    }
}
