package com.atanas.kanchev.testframework.selenium.handlers;

import com.atanas.kanchev.testframework.selenium.context.WebContext;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.net.URL;

import static com.atanas.kanchev.testframework.core.CustomExceptions.Common.NullArgumentException;
import static com.atanas.kanchev.testframework.selenium.context.ContextFactory.getCurrentContext;

/**
 * Created by atanas on 24/03/2016.
 */
public interface IWebHandler extends IBaseHandler {

    /**
     * Got to URL
     *
     * @param url valid URL address
     * @return this
     */
    default IWebHandler goToURL(final URL url) throws NullArgumentException {

        if (url == null)
            throw new NullArgumentException("Null method argument");
        else
            ((WebContext) getCurrentContext()).getDriver().navigate().to(url);

        return this;
    }

    ////////////////////////
    // ELEMENT NAVIGATION //
    ////////////////////////

    /**
     * Go to element using WebElement
     *
     * @param element WebElement
     * @return this
     */
    default IWebHandler goToWebElement(final WebElement element) throws NullArgumentException {

        if (element == null)
            throw new NullArgumentException("Null method argument ");
        else
            ((WebContext) getCurrentContext()).setCurrentElement(element);

        return this;
    }

    /**
     * Find element by ID
     *
     * @param id String
     * @return this
     * @throws NullArgumentException
     */
    default IWebHandler findElementByID(final String id) throws NullArgumentException {

        if (id == null)
            throw new NullArgumentException("Null method argument ");
        else
            try {
                ((WebContext) getCurrentContext()).setCurrentElement(((WebContext) getCurrentContext()).getDriver().findElement(By.id(id)));
            } catch (NoSuchElementException e) {
                e.printStackTrace();
            }
        return this;
    }

    /**
     * Find elements by ID
     *
     * @param id String
     * @return this
     * @throws NullArgumentException
     */
    default IWebHandler findElementsByID(final String id) throws NullArgumentException {

        if (id == null)
            throw new NullArgumentException("Null method argument ");
        else
            try {
                ((WebContext) getCurrentContext()).setWebElementsList(((WebContext) getCurrentContext()).getDriver().findElements(By.id(id)));
            } catch (NoSuchElementException e) {
                e.printStackTrace();
            }
        return this;
    }

    /**
     * Find element by name
     *
     * @param name String
     * @return this
     * @throws NullArgumentException
     */
    default IWebHandler findElementByName(final String name) throws NullArgumentException {

        if (name == null)
            throw new NullArgumentException("Null method argument ");
        else
            try {
                ((WebContext) getCurrentContext()).setCurrentElement(((WebContext) getCurrentContext()).getDriver().findElement(By.name(name)));
            } catch (NoSuchElementException e) {
                e.printStackTrace();
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
