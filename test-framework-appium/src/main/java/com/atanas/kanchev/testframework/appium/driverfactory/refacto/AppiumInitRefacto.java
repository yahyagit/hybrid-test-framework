package com.atanas.kanchev.testframework.appium.driverfactory.refacto;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by atanas on 21/07/16.
 */
@Deprecated
public class AppiumInitRefacto extends AppiumDriverFactory {

    private final static Logger logger = LoggerFactory.getLogger(AppiumInitRefacto.class);

    private AppiumDriverFactory appiumDriverFactory = new AppiumDriverFactory();

    /**
     * Create AndroidDriver
     * <p>
     * //     * @param appiumServerURL AppiumInit server URL String, e.g. "http://127.0.0.1:4723/wd/hub"
     *
     * @return AndroidDriver instance
     */
    public AndroidDriver<AndroidElement> initAndroidDriver() {
        return appiumDriverFactory.getAndroidDriver();
    }

    public AppiumDriverFactory getAppiumDriverFactory() {
        return appiumDriverFactory;
    }
}
