package com.atanas.kanchev.testframework.core.tests.handlers.appium.android;

import com.atanas.kanchev.testframework.appium.driverfactory.AppiumDeviceTypesEnum;
import com.atanas.kanchev.testframework.core.handlers.wrappers.IAppium;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertNotEquals;

public class AndroidAppStringsTest implements IAppium {

    @Before
    public void setUp() throws Exception {

        File appDir = new File("src\\test\\java\\com\\atanas\\kanchev\\testframework\\core\\tests\\handlers\\appium\\android");
        File app = new File(appDir, "ApiDemos-debug.apk");

        setup()
                .setupDevice()
                .setApp(app.getAbsolutePath())
                .setDeviceType(AppiumDeviceTypesEnum.ANDROID_DEVICE)
                .setDeviceName("ZY22398GL7")
                .setPlatformVersion("6.0.1");

        setup()
                .setupDeviceServer()
                .setNewCommandTimeout(10)
                .setFullReset(false)
                .setAutoLaunch(false);

        setup()
                .setupAndroidDriver()
                .setAndroidDeviceReadyTimeout(10)
                .setEnablePerformanceLogging(true);

        setup()
                .initAndroidDriver("http://127.0.0.1:4723/wd/hub");

    }

    @Test
    public void getAppStrings() {
        assertNotEquals(0, appium().methods().hasAppStrings().getAppStringMap().size());
    }

    @Test
    public void getGetAppStringsUsingLang() {
        assertNotEquals(0, appium().methods().hasAppStrings().getAppStringMap("en").size());
    }
}
