/*
 * Copyright 2016 Atanas Stoychev Kanchev
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>DesiredCapsFactory class.</p>
 *
 * @author Atanas Kanchev
 */
public class DesiredCapsFactory {

    private static final Logger logger = LoggerFactory.getLogger(DesiredCapsFactory.class);
    private DesiredCapabilities caps;

    public DesiredCapsFactory() {
        this.caps = createCommonCapabilities();
    }

    /**
     * <p>getDefaultChromeCaps.</p>
     *
     * @return a {@link org.openqa.selenium.remote.DesiredCapabilities} object.
     */
    public DesiredCapabilities getDefaultChromeCaps() {

        caps = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        if (DriverConfiguration.getChromeSwitches() != null) {
            options.addArguments("chrome.switches", DriverConfiguration.getChromeSwitches());
        }
        caps.setCapability(ChromeOptions.CAPABILITY, options);
        logger.debug("Configured default Chrome " + caps.toString());

        return caps;
    }

    /**
     * <p>getDefaultFirefoxCaps.</p>
     *
     * @return a {@link org.openqa.selenium.remote.DesiredCapabilities} object.
     */
    public DesiredCapabilities getDefaultFirefoxCaps() {

        caps = DesiredCapabilities.firefox();
        FirefoxProfile myProfile = new FirefoxProfile();

        if (DriverConfiguration.getFirefoxProfile() != null) {
            myProfile = DriverConfiguration.getFirefoxProfile();
        }
        myProfile.setPreference("network.automatic-ntlm-auth.trusted-uris", "http://,https://");
        myProfile.setPreference("network.automatic-ntlm-auth.allow-non-fqdn", true);
        myProfile.setPreference("network.negotiate-auth.delegation-uris", "http://,https://");
        myProfile.setPreference("network.negotiate-auth.trusted-uris", "http://,https://");
        myProfile.setPreference("network.http.phishy-userpass-length", 255);
        myProfile.setPreference("security.csp.enable", false);

        caps.setCapability(FirefoxDriver.PROFILE, myProfile);

        logger.debug("Configured default Firefox " + caps.toString());

        return caps;
    }

    /**
     * <p>getDefaultIECaps.</p>
     *
     * @return a {@link org.openqa.selenium.remote.DesiredCapabilities} object.
     */
    public DesiredCapabilities getDefaultIECaps() {

        caps = DesiredCapabilities.internetExplorer();
        caps.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
        caps.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
        caps.setCapability("requireWindowFocus", true);

        logger.debug("Configured default IE " + caps.toString());

        return caps;
    }

    /**
     * <p>getDefaultEdgeCaps.</p>
     *
     * @return a {@link org.openqa.selenium.remote.DesiredCapabilities} object.
     */
    public DesiredCapabilities getDefaultEdgeCaps() {

        caps = DesiredCapabilities.edge();
        logger.debug("Configured default Edge " + caps.toString());

        return caps;
    }

    /**
     * <p>getDefaultSafariCaps.</p>
     *
     * @return a {@link org.openqa.selenium.remote.DesiredCapabilities} object.
     */
    public DesiredCapabilities getDefaultSafariCaps() {

        caps = DesiredCapabilities.safari();
        caps.setCapability("safari.cleanSession", true);

        logger.debug("Configured default Safari " + caps.toString());

        return caps;
    }

    /**
     * <p>getDefaultOperaCaps.</p>
     *
     * @return a {@link org.openqa.selenium.remote.DesiredCapabilities} object.
     */
    public DesiredCapabilities getDefaultOperaCaps() {
        caps = DesiredCapabilities.operaBlink();
        logger.debug("Configured default Opera " + caps.toString());
        return caps;
    }

    /**
     * <p>getDefaultPhantomJSCaps.</p>
     *
     * @param proxySettings a {@link org.openqa.selenium.Proxy} object.
     * @return a {@link org.openqa.selenium.remote.DesiredCapabilities} object.
     */
    public DesiredCapabilities getDefaultPhantomJSCaps(Proxy proxySettings) {
        caps = DesiredCapabilities.phantomjs();
        final List<String> cliArguments = new ArrayList<String>();
        cliArguments.add("--web-security=false");
        cliArguments.add("--ssl-protocol=any");
        cliArguments.add("--ignore-ssl-errors=true");
        caps.setCapability("phantomjs.cli.args",
            applyPhantomJSProxySettings(cliArguments, proxySettings));
        caps.setCapability("takesScreenshot", true);

        logger.debug("Configured default PhantomJS " + caps.toString());

        return caps;
    }

    private List<String> applyPhantomJSProxySettings(List<String> cliArguments,
        Proxy proxySettings) {
        if (null == proxySettings) {
            cliArguments.add("--proxy-type=none");
        } else {
            cliArguments.add("--proxy-type=http");
            cliArguments.add("--proxy=" + proxySettings.getHttpProxy());
        }
        return cliArguments;
    }

    /**
     * <p>mergeCapabilities.</p>
     *
     * @param sourceCaps a {@link org.openqa.selenium.remote.DesiredCapabilities} object.
     * @param newCaps    a {@link org.openqa.selenium.remote.DesiredCapabilities} object.
     * @return a {@link org.openqa.selenium.remote.DesiredCapabilities} object.
     */
    public DesiredCapabilities mergeCapabilities(DesiredCapabilities sourceCaps,
        DesiredCapabilities newCaps) {

        logger
            .debug("Merging capabilities [NEW_CAPS]: " + newCaps + " into [SOURCE]: " + sourceCaps);

        return caps = sourceCaps.merge(newCaps);
    }

    /**
     * <p>mergeCapabilities.</p>
     *
     * @param newCaps a {@link org.openqa.selenium.remote.DesiredCapabilities} object.
     * @return a {@link org.openqa.selenium.remote.DesiredCapabilities} object.
     */
    public DesiredCapabilities mergeCapabilities(DesiredCapabilities newCaps) {

        logger.debug("Merging capabilities [NEW_CAPS]: " + newCaps + " into [SOURCE]: " + caps);

        return caps = caps.merge(newCaps);
    }

    /**
     * <p>setCustomChromeOptions.</p>
     *
     * @param chromeOptions a {@link org.openqa.selenium.chrome.ChromeOptions} object.
     * @return a {@link org.openqa.selenium.remote.DesiredCapabilities} object.
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
     * <p>setCustomChromeOptions.</p>
     *
     * @param chromeOptions a {@link java.util.Map} object.
     * @return a {@link org.openqa.selenium.remote.DesiredCapabilities} object.
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
     * <p>setCustomFirefoxProfile.</p>
     *
     * @param firefoxProfile a {@link org.openqa.selenium.firefox.FirefoxProfile} object.
     * @return a {@link org.openqa.selenium.remote.DesiredCapabilities} object.
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

    private DesiredCapabilities createCommonCapabilities() {
        DesiredCapabilities caps = new DesiredCapabilities();
        if (DriverConfiguration.getBrowserVersion() != null && !DriverConfiguration
            .getBrowserVersion().isEmpty()) {
            caps.setVersion(DriverConfiguration.getBrowserVersion());
        }
        caps.setCapability(CapabilityType.PAGE_LOAD_STRATEGY,
            DriverConfiguration.getPageLoadStrategy());
        return caps;
    }

}
