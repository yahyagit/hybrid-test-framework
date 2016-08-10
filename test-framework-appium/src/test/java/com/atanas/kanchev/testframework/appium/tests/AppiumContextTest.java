package com.atanas.kanchev.testframework.appium.tests;

import com.atanas.kanchev.testframework.appium.context.AppiumContext;
import com.atanas.kanchev.testframework.appium.wrappers.IAppium;
import com.atanas.kanchev.testframework.commons.wrappers.IContext;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Test;

/**
 * Created by atanas on 10/08/16.
 */
public class AppiumContextTest extends BaseTest implements IContext, IAppium {

    @Test
    public void tearDownContext() throws Exception {

        AppiumContext<AndroidDriver> appiumContext = new AppiumContext<>(APPIUM_DRIVER_FACTORY.getAndroidDriver());
        context().addContext(appiumContext);
        context().tearDownContext(appiumContext);
    }

}