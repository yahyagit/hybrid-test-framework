package com.atanas.kanchev.testframework.selenium.driverfactory;

import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.openqa.selenium.remote.CapabilityType.PROXY;

/**
 * Desired capabilities factory
 * <p>
 * Configure instance of {@link  org.openqa.selenium.remote.DesiredCapabilities}
 * <p>
 * Supports Chrome, Firefox and Safari capabilities setupBrowser
 */
public class DesiredCapabilitiesFactory {

    private static final Logger logger = LoggerFactory.getLogger(DesiredCapabilitiesFactory.class);

    private DesiredCapabilities caps;

    /**
     * Get public Chrome capabilities
     *
     * @return configured DesiredCapabilities instance
     */
    public DesiredCapabilities getDefaultChromeCaps() {

        caps = DesiredCapabilities.chrome();
        caps.setCapability("chrome.switches", Arrays.asList("--no-default-browser-check"));
        HashMap<String, String> chromePreferences = new HashMap<>();
        chromePreferences.put("profile.password_manager_enabled", "false");
        caps.setCapability("chrome.prefs", chromePreferences);

        logger.debug("Configured public Chrome DesiredCapabilities: " + caps.toString());

        return caps;
    }

    /**
     * Get public Firefox capabilities
     *
     * @return configured DesiredCapabilities instance
     */
    public DesiredCapabilities getDefaultFirefoxCaps() {

        caps = DesiredCapabilities.firefox();
        logger.debug("Configured public Firefox DesiredCapabilities: " + caps.toString());

        return caps;
    }

    /**
     * Get public Safari capabilities
     *
     * @return configured DesiredCapabilities instance
     */
    public DesiredCapabilities getDefaultSafariCaps() {

        caps = DesiredCapabilities.safari();
        caps.setCapability("safari.cleanSession", true);

        logger.debug("Configured public Safari DesiredCapabilities: " + caps.toString());

        return caps;
    }

    /**
     * Merge capabilities
     *
     * @param sourceCaps instance of the source capabilities
     * @param newCaps    instance of the capabilities to be merged to the sourceCaps
     * @return DesiredCapabilities instance
     */
    public DesiredCapabilities mergeCapabilities(DesiredCapabilities sourceCaps, DesiredCapabilities newCaps) {

        logger.debug("Merging capabilities [NEW_CAPS]: " + sourceCaps + " into [SOURCE]: " + sourceCaps);

        return caps = sourceCaps.merge(newCaps);
    }

    /**
     * Merge capabilities
     *
     * @param newCaps instance of the capabilities to be merged to the {@link DesiredCapabilitiesFactory#caps}
     * @return DesiredCapabilities instance
     */
    public DesiredCapabilities mergeCapabilities(DesiredCapabilities newCaps) {

        logger.debug("Merging capabilities [NEW_CAPS]: " + newCaps + " into [SOURCE]: " + caps);

        return caps = caps.merge(newCaps);
    }

    /**
     * Set custom ChromeOptions
     *
     * @param chromeOptions ChromeOptions
     * @return DesiredCapabilities instance
     */
    public DesiredCapabilities setCustomChromeOptions(ChromeOptions chromeOptions) {

        if (chromeOptions != null) {
            try {
                if (caps == null)
                    caps = DesiredCapabilities.chrome();
                caps.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                logger.debug("Setting custom Chrome Options: " + chromeOptions.toJson());
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        } else
            throw new IllegalArgumentException("Null ChromeOptions argument");

        return caps;
    }

    /**
     * Set custom ChromeOptions
     *
     * @param chromeOptions Map chromeOptions
     * @return DesiredCapabilities instance
     */
    public DesiredCapabilities setCustomChromeOptions(Map<String, Object> chromeOptions) {

        if (chromeOptions != null) {
            if (caps == null)
                caps = DesiredCapabilities.chrome();
            caps.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
            logger.debug("Setting custom Chrome Options: " + chromeOptions.entrySet());
        } else
            throw new IllegalArgumentException("Null ChromeOptions argument");

        return caps;
    }

    /**
     * Set custom FirefoxProfile
     *
     * @param firefoxProfile FirefoxProfile
     * @return DesiredCapabilities instance
     */
    public DesiredCapabilities setCustomFirefoxProfile(FirefoxProfile firefoxProfile) {

        if (firefoxProfile != null) {
            if (caps == null)
                caps = DesiredCapabilities.firefox();
            caps.setCapability(FirefoxDriver.PROFILE, firefoxProfile);
        } else
            throw new IllegalArgumentException("Null FirefoxProfile argument");

        return caps;
    }

    public DesiredCapabilities addProxySettings(Proxy proxySettings) {
        if (null != proxySettings) {
            caps.setCapability(PROXY, proxySettings);
        }

        return caps;
    }

    /**
     * * Set {@link org.openqa.selenium.remote.DesiredCapabilities#setBrowserName(java.lang.String)}
     * "ANY" if no value for the runtime J
     * Set {@link org.openqa.selenium.remote.DesiredCapabilities#setVersion(java.lang.String)}
     * "ANY" if no value for the runtime JVM arg -DbrowserVersion has been supplied.
     *
     * @param version  String remote browser version
     * @param browsers Browsers
     * @return DesiredCapabilities instance
     */
    public DesiredCapabilities setRemoteBrowserCaps(String version, Platform platform, Browsers browsers) {

        switch (browsers) {
            case FIREFOX:
                caps.setBrowserName("firefox");
                break;
            case CHROME:
                caps.setBrowserName("chrome");
                break;
        }
        caps.setPlatform(platform);
        caps.setVersion(version);
        return caps;
    }

}
