package com.atanas.kanchev.testframework.selenium.handlers;

import com.atanas.kanchev.testframework.core.context.AbstractContext;
import com.atanas.kanchev.testframework.selenium.context.WebContext;
import com.atanas.kanchev.testframework.selenium.driverfactory.DriverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Driver Interface
 *
 * @author Atanas Ksnchev
 */
class IDriver {

    // the logger
    Logger logger = LoggerFactory.getLogger(IDriver.class);

    public DriverFactory configureBrowser() {
        return new DriverFactory();
    }

    public void configureContext(DriverFactory driverFactory) {
        AbstractContext context = new WebContext();
        ((WebContext) context).setDriver(driverFactory.setupWebDriver().getChromeDriver().getDriver());
        context.addContext(context);
    }


}
