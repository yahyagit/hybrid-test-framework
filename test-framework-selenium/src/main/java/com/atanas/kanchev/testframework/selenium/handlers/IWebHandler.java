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

import java.net.URL;

import static com.atanas.kanchev.testframework.core.CustomExceptions.Common.NullArgumentException;
import static com.atanas.kanchev.testframework.selenium.context.ContextFactory.getCurrentContext;

/**
 * Web Handler Interface
 */
public interface IWebHandler extends IBaseHandler {

    // the logger
    Logger logger = LoggerFactory.getLogger(IWebHandler.class);

    static WebContext getWebContext() {
        return ((WebContext) getCurrentContext());
    }

    default IWebHandler goToRootElement() {

        try {
            getWebContext().setCurrentElement(getWebContext().getDriver().findElement(By.xpath("/html/body")));
        } catch (NoSuchElementException nsee) {
            logger.error("Unable to return to Root Element - Body");
        }

        return this;
    }

    /**
     * Got to URL
     *
     * @param url valid URL instance
     * @return this
     */
    default IWebHandler goToURL(final URL url) {

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
    default IWebHandler findElementBy(LocatorsFactory locatorType, String locator) {
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
    default IWebHandler findElementsBy(LocatorsFactory locatorType, String locator) {

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
    default IWebHandler goToWebElement(final WebElement element) {

        if (element == null)
            throw new CustomExceptions.Common.NullArgumentException("Null method argument: WebElement element");
        else
            ((WebContext) getCurrentContext()).setCurrentElement(element);

        return this;
    }

    /**
     * Refresh page
     *
     * @return this
     */
    default IWebHandler refresh() throws NullArgumentException {

        ((WebContext) getCurrentContext()).getDriver().navigate().refresh();

        return this;
    }

    default Wait waitFor() {
        return new Wait();
    }


    class Wait implements IWait {

    }


}

