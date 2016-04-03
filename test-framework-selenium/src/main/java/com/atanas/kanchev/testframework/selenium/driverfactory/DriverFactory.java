package com.atanas.kanchev.testframework.selenium.driverfactory;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.atanas.kanchev.testframework.selenium.driverfactory.IRootDriver.DEFAULT_IMPLICITLY_WAIT;
import static com.atanas.kanchev.testframework.selenium.driverfactory.IRootDriver.DEFAULT_PAGE_TIMEOUT;

/**
 * Driver Factory
 */
public final class DriverFactory implements IWebDriver, IDesiredCapabilities {

    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);

    public void setSelectedBrowser(BrowserConfig selectedBrowser) {
        this.selectedBrowser = selectedBrowser;
    }

    private BrowserConfig selectedBrowser;

    public DriverFactory(BrowserConfig selectedBrowser) {
        this.selectedBrowser = selectedBrowser;
    }

    public DriverFactory() {
        selectedBrowser = JVMArgsFactory.getBrowserType();
    }

    public WebDriver getDriver() {
        return configureTimeouts(selectedBrowser.getWebDriverObject(selectedBrowser.getDesiredCapabilities()), DEFAULT_IMPLICITLY_WAIT, DEFAULT_PAGE_TIMEOUT);
    }


    //////////////
    // SETTERS //
    /////////////

//    public void setSessionName(String name) {
//        logger.debug("Setting session name to: " + name);
//        sessionName = name;
//    }

//    public WebDriver getWebDriverDriver() {
//        return driverThread.get().getWebDriverDriver();
//    }

//    private class WebDriverThread {
//
//        private final BrowserConfig selectedBrowser;
//
//        WebDriverThread(BrowserConfig selectedBrowser) {
//            this.selectedBrowser = selectedBrowser;
//        }
//
//        WebDriverThread() {
//            selectedBrowser = JVMArgsFactory.getBrowserType();
//        }
//
//        WebDriver getWebDriverDriver() {
//
//            DesiredCapabilities desiredCapabilities = selectedBrowser.getDesiredCapabilities();
//
//            return selectedBrowser.getWebDriverObject(desiredCapabilities);
//        }
//
//    }
}



