package com.atanas.kanchev.testframework.selenium.driverfactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.atanas.kanchev.testframework.selenium.driverfactory.JVMArgsFactory.ArgsEnum.*;

/**
 * JVM Arguments Factory
 *
 * @author Atanas Kanchev
 */
public class JVMArgsFactory {

    private static final Logger logger = LoggerFactory.getLogger(JVMArgsFactory.class);

    /**
     * Get JVM arg: execution environment
     *
     * @return {@link ArgsEnum#ENVIRONMENT}
     */
    public static String getEnvironment() {
        return getJMVArg(ENVIRONMENT, null);
    }

    /**
     * Get JVM arg: browser type
     *
     * @return {@link ArgsEnum#BROWSER_TYPE} or {@link DefaultProperties#DEFAULT_BROWSER}
     */
    public static Browsers getBrowserType() {

        Browsers browser = DefaultProperties.DEFAULT_BROWSER;

        try {
            browser = Browsers.valueOf(getJMVArg(BROWSER_TYPE));
            logger.warn("Setting browser type to: " + browser.name());
        } catch (NullPointerException npe) {
            logger.warn("No JVM arg for browser type is specified, defaulting to: " + browser.name());
        } catch (IllegalArgumentException iae) {
            logger.warn("Empty JVM arg for browser type is specified, defaulting to: " + browser.name());
        }

        return browser;
    }

    public static String getBrowserResolution() {
        return getJMVArg(BROWSER_RESOLUTION, DefaultProperties.DEFAULT_BROWSER_RES);
    }

    public static String getRemoteBrowserVersion() {
        return getJMVArg(REMOTE_BROWSER_VERSION, null);
    }

    public static String getRemotePlatform() {
        return getJMVArg(REMOTE_PLATFORM, null);
    }

    public static boolean isGridExecution() {
        return getJMVArg(IS_GRID_EXECUTION) != null && Boolean.valueOf(getJMVArg(IS_GRID_EXECUTION)) == Boolean.TRUE;
    }

    public static String getHubUrl() {
        return getJMVArg(HUB_URL, null);
    }

    public static String getCustomCapability() {
        return getJMVArg(CUSTOM_CAPABILITY, null);
    }

    public static String getUserAgent() {
        return getJMVArg(USER_AGENT, null);
    }

    public static String getChromeBinaryVersion() {
        return getJMVArg(CHROME_BINARY_VERSION, null);
    }

    public static boolean reuseBrowser() {
        return getJMVArg(REUSE_BROWSER) != null && Boolean.valueOf(getJMVArg(REUSE_BROWSER)) == Boolean.TRUE;
    }

    public static boolean startMaximised() {
        return getJMVArg(START_MAXIMIZED) != null && Boolean.valueOf(getJMVArg(START_MAXIMIZED)) == Boolean.TRUE;
    }

    public static String getImplicitlyWait() {
        return getJMVArg(IMPLICITLY_WAIT);
    }

    public static String getPageLoadTimeout(){
        return getJMVArg(PAGE_LOAD_TIMEOUT);
    }

    public static void setJVMArgument(ArgsEnum argument, final String value) {
        setJMVArg(argument, value);
    }

    /**
     * JVM arguments enum
     */
    enum ArgsEnum {

        ENVIRONMENT("env"),

        BROWSER_TYPE("browser"),
        BROWSER_RESOLUTION("resolution"),
        REUSE_BROWSER("reuse.browser"),
        START_MAXIMIZED("start.maximised"),
        IMPLICITLY_WAIT("impl.wait"),
        PAGE_LOAD_TIMEOUT("page.load.timeout"),

        // Grid
        REMOTE_BROWSER_VERSION("browser.version"),
        REMOTE_PLATFORM("platform"),
        IS_GRID_EXECUTION("grid"),
        HUB_URL("hub"),

        // Custom capabilities
        CUSTOM_CAPABILITY("capability"),

        // Mobile
        USER_AGENT("user.agent"),

        // Binary versions
        CHROME_BINARY_VERSION("chrome.bin.version");

        // Proxy
        //TODO add the proxy stuff

        private final String name;

        ArgsEnum(String name) {
            this.name = name;
        }

        private String getName() {
            return name;
        }

        /**
         * Get JVM argument
         *
         * @param arg
         * @param def
         * @return
         */
        protected static String getJMVArg(ArgsEnum arg, String def) {
            return System.getProperty(arg.getName(), def);
        }

        /**
         * Get JVM argument
         *
         * @param arg JVMArgsFactory enum
         * @return String
         */
        protected static String getJMVArg(ArgsEnum arg) {
            return System.getProperty(arg.getName());
        }

        /**
         * Set JVM argument
         *
         * @param arg   JVMArgsFactory enum
         * @param value String
         */
        protected static void setJMVArg(ArgsEnum arg, String value) {
            System.setProperty(arg.getName(), value);
        }

    }
}
