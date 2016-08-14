package com.atanas.kanchev.testframework.appium.context;

import com.atanas.kanchev.testframework.commons.context.AbstractContext;
import com.atanas.kanchev.testframework.selenium.context.SeleniumContext;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Appium Context
 *
 * @param <T> driver type, e.g. AndroidDriver, IOSDriver
 * @author Atanas Ksnchev
 */
public final class AppiumContext<T> extends SeleniumContext<T> {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(AppiumContext.class);

    public AppiumContext(T driver) {
        this(driver, "appiumCtx_");
    }

    public AppiumContext(T driver, String contextName) {
        super(driver, contextName);
    }

    @Override public void tearDownContext(AbstractContext context) {

        logger.debug("Tearing down context " + context.getContextName());

        if (context instanceof AppiumContext) {

            if (((AppiumContext<AndroidDriver>) context).getDriver() != null) {

                if (context.getDriver() instanceof AndroidDriver)
                    try {
                        ((AppiumContext<AndroidDriver>) context).getDriver().closeApp();
                        ((AppiumContext<AndroidDriver>) context).getDriver().quit();
                    } catch (Exception e) {
                        logger.error(e.getMessage());
                    }

                if (context.getDriver() instanceof IOSDriver)
                    ((AppiumContext<IOSDriver>) context).getDriver().quit();
            }
        }
    }

    @Override public String toString() {
        return getClass().getSimpleName();
    }

}
