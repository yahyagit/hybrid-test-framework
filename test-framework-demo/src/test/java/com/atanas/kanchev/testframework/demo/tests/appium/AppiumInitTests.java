package com.atanas.kanchev.testframework.demo.tests.appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.junit.After;

/**
 * @author Atanas Ksnchev
 */
public class AppiumInitTests  {

    private AppiumDriver<AndroidElement> d;
    private String AppiumHub = "http://127.0.0.1:4723/wd/hub";

    @After
    public void tearDown() throws Exception {
        d.quit();
        d = null;
    }

//    @Test
//    public void androidNativeAppTest() throws Exception {
//
//        File app = new File(".\\src\\test\\resources\\ContactManager.apk");
//
//        setupDevice()
//                .setDeviceType(AppiumDeviceTypesEnum.ANDROID_DEVICE)
//                .setDeviceName("a1001")
//                .setPlatformName("Android")
//                .setPlatformVersion("6.0.1")
//                .setApp(app.getAbsolutePath());
//
//        setupDeviceServer()
//                .setFullReset(false)
//                .setAutoLaunch(false);
//
//        setupAndroidDriver()
//                .setAppPackage("com.example.android.contactmanager")
//                .setAndroidDeviceReadyTimeout(60)
//                .setEnablePerformanceLogging(true);
//
//        d = initAndroidDriver(AppiumHub);
//
//    }
//
//    @Test
//    public void androidEmulatorNativeBrowserTest() throws Exception {
//
//        setupDevice()
//                .setDeviceType(AppiumDeviceTypesEnum.ANDROID_EMULATOR)
//                .setDeviceUDID("emulator-5554")
//                .setDeviceName("a1001")
//                .setPlatformName("Android");
//
//        setupDeviceServer()
//                .setNewCommandTimeout(60)
//                .setBrowserName("browser");
//
//        setupAndroidDriver()
//                .setAppPackage("com.android.browser.BrowserActivity")
//                .setAvdReadyTimeout(60)
//                .setEnablePerformanceLogging(true);
//
//        d = initAndroidDriver(AppiumHub);
//        d.get("https://www.bbc.co.uk");
//
//    }
}
