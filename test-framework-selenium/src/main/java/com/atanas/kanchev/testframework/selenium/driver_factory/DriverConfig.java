package com.atanas.kanchev.testframework.selenium.driver_factory;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import static com.atanas.kanchev.testframework.selenium.driver_factory.Conf.DefaultProperties.*;

/**
 * @author Atanas Ksnchev
 */
public class DriverConfig {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(DriverConfig.class);

    private final WebDriver driver;

    public DriverConfig(WebDriver driver) {
        this.driver = driver;
        confTimeouts();
        confResolution();
    }

    private WebDriver confTimeouts() {
        logger.debug("Setting implicitly wait to: " + DEFAULT_IMPL_WAIT + " ms.");
        driver.manage().timeouts().implicitlyWait(DEFAULT_IMPL_WAIT, TimeUnit.MILLISECONDS);
        logger.debug("Setting page load timeout to: " + DEFAULT_PAGE_LOAD_TIMEOUT + " ms.");
        driver.manage().timeouts().pageLoadTimeout(DEFAULT_PAGE_LOAD_TIMEOUT, TimeUnit.MILLISECONDS);
        return driver;
    }

    private WebDriver confResolution() {

        if (Conf.JVMArgs.START_MAXIMIZED) {
            logger.debug("Maximising browser window");
            driver.manage().window().maximize();
        } else {
            logger.debug("Setting resolution to: " + DEFAULT_BROWSER_RES_WIDTH + "*" + DEFAULT_BROWSER_RES_HEIGHT);
            driver.manage().window().setSize(new Dimension(DEFAULT_BROWSER_RES_WIDTH, DEFAULT_BROWSER_RES_HEIGHT));
        }

        return driver;
    }

    public WebDriver getDriver() {
        return driver;
    }
}