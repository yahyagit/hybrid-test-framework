package com.atanas.kanchev.testframework.selenium.handlers;

import com.atanas.kanchev.testframework.core.exceptions.CustomExceptions;
import com.atanas.kanchev.testframework.selenium.context.WebContext;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import static com.atanas.kanchev.testframework.selenium.handlers.CommonPageDefinitions.COLOR.*;
import static com.atanas.kanchev.testframework.selenium.handlers.CommonPageDefinitions.CSS.*;
import static com.atanas.kanchev.testframework.selenium.handlers.LocatorsFactory.getWebContext;

/**
 * Probe Current WebElement Interface
 *
 * @author Atanas Ksnchev
 */
interface IProbe extends IWrapper {

    // the logger
    Logger logger = LoggerFactory.getLogger(IProbe.class);

    /**
     * Go to WebElement
     *
     * @param locatorType LocatorsFactory
     * @param locator     String
     * @return boolean boolean
     */
    default boolean probeElement(LocatorsFactory locatorType, String locator) {

        if (locatorType == null || locator == null)
            throw new CustomExceptions.Common.NullArgumentException();
        else {
            try {
                logger.debug("Locating element by " + locatorType + " locator: " + locator);
                locatorType.locateElement(locatorType, locator);
            } catch (NoSuchElementException e) {
                logger.error("Unable to locate element by " + locatorType + " locator: " + locator, e);
                return false;
            }
        }

        return true;
    }

    /**
     * Finder elements by
     *
     * @param locatorType LocatorsFactory
     * @param locator     locator
     * @return this boolean
     */
    default boolean probeElements(LocatorsFactory locatorType, String locator) {

        if (locatorType == null || locator == null)
            throw new CustomExceptions.Common.NullArgumentException();
        else {
            try {
                logger.debug("Locating elements by " + locatorType + " locator: " + locator);
                locatorType.locateElements(locatorType, locator);
            } catch (NoSuchElementException e) {
                logger.error("Unable to locate elements by " + locatorType + " locator: " + locator, e);
                return false;
            }
        }

        return true;
    }


    /**
     * Probe if {@link WebContext#currentElement} has any text i.e. </br>
     * {@code <div>Text</div>} match will return <b>true</b>.
     * The text to be found can be anywhere in the element text string
     *
     * @return {@code true} if the current WebElement has any text
     */
    default boolean hasAnyText() {
        return getWebContext().getCurrentElement() != null && !getWebContext().getCurrentElement().getText().isEmpty();
    }

