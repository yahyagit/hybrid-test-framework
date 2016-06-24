package com.atanas.kanchev.testframework.selenium.driver_factory;

import io.github.bonigarcia.wdm.*;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.MarionetteDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.openqa.selenium.remote.CapabilityType.PROXY;

public enum DriverType implements DriverSetup {

    FIREFOX {
        public DesiredCapabilities getDesiredCapabilities(Proxy proxySettings) {
            return addProxySettings(new DesiredCapsFactory().getDefaultFirefoxCaps(), proxySettings);
        }

        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            MarionetteDriverManager.getInstance().setup();
            return configure(new MarionetteDriver(capabilities));
        }
    },

    CHROME {
        public DesiredCapabilities getDesiredCapabilities(Proxy proxySettings) {
            return addProxySettings(new DesiredCapsFactory().getDefaultChromeCaps(), proxySettings);
        }

        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            ChromeDriverManager.getInstance().setup();
            return configure(new ChromeDriver(capabilities));
        }
    },

    IE {
        public DesiredCapabilities getDesiredCapabilities(Proxy proxySettings) {
            return addProxySettings(new DesiredCapsFactory().getDefaultIECaps(), proxySettings);
        }

        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            InternetExplorerDriverManager.getInstance().setup();
            return configure(new InternetExplorerDriver(capabilities));
        }
    },

    EDGE {
        public DesiredCapabilities getDesiredCapabilities(Proxy proxySettings) {
            return addProxySettings(new DesiredCapsFactory().getDefaultEdgeCaps(), proxySettings);
        }

        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            EdgeDriverManager.getInstance().setup();
            return configure(new EdgeDriver(capabilities));
        }
    },

    SAFARI {
        public DesiredCapabilities getDesiredCapabilities(Proxy proxySettings) {
            return addProxySettings(new DesiredCapsFactory().getDefaultSafariCaps(), proxySettings);
        }

        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            return configure(new SafariDriver(capabilities));
        }
    },

    OPERA {
        public DesiredCapabilities getDesiredCapabilities(Proxy proxySettings) {
            return addProxySettings(new DesiredCapsFactory().getDefaultOperaCaps(), proxySettings);
        }

        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            OperaDriverManager.getInstance().setup();
            return configure(new OperaDriver(capabilities));
        }
    },

    PHANTOMJS {
        public DesiredCapabilities getDesiredCapabilities(Proxy proxySettings) {
            PhantomJsDriverManager.getInstance().setup();
            return new DesiredCapsFactory().getDefaultPhantomJSCaps(proxySettings);
        }

        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            return configure(new PhantomJSDriver(capabilities));
        }
    };

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(DriverType.class);

    DesiredCapabilities addProxySettings(DesiredCapabilities capabilities, Proxy proxySettings) {
        if (null != proxySettings) {
            capabilities.setCapability(PROXY, proxySettings);
        }
        logger.debug("Proxy settings: " + proxySettings);
        return capabilities;
    }

    WebDriver configure(WebDriver driver) {
        return new DriverConfig(driver).getDriver();
    }

}

interface DriverSetup {

    WebDriver getWebDriverObject(DesiredCapabilities desiredCapabilities);

    DesiredCapabilities getDesiredCapabilities(Proxy proxySettings);
}