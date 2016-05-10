package com.atanas.kanchev.testframework.selenium.handlers;

import com.atanas.kanchev.testframework.core.context.AbstractContext;
import com.atanas.kanchev.testframework.selenium.context.WebContext;
import com.atanas.kanchev.testframework.selenium.driverfactory.Browsers;
import com.atanas.kanchev.testframework.selenium.driverfactory.DriverFactory;
import com.atanas.kanchev.testframework.selenium.driverfactory.JVMArgsFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DriverConfig
 *
 * @author Atanas Ksnchev
 */
class DriverConfig {

    // the logger
    Logger logger = LoggerFactory.getLogger(DriverConfig.class);

    private Browsers selectedBrowser;

    public DriverConfig() {
        this.selectedBrowser = JVMArgsFactory.getBrowserType();
    }

    public DriverFactory configureBrowser() {
        return new DriverFactory(selectedBrowser);
    }

    public void configureContext(DriverFactory driverFactory) {
        AbstractContext context = new WebContext();
        ((WebContext) context).setDriver(driverFactory.setupWebDriver().getChromeDriver().getDriver());
        context.addContext(context);
    }


}
