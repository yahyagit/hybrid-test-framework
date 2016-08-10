package com.atanas.kanchev.testframework.core.tests.handlers.appium.android.native_app_tests;

import com.atanas.kanchev.testframework.appium.wrappers.IAppium;
import com.atanas.kanchev.testframework.commons.wrappers.IContext;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.Platform;

import java.io.File;

/**
 * @author Atanas Ksnchev
 */
public class BaseTest implements IAppium, IContext {

    @BeforeClass
    public static void setUp() throws Exception {
        File appDir = new File("src/test/java/com/atanas/kanchev/testframework/core/tests/handlers/appium/android/native_app_tests");
        File app = new File(appDir, "ApiDemos-debug.apk");

//        APPIUM_INIT
//                .setupDevice()
//                .setApp(app.getAbsolutePath())
//                .setDeviceType(AppiumDeviceTypesEnum.ANDROID_DEVICE)
//                .setDeviceName("ZY22398GL7")
//                .setPlatformVersion("6.0.1");
//
//        APPIUM_INIT
//                .setupDeviceServer()
//                .setNewCommandTimeout(10)
//                .setFullReset(false)
//                .setAutoLaunch(false);
//
//        APPIUM_INIT
//                .setupAndroidDriver()
//                .setAndroidDeviceReadyTimeout(10)
//                .setEnablePerformanceLogging(true);
//
//        APPIUM_INIT
//                .startAppiumServer();
//
//        APPIUM_INIT
//                .initAndroidDriver("http://127.0.0.1:4723/wd/hub");

        APPIUM_DRIVER_FACTORY
                .buildDefaultService()
                .startServer();

        APPIUM_DRIVER_FACTORY
                .setCap(MobileCapabilityType.APP, app.getAbsolutePath())
                .setCap(MobileCapabilityType.DEVICE_NAME, "8adea98f")
                .setCap(MobileCapabilityType.PLATFORM_VERSION, "6.0.1")
                .setCap(MobileCapabilityType.PLATFORM, Platform.ANDROID)

                .setCap(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 10)
                .setCap(MobileCapabilityType.FULL_RESET, false)
                .setCap(AndroidMobileCapabilityType.ANDROID_DEVICE_READY_TIMEOUT, 10)
                .setCap(AndroidMobileCapabilityType.ENABLE_PERFORMANCE_LOGGING, true)
                .getAndroidDriver();

//        APPIUM_DRIVER_FACTORY.getAndroidDriver();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        CONTEXT_FACTORY.tearDownContexts();
        APPIUM_DRIVER_FACTORY.stopServer();
    }
}
