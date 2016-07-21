package com.atanas.kanchev.testframework.core.tests.handlers.appium.android.native_app_tests;

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
        File appDir = new File("src/test/java/com/atanas/kanchev/testframework/core/tests/handlers/APPIUM_INIT/android/native_app_tests");
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
    }

    @AfterClass
    public static void tearDown() throws Exception {
        CONTEXT_FACTORY.tearDownContexts();
//        APPIUM_INIT.stopAppiumServer();
    }
}
