package com.atanas.kanchev.testframework.selenium.handlers;

import com.atanas.kanchev.testframework.core.exceptions.CustomExceptions;
import com.atanas.kanchev.testframework.selenium.context.WebContext;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Quotes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.atanas.kanchev.testframework.selenium.context.ContextFactory.getCurrentContext;
import static com.atanas.kanchev.testframework.selenium.handlers.LocatorsFactory.XPATH;
import static com.atanas.kanchev.testframework.selenium.handlers.LocatorsFactory.getWebContext;

/**
 * @author Atanas Ksnchev
 */
public interface IFinder extends IWrapper{

    // the logger
    Logger logger = LoggerFactory.getLogger(IFinder.class);

    /**
     * Go to the root page element /html/body
     *
     * @return this
     */
    default IFinder rootElement() {

        try {
            getWebContext().setCurrentElement(getWebContext().getDriver().findElement(By.xpath("/html/body")));
        } catch (NoSuchElementException nsee) {
            logger.error("Unable to return to Root Element - Body");
        }

        return this;
    }

    /**
     * Go to WebElement
     *
     * @param locatorType LocatorsFactory
     * @param locator     String
     * @return this
     */
    default IFinder elementBy(LocatorsFactory locatorType, String locator) {
        if (locatorType == null || locator == null)
            throw new CustomExceptions.Common.NullArgumentException();
        else
            ((WebContext) getCurrentContext()).setCurrentElement(locatorType.locateElement(locatorType, locator));
        return this;
    }

    /**
     * Finder elements by
     *
     * @param locatorType LocatorsFactory
     * @param locator     locator
     * @return this
     */
    default IFinder elementsBy(LocatorsFactory locatorType, String locator) {

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
    default IFinder webElement(final WebElement element) {

        if (element == null)
            throw new CustomExceptions.Common.NullArgumentException("Null method argument: WebElement element");
        else
            ((WebContext) getCurrentContext()).setCurrentElement(element);

        return this;
    }

    /**
     * Finder element containing given text
     *
     * @param text Text to search
     * @return this
     */
    default IFinder containsText(String text) {

        if (text == null)
            throw new CustomExceptions.Common.NullArgumentException();
        else
            ((WebContext) getCurrentContext()).setCurrentElement(XPATH.locateElement(XPATH, (".//*/text()[contains(normalize-space(.), " + Quotes.escape(text) + ")]/parent::*")));
        return this;

    }

    /**
     * Finder element having exact text
     *
     * @param text Text to search
     * @return this
     */
    default IFinder havingText(String text) {

        if (text == null)
            throw new CustomExceptions.Common.NullArgumentException();
        else
            ((WebContext) getCurrentContext()).setCurrentElement(XPATH.locateElement(XPATH, (".//*/text()[normalize-space(.) = " + Quotes.escape(text) + "]/parent::*")));
        return this;

    }
}