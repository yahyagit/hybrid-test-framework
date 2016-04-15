package com.atanas.kanchev.testframework.selenium.driverfactory;

import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

import static org.openqa.selenium.remote.CapabilityType.PROXY;

/**
 * Created by atanas on 24/03/2016.
 */
public enum BrowserConfig implements IWebDriverSetup, IRemoteWebDriverSetup, IDesiredCapabilities, IWebDriver {

    FIREFOX {
        @Override
        public DesiredCapabilities setRemoteBrowserCaps(final String version, final Platform platform, DesiredCapabilities caps) {
            caps.setBrowserName("firefox");
            caps.setVersion(version);
            return caps;
        }

        @Override
        public RemoteWebDriver getRemoteWebDriverObject(final URL remoteAddress, final String version, final Platform platform) {
            DesiredCapabilities defCaps = getDefaultFirefoxCaps();
            DesiredCapabilities capabilities =
                    mergeCapabilities(defCaps, setRemoteBrowserCaps(version, platform, defCaps));
            return new RemoteWebDriver(remoteAddress, capabilities);
        }

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
        public DesiredCapabilities setRemoteBrowserCaps(final String version, final Platform platform, final DesiredCapabilities caps) {
            caps.setBrowserName("chrome");
            caps.setVersion(version);
            caps.setPlatform(platform);
            return caps;
        }

        @Override
        public RemoteWebDriver getRemoteWebDriverObject(final URL remoteAddress, final String version, final Platform platform) {
            DesiredCapabilities capabilities =
                    mergeCapabilities(getDefaultChromeCaps(), setRemoteBrowserCaps(version, platform, getDefaultChromeCaps()));
            return new RemoteWebDriver(remoteAddress, capabilities);
        }

        @Override
        public WebDriver getWebDriverObject(DesiredCapabilities desiredCapabilities) {
            return getChromeDriver(desiredCapabilities);
        }

        @Override
        public DesiredCapabilities getDesiredCapabilities(Proxy proxySettings) {
            DesiredCapabilities caps = getDefaultChromeCaps();
            return addProxySettings(caps, proxySettings);
        }

        @Override
        public DesiredCapabilities getDesiredCapabilities() {
            return getDefaultChromeCaps();
        }
    };

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(BrowserConfig.class);

    protected DesiredCapabilities addProxySettings(DesiredCapabilities capabilities, Proxy proxySettings) {
        if (null != proxySettings) {
            capabilities.setCapability(PROXY, proxySettings);
        }

        return capabilities;
    }

}

interface IWebDriverSetup {

    WebDriver getWebDriverObject(DesiredCapabilities desiredCapabilities);

    DesiredCapabilities getDesiredCapabilities(Proxy proxySettings);

    DesiredCapabilities getDesiredCapabilities();
}

interface IRemoteWebDriverSetup {

    /**
     * * Set {@link org.openqa.selenium.remote.DesiredCapabilities#setBrowserName(java.lang.String)}
     * "ANY" if no value for the runtime J
     * Set {@link org.openqa.selenium.remote.DesiredCapabilities#setVersion(java.lang.String)}
     * "ANY" if no value for the runtime JVM arg -DbrowserVersion has been supplied.
     *
     * @param version String remote browser version
     * @param caps    DesiredCapabilities
     * @return DesiredCapabilities instance
     */
    DesiredCapabilities setRemoteBrowserCaps(final String version, final Platform platform, final DesiredCapabilities caps);

    /**
     * Create RemoteWebDriver instance
     *
     * @param remoteAddress Hub URL Address
     * @return RemoteWebDriver instance
     */
    RemoteWebDriver getRemoteWebDriverObject(final URL remoteAddress, final String version, final Platform platform);
}