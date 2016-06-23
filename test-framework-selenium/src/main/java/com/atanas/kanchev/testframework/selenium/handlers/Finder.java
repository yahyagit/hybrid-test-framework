package com.atanas.kanchev.testframework.selenium.handlers;

import com.atanas.kanchev.testframework.commons.exceptions.CustomExceptions;
import com.atanas.kanchev.testframework.selenium.context.WebContext;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Quotes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.atanas.kanchev.testframework.core.context.ContextFactory.getCurrentContext;
import static com.atanas.kanchev.testframework.selenium.handlers.Locator.XPATH;
import static com.atanas.kanchev.testframework.selenium.handlers.Locator.getWebContext;

/**
 * @author Atanas Ksnchev
 */
public class Finder implements IWrapper {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(Finder.class);

    /**
     * Go to the root page element /html/body
     *
     * @return this
     */
    public Finder rootElement() {

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
     * @param locatorType Locator
     * @param locator     String
     * @return this
     */
    public Finder elementBy(Locator locatorType, String locator) {
        if (locatorType == null || locator == null)
            throw new CustomExceptions.Common.NullArgumentException();
        else
            ((WebContext) getCurrentContext()).setCurrentElement(locatorType.locateElement(locatorType, locator));
        return this;
    }

    /**
     * Finder elements by
     *
     * @param locatorType Locator
     * @param locator     locator
     * @return this
     */
    public Finder elementsBy(Locator locatorType, String locator) {

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
    public Finder webElement(final WebElement element) {

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
    public Finder containsText(String text) {

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
    public Finder havingText(String text) {

        if (text == null)
            throw new CustomExceptions.Common.NullArgumentException();
        else
            ((WebContext) getCurrentContext()).setCurrentElement(XPATH.locateElement(XPATH, (".//*/text()[normalize-space(.) = " + Quotes.escape(text) + "]/parent::*")));
        return this;

    }
}