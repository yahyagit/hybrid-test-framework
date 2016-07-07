package com.atanas.kanchev.testframework.core.context;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.Connection;
import io.appium.java_client.ios.IOSDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Appium Context
 *
 * @param <T>
 * @author Atanas Ksnchev
 */
public final class AppiumContext<T> extends SeleniumContext<T> {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(AppiumContext.class);

    public AppiumContext(T driver) {
        this(driver, "appiumContext_");
    }

    public AppiumContext(T driver, String contextName) {
        super(driver, contextName);
    }

    @Override
    public void tearDownContext(AbstractContext context) {

        if (context instanceof AppiumContext) {

            if (((AppiumContext<AndroidDriver>) context).getDriver() != null) {

                ((AppiumContext<AndroidDriver>) context).getDriver().setConnection(Connection.DATA);

                if (context.getDriver() instanceof AndroidDriver)
                    ((AppiumContext<AndroidDriver>) context).getDriver().quit();

                if (context.getDriver() instanceof IOSDriver)
                    ((AppiumContext<IOSDriver>) context).getDriver().quit();
            }
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

}