package com.atanas.kanchev.testframework.core.handlers;

import org.openqa.selenium.*;
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


    public Wait presenceOfElement(By locator) {
        new LocatorFactory().isElementPresent(locator, wait);
        return this;
    }

    public Wait visibilityOfElement(By locator) {
        new LocatorFactory().isElementVisible(locator, true,  wait);
        return this;
    }

    public Wait invisibilityOfElement(By locator) {
        new LocatorFactory().isElementVisible(locator, false,  wait);
        return this;
    }

    public Wait elementToBeClickable(By locator) {
        new LocatorFactory().isElementClickable(locator, wait);
        return this;
    }

    /**
     * Wait For Value To Be Present
     *
     * @param text
     * @return this
     */
    public Wait valueToBePresent(By locator, String text) {
        new LocatorFactory().isTextPresentInElementValue(locator, text, wait);
        return this;
    }


    /**
     * Wait For Stale Element By Css
     *
     * @param cssSelector
     */
    public Wait staleElementByCss(final String cssSelector) {
        try {
            logger.debug("Trying to findElement element: " + cssSelector + " within waitForStale Method");
            //TODO fix me
            //findElement().elementBy(Locator.CSS_SELECTOR, cssSelector);
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
    public Wait textToBePresentInElement(By locator, final String text) {
        new LocatorFactory().isTextPresentInElement(locator, text, wait);
        return this;
    }

    public Wait frameByIdToBeAvailableAndSwitch(By locator) {
        new LocatorFactory().isFrameAvailable(locator, wait);
        return this;
    }


}