package com.atanas.kanchev.testframework.core.handlers;

import com.atanas.kanchev.testframework.commons.exceptions.CustomExceptions;
import com.atanas.kanchev.testframework.core.context.ContextFactory;
import com.atanas.kanchev.testframework.core.context.WebContext;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Quotes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.atanas.kanchev.testframework.core.context.ContextFactory.getCurrentContext;

/**
 * @author Atanas Ksnchev
 */
public final class Finder implements IWrapper {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(Finder.class);

    public Finder() {
        goToRootElement();
    }

    public Finder(Class<?> clasz) {
        PageFactory.initElements((WebDriver) ContextFactory.getCurrentContext().getDriver(), clasz);
    }

    public Finder(WebElement element){
        ((WebContext) getCurrentContext()).setCurrentElement(element);
    }

    /**
     * Go to the root page element /html/body
     *
     * @return this
     */
    public Finder goToRootElement() {

        try {
            ((WebContext) getCurrentContext()).setCurrentElement(new LocatorFactory().findElement(By.xpath("/html/body")));
        } catch (NoSuchElementException nsee) {
            logger.error("Unable to return to Root Element - Body");
        }

        return this;
    }

    /**
     * Go to WebElement
     *
     * @param locator String
     * @return this
     */
    public Finder elementBy(By locator) {
        if (locator == null)
            throw new CustomExceptions.Common.NullArgumentException();
        else
            ((WebContext) getCurrentContext()).setCurrentElement(new LocatorFactory().findElement(locator));
        return this;
    }

    /**
     * Finder elements by
     *
     * @param locator locator
     * @return this
     */
    public Finder elementsBy(By locator) {

        if (locator == null)
            throw new CustomExceptions.Common.NullArgumentException();
        else
            ((WebContext) getCurrentContext()).setWebElementsList(new LocatorFactory().findElements(locator));
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
            ((WebContext) getCurrentContext()).setCurrentElement(
                    new LocatorFactory().findElement(By.xpath(".//*/text()[contains(normalize-space(.), " + Quotes.escape(text) + ")]/parent::*")));
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
            ((WebContext) getCurrentContext()).setCurrentElement(
                    new LocatorFactory().findElement(By.xpath(".//*/text()[normalize-space(.) = " + Quotes.escape(text) + "]/parent::*")));
        return this;

    }

}