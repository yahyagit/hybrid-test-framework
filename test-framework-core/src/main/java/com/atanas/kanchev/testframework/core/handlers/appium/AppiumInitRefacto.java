package com.atanas.kanchev.testframework.core.handlers.appium;

import com.atanas.kanchev.testframework.appium.driverfactory.refacto.AppiumDriverFactory;
import com.atanas.kanchev.testframework.core.context.AppiumContext;
import com.atanas.kanchev.testframework.core.handlers.wrappers.IContext;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by atanas on 21/07/16.
 */
public class AppiumInitRefacto implements IContext {

    private final static Logger logger = LoggerFactory.getLogger(AppiumInitRefacto.class);

    private AppiumDriverFactory appiumDriverFactory = new AppiumDriverFactory();

    /**
     * Create AndroidDriver
     * <p>
     * //     * @param appiumServerURL AppiumInit server URL String, e.g. "http://127.0.0.1:4723/wd/hub"
     *
     * @return AndroidDriver instance
     */
    public void initAndroidDriver() {

        AppiumContext<AndroidDriver<AndroidElement>> context = new AppiumContext<>(appiumDriverFactory.getAndroidDriver());
        context().addContext(context);

    }

    public AppiumDriverFactory getAppiumDriverFactory() {
        return appiumDriverFactory;
    }
}
