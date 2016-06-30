package com.atanas.kanchev.testframework.core.handlers;

import com.atanas.kanchev.testframework.commons.exceptions.CustomExceptions;
import com.atanas.kanchev.testframework.core.context.AppiumContext;
import com.atanas.kanchev.testframework.core.context.ContextFactory;
import com.atanas.kanchev.testframework.core.context.WebContext;
import com.atanas.kanchev.testframework.selenium.driver_factory.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Set;

import static com.atanas.kanchev.testframework.core.context.ContextFactory.getCurrentContext;

/**
 * Nav Interface
 */
public final class Navigate implements IWrapper {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(Navigate.class);

    DriverFactory driverFactory;

    public Navigate(DriverFactory driverFactory) {
        this.driverFactory = driverFactory;
    }

    /**
     * Load a new web page in the current browser window. This is done using an HTTP GET operation,
     * and the method will block until the load is complete. This will follow redirects issued
     * either by the server or as a meta-redirect from within the returned HTML. Should a
     * meta-redirect "rest" for any duration of time, it is best to wait until this timeout is over,
     * since should the underlying page change whilst your test is executing the results of future
     * calls against this interface will be against the freshly loaded page.
     *
     * @param url valid URL instance
     * @return this
     */
    public Navigate getPage(final String url) {

        if (url == null)
            throw new CustomExceptions.Common.NullArgumentException("Null method argument: URL");
        else {
            URL address = null;
            try {
                address = new URL(url);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            try {
                getCurrentContext();
            } catch (CustomExceptions.Common.NullArgumentException e) {
                WebContext<WebDriver> context = new WebContext<>(driverFactory.getDriver());
                if (driverFactory.isReuseBrowser())
                    context.setContextReusable(true);
                ContextFactory.addContext(context);
            }

            logger.debug("Navigating to " + url);

            if (getCurrentContext() instanceof AppiumContext)
                INavigateAppium.getPage(address);
            if (getCurrentContext() instanceof WebContext)
                INavigateSelenium.getPage(address);
        }
        return this;

    }

    /**
     * Move back a single "item" in the browser's history.
     *
     * @return this
     */
    public Navigate back() {
        logger.debug("Navigating back");
        if (getCurrentContext() instanceof AppiumContext) INavigateAppium.back();
        if (getCurrentContext() instanceof WebContext) INavigateSelenium.back();
        return this;
    }

    /**
     * Move a single "item" forward in the browser's history. Does nothing if we are on the latest
     * page viewed.
     *
     * @return this
     */
    public Navigate forward() {
        logger.debug("Navigating forward");
        if (getCurrentContext() instanceof AppiumContext) INavigateAppium.forward();
        if (getCurrentContext() instanceof WebContext) INavigateSelenium.forward();
        return this;
    }

    /**
     * Refresh the current page
     *
     * @return this
     */
    public Navigate refresh() {
        logger.debug("Refreshing page");
        if (getCurrentContext() instanceof AppiumContext) INavigateAppium.refresh();
        if (getCurrentContext() instanceof WebContext) INavigateSelenium.refresh();
        return this;
    }


    public Navigate navigateToWindowByPartialTitle(String title) {

        try {
            WebDriver popup;
            Iterator<String> windowIterator = ((WebContext<WebDriver>) getCurrentContext()).getDriver().getWindowHandles().iterator();
            while (windowIterator.hasNext()) {
                String windowHandle = windowIterator.next();
                popup = ((WebContext<WebDriver>) getCurrentContext()).getDriver().switchTo().window(windowHandle);
                if (popup.getTitle().contains(title)) {
                    break;
                }
            }
            logger.debug("navigateToWindowByPartialTitle : Navigated to window by:" + title);

        } catch (NoSuchWindowException nswe) {
            logger.error("navigateToWindowByPartialTitle : No Window found");
            throw new NoSuchWindowException("navigateToWindowByPartialTitle : No Window found");

        }

        return null;
    }

    public Navigate navigateToWindow(String windowIdentifier) {
        try {
            ((WebContext<WebDriver>) getCurrentContext()).getDriver().switchTo().window(windowIdentifier);
        } catch (NoSuchWindowException nswe) {

        }
        return this;
    }

    /**
     * Goto other window. Move to child window from parent or vise-versa
     *
     * @return true, if successful
     */
    public Navigate navigateToOtherWindow() {

        Set<String> handles = ((WebContext<WebDriver>) getCurrentContext()).getDriver().getWindowHandles();
        String currentWindow = ((WebContext<WebDriver>) getCurrentContext()).getDriver().getWindowHandle();
        for (String handle : handles) {
            if (!handle.equals(currentWindow)) {
                ((WebContext<WebDriver>) getCurrentContext()).getDriver().switchTo().window(handle);
                logger.debug("Switched to Other Window");
            }
        }
        return this;
    }

    /**
     * Switch to IFrame following activation and focus.
     *
     * @return true if successful, false if there is no IFrame
     */
    public Navigate navigateToActivateFrame() {
        try {
            ((WebContext<WebDriver>) getCurrentContext()).getDriver().switchTo().frame(0);
            logger.debug("Switched to Active Frame");

        } catch (NoSuchFrameException nsfe) {
            logger.error("Unable to Switch Frame - No Such Frame");

        }
        return this;
    }

    /**
     * Switch to IFrame by Id
     *
     * @param id The Id
     * @return true if successful, false if there is no such IFrame
     */
    public Navigate navigateToFrameById(String id) {
        try {
            ((WebContext<WebDriver>) getCurrentContext()).getDriver().switchTo().frame(id);
            logger.debug("Switched to Active Frame by Id");

        } catch (NoSuchFrameException nsfe) {
            logger.error("Unable to Switch Frame - No Such Frame");

        }
        return this;
    }

    /**
     * Switch to IFrame by className
     *
     * @param
     * @return true if successful, false if there is no such IFrame
     */
    public Navigate navigateToFrameBy(By by) {
        try {
            ((WebContext<WebDriver>) getCurrentContext()).getDriver().switchTo().frame(((WebContext<WebDriver>) getCurrentContext()).getDriver().findElement(by));
            logger.debug("Switched to Active Frame by className");

        } catch (NoSuchFrameException nsfe) {
            logger.error("Unable to Switch Frame - No Such Frame");

        }
        return this;
    }


    public Navigate waitForFrameByIdToBeAvailableAndSwitch(String frameId) {
        try {
            new WebDriverWait(((WebContext<WebDriver>) getCurrentContext()).getDriver(), 5)
                    .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameId));

        } catch (NoSuchFrameException nsfe) {
            logger.error("Unable to Switch Frame - No Such Frame");

        }
        return this;
    }
    /**
     * Switch to parent window from child window or IFrame. Will have no effect
     * if only one window open
     *
     * @return true
     */
    public Navigate returnToDefaultWindow() {
        ((WebContext<WebDriver>) getCurrentContext()).getDriver().switchTo().defaultContent();
        return this;
    }

    public Navigate deleteCookies() {
        try {
            ((WebContext<WebDriver>) getCurrentContext()).getDriver().manage().deleteAllCookies();
        } catch (NoSuchWindowException nsw) {

        }
        return this;
    }

    public Navigate deleteCookie(String cookieName) {
        try {
            ((WebContext<WebDriver>) getCurrentContext()).getDriver().manage().deleteCookieNamed(cookieName);
        } catch (NoSuchWindowException nsw) {

        }
        return this;
    }

    public Navigate setCookie(String cookieName, String cookieValue) {
        try {
            ((WebContext<WebDriver>) getCurrentContext()).getDriver().manage().addCookie(new Cookie(cookieName, cookieValue));
        } catch (NoSuchWindowException nsw) {

        }
        return this;
    }



}