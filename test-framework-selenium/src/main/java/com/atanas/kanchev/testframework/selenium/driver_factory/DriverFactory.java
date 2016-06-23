package com.atanas.kanchev.testframework.selenium.driver_factory;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static com.atanas.kanchev.testframework.selenium.driver_factory.Conf.JVMArgs.*;
import static com.atanas.kanchev.testframework.selenium.driver_factory.Conf.OPERATING_SYSTEM;
import static com.atanas.kanchev.testframework.selenium.driver_factory.Conf.SYSTEM_ARCHITECTURE;
import static com.atanas.kanchev.testframework.selenium.driver_factory.DriverType.valueOf;
import static org.openqa.selenium.Proxy.ProxyType.MANUAL;

public class DriverFactory {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);

    private WebDriver driver;
    private DriverType selectedDriverType;
    private Proxy proxy;

    public DriverFactory() {
        configureProxy();
        determineEffectiveDriverType();
    }

    public WebDriver getDriver() {
        return instantiateWebDriver(selectedDriverType.getDesiredCapabilities(proxy));
    }

    public void quitDriver() {
        if (null != driver) {
            driver.quit();
        }
    }

    private void configureProxy() {
        if (null == driver) {
            if (PROXY_ENABLED) {
                proxy = new Proxy();
                proxy.setProxyType(MANUAL);
                proxy.setHttpProxy(PROXY_DETAILS);
                proxy.setSslProxy(PROXY_DETAILS);
            }
        }
    }

    private void determineEffectiveDriverType() {
        DriverType driverType = Conf.DefaultProperties.DEFAULT_BROWSER;
        try {
            driverType = valueOf(BROWSER);
        } catch (IllegalArgumentException ignored) {
            logger.error("Unknown driver specified, defaulting to '" + driverType + "'...");
        } catch (NullPointerException ignored) {
            logger.error("No driver specified, defaulting to '" + driverType + "'...");
        }
        selectedDriverType = driverType;
    }

    private WebDriver instantiateWebDriver(DesiredCapabilities desiredCapabilities) {

        logger.debug("Current Operating System: " + OPERATING_SYSTEM);
        logger.debug("Current Architecture: " + SYSTEM_ARCHITECTURE);
        logger.debug("Current Browser Selection: " + selectedDriverType);

        if (USE_REMOTE_WEB_DRIVER) {
            RemoteDriverFactory remoteDriverFactory = new RemoteDriverFactory();
            driver = remoteDriverFactory.getRemoteWebDriver(desiredCapabilities);
//            logger.debug("Running on IP: " + remoteDriverFactory.getExecutionIP());
        } else {
            driver = selectedDriverType.getWebDriverObject(desiredCapabilities);
            logger.debug("Running on IP: " + getExecutionIP());
        }
        return driver;
    }

    /////////////////////
    // STATIC MEMBERS //
    ////////////////////

    /**
     * Get endpoint execution IP address
     *
     * @return String IP
     */
    public String getExecutionIP() {

        String ip = null;

        try {
            InetAddress IP = InetAddress.getLocalHost();
            ip = IP.getHostAddress();
        } catch (UnknownHostException e) {
            logger.error(e.getMessage());
        }

        return ip;
    }
}