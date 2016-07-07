package com.atanas.kanchev.testframework.core.tests.handlers.appium.android.native_app_tests;

import com.atanas.kanchev.testframework.appium.driverfactory.AppiumDeviceTypesEnum;
import com.atanas.kanchev.testframework.core.handlers.wrappers.IAppium;
import com.atanas.kanchev.testframework.core.handlers.wrappers.IContext;
import io.appium.java_client.NoSuchContextException;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class AndroidContextTest implements IAppium, IContext{

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

    @Before
    public void setUp() throws Exception {
        appium().methods().startActivity().startActivity("io.appium.android.apis", ".view.WebView1");
        Thread.sleep(20000);

    }

    @AfterClass
    public static void tearDown() throws Exception {
        DEVICE_BASED_HANDLER.stopAppiumServer();
        CONTEXT_FACTORY.tearDownContexts();

    }

    @Test
    public void testGetContext() {
        assertEquals("NATIVE_APP", appium().methods().contextAware().getContext());
    }

    @Test
    public void testGetContextHandles() {
        assertEquals(appium().methods().contextAware().getContextHandles().size(), 2);
    }

    @Test
    public void testSwitchContext() {
        appium().methods().contextAware().getContextHandles();
        appium().methods().contextAware().context("WEBVIEW_io.appium.android.apis");
        assertEquals(appium().methods().contextAware().getContext(), "WEBVIEW_io.appium.android.apis");
        appium().methods().contextAware().context("NATIVE_APP");
    }

    @Test(expected = NoSuchContextException.class) public void testContextError() {
        appium().methods().contextAware().context("Planet of the Ape-ium");
    }

}
