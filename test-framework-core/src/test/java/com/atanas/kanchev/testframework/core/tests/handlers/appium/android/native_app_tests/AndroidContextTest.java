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

public class AndroidContextTest implements IAppium, IContext {

    /**
     * initialization.
     */
    @BeforeClass
    public static void beforeClass() throws Exception {
        File appDir = new File("src\\test\\java\\com\\atanas\\kanchev\\testframework\\core\\tests\\handlers\\APPIUM_INIT\\android");
        File app = new File(appDir, "IntentExample.apk");

        APPIUM_INIT
                .setupDevice()
                .setApp(app.getAbsolutePath())
                .setDeviceType(AppiumDeviceTypesEnum.ANDROID_DEVICE)
                .setDeviceName("ZY22398GL7")
                .setPlatformVersion("6.0.1");

        APPIUM_INIT
                .setupDeviceServer()
                .setNewCommandTimeout(10)
                .setFullReset(false)
                .setAutoLaunch(false);

        APPIUM_INIT
                .setupAndroidDriver()
                .setAndroidDeviceReadyTimeout(10)
                .setEnablePerformanceLogging(true);

        APPIUM_INIT
                .startAppiumServer();

        APPIUM_INIT
                .initAndroidDriver("http://127.0.0.1:4723/wd/hub");
    }

    @Before
    public void setUp() throws Exception {
        android()
                .activity()
                .startActivity("io.APPIUM_INIT.android.apis", ".view.WebView1");
        Thread.sleep(20000);

    }

    @AfterClass
    public static void tearDown() throws Exception {
        APPIUM_INIT.stopAppiumServer();
        CONTEXT_FACTORY.tearDownContexts();

    }

    @Test
    public void testGetContext() {
        assertEquals("NATIVE_APP", android()
                .contextAware()
                .getContext());
    }

    @Test
    public void testGetContextHandles() {
        assertEquals(android()
                .contextAware()
                .getContextHandles().size(), 2);
    }

    @Test
    public void testSwitchContext() {
        android()
                .contextAware()
                .getContextHandles();
        android()
                .contextAware()
                .context("WEBVIEW_io.APPIUM_INIT.android.apis");
        assertEquals(android()
                .contextAware()
                .getContext(), "WEBVIEW_io.APPIUM_INIT.android.apis");
        android()
                .contextAware()
                .context("NATIVE_APP");
    }

    @Test(expected = NoSuchContextException.class)
    public void testContextError() {
        android()
                .contextAware()
                .context("Planet of the Ape-ium");
    }

}
