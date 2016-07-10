package com.atanas.kanchev.testframework.core.tests.handlers.appium.android.browser_tests;

import com.atanas.kanchev.testframework.appium.driverfactory.AppiumDeviceTypesEnum;
import com.atanas.kanchev.testframework.core.handlers.wrappers.*;
import io.appium.java_client.remote.MobileBrowserType;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;

/**
 * Created by atanas on 09/07/2016.
 */
public class ChromeTest implements IContext, IAppium, ISelenium {

    @After
    public void tearDown() throws Exception {
        context().tearDownContexts();
    }

    @Test
    public void androidChromeTest() throws Exception {

        appiumInit()
                .setupDevice()
                .setDeviceType(AppiumDeviceTypesEnum.ANDROID_DEVICE)
                .setDeviceName("ZY22398GL7")
                .setPlatformVersion("6.0.1");

        appiumInit()
                .setupDeviceServer()
                .setBrowserName(MobileBrowserType.CHROME)
                .setFullReset(false)
                .setAutoLaunch(false);

        appiumInit()
                .setupAndroidDriver()
                .setAndroidDeviceReadyTimeout(60)
                .setEnablePerformanceLogging(true);

        appiumInit()
                .initAndroidDriver("http://127.0.0.1:4723/wd/hub");

        goTo("https://bbc.co.uk");

        find()
                .elementBy(By.id("idcta-link"));

        interact()
                .click();

        android()
                .orientation()
                .rotate(ScreenOrientation.LANDSCAPE);


    }
}
