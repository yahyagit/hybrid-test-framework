package com.atanas.kanchev.testframework.selenium.driver_factory;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Atanas Ksnchev
 */
public class DriverConfig{

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(DriverConfig.class);

    public static final String OPERATING_SYSTEM = System.getProperty("os.name").toUpperCase();
    public static final String SYSTEM_ARCHITECTURE = System.getProperty("os.arch");

    public static final DriverType DEFAULT_BROWSER = DriverType.FIREFOX;
    public static final int DEFAULT_BROWSER_RES_WIDTH = 1024;
    public static final int DEFAULT_BROWSER_RES_HEIGHT = 768;
    public static final long DEFAULT_IMPL_WAIT = 500L;
    public static final long DEFAULT_PAGE_LOAD_TIMEOUT = 3000L;

    private DesiredCapabilities customCapabilities;

    private String browser = System.getProperty("browser", DEFAULT_BROWSER.toString()).toUpperCase();
    private boolean useRemoteWebDriver = Boolean.getBoolean("remoteDriver");
    private String gridURL = System.getProperty("gridURL");
    private String browserVersion = System.getProperty("browserVersion");
    private String platform = System.getProperty("desiredPlatform", "ANY");
    private boolean proxyEnabled = Boolean.getBoolean("proxyEnabled");
    private String proxyHost = System.getProperty("proxyHost");
    private int proxyPort = Integer.getInteger("proxyPort", 0);
    private String proxyDetails = String.format("%s:%d", proxyHost, proxyPort);
    private boolean startMaximized = Boolean.valueOf(System.getProperty("start.maximized", "false"));
    private boolean reuseBrowser = Boolean.valueOf(System.getProperty("reuse.browser", "false"));

    public DesiredCapabilities getCustomCapabilities() {
        return customCapabilities;
    }

    public void setCustomCapabilities(DesiredCapabilities customCapabilities) {
        this.customCapabilities = customCapabilities;
    }

    public String getBrowser() {
        return browser;
    }

    public DriverConfig setBrowser(String browser) {
        this.browser = browser.toUpperCase();
        return this;
    }

    public boolean isUseRemoteWebDriver() {
        return useRemoteWebDriver;
    }

    public DriverConfig setUseRemoteWebDriver(boolean useRemoteWebDriver) {
        this.useRemoteWebDriver = useRemoteWebDriver;
        return this;
    }

    public String getGridURL() {
        return gridURL;
    }

    public DriverConfig setGridURL(String gridURL) {
        this.gridURL = gridURL;
        return this;
    }

    public String getBrowserVersion() {
        return browserVersion;
    }

    public DriverConfig setBrowserVersion(String browserVersion) {
        this.browserVersion = browserVersion;
        return this;
    }

    public String getPlatform() {
        return platform;
    }

    public DriverConfig setPlatform(String platform) {
        this.platform = platform;
        return this;
    }

    public boolean isProxyEnabled() {
        return proxyEnabled;
    }

    public DriverConfig setProxyEnabled(boolean proxyEnabled) {
        this.proxyEnabled = proxyEnabled;
        return this;
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public DriverConfig setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
        return this;
    }

    public int getProxyPort() {
        return proxyPort;
    }

    public DriverConfig setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
        return this;
    }

    public String getProxyDetails() {
        return proxyDetails;
    }

    public DriverConfig setProxyDetails(String proxyDetails) {
        this.proxyDetails = proxyDetails;
        return this;
    }

    public boolean isStartMaximized() {
        return startMaximized;
    }

    public DriverConfig setStartMaximized(boolean startMaximized) {
        this.startMaximized = startMaximized;
        return this;
    }

    public boolean isReuseBrowser() {
        return reuseBrowser;
    }

    public DriverConfig setReuseBrowser(boolean reuseBrowser) {
        this.reuseBrowser = reuseBrowser;
        return this;
    }

}