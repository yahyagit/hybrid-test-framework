package com.atanas.kanchev.testframework.core.handlers;

import com.atanas.kanchev.testframework.core.context.SeleniumContext;
import com.atanas.kanchev.testframework.core.handlers.wrappers.IContext;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by atanas on 26/06/2016.
 */
public class LocatorFactory implements IElementLocator, IWait, IContext {

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

    @Override
    public boolean isElementPresent(By locator, long wait) {

        try {
            getWait(wait).until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            logger.error("Timeout after waiting for presence of element by:  " + locator.toString());
            throw new TimeoutException(e.getMessage());
        }
    }

    @Override
    public boolean isElementVisible(By locator, boolean visibility, long wait) {

        if (visibility)
            try {
                getWait(wait).until(ExpectedConditions.visibilityOfElementLocated(locator));
                return true;
            } catch (TimeoutException e) {
                logger.error("Timeout after waiting " + wait + " for visibility of element by:  " + locator.toString());
                return false;
            }
        else try {
            getWait(wait).until(ExpectedConditions.invisibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            logger.error("Timeout after waiting " + wait + " for invisibility of element by:  " + locator.toString());
            throw new TimeoutException(e.getMessage());
        }

    }

    @Override
    public boolean isElementClickable(By locator, long wait) {

        try {
            getWait(wait).until(ExpectedConditions.elementToBeClickable(locator));
            return true;
        } catch (TimeoutException e) {
            logger.error("Timeout after waiting for element to be clickable located by:  " + locator.toString());
            throw new TimeoutException(e.getMessage());
        }

    }

    @Override
    public boolean isTextPresentInElementValue(By locator, String text, long wait) {
        try {
            getWait(wait).until(ExpectedConditions.textToBePresentInElementValue(locator, text));
            return true;
        } catch (TimeoutException e) {
            logger.error("Timeout after waiting text  " + text + " to be present in element located by " + locator.toString());
            throw new TimeoutException(e.getMessage());
        }
    }

    @Override
    public boolean isTextPresentInElement(By locator, String text, long wait) {

        ((SeleniumContext) context().getCurrentContext()).setCurrentElement(findElement(locator));
        try {
            getWait(wait).until(ExpectedConditions.textToBePresentInElement(((SeleniumContext) context().getCurrentContext()).getCurrentElement(), text));
            return true;
        } catch (TimeoutException e) {
            logger.error("Timeout after waiting for invisibility of element by text:  " + text);
            throw new TimeoutException(e.getMessage());
        }
    }

    @Override
    public boolean isFrameAvailable(By locator, long wait) {
        try {
            getWait(wait).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
            return true;
        } catch (NoSuchFrameException e) {
            logger.error("No frame found located by " + locator.toString());
            throw new NoSuchFrameException(e.getMessage());
        }
    }

    private FluentWait<WebDriver> getWait(long duration) {
        try {
            return new FluentWait<>((((SeleniumContext<WebDriver>) context().getCurrentContext()).getDriver()))
                    .withTimeout(duration, SECONDS)
                    .pollingEvery(5, SECONDS)
                    .ignoring(NoSuchElementException.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

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

/**
 * Wait Factory Interface
 */
interface IWait {

    /**
     * Is WebElement located by @param org.openqa.selenium.By present
     *
     * @param locator Locator type
     * @param wait    Wait time in seconds
     * @return boolean result
     */
    boolean isElementPresent(By locator, long wait);

    /**
     * Is WebElement located by @param rg.openqa.selenium.By visible
     *
     * @param locator    Locator type
     * @param visibility Visibility or invisibility
     * @param wait       Wait time in seconds
     * @return boolean result
     */
    boolean isElementVisible(By locator, boolean visibility, long wait);

    /**
     * Is WebElement located by @param org.openqa.selenium.By clickable
     *
     * @param locator Locator type
     * @param wait    Wait time in seconds
     * @return boolean result
     */
    boolean isElementClickable(By locator, long wait);

    /**
     * Wait for given @param value to be present
     *
     * @param locator Locator type
     * @param text    String value
     * @param wait    Wait time in seconds
     * @return boolean result
     */
    boolean isTextPresentInElementValue(By locator, String text, long wait);

    boolean isTextPresentInElement(By locator, String text, long wait);

    boolean isFrameAvailable(By locator, long wait);


}


