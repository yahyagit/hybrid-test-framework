/*
 * Copyright 2016 Atanas Stoychev Kanchev
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.atanas.kanchev.testframework.selenium.handlers;

import com.atanas.kanchev.testframework.commons.wrappers.IContext;
import com.atanas.kanchev.testframework.selenium.context.SeleniumContext;
import com.atanas.kanchev.testframework.selenium.wrappers.ISelenium;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * <p>Wait class.</p>
 *
 * @author Atanas Kanchev
 */
public final class Wait implements IWait, IContext {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(Wait.class);

    private final long wait;

    /**
     * <p>Constructor for Wait.</p>
     *
     * @param wait a long.
     */
    public Wait(long wait) {
        this.wait = wait;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWait presenceOfElement(By locator) {
        //        new LocatorFactory().isElementPresent(locator, wait);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWait visibilityOfElement(By locator) {
        //        new LocatorFactory().isElementVisible(locator, true, wait);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWait invisibilityOfElement(By locator) {
        //        new LocatorFactory().isElementVisible(locator, false, wait);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWait elementToBeClickable(By locator) {
        //        new LocatorFactory().isElementClickable(locator, wait);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWait valueToBePresent(By locator, String text) {
        //        new LocatorFactory().isTextPresentInElementValue(locator, text, wait);
        return this;
    }

    //    @Override
    //    public IWait textToBePresentInElement(By locator, final String text) {
    //        new LocatorFactory().isTextPresentInElement(locator, text, wait);
    //        return this;
    //    }

    /**
     * {@inheritDoc}
     */
    @Override public IWait frameToBeAvailableAndSwitch(By locator) {
        //        new LocatorFactory().isFrameAvailable(locator, wait);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean isElementPresent(By locator, long wait) {

        try {
            getWait(wait).until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            logger
                .error("Timeout after waiting for presence of element by:  " + locator.toString());
            throw new TimeoutException(e.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean isElementVisible(By locator, boolean visibility, long wait) {

        if (visibility)
            try {
                getWait(wait).until(ExpectedConditions.visibilityOfElementLocated(locator));
                return true;
            } catch (TimeoutException e) {
                logger.error(
                    "Timeout after waiting " + wait + " for visibility of element by:  " + locator
                        .toString());
                return false;
            }
        else
            try {
                getWait(wait).until(ExpectedConditions.invisibilityOfElementLocated(locator));
                return true;
            } catch (TimeoutException e) {
                logger.error(
                    "Timeout after waiting " + wait + " for invisibility of element by:  " + locator
                        .toString());
                throw new TimeoutException(e.getMessage());
            }

    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean isElementClickable(By locator, long wait) {

        try {
            getWait(wait).until(ExpectedConditions.elementToBeClickable(locator));
            return true;
        } catch (TimeoutException e) {
            logger.error("Timeout after waiting for element to be clickable located by:  " + locator
                .toString());
            throw new TimeoutException(e.getMessage());
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean isTextPresentInElementValue(By locator, String text, long wait) {
        try {
            getWait(wait).until(ExpectedConditions.textToBePresentInElementValue(locator, text));
            return true;
        } catch (TimeoutException e) {
            logger.error(
                "Timeout after waiting text  " + text + " to be present in element located by "
                    + locator.toString());
            throw new TimeoutException(e.getMessage());
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override public boolean isFrameAvailable(By locator, long wait) {
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
            return new FluentWait<>(
                (((SeleniumContext<WebDriver>) context().getCurrentContext()).getDriver()))
                .withTimeout(duration, SECONDS).pollingEvery(5, SECONDS)
                .ignoring(NoSuchElementException.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}


interface IWait extends ISelenium {

    /**
     * <p>presenceOfElement.</p>
     *
     * @param locator a {@link org.openqa.selenium.By} object.
     * @return a {@link com.atanas.kanchev.testframework.selenium.handlers.IWait} object.
     */
    IWait presenceOfElement(By locator);

    /**
     * <p>visibilityOfElement.</p>
     *
     * @param locator a {@link org.openqa.selenium.By} object.
     * @return a {@link com.atanas.kanchev.testframework.selenium.handlers.IWait} object.
     */
    IWait visibilityOfElement(By locator);

    /**
     * <p>invisibilityOfElement.</p>
     *
     * @param locator a {@link org.openqa.selenium.By} object.
     * @return a {@link com.atanas.kanchev.testframework.selenium.handlers.IWait} object.
     */
    IWait invisibilityOfElement(By locator);

    /**
     * <p>elementToBeClickable.</p>
     *
     * @param locator a {@link org.openqa.selenium.By} object.
     * @return a {@link com.atanas.kanchev.testframework.selenium.handlers.IWait} object.
     */
    IWait elementToBeClickable(By locator);

    /**
     * <p>valueToBePresent.</p>
     *
     * @param locator a {@link org.openqa.selenium.By} object.
     * @param text    a {@link java.lang.String} object.
     * @return a {@link com.atanas.kanchev.testframework.selenium.handlers.IWait} object.
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
     * <p>frameToBeAvailableAndSwitch.</p>
     *
     * @param locator a {@link org.openqa.selenium.By} object.
     * @return a {@link com.atanas.kanchev.testframework.selenium.handlers.IWait} object.
     */
    IWait frameToBeAvailableAndSwitch(By locator);

    /**
     * <p>isElementPresent.</p>
     *
     * @param locator a {@link org.openqa.selenium.By} object.
     * @param wait    a long.
     * @return a boolean.
     */
    boolean isElementPresent(By locator, long wait);

    /**
     * <p>isElementVisible.</p>
     *
     * @param locator    a {@link org.openqa.selenium.By} object.
     * @param visibility a boolean.
     * @param wait       a long.
     * @return a boolean.
     */
    boolean isElementVisible(By locator, boolean visibility, long wait);

    /**
     * <p>isElementClickable.</p>
     *
     * @param locator a {@link org.openqa.selenium.By} object.
     * @param wait    a long.
     * @return a boolean.
     */
    boolean isElementClickable(By locator, long wait);

    /**
     * <p>isTextPresentInElementValue.</p>
     *
     * @param locator a {@link org.openqa.selenium.By} object.
     * @param text    a {@link java.lang.String} object.
     * @param wait    a long.
     * @return a boolean.
     */
    boolean isTextPresentInElementValue(By locator, String text, long wait);

    //    boolean isTextPresentInElement(By locator, String text, long wait);

    /**
     * <p>isFrameAvailable.</p>
     *
     * @param locator a {@link org.openqa.selenium.By} object.
     * @param wait    a long.
     * @return a boolean.
     */
    boolean isFrameAvailable(By locator, long wait);

}
