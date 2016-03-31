package com.atanas.kanchev.testframework.selenium.handlers;

import com.atanas.kanchev.testframework.selenium.context.WebContext;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.atanas.kanchev.testframework.selenium.context.ContextFactory.getCurrentContext;

/**
 * WebDriver Wait Interface
 */
interface IWait extends IWebHandler {

    // the logger
    Logger logger = LoggerFactory.getLogger(IWait.class);

    static WebContext getWebContext() {
        return ((WebContext) getCurrentContext());
    }

    default IWait presenseOfElement(LocatorsFactory locatorType, String locator, long wait) {

        locatorType.presenceOfElement(locatorType, locator, wait);

        return this;
    }

    default IWait visibilityOfElement(LocatorsFactory locatorType, String locator, long wait) {

        locatorType.visibilityOfElement(locatorType, locator, wait);

        return this;
    }

    default IWait invisibilityOfElement(LocatorsFactory locatorType, String locator, long wait) {

        locatorType.invisibilityOfElement(locatorType, locator, wait);

        return this;
    }

    default IWait elementToBeClickable(LocatorsFactory locatorType, String locator, long wait) {

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
            logger.error("Timeout after waiting for " + getWebContext().getImplicitlyWait()
                    + " s. for invisibility of element by text:  " + text);
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
            findElementBy(LocatorsFactory.CSS_SELECTOR, cssSelector);
        } catch (StaleElementReferenceException e) {
            logger.debug("Element By CSS: " + cssSelector + " Triggered Stale Element Reference");
            sleep(2000);
        } catch (NoSuchElementException e) {
            logger.debug("Element By CSS: " + cssSelector + " was not found");
            sleep(2000);
        }
        goToRootElement();

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