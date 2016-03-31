package com.atanas.kanchev.testframework.selenium.driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

/**
 * WebDriver Interface
 */
interface IWebDriver extends IRootDriver {

    // the logger
    Logger logger = LoggerFactory.getLogger(IWebDriver.class);

    // JVM args - chrome binary version
    String JVM_CHROME_BINARY_VERSION = "";

    ////////////////////////
    // SELENIUM BINARIES //
    ///////////////////////
    String BIN_ROOT = "src/test/resources/selenium/";
    String CHROME_BIN_WIN_X86_PATH = BIN_ROOT.concat("win/chrome/x86");
    String CHROME_BIN_LINUX_X64_PATH = BIN_ROOT.concat("linux/chrome/x64");
    String CHROME_BIN_LINUX_X86_PATH = BIN_ROOT.concat("linux/chrome/x86");
    String CHROME_BIN_MAC_X86_PATH = BIN_ROOT.concat("mac/chrome/x86");

    // Prop file keys
    String CHROME_BIN_PROP_KEY = "chrome.bin.version";
    // Selenium System property names
    String CHROME_BIN_SYS_PROP_NAME = "webdriver.chrome.driver";

    //////////////////////
    // DEFAULT METHODS //
    /////////////////////

    /**
     * Get default FirefoxDriver
     *
     * @return new FirefoxDriver()
     */
    default WebDriver getFirefoxDriver() {
        return new FirefoxDriver();
    }

    /**
     * Get FirefoxDriver using pre-configured DesiredCapabilities
     *
     * @param capabilities DesiredCapabilities
     * @return new FirefoxDriver(capabilities)
     */
    default WebDriver getFirefoxDriver(DesiredCapabilities capabilities) {
        return new FirefoxDriver(capabilities);
    }

    /**
     * Get FirefoxDriver using pre-configured FirefoxProfile
     *
     * @param profile FirefoxProfile
     * @return new FirefoxDriver(profile)
     */
    default WebDriver getFirefoxDriver(FirefoxProfile profile) {
        return new FirefoxDriver(profile);
    }

    /**
     * Get ChromeDriver using pre-configured ChromeDriverService and DesiredCapabilities
     *
     * @param service      ChromeDriverService
     * @param capabilities DesiredCapabilities
     * @return ChromeDriver(service, capabilities)
     */
    default WebDriver getChromeDriver(ChromeDriverService service, DesiredCapabilities capabilities) {
        return new ChromeDriver(service, capabilities);
    }

    /**
     * Get ChromeDriver using default ChromeDriverService and pre-configured DesiredCapabilities
     *
     * @param capabilities DesiredCapabilities
     * @return new ChromeDriver(service, capabilities)
     */
    default WebDriver getChromeDriver(DesiredCapabilities capabilities) {

        ChromeDriverService.Builder builder = new ChromeDriverService.Builder();
        builder.usingDriverExecutable(new File(setChromeBinProperty()));
        builder.withSilent(true);
        ChromeDriverService service = builder.build();

        return new ChromeDriver(service, capabilities);
    }

    /**
     * Get SafariDriver using pre-configured DesiredCapabilities
     *
     * @param capabilities DesiredCapabilities
     * @return new SafariDriver(capabilities)
     */
    default WebDriver getSafariDriver(DesiredCapabilities capabilities) {

        if (!OS.toLowerCase().contains("mac") || !OS.toLowerCase().contains("windows"))
            throw new RuntimeException("The current platform does not support Safari: " + OS);
        else
            return new SafariDriver(capabilities);
    }

    /**
     * Configure Selenium WebDriver timeouts
     *
     * @param driver          WebDriver instance
     * @param implicitlyWait  implicitly wait in sec
     * @param pageLoadTimeout pageLoad timeout in sec
     * @return configured WebDriver instance
     */
    default WebDriver configureTimeouts(final WebDriver driver, long implicitlyWait, long pageLoadTimeout) {

        if (driver == null) throw new NullPointerException("Null argument is not permitted");
        else if (implicitlyWait != 0) {
            logger.debug("Setting implicitly wait to: " + implicitlyWait + " s.");
            driver.manage().timeouts().implicitlyWait(implicitlyWait, TimeUnit.SECONDS);
        } else if (pageLoadTimeout != 0) {
            logger.debug("Setting page load timeout to: " + implicitlyWait + " s.");
            driver.manage().timeouts().pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
        }

        return driver;
    }

    /////////////////////
    // STATIC MEMBERS //
    ////////////////////

    /**
     * Get endpoint execution IP address
     *
     * @return String IP
     */
    static String getExecutionIP() {

        String ip = null;

        try {
            InetAddress IP = InetAddress.getLocalHost();
            ip = IP.getHostAddress();
        } catch (UnknownHostException e) {
            logger.error(e.getMessage());
        }

        return ip;
    }

    default String setChromeBinProperty() {

        String version;
        if (JVM_CHROME_BINARY_VERSION != null) {
            logger.debug("Using JVM arg to configure Chrome binary version");
            version = JVM_CHROME_BINARY_VERSION;
        } else {
            logger.debug("Using selenium.properties file to configure chrome binary version");
            version = IRootDriver.getSeleniumPropFile().getProperty(CHROME_BIN_PROP_KEY);
            System.out.println(IRootDriver.getSeleniumPropFile().stringPropertyNames());
            if (version == null)
                throw new NullPointerException("Missing property " + CHROME_BIN_PROP_KEY + " in selenium.properties file");
        }

        String path;
        if (ARCHITECTURE.equals("x86_64") || ARCHITECTURE.equals("amd64")) {
            if (OS.toLowerCase().contains("windows"))  // Set x64 ChromeDriver on Windows
                path = CHROME_BIN_WIN_X86_PATH + version + "/chromedriver.exe";
            else if (OS.toLowerCase().contains("linux"))  // Set x64 ChromeDriver on Linux
                path = CHROME_BIN_LINUX_X64_PATH + version + "/chromedriver";
            else if (OS.toLowerCase().contains("mac")) // Set x64 ChromeDriver on mac
                path = CHROME_BIN_MAC_X86_PATH + version + "/chromedriver";
            else
                throw new RuntimeException("Unable to set chrome binary path: Unsupported OS" + OS);

        } else {// The current system architecture is x86
            if (OS.toLowerCase().contains("windows"))  // Set x86 ChromeDriver on Windows
                path = CHROME_BIN_WIN_X86_PATH + version + "/chromedriver.exe";
            else if (OS.toLowerCase().contains("mac"))  // Set x86 ChromeDriver on Mac
                path = CHROME_BIN_MAC_X86_PATH + version + "/chromedriver";
            else if (OS.toLowerCase().contains("linux"))  // Set x86 ChromeDriver on Linux
                path = CHROME_BIN_LINUX_X86_PATH + version + "/chromedriver";
            else
                throw new RuntimeException("Unable to set chrome binary path: Unsupported OS " + OS);
        }

        setWebDriverSystemProperty(CHROME_BIN_SYS_PROP_NAME, path);

        return path;
    }

    /**
     * Set WebDriverFactory System property
     *
     * @param propName  name
     * @param propValue value
     */
    static void setWebDriverSystemProperty(final String propName, final String propValue) {

        if (propName == null || propValue == null)
            throw new NullPointerException("Null argument is not permitted");
        else if (propName.isEmpty() || propValue.isEmpty())
            throw new IllegalArgumentException("Empty argument is not permitted");
        else {
            logger.debug("Configuring System property: " + propName + " with value: " + propValue);
            System.setProperty(propName, propValue);
        }

    }

}