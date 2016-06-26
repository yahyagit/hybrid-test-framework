package com.atanas.kanchev.testframework.core.handlers;

import com.atanas.kanchev.testframework.commons.exceptions.CustomExceptions;

import com.atanas.kanchev.testframework.core.context.ContextFactory;
import com.atanas.kanchev.testframework.core.context.WebContext;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Probe WebElement
 *
 * @author Atanas Ksnchev
 */
public class Probe implements IWrapper {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(Probe.class);

    /**
     * Constructor
     *
     * @param locator By
     */
    public Probe(By locator) {

        if (locator == null)
            throw new CustomExceptions.Common.NullArgumentException();
        else {
            try {
                logger.debug("Trying to locate element using " + locator);
                ((WebContext) ContextFactory.getCurrentContext()).setCurrentElement(
                        new LocatorFactory().findElement(locator));
                logger.debug("Element found, setting as the current element pointer WebContext#currentElement");
            } catch (NoSuchElementException e) {
                logger.error("Unable to locate element by " + locator, e.getMessage());
            }
        }
    }

    /**
     * Check if WebElement exists
     *
     * @return true if element is found in DOM
     */
    public boolean exist() {
        try {
            return ((WebContext) ContextFactory.getCurrentContext()).getCurrentElement() != null;
        } catch (CustomExceptions.Common.NullReferenceException e) {
            return false;
        }
    }

    /**
     * Probe if {@link WebContext#currentElement} has any text i.e. </br>
     * {@code <div>Text</div>} match will return <b>true</b>.
     * The text to be found can be anywhere in the element text string
     *
     * @return {@code true} if the current WebElement has any text
     */
    public boolean hasAnyText() {

        boolean hasAnyText = !((WebContext) ContextFactory.getCurrentContext()).getCurrentElement().getText().isEmpty();
        logger.debug("Has any text: " + hasAnyText);
        return hasAnyText;

    }

    /**
     * Probe if {@link WebContext#currentElement} has partial text match e.g.
     * {@code hasPartialText("hello");} matching against {@code <div>hello there<\div>}
     * will return <b>true</b> The text to be found can be a subset of
     * element text string </br> The method can also be used for multiple String elements
     * e.g. {@code hasPartialText("+", "-");
     * </br>(Note once an item is matched it returns true, irrespective of the other checks)
     *
     * @param isCaseSensitiveMatch {@code boolean}
     * @param isPreciseMatch       {@code boolean}
     * @param textElements         the text elements
     * @return {@code true} if the current WebElement contains the text
     */
    public boolean hasText(boolean isCaseSensitiveMatch, boolean isPreciseMatch, String... textElements) {

        String elText = ((WebContext) ContextFactory.getCurrentContext()).getCurrentElement().getText();

        boolean matchFound = false;

        if (textElements != null && textElements.length > 0) {
            logger.debug("Matching " + Arrays.toString(textElements) + " against element text:  " + elText);
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
    public boolean hasAttribute(boolean preciseMatch, String attributeName, String attributeText) {

        String attrValue = ((WebContext) ContextFactory.getCurrentContext()).getCurrentElement().getAttribute(attributeName);

        if (attrValue == null) {
            logger.error("The current element doesn't have a child attribute: " + attributeName);
            return false;
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
    public boolean isOfTagType(String tag) {

        String tagName = ((WebContext) ContextFactory.getCurrentContext()).getCurrentElement().getTagName();
        logger.debug("Current element tag name: " + tagName);

        return tagName.equals(tag);
    }

    /**
     * Checks if {@link WebContext#currentElement} is enabled i.e. can receive focus.
     *
     * @return {@code true} if element is enabled
     */
    public boolean isEnabled() {
        return ((WebContext) ContextFactory.getCurrentContext()).getCurrentElement().isEnabled();
    }

    /**
     * Checks if {@link WebContext#currentElement} is selected. Checkboxes, options in select and radio buttons.
     *
     * @return {@code true} if element is selected
     */
    public boolean isSelected() {
        return ((WebContext) ContextFactory.getCurrentContext()).getCurrentElement().isSelected();
    }

    /**
     * Checks {@link WebContext#currentElement} is active.
     *
     * @return {@code true} if element is active
     */
    public boolean isActive() {
        return ((WebContext) ContextFactory.getCurrentContext()).getCurrentElement().getAttribute("class").contains("active");
    }

    /**
     * Check if {@link WebContext#currentElement} has color as expected
     *
     * @param css                  CSS definition {@link CommonPageDefinitions.CSS}
     * @param expectedColorHexCode HEX color code
     * @return {@code true} if the color matches
     */
    public boolean hasColour(CommonPageDefinitions.CSS css, String expectedColorHexCode) {

        Color expColor;

        try {
            expColor = Color.fromString(expectedColorHexCode);
            logger.debug("Expected color: " + expColor.asHex());
        } catch (IllegalArgumentException e) {
            logger.error("Error processing color : " + expectedColorHexCode + " : color should have format : #[6 Hexadecimal Characters]");
            throw new IllegalArgumentException();
        }

        switch (css) {
            case CSS_BACKGROUND_COLOUR:
                Color backgroundColor = Color.fromString(
                        ((WebContext) ContextFactory.getCurrentContext()).getCurrentElement().getCssValue(CommonPageDefinitions.CSS.CSS_BACKGROUND_COLOUR.getDefinition()));
                logger.debug("Actual color: " + backgroundColor.asHex());
                return expColor.asHex().equals(backgroundColor.asHex());
            case CSS_TEXT_COLOUR:
                Color textColor = Color.fromString(
                        ((WebContext) ContextFactory.getCurrentContext()).getCurrentElement().getCssValue(CommonPageDefinitions.CSS.CSS_TEXT_COLOUR.getDefinition()));
                logger.debug("Actual color: " + textColor.asHex());
                return expColor.asHex().equals(textColor.asHex());
        }

        return false;
    }

}