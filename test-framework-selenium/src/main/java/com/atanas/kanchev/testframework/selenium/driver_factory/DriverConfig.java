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
    public static final long DEFAULT_PAGE_LOAD_TIMEOUT = 30000L;

    private DesiredCapabilities customCapabilities;

    private String browser = System.getProperty("browser", DEFAULT_BROWSER.toString()).toUpperCase();
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
    private boolean startMaximized = Boolean.valueOf(System.getProperty("start.maximized", "false"));
    private boolean reuseBrowser = Boolean.valueOf(System.getProperty("reuse.browser", "false"));

    public String getResolution() {
        return resolution;
    }

    public DriverConfig setResolution(String resolution) {
        this.resolution = resolution;
        return this;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public DriverConfig setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    public String getEnv() {
        return env;
    }

    public DriverConfig setEnv(String env) {
        this.env = env;
        return this;
    }

    public DesiredCapabilities getCustomCapabilities() {
        return customCapabilities;
    }

    public DriverConfig setCustomCapabilities(DesiredCapabilities customCapabilities) {
        this.customCapabilities = customCapabilities;
        return this;
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

    public String getHubURL() {
        return hubURL;
    }

    public DriverConfig setHubURL(String hubURL) {
        this.hubURL = hubURL;
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