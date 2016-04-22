package com.atanas.kanchev.testframework.selenium.driverfactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JVM Arguments Factory
 *
 * @author Atanas Kanchev
 */
public class JVMArgsFactory {

    private static final Logger logger = LoggerFactory.getLogger(JVMArgsFactory.class);

    private static final BrowserConfig defaultBrowser = BrowserConfig.FIREFOX;

    private static final String ENVIRONMENT = ArgsEnum.getJMVArg(ArgsEnum.ENVIRONMENT);

    private static final String BROWSER_TYPE = ArgsEnum.getJMVArg(ArgsEnum.BROWSER_TYPE);
    private static final String BROWSER_RESOLUTION = ArgsEnum.getJMVArg(ArgsEnum.BROWSER_RESOLUTION);

    // Grid
    private static final String REMOTE_BROWSER_VERSION = ArgsEnum.getJMVArg(ArgsEnum.REMOTE_BROWSER_VERSION);
    private static final String REMOTE_PLATFORM = ArgsEnum.getJMVArg(ArgsEnum.REMOTE_PLATFORM);
    private static final String IS_GRID_EXECUTION = ArgsEnum.getJMVArg(ArgsEnum.IS_GRID_EXECUTION);
    private static final String HUB_URL = ArgsEnum.getJMVArg(ArgsEnum.HUB_URL);

    // Custom capabilities
    private static final String CUSTOM_CAPABILITY = ArgsEnum.getJMVArg(ArgsEnum.CUSTOM_CAPABILITY);

    // Mobile
    private static final String USER_AGENT = ArgsEnum.getJMVArg(ArgsEnum.USER_AGENT);

    // Binary versions
    private static final String CHROME_BINARY_VERSION = ArgsEnum.getJMVArg(ArgsEnum.CHROME_BINARY_VERSION);

    //////////////
    // GETTERS //
    /////////////

    public static String getENVIRONMENT() {
        return ENVIRONMENT;
    }

    public static BrowserConfig getBrowserType() {

        BrowserConfig browser = defaultBrowser;

        try {
            browser = BrowserConfig.valueOf(BROWSER_TYPE.toUpperCase());
            logger.warn("Setting browser type to: " + browser.name());
        } catch (NullPointerException npe) {
            logger.warn("No JVM arg for browser type is specified, defaulting to: " + browser.name());
        } catch (IllegalArgumentException iae) {
            logger.warn("Empty JVM arg for browser type is specified, defaulting to: " + browser.name());
        }

        return browser;
    }

    public static String getBrowserResolution() {
        return BROWSER_RESOLUTION;
    }

    public static String getRemoteBrowserVersion() {
        return REMOTE_BROWSER_VERSION;
    }

    public static String getRemotePlatform() {
        return REMOTE_PLATFORM;
    }

    public static String getIsGridExecution() {
        return IS_GRID_EXECUTION;
    }

    public static String getHubUrl() {
        return HUB_URL;
    }

    public static String getCustomCapability() {
        return CUSTOM_CAPABILITY;
    }

    public static String getUserAgent() {
        return USER_AGENT;
    }

    public static String getChromeBinaryVersion() {
        return CHROME_BINARY_VERSION;
    }

    public static void setJVMArgument(ArgsEnum argument, final String value) {
        ArgsEnum.setJMVArg(argument, value);
    }

    /**
     * JVM arguments enum
     */
    public enum ArgsEnum {

        ENVIRONMENT("env"),

        BROWSER_TYPE("browser"),
        BROWSER_RESOLUTION("resolution"),

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
         * @param arg JVMArgsFactory enum
         * @return String
         */
        private static String getJMVArg(ArgsEnum arg) {
            return System.getProperty(arg.getName());
        }

        /**
         * Set JVM argument
         *
         * @param arg   JVMArgsFactory enum
         * @param value String
         */
        private static void setJMVArg(ArgsEnum arg, String value) {
            System.setProperty(arg.getName(), value);
        }

        }
}
