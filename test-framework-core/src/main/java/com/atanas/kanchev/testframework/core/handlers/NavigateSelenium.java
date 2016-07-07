package com.atanas.kanchev.testframework.core.handlers;

import com.atanas.kanchev.testframework.commons.exceptions.CustomExceptions;
import com.atanas.kanchev.testframework.core.context.SeleniumContext;
import com.atanas.kanchev.testframework.core.handlers.wrappers.IContext;
import com.atanas.kanchev.testframework.core.handlers.wrappers.IWrapper;
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

/**
 * Created by atanas on 02/07/2016.
 */
public class NavigateSelenium implements IWrapper, INavigate, IContext {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(NavigateSelenium.class);

    private final WebDriver driver;

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

    @Override
    public INavigate getPage(URL url) {
        if (url == null)
            throw new CustomExceptions.Common.NullArgumentException("Null method argument: URL");
        else {

            logger.debug("Navigating to " + url);
            driver.navigate().to(url);

        }
        return this;
    }

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

    @Override
    public INavigate back() {
        logger.debug("Navigating back");
        driver.navigate().back();
        return this;
    }

    @Override
    public INavigate forward() {
        logger.debug("Navigating forward");
        driver.navigate().forward();
        return this;
    }

    @Override
    public INavigate refresh() {
        logger.debug("Refreshing page");
        driver.navigate().refresh();
        return this;
    }

    @Override
    public INavigate navigateToWindowByPartialTitle(String title) {
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

    @Override
    public INavigate navigateToWindow(String windowIdentifier) {
        try {
            driver.switchTo().window(windowIdentifier);
        } catch (NoSuchWindowException nswe) {

        }
        return this;
    }

    @Override
    public INavigate navigateToOtherWindow() {
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

    @Override
    public INavigate navigateToActivateFrame() {
        try {
            driver.switchTo().frame(0);
            logger.debug("Switched to Active Frame");

        } catch (NoSuchFrameException nsfe) {
            logger.error("Unable to Switch Frame - No Such Frame");

        }
        return this;
    }

    @Override
    public INavigate navigateToFrameById(String id) {
        try {
            driver.switchTo().frame(id);
            logger.debug("Switched to Active Frame by Id");

        } catch (NoSuchFrameException nsfe) {
            logger.error("Unable to Switch Frame - No Such Frame");

        }
        return this;
    }

    @Override
    public INavigate navigateToFrameBy(By by) {
        try {
            driver.switchTo().frame(driver.findElement(by));
            logger.debug("Switched to Active Frame by className");

        } catch (NoSuchFrameException nsfe) {
            logger.error("Unable to Switch Frame - No Such Frame");

        }
        return this;
    }

    @Override
    public INavigate waitForFrameByIdToBeAvailableAndSwitch(String frameId) {
        try {
            new WebDriverWait(driver, 5)
                    .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameId));

        } catch (NoSuchFrameException nsfe) {
            logger.error("Unable to Switch Frame - No Such Frame");

        }
        return this;
    }

    @Override
    public INavigate returnToDefaultWindow() {
        driver.switchTo().defaultContent();
        return this;
    }


    @Override
    public INavigate deleteCookies() {
        try {
            driver.manage().deleteAllCookies();
        } catch (NoSuchWindowException nsw) {

        }
        return this;
    }

    @Override
    public INavigate deleteCookie(String cookieName) {
        try {
            driver.manage().deleteCookieNamed(cookieName);
        } catch (NoSuchWindowException nsw) {

        }
        return this;
    }

    @Override
    public INavigate setCookie(String cookieName, String cookieValue) {
        try {
            driver.manage().addCookie(new Cookie(cookieName, cookieValue));
        } catch (NoSuchWindowException nsw) {

        }
        return this;
    }
}

interface INavigate {

    INavigate getPage(final URL url);

    INavigate getPage(final String url);

    INavigate back();

    INavigate forward();

    INavigate refresh();

    INavigate navigateToWindowByPartialTitle(String title);

    INavigate navigateToWindow(String windowIdentifier);

    INavigate navigateToOtherWindow();

    INavigate navigateToActivateFrame();

    INavigate navigateToFrameById(String id);

    INavigate navigateToFrameBy(By by);

    INavigate waitForFrameByIdToBeAvailableAndSwitch(String frameId);

    INavigate returnToDefaultWindow();

    INavigate deleteCookies();

    INavigate deleteCookie(String cookieName);

    INavigate setCookie(String cookieName, String cookieValue);

}
