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

import com.atanas.kanchev.testframework.commons.conf.JVMArgs;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverConfiguration extends Defaults {

    private final Logger logger = LoggerFactory.getLogger(DriverConfiguration.class.getName());

    public DriverConfiguration() {
        logger.debug(DriverType.getDriverTypes());
        logger.debug(super.toString());
        logger.debug(this.toString());

    }

    /**
     * If reuseBrowser is true, browser window stays open after running tests.
     * It may be useful for debugging or saving execution time.
     * Can be configured either programmatically or by system property "-DreuseBrowser=true".
     * <p/>
     * Default value: false.
     */
    private static boolean reuseBrowser = Boolean.parseBoolean(System
        .getProperty(JVMArgs.reuseBrowser.name(), String.valueOf(Defaults.DEF_REUSE_BROWSER)));

    /**
     * Specifies the amount of time the driver should wait when searching for an element if it is
     * not immediately present.
     */
    private static long implicitlyWait = Long.parseLong(
        System.getProperty(JVMArgs.implicitlyWait.name(), String.valueOf(DEF_IMPL_WAIT)));

    /**
     * Sets the amount of time to wait for a page load to complete before throwing an error.
     */
    private static long pageLoadTimeout = Long.parseLong(
        System.getProperty(JVMArgs.pageLoadTimeout.name(), String.valueOf(DEF_PAGE_LOAD_TIMEOUT)));

    /**
     * Interval in milliseconds, when checking if a single element is appeared
     * Default value: 100 (milliseconds)
     */
    private static long pollingInterval = Long.parseLong(System
        .getProperty(JVMArgs.pollingInterval.name(),
            String.valueOf(Defaults.DEF_POLLING_INTERVAL)));

    /**
     * Should re-spawn browser if it's disappeared (hangs, broken, unexpectedly closed).
     * <p>
     * Can be configured either programmatically or by system property "-DreopenBrowserOnFail=false".
     * <p>
     * Default value: true
     * Set this property to false if you want to disable automatic re-spawning the browser.
     */
    private static boolean reopenBrowserOnFail = Boolean.parseBoolean(System
        .getProperty(JVMArgs.reopenBrowserOnFail.name(),
            String.valueOf(Defaults.DEF_REOPEN_BROWSER_ON_FAIL)));

    /**
     * Timeout (in milliseconds) for closing/killing browser.
     * <p/>
     * Sometimes we have problems with calling driver.close() or driver.quit() method, and test always is suspended too long.
     * <p/>
     * Default value: 5000 (milliseconds)
     */
    private static long closeBrowserTimeout = Long.parseLong(System
        .getProperty(JVMArgs.closeBrowserTimeout.name(),
            String.valueOf(Defaults.DEF_CLOSE_BROWSER_TIMEOUT)));

    /**
     * Which browser to use.
     * Can be configured either programmatically or by system property "-Dbrowser=ie".
     * <p/>
     * Default value: "firefox"
     */
    private static String browser =
        System.getProperty(JVMArgs.browser.name(), String.valueOf(Defaults.DEF_BROWSER));

    /**
     * The browser window size.
     * Can be configured either programmatically or by system property "-DbrowserSize=1024x768".
     * <p>
     * Default value: none (browser size will not be set explicitly)
     */
    private static String browserSize = System.getProperty(JVMArgs.browserSize.name(),
        Defaults.DEF_BROWSER_RES_WIDTH + "x" + Defaults.DEF_BROWSER_RES_HEIGHT);

    /**
     * The browser window is maximized when started.
     * Can be configured either programmatically or by system property "startMaximized=true".
     * <p>
     * Default value: true
     */
    private static boolean startMaximized = Boolean.parseBoolean(System
        .getProperty(JVMArgs.startMaximized.name(), String.valueOf(Defaults.DEF_START_MAXIMISED)));

    /**
     * Emulate mobile devices by setting user agents
     * Can be configured either programmatically or
     * by system property "-DuserAgent="Mozilla/5.0 (iPhone; CPU iPhone OS 5_0 like Mac OS X)"".
     * <p>
     * Default value: none (browser size will not be set explicitly)
     */
    private static String userAgent = System.getProperty(JVMArgs.userAgent.name());

    /**
     * Value of "chrome.switches" parameter (in case of using Chrome driver).
     * Can be configured either programmatically or by system property,
     * i.e. "-DchromeSwitches=--disable-popup-blocking".
     * <p>
     * Default value: none
     */
    private static String chromeSwitches = System.getProperty(JVMArgs.chromeSwitches.name());

    private static FirefoxProfile firefoxProfile;

    /**
     * URL of remote web driver (in case of using Selenium Grid).
     * Can be configured either programmatically or
     * by system property "-Dhub=http://localhost:5678/wd/hub".
     * <p>
     * Default value: null (Grid is not used).
     */
    private static String hub = System.getProperty(JVMArgs.hub.name());

    /**
     * Which browser version to use
     * Can be configured either programmatically or
     * by system property or "-DbrowserVersion=45".
     * <p/>
     * Default value: none
     */
    private static String browserVersion = System.getProperty(JVMArgs.browserVersion.name());

    /**
     * Which platform to use
     * Can be configured either programmatically or
     * by system property or "-Dplatform=linux".
     * <p/>
     * Default value: {@link org.openqa.selenium.Platform#ANY}
     */
    private static String platform =
        System.getProperty(JVMArgs.platform.name(), String.valueOf(Platform.ANY));

    /**
     * Should webdriver wait until page is completely loaded.
     * Possible values: "none", "normal" and "eager".
     * Default value: "normal".
     * <p>
     * - `normal`: return after the load event fires on the new page (it's default in Selenium webdriver);
     * - `eager`: return after DOMContentLoaded fires;
     * - `none`: return immediately (it's the framework default).
     * <p>
     * It seems that `none` is the best option for because all its commands wait until
     * corresponding condition becomes true.
     * Thought, we left default value `normal` because we afraid to break users' existing tests.
     * <p>
     * See https://w3c.github.io/webdriver/webdriver-spec.html#dfn-page-loading-strategy
     *
     * @since 3.5
     */
    private static String pageLoadStrategy = System.getProperty("page.load.strategy", "normal");

    /**
     * Take screenshot on failing tests.
     * <p>
     * Default value: true
     */
    private static boolean screenshotOnFailure = Boolean.parseBoolean(System
        .getProperty(JVMArgs.screenshotOnFailure.name(),
            String.valueOf(Defaults.DEF_SCREENSHOT_ON_FAILURE)));

    /**
     * Save page source on failing tests.
     * <p>
     * Default value: false
     */
    private static boolean savePageSourceOnFailure = Boolean.parseBoolean(System
        .getProperty(JVMArgs.savePageSourceOnFailure.name(),
            String.valueOf(Defaults.DEF_PAGE_SOURCE_ON_FAILURE)));

    /**
     * Folder to store screenshots and page source on test failure
     * Can be configured either programmatically or by system property "-DreportsFolder=test-result/reports".
     * <p>
     * Default value: "target/reports/tests"
     */
    private static String reportsFolder = System
        .getProperty(JVMArgs.reportsFolder.name(), String.valueOf(Defaults.DEF_REPORTS_FOLDER));

    /**
     * Custom DesiredCapabilities
     */
    private static DesiredCapabilities customCapabilities;

    /**
     * Selenium proxy {@link org.openqa.selenium.Proxy}
     */
    private static Proxy proxy;

    /**
     * Is Selenium proxy enabled
     * Default value: false
     */
    private static boolean proxyEnabled = Boolean.getBoolean("proxyEnabled");

    /**
     * Proxy host name
     */
    private static String proxyHostname = System.getProperty("proxyHost");

    /**
     * Proxy host port
     */
    private static Integer proxyPort = Integer.getInteger("proxyPort");

    public static long getImplicitlyWait() {
        return implicitlyWait;
    }

    public static void setImplicitlyWait(long implicitlyWait) {
        DriverConfiguration.implicitlyWait = implicitlyWait;
    }

    public static long getPageLoadTimeout() {
        return pageLoadTimeout;
    }

    public static void setPageLoadTimeout(long pageLoadTimeout) {
        DriverConfiguration.pageLoadTimeout = pageLoadTimeout;
    }

    public static long getPollingInterval() {
        return pollingInterval;
    }

    public static void setPollingInterval(long pollingInterval) {
        DriverConfiguration.pollingInterval = pollingInterval;
    }

    public static boolean isReuseBrowser() {
        return reuseBrowser;
    }

    public static void setReuseBrowser(boolean reuseBrowser) {
        DriverConfiguration.reuseBrowser = reuseBrowser;
    }

    public static boolean isReopenBrowserOnFail() {
        return reopenBrowserOnFail;
    }

    public static void setReopenBrowserOnFail(boolean reopenBrowserOnFail) {
        DriverConfiguration.reopenBrowserOnFail = reopenBrowserOnFail;
    }

    public static long getCloseBrowserTimeout() {
        return closeBrowserTimeout;
    }

    public static void setCloseBrowserTimeout(long closeBrowserTimeout) {
        DriverConfiguration.closeBrowserTimeout = closeBrowserTimeout;
    }

    public static String getBrowser() {
        return browser;
    }

    public static void setBrowser(String browser) {
        DriverConfiguration.browser = browser;
    }

    public static String getBrowserSize() {
        return browserSize;
    }

    public static void setBrowserSize(String browserSize) {
        DriverConfiguration.browserSize = browserSize;
    }

    public static boolean isStartMaximized() {
        return startMaximized;
    }

    public static void setStartMaximized(boolean startMaximized) {
        DriverConfiguration.startMaximized = startMaximized;
    }

    public static String getUserAgent() {
        return userAgent;
    }

    public static void setUserAgent(String userAgent) {
        DriverConfiguration.userAgent = userAgent;
    }

    public static String getChromeSwitches() {
        return chromeSwitches;
    }

    public static void setChromeSwitches(String chromeSwitches) {
        DriverConfiguration.chromeSwitches = chromeSwitches;
    }

    public static FirefoxProfile getFirefoxProfile() {
        return firefoxProfile;
    }

    public static void setFirefoxProfile(FirefoxProfile firefoxProfile) {
        DriverConfiguration.firefoxProfile = firefoxProfile;
    }

    public static String getHub() {
        return hub;
    }

    public static void setHub(String hub) {
        DriverConfiguration.hub = hub;
    }

    public static String getBrowserVersion() {
        return browserVersion;
    }

    public static void setBrowserVersion(String browserVersion) {
        DriverConfiguration.browserVersion = browserVersion;
    }

    public static String getPlatform() {
        return platform;
    }

    public static void setPlatform(String platform) {
        DriverConfiguration.platform = platform;
    }

    public static String getPageLoadStrategy() {
        return pageLoadStrategy;
    }

    public static void setPageLoadStrategy(String pageLoadStrategy) {
        DriverConfiguration.pageLoadStrategy = pageLoadStrategy;
    }

    public static boolean isScreenshotOnFailure() {
        return screenshotOnFailure;
    }

    public static void setScreenshotOnFailure(boolean screenshotOnFailure) {
        DriverConfiguration.screenshotOnFailure = screenshotOnFailure;
    }

    public static boolean isSavePageSourceOnFailure() {
        return savePageSourceOnFailure;
    }

    public static void setSavePageSourceOnFailure(boolean savePageSourceOnFailure) {
        DriverConfiguration.savePageSourceOnFailure = savePageSourceOnFailure;
    }

    public static String getReportsFolder() {
        return reportsFolder;
    }

    public static void setReportsFolder(String reportsFolder) {
        DriverConfiguration.reportsFolder = reportsFolder;
    }

    public static DesiredCapabilities getCustomCapabilities() {
        return customCapabilities;
    }

    public static void setCustomCapabilities(DesiredCapabilities customCapabilities) {
        DriverConfiguration.customCapabilities = customCapabilities;
    }

    public static Proxy getProxy() {
        return proxy;
    }

    public static void setProxy(Proxy proxy) {
        DriverConfiguration.proxy = proxy;
    }

    public static boolean isProxyEnabled() {
        return proxyEnabled;
    }

    public static void setProxyEnabled(boolean proxyEnabled) {
        DriverConfiguration.proxyEnabled = proxyEnabled;
    }

    public static String getProxyHostname() {
        return proxyHostname;
    }

    public static void setProxyHostname(String proxyHostname) {
        DriverConfiguration.proxyHostname = proxyHostname;
    }

    public static Integer getProxyPort() {
        return proxyPort;
    }

    public static void setProxyPort(Integer proxyPort) {
        DriverConfiguration.proxyPort = proxyPort;
    }

    @Override public String toString() {
        return "DriverConfiguration{" + "\nimplicitlyWait=" + implicitlyWait + ",\npageLoadTimeout="
            + pageLoadTimeout + ",\npollingInterval=" + pollingInterval + ",\nreuseBrowser="
            + reuseBrowser + ",\nreopenBrowserOnFail=" + reopenBrowserOnFail
            + ",\ncloseBrowserTimeout=" + closeBrowserTimeout + ",\nbrowser='" + browser + '\''
            + ",\nbrowserSize='" + browserSize + '\'' + ",\nstartMaximized=" + startMaximized
            + ",\nuserAgent='" + userAgent + '\'' + ",\nchromeSwitches='" + chromeSwitches + '\''
            + ",\nhub='" + hub + '\'' + ",\nbrowserVersion='" + browserVersion + '\''
            + ",\nplatform='" + platform + '\'' + ",\npageLoadStrategy='" + pageLoadStrategy + '\''
            + ",\nscreenshotOnFailure=" + screenshotOnFailure + ",\nsavePageSourceOnFailure="
            + savePageSourceOnFailure + ",\nreportsFolder='" + reportsFolder + '\''
            + ",\ncustomCapabilities=" + customCapabilities + '}';
    }
}
