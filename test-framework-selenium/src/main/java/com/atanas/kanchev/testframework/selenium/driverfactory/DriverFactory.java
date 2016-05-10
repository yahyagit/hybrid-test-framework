package com.atanas.kanchev.testframework.selenium.driverfactory;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DriverConfig Factory
 */
public final class DriverFactory {

    //the logger
    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);

    private RemoteWebDriverFactory remoteWebDriverFactory;
    private WebDriverFactory webDriverFactory;
    private DesiredCapabilitiesFactory desiredCapabilitiesFactory;
    private Browsers selectedBrowser;

    public DriverFactory(Browsers selectedBrowser) {
        this.desiredCapabilitiesFactory = new DesiredCapabilitiesFactory();
        this.remoteWebDriverFactory = new RemoteWebDriverFactory();
        this.webDriverFactory = new WebDriverFactory(this.desiredCapabilitiesFactory);
        this.selectedBrowser = selectedBrowser;
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
                return setupWebDriver().getChromeDriver().getDriver();

            case FIREFOX:
                return setupWebDriver().getFirefoxDriver().getDriver();

            case SAFARI:
                return setupWebDriver().getSafariDriver().getDriver();

        }

        return null;
    }


}


