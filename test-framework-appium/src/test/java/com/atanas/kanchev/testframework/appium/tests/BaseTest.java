package com.atanas.kanchev.testframework.appium.tests;

import com.atanas.kanchev.testframework.appium.wrappers.IAppium;
import com.atanas.kanchev.testframework.commons.wrappers.IContext;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.BeforeClass;
import org.openqa.selenium.Platform;

/**
 * @author Atanas Ksnchev
 */
public class BaseTest implements IAppium, IContext {

    @BeforeClass
    public static void setUp() throws Exception {

        APPIUM_DRIVER_FACTORY
                .buildDefaultService()
                .startServer();

        APPIUM_DRIVER_FACTORY
                .setCap(MobileCapabilityType.BROWSER_NAME, "chrome")
                .setCap(MobileCapabilityType.DEVICE_NAME, "8adea98f")
                .setCap(MobileCapabilityType.PLATFORM_VERSION, "6.0.1")
                .setCap(MobileCapabilityType.PLATFORM, Platform.ANDROID)

                .setCap(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 10)
                .setCap(MobileCapabilityType.FULL_RESET, false)
                .setCap(MobileCapabilityType.NO_RESET, true)
                .setCap(AndroidMobileCapabilityType.ANDROID_DEVICE_READY_TIMEOUT, 10)
                .setCap(AndroidMobileCapabilityType.ENABLE_PERFORMANCE_LOGGING, true);

    }

}
