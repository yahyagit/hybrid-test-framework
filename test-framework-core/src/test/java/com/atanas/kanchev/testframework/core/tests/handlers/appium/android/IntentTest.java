package com.atanas.kanchev.testframework.core.tests.handlers.appium.android;

import com.atanas.kanchev.testframework.appium.driverfactory.AppiumDeviceTypesEnum;
import com.atanas.kanchev.testframework.core.handlers.wrappers.IAppium;
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
        File appDir = new File("src\\test\\java\\com\\atanas\\kanchev\\testframework\\core\\tests\\handlers\\appium\\android");
        File app = new File(appDir, "IntentExample.apk");

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

    @Test
    public void startActivityWithIntent() {
        appium().methods().startActivity().startActivity("com.android.mms", ".ui.ComposeMessageActivity", null, null,
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
        appium().methods().startActivity().startActivity("com.prgguru.android", ".GreetingActivity", null, null,
                "android.intent.action.MAIN", "android.intent.category.DEFAULT", "0x4000000",
                "--es \"USERNAME\" \"AppiumIntentTest\" -t \"text/plain\"");
        assertEquals(appium().methods().find().findElementById("com.prgguru.android:id/textView1").getText(),
                "Welcome AppiumIntentTest");
    }
}
