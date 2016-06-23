package com.atanas.kanchev.testframework.core.handlers;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * WebDriver Wait
 */
public class Wait implements IWrapper {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(Wait.class);

    private final long wait;

    public Wait(long wait) {
        this.wait = wait;
    }


    public Wait presenceOfElement(Locator locatorType, String locator) {

        locatorType.presenceOfElement(locatorType, locator, wait);

        return this;
    }

    public Wait visibilityOfElement(Locator locatorType, String locator) {

        locatorType.visibilityOfElement(locatorType, locator, wait);

        return this;
    }

    public Wait invisibilityOfElement(Locator locatorType, String locator) {

        locatorType.invisibilityOfElement(locatorType, locator, wait);

        return this;
    }

    public Wait elementToBeClickable(Locator locatorType, String locator) {

        locatorType.elementToBeClickable(locatorType, locator, wait);

        return this;
    }

    /**
     * Wait For Value To Be Present
     *
     * @param text
     * @return this
     */
    public Wait valueToBePresent(final String text) {

        try {
            new WebDriverWait(Locator.getWebContext().getDriver(), wait)
                    .until(ExpectedConditions.textToBePresentInElementValue(Locator.getWebContext().getCurrentElement(), text));
        } catch (TimeoutException e) {
            logger.error("Timeout after waiting for invisibility of element by text:  " + text);
            throw new TimeoutException(e.getMessage());
        }
        return this;
    }


    /**
     * Wait For Stale Element By Css
     *
     * @param cssSelector
     */
    public Wait staleElementByCss(final String cssSelector) {
        try {
            logger.debug("Trying to find element: " + cssSelector + " within waitForStale Method");
            find().elementBy(Locator.CSS_SELECTOR, cssSelector);
        } catch (StaleElementReferenceException e) {
            logger.debug("Element By CSS: " + cssSelector + " Triggered Stale Element Reference");
            sleep(2000);
        } catch (NoSuchElementException e) {
            logger.debug("Element By CSS: " + cssSelector + " was not found");
            sleep(2000);
        }
        find().rootElement();

        return this;
    }

    /**
     * Wait For Text To Be Present In Element
     *
     * @param text
     * @return
     */
    public Wait textToBePresentInElement(final String text) {


        try {
            new WebDriverWait(Locator.getWebContext().getDriver(), wait).
                    until(ExpectedConditions.textToBePresentInElement(Locator.getWebContext().getCurrentElement(), text));
        } catch (TimeoutException e) {
            logger.error("String Not Found: " + text);
            throw new TimeoutException(e.getMessage());
        }
        return this;
    }

    public Wait frameByIdToBeAvailableAndSwitch(String frameId) {
        try {
            new WebDriverWait(Locator.getWebContext().getDriver(), wait)
                    .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameId));

        } catch (NoSuchFrameException nsfe) {
            logger.error("Unable to Switch Frame - No Such Frame");
            throw new NoSuchFrameException(nsfe.getMessage());

        }
        return this;
    }


}