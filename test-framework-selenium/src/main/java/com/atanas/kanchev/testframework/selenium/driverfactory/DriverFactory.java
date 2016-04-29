package com.atanas.kanchev.testframework.selenium.driverfactory;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Driver Factory
 */
public final class DriverFactory {

    //the logger
    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);

    private BrowserConfig selectedBrowser;

    public DriverFactory(BrowserConfig selectedBrowser) {
        this.selectedBrowser = selectedBrowser;
    }

    public DriverFactory() {
        selectedBrowser = JVMArgsFactory.getBrowserType();
    }

    public WebDriver getWebDriverDriver() {
        return selectedBrowser.getWebDriverObject(selectedBrowser.getDesiredCapabilities());
    }

    public RemoteWebDriver getRemoteWebDriver() {
        URL url = null;
        try {
            url = new URL("http://10.1.29.8:4444//wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return (RemoteWebDriver) selectedBrowser.getRemoteWebDriverObject(url, "46", Platform.WINDOWS);
    }

    public DriverFactory setSelectedBrowser(BrowserConfig selectedBrowser) {
        this.selectedBrowser = selectedBrowser;
        return this;
    }

    public BrowserConfig conf() {
        return this.selectedBrowser;
    }

}



