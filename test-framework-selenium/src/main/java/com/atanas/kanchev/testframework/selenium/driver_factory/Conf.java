package com.atanas.kanchev.testframework.selenium.driver_factory;

import static com.atanas.kanchev.testframework.selenium.driver_factory.Conf.DefaultProperties.DEFAULT_BROWSER;

/**
 * @author Atanas Ksnchev
 */
public class Conf {

    public static final String OPERATING_SYSTEM = System.getProperty("os.name").toUpperCase();
    public static final String SYSTEM_ARCHITECTURE = System.getProperty("os.arch");

    public static class DefaultProperties {
        public static final DriverType DEFAULT_BROWSER = DriverType.FIREFOX;
        public static final int DEFAULT_BROWSER_RES_WIDTH = 1024;
        public static final int DEFAULT_BROWSER_RES_HEIGHT = 768;
        public static final long DEFAULT_IMPL_WAIT = 500L;
        public static final long DEFAULT_PAGE_LOAD_TIMEOUT = 3000L;
    }

    public static class JVMArgs {
        public static final String BROWSER = System.getProperty("browser", DEFAULT_BROWSER.toString()).toUpperCase();
        public static final boolean USE_REMOTE_WEB_DRIVER = Boolean.getBoolean("remoteDriver");
        public static final String GRID_URL = System.getProperty("gridURL");
        public static final String BROWSER_VERSION = System.getProperty("desiredBrowserVersion");
        public static final String PLATFORM = System.getProperty("desiredPlatform", "ANY");
        public static final boolean PROXY_ENABLED = Boolean.getBoolean("proxyEnabled");
        public static final String PROXY_HOSTNAME = System.getProperty("proxyHost");
        public static final Integer PROXY_PORT = Integer.getInteger("PROXY_PORT");
        public static final String PROXY_DETAILS = String.format("%s:%d", PROXY_HOSTNAME, PROXY_PORT);
        public static final boolean START_MAXIMIZED = Boolean.valueOf(System.getProperty("start.maximized", "false"));
        public static final boolean REUSE_BROWSER = Boolean.valueOf(System.getProperty("reuse.browser", "false"));
    }
}
