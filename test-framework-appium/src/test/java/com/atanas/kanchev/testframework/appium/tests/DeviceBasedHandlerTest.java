package com.atanas.kanchev.testframework.appium.tests;

import com.atanas.kanchev.testframework.appium.driverfactory.AppiumDeviceTypesEnum;
import com.atanas.kanchev.testframework.appium.handlers.DeviceBasedHandler;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.junit.Test;

import java.io.File;

/**
 * @author Atanas Ksnchev
 */
public class DeviceBasedHandlerTest extends DeviceBasedHandler {

    @Test
    public void setupDeviceTest() throws Exception {

        File classpathRoot = new File(System.getProperty("user.dir"));
        //File appDir = new File(classpathRoot, "D:\\IdeaProjects\\personal\\test-framework\\test-framework-appium\\src\\test\\java\\com\\atanas\\kanchev\\testframework\\appium\\tests\\ContactManager.apk");
        File app = new File("D:\\IdeaProjects\\personal\\test-framework\\test-framework-appium\\src\\test\\java\\com\\atanas\\kanchev\\testframework\\appium\\tests\\ContactManager.apk");

        setupDevice()
                .setDeviceType(AppiumDeviceTypesEnum.ANDROID_DEVICE)
                .setDeviceName("a1001")
                .setPlatformName("Android")
                .setPlatformVersion("6.0.1")
                .setApp("Chrome");

        setupDeviceServer()
                .setFullReset(false)
                .setAutoLaunch(false);


        setupAndroidDriver()
                //.setAppPackage("com.example.android.contactmanager")
                .setAppPackage("com.android.chrome")
                .setAndroidDeviceReadyTimeout(60)
                .setEnablePerformanceLogging(true);

        AppiumDriver<AndroidElement> d = createAndroidDriver("http://127.0.0.1:4723/wd/hub");
        d.get("https://www.google.co.uk");
        Thread.sleep(10000);
        d.quit();
    }
}