package com.atanas.kanchev.testframework.appium.tests.android.browser_tests;

import com.atanas.kanchev.testframework.appium.wrappers.IAppium;
import com.atanas.kanchev.testframework.commons.wrappers.IContext;
import com.atanas.kanchev.testframework.selenium.context.SeleniumContext;
import com.atanas.kanchev.testframework.selenium.wrappers.ISelenium;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.SearchContext;

public class ChromeTest implements IContext<SeleniumContext>, IAppium, ISelenium {

    @After public void tearDown() throws Exception {
        context().tearDownContexts();
    }

    @Test public void androidChromeTest() throws Exception {

        appiumInit().buildDefaultService();
        appiumInit().startServer();

        appiumInit()

            .setCap(MobileCapabilityType.BROWSER_NAME, MobileBrowserType.CHROME)
            .setCap(MobileCapabilityType.PLATFORM, Platform.ANDROID)
            .setCap(MobileCapabilityType.DEVICE_NAME, "ZY22398GL7")
            .setCap(MobileCapabilityType.PLATFORM_VERSION, "6.0.1")

            .setCap(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60)
            .setCap(MobileCapabilityType.FULL_RESET, false)
            .setCap(AndroidMobileCapabilityType.ANDROID_DEVICE_READY_TIMEOUT, 60)
            .setCap(AndroidMobileCapabilityType.ENABLE_PERFORMANCE_LOGGING, true);

        appiumInit().getAndroidDriver();

        goTo("https://bbc.co.uk");

        find().elementBy(By.id("idcta-link"));

        interact().click();

        android().orientation().rotate(ScreenOrientation.LANDSCAPE);

    }
}
