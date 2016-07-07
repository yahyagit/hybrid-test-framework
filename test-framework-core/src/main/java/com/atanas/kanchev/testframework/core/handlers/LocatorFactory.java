package com.atanas.kanchev.testframework.core.handlers;

import com.atanas.kanchev.testframework.core.context.SeleniumContext;
import com.atanas.kanchev.testframework.core.handlers.wrappers.IContext;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by atanas on 26/06/2016.
 */
public class LocatorFactory implements IElementLocator, IContext {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(LocatorFactory.class);

    @Override
    public WebElement findElement(By locator) {
        logger.debug("Locating element using " + locator.toString());
        try {
            return ((SeleniumContext<WebDriver>) context().getCurrentContext()).getDriver().findElement(locator);
        } catch (NoSuchElementException nsee) {
            throw new NoSuchElementException("Unable to locate element using " + locator.toString(), nsee);
        }
    }

    @Override
    public List<WebElement> findElements(By locator) {
        logger.debug("Locating elements using " + locator.toString());
        try {
            List<WebElement> e = ((SeleniumContext<WebDriver>) context().getCurrentContext()).getDriver().findElements(locator);
            int numberOfElementsFound = e.size();
            if (numberOfElementsFound == 0)
                throw new NoSuchElementException("Elements found: " + numberOfElementsFound);
            logger.debug("Elements found: " + numberOfElementsFound);
            return e;
        } catch (NoSuchElementException nsee) {
            throw new NoSuchElementException("Unable to locate any elements using " + locator.toString(), nsee);
        }
    }

    // TODO Fix me - find me a place to live!

//    @Override
//    public boolean isTextPresentInElement(By locator, String text, long wait) {
//
//        ((SeleniumContext) context().getCurrentContext()).setCurrentElement(findElement(locator));
//        try {
//            getWait(wait).until(ExpectedConditions.textToBePresentInElement(((SeleniumContext) context().getCurrentContext()).getCurrentElement(), text));
//            return true;
//        } catch (TimeoutException e) {
//            logger.error("Timeout after waiting for invisibility of element by text:  " + text);
//            throw new TimeoutException(e.getMessage());
//        }
//    }

}

/**
 * Element Locator Interface
 */
interface IElementLocator {

    /**
     * Locate an element by locator @param org.openqa.selenium.By
     *
     * @param locator Locator type
     * @return WebElement element
     */
    WebElement findElement(By locator);

    /**
     * Locate all WebElement by locator @param org.openqa.selenium.By
     *
     * @param locator Locator type
     * @return Collection List<WebElement> having all elements found
     */
    List<WebElement> findElements(By locator);

}