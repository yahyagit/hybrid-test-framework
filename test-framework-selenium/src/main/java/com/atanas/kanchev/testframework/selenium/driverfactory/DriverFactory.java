package com.atanas.kanchev.testframework.selenium.driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by atanas on 24/03/2016.
 */
public class DriverFactory implements IWebDriver, IDesiredCapabilities {

    private static final Logger logger = LoggerFactory.getLogger(IWebDriver.class);

    private static Map<String, WebDriverThread> driverThreadPool = Collections.synchronizedMap(new LinkedHashMap<>());
    private static ThreadLocal<WebDriverThread> driverThread = new ThreadLocal<WebDriverThread>() {
        @Override
        protected WebDriverThread initialValue() {

            WebDriverThread webDriverThread = new WebDriverThread();

            if (sessionName != null)
                driverThreadPool.put(sessionName, webDriverThread);
            else
                driverThreadPool.put("default", webDriverThread);
            logger.debug("INITIAL VALUE!");
            return webDriverThread;
        }
    };

    private static String sessionName;

    //////////////
    // SETTERS //
    /////////////

    public void setSessionName(String name) {
        logger.debug("Setting session name to: " + name);
        sessionName = name;
    }

    public WebDriver getDriver() {
        return driverThread.get().getDriver();
    }
}

class WebDriverThread {

    private BrowserConfig selectedBrowser;

    public WebDriver getDriver() {
        selectedBrowser = JVMArgs.getBrowserType();
        DesiredCapabilities desiredCapabilities = selectedBrowser.getDesiredCapabilities();
        return selectedBrowser.getWebDriverObject(desiredCapabilities);
    }

}

