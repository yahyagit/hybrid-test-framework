package com.atanas.kanchev.testframework.selenium.driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by atanas on 24/03/2016.
 */
public class DriverFactory implements IWebDriver, IDesiredCapabilities {

    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);

    public void setSelectedBrowser(BrowserConfig selectedBrowser) {
        this.selectedBrowser = selectedBrowser;
    }

    private BrowserConfig selectedBrowser;

    public DriverFactory(BrowserConfig selectedBrowser) {
        this.selectedBrowser = selectedBrowser;
    }

    public DriverFactory() {
        selectedBrowser = JVMArgs.getBrowserType();
    }

    //    private static Map<String, WebDriverThread> driverThreadPool = Collections.synchronizedMap(new LinkedHashMap<>());
//    private static ThreadLocal<WebDriverThread> driverThread = new ThreadLocal<WebDriverThread>() {
//        @Override
//        protected WebDriverThread initialValue() {
//
//            WebDriverThread webDriverThread = new WebDriverThread();
//
//            if (sessionName != null)
//                driverThreadPool.put(sessionName, webDriverThread);
//            else
//                driverThreadPool.put("default", webDriverThread);
//            logger.debug("INITIAL VALUE!");
//            return webDriverThread;
//        }
//    };

//    private static String sessionName;

    public WebDriver getDriver() {

        DesiredCapabilities desiredCapabilities = selectedBrowser.getDesiredCapabilities();

        return selectedBrowser.getWebDriverObject(desiredCapabilities);
    }


    //////////////
    // SETTERS //
    /////////////

//    public void setSessionName(String name) {
//        logger.debug("Setting session name to: " + name);
//        sessionName = name;
//    }

//    public WebDriver getDriver() {
//        return driverThread.get().getDriver();
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
//            selectedBrowser = JVMArgs.getBrowserType();
//        }
//
//        WebDriver getDriver() {
//
//            DesiredCapabilities desiredCapabilities = selectedBrowser.getDesiredCapabilities();
//
//            return selectedBrowser.getWebDriverObject(desiredCapabilities);
//        }
//
//    }
}



