package com.atanas.kanchev.testframework.selenium.handlers;

import com.atanas.kanchev.testframework.selenium.context.AbstractContext;
import com.atanas.kanchev.testframework.selenium.context.WebContext;
import com.atanas.kanchev.testframework.selenium.driverfactory.DriverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.atanas.kanchev.testframework.selenium.handlers.Locator.getWebContext;

/**
 * Driver Interface
 *
 * @author Atanas Ksnchev
 */
interface IDriver {

    // the logger
    Logger logger = LoggerFactory.getLogger(IDriver.class);

    default DriverFactory getDriverFactory() {
        return new DriverFactory();
    }

    default void configureContext(DriverFactory driverFactory) {
        AbstractContext context = new WebContext();
        ((WebContext) context).setDriver(driverFactory.getDriver());
        context.addContext(context);
    }

    default IDriver setImplicitlyWait(long wait) {
        getWebContext().setImplicitlyWait(wait);
        return this;
    }

    default IDriver setPageLoadTimeout(long timeout) {
        getWebContext().setPageLoadTimeout(timeout);
        return this;
    }


}
