package com.atanas.kanchev.testframework.selenium.driverfactory;

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

/**
 * Desired capabilities interface
 * <p>
 * Configure instance of {@link  org.openqa.selenium.remote.DesiredCapabilities}
 * <p>
 * Supports Chrome, Firefox and Safari capabilities setupBrowser
 */
public interface IDesiredCapabilities {

    Logger logger = LoggerFactory.getLogger(IDesiredCapabilities.class);

    /**
     * Get default Chrome capabilities
     *
     * @return configured DesiredCapabilities instance
     */
    default DesiredCapabilities getDefaultChromeCaps() {

        DesiredCapabilities caps = DesiredCapabilities.chrome();
        caps.setCapability("chrome.switches", Arrays.asList("--no-default-browser-check"));
        HashMap<String, String> chromePreferences = new HashMap<>();
        chromePreferences.put("profile.password_manager_enabled", "false");
        caps.setCapability("chrome.prefs", chromePreferences);

        logger.debug("Configured default Chrome DesiredCapabilities: " + caps.toString());

        return caps;
    }

    /**
     * Get default Firefox capabilities
     *
     * @return configured DesiredCapabilities instance
     */
    default DesiredCapabilities getDefaultFirefoxCaps() {

        DesiredCapabilities caps = DesiredCapabilities.firefox();
        logger.debug("Configured default Firefox DesiredCapabilities: " + caps.toString());

        return caps;
    }

    /**
     * Get default Safari capabilities
     *
     * @return configured DesiredCapabilities instance
     */
    default DesiredCapabilities getDefaultSafariCaps() {

        DesiredCapabilities caps = DesiredCapabilities.safari();
        caps.setCapability("safari.cleanSession", true);

        logger.debug("Configured default Safari DesiredCapabilities: " + caps.toString());

        return caps;
    }

    /**
     * Merge capabilities
     *
     * @param sourceCaps instance of the source capabilities
     * @param newCaps    instance of the capabilities to be merged to the sourceCaps
     * @return DesiredCapabilities instance
     */
    default DesiredCapabilities mergeCapabilities(DesiredCapabilities sourceCaps, DesiredCapabilities newCaps) {

        logger.debug("Merging capabilities [NEW_CAPS]: " + sourceCaps + " into [SOURCE]: " + sourceCaps);

        return sourceCaps.merge(newCaps);
    }

    /**
     * Set custom ChromeOptions
     *
     * @param caps          DesiredCapabilities
     * @param chromeOptions ChromeOptions
     * @return DesiredCapabilities instance
     */
    default DesiredCapabilities setCustomChromeOptions(DesiredCapabilities caps, ChromeOptions chromeOptions) {

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
     * @param caps          DesiredCapabilities
     * @param chromeOptions Map chromeOptions
     * @return DesiredCapabilities instance
     */
    default DesiredCapabilities setCustomChromeOptions(DesiredCapabilities caps, Map<String, Object> chromeOptions) {

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
     * @param caps           DesiredCapabilities
     * @param firefoxProfile FirefoxProfile
     * @return DesiredCapabilities instance
     */
    default DesiredCapabilities setCustomFirefoxProfile(DesiredCapabilities caps, FirefoxProfile firefoxProfile) {

        if (firefoxProfile != null) {
            if (caps == null)
                caps = DesiredCapabilities.firefox();
            caps.setCapability(FirefoxDriver.PROFILE, firefoxProfile);
        } else
            throw new IllegalArgumentException("Null FirefoxProfile argument");

        return caps;
    }
}
