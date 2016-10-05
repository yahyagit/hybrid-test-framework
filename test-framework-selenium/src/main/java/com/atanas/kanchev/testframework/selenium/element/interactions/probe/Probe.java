/*
 * Copyright 2016 Atanas Stoychev Kanchev
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.atanas.kanchev.testframework.selenium.element.interactions.probe;

import com.atanas.kanchev.testframework.commons.context.ContextKey;
import com.atanas.kanchev.testframework.commons.exceptions.CustomExceptions;
import com.atanas.kanchev.testframework.selenium.accessors.SeleniumAccessorsSingleton;
import com.atanas.kanchev.testframework.selenium.context.SeleniumContext;
import com.atanas.kanchev.testframework.selenium.handlers.CommonPageDefinitions;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static com.atanas.kanchev.testframework.commons.accessors.ContextsAccessor.$context;
import static com.atanas.kanchev.testframework.selenium.accessors.SeleniumAccessors.$selenium;

/**
 * <p>Probes class.</p>
 *
 * @author Atanas Kanchev
 */
public final class Probe implements IProbe, IProbeExpectedConditions {

    private static final Logger logger = LoggerFactory.getLogger(Probe.class);

    ContextKey<SeleniumContext> currentContextKey;

    /**
     * <p>Constructor for Probes.</p>
     *
     * @param locator a {@link By} object.
     */
    public Probe(By locator) {

        if (locator == null)
            throw new CustomExceptions.Common.NullArgumentException();
        else {
            logger.debug("Trying to locate element using " + locator);
            try {
                $selenium().findElement(locator);
                logger.debug(
                    "Element found, setting as the current element pointer SeleniumContext#currentElement");
            } catch (NoSuchElementException e) {
                logger.debug("Unable to locate element by " + locator, e.getMessage());
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean exist() {
        try {
            return $context().getContext(SeleniumAccessorsSingleton.currentContextKey)
                .getCurrentElement() != null;
        } catch (CustomExceptions.Common.NullReferenceException e) {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean hasAnyText() {

        boolean hasAnyText =
            !$context().getContext(SeleniumAccessorsSingleton.currentContextKey).getCurrentElement()
                .getText().isEmpty();
        logger.debug("Has any text: " + hasAnyText);
        return hasAnyText;

    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean hasText(boolean isCaseSensitiveMatch, boolean isPreciseMatch,
        String... textElements) {

        String elText =
            $context().getContext(SeleniumAccessorsSingleton.currentContextKey).getCurrentElement()
                .getText();

        boolean matchFound = false;

        if (textElements != null && textElements.length > 0) {
            logger.debug(
                "Matching " + Arrays.toString(textElements) + " against element text:  " + elText);
            for (String textElement : textElements) {

                if (isCaseSensitiveMatch) {
                    if (isPreciseMatch) {
                        if (elText.equals(textElement)) {
                            matchFound = true;
                            break;
                        }
                    } else {
                        if (elText.contains(textElement)) {
                            matchFound = true;
                            break;
                        }
                    }

                } else {
                    if (isPreciseMatch) {
                        if (elText.toLowerCase().equals(textElement.toLowerCase())) {
                            matchFound = true;
                            break;
                        }
                    } else {
                        if (elText.toLowerCase().contains(textElement.toLowerCase())) {
                            matchFound = true;
                            break;
                        }
                    }
                }
            }

            if (matchFound)
                logger.debug(
                    "Match found! Expected: [" + Arrays.toString(textElements) + "] Actual: ["
                        + elText + "]");
            else
                logger.error(
                    "Match not found! Expected: [" + Arrays.toString(textElements) + "] Actual: ["
                        + elText + "]");

        }
        return matchFound;
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean hasAttribute(String attributeName, String attributeText,
        boolean preciseMatch) {

        String attrValue =
            $context().getContext(SeleniumAccessorsSingleton.currentContextKey).getCurrentElement()
                .getAttribute(attributeName);

        if (attrValue == null) {
            logger.error("The current element doesn't have a child attribute: " + attributeName);
            return false;
        }

        boolean matchFound = false;
        if (preciseMatch) {
            if (attrValue.equals(attributeText))
                matchFound = true;
        } else {
            if (attrValue.contains(attributeText))
                matchFound = true;
        }

        if (matchFound)
            logger.debug("Match found! Attribute name: [" + attributeName + "] Attribute Value: ["
                + attrValue + "]");
        else
            logger.error(
                "Match not found! Attribute name: [" + attributeName + "] Attribute Value: ["
                    + attrValue + "]");


        return matchFound;
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean isOfTagType(String tag) {

        String tagName =
            $context().getContext(SeleniumAccessorsSingleton.currentContextKey).getCurrentElement()
                .getTagName();
        logger.debug("Current element tag name: " + tagName);

        return tagName.equals(tag);
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean isEnabled() {
        return $context().getContext(SeleniumAccessorsSingleton.currentContextKey)
            .getCurrentElement().isEnabled();
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean isSelected() {
        return $context().getContext(SeleniumAccessorsSingleton.currentContextKey)
            .getCurrentElement().isSelected();
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean isActive() {
        return $context().getContext(SeleniumAccessorsSingleton.currentContextKey)
            .getCurrentElement().getAttribute("class").contains("active");
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean isDisplayed() {
        return $context().getContext(SeleniumAccessorsSingleton.currentContextKey)
            .getCurrentElement().isDisplayed();
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean hasColour(CommonPageDefinitions.CSS css, String expectedColorHexCode) {

        Color expColor;

        try {
            expColor = Color.fromString(expectedColorHexCode);
            logger.debug("Expected color: " + expColor.asHex());
        } catch (IllegalArgumentException e) {
            logger.error("Error processing color : " + expectedColorHexCode
                + " : color should have format : #[6 Hexadecimal Characters]");
            throw new IllegalArgumentException();
        }

        switch (css) {
            case CSS_BACKGROUND_COLOUR:
                Color backgroundColor = Color.fromString(
                    $context().getContext(SeleniumAccessorsSingleton.currentContextKey)
                        .getCurrentElement().getCssValue(
                        CommonPageDefinitions.CSS.CSS_BACKGROUND_COLOUR.getDefinition()));
                logger.debug("Actual color: " + backgroundColor.asHex());
                return expColor.asHex().equals(backgroundColor.asHex());
            case CSS_TEXT_COLOUR:
                Color textColor = Color.fromString(
                    $context().getContext(SeleniumAccessorsSingleton.currentContextKey)
                        .getCurrentElement()
                        .getCssValue(CommonPageDefinitions.CSS.CSS_TEXT_COLOUR.getDefinition()));
                logger.debug("Actual color: " + textColor.asHex());
                return expColor.asHex().equals(textColor.asHex());
        }

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean hasPartialImagePath(String imagePath) {
        boolean contains = false;
        if (!$context().getContext(SeleniumAccessorsSingleton.currentContextKey).getCurrentElement()
            .getTagName().equals(CommonPageDefinitions.HTML.IMAGE.getDefinition())) {
            logger.error("hasImage : Current element is not image container");
        } else {
            contains = $context().getContext(SeleniumAccessorsSingleton.currentContextKey)
                .getCurrentElement().getAttribute("src").contains(imagePath);
        }
        return contains;
    }

    //    /**
    //     * {@inheritDoc}
    //     */
    //    @Override public boolean hasLinkToURL(String url) {
    //        boolean hasAnchorAndHref = false;
    //        $context().getContext(SeleniumAccessorsSingleton.currentContextKey).setWebElementsList(
    //            $context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver().findElements(By.tagName(CommonPageDefinitions.HTML.ANCHOR.getDefinition())));
    //
    //        for (WebElement anchor : $context().getContext(SeleniumAccessorsSingleton.currentContextKey).getWebElementsList()) {
    //            if (anchor.getAttribute("href") != null && anchor.getAttribute("href").contains(url)) {
    //                hasAnchorAndHref = true;
    //                break;
    //            }
    //
    //        }
    //        return hasAnchorAndHref;
    //    }
    //
    //    /**
    //     * {@inheritDoc}
    //     */
    //    @Override public boolean followLinkToURL(String link) {
    //        boolean canFollow = false;
    //        if (hasLinkToURL(link)) {
    //            $context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver().navigate()
    //                .to(link);
    //            canFollow = true;
    //        }
    //        return canFollow;
    //    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean hasPartialCookieValue(String cookieName, String cookieValue) {

        try {

            if ($context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver()
                .manage().getCookieNamed(cookieName).getValue().contains(cookieValue)) {
                return true;
            }
        } catch (NullPointerException e) {
            logger.error("Cookie: " + cookieName + " does not exist");
            logger.debug("Cookie Names:");
            for (Cookie cookie : $context().getContext(SeleniumAccessorsSingleton.currentContextKey)
                .getDriver().manage().getCookies()) {
                logger.debug(cookie.getName());
            }
            return false;
        }
        logger.error(
            "Cookie does not contain the value: " + cookieValue + ", actual value: " + ($context()
                .getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver().manage()
                .getCookieNamed(cookieName).getValue()));
        return false;

    }

    @Override public boolean titleIs(String title) {
        logger.debug("Probing " + ExpectedConditions.titleIs(title).toString());
        return ExpectedConditions.titleIs(title)
            .apply($context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean titleContains(String title) {
        logger.debug("Probing " + ExpectedConditions.titleContains(title).toString());
        return ExpectedConditions.titleContains(title)
            .apply($context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean urlToBe(String url) {
        logger.debug("Probing " + ExpectedConditions.urlToBe(url).toString());
        return ExpectedConditions.urlToBe(url)
            .apply($context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean urlContains(String fraction) {
        logger.debug("Probing " + ExpectedConditions.urlContains(fraction).toString());
        return ExpectedConditions.urlContains(fraction)
            .apply($context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean urlMatches(String regex) {
        logger.debug("Probing " + ExpectedConditions.urlMatches(regex).toString());
        return ExpectedConditions.urlMatches(regex)
            .apply($context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public IProbeExpectedConditions presenceOfElementLocated(By locator) {
        logger.debug("Probing " + ExpectedConditions.presenceOfElementLocated(locator).toString());
        $context().getContext(SeleniumAccessorsSingleton.currentContextKey).setCurrentElement(
            ExpectedConditions.presenceOfElementLocated(locator).apply(
                $context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver()));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IProbeExpectedConditions visibilityOfElementLocated(By locator) {
        logger
            .debug("Probing " + ExpectedConditions.visibilityOfElementLocated(locator).toString());
        $context().getContext(SeleniumAccessorsSingleton.currentContextKey).setCurrentElement(
            ExpectedConditions.visibilityOfElementLocated(locator).apply(
                $context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver()));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IProbeExpectedConditions visibilityOfAllElementsLocatedBy(By locator) {
        logger.debug(
            "Probing " + ExpectedConditions.visibilityOfAllElementsLocatedBy(locator).toString());
        $context().getContext(SeleniumAccessorsSingleton.currentContextKey).setWebElementsList(
            ExpectedConditions.visibilityOfAllElementsLocatedBy(locator).apply(
                $context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver()));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IProbeExpectedConditions visibilityOfAllElements(List<WebElement> elements) {
        logger.debug("Probing " + ExpectedConditions.visibilityOfAllElements(elements).toString());
        $context().getContext(SeleniumAccessorsSingleton.currentContextKey).setWebElementsList(
            ExpectedConditions.visibilityOfAllElements(elements).apply(
                $context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver()));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IProbeExpectedConditions visibilityOf(WebElement element) {
        logger.debug("Probing " + ExpectedConditions.visibilityOf(element).toString());
        $context().getContext(SeleniumAccessorsSingleton.currentContextKey).setCurrentElement(
            ExpectedConditions.visibilityOf(element).apply(
                $context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver()));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IProbeExpectedConditions presenceOfAllElementsLocatedBy(By locator) {
        logger.debug(
            "Probing " + ExpectedConditions.presenceOfAllElementsLocatedBy(locator).toString());
        $context().getContext(SeleniumAccessorsSingleton.currentContextKey).setWebElementsList(
            ExpectedConditions.presenceOfAllElementsLocatedBy(locator).apply(
                $context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver()));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean textToBePresentInElement(WebElement element, String text) {
        logger.debug(
            "Probing " + ExpectedConditions.textToBePresentInElement(element, text).toString());
        return ExpectedConditions.textToBePresentInElement(element, text)
            .apply($context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean textToBePresentInElementLocated(By locator, String text) {
        logger.debug("Probing " + ExpectedConditions.textToBePresentInElementLocated(locator, text)
            .toString());
        return ExpectedConditions.textToBePresentInElementLocated(locator, text)
            .apply($context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public IProbeExpectedConditions textToBePresentInElementValue(WebElement element,
        String text) {
        logger.debug("Probing " + ExpectedConditions.textToBePresentInElementValue(element, text)
            .toString());
        ExpectedConditions.textToBePresentInElementValue(element, text)
            .apply($context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver());
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean textToBePresentInElementValue(By locator, String text) {
        logger.debug("Probing " + ExpectedConditions.textToBePresentInElementValue(locator, text)
            .toString());
        return ExpectedConditions.textToBePresentInElementValue(locator, text)
            .apply($context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public IProbeExpectedConditions frameToBeAvailableAndSwitchToIt(String frameLocator) {
        logger.debug("Probing " + ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator)
            .toString());
        ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator)
            .apply($context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver());
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IProbeExpectedConditions frameToBeAvailableAndSwitchToIt(By locator) {
        logger.debug(
            "Probing " + ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator).toString());
        ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator)
            .apply($context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver());
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IProbeExpectedConditions frameToBeAvailableAndSwitchToIt(int frameLocator) {
        logger.debug("Probing " + ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator)
            .toString());
        ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator)
            .apply($context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver());
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IProbeExpectedConditions frameToBeAvailableAndSwitchToIt(
        WebElement frameLocator) {
        logger.debug("Probing " + ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator)
            .toString());
        ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator)
            .apply($context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver());
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean invisibilityOfElementLocated(By locator) {
        logger.debug(
            "Probing " + ExpectedConditions.invisibilityOfElementLocated(locator).toString());
        return ExpectedConditions.invisibilityOfElementLocated(locator)
            .apply($context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean invisibilityOfElementWithText(By locator, String text) {
        logger.debug("Probing " + ExpectedConditions.invisibilityOfElementWithText(locator, text)
            .toString());
        return ExpectedConditions.invisibilityOfElementWithText(locator, text)
            .apply($context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public IProbeExpectedConditions elementToBeClickable(By locator) {
        logger.debug("Probing " + ExpectedConditions.elementToBeClickable(locator).toString());
        $context().getContext(SeleniumAccessorsSingleton.currentContextKey).setCurrentElement(
            ExpectedConditions.elementToBeClickable(locator).apply(
                $context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver()));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IProbeExpectedConditions elementToBeClickable(WebElement element) {
        logger.debug("Probing " + ExpectedConditions.elementToBeClickable(element).toString());
        $context().getContext(SeleniumAccessorsSingleton.currentContextKey).setCurrentElement(
            ExpectedConditions.elementToBeClickable(element).apply(
                $context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver()));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean stalenessOf(WebElement element) {
        logger.debug("Probing " + ExpectedConditions.stalenessOf(element).toString());
        return ExpectedConditions.stalenessOf(element)
            .apply($context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public <U> IProbeExpectedConditions refreshed(ExpectedCondition<U> condition) {
        logger.debug("Probing " + ExpectedConditions.refreshed(condition).toString());
        ExpectedConditions.refreshed(condition)
            .apply($context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver());
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean elementToBeSelected(WebElement element) {
        logger.debug("Probing " + ExpectedConditions.elementToBeSelected(element).toString());
        return ExpectedConditions.elementToBeSelected(element)
            .apply($context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean elementSelectionStateToBe(WebElement element, boolean selected) {
        logger.debug("Probing " + ExpectedConditions.elementSelectionStateToBe(element, selected)
            .toString());
        return ExpectedConditions.elementSelectionStateToBe(element, selected)
            .apply($context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean elementToBeSelected(By locator) {
        logger.debug("Probing " + ExpectedConditions.elementToBeSelected(locator).toString());
        return ExpectedConditions.elementToBeSelected(locator)
            .apply($context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean elementSelectionStateToBe(By locator, boolean selected) {
        logger.debug("Probing " + ExpectedConditions.elementSelectionStateToBe(locator, selected)
            .toString());
        return ExpectedConditions.elementSelectionStateToBe(locator, selected)
            .apply($context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public IProbeExpectedConditions alertIsPresent() {
        //TODO
        logger.debug("Probing " + ExpectedConditions.alertIsPresent().toString());
        ExpectedConditions.alertIsPresent()
            .apply($context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver());
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean numberOfWindowsToBe(int expectedNumberOfWindows) {
        logger.debug("Probing " + ExpectedConditions.numberOfWindowsToBe(expectedNumberOfWindows)
            .toString());
        return ExpectedConditions.numberOfWindowsToBe(expectedNumberOfWindows)
            .apply($context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean not(ExpectedCondition<?> condition) {
        logger.debug("Probing " + ExpectedConditions.not(condition).toString());
        return ExpectedConditions.not(condition)
            .apply($context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean attributeToBe(By locator, String attribute, String value) {
        logger.debug(
            "Probing " + ExpectedConditions.attributeToBe(locator, attribute, value).toString());
        return ExpectedConditions.attributeToBe(locator, attribute, value)
            .apply($context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean textToBe(By locator, String value) {
        logger.debug("Probing " + ExpectedConditions.textToBe(locator, value).toString());
        return ExpectedConditions.textToBe(locator, value)
            .apply($context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean textMatches(By locator, Pattern pattern) {
        logger.debug("Probing " + ExpectedConditions.textMatches(locator, pattern).toString());
        return ExpectedConditions.textMatches(locator, pattern)
            .apply($context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public IProbeExpectedConditions numberOfElementsToBeMoreThan(By locator,
        Integer number) {
        logger.debug("Probing " + ExpectedConditions.numberOfElementsToBeMoreThan(locator, number)
            .toString());
        $context().getContext(SeleniumAccessorsSingleton.currentContextKey).setWebElementsList(
            ExpectedConditions.numberOfElementsToBeMoreThan(locator, number).apply(
                $context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver()));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IProbeExpectedConditions numberOfElementsToBeLessThan(By locator,
        Integer number) {
        logger.debug("Probing " + ExpectedConditions.numberOfElementsToBeLessThan(locator, number)
            .toString());
        $context().getContext(SeleniumAccessorsSingleton.currentContextKey).setWebElementsList(
            ExpectedConditions.numberOfElementsToBeLessThan(locator, number).apply(
                $context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver()));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IProbeExpectedConditions numberOfElementsToBe(By locator, Integer number) {
        logger.debug(
            "Probing " + ExpectedConditions.numberOfElementsToBe(locator, number).toString());
        $context().getContext(SeleniumAccessorsSingleton.currentContextKey).setWebElementsList(
            ExpectedConditions.numberOfElementsToBe(locator, number).apply(
                $context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver()));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean attributeToBe(WebElement element, String attribute, String value) {
        logger.debug(
            "Probing " + ExpectedConditions.attributeToBe(element, attribute, value).toString());
        return ExpectedConditions.attributeToBe(element, attribute, value)
            .apply($context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean attributeContains(WebElement element, String attribute, String value) {
        logger.debug("Probing " + ExpectedConditions.attributeContains(element, attribute, value)
            .toString());
        return ExpectedConditions.attributeContains(element, attribute, value)
            .apply($context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean attributeContains(By locator, String attribute, String value) {
        logger.debug("Probing " + ExpectedConditions.attributeContains(locator, attribute, value)
            .toString());
        return ExpectedConditions.attributeContains(locator, attribute, value)
            .apply($context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean attributeToBeNotEmpty(WebElement element, String attribute) {
        logger.debug(
            "Probing " + ExpectedConditions.attributeToBeNotEmpty(element, attribute).toString());
        return ExpectedConditions.attributeToBeNotEmpty(element, attribute)
            .apply($context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public IProbeExpectedConditions visibilityOfNestedElementsLocatedBy(By locator,
        By sub_locator) {
        logger.debug("Probing " + ExpectedConditions
            .visibilityOfNestedElementsLocatedBy(locator, sub_locator).toString());
        $context().getContext(SeleniumAccessorsSingleton.currentContextKey).setWebElementsList(
            ExpectedConditions.visibilityOfNestedElementsLocatedBy(locator, sub_locator).apply(
                $context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver()));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IProbeExpectedConditions visibilityOfNestedElementsLocatedBy(
        WebElement element, By sub_locator) {
        logger.debug("Probing " + ExpectedConditions
            .visibilityOfNestedElementsLocatedBy(element, sub_locator).toString());
        $context().getContext(SeleniumAccessorsSingleton.currentContextKey).setWebElementsList(
            ExpectedConditions.visibilityOfNestedElementsLocatedBy(element, sub_locator).apply(
                $context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver()));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IProbeExpectedConditions presenceOfNestedElementLocatedBy(By locator,
        By sub_locator) {
        logger.debug(
            "Probing " + ExpectedConditions.presenceOfNestedElementLocatedBy(locator, sub_locator)
                .toString());
        $context().getContext(SeleniumAccessorsSingleton.currentContextKey).setCurrentElement(
            ExpectedConditions.presenceOfNestedElementLocatedBy(locator, sub_locator).apply(
                $context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver()));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IProbeExpectedConditions presenceOfNestedElementLocatedBy(WebElement element,
        By sub_locator) {
        logger.debug(
            "Probing " + ExpectedConditions.presenceOfNestedElementLocatedBy(element, sub_locator)
                .toString());
        $context().getContext(SeleniumAccessorsSingleton.currentContextKey).setCurrentElement(
            ExpectedConditions.presenceOfNestedElementLocatedBy(element, sub_locator).apply(
                $context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver()));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IProbeExpectedConditions presenceOfNestedElementsLocatedBy(By locator,
        By sub_locator) {
        logger.debug(
            "Probing " + ExpectedConditions.presenceOfNestedElementsLocatedBy(locator, sub_locator)
                .toString());
        $context().getContext(SeleniumAccessorsSingleton.currentContextKey).setWebElementsList(
            ExpectedConditions.presenceOfNestedElementsLocatedBy(locator, sub_locator).apply(
                $context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver()));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean invisibilityOfAllElements(List<WebElement> elements) {
        logger
            .debug("Probing " + ExpectedConditions.invisibilityOfAllElements(elements).toString());
        return ExpectedConditions.invisibilityOfAllElements(elements)
            .apply($context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean or(ExpectedCondition<?>... conditions) {
        logger.debug("Probing " + ExpectedConditions.or(conditions).toString());
        return ExpectedConditions.or(conditions)
            .apply($context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean and(ExpectedCondition<?>... conditions) {
        logger.debug("Probing " + ExpectedConditions.and(conditions).toString());
        return ExpectedConditions.and(conditions).
            apply($context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean javaScriptThrowsNoExceptions(String javaScript) {
        logger.debug(
            "Probing " + ExpectedConditions.javaScriptThrowsNoExceptions(javaScript).toString());
        return ExpectedConditions.javaScriptThrowsNoExceptions(javaScript)
            .apply($context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public Object jsReturnsValue(String javaScript) {
        logger.debug("Probing " + ExpectedConditions.jsReturnsValue(javaScript).toString());
        return ExpectedConditions.jsReturnsValue(javaScript)
            .apply($context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver());
    }

}
