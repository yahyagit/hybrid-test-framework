package com.atanas.kanchev.testframework.core.tests.handlers.selenium;

import com.atanas.kanchev.testframework.appium.driverfactory.AppiumDeviceTypesEnum;
import com.atanas.kanchev.testframework.core.handlers.wrappers.IAppium;
import com.atanas.kanchev.testframework.core.handlers.wrappers.IContext;
import io.appium.java_client.remote.MobileBrowserType;
import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 * @author Atanas Ksnchev
 */
public class BaseTest implements IAppium, IContext {

    @BeforeClass
    public static void setUp() throws Exception {

        APPIUM_INIT
                .setupDevice()
                .setDeviceType(AppiumDeviceTypesEnum.ANDROID_DEVICE)
                .setDeviceName("ZY22398GL7")
                .setPlatformVersion("6.0.1");

        APPIUM_INIT
                .setupDeviceServer()
                .setBrowserName(MobileBrowserType.CHROME)
                .setFullReset(false)
                .setAutoLaunch(false);

        APPIUM_INIT
                .setupAndroidDriver()
                .setAndroidDeviceReadyTimeout(60)
                .setEnablePerformanceLogging(true);
        APPIUM_INIT
                .startAppiumServer();

        APPIUM_INIT
                .initAndroidDriver("http://127.0.0.1:4723/wd/hub");

    }

    @AfterClass
    public static void tearDown() throws Exception {
        CONTEXT_FACTORY.tearDownContexts();
        APPIUM_INIT.stopAppiumServer();
    }
}
