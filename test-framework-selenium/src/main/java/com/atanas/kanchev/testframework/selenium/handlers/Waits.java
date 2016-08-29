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

import com.atanas.kanchev.testframework.dataservices.dataprovider.properties.PropertyReader;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import static com.atanas.kanchev.testframework.commons.accessors.ContextsAccessor.context;
import static com.atanas.kanchev.testframework.selenium.accessors.SeleniumAccessorsSingleton.currentContextKey;

/**
 * <p>Waits class.</p>
 *
 * @author Atanas Kanchev
 */
public final class Waits implements IWaitsExpectedCondition {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(Waits.class);

    private long duration;
    private long polling;
    private TimeUnit unit;

    public Waits() {
        this.duration = Long.parseLong(PropertyReader.getProp("default", "wait.duration"));
        this.polling = Long.parseLong(PropertyReader.getProp("default", "polling.interval"));
        this.unit = TimeUnit.valueOf(PropertyReader.getProp("default", "time.unit"));
        logger.debug(toString());
    }

    /**
     * <p>Constructor for Waits.</p>
     *
     * @param duration a {@link java.lang.Long}
     */
    public Waits(long duration) {
        this.duration = duration;
        this.polling = Long.parseLong(PropertyReader.getProp("default", "polling.interval"));
        this.unit = TimeUnit.valueOf(PropertyReader.getProp("default", "time.unit"));
        logger.debug(toString());
    }

