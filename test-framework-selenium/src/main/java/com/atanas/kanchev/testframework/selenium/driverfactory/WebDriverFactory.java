package com.atanas.kanchev.testframework.selenium.driverfactory;

import com.atanas.kanchev.testframework.core.exceptions.CustomExceptions;
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

import static com.atanas.kanchev.testframework.selenium.driverfactory.DefaultProperties.DEFAULT_IMPLICIT_WAIT;
import static com.atanas.kanchev.testframework.selenium.driverfactory.DefaultProperties.DEFAULT_PAGE_LOAD_TIMEOUT;
import static com.atanas.kanchev.testframework.selenium.driverfactory.IRootDriver.OS;

/**
 * WebDriver Factory
 *
 * @author Atanas Kanchev
 */
public final class WebDriverFactory extends AbstractDriver<WebDriver> {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(WebDriverFactory.class);

    private final DesiredCapabilitiesFactory dcf;

    public WebDriverFactory(DesiredCapabilitiesFactory dcf) {
        this.dcf = dcf;
    }

    /**
     * Get FirefoxDriver
     *
     * @return new instance of {@link org.openqa.selenium.firefox.FirefoxDriver#FirefoxDriver()}
     */
    public WebDriverFactory getFirefoxDriver() {

        super.setDriver(new FirefoxDriver(dcf.getDefaultFirefoxCaps()))
                .configureTimeouts(DEFAULT_IMPLICIT_WAIT, DEFAULT_PAGE_LOAD_TIMEOUT);

        return this;
    }

    /**
     * Get FirefoxDriver using pre-configured DesiredCapabilities
     *
     * @param capabilities DesiredCapabilities
     * @return new instance of {@link org.openqa.selenium.firefox.FirefoxDriver#FirefoxDriver()}
     */
    public WebDriverFactory getFirefoxDriver(DesiredCapabilities capabilities) {

        super.setDriver(new FirefoxDriver(capabilities))
                .configureTimeouts(DEFAULT_IMPLICIT_WAIT, DEFAULT_PAGE_LOAD_TIMEOUT);

        return this;
    }

    /**
     * Get FirefoxDriver using pre-configured FirefoxProfile
     *
     * @param profile FirefoxProfile
     * @return new instance of {@link org.openqa.selenium.firefox.FirefoxDriver#FirefoxDriver()}
     */
    public WebDriverFactory getFirefoxDriver(FirefoxProfile profile) {

        super.setDriver(new FirefoxDriver(profile))
                .configureTimeouts(DEFAULT_IMPLICIT_WAIT, DEFAULT_PAGE_LOAD_TIMEOUT);

        return this;
    }

    /**
     * Get ChromeDriver using pre-configured ChromeDriverService and DesiredCapabilities
     *
     * @param service      ChromeDriverService
     * @param capabilities DesiredCapabilities
     * @return {@link ChromeDriver#ChromeDriver(ChromeDriverService, Capabilities)}
     */
    public WebDriverFactory getChromeDriver(ChromeDriverService service, DesiredCapabilities capabilities) {

        super.setDriver(new ChromeDriver(service, capabilities))
                .configureTimeouts(DEFAULT_IMPLICIT_WAIT, DEFAULT_PAGE_LOAD_TIMEOUT);

        return this;
    }

    /**
     * Get ChromeDriver using public ChromeDriverService and pre-configured DesiredCapabilities
     *
     * @param capabilities DesiredCapabilities
     * @return new instance of {@link ChromeDriver#ChromeDriver(ChromeDriverService, Capabilities)}
     */
    public WebDriverFactory getChromeDriver(DesiredCapabilities capabilities) {

        super.setDriver(new ChromeDriver(new BinariesResolver().configureChromeDriverService(), capabilities))
                .configureTimeouts(DEFAULT_IMPLICIT_WAIT, DEFAULT_PAGE_LOAD_TIMEOUT);

        return this;
    }

    /**
     * Get ChromeDriver using public ChromeDriverService and pre-configured DesiredCapabilities
     *
     * @return new instance of {@link ChromeDriver#ChromeDriver(ChromeDriverService, Capabilities)}
     */
    public WebDriverFactory getChromeDriver() {

        super.setDriver(new ChromeDriver(new BinariesResolver().configureChromeDriverService()))
                .configureTimeouts(DEFAULT_IMPLICIT_WAIT, DEFAULT_PAGE_LOAD_TIMEOUT);

        return this;
    }

    /**
     * Get SafariDriver using pre-configured DesiredCapabilities
     *
     * @param capabilities DesiredCapabilities
     * @return new instance of {@link SafariDriver#SafariDriver(org.openqa.selenium.Capabilities)}
     */
    public WebDriverFactory getSafariDriver(DesiredCapabilities capabilities) {

        if (!OS.toLowerCase().contains("mac") || !OS.toLowerCase().contains("windows"))
            throw new RuntimeException("The current platform does not support Safari: " + OS);
        else
            super.setDriver(new SafariDriver(capabilities))
                    .configureTimeouts(DEFAULT_IMPLICIT_WAIT, DEFAULT_PAGE_LOAD_TIMEOUT);

        return this;
    }

    /**
     * Get SafariDriver using default DesiredCapabilities
     *
     * @return new instance of {@link SafariDriver#SafariDriver(org.openqa.selenium.Capabilities)}
     */
    public WebDriverFactory getSafariDriver() {

        if (!OS.toLowerCase().contains("mac") || !OS.toLowerCase().contains("windows"))
            throw new RuntimeException("The current platform does not support Safari: " + OS);
        else
            super.setDriver(new SafariDriver(dcf.getDefaultSafariCaps()))
                    .configureTimeouts(DEFAULT_IMPLICIT_WAIT, DEFAULT_PAGE_LOAD_TIMEOUT);

        return this;
    }

    /**
     * Configure Selenium WebDriver timeouts
     *
     * @param implicitlyWait  implicitly wait in sec
     * @param pageLoadTimeout pageLoad timeout in sec
     * @return configured WebDriver instance
     */
    public WebDriver configureTimeouts(long implicitlyWait, long pageLoadTimeout) {

        if (super.getDriver() == null)
            throw new CustomExceptions.Common.NullArgumentException("Null argument is not permitted");
        if (implicitlyWait != 0) {
            logger.debug("Setting implicitly wait to: " + implicitlyWait + " s.");
            super.getDriver().manage().timeouts().implicitlyWait(implicitlyWait, TimeUnit.SECONDS);
        }
        if (pageLoadTimeout != 0) {
            logger.debug("Setting page load timeout to: " + pageLoadTimeout + " s.");
            super.getDriver().manage().timeouts().pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
        }
        return super.getDriver();
    }

    /////////////////////
    // STATIC MEMBERS //
    ////////////////////

    /**
     * Get endpoint execution IP address
     *
     * @return String IP
     */
    public String getExecutionIP() {

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