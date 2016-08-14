package com.atanas.kanchev.testframework.selenium.driverfactory;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

/**
 * @author Atanas Ksnchev
 */
public class DesiredCapsFactory {

    private static final Logger logger = LoggerFactory.getLogger(DesiredCapsFactory.class);
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

        logger.debug("Configured default Chrome DesiredCapabilities: " + caps.toString());

        return caps;
    }

    /**
     * Get public Firefox capabilities
     *
     * @return configured DesiredCapabilities instance
     */
    public DesiredCapabilities getDefaultFirefoxCaps() {

        caps = DesiredCapabilities.firefox();
        logger.debug("Configured default Firefox DesiredCapabilities: " + caps.toString());

        return caps;
    }

    public DesiredCapabilities getDefaultIECaps() {

        caps = DesiredCapabilities.internetExplorer();
        caps.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
        caps.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
        caps.setCapability("requireWindowFocus", true);

        logger.debug("Configured default IE DesiredCapabilities: " + caps.toString());

        return caps;
    }

    public DesiredCapabilities getDefaultEdgeCaps() {

        caps = DesiredCapabilities.edge();
        logger.debug("Configured default Edge DesiredCapabilities: " + caps.toString());

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

        logger.debug("Configured default Safari DesiredCapabilities: " + caps.toString());

        return caps;
    }

    public DesiredCapabilities getDefaultOperaCaps() {
        caps = DesiredCapabilities.operaBlink();
        logger.debug("Configured default Opera DesiredCapabilities: " + caps.toString());
        return caps;
    }

    public DesiredCapabilities getDefaultPhantomJSCaps(Proxy proxySettings) {
        caps = DesiredCapabilities.phantomjs();
        final List<String> cliArguments = new ArrayList<String>();
        cliArguments.add("--web-security=false");
        cliArguments.add("--ssl-protocol=any");
        cliArguments.add("--ignore-ssl-errors=true");
        caps.setCapability("phantomjs.cli.args", applyPhantomJSProxySettings(cliArguments, proxySettings));
        caps.setCapability("takesScreenshot", true);

        logger.debug("Configured default PhantomJS DesiredCapabilities: " + caps.toString());

        return caps;
    }

    private List<String> applyPhantomJSProxySettings(List<String> cliArguments, Proxy proxySettings) {
        if (null == proxySettings) {
            cliArguments.add("--proxy-type=none");
        } else {
            cliArguments.add("--proxy-type=http");
            cliArguments.add("--proxy=" + proxySettings.getHttpProxy());
        }
        return cliArguments;
    }

    /**
     * Merge capabilities
     *
     * @param sourceCaps instance of the source capabilities
     * @param newCaps    instance of the capabilities to be merged to the sourceCaps
     * @return DesiredCapabilities instance
     */
    public DesiredCapabilities mergeCapabilities(DesiredCapabilities sourceCaps, DesiredCapabilities newCaps) {

        logger.debug("Merging capabilities [NEW_CAPS]: " + newCaps + " into [SOURCE]: " + sourceCaps);

        return caps = sourceCaps.merge(newCaps);
    }

    /**
     * Merge capabilities
     *
     * @param newCaps instance of the capabilities to be merged to the {@link DesiredCapsFactory#caps}
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

}
