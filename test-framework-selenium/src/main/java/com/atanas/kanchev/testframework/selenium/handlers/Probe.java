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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * <p>Probe class.</p>
 *
 * @author Atanas Kanchev
 */
public final class Probe implements IProbe, IContext {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(Probe.class);

    /**
     * <p>Constructor for Probe.</p>
     *
     * @param locator a {@link org.openqa.selenium.By} object.
     */
    public Probe(By locator) {

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
            return ((SeleniumContext) context().getCurrentContext()).getCurrentElement() != null;
        } catch (CustomExceptions.Common.NullReferenceException e) {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean hasAnyText() {

        boolean hasAnyText =
            !((SeleniumContext) context().getCurrentContext()).getCurrentElement().getText()
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
            ((SeleniumContext) context().getCurrentContext()).getCurrentElement().getText();

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
    @Override public boolean hasAttribute(boolean preciseMatch, String attributeName,
        String attributeText) {

        String attrValue = ((SeleniumContext) context().getCurrentContext()).getCurrentElement()
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
            ((SeleniumContext) context().getCurrentContext()).getCurrentElement().getTagName();
        logger.debug("Current element tag name: " + tagName);

        return tagName.equals(tag);
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean isEnabled() {
        return ((SeleniumContext) context().getCurrentContext()).getCurrentElement().isEnabled();
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean isSelected() {
        return ((SeleniumContext) context().getCurrentContext()).getCurrentElement().isSelected();
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean isActive() {
        return ((SeleniumContext) context().getCurrentContext()).getCurrentElement()
            .getAttribute("class").contains("active");
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean isDisplayed() {
        return ((SeleniumContext) context().getCurrentContext()).getCurrentElement().isDisplayed();
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
                    ((SeleniumContext) context().getCurrentContext()).getCurrentElement()
                        .getCssValue(
                            CommonPageDefinitions.CSS.CSS_BACKGROUND_COLOUR.getDefinition()));
                logger.debug("Actual color: " + backgroundColor.asHex());
                return expColor.asHex().equals(backgroundColor.asHex());
            case CSS_TEXT_COLOUR:
                Color textColor = Color.fromString(
                    ((SeleniumContext) context().getCurrentContext()).getCurrentElement()
                        .getCssValue(CommonPageDefinitions.CSS.CSS_TEXT_COLOUR.getDefinition()));
                logger.debug("Actual color: " + textColor.asHex());
                return expColor.asHex().equals(textColor.asHex());
        }

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean hasURL(String expectedURL, boolean isPreciseMatch) {
        String actualURL = ((SeleniumContext<WebDriver>) context().getCurrentContext()).getDriver()
            .getCurrentUrl();
        if (isPreciseMatch)
            return actualURL.equals(expectedURL);
        else
            return actualURL.contains(expectedURL);

    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean hasTitle(String expectedTitle, boolean isPreciseMatch) {
        String actualURL =
            ((SeleniumContext<WebDriver>) context().getCurrentContext()).getDriver().getTitle();
        if (isPreciseMatch)
            return actualURL.equals(expectedTitle);
        else
            return actualURL.contains(expectedTitle);

    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean hasPartialImagePath(String imagePath) {
        boolean contains = false;
        if (!((SeleniumContext) context().getCurrentContext()).getCurrentElement().getTagName()
            .equals(CommonPageDefinitions.HTML.IMAGE.getDefinition())) {
            logger.error("hasImage : Current element is not image container");
        } else {
            contains = ((SeleniumContext) context().getCurrentContext()).getCurrentElement()
                .getAttribute("src").contains(imagePath);
        }
        return contains;
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean hasLinkToURL(String url) {
        boolean hasAnchorAndHref = false;
        ((SeleniumContext) context().getCurrentContext()).setWebElementsList(
            ((SeleniumContext<WebDriver>) context().getCurrentContext()).getDriver()
                .findElements(By.tagName(CommonPageDefinitions.HTML.ANCHOR.getDefinition())));

        for (WebElement anchor : ((SeleniumContext<WebDriver>) context().getCurrentContext())
            .getWebElementsList()) {
            if (anchor.getAttribute("href") != null && anchor.getAttribute("href").contains(url))
                ;
            hasAnchorAndHref = true;
            break;

        }
        return hasAnchorAndHref;
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean followLinkToURL(String link) {
        boolean canFollow = false;
        if (hasLinkToURL(link)) {
            ((SeleniumContext<WebDriver>) context().getCurrentContext()).getDriver().navigate()
                .to(link);
            canFollow = true;
        }
        return canFollow;
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean titleHasText(String text, boolean isPreciseMatch) {

        String title =
            ((SeleniumContext<WebDriver>) context().getCurrentContext()).getDriver().getTitle();

        boolean matchFound = false;
        if (isPreciseMatch) {
            if (title.contains(text))
                matchFound = true;
        } else {
            if (title.equals(text))
                matchFound = true;
        }

        if (matchFound)
            logger.debug("Page title contains: " + text + " Actual Title: " + title);
        else
            logger.error("Page title does not contain: " + text + " Actual Title: " + title);

        return matchFound;
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean hasPartialCookieValue(String cookieName, String cookieValue) {

        try {

            if (((SeleniumContext<WebDriver>) context().getCurrentContext()).getDriver().manage()
                .getCookieNamed(cookieName).getValue().contains(cookieValue)) {
                return true;
            }
        } catch (NullPointerException e) {
            logger.error("Cookie: " + cookieName + " does not exist");
            logger.debug("Cookie Names:");
            for (Cookie cookie : ((SeleniumContext<WebDriver>) context().getCurrentContext())
                .getDriver().manage().getCookies()) {
                logger.debug(cookie.getName());
            }
            return false;
        }
        logger.error("Cookie does not contain the value: " + cookieValue + ", actual value: "
            + (((SeleniumContext<WebDriver>) context().getCurrentContext())).getDriver().manage()
            .getCookieNamed(cookieName).getValue());
        return false;

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
    boolean hasAttribute(boolean preciseMatch, String attributeName, String attributeText);

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
     * <p>hasURL.</p>
     *
     * @param expectedURL    a {@link java.lang.String} object.
     * @param isPreciseMatch a boolean.
     * @return a boolean.
     */
    boolean hasURL(String expectedURL, boolean isPreciseMatch);


    /**
     * <p>hasTitle.</p>
     *
     * @param expectedTitle  a {@link java.lang.String} object.
     * @param isPreciseMatch a boolean.
     * @return a boolean.
     */
    boolean hasTitle(String expectedTitle, boolean isPreciseMatch);

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
     * <p>titleHasText.</p>
     *
     * @param text           a {@link java.lang.String} object.
     * @param isPreciseMatch a boolean.
     * @return a boolean.
     */
    boolean titleHasText(String text, boolean isPreciseMatch);

    /**
     * <p>hasPartialCookieValue.</p>
     *
     * @param cookieName  a {@link java.lang.String} object.
     * @param cookieValue a {@link java.lang.String} object.
     * @return a boolean.
     */
    boolean hasPartialCookieValue(String cookieName, String cookieValue);

}
