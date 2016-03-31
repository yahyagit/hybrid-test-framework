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
 * Created by atanas on 24/03/2016.
 */
public interface IWebHandler extends IBaseHandler {

    // the logger
    Logger logger = LoggerFactory.getLogger(IWebHandler.class);

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
                ((WebContext) getCurrentContext()).getDriver().navigate().to(url);
            }

            ((WebContext) getCurrentContext()).getDriver().navigate().to(url);
        }


        return this;
    }

    ////////////////////////
    // ELEMENT NAVIGATION //
    ////////////////////////

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
     * Find element by ID
     *
     * @param id String
     * @return this
     */
    default IWebHandler findElementByID(final String id) {

        if (id == null)
            throw new CustomExceptions.Common.NullArgumentException("Null method argument: ID");
        else
            try {
                ((WebContext) getCurrentContext()).setCurrentElement(((WebContext) getCurrentContext()).getDriver().findElement(By.id(id)));
            } catch (NoSuchElementException e) {
                logger.error("Unable to find element by id: " + id);
            }
        return this;
    }

    /**
     * Find elements by ID
     *
     * @param id String
     * @return this
     */
    default IWebHandler findElementsByID(final String id) {

        if (id == null)
            throw new CustomExceptions.Common.NullArgumentException("Null method argument: ID");
        else
            try {
                ((WebContext) getCurrentContext()).setWebElementsList(((WebContext) getCurrentContext()).getDriver().findElements(By.id(id)));
            } catch (NoSuchElementException e) {
                logger.error("Unable to find elements by id: " + id);
            }
        return this;
    }

    /**
     * Find element by name
     *
     * @param name String
     * @return this
     */
    default IWebHandler findElementByName(final String name) {

        if (name == null)
            throw new CustomExceptions.Common.NullArgumentException("Null method argument:  name");
        else
            try {
                ((WebContext) getCurrentContext()).setCurrentElement(((WebContext) getCurrentContext()).getDriver().findElement(By.name(name)));
            } catch (NoSuchElementException e) {
                logger.error("Unable to find elements by : " + name);
            }

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
}
