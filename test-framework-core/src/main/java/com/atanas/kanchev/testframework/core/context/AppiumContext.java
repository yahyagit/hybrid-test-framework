package com.atanas.kanchev.testframework.core.context;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Appium Context
 *
 * @param <T>
 * @author Atanas Ksnchev
 */
public final class AppiumContext<T> extends WebContext<T> {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(AppiumContext.class);

    public AppiumContext(T driver) {
        super(driver, "appiumContext_");
    }

    @Override
    public void tearDownContext(AbstractContext context) {
        if (context instanceof AppiumContext) {
            if (context.getDriver() instanceof AndroidDriver)
                ((AppiumContext<AndroidDriver>) context).getDriver().quit();
            if (context.getDriver() instanceof IOSDriver)
                ((AppiumContext<IOSDriver>) context).getDriver().quit();
        }
    }

    @Override
    public void tearDownContexts() {
        for (AbstractContext context : ContextFactory.getContextMap().values()) {
            tearDownContext(context);
            removeContext(context);
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

}