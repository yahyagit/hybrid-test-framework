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
 * @version 1.0
 */
public final class AppiumContext<T> extends SeleniumContext<T> {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(AppiumContext.class);

    /**
     * <p>Constructor for AppiumContext.</p>
     *
     * @param driver a T object.
     */
    public AppiumContext(T driver) {
        this(driver, "appiumCtx_");
    }

    /**
     * <p>Constructor for AppiumContext.</p>
     *
     * @param driver      a T object.
     * @param contextName a {@link java.lang.String} object.
     */
    public AppiumContext(T driver, String contextName) {
        super(driver, contextName);
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override public String toString() {
        return getClass().getSimpleName();
    }

}
