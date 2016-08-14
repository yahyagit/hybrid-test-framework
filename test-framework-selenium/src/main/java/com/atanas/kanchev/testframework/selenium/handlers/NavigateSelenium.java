package com.atanas.kanchev.testframework.selenium.handlers;

import com.atanas.kanchev.testframework.commons.exceptions.CustomExceptions;
import com.atanas.kanchev.testframework.commons.wrappers.IContext;
import com.atanas.kanchev.testframework.selenium.context.SeleniumContext;
import com.atanas.kanchev.testframework.selenium.driverfactory.DriverFactory;
import com.atanas.kanchev.testframework.selenium.wrappers.ISelenium;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by atanas on 02/07/2016.
 *
 * @author atanas
 * @version $Id: $Id
 */
public class NavigateSelenium implements INavigateSelenium, IContext {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(NavigateSelenium.class);

    private final WebDriver driver;

    /**
     * <p>Constructor for NavigateSelenium.</p>
     *
     * @param driverFactory a {@link com.atanas.kanchev.testframework.selenium.driverfactory.DriverFactory} object.
     */
    public NavigateSelenium(DriverFactory driverFactory) {

        try {
            context().getCurrentContext();
        } catch (CustomExceptions.Common.NullArgumentException e) {
            SeleniumContext<WebDriver> context = new SeleniumContext<>(driverFactory.getDriver());
            if (driverFactory.isReuseBrowser())
                context.setContextReusable(true);
            context().addContext(context);
        }
        driver = (WebDriver) context().getCurrentContext().getDriver();
    }

