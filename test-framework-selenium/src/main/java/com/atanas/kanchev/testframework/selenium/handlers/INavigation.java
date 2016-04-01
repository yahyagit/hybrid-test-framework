package com.atanas.kanchev.testframework.selenium.handlers;

import com.atanas.kanchev.testframework.core.CustomExceptions;
import com.atanas.kanchev.testframework.selenium.context.AbstractContext;
import com.atanas.kanchev.testframework.selenium.context.WebContext;
import com.atanas.kanchev.testframework.selenium.driverfactory.BrowserConfig;
import com.atanas.kanchev.testframework.selenium.driverfactory.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

import static com.atanas.kanchev.testframework.selenium.context.ContextFactory.getCurrentContext;

/**
 * Navigate Interface
 */
public interface INavigation extends IWebHandler {

    // the logger
    Logger logger = LoggerFactory.getLogger(INavigation.class);

    default INavigation goToRootElement() {

        try {
            getWebContext().setCurrentElement(getWebContext().getDriver().findElement(By.xpath("/html/body")));
        } catch (NoSuchElementException nsee) {
            logger.error("Unable to return to Root Element - Body");
        }

        return this;
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
    default INavigation getURL(final URL url) {

        if (url == null)
            throw new CustomExceptions.Common.NullArgumentException("Null method argument: URL");
        else {

            try {
                getCurrentContext();
            } catch (CustomExceptions.Common.NullArgumentException e) {
                DriverFactory driverFactory = new DriverFactory();
                driverFactory.setSelectedBrowser(BrowserConfig.CHROME);
                AbstractContext context = new WebContext();
                ((WebContext) context).setDriver(driverFactory.getDriver());
                context.addContext(context);

            }

            ((WebContext) getCurrentContext()).getDriver().navigate().to(url);
        }

        return this;
    }

    /**
     * Overloaded version of {@link INavigation#getURL(java.net.URL))} that makes it easy to pass in a String URL.
     *
     * @param url The URL to load. It is best to use a fully qualified URL
     * @return this
     */
    default INavigation getURL(final String url) {

        URL address = null;
        try {
            address = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        getURL(address);

        return this;
    }
    ////////////////////////
    // ELEMENT NAVIGATION //
    ////////////////////////

    /**
     * Go to WebElement
     *
     * @param locatorType LocatorsFactory
     * @param locator     String
     * @return this
     */
    default INavigation findElementBy(LocatorsFactory locatorType, String locator) {
        if (locatorType == null || locator == null)
            throw new CustomExceptions.Common.NullArgumentException();
        else
            ((WebContext) getCurrentContext()).setCurrentElement(locatorType.locateElement(locatorType, locator));
        return this;
    }

    /**
     * Find elements by
     *
     * @param locatorType LocatorsFactory
     * @param locator     locator
     * @return this
     */
    default INavigation findElementsBy(LocatorsFactory locatorType, String locator) {

        if (locatorType == null || locator == null)
            throw new CustomExceptions.Common.NullArgumentException();
        else
            ((WebContext) getCurrentContext()).setWebElementsList(locatorType.locateElements(locatorType, locator));
        return this;

    }

    /**
     * Go to element using WebElement instance
     *
     * @param element WebElement
     * @return this
     */
    default INavigation goToWebElement(final WebElement element) {

        if (element == null)
            throw new CustomExceptions.Common.NullArgumentException("Null method argument: WebElement element");
        else
            ((WebContext) getCurrentContext()).setCurrentElement(element);

        return this;
    }

    ////////////////////////
    // BROWSER NAVIGATION //
    ////////////////////////

    /**
     * Move back a single "item" in the browser's history.
     *
     * @return this
     */
    default INavigation goBack() {
        ((WebContext) getCurrentContext()).getDriver().navigate().back();
        return this;
    }

    /**
     * Move a single "item" forward in the browser's history. Does nothing if we are on the latest
     * page viewed.
     *
     * @return this
     */
    default INavigation goForward() {
        ((WebContext) getCurrentContext()).getDriver().navigate().forward();
        return this;
    }

    /**
     * Refresh the current page
     *
     * @return this
     */
    default INavigation refresh() {
        ((WebContext) getCurrentContext()).getDriver().navigate().refresh();
        return this;
    }
}