    /**
     * <p>Constructor for Waits.</p>
     *
     * @param duration a {@link java.lang.Long}
     * @param polling  a {@link java.lang.Long}
     * @param unit     a {@link TimeUnit}
     */
    public Waits(long duration, long polling, TimeUnit unit) {
        this.duration = duration;
        this.polling = polling;
        this.unit = unit;
        logger.debug(toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition titleIs(String title) {
        logger.debug("Waiting " + ExpectedConditions.titleIs(title).toString());
        try {
            waiting(duration).until(ExpectedConditions.titleIs(title));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition titleContains(String title) {
        logger.debug("Waiting " + ExpectedConditions.titleContains(title).toString());
        try {
            waiting(duration).until(ExpectedConditions.titleContains(title));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition urlToBe(String url) {
        logger.debug("Waiting " + ExpectedConditions.urlToBe(url).toString());
        try {
            waiting(duration).until(ExpectedConditions.urlToBe(url));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition urlContains(String fraction) {
        logger.debug("Waiting " + ExpectedConditions.urlContains(fraction).toString());
        try {
            waiting(duration).until(ExpectedConditions.urlContains(fraction));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition urlMatches(String regex) {
        logger.debug("Waiting " + ExpectedConditions.urlMatches(regex).toString());
        try {
            waiting(duration).until(ExpectedConditions.urlMatches(regex));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition presenceOfElementLocated(By locator) {
        logger.debug("Waiting " + ExpectedConditions.presenceOfElementLocated(locator).toString());
        try {
            context().getContext(currentContextKey).setCurrentElement(
                waiting(duration).until(ExpectedConditions.presenceOfElementLocated(locator)));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition visibilityOfElementLocated(By locator) {
        logger
            .debug("Waiting " + ExpectedConditions.visibilityOfElementLocated(locator).toString());
        try {
            context().getContext(currentContextKey).setCurrentElement(
                waiting(duration).until(ExpectedConditions.visibilityOfElementLocated(locator)));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition visibilityOfAllElementsLocatedBy(By locator) {
        logger.debug(
            "Waiting " + ExpectedConditions.visibilityOfAllElementsLocatedBy(locator).toString());
        try {
            context().getContext(currentContextKey).setWebElementsList(waiting(duration)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator)));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition visibilityOfAllElements(List<WebElement> elements) {
        logger.debug("Waiting " + ExpectedConditions.visibilityOfAllElements(elements).toString());
        try {
            context().getContext(currentContextKey).setWebElementsList(
                waiting(duration).until(ExpectedConditions.visibilityOfAllElements(elements)));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition visibilityOf(WebElement element) {
        logger.debug("Waiting " + ExpectedConditions.visibilityOf(element).toString());
        try {
            context().getContext(currentContextKey).setCurrentElement(
                waiting(duration).until(ExpectedConditions.visibilityOf(element)));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition presenceOfAllElementsLocatedBy(By locator) {
        logger.debug(
            "Waiting " + ExpectedConditions.presenceOfAllElementsLocatedBy(locator).toString());
        try {
            context().getContext(currentContextKey).setWebElementsList(waiting(duration)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator)));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition textToBePresentInElement(WebElement element,
        String text) {
        logger.debug(
            "Waiting " + ExpectedConditions.textToBePresentInElement(element, text).toString());
        try {
            waiting(duration).until(ExpectedConditions.textToBePresentInElement(element, text));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition textToBePresentInElementLocated(By locator,
        String text) {
        logger.debug("Waiting " + ExpectedConditions.textToBePresentInElementLocated(locator, text)
            .toString());
        try {
            waiting(duration)
                .until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition textToBePresentInElementValue(WebElement element,
        String text) {
        logger.debug("Waiting " + ExpectedConditions.textToBePresentInElementValue(element, text)
            .toString());
        try {
            waiting(duration)
                .until(ExpectedConditions.textToBePresentInElementValue(element, text));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition textToBePresentInElementValue(By locator,
        String text) {
        logger.debug("Waiting " + ExpectedConditions.textToBePresentInElementValue(locator, text)
            .toString());
        try {
            waiting(duration)
                .until(ExpectedConditions.textToBePresentInElementValue(locator, text));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition frameToBeAvailableAndSwitchToIt(String frameLocator) {
        logger.debug("Waiting " + ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator)
            .toString());
        try {
            waiting(duration)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition frameToBeAvailableAndSwitchToIt(By locator) {
        logger.debug(
            "Waiting " + ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator).toString());
        try {
            waiting(duration).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition frameToBeAvailableAndSwitchToIt(int frameLocator) {
        logger.debug("Waiting " + ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator)
            .toString());
        try {
            waiting(duration)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition frameToBeAvailableAndSwitchToIt(
        WebElement frameLocator) {
        logger.debug("Waiting " + ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator)
            .toString());
        try {
            waiting(duration)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition invisibilityOfElementLocated(By locator) {
        logger.debug(
            "Waiting " + ExpectedConditions.invisibilityOfElementLocated(locator).toString());
        try {
            waiting(duration).until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition invisibilityOfElementWithText(By locator,
        String text) {
        logger.debug("Waiting " + ExpectedConditions.invisibilityOfElementWithText(locator, text)
            .toString());
        try {
            waiting(duration)
                .until(ExpectedConditions.invisibilityOfElementWithText(locator, text));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition elementToBeClickable(By locator) {
        logger.debug("Waiting " + ExpectedConditions.elementToBeClickable(locator).toString());
        try {
            context().getContext(currentContextKey).setCurrentElement(
                waiting(duration).until(ExpectedConditions.elementToBeClickable(locator)));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition elementToBeClickable(WebElement element) {
        logger.debug("Waiting " + ExpectedConditions.elementToBeClickable(element).toString());
        try {
            context().getContext(currentContextKey).setCurrentElement(
                waiting(duration).until(ExpectedConditions.elementToBeClickable(element)));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition stalenessOf(WebElement element) {
        logger.debug("Waiting " + ExpectedConditions.stalenessOf(element).toString());
        try {
            waiting(duration).until(ExpectedConditions.stalenessOf(element));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public <T> IWaitsExpectedCondition refreshed(ExpectedCondition<T> condition) {
        logger.debug("Waiting " + ExpectedConditions.refreshed(condition).toString());
        try {
            waiting(duration).until(ExpectedConditions.refreshed(condition));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition elementToBeSelected(WebElement element) {
        logger.debug("Waiting " + ExpectedConditions.elementToBeSelected(element).toString());
        try {
            waiting(duration).until(ExpectedConditions.elementToBeSelected(element));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition elementSelectionStateToBe(WebElement element,
        boolean selected) {
        logger.debug("Waiting " + ExpectedConditions.elementSelectionStateToBe(element, selected)
            .toString());
        try {
            waiting(duration)
                .until(ExpectedConditions.elementSelectionStateToBe(element, selected));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition elementToBeSelected(By locator) {
        logger.debug("Waiting " + ExpectedConditions.elementToBeSelected(locator).toString());
        try {
            waiting(duration).until(ExpectedConditions.elementToBeSelected(locator));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition elementSelectionStateToBe(By locator,
        boolean selected) {
        logger.debug("Waiting " + ExpectedConditions.elementSelectionStateToBe(locator, selected)
            .toString());
        try {
            waiting(duration)
                .until(ExpectedConditions.elementSelectionStateToBe(locator, selected));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition alertIsPresent() {
        logger.debug("Waiting " + ExpectedConditions.alertIsPresent().toString());
        try {
            waiting(duration).until(ExpectedConditions.alertIsPresent());
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition numberOfWindowsToBe(int expectedNumberOfWindows) {
        logger.debug("Waiting " + ExpectedConditions.numberOfWindowsToBe(expectedNumberOfWindows)
            .toString());
        try {
            waiting(duration)
                .until(ExpectedConditions.numberOfWindowsToBe(expectedNumberOfWindows));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition not(ExpectedCondition<?> condition) {
        logger.debug("Waiting " + ExpectedConditions.not(condition).toString());
        try {
            waiting(duration).until(ExpectedConditions.not(condition));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition attributeToBe(By locator, String attribute,
        String value) {
        logger.debug(
            "Waiting " + ExpectedConditions.attributeToBe(locator, attribute, value).toString());
        try {
            waiting(duration).until(ExpectedConditions.attributeToBe(locator, attribute, value));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition textToBe(By locator, String value) {
        logger.debug("Waiting " + ExpectedConditions.textToBe(locator, value).toString());
        try {
            waiting(duration).until(ExpectedConditions.textToBe(locator, value));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition textMatches(By locator, Pattern pattern) {
        logger.debug("Waiting " + ExpectedConditions.textMatches(locator, pattern).toString());
        try {
            waiting(duration).until(ExpectedConditions.textMatches(locator, pattern));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition numberOfElementsToBeMoreThan(By locator,
        Integer number) {
        logger.debug("Waiting " + ExpectedConditions.numberOfElementsToBeMoreThan(locator, number)
            .toString());
        try {
            context().getContext(currentContextKey).setWebElementsList(waiting(duration)
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, number)));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition numberOfElementsToBeLessThan(By locator,
        Integer number) {
        logger.debug("Waiting " + ExpectedConditions.numberOfElementsToBeLessThan(locator, number)
            .toString());
        try {
            context().getContext(currentContextKey).setWebElementsList(waiting(duration)
                .until(ExpectedConditions.numberOfElementsToBeLessThan(locator, number)));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition numberOfElementsToBe(By locator, Integer number) {
        logger.debug(
            "Waiting " + ExpectedConditions.numberOfElementsToBe(locator, number).toString());
        try {
            context().getContext(currentContextKey).setWebElementsList(
                waiting(duration).until(ExpectedConditions.numberOfElementsToBe(locator, number)));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition attributeToBe(WebElement element, String attribute,
        String value) {
        logger.debug(
            "Waiting " + ExpectedConditions.attributeToBe(element, attribute, value).toString());
        try {
            waiting(duration).until(ExpectedConditions.attributeToBe(element, attribute, value));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition attributeContains(WebElement element, String attribute,
        String value) {
        logger.debug("Waiting " + ExpectedConditions.attributeContains(element, attribute, value)
            .toString());
        try {
            waiting(duration)
                .until(ExpectedConditions.attributeContains(element, attribute, value));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition attributeContains(By locator, String attribute,
        String value) {
        logger.debug("Waiting " + ExpectedConditions.attributeContains(locator, attribute, value)
            .toString());
        try {
            waiting(duration)
                .until(ExpectedConditions.attributeContains(locator, attribute, value));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition attributeToBeNotEmpty(WebElement element,
        String attribute) {
        logger.debug(
            "Waiting " + ExpectedConditions.attributeToBeNotEmpty(element, attribute).toString());
        try {
            waiting(duration).until(ExpectedConditions.attributeToBeNotEmpty(element, attribute));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition visibilityOfNestedElementsLocatedBy(By locator,
        By sub_locator) {
        logger.debug("Waiting " + ExpectedConditions
            .visibilityOfNestedElementsLocatedBy(locator, sub_locator).toString());
        try {
            context().getContext(currentContextKey).setWebElementsList(waiting(duration)
                .until(
                    ExpectedConditions.visibilityOfNestedElementsLocatedBy(locator, sub_locator)));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition visibilityOfNestedElementsLocatedBy(WebElement element,
        By sub_locator) {
        logger.debug("Waiting " + ExpectedConditions
            .visibilityOfNestedElementsLocatedBy(element, sub_locator).toString());
        try {
            context().getContext(currentContextKey).setWebElementsList(waiting(duration)
                .until(
                    ExpectedConditions.visibilityOfNestedElementsLocatedBy(element, sub_locator)));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition presenceOfNestedElementLocatedBy(By locator,
        By sub_locator) {
        logger.debug(
            "Waiting " + ExpectedConditions.presenceOfNestedElementLocatedBy(locator, sub_locator)
                .toString());
        try {
            context().getContext(currentContextKey).setCurrentElement(waiting(duration)
                .until(ExpectedConditions.presenceOfNestedElementLocatedBy(locator, sub_locator)));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition presenceOfNestedElementLocatedBy(WebElement element,
        By sub_locator) {
        logger.debug(
            "Waiting " + ExpectedConditions.presenceOfNestedElementLocatedBy(element, sub_locator)
                .toString());
        try {
            context().getContext(currentContextKey).setCurrentElement(waiting(duration)
                .until(ExpectedConditions.presenceOfNestedElementLocatedBy(element, sub_locator)));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition presenceOfNestedElementsLocatedBy(By locator,
        By sub_locator) {
        logger.debug(
            "Waiting " + ExpectedConditions.presenceOfNestedElementsLocatedBy(locator, sub_locator)
                .toString());
        try {
            context().getContext(currentContextKey).setWebElementsList(waiting(duration)
                .until(ExpectedConditions.presenceOfNestedElementsLocatedBy(locator, sub_locator)));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition invisibilityOfAllElements(List<WebElement> elements) {
        logger
            .debug("Waiting " + ExpectedConditions.invisibilityOfAllElements(elements).toString());
        try {
            waiting(duration).until(ExpectedConditions.invisibilityOfAllElements(elements));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition or(ExpectedCondition<?>... conditions) {
        logger.debug("Waiting " + ExpectedConditions.or(conditions).toString());
        try {
            waiting(duration).until(ExpectedConditions.or(conditions));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition and(ExpectedCondition<?>... conditions) {
        logger.debug("Waiting " + ExpectedConditions.and(conditions).toString());
        try {
            waiting(duration).until(ExpectedConditions.and(conditions));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition javaScriptThrowsNoExceptions(String javaScript) {
        logger.debug(
            "Waiting " + ExpectedConditions.javaScriptThrowsNoExceptions(javaScript).toString());
        try {
            waiting(duration).until(ExpectedConditions.javaScriptThrowsNoExceptions(javaScript));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IWaitsExpectedCondition jsReturnsValue(String javaScript) {
        logger.debug("Waiting " + ExpectedConditions.jsReturnsValue(javaScript).toString());
        try {
            waiting(duration).until(ExpectedConditions.jsReturnsValue(javaScript));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return this;
    }

    /**
     * Hard wait for fixed time period
     *
     * @param waitInSec wait time in seconds
     * @return this
     */
    public Waits hardWait(int waitInSec) throws InterruptedException {
        Thread.sleep(waitInSec);
        return this;
    }

    private Wait<WebDriver> waiting(long duration) {
        return new FluentWait<>(
            context().getContext(currentContextKey).getDriver())
            .withTimeout(duration, unit).pollingEvery(polling, unit)
            .ignoring(NoSuchElementException.class).ignoring(NoSuchFrameException.class)
            .ignoring(StaleElementReferenceException.class)
            .ignoring(ElementNotVisibleException.class);

    }

    @Override public String toString() {
        return "Waits {" + "duration=" + duration + ", polling=" + polling + ", unit=" + unit + '}';
    }
}


/**
 * Implementation of {@link org.openqa.selenium.support.ui.ExpectedConditions}
 * Models a condition that might reasonably be expected to eventually evaluate to something that is
 * neither null nor false. Examples would include determining if a web page has loaded or that an
 * element is visible.
 * <p>
 * Note that it is expected that ExpectedConditions are idempotent. They will be called in a loop by
 * the {@link WebDriverWait} and any modification of the state of the application under test may
 * have unexpected side-effects.
 */
interface IWaitsExpectedCondition {

    /**
     * An expectation for checking the title of a page.
     *
     * @param title the expected title, which must be an exact match
     * @return true when the title matches, false otherwise
     */
    IWaitsExpectedCondition titleIs(final String title);

    /**
     * An expectation for checking that the title contains a case-sensitive substring
     *
     * @param title the fragment of title expected
     * @return true when the title matches, false otherwise
     */
    IWaitsExpectedCondition titleContains(final String title);

    /**
     * An expectation for the URL of the current page to be a specific url.
     *
     * @param url the url that the page should be on
     * @return <code>true</code> when the URL is what it should be
     */
    IWaitsExpectedCondition urlToBe(final String url);

    /**
     * An expectation for the URL of the current page to contain specific text.
     *
     * @param fraction the fraction of the url that the page should be on
     * @return <code>true</code> when the URL contains the text
     */
    IWaitsExpectedCondition urlContains(final String fraction);

    /**
     * Expectation for the URL to match a specific regular expression
     *
     * @param regex the regular expression that the URL should match
     * @return <code>true</code> if the URL matches the specified regular expression
     */
    IWaitsExpectedCondition urlMatches(final String regex);

    /**
     * An expectation for checking that an element is present on the DOM of a page. This does not
     * necessarily mean that the element is visible.
     *
     * @param locator used to find the element
     * @return the WebElement once it is located
     */
    IWaitsExpectedCondition presenceOfElementLocated(final By locator);

    /**
     * An expectation for checking that an element is present on the DOM of a page and visible.
     * Visibility means that the element is not only displayed but also has a height and width that is
     * greater than 0.
     *
     * @param locator used to find the element
     * @return the WebElement once it is located and visible
     */
    IWaitsExpectedCondition visibilityOfElementLocated(final By locator);

    /**
     * An expectation for checking that all elements present on the web page that match the locator
     * are visible. Visibility means that the elements are not only displayed but also have a height
     * and width that is greater than 0.
     *
     * @param locator used to find the element
     * @return the list of WebElements once they are located
     */
    IWaitsExpectedCondition visibilityOfAllElementsLocatedBy(final By locator);

    /**
     * An expectation for checking that all elements present on the web page that match the locator
     * are visible. Visibility means that the elements are not only displayed but also have a height
     * and width that is greater than 0.
     *
     * @param elements list of WebElements
     * @return the list of WebElements once they are located
     */
    IWaitsExpectedCondition visibilityOfAllElements(final List<WebElement> elements);

    /**
     * An expectation for checking that an element, known to be present on the DOM of a page, is
     * visible. Visibility means that the element is not only displayed but also has a height and
     * width that is greater than 0.
     *
     * @param element the WebElement
     * @return the (same) WebElement once it is visible
     */
    IWaitsExpectedCondition visibilityOf(final WebElement element);

    /**
     * An expectation for checking that there is at least one element present on a web page.
     *
     * @param locator used to find the element
     * @return the list of WebElements once they are located
     */
    IWaitsExpectedCondition presenceOfAllElementsLocatedBy(final By locator);

    /**
     * An expectation for checking if the given text is present in the specified element.
     *
     * @param element the WebElement
     * @param text    to be present in the element
     * @return true once the element contains the given text
     */
    IWaitsExpectedCondition textToBePresentInElement(final WebElement element, final String text);

    /**
     * An expectation for checking if the given text is present in the element that matches the given
     * locator.
     *
     * @param locator used to find the element
     * @param text    to be present in the element found by the locator
     * @return true once the first element located by locator contains the given text
     */
    IWaitsExpectedCondition textToBePresentInElementLocated(final By locator, final String text);

    /**
     * An expectation for checking if the given text is present in the specified elements value
     * attribute.
     *
     * @param element the WebElement
     * @param text    to be present in the element's value attribute
     * @return true once the element's value attribute contains the given text
     */
    IWaitsExpectedCondition textToBePresentInElementValue(final WebElement element,
        final String text);

    /**
     * An expectation for checking if the given text is present in the specified elements value
     * attribute.
     *
     * @param locator used to find the element
     * @param text    to be present in the value attribute of the element found by the locator
     * @return true once the value attribute of the first element located by locator contains the
     * given text
     */
    IWaitsExpectedCondition textToBePresentInElementValue(final By locator, final String text);

    /**
     * An expectation for checking whether the given frame is available to switch to. <p> If the frame
     * is available it switches the given driver to the specified frame.
     *
     * @param frameLocator used to find the frame (id or name)
     * @return WebDriver omniaDriver after frame has been switched
     */
    IWaitsExpectedCondition frameToBeAvailableAndSwitchToIt(final String frameLocator);

    /**
     * An expectation for checking whether the given frame is available to switch to. <p> If the frame
     * is available it switches the given driver to the specified frame.
     *
     * @param locator used to find the frame
     * @return WebDriver omniaDriver after frame has been switched
     */
    IWaitsExpectedCondition frameToBeAvailableAndSwitchToIt(final By locator);

    /**
     * An expectation for checking whether the given frame is available to switch to. <p> If the frame
     * is available it switches the given driver to the specified frameIndex.
     *
     * @param frameLocator used to find the frame (index)
     * @return WebDriver omniaDriver after frame has been switched
     */
    IWaitsExpectedCondition frameToBeAvailableAndSwitchToIt(final int frameLocator);

    /**
     * An expectation for checking whether the given frame is available to switch to. <p> If the frame
     * is available it switches the given driver to the specified webelement.
     *
     * @param frameLocator used to find the frame (webelement)
     * @return WebDriver omniaDriver after frame has been switched
     */
    IWaitsExpectedCondition frameToBeAvailableAndSwitchToIt(final WebElement frameLocator);

    /**
     * An expectation for checking that an element is either invisible or not present on the DOM.
     *
     * @param locator used to find the element
     * @return true if the element is not displayed or the element doesn't exist or stale element
     */
    IWaitsExpectedCondition invisibilityOfElementLocated(final By locator);

    /**
     * An expectation for checking that an element with text is either invisible or not present on the
     * DOM.
     *
     * @param locator used to find the element
     * @param text    of the element
     * @return true if no such element, stale element or displayed text not equal that provided
     */
    IWaitsExpectedCondition invisibilityOfElementWithText(final By locator, final String text);

    /**
     * An expectation for checking an element is visible and enabled such that you can click it.
     *
     * @param locator used to find the element
     * @return the WebElement once it is located and clickable (visible and enabled)
     */
    IWaitsExpectedCondition elementToBeClickable(final By locator);

    /**
     * An expectation for checking an element is visible and enabled such that you can click it.
     *
     * @param element the WebElement
     * @return the (same) WebElement once it is clickable (visible and enabled)
     */
    IWaitsExpectedCondition elementToBeClickable(final WebElement element);

    /**
     * Wait until an element is no longer attached to the DOM.
     *
     * @param element The element to wait for.
     * @return false is the element is still attached to the DOM, true otherwise.
     */
    IWaitsExpectedCondition stalenessOf(final WebElement element);

    /**
     * Wrapper for a condition, which allows for elements to update by redrawing.
     * <p>
     * This works around the problem of conditions which have two parts: find an element and then
     * check for some condition on it. For these conditions it is possible that an element is located
     * and then subsequently it is redrawn on the client. When this happens a {@link
     * StaleElementReferenceException} is thrown when the second part of the condition is checked.
     *
     * @param condition ExpectedCondition to wrap
     * @param <T>       return type of the condition provided
     * @return the result of the provided condition
     */
    <T> IWaitsExpectedCondition refreshed(final ExpectedCondition<T> condition);

    /**
     * An expectation for checking if the given element is selected.
     *
     * @param element WebElement to be selected
     * @return true once the element is selected
     */
    IWaitsExpectedCondition elementToBeSelected(final WebElement element);

    /**
     * An expectation for checking if the given element is selected.
     *
     * @param element  WebElement to be selected
     * @param selected boolean state of the selection state of the element
     * @return true once the element's selection stated is that of selected
     */
    IWaitsExpectedCondition elementSelectionStateToBe(final WebElement element,
        final boolean selected);

    IWaitsExpectedCondition elementToBeSelected(final By locator);

    IWaitsExpectedCondition elementSelectionStateToBe(final By locator, final boolean selected);

    IWaitsExpectedCondition alertIsPresent();

    IWaitsExpectedCondition numberOfWindowsToBe(final int expectedNumberOfWindows);

    /**
     * An expectation with the logical opposite condition of the given condition.
     * <p>
     * Note that if the Condition your are inverting throws an exception that is caught by the Ignored
     * Exceptions, the inversion will not take place and lead to confusing results.
     *
     * @param condition ExpectedCondition to be inverted
     * @return true once the condition is satisfied
     */
    IWaitsExpectedCondition not(final ExpectedCondition<?> condition);

    /**
     * An expectation for checking WebElement with given locator has attribute with a specific value
     *
     * @param locator   used to find the element
     * @param attribute used to define css or html attribute
     * @param value     used as expected attribute value
     * @return Boolean true when element has css or html attribute with the value
     */
    IWaitsExpectedCondition attributeToBe(final By locator, final String attribute,
        final String value);

    /**
     * An expectation for checking WebElement with given locator has specific text
     *
     * @param locator used to find the element
     * @param value   used as expected text
     * @return Boolean true when element has text value equal to @value
     */
    IWaitsExpectedCondition textToBe(final By locator, final String value);

    /**
     * An expectation for checking WebElement with given locator has text with a value as a part of
     * it
     *
     * @param locator used to find the element
     * @param pattern used as expected text matcher pattern
     * @return Boolean true when element has text value containing @value
     */
    IWaitsExpectedCondition textMatches(final By locator, final Pattern pattern);

    /**
     * An expectation for checking number of WebElements with given locator
     *
     * @param locator used to find the element
     * @param number  user to define exact number of elements
     * @return Boolean true when size of elements list is equal to defined
     */
    IWaitsExpectedCondition numberOfElementsToBeMoreThan(final By locator, final Integer number);

    /**
     * An expectation for checking number of WebElements with given locator being less than defined
     * number
     *
     * @param locator used to find the element
     * @param number  user to define maximum number of elements
     * @return Boolean true when size of elements list is less than defined
     */
    IWaitsExpectedCondition numberOfElementsToBeLessThan(final By locator, final Integer number);

    /**
     * An expectation for checking number of WebElements with given locator
     *
     * @param locator used to find the element
     * @param number  user to define number of elements
     * @return Boolean true when size of elements list is equal to defined
     */
    IWaitsExpectedCondition numberOfElementsToBe(final By locator, final Integer number);

    /**
     * An expectation for checking given WebElement has attribute with a specific value
     *
     * @param element   used to check it's parameters
     * @param attribute used to define css or html attribute
     * @param value     used as expected attribute value
     * @return Boolean true when element has css or html attribute with the value
     */
    IWaitsExpectedCondition attributeToBe(final WebElement element, final String attribute,
        final String value);

    /**
     * An expectation for checking WebElement with given locator has attribute which contains specific
     * value
     *
     * @param element   used to check it's parameters
     * @param attribute used to define css or html attribute
     * @param value     used as expected attribute value
     * @return Boolean true when element has css or html attribute which contains the value
     */
    IWaitsExpectedCondition attributeContains(final WebElement element, final String attribute,
        final String value);

    /**
     * An expectation for checking WebElement with given locator has attribute which contains specific
     * value
     *
     * @param locator   used to define WebElement to check it's parameters
     * @param attribute used to define css or html attribute
     * @param value     used as expected attribute value
     * @return Boolean true when element has css or html attribute which contains the value
     */
    IWaitsExpectedCondition attributeContains(final By locator, final String attribute,
        final String value);

    /**
     * An expectation for checking WebElement any non empty value for given attribute
     *
     * @param element   used to check it's parameters
     * @param attribute used to define css or html attribute
     * @return Boolean true when element has css or html attribute with non empty value
     */
    IWaitsExpectedCondition attributeToBeNotEmpty(final WebElement element, final String attribute);

    /**
     * An expectation for checking child WebElement as a part of parent element to be visible
     *
     * @param locator     used to check parent element. For example table with locator
     *                    By.xpath("//table")
     * @param sub_locator used to find child element. For example td By.xpath("./tr/td")
     * @return visible nested element
     */
    IWaitsExpectedCondition visibilityOfNestedElementsLocatedBy(final By locator,
        final By sub_locator);

    /**
     * An expectation for checking child WebElement as a part of parent element to be visible
     *
     * @param element     used as parent element. For example table with locator By.xpath("//table")
     * @param sub_locator used to find child element. For example td By.xpath("./tr/td")
     * @return visible subelement
     */
    IWaitsExpectedCondition visibilityOfNestedElementsLocatedBy(final WebElement element,
        final By sub_locator);

    /**
     * An expectation for checking child WebElement as a part of parent element to present
     *
     * @param locator     used to check parent element. For example table with locator
     *                    By.xpath("//table")
     * @param sub_locator used to find child element. For example td By.xpath("./tr/td")
     * @return subelement
     */
    IWaitsExpectedCondition presenceOfNestedElementLocatedBy(final By locator,
        final By sub_locator);

    /**
     * An expectation for checking child WebElement as a part of parent element to be present
     *
     * @param element     used as parent element
     * @param sub_locator used to find child element. For example td By.xpath("./tr/td")
     * @return subelement
     */
    IWaitsExpectedCondition presenceOfNestedElementLocatedBy(final WebElement element,
        final By sub_locator);

    /**
     * An expectation for checking child WebElement as a part of parent element to present
     *
     * @param locator     used to check parent element. For example table with locator
     *                    By.xpath("//table")
     * @param sub_locator used to find child element. For example td By.xpath("./tr/td")
     * @return subelement
     */
    IWaitsExpectedCondition presenceOfNestedElementsLocatedBy(final By locator,
        final By sub_locator);

    /**
     * An expectation for checking all elements from given list to be invisible
     *
     * @param elements used to check their invisibility
     * @return Boolean true when all elements are not visible anymore
     */
    IWaitsExpectedCondition invisibilityOfAllElements(final List<WebElement> elements);

    /**
     * An expectation with the logical or condition of the given list of conditions.
     * <p>
     * Each condition is checked until at leas one of them returns true or not null
     *
     * @param conditions ExpectedCondition is a list of alternative conditions
     * @return true once one of conditions is satisfied
     */
    IWaitsExpectedCondition or(final ExpectedCondition<?>... conditions);

    /**
     * An expectation with the logical and condition of the given list of conditions.
     * <p>
     * Each condition is checked until all of them return true or not null
     *
     * @param conditions ExpectedCondition is a list of alternative conditions
     * @return true once all conditions are satisfied
     */
    IWaitsExpectedCondition and(final ExpectedCondition<?>... conditions);

    /**
     * An expectation to check if js executable
     * <p>
     * Usefull when  you know, that there should be js val or something at the stage
     *
     * @param javaScript used as executable script
     * @return true once javaScript executed without errors
     */
    IWaitsExpectedCondition javaScriptThrowsNoExceptions(final String javaScript);

    /**
     * An expectation for String value from javascript
     *
     * @param javaScript as executable js line
     * @return true once js return string
     */
    IWaitsExpectedCondition jsReturnsValue(final String javaScript);

}
