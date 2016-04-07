package com.atanas.kanchev.testframework.selenium.driverfactory;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import static com.atanas.kanchev.testframework.selenium.driverfactory.IRootDriver.OS;

/**
 * WebDriver Configuration Interface
 *
 * @author Atanas Kanchev
 */
interface IWebDriver  {

    // the logger
    Logger logger = LoggerFactory.getLogger(IWebDriver.class);

    //////////////////////
    // DEFAULT METHODS //
    /////////////////////

    /**
     * Get default FirefoxDriver
     *
     * @return new instance of {@link org.openqa.selenium.firefox.FirefoxDriver#FirefoxDriver()}
     */
    default WebDriver getFirefoxDriver() {
        return new FirefoxDriver();
    }

    /**
     * Get FirefoxDriver using pre-configured DesiredCapabilities
     *
     * @param capabilities DesiredCapabilities
     * @return new instance of {@link org.openqa.selenium.firefox.FirefoxDriver#FirefoxDriver()}
     */
    default WebDriver getFirefoxDriver(DesiredCapabilities capabilities) {
        return new FirefoxDriver(capabilities);
    }

    /**
     * Get FirefoxDriver using pre-configured FirefoxProfile
     *
     * @param profile FirefoxProfile
     * @return new instance of {@link org.openqa.selenium.firefox.FirefoxDriver#FirefoxDriver()}
     */
    default WebDriver getFirefoxDriver(FirefoxProfile profile) {
        return new FirefoxDriver(profile);
    }

    /**
     * Get ChromeDriver using pre-configured ChromeDriverService and DesiredCapabilities
     *
     * @param service      ChromeDriverService
     * @param capabilities DesiredCapabilities
     * @return {@link ChromeDriver#ChromeDriver(ChromeDriverService, Capabilities)}
     */
    default WebDriver getChromeDriver(ChromeDriverService service, DesiredCapabilities capabilities) {
        return new ChromeDriver(service, capabilities);
    }

    /**
     * Get ChromeDriver using default ChromeDriverService and pre-configured DesiredCapabilities
     *
     * @param capabilities DesiredCapabilities
     * @return new instance of {@link ChromeDriver#ChromeDriver(ChromeDriverService, Capabilities)}
     */
    default WebDriver getChromeDriver(DesiredCapabilities capabilities) {

        return new ChromeDriver(new BinariesResolver().configureChromeDriverService(), capabilities);
    }

    /**
     * Get SafariDriver using pre-configured DesiredCapabilities
     *
     * @param capabilities DesiredCapabilities
     * @return new instance of {@link SafariDriver#SafariDriver(org.openqa.selenium.Capabilities)}
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
        if (implicitlyWait != 0) {
            logger.debug("Setting implicitly wait to: " + implicitlyWait + " s.");
            driver.manage().timeouts().implicitlyWait(implicitlyWait, TimeUnit.SECONDS);
        }
        if (pageLoadTimeout != 0) {
            logger.debug("Setting page load timeout to: " + pageLoadTimeout + " s.");
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


}