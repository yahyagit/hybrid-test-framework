package com.atanas.kanchev.testframework.selenium.driverfactory;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import static org.openqa.selenium.remote.CapabilityType.PROXY;

/**
 * Created by atanas on 24/03/2016.
 */
public enum BrowserConfig implements DriverSetup, IDesiredCapabilities, IWebDriver {

    FIREFOX {
        @Override
        public WebDriver getWebDriverObject(DesiredCapabilities desiredCapabilities) {

            return getFirefoxDriver(desiredCapabilities);
        }

        @Override
        public DesiredCapabilities getDesiredCapabilities(Proxy proxySettings) {
            DesiredCapabilities caps = getDefaultFirefoxCaps();
            return addProxySettings(caps, proxySettings);
        }

        @Override
        public DesiredCapabilities getDesiredCapabilities() {
            return getDefaultFirefoxCaps();
        }
    },

    CHROME {
        @Override
        public WebDriver getWebDriverObject(DesiredCapabilities desiredCapabilities) {
            return getChromeDriver(desiredCapabilities);
        }

        @Override
        public DesiredCapabilities getDesiredCapabilities(Proxy proxySettings) {
            return null;
        }

        @Override
        public DesiredCapabilities getDesiredCapabilities() {
            return getDefaultChromeCaps();
        }
    };

    protected DesiredCapabilities addProxySettings(DesiredCapabilities capabilities, Proxy proxySettings) {
        if (null != proxySettings) {
            capabilities.setCapability(PROXY, proxySettings);
        }

        return capabilities;
    }
}

interface DriverSetup {

    WebDriver getWebDriverObject(DesiredCapabilities desiredCapabilities);

    DesiredCapabilities getDesiredCapabilities(Proxy proxySettings);

    DesiredCapabilities getDesiredCapabilities();
}