    /**
     * Probe if {@link WebContext#currentElement} has partial text match e.g.
     * {@code hasPartialText("hello");} matching against {@code <div>hello there<\div>}
     * will return <b>true</b> The text to be found can be a subset of
     * element text string </br> The method can also be used for multiple String elements
     * e.g. {@code hasPartialText("+", "-");
     * </br>(Note once an item is matched it returns true, irrespective of the other checks)
     *
     * @param caseSensitive
     * @param preciseMatch
     * @param textElements  the text elements
     * @return {@code true} if the current WebElement contains the text
     */
    default boolean hasText(boolean caseSensitive, boolean preciseMatch, String... textElements) {

        String elText = getWebContext().getCurrentElement().getText();

        boolean matchFound = false;

        if (textElements != null && textElements.length > 0) {
            logger.debug("Matching " + Arrays.toString(textElements) + " against element text:  " + elText);
            for (String textElement : textElements) {

                if (caseSensitive) {
                    if (preciseMatch) {
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
                    if (preciseMatch) {
                        if (elText.toLowerCase().equals(textElement)) {
                            matchFound = true;
                            break;
                        }
                    } else {
                        if (elText.toLowerCase().contains(textElement)) {
                            matchFound = true;
                            break;
                        }
                    }
                }
            }

            if (matchFound)
                logger.debug("Match found! Expected: [" + Arrays.toString(textElements) + "] Actual: [" + elText + "]");
            else
                logger.error("Match not found! Expected: [" + Arrays.toString(textElements) + "] Actual: [" + elText + "]");

        }
        return matchFound;
    }

    /**
     * Check if {@link WebContext#currentElement} contains specified identifier. The identifier to be
     * found can be a subset of element identifier
     *
     * @param preciseMatch  {@code boolean}
     * @param attributeName the attribute name to check
     * @param attributeText the attribute text to check
     * @return {@code true} if identifier is present and its value matches @param attributeText
     */
    default boolean hasAttribute(boolean preciseMatch, String attributeName, String attributeText) {

        String attrValue;

        try {
            attrValue = getWebContext().getCurrentElement().getAttribute(attributeName);
        } catch (NullPointerException npe) {
            logger.error("The current element doesn't have a child attribute: " + attributeName);
            throw new CustomExceptions.Common.IllegalArgumentException();
        }

        boolean matchFound = false;

        if (preciseMatch) {
            if (attrValue.equals(attributeText)) matchFound = true;
        } else {
            if (attrValue.contains(attributeText)) matchFound = true;
        }

        if (matchFound)
            logger.debug("Match found! Attribute name: [" + attributeName + "] Attribute Value: [" + attrValue + "]");
        else
            logger.error("Match not found! Attribute name: [" + attributeName + "] Attribute Value: [" + attrValue + "]");

        return matchFound;
    }

    /**
     * Checks if {@link WebContext#currentElement} of specified HTML tag type.
     *
     * @param tag the tag name to check
     * @return {@code true}, if element of specified tag type
     */
    default boolean isOfTagType(String tag) {
        return getWebContext().getCurrentElement() != null && getWebContext().getCurrentElement().getTagName().equals(tag);
    }

    /**
     * Checks if {@link WebContext#currentElement} is enabled i.e. can receive focus.
     *
     * @return {@code true} if element is enabled
     */
    default boolean isEnabled() {
        return getWebContext().getCurrentElement() != null && getWebContext().getCurrentElement().isEnabled();
    }

    /**
     * Checks if {@link WebContext#currentElement} is selected. Checkboxes, options in select and radio buttons.
     *
     * @return {@code true} if element is selected
     */
    default boolean isSelected() {
        return getWebContext().getCurrentElement() != null && getWebContext().getCurrentElement().isSelected();
    }

    /**
     * Checks {@link WebContext#currentElement} is active.
     *
     * @return {@code true} if element is active
     */
    default boolean isActive() {
        return getWebContext().getCurrentElement() != null && getWebContext().getCurrentElement().getAttribute("class").contains("active");
    }

    /**
     * Check if {@link WebContext#currentElement} is displayed.
     *
     * @return {@code true} if element is displayed
     */
    default boolean isDisplayed() {
        return getWebContext().getCurrentElement() != null && getWebContext().getCurrentElement().isDisplayed();
    }

    /**
     * Check if {@link WebContext#currentElement} has border color
     *
     * @param expectedColorHexCode
     * @return {@code true} if element has border color
     */
    default boolean hasBorderColour(String expectedColorHexCode) {

        boolean hasColour = true;
        Color expectedColour;
        Color actualColour;
        int testRepetitions;
        final int maxRepetitions = 4;

        String[] borderAttributes = {
                CSS_BORDER_TOP_COLOUR.name(),
                CSS_BORDER_LEFT_COLOUR.name(),
                CSS_BORDER_RIGHT_COLOUR.name(),
                CSS_BORDER_BOTTOM_COLOUR.name()};


        try {
            expectedColour = Color.fromString(expectedColorHexCode);
        } catch (IllegalArgumentException e) {
            logger.error("Error processing color : " + expectedColorHexCode + " : colour should have format : #[6 Hexadecimal Characters]");
            throw new IllegalArgumentException();
        }

        // Ensure expected and actual are different to begin with
        if (expectedColorHexCode.equals(COLOUR_WHITE.name())) {
            actualColour = Color.fromString(COLOUR_BLACK.name());
        } else {
            actualColour = Color.fromString(COLOUR_WHITE.name());
        }

        for (String attr : borderAttributes) {
            if (!hasColour) {
                break;
            }

            // Firefox : rgba(187, 187, 187)
            // Chrome : rgba(187, 187, 187, 1)
            // IE : 6 digit hexadecimal
            // fromString() will accept all formats
            testRepetitions = 0;
            while (!expectedColour.equals(actualColour)) {
                actualColour = Color.fromString(getWebContext().getCurrentElement().getCssValue(attr));
                try {
                    Thread.sleep(500);
                    logger.debug("Hex Value" + actualColour.asHex());
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                if (++testRepetitions == maxRepetitions) {
                    hasColour = false;
                    logger.error("Fail : Expected = " + expectedColour.toString() + ", Actual = " + actualColour.toString());
                    break;
                }
            }
        }
        return hasColour;
    }

    /**
     * Check if {@link WebContext#currentElement} text has color as expected
     *
     * @param expectedColorHexCode
     * @return
     */
    /**
     * Check if {@link WebContext#currentElement} has color as expected
     *
     * @param css                  CSS definition {@link CommonPageDefinitions.CSS}
     * @param expectedColorHexCode HEX color code
     * @return {@code true} if the color matches
     */
    default boolean hasColour(CommonPageDefinitions.CSS css, String expectedColorHexCode) {

        Color expColor;

        try {
            expColor = Color.fromString(expectedColorHexCode);
        } catch (IllegalArgumentException e) {
            logger.error("Error processing color : " + expectedColorHexCode + " : color should have format : #[6 Hexadecimal Characters]");
            throw new IllegalArgumentException();
        }

        switch (css) {
            case CSS_BACKGROUND_COLOUR:
                Color backgroundColor = Color.fromString(getWebContext().getCurrentElement().getCssValue(CSS_BACKGROUND_COLOUR.name()));
                return expColor.equals(backgroundColor);
            case CSS_TEXT_COLOUR:
                Color textColor = Color.fromString(getWebContext().getCurrentElement().getCssValue(CSS_TEXT_COLOUR.name()));
                return expColor.equals(textColor);
        }

        return false;
    }

}