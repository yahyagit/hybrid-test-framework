package com.atanas.kanchev.testframework.core.tests.handlers.appium.android.native_app_tests;

import com.atanas.kanchev.testframework.appium.wrappers.IAppium;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Test;

import java.io.File;

/**
 * Created by atanas on 13/07/2016.
 */
public class InitRefactoTest implements IAppium {

    @Test
    public void name() throws Exception {

        File appDir = new File("src/test/java/com/atanas/kanchev/testframework/core/tests/handlers/appium/android/native_app_tests");
        File app = new File(appDir, "ApiDemos-debug.apk");

        appiumInit()
                .buildDefaultService()
                .startServer();

        appiumInit()
                .setCap(MobileCapabilityType.APP, app.getAbsolutePath())
                .setCap(MobileCapabilityType.DEVICE_NAME, "8adea98f")
                .setCap(MobileCapabilityType.PLATFORM_VERSION, "6.0.1")

                .setCap(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 10)
                .setCap(MobileCapabilityType.FULL_RESET, false)
                .setCap(AndroidMobileCapabilityType.ANDROID_DEVICE_READY_TIMEOUT, 10)
                .setCap(AndroidMobileCapabilityType.ENABLE_PERFORMANCE_LOGGING, true)
                .getAndroidDriver();

    }
}
