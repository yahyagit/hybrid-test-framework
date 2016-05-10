package com.atanas.kanchev.testframework.selenium.handlers;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.atanas.kanchev.testframework.selenium.handlers.Locator.getWebContext;

/**
 * WebDriver Wait Interface
 */
interface IWait extends IWrapper {

    // the logger
    Logger logger = LoggerFactory.getLogger(IWait.class);

    default IWait presenceOfElement(Locator locatorType, String locator, long wait) {

        locatorType.presenceOfElement(locatorType, locator, wait);

        return this;
    }

    default IWait visibilityOfElement(Locator locatorType, String locator, long wait) {

        locatorType.visibilityOfElement(locatorType, locator, wait);

        return this;
    }

    default IWait invisibilityOfElement(Locator locatorType, String locator, long wait) {

        locatorType.invisibilityOfElement(locatorType, locator, wait);

        return this;
    }

    default IWait elementToBeClickable(Locator locatorType, String locator, long wait) {

        locatorType.elementToBeClickable(locatorType, locator, wait);

        return this;
    }

    /**
     * Wait For Value To Be Present
     *
     * @param text
     * @return this
     */
    default IWait valueToBePresent(final String text, long wait) {

        try {
            new WebDriverWait(getWebContext().getDriver(), wait)
                    .until(ExpectedConditions.textToBePresentInElementValue(getWebContext().getCurrentElement(), text));
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
    default IWait staleElementByCss(final String cssSelector) {
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
    default IWait textToBePresentInElement(final String text, long wait) {


        try {
            new WebDriverWait(getWebContext().getDriver(), wait).
                    until(ExpectedConditions.textToBePresentInElement(getWebContext().getCurrentElement(), text));
        } catch (TimeoutException e) {
            logger.error("String Not Found: " + text);
            throw new TimeoutException(e.getMessage());
        }
        return this;
    }

    default IWait frameByIdToBeAvailableAndSwitch(String frameId, long wait) {
        try {
            new WebDriverWait(getWebContext().getDriver(), wait)
                    .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameId));

        } catch (NoSuchFrameException nsfe) {
            logger.error("Unable to Switch Frame - No Such Frame");
            throw new NoSuchFrameException(nsfe.getMessage());

        }
        return this;
    }


}