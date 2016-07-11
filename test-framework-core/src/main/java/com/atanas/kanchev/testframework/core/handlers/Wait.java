package com.atanas.kanchev.testframework.core.handlers;

import com.atanas.kanchev.testframework.core.context.SeleniumContext;
import com.atanas.kanchev.testframework.core.handlers.wrappers.IContext;
import com.atanas.kanchev.testframework.core.handlers.wrappers.ISelenium;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Wait
 */
public final class Wait implements IWait, IContext {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(Wait.class);

    private final long wait;

    public Wait(long wait) {
        this.wait = wait;
    }

    @Override
    public IWait presenceOfElement(By locator) {
//        new LocatorFactory().isElementPresent(locator, wait);
        return this;
    }

    @Override
    public IWait visibilityOfElement(By locator) {
//        new LocatorFactory().isElementVisible(locator, true, wait);
        return this;
    }

    @Override
    public IWait invisibilityOfElement(By locator) {
//        new LocatorFactory().isElementVisible(locator, false, wait);
        return this;
    }

    @Override
    public IWait elementToBeClickable(By locator) {
//        new LocatorFactory().isElementClickable(locator, wait);
        return this;
    }

    @Override
    public IWait valueToBePresent(By locator, String text) {
//        new LocatorFactory().isTextPresentInElementValue(locator, text, wait);
        return this;
    }

//    @Override
//    public IWait textToBePresentInElement(By locator, final String text) {
//        new LocatorFactory().isTextPresentInElement(locator, text, wait);
//        return this;
//    }

    @Override
    public IWait frameToBeAvailableAndSwitch(By locator) {
//        new LocatorFactory().isFrameAvailable(locator, wait);
        return this;
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
 * Wait Factory Interface
 */
interface IWait extends ISelenium {

    /**
     * Wait for presence of an element
     *
     * @param locator {@link By} locator
     * @return this
     */
    IWait presenceOfElement(By locator);

    /**
     * Wait for visibility of and element
     *
     * @param locator {@link By} locator
     * @return this
     */
    IWait visibilityOfElement(By locator);

    /**
     * Wait for invisibility of an element
     *
     * @param locator {@link By} locator
     * @return this
     */
    IWait invisibilityOfElement(By locator);

    /**
     * Wait for element to be clickable
     *
     * @param locator {@link By} locator
     * @return this
     */
    IWait elementToBeClickable(By locator);

    /**
     * Wait for value to be present
     *
     * @param locator {@link By} locator
     * @param text    String
     * @return this
     */
    IWait valueToBePresent(By locator, String text);

//    /**
//     * Wait for text to be present in element
//     *
//     * @param locator {@link By} locator
//     * @param text    String
//     * @return this
//     */
//    IWait textToBePresentInElement(By locator, final String text);

    /**
     * Wait for frame to become available and switch to it
     *
     * @param locator {@link By} locator
     * @return this
     */
    IWait frameToBeAvailableAndSwitch(By locator);

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

//    boolean isTextPresentInElement(By locator, String text, long wait);

    boolean isFrameAvailable(By locator, long wait);

}