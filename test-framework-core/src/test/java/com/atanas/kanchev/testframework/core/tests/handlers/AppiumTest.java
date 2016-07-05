package com.atanas.kanchev.testframework.core.tests.handlers;

import com.atanas.kanchev.testframework.appium.driverfactory.AppiumDeviceTypesEnum;
import com.atanas.kanchev.testframework.core.handlers.wrappers.IWrapper;
import io.appium.java_client.remote.MobileBrowserType;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;

/**
 * @author Atanas Ksnchev
 */
public class AppiumTest implements IWrapper {
    @After
    public void tearDown() throws Exception {
        context().tearDownContexts();
    }

    @Test
    public void androidChromeTest() throws Exception {

        setup()
                .setupDevice()
                .setDeviceType(AppiumDeviceTypesEnum.ANDROID_DEVICE)
                .setDeviceName("ZY22398GL7")
                .setPlatformVersion("6.0.1");

        setup()
                .setupDeviceServer()
                .setBrowserName(MobileBrowserType.CHROME)
                .setFullReset(false)
                .setAutoLaunch(false);

        setup()
                .setupAndroidDriver()
                .setAndroidDeviceReadyTimeout(60)
                .setEnablePerformanceLogging(true);

        setup()
                .initAndroidDriver("http://127.0.0.1:4723/wd/hub");
        goTo("https://bbc.co.uk");
        find().elementBy(By.id("idcta-link"));

        appium().deviceTap();
        interact().click();


        Thread.sleep(10000);

        appium().methods().rotate().rotate(ScreenOrientation.PORTRAIT);


    }

    @Test
    public void setupDeviceTest() throws Exception {

//        File classpathRoot = new File(System.getProperty("user.dir"));
//        //File appDir = new File(classpathRoot, "D:\\IdeaProjects\\personal\\test-framework\\test-framework-appium\\src\\test\\java\\com\\atanas\\kanchev\\testframework\\appium\\tests\\ContactManager.apk");
//        File app = new File("D:\\IdeaProjects\\personal\\test-framework\\test-framework-appium\\src\\test\\java\\com\\atanas\\kanchev\\testframework\\appium\\tests\\ContactManager.apk");
//
//        setupDevice()
//                .setDeviceType(AppiumDeviceTypesEnum.ANDROID_DEVICE)
//                .setDeviceName("a1001")
//                .setPlatformName("Android")
//                .setPlatformVersion("6.0.1")
//                .setApp("Chrome");
//
//        setupDeviceServer()
//                .setFullReset(false)
//                .setAutoLaunch(false);
//
//
//        setupAndroidDriver()
//                //.setAppPackage("com.example.android.contactmanager")
//                .setAppPackage("com.android.chrome")
//                .setAndroidDeviceReadyTimeout(60)
//                .setEnablePerformanceLogging(true);
//
//        AndroidDriver<AndroidElement> d = initAndroidDriver("http://127.0.0.1:4723/wd/hub");
//        d.get("https://bbc.co.uk");
//        Thread.sleep(10000);
//        d.quit();
    }
}