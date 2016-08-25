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

import com.atanas.kanchev.testframework.commons.exceptions.CustomExceptions;
import com.atanas.kanchev.testframework.commons.wrappers.IContext;
import com.atanas.kanchev.testframework.selenium.context.SeleniumContext;
import com.atanas.kanchev.testframework.selenium.wrappers.ISelenium;
import org.openqa.selenium.*;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * <p>Probes class.</p>
 *
 * @author Atanas Kanchev
 */
public final class Probes<T extends WebDriver> implements IProbe, IProbeExpectedConditions, IContext<SeleniumContext> {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(Probes.class);

    /**
     * <p>Constructor for Probes.</p>
     *
     * @param locator a {@link org.openqa.selenium.By} object.
     */
    public Probes(By locator) {

        if (locator == null)
            throw new CustomExceptions.Common.NullArgumentException();
        else {
            logger.debug("Trying to locate element using " + locator);
            try {
                find().elementBy(locator);
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
            return context().getCurrentContext().getCurrentElement() != null;
        } catch (CustomExceptions.Common.NullReferenceException e) {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean hasAnyText() {

        boolean hasAnyText =
            ! context().getCurrentContext().getCurrentElement().getText()
                .isEmpty();
        logger.debug("Has any text: " + hasAnyText);
        return hasAnyText;

    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean hasText(boolean isCaseSensitiveMatch, boolean isPreciseMatch,
        String... textElements) {

        String elText =
            context().getCurrentContext().getCurrentElement().getText();

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

        String attrValue =  context().getCurrentContext().getCurrentElement()
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
            context().getCurrentContext().getCurrentElement().getTagName();
        logger.debug("Current element tag name: " + tagName);

        return tagName.equals(tag);
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean isEnabled() {
        return context().getCurrentContext().getCurrentElement().isEnabled();
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean isSelected() {
        return context().getCurrentContext().getCurrentElement().isSelected();
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean isActive() {
        return  context().getCurrentContext().getCurrentElement()
            .getAttribute("class").contains("active");
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean isDisplayed() {
        return context().getCurrentContext().getCurrentElement().isDisplayed();
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
                   context().getCurrentContext().getCurrentElement()
                        .getCssValue(
                            CommonPageDefinitions.CSS.CSS_BACKGROUND_COLOUR.getDefinition()));
                logger.debug("Actual color: " + backgroundColor.asHex());
                return expColor.asHex().equals(backgroundColor.asHex());
            case CSS_TEXT_COLOUR:
                Color textColor = Color.fromString(
                    context().getCurrentContext().getCurrentElement()
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
        if (!context().getCurrentContext().getCurrentElement().getTagName()
            .equals(CommonPageDefinitions.HTML.IMAGE.getDefinition())) {
            logger.error("hasImage : Current element is not image container");
        } else {
            contains = context().getCurrentContext().getCurrentElement()
                .getAttribute("src").contains(imagePath);
        }
        return contains;
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean hasLinkToURL(String url) {
        boolean hasAnchorAndHref = false;
        ((SeleniumContext<T>) context().getCurrentContext()).setWebElementsList(
            ((SeleniumContext<T>) context().getCurrentContext()).getDriver()
                .findElements(By.tagName(CommonPageDefinitions.HTML.ANCHOR.getDefinition())));

        for (WebElement anchor : ((SeleniumContext<T>) context().getCurrentContext())
            .getWebElementsList()) {
            if (anchor.getAttribute("href") != null && anchor.getAttribute("href").contains(url)) {
                hasAnchorAndHref = true;
                break;
            }

        }
        return hasAnchorAndHref;
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean followLinkToURL(String link) {
        boolean canFollow = false;
        if (hasLinkToURL(link)) {
            ((SeleniumContext<T>) context().getCurrentContext()).getDriver().navigate()
                .to(link);
            canFollow = true;
        }
        return canFollow;
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean hasPartialCookieValue(String cookieName, String cookieValue) {

        try {

            if (((SeleniumContext<T>) context().getCurrentContext()).getDriver().manage()
                .getCookieNamed(cookieName).getValue().contains(cookieValue)) {
                return true;
            }
        } catch (NullPointerException e) {
            logger.error("Cookie: " + cookieName + " does not exist");
            logger.debug("Cookie Names:");
            for (Cookie cookie : ((SeleniumContext<T>) context().getCurrentContext())
                .getDriver().manage().getCookies()) {
                logger.debug(cookie.getName());
            }
            return false;
        }
        logger.error("Cookie does not contain the value: " + cookieValue + ", actual value: "
            + (((SeleniumContext<T>) context().getCurrentContext())).getDriver().manage()
            .getCookieNamed(cookieName).getValue());
        return false;

    }

    @Override public boolean titleIs(String title) {
        logger.debug("Probing " + ExpectedConditions.titleIs(title).toString());
        return ExpectedConditions.titleIs(title)
            .apply((T) context().getCurrentContext().getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean titleContains(String title) {
        logger.debug("Probing " + ExpectedConditions.titleContains(title).toString());
        return ExpectedConditions.titleContains(title)
            .apply((T) context().getCurrentContext().getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean urlToBe(String url) {
        logger.debug("Probing " + ExpectedConditions.urlToBe(url).toString());
        return ExpectedConditions.urlToBe(url)
            .apply((T) context().getCurrentContext().getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean urlContains(String fraction) {
        logger.debug("Probing " + ExpectedConditions.urlContains(fraction).toString());
        return ExpectedConditions.urlContains(fraction)
            .apply((T) context().getCurrentContext().getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean urlMatches(String regex) {
        logger.debug("Probing " + ExpectedConditions.urlMatches(regex).toString());
        return ExpectedConditions.urlMatches(regex)
            .apply((T) context().getCurrentContext().getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public IProbeExpectedConditions presenceOfElementLocated(By locator) {
        logger.debug("Probing " + ExpectedConditions.presenceOfElementLocated(locator).toString());
        ((SeleniumContext) context().getCurrentContext()).setCurrentElement(
            ExpectedConditions.presenceOfElementLocated(locator)
                .apply((T) context().getCurrentContext().getDriver()));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IProbeExpectedConditions visibilityOfElementLocated(By locator) {
        logger
            .debug("Probing " + ExpectedConditions.visibilityOfElementLocated(locator).toString());
        ((SeleniumContext) context().getCurrentContext()).setCurrentElement(
            ExpectedConditions.visibilityOfElementLocated(locator)
                .apply((T) context().getCurrentContext().getDriver()));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IProbeExpectedConditions visibilityOfAllElementsLocatedBy(By locator) {
        logger.debug(
            "Probing " + ExpectedConditions.visibilityOfAllElementsLocatedBy(locator).toString());
        ((SeleniumContext) context().getCurrentContext()).setWebElementsList(
            ExpectedConditions.visibilityOfAllElementsLocatedBy(locator)
                .apply((T) context().getCurrentContext().getDriver()));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IProbeExpectedConditions visibilityOfAllElements(List<WebElement> elements) {
        logger.debug("Probing " + ExpectedConditions.visibilityOfAllElements(elements).toString());
        ((SeleniumContext) context().getCurrentContext()).setWebElementsList(
            ExpectedConditions.visibilityOfAllElements(elements)
                .apply((T) context().getCurrentContext().getDriver()));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IProbeExpectedConditions visibilityOf(WebElement element) {
        logger.debug("Probing " + ExpectedConditions.visibilityOf(element).toString());
        ((SeleniumContext) context().getCurrentContext()).setCurrentElement(
            ExpectedConditions.visibilityOf(element)
                .apply((T) context().getCurrentContext().getDriver()));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IProbeExpectedConditions presenceOfAllElementsLocatedBy(By locator) {
        logger.debug(
            "Probing " + ExpectedConditions.presenceOfAllElementsLocatedBy(locator).toString());
        ((SeleniumContext) context().getCurrentContext()).setWebElementsList(
            ExpectedConditions.presenceOfAllElementsLocatedBy(locator)
                .apply((T) context().getCurrentContext().getDriver()));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean textToBePresentInElement(WebElement element, String text) {
        logger.debug(
            "Probing " + ExpectedConditions.textToBePresentInElement(element, text).toString());
        return ExpectedConditions.textToBePresentInElement(element, text)
            .apply((T) context().getCurrentContext().getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean textToBePresentInElementLocated(By locator, String text) {
        logger.debug("Probing " + ExpectedConditions.textToBePresentInElementLocated(locator, text)
            .toString());
        return ExpectedConditions.textToBePresentInElementLocated(locator, text)
            .apply((T) context().getCurrentContext().getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public IProbeExpectedConditions textToBePresentInElementValue(WebElement element,
        String text) {
        logger.debug("Probing " + ExpectedConditions.textToBePresentInElementValue(element, text)
            .toString());
        ExpectedConditions.textToBePresentInElementValue(element, text)
            .apply((T) context().getCurrentContext().getDriver());
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean textToBePresentInElementValue(By locator, String text) {
        logger.debug("Probing " + ExpectedConditions.textToBePresentInElementValue(locator, text)
            .toString());
        return ExpectedConditions.textToBePresentInElementValue(locator, text)
            .apply((T) context().getCurrentContext().getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public IProbeExpectedConditions frameToBeAvailableAndSwitchToIt(String frameLocator) {
        logger.debug("Probing " + ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator)
            .toString());
        ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator)
            .apply((T) context().getCurrentContext().getDriver());
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IProbeExpectedConditions frameToBeAvailableAndSwitchToIt(By locator) {
        logger.debug(
            "Probing " + ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator).toString());
        ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator)
            .apply((T) context().getCurrentContext().getDriver());
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IProbeExpectedConditions frameToBeAvailableAndSwitchToIt(int frameLocator) {
        logger.debug("Probing " + ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator)
            .toString());
        ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator)
            .apply((T) context().getCurrentContext().getDriver());
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
            .apply((T) context().getCurrentContext().getDriver());
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean invisibilityOfElementLocated(By locator) {
        logger.debug(
            "Probing " + ExpectedConditions.invisibilityOfElementLocated(locator).toString());
        return ExpectedConditions.invisibilityOfElementLocated(locator)
            .apply((T) context().getCurrentContext().getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean invisibilityOfElementWithText(By locator, String text) {
        logger.debug("Probing " + ExpectedConditions.invisibilityOfElementWithText(locator, text)
            .toString());
        return ExpectedConditions.invisibilityOfElementWithText(locator, text)
            .apply((T) context().getCurrentContext().getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public IProbeExpectedConditions elementToBeClickable(By locator) {
        logger.debug("Probing " + ExpectedConditions.elementToBeClickable(locator).toString());
        ((SeleniumContext) context().getCurrentContext()).setCurrentElement(
            ExpectedConditions.elementToBeClickable(locator)
                .apply((T) context().getCurrentContext().getDriver()));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IProbeExpectedConditions elementToBeClickable(WebElement element) {
        logger.debug("Probing " + ExpectedConditions.elementToBeClickable(element).toString());
        ((SeleniumContext) context().getCurrentContext()).setCurrentElement(
            ExpectedConditions.elementToBeClickable(element)
                .apply((T) context().getCurrentContext().getDriver()));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean stalenessOf(WebElement element) {
        logger.debug("Probing " + ExpectedConditions.stalenessOf(element).toString());
        return ExpectedConditions.stalenessOf(element)
            .apply((T) context().getCurrentContext().getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public <U> IProbeExpectedConditions refreshed(ExpectedCondition<U> condition) {
        logger.debug("Probing " + ExpectedConditions.refreshed(condition).toString());
        ExpectedConditions.refreshed(condition)
            .apply((T) context().getCurrentContext().getDriver());
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean elementToBeSelected(WebElement element) {
        logger.debug("Probing " + ExpectedConditions.elementToBeSelected(element).toString());
        return ExpectedConditions.elementToBeSelected(element)
            .apply((T) context().getCurrentContext().getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean elementSelectionStateToBe(WebElement element, boolean selected) {
        logger.debug("Probing " + ExpectedConditions.elementSelectionStateToBe(element, selected)
            .toString());
        return ExpectedConditions.elementSelectionStateToBe(element, selected)
            .apply((T) context().getCurrentContext().getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean elementToBeSelected(By locator) {
        logger.debug("Probing " + ExpectedConditions.elementToBeSelected(locator).toString());
        return ExpectedConditions.elementToBeSelected(locator)
            .apply((T) context().getCurrentContext().getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean elementSelectionStateToBe(By locator, boolean selected) {
        logger.debug("Probing " + ExpectedConditions.elementSelectionStateToBe(locator, selected)
            .toString());
        return ExpectedConditions.elementSelectionStateToBe(locator, selected)
            .apply((T) context().getCurrentContext().getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public IProbeExpectedConditions alertIsPresent() {
        //TODO
        logger.debug("Probing " + ExpectedConditions.alertIsPresent().toString());
        ExpectedConditions.alertIsPresent()
            .apply((T) context().getCurrentContext().getDriver());
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean numberOfWindowsToBe(int expectedNumberOfWindows) {
        logger.debug("Probing " + ExpectedConditions.numberOfWindowsToBe(expectedNumberOfWindows)
            .toString());
        return ExpectedConditions.numberOfWindowsToBe(expectedNumberOfWindows)
            .apply((T) context().getCurrentContext().getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean not(ExpectedCondition<?> condition) {
        logger.debug("Probing " + ExpectedConditions.not(condition).toString());
        return ExpectedConditions.not(condition)
            .apply((T) context().getCurrentContext().getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean attributeToBe(By locator, String attribute, String value) {
        logger.debug(
            "Probing " + ExpectedConditions.attributeToBe(locator, attribute, value).toString());
        return ExpectedConditions.attributeToBe(locator, attribute, value)
            .apply((T) context().getCurrentContext().getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean textToBe(By locator, String value) {
        logger.debug("Probing " + ExpectedConditions.textToBe(locator, value).toString());
        return ExpectedConditions.textToBe(locator, value)
            .apply((T) context().getCurrentContext().getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean textMatches(By locator, Pattern pattern) {
        logger.debug("Probing " + ExpectedConditions.textMatches(locator, pattern).toString());
        return ExpectedConditions.textMatches(locator, pattern)
            .apply((T) context().getCurrentContext().getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public IProbeExpectedConditions numberOfElementsToBeMoreThan(By locator,
        Integer number) {
        logger.debug("Probing " + ExpectedConditions.numberOfElementsToBeMoreThan(locator, number)
            .toString());
        ((SeleniumContext) context().getCurrentContext()).setWebElementsList(
            ExpectedConditions.numberOfElementsToBeMoreThan(locator, number)
                .apply((T) context().getCurrentContext().getDriver()));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IProbeExpectedConditions numberOfElementsToBeLessThan(By locator,
        Integer number) {
        logger.debug("Probing " + ExpectedConditions.numberOfElementsToBeLessThan(locator, number)
            .toString());
        ((SeleniumContext) context().getCurrentContext()).setWebElementsList(
            ExpectedConditions.numberOfElementsToBeLessThan(locator, number)
                .apply((T) context().getCurrentContext().getDriver()));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IProbeExpectedConditions numberOfElementsToBe(By locator, Integer number) {
        logger.debug(
            "Probing " + ExpectedConditions.numberOfElementsToBe(locator, number).toString());
        ((SeleniumContext) context().getCurrentContext()).setWebElementsList(
            ExpectedConditions.numberOfElementsToBe(locator, number)
                .apply((T) context().getCurrentContext().getDriver()));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean attributeToBe(WebElement element, String attribute, String value) {
        logger.debug(
            "Probing " + ExpectedConditions.attributeToBe(element, attribute, value).toString());
        return ExpectedConditions.attributeToBe(element, attribute, value)
            .apply((T) context().getCurrentContext().getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean attributeContains(WebElement element, String attribute, String value) {
        logger.debug("Probing " + ExpectedConditions.attributeContains(element, attribute, value)
            .toString());
        return ExpectedConditions.attributeContains(element, attribute, value)
            .apply((T) context().getCurrentContext().getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean attributeContains(By locator, String attribute, String value) {
        logger.debug("Probing " + ExpectedConditions.attributeContains(locator, attribute, value)
            .toString());
        return ExpectedConditions.attributeContains(locator, attribute, value)
            .apply((T) context().getCurrentContext().getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean attributeToBeNotEmpty(WebElement element, String attribute) {
        logger.debug(
            "Probing " + ExpectedConditions.attributeToBeNotEmpty(element, attribute).toString());
        return ExpectedConditions.attributeToBeNotEmpty(element, attribute)
            .apply((T) context().getCurrentContext().getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public IProbeExpectedConditions visibilityOfNestedElementsLocatedBy(By locator,
        By sub_locator) {
        logger.debug("Probing " + ExpectedConditions
            .visibilityOfNestedElementsLocatedBy(locator, sub_locator).toString());
        ((SeleniumContext) context().getCurrentContext()).setWebElementsList(
            ExpectedConditions.visibilityOfNestedElementsLocatedBy(locator, sub_locator)
                .apply((T) context().getCurrentContext().getDriver()));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public IProbeExpectedConditions visibilityOfNestedElementsLocatedBy(
        WebElement element, By sub_locator) {
        logger.debug("Probing " + ExpectedConditions
            .visibilityOfNestedElementsLocatedBy(element, sub_locator).toString());
        ((SeleniumContext) context().getCurrentContext()).setWebElementsList(
            ExpectedConditions.visibilityOfNestedElementsLocatedBy(element, sub_locator)
                .apply((T) context().getCurrentContext().getDriver()));
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
        ((SeleniumContext) context().getCurrentContext()).setCurrentElement(
            ExpectedConditions.presenceOfNestedElementLocatedBy(locator, sub_locator)
                .apply((T) context().getCurrentContext().getDriver()));
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
        ((SeleniumContext) context().getCurrentContext()).setCurrentElement(
            ExpectedConditions.presenceOfNestedElementLocatedBy(element, sub_locator)
                .apply((T) context().getCurrentContext().getDriver()));
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
        ((SeleniumContext) context().getCurrentContext()).setWebElementsList(
            ExpectedConditions.presenceOfNestedElementsLocatedBy(locator, sub_locator)
                .apply((T) context().getCurrentContext().getDriver()));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean invisibilityOfAllElements(List<WebElement> elements) {
        logger
            .debug("Probing " + ExpectedConditions.invisibilityOfAllElements(elements).toString());
        return ExpectedConditions.invisibilityOfAllElements(elements)
            .apply((T) context().getCurrentContext().getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean or(ExpectedCondition<?>... conditions) {
        logger.debug("Probing " + ExpectedConditions.or(conditions).toString());
        return ExpectedConditions.or(conditions)
            .apply((T) context().getCurrentContext().getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean and(ExpectedCondition<?>... conditions) {
        logger.debug("Probing " + ExpectedConditions.and(conditions).toString());
        return ExpectedConditions.and(conditions).
            apply((T) context().getCurrentContext().getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean javaScriptThrowsNoExceptions(String javaScript) {
        logger.debug(
            "Probing " + ExpectedConditions.javaScriptThrowsNoExceptions(javaScript).toString());
        return ExpectedConditions.javaScriptThrowsNoExceptions(javaScript)
            .apply((T) context().getCurrentContext().getDriver());
    }

    /**
     * {@inheritDoc}
     */
    @Override public Object jsReturnsValue(String javaScript) {
        logger.debug("Probing " + ExpectedConditions.jsReturnsValue(javaScript).toString());
        return ExpectedConditions.jsReturnsValue(javaScript)
            .apply((T) context().getCurrentContext().getDriver());
    }

}


interface IProbe extends ISelenium {
    /**
     * <p>exist.</p>
     *
     * @return a boolean.
     */
    boolean exist();

    /**
     * <p>hasAnyText.</p>
     *
     * @return a boolean.
     */
    boolean hasAnyText();

    /**
     * <p>hasText.</p>
     *
     * @param isCaseSensitiveMatch a boolean.
     * @param isPreciseMatch       a boolean.
     * @param textElements         a {@link java.lang.String} object.
     * @return a boolean.
     */
    boolean hasText(boolean isCaseSensitiveMatch, boolean isPreciseMatch, String... textElements);

    /**
     * <p>hasAttribute.</p>
     *
     * @param preciseMatch  a boolean.
     * @param attributeName a {@link java.lang.String} object.
     * @param attributeText a {@link java.lang.String} object.
     * @return a boolean.
     */
    boolean hasAttribute(String attributeName, String attributeText, boolean preciseMatch);

    /**
     * <p>isOfTagType.</p>
     *
     * @param tag a {@link java.lang.String} object.
     * @return a boolean.
     */
    boolean isOfTagType(String tag);

    /**
     * <p>isEnabled.</p>
     *
     * @return a boolean.
     */
    boolean isEnabled();

    /**
     * <p>isSelected.</p>
     *
     * @return a boolean.
     */
    boolean isSelected();

    /**
     * <p>isActive.</p>
     *
     * @return a boolean.
     */
    boolean isActive();

    /**
     * <p>isDisplayed.</p>
     *
     * @return a boolean.
     */
    boolean isDisplayed();

    /**
     * <p>hasColour.</p>
     *
     * @param css                  a {@link com.atanas.kanchev.testframework.selenium.handlers.CommonPageDefinitions.CSS} object.
     * @param expectedColorHexCode a {@link java.lang.String} object.
     * @return a boolean.
     */
    boolean hasColour(CommonPageDefinitions.CSS css, String expectedColorHexCode);

    /**
     * <p>hasPartialImagePath.</p>
     *
     * @param imagePath a {@link java.lang.String} object.
     * @return a boolean.
     */
    boolean hasPartialImagePath(String imagePath);

    /**
     * <p>hasLinkToURL.</p>
     *
     * @param url a {@link java.lang.String} object.
     * @return a boolean.
     */
    boolean hasLinkToURL(String url);

    /**
     * <p>followLinkToURL.</p>
     *
     * @param link a {@link java.lang.String} object.
     * @return a boolean.
     */
    boolean followLinkToURL(String link);

    /**
     * <p>hasPartialCookieValue.</p>
     *
     * @param cookieName  a {@link java.lang.String} object.
     * @param cookieValue a {@link java.lang.String} object.
     * @return a boolean.
     */
    boolean hasPartialCookieValue(String cookieName, String cookieValue);
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
interface IProbeExpectedConditions extends ISelenium{

    /**
     * An expectation for checking the title of a page.
     *
     * @param title the expected title, which must be an exact match
     * @return true when the title matches, false otherwise
     */
    boolean titleIs(final String title);

    /**
     * An expectation for checking that the title contains a case-sensitive substring
     *
     * @param title the fragment of title expected
     * @return true when the title matches, false otherwise
     */
    boolean titleContains(final String title);

    /**
     * An expectation for the URL of the current page to be a specific url.
     *
     * @param url the url that the page should be on
     * @return <code>true</code> when the URL is what it should be
     */
    boolean urlToBe(final String url);

    /**
     * An expectation for the URL of the current page to contain specific text.
     *
     * @param fraction the fraction of the url that the page should be on
     * @return <code>true</code> when the URL contains the text
     */
    boolean urlContains(final String fraction);

    /**
     * Expectation for the URL to match a specific regular expression
     *
     * @param regex the regular expression that the URL should match
     * @return <code>true</code> if the URL matches the specified regular expression
     */
    boolean urlMatches(final String regex);

    /**
     * An expectation for checking that an element is present on the DOM of a page. This does not
     * necessarily mean that the element is visible.
     *
     * @param locator used to find the element
     * @return the WebElement once it is located
     */
    IProbeExpectedConditions presenceOfElementLocated(final By locator);

    /**
     * An expectation for checking that an element is present on the DOM of a page and visible.
     * Visibility means that the element is not only displayed but also has a height and width that is
     * greater than 0.
     *
     * @param locator used to find the element
     * @return the WebElement once it is located and visible
     */
    IProbeExpectedConditions visibilityOfElementLocated(final By locator);

    /**
     * An expectation for checking that all elements present on the web page that match the locator
     * are visible. Visibility means that the elements are not only displayed but also have a height
     * and width that is greater than 0.
     *
     * @param locator used to find the element
     * @return the list of WebElements once they are located
     */
    IProbeExpectedConditions visibilityOfAllElementsLocatedBy(final By locator);

    /**
     * An expectation for checking that all elements present on the web page that match the locator
     * are visible. Visibility means that the elements are not only displayed but also have a height
     * and width that is greater than 0.
     *
     * @param elements list of WebElements
     * @return the list of WebElements once they are located
     */
    IProbeExpectedConditions visibilityOfAllElements(final List<WebElement> elements);

    /**
     * An expectation for checking that an element, known to be present on the DOM of a page, is
     * visible. Visibility means that the element is not only displayed but also has a height and
     * width that is greater than 0.
     *
     * @param element the WebElement
     * @return the (same) WebElement once it is visible
     */
    IProbeExpectedConditions visibilityOf(final WebElement element);

    /**
     * An expectation for checking that there is at least one element present on a web page.
     *
     * @param locator used to find the element
     * @return the list of WebElements once they are located
     */
    IProbeExpectedConditions presenceOfAllElementsLocatedBy(final By locator);

    /**
     * An expectation for checking if the given text is present in the specified element.
     *
     * @param element the WebElement
     * @param text    to be present in the element
     * @return true once the element contains the given text
     */
    boolean textToBePresentInElement(final WebElement element, final String text);

    /**
     * An expectation for checking if the given text is present in the element that matches the given
     * locator.
     *
     * @param locator used to find the element
     * @param text    to be present in the element found by the locator
     * @return true once the first element located by locator contains the given text
     */
    boolean textToBePresentInElementLocated(final By locator, final String text);

    /**
     * An expectation for checking if the given text is present in the specified elements value
     * attribute.
     *
     * @param element the WebElement
     * @param text    to be present in the element's value attribute
     * @return true once the element's value attribute contains the given text
     */
    IProbeExpectedConditions textToBePresentInElementValue(final WebElement element,
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
    boolean textToBePresentInElementValue(final By locator, final String text);

    /**
     * An expectation for checking whether the given frame is available to switch to. <p> If the frame
     * is available it switches the given driver to the specified frame.
     *
     * @param frameLocator used to find the frame (id or name)
     * @return WebDriver omniaDriver after frame has been switched
     */
    IProbeExpectedConditions frameToBeAvailableAndSwitchToIt(final String frameLocator);

    /**
     * An expectation for checking whether the given frame is available to switch to. <p> If the frame
     * is available it switches the given driver to the specified frame.
     *
     * @param locator used to find the frame
     * @return WebDriver omniaDriver after frame has been switched
     */
    IProbeExpectedConditions frameToBeAvailableAndSwitchToIt(final By locator);

    /**
     * An expectation for checking whether the given frame is available to switch to. <p> If the frame
     * is available it switches the given driver to the specified frameIndex.
     *
     * @param frameLocator used to find the frame (index)
     * @return WebDriver omniaDriver after frame has been switched
     */
    IProbeExpectedConditions frameToBeAvailableAndSwitchToIt(final int frameLocator);

    /**
     * An expectation for checking whether the given frame is available to switch to. <p> If the frame
     * is available it switches the given driver to the specified webelement.
     *
     * @param frameLocator used to find the frame (webelement)
     * @return WebDriver omniaDriver after frame has been switched
     */
    IProbeExpectedConditions frameToBeAvailableAndSwitchToIt(final WebElement frameLocator);

    /**
     * An expectation for checking that an element is either invisible or not present on the DOM.
     *
     * @param locator used to find the element
     * @return true if the element is not displayed or the element doesn't exist or stale element
     */
    boolean invisibilityOfElementLocated(final By locator);

    /**
     * An expectation for checking that an element with text is either invisible or not present on the
     * DOM.
     *
     * @param locator used to find the element
     * @param text    of the element
     * @return true if no such element, stale element or displayed text not equal that provided
     */
    boolean invisibilityOfElementWithText(final By locator, final String text);

    /**
     * An expectation for checking an element is visible and enabled such that you can click it.
     *
     * @param locator used to find the element
     * @return the WebElement once it is located and clickable (visible and enabled)
     */
    IProbeExpectedConditions elementToBeClickable(final By locator);

    /**
     * An expectation for checking an element is visible and enabled such that you can click it.
     *
     * @param element the WebElement
     * @return the (same) WebElement once it is clickable (visible and enabled)
     */
    IProbeExpectedConditions elementToBeClickable(final WebElement element);

    /**
     * IWait until an element is no longer attached to the DOM.
     *
     * @param element The element to wait for.
     * @return false is the element is still attached to the DOM, true otherwise.
     */
    boolean stalenessOf(final WebElement element);

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
    <T> IProbeExpectedConditions refreshed(final ExpectedCondition<T> condition);

    /**
     * An expectation for checking if the given element is selected.
     *
     * @param element WebElement to be selected
     * @return true once the element is selected
     */
    boolean elementToBeSelected(final WebElement element);

    /**
     * An expectation for checking if the given element is selected.
     *
     * @param element  WebElement to be selected
     * @param selected boolean state of the selection state of the element
     * @return true once the element's selection stated is that of selected
     */
    boolean elementSelectionStateToBe(final WebElement element, final boolean selected);

    boolean elementToBeSelected(final By locator);

    boolean elementSelectionStateToBe(final By locator, final boolean selected);

    IProbeExpectedConditions alertIsPresent();

    boolean numberOfWindowsToBe(final int expectedNumberOfWindows);

    /**
     * An expectation with the logical opposite condition of the given condition.
     * <p>
     * Note that if the Condition your are inverting throws an exception that is caught by the Ignored
     * Exceptions, the inversion will not take place and lead to confusing results.
     *
     * @param condition ExpectedCondition to be inverted
     * @return true once the condition is satisfied
     */
    boolean not(final ExpectedCondition<?> condition);

    /**
     * An expectation for checking WebElement with given locator has attribute with a specific value
     *
     * @param locator   used to find the element
     * @param attribute used to define css or html attribute
     * @param value     used as expected attribute value
     * @return Boolean true when element has css or html attribute with the value
     */
    boolean attributeToBe(final By locator, final String attribute, final String value);

    /**
     * An expectation for checking WebElement with given locator has specific text
     *
     * @param locator used to find the element
     * @param value   used as expected text
     * @return Boolean true when element has text value equal to @value
     */
    boolean textToBe(final By locator, final String value);

    /**
     * An expectation for checking WebElement with given locator has text with a value as a part of
     * it
     *
     * @param locator used to find the element
     * @param pattern used as expected text matcher pattern
     * @return Boolean true when element has text value containing @value
     */
    boolean textMatches(final By locator, final Pattern pattern);

    /**
     * An expectation for checking number of WebElements with given locator
     *
     * @param locator used to find the element
     * @param number  user to define exact number of elements
     * @return Boolean true when size of elements list is equal to defined
     */
    IProbeExpectedConditions numberOfElementsToBeMoreThan(final By locator, final Integer number);

    /**
     * An expectation for checking number of WebElements with given locator being less than defined
     * number
     *
     * @param locator used to find the element
     * @param number  user to define maximum number of elements
     * @return Boolean true when size of elements list is less than defined
     */
    IProbeExpectedConditions numberOfElementsToBeLessThan(final By locator, final Integer number);

    /**
     * An expectation for checking number of WebElements with given locator
     *
     * @param locator used to find the element
     * @param number  user to define number of elements
     * @return Boolean true when size of elements list is equal to defined
     */
    IProbeExpectedConditions numberOfElementsToBe(final By locator, final Integer number);

    /**
     * An expectation for checking given WebElement has attribute with a specific value
     *
     * @param element   used to check it's parameters
     * @param attribute used to define css or html attribute
     * @param value     used as expected attribute value
     * @return Boolean true when element has css or html attribute with the value
     */
    boolean attributeToBe(final WebElement element, final String attribute, final String value);

    /**
     * An expectation for checking WebElement with given locator has attribute which contains specific
     * value
     *
     * @param element   used to check it's parameters
     * @param attribute used to define css or html attribute
     * @param value     used as expected attribute value
     * @return Boolean true when element has css or html attribute which contains the value
     */
    boolean attributeContains(final WebElement element, final String attribute, final String value);

    /**
     * An expectation for checking WebElement with given locator has attribute which contains specific
     * value
     *
     * @param locator   used to define WebElement to check it's parameters
     * @param attribute used to define css or html attribute
     * @param value     used as expected attribute value
     * @return Boolean true when element has css or html attribute which contains the value
     */
    boolean attributeContains(final By locator, final String attribute, final String value);

    /**
     * An expectation for checking WebElement any non empty value for given attribute
     *
     * @param element   used to check it's parameters
     * @param attribute used to define css or html attribute
     * @return Boolean true when element has css or html attribute with non empty value
     */
    boolean attributeToBeNotEmpty(final WebElement element, final String attribute);

    /**
     * An expectation for checking child WebElement as a part of parent element to be visible
     *
     * @param locator     used to check parent element. For example table with locator
     *                    By.xpath("//table")
     * @param sub_locator used to find child element. For example td By.xpath("./tr/td")
     * @return visible nested element
     */
    IProbeExpectedConditions visibilityOfNestedElementsLocatedBy(final By locator,
        final By sub_locator);

    /**
     * An expectation for checking child WebElement as a part of parent element to be visible
     *
     * @param element     used as parent element. For example table with locator By.xpath("//table")
     * @param sub_locator used to find child element. For example td By.xpath("./tr/td")
     * @return visible subelement
     */
    IProbeExpectedConditions visibilityOfNestedElementsLocatedBy(final WebElement element,
        final By sub_locator);

    /**
     * An expectation for checking child WebElement as a part of parent element to present
     *
     * @param locator     used to check parent element. For example table with locator
     *                    By.xpath("//table")
     * @param sub_locator used to find child element. For example td By.xpath("./tr/td")
     * @return subelement
     */
    IProbeExpectedConditions presenceOfNestedElementLocatedBy(final By locator,
        final By sub_locator);

    /**
     * An expectation for checking child WebElement as a part of parent element to be present
     *
     * @param element     used as parent element
     * @param sub_locator used to find child element. For example td By.xpath("./tr/td")
     * @return subelement
     */
    IProbeExpectedConditions presenceOfNestedElementLocatedBy(final WebElement element,
        final By sub_locator);

    /**
     * An expectation for checking child WebElement as a part of parent element to present
     *
     * @param locator     used to check parent element. For example table with locator
     *                    By.xpath("//table")
     * @param sub_locator used to find child element. For example td By.xpath("./tr/td")
     * @return subelement
     */
    IProbeExpectedConditions presenceOfNestedElementsLocatedBy(final By locator,
        final By sub_locator);

    /**
     * An expectation for checking all elements from given list to be invisible
     *
     * @param elements used to check their invisibility
     * @return Boolean true when all elements are not visible anymore
     */
    boolean invisibilityOfAllElements(final List<WebElement> elements);

    /**
     * An expectation with the logical or condition of the given list of conditions.
     * <p>
     * Each condition is checked until at leas one of them returns true or not null
     *
     * @param conditions ExpectedCondition is a list of alternative conditions
     * @return true once one of conditions is satisfied
     */
    boolean or(final ExpectedCondition<?>... conditions);

    /**
     * An expectation with the logical and condition of the given list of conditions.
     * <p>
     * Each condition is checked until all of them return true or not null
     *
     * @param conditions ExpectedCondition is a list of alternative conditions
     * @return true once all conditions are satisfied
     */
    boolean and(final ExpectedCondition<?>... conditions);

    /**
     * An expectation to check if js executable
     * <p>
     * Usefull when  you know, that there should be js val or something at the stage
     *
     * @param javaScript used as executable script
     * @return true once javaScript executed without errors
     */
    boolean javaScriptThrowsNoExceptions(final String javaScript);

    /**
     * An expectation for String value from javascript
     *
     * @param javaScript as executable js line
     * @return true once js return string
     */
    Object jsReturnsValue(final String javaScript);

}