    /** {@inheritDoc} */
    @Override
    public INavigateSelenium getPage(URL url) {
        if (url == null)
            throw new CustomExceptions.Common.NullArgumentException("Null method argument: URL");
        else {

            logger.debug("Navigating to " + url);
            driver.navigate().to(url);

        }
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public NavigateSelenium getPage(String url) {
        if (url == null)
            throw new CustomExceptions.Common.NullArgumentException("Null method argument: URL");
        else {
            try {
                getPage(new URL(url));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public INavigateSelenium back() {
        logger.debug("Navigating back");
        driver.navigate().back();
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public INavigateSelenium forward() {
        logger.debug("Navigating forward");
        driver.navigate().forward();
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public INavigateSelenium refresh() {
        logger.debug("Refreshing page");
        driver.navigate().refresh();
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public INavigateSelenium navigateToWindowByPartialTitle(String title) {
        try {
            WebDriver popup;
            Iterator<String> windowIterator = driver.getWindowHandles().iterator();
            while (windowIterator.hasNext()) {
                String windowHandle = windowIterator.next();
                popup = driver.switchTo().window(windowHandle);
                if (popup.getTitle().contains(title)) {
                    break;
                }
            }
            logger.debug("navigateToWindowByPartialTitle : Navigated to window by:" + title);

        } catch (NoSuchWindowException nswe) {
            logger.error("navigateToWindowByPartialTitle : No Window found");
            throw new NoSuchWindowException("navigateToWindowByPartialTitle : No Window found");

        }
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public INavigateSelenium navigateToWindow(String windowIdentifier) {
        try {
            driver.switchTo().window(windowIdentifier);
        } catch (NoSuchWindowException nswe) {

        }
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public INavigateSelenium navigateToOtherWindow() {
        Set<String> handles = driver.getWindowHandles();
        String currentWindow = driver.getWindowHandle();
        for (String handle : handles) {
            if (!handle.equals(currentWindow)) {
                driver.switchTo().window(handle);
                logger.debug("Switched to Other Window");
            }
        }
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public INavigateSelenium navigateToActivateFrame() {
        try {
            driver.switchTo().frame(0);
            logger.debug("Switched to Active Frame");

        } catch (NoSuchFrameException nsfe) {
            logger.error("Unable to Switch Frame - No Such Frame");

        }
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public INavigateSelenium navigateToFrameById(String id) {
        try {
            driver.switchTo().frame(id);
            logger.debug("Switched to Active Frame by Id");

        } catch (NoSuchFrameException nsfe) {
            logger.error("Unable to Switch Frame - No Such Frame");

        }
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public INavigateSelenium navigateToFrameBy(By by) {
        try {
            driver.switchTo().frame(driver.findElement(by));
            logger.debug("Switched to Active Frame by className");

        } catch (NoSuchFrameException nsfe) {
            logger.error("Unable to Switch Frame - No Such Frame");

        }
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public INavigateSelenium waitForFrameByIdToBeAvailableAndSwitch(String frameId) {
        try {
            new WebDriverWait(driver, 5)
                    .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameId));

        } catch (NoSuchFrameException nsfe) {
            logger.error("Unable to Switch Frame - No Such Frame");

        }
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public INavigateSelenium returnToDefaultWindow() {
        driver.switchTo().defaultContent();
        return this;
    }


    /** {@inheritDoc} */
    @Override
    public INavigateSelenium deleteCookies() {
        try {
            driver.manage().deleteAllCookies();
        } catch (NoSuchWindowException nsw) {

        }
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public INavigateSelenium deleteCookie(String cookieName) {
        try {
            driver.manage().deleteCookieNamed(cookieName);
        } catch (NoSuchWindowException nsw) {

        }
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public INavigateSelenium setCookie(String cookieName, String cookieValue) {
        try {
            driver.manage().addCookie(new Cookie(cookieName, cookieValue));
        } catch (NoSuchWindowException nsw) {

        }
        return this;
    }
}

interface INavigateSelenium extends ISelenium {

    /**
     * <p>getPage.</p>
     *
     * @param url a {@link java.net.URL} object.
     * @return a {@link com.atanas.kanchev.testframework.selenium.handlers.INavigateSelenium} object.
     */
    INavigateSelenium getPage(final URL url);

    /**
     * <p>getPage.</p>
     *
     * @param url a {@link java.lang.String} object.
     * @return a {@link com.atanas.kanchev.testframework.selenium.handlers.INavigateSelenium} object.
     */
    INavigateSelenium getPage(final String url);

    /**
     * <p>back.</p>
     *
     * @return a {@link com.atanas.kanchev.testframework.selenium.handlers.INavigateSelenium} object.
     */
    INavigateSelenium back();

    /**
     * <p>forward.</p>
     *
     * @return a {@link com.atanas.kanchev.testframework.selenium.handlers.INavigateSelenium} object.
     */
    INavigateSelenium forward();

    /**
     * <p>refresh.</p>
     *
     * @return a {@link com.atanas.kanchev.testframework.selenium.handlers.INavigateSelenium} object.
     */
    INavigateSelenium refresh();

    /**
     * <p>navigateToWindowByPartialTitle.</p>
     *
     * @param title a {@link java.lang.String} object.
     * @return a {@link com.atanas.kanchev.testframework.selenium.handlers.INavigateSelenium} object.
     */
    INavigateSelenium navigateToWindowByPartialTitle(String title);

    /**
     * <p>navigateToWindow.</p>
     *
     * @param windowIdentifier a {@link java.lang.String} object.
     * @return a {@link com.atanas.kanchev.testframework.selenium.handlers.INavigateSelenium} object.
     */
    INavigateSelenium navigateToWindow(String windowIdentifier);

    /**
     * <p>navigateToOtherWindow.</p>
     *
     * @return a {@link com.atanas.kanchev.testframework.selenium.handlers.INavigateSelenium} object.
     */
    INavigateSelenium navigateToOtherWindow();

    /**
     * <p>navigateToActivateFrame.</p>
     *
     * @return a {@link com.atanas.kanchev.testframework.selenium.handlers.INavigateSelenium} object.
     */
    INavigateSelenium navigateToActivateFrame();

    /**
     * <p>navigateToFrameById.</p>
     *
     * @param id a {@link java.lang.String} object.
     * @return a {@link com.atanas.kanchev.testframework.selenium.handlers.INavigateSelenium} object.
     */
    INavigateSelenium navigateToFrameById(String id);

    /**
     * <p>navigateToFrameBy.</p>
     *
     * @param by a {@link org.openqa.selenium.By} object.
     * @return a {@link com.atanas.kanchev.testframework.selenium.handlers.INavigateSelenium} object.
     */
    INavigateSelenium navigateToFrameBy(By by);

    /**
     * <p>waitForFrameByIdToBeAvailableAndSwitch.</p>
     *
     * @param frameId a {@link java.lang.String} object.
     * @return a {@link com.atanas.kanchev.testframework.selenium.handlers.INavigateSelenium} object.
     */
    INavigateSelenium waitForFrameByIdToBeAvailableAndSwitch(String frameId);

    /**
     * <p>returnToDefaultWindow.</p>
     *
     * @return a {@link com.atanas.kanchev.testframework.selenium.handlers.INavigateSelenium} object.
     */
    INavigateSelenium returnToDefaultWindow();

    /**
     * <p>deleteCookies.</p>
     *
     * @return a {@link com.atanas.kanchev.testframework.selenium.handlers.INavigateSelenium} object.
     */
    INavigateSelenium deleteCookies();

    /**
     * <p>deleteCookie.</p>
     *
     * @param cookieName a {@link java.lang.String} object.
     * @return a {@link com.atanas.kanchev.testframework.selenium.handlers.INavigateSelenium} object.
     */
    INavigateSelenium deleteCookie(String cookieName);

    /**
     * <p>setCookie.</p>
     *
     * @param cookieName a {@link java.lang.String} object.
     * @param cookieValue a {@link java.lang.String} object.
     * @return a {@link com.atanas.kanchev.testframework.selenium.handlers.INavigateSelenium} object.
     */
    INavigateSelenium setCookie(String cookieName, String cookieValue);

}
