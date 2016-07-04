package com.atanas.kanchev.testframework.core.handlers;

import com.atanas.kanchev.testframework.core.handlers.wrappers.IFind;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Wait
 */
public final class Wait implements IWaitt, IFind {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(Wait.class);

    private final long wait;

    public Wait(long wait) {
        this.wait = wait;
    }

    @Override
    public IWaitt presenceOfElement(By locator) {
        new LocatorFactory().isElementPresent(locator, wait);
        return this;
    }

    @Override
    public IWaitt visibilityOfElement(By locator) {
        new LocatorFactory().isElementVisible(locator, true, wait);
        return this;
    }

    @Override
    public IWaitt invisibilityOfElement(By locator) {
        new LocatorFactory().isElementVisible(locator, false, wait);
        return this;
    }

    @Override
    public IWaitt elementToBeClickable(By locator) {
        new LocatorFactory().isElementClickable(locator, wait);
        return this;
    }

    @Override
    public IWaitt valueToBePresent(By locator, String text) {
        new LocatorFactory().isTextPresentInElementValue(locator, text, wait);
        return this;
    }

    @Override
    public IWaitt staleElementByCss(final String cssSelector) {
        try {
            logger.debug("Trying to findElement element: " + cssSelector + " within waitForStale Method");
            //TODO fix me
            //findElement().elementBy(Locator.CSS_SELECTOR, cssSelector);
        } catch (StaleElementReferenceException e) {
            logger.debug("Element By CSS: " + cssSelector + " Triggered Stale Element Reference");
//            sleep(2000);
        } catch (NoSuchElementException e) {
            logger.debug("Element By CSS: " + cssSelector + " was not found");
//            sleep(2000);
        }
        find().goToRootElement();

        return this;
    }

    @Override
    public IWaitt textToBePresentInElement(By locator, final String text) {
        new LocatorFactory().isTextPresentInElement(locator, text, wait);
        return this;
    }

    @Override
    public IWaitt frameToBeAvailableAndSwitch(By locator) {
        new LocatorFactory().isFrameAvailable(locator, wait);
        return this;
    }

}

/**
 * Wait Factory Interface
 */
interface IWaitt {

    /**
     * Wait for presence of an element
     *
     * @param locator {@link By} locator
     * @return this
     */
    IWaitt presenceOfElement(By locator);

    /**
     * Wait for visibility of and element
     *
     * @param locator {@link By} locator
     * @return this
     */
    IWaitt visibilityOfElement(By locator);

    /**
     * Wait for invisibility of an element
     *
     * @param locator {@link By} locator
     * @return this
     */
    IWaitt invisibilityOfElement(By locator);

    /**
     * Wait for element to be clickable
     *
     * @param locator {@link By} locator
     * @return this
     */
    IWaitt elementToBeClickable(By locator);

    /**
     * Wait for value to be present
     *
     * @param locator {@link By} locator
     * @param text    String
     * @return this
     */
    IWaitt valueToBePresent(By locator, String text);

    /**
     * Wait for staleness element by css selector
     *
     * @param cssSelector String css celector
     * @return this
     */
    IWaitt staleElementByCss(final String cssSelector);

    /**
     * Wait for text to be present in element
     *
     * @param locator {@link By} locator
     * @param text    String
     * @return this
     */
    IWaitt textToBePresentInElement(By locator, final String text);

    /**
     * Wait for frame to become available and switch to it
     *
     * @param locator {@link By} locator
     * @return this
     */
    IWaitt frameToBeAvailableAndSwitch(By locator);

}