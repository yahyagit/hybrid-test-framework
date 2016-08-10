package com.atanas.kanchev.testframework.core.tests.handlers.appium.android.native_app_tests;

import com.atanas.kanchev.testframework.appium.wrappers.IAppium;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class IntentTest implements IAppium {

    /**
     * initialization.
     */
    @BeforeClass
    public static void beforeClass() throws Exception {
        File appDir = new File("src\\test\\java\\com\\atanas\\kanchev\\testframework\\core\\tests\\handlers\\APPIUM_INIT\\android");
        File app = new File(appDir, "IntentExample.apk");

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
//        APPIUM_INIT.stopAppiumServer();

    }

    @Test
    public void startActivityWithIntent() {
        android().activity().startActivity("com.android.mms", ".ui.ComposeMessageActivity", null, null,
                "android.intent.action.SEND", "android.intent.category.DEFAULT", "0x4000000",
                "-d \"TestIntent\" -t \"text/plain\"");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void startActivityWithDefaultIntentAndDefaultCategoryWithOptionalArgs() {
        android().activity().startActivity("com.prgguru.android", ".GreetingActivity", null, null,
                "android.intent.action.MAIN", "android.intent.category.DEFAULT", "0x4000000",
                "--es \"USERNAME\" \"AppiumIntentTest\" -t \"text/plain\"");
        assertEquals(android().find().findElementById("com.prgguru.android:id/textView1").getText(),
                "Welcome AppiumIntentTest");
    }
}
