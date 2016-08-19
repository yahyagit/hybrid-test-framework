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

import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>DriverConfig class.</p>
 *
 * @author Atanas Kanchev
 */
public class DriverConfig {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(DriverConfig.class);

    /**
     * Constant <code>OPERATING_SYSTEM="System.getProperty(os.name).toUpperCase"{trunked}</code>
     */
    public static final String OPERATING_SYSTEM = System.getProperty("os.name").toUpperCase();
    /**
     * Constant <code>SYSTEM_ARCHITECTURE="System.getProperty(os.arch)"</code>
     */
    public static final String SYSTEM_ARCHITECTURE = System.getProperty("os.arch");

    /**
     * Constant <code>DEFAULT_BROWSER</code>
     */
    public static final DriverType DEFAULT_BROWSER = DriverType.FIREFOX;
    /**
     * Constant <code>DEFAULT_BROWSER_RES_WIDTH=1024</code>
     */
    public static final int DEFAULT_BROWSER_RES_WIDTH = 1024;
    /**
     * Constant <code>DEFAULT_BROWSER_RES_HEIGHT=768</code>
     */
    public static final int DEFAULT_BROWSER_RES_HEIGHT = 768;
    /**
     * Constant <code>DEFAULT_IMPL_WAIT=500L</code>
     */
    public static final long DEFAULT_IMPL_WAIT = 500L;
    /**
     * Constant <code>DEFAULT_PAGE_LOAD_TIMEOUT=30000L</code>
     */
    public static final long DEFAULT_PAGE_LOAD_TIMEOUT = 30000L;

    private DesiredCapabilities customCapabilities;

    private String browser =
        System.getProperty("browser", DEFAULT_BROWSER.toString()).toUpperCase();
    private String resolution = System.getProperty("resolution");
    private String userAgent = System.getProperty("user.agent");
    private String env = System.getProperty("env");
    // TODO Add resolution, user agent and env handlers
    private boolean useRemoteWebDriver = Boolean.getBoolean("grid.enabled");
    private String hubURL = System.getProperty("grid.hub.url");
    private String browserVersion = System.getProperty("grid.browser.version");
    private String platform = System.getProperty("platform", "ANY");
    private boolean proxyEnabled = Boolean.getBoolean("proxy.enabled");
    private String proxyHost = System.getProperty("proxy.host");
    private int proxyPort = Integer.getInteger("proxy.port", 0);
    private String proxyDetails = String.format("%s:%d", proxyHost, proxyPort);
    private boolean startMaximized =
        Boolean.valueOf(System.getProperty("start.maximized", "false"));
    private boolean reuseBrowser = Boolean.valueOf(System.getProperty("reuse.browser", "false"));

    /**
     * <p>Getter for the field <code>resolution</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getResolution() {
        return resolution;
    }

    /**
     * <p>Setter for the field <code>resolution</code>.</p>
     *
     * @param resolution a {@link java.lang.String} object.
     * @return a {@link com.atanas.kanchev.testframework.selenium.driverfactory.DriverConfig} object.
     */
    public DriverConfig setResolution(String resolution) {
        this.resolution = resolution;
        return this;
    }

    /**
     * <p>Getter for the field <code>userAgent</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * <p>Setter for the field <code>userAgent</code>.</p>
     *
     * @param userAgent a {@link java.lang.String} object.
     * @return a {@link com.atanas.kanchev.testframework.selenium.driverfactory.DriverConfig} object.
     */
    public DriverConfig setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    /**
     * <p>Getter for the field <code>env</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getEnv() {
        return env;
    }

    /**
     * <p>Setter for the field <code>env</code>.</p>
     *
     * @param env a {@link java.lang.String} object.
     * @return a {@link com.atanas.kanchev.testframework.selenium.driverfactory.DriverConfig} object.
     */
    public DriverConfig setEnv(String env) {
        this.env = env;
        return this;
    }

    /**
     * <p>Getter for the field <code>customCapabilities</code>.</p>
     *
     * @return a {@link org.openqa.selenium.remote.DesiredCapabilities} object.
     */
    public DesiredCapabilities getCustomCapabilities() {
        return customCapabilities;
    }

    /**
     * <p>Setter for the field <code>customCapabilities</code>.</p>
     *
     * @param customCapabilities a {@link org.openqa.selenium.remote.DesiredCapabilities} object.
     * @return a {@link com.atanas.kanchev.testframework.selenium.driverfactory.DriverConfig} object.
     */
    public DriverConfig setCustomCapabilities(DesiredCapabilities customCapabilities) {
        this.customCapabilities = customCapabilities;
        return this;
    }

    /**
     * <p>Getter for the field <code>browser</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getBrowser() {
        return browser;
    }

    /**
     * <p>Setter for the field <code>browser</code>.</p>
     *
     * @param browser a {@link java.lang.String} object.
     * @return a {@link com.atanas.kanchev.testframework.selenium.driverfactory.DriverConfig} object.
     */
    public DriverConfig setBrowser(String browser) {
        this.browser = browser.toUpperCase();
        return this;
    }

