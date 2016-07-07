package com.atanas.kanchev.testframework.core.tests.handlers.appium.android.native_app_tests;

import com.atanas.kanchev.testframework.appium.driverfactory.AppiumDeviceTypesEnum;
import com.atanas.kanchev.testframework.core.handlers.wrappers.IAppium;
import com.atanas.kanchev.testframework.core.handlers.wrappers.IContext;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.io.File;

/**
 * @author Atanas Ksnchev
 */
public class BaseTest implements IAppium, IContext {

    @BeforeClass
    public static void setUp() throws Exception {
        File appDir = new File("src/test/java/com/atanas/kanchev/testframework/core/tests/handlers/appium/android/native_app_tests");
        File app = new File(appDir, "ApiDemos-debug.apk");

        DEVICE_BASED_HANDLER
                .setupDevice()
                .setApp(app.getAbsolutePath())
                .setDeviceType(AppiumDeviceTypesEnum.ANDROID_DEVICE)
                .setDeviceName("ZY22398GL7")
                .setPlatformVersion("6.0.1");

        DEVICE_BASED_HANDLER
                .setupDeviceServer()
                .setNewCommandTimeout(10)
                .setFullReset(false)
                .setAutoLaunch(false);

        DEVICE_BASED_HANDLER
                .setupAndroidDriver()
                .setAndroidDeviceReadyTimeout(10)
                .setEnablePerformanceLogging(true);

        DEVICE_BASED_HANDLER
                .startAppiumServer();

        DEVICE_BASED_HANDLER
                .initAndroidDriver("http://127.0.0.1:4723/wd/hub");
    }

    @AfterClass
    public static void tearDown() throws Exception {
        DEVICE_BASED_HANDLER.stopAppiumServer();
        CONTEXT_FACTORY.tearDownContexts();

    }
}
