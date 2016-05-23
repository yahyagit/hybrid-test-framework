package com.atanas.kanchev.testframework.selenium.driverfactory;

import com.atanas.kanchev.testframework.core.context.AbstractContext;
import com.atanas.kanchev.testframework.core.context.ContextFactory;
import com.atanas.kanchev.testframework.selenium.context.WebContext;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DriverConfig Factory
 */
public final class DriverFactory {

    //the logger
    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);

    private final RemoteWebDriverFactory remoteWebDriverFactory;
    private final WebDriverFactory webDriverFactory;
    private final DesiredCapabilitiesFactory desiredCapabilitiesFactory;
    private final Browsers selectedBrowser;

    public DriverFactory(Browsers selectedBrowser) {
        this.desiredCapabilitiesFactory = new DesiredCapabilitiesFactory();
        this.remoteWebDriverFactory = new RemoteWebDriverFactory();
        this.webDriverFactory = new WebDriverFactory(this.desiredCapabilitiesFactory);
        if (selectedBrowser == null) this.selectedBrowser = JVMArgsFactory.getBrowserType();
        else this.selectedBrowser = selectedBrowser;
    }

    public WebDriverFactory setupWebDriver() {
        return webDriverFactory;
    }

    public RemoteWebDriverFactory setupRemoteWebDriver() {
        return remoteWebDriverFactory;
    }

    public DesiredCapabilitiesFactory setupDesiredCapabilities() {
        return desiredCapabilitiesFactory;
    }

    public WebDriver getDriver() {

        switch (selectedBrowser) {

            case CHROME:
                return configureContext(setupWebDriver().getChromeDriver().getDriver());

            case FIREFOX:
                return configureContext(setupWebDriver().getFirefoxDriver().getDriver());

            case SAFARI:
                return configureContext(setupWebDriver().getSafariDriver().getDriver());

        }

        return null;
    }

    private WebDriver configureContext(WebDriver driver) {
        AbstractContext<WebDriver> context = new WebContext();
        context.setDriver(driver);
        ContextFactory.addContext(context);
        return driver;
    }


}