    /**
     * <p>isUseRemoteWebDriver.</p>
     *
     * @return a boolean.
     */
    public boolean isUseRemoteWebDriver() {
        return useRemoteWebDriver;
    }

    /**
     * <p>Setter for the field <code>useRemoteWebDriver</code>.</p>
     *
     * @param useRemoteWebDriver a boolean.
     * @return a {@link com.atanas.kanchev.testframework.selenium.driverfactory.DriverConfig} object.
     */
    public DriverConfig setUseRemoteWebDriver(boolean useRemoteWebDriver) {
        this.useRemoteWebDriver = useRemoteWebDriver;
        return this;
    }

    /**
     * <p>Getter for the field <code>hubURL</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getHubURL() {
        return hubURL;
    }

    /**
     * <p>Setter for the field <code>hubURL</code>.</p>
     *
     * @param hubURL a {@link java.lang.String} object.
     * @return a {@link com.atanas.kanchev.testframework.selenium.driverfactory.DriverConfig} object.
     */
    public DriverConfig setHubURL(String hubURL) {
        this.hubURL = hubURL;
        return this;
    }

    /**
     * <p>Getter for the field <code>browserVersion</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getBrowserVersion() {
        return browserVersion;
    }

    /**
     * <p>Setter for the field <code>browserVersion</code>.</p>
     *
     * @param browserVersion a {@link java.lang.String} object.
     * @return a {@link com.atanas.kanchev.testframework.selenium.driverfactory.DriverConfig} object.
     */
    public DriverConfig setBrowserVersion(String browserVersion) {
        this.browserVersion = browserVersion;
        return this;
    }

    /**
     * <p>Getter for the field <code>platform</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * <p>Setter for the field <code>platform</code>.</p>
     *
     * @param platform a {@link java.lang.String} object.
     * @return a {@link com.atanas.kanchev.testframework.selenium.driverfactory.DriverConfig} object.
     */
    public DriverConfig setPlatform(String platform) {
        this.platform = platform;
        return this;
    }

    /**
     * <p>isProxyEnabled.</p>
     *
     * @return a boolean.
     */
    public boolean isProxyEnabled() {
        return proxyEnabled;
    }

    /**
     * <p>Setter for the field <code>proxyEnabled</code>.</p>
     *
     * @param proxyEnabled a boolean.
     * @return a {@link com.atanas.kanchev.testframework.selenium.driverfactory.DriverConfig} object.
     */
    public DriverConfig setProxyEnabled(boolean proxyEnabled) {
        this.proxyEnabled = proxyEnabled;
        return this;
    }

    /**
     * <p>Getter for the field <code>proxyHost</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getProxyHost() {
        return proxyHost;
    }

    /**
     * <p>Setter for the field <code>proxyHost</code>.</p>
     *
     * @param proxyHost a {@link java.lang.String} object.
     * @return a {@link com.atanas.kanchev.testframework.selenium.driverfactory.DriverConfig} object.
     */
    public DriverConfig setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
        return this;
    }

    /**
     * <p>Getter for the field <code>proxyPort</code>.</p>
     *
     * @return a int.
     */
    public int getProxyPort() {
        return proxyPort;
    }

    /**
     * <p>Setter for the field <code>proxyPort</code>.</p>
     *
     * @param proxyPort a int.
     * @return a {@link com.atanas.kanchev.testframework.selenium.driverfactory.DriverConfig} object.
     */
    public DriverConfig setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
        return this;
    }

    /**
     * <p>Getter for the field <code>proxyDetails</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getProxyDetails() {
        return proxyDetails;
    }

    /**
     * <p>Setter for the field <code>proxyDetails</code>.</p>
     *
     * @param proxyDetails a {@link java.lang.String} object.
     * @return a {@link com.atanas.kanchev.testframework.selenium.driverfactory.DriverConfig} object.
     */
    public DriverConfig setProxyDetails(String proxyDetails) {
        this.proxyDetails = proxyDetails;
        return this;
    }

    /**
     * <p>isStartMaximized.</p>
     *
     * @return a boolean.
     */
    public boolean isStartMaximized() {
        return startMaximized;
    }

    /**
     * <p>Setter for the field <code>startMaximized</code>.</p>
     *
     * @param startMaximized a boolean.
     * @return a {@link com.atanas.kanchev.testframework.selenium.driverfactory.DriverConfig} object.
     */
    public DriverConfig setStartMaximized(boolean startMaximized) {
        this.startMaximized = startMaximized;
        return this;
    }

    /**
     * <p>isReuseBrowser.</p>
     *
     * @return a boolean.
     */
    public boolean isReuseBrowser() {
        return reuseBrowser;
    }

    /**
     * <p>Setter for the field <code>reuseBrowser</code>.</p>
     *
     * @param reuseBrowser a boolean.
     * @return a {@link com.atanas.kanchev.testframework.selenium.driverfactory.DriverConfig} object.
     */
    public DriverConfig setReuseBrowser(boolean reuseBrowser) {
        this.reuseBrowser = reuseBrowser;
        return this;
    }

}
