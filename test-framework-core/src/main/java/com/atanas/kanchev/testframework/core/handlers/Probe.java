package com.atanas.kanchev.testframework.core.handlers;

import com.atanas.kanchev.testframework.commons.exceptions.CustomExceptions;
import com.atanas.kanchev.testframework.core.context.SeleniumContext;
import com.atanas.kanchev.testframework.core.handlers.wrappers.IContext;
import com.atanas.kanchev.testframework.core.handlers.wrappers.ISelenium;
import org.openqa.selenium.*;
import org.openqa.selenium.support.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Probe
 *
 * @author Atanas Ksnchev
 */
public final class Probe implements IProbe, IContext {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(Probe.class);

    public Probe(By locator) {

        if (locator == null)
            throw new CustomExceptions.Common.NullArgumentException();
        else {

            logger.debug("Trying to locate element using " + locator);
            try {
                find().elementBy(locator);
                logger.debug("Element found, setting as the current element pointer SeleniumContext#currentElement");
            } catch (NoSuchElementException e) {
                logger.debug("Unable to locate element by " + locator, e.getMessage());
            }
        }
    }

    @Override
    public boolean exist() {
        try {
            return ((SeleniumContext) context().getCurrentContext()).getCurrentElement() != null;
        } catch (CustomExceptions.Common.NullReferenceException e) {
            return false;
        }
    }

    @Override
    public boolean hasAnyText() {

        boolean hasAnyText = !((SeleniumContext) context().getCurrentContext()).getCurrentElement().getText().isEmpty();
        logger.debug("Has any text: " + hasAnyText);
        return hasAnyText;

    }

    @Override
    public boolean hasText(boolean isCaseSensitiveMatch, boolean isPreciseMatch, String... textElements) {

        String elText = ((SeleniumContext) context().getCurrentContext()).getCurrentElement().getText();

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

    @Override
    public boolean hasAttribute(boolean preciseMatch, String attributeName, String attributeText) {

        String attrValue = ((SeleniumContext) context().getCurrentContext()).getCurrentElement().getAttribute(attributeName);

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

    @Override
    public boolean isOfTagType(String tag) {

        String tagName = ((SeleniumContext) context().getCurrentContext()).getCurrentElement().getTagName();
        logger.debug("Current element tag name: " + tagName);

        return tagName.equals(tag);
    }

    @Override
    public boolean isEnabled() {
        return ((SeleniumContext) context().getCurrentContext()).getCurrentElement().isEnabled();
    }

    @Override
    public boolean isSelected() {
        return ((SeleniumContext) context().getCurrentContext()).getCurrentElement().isSelected();
    }

    @Override
    public boolean isActive() {
        return ((SeleniumContext) context().getCurrentContext()).getCurrentElement().getAttribute("class").contains("active");
    }

    @Override
    public boolean isDisplayed() {
        return ((SeleniumContext) context().getCurrentContext()).getCurrentElement().isDisplayed();
    }

    @Override
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
                        ((SeleniumContext) context().getCurrentContext()).getCurrentElement().getCssValue(CommonPageDefinitions.CSS.CSS_BACKGROUND_COLOUR.getDefinition()));
                logger.debug("Actual color: " + backgroundColor.asHex());
                return expColor.asHex().equals(backgroundColor.asHex());
            case CSS_TEXT_COLOUR:
                Color textColor = Color.fromString(
                        ((SeleniumContext) context().getCurrentContext()).getCurrentElement().getCssValue(CommonPageDefinitions.CSS.CSS_TEXT_COLOUR.getDefinition()));
                logger.debug("Actual color: " + textColor.asHex());
                return expColor.asHex().equals(textColor.asHex());
        }

        return false;
    }

    @Override
    public boolean hasURL(String expectedURL, boolean isPreciseMatch) {
        String actualURL = ((SeleniumContext<WebDriver>) context().getCurrentContext()).getDriver().getCurrentUrl();
        if (isPreciseMatch) return actualURL.equals(expectedURL);
        else return actualURL.contains(expectedURL);

    }

    @Override
    public boolean hasTitle(String expectedTitle, boolean isPreciseMatch) {
        String actualURL = ((SeleniumContext<WebDriver>) context().getCurrentContext()).getDriver().getTitle();
        if (isPreciseMatch) return actualURL.equals(expectedTitle);
        else return actualURL.contains(expectedTitle);

    }

    @Override
    public boolean hasPartialImagePath(String imagePath) {
        boolean contains = false;
        if (!((SeleniumContext) context().getCurrentContext()).getCurrentElement().getTagName().equals(CommonPageDefinitions.HTML.IMAGE.getDefinition())) {
            logger.error("hasImage : Current element is not image container");
        } else {
            contains = ((SeleniumContext) context().getCurrentContext()).getCurrentElement().getAttribute("src").contains(imagePath);
        }
        return contains;
    }

    @Override
    public boolean hasLinkToURL(String url) {
        boolean hasAnchorAndHref = false;
        ((SeleniumContext) context().getCurrentContext()).setWebElementsList(
                ((SeleniumContext<WebDriver>) context().getCurrentContext()).getDriver().findElements(By.tagName(CommonPageDefinitions.HTML.ANCHOR.getDefinition())));

        for (WebElement anchor : ((SeleniumContext<WebDriver>) context().getCurrentContext()).getWebElementsList()) {
            if (anchor.getAttribute("href") != null && anchor.getAttribute("href").contains(url)) ;
            hasAnchorAndHref = true;
            break;

        }
        return hasAnchorAndHref;
    }

    @Override
    public boolean followLinkToURL(String link) {
        boolean canFollow = false;
        if (hasLinkToURL(link)) {
            ((SeleniumContext<WebDriver>) context().getCurrentContext()).getDriver().navigate().to(link);
            canFollow = true;
        }
        return canFollow;
    }

    @Override
    public boolean titleHasText(String text, boolean isPreciseMatch) {

        String title = ((SeleniumContext<WebDriver>) context().getCurrentContext()).getDriver().getTitle();

        boolean matchFound = false;
        if (isPreciseMatch) {
            if (title.contains(text)) matchFound = true;
        } else {
            if (title.equals(text)) matchFound = true;
        }

        if (matchFound) logger.debug("Page title contains: " + text + " Actual Title: " + title);
        else logger.error("Page title does not contain: " + text + " Actual Title: " + title);

        return matchFound;
    }

    @Override
    public boolean hasPartialCookieValue(String cookieName, String cookieValue) {

        try {

            if (((SeleniumContext<WebDriver>) context().getCurrentContext()).getDriver().manage().getCookieNamed(cookieName).getValue().contains(cookieValue)) {
                return true;
            }
        } catch (NullPointerException e) {
            logger.error("Cookie: " + cookieName + " does not exist");
            logger.debug("Cookie Names:");
            for (Cookie cookie : ((SeleniumContext<WebDriver>) context().getCurrentContext()).getDriver().manage().getCookies()) {
                logger.debug(cookie.getName());
            }
            return false;
        }
        logger.error("Cookie does not contain the value: " + cookieValue + ", actual value: " + (((SeleniumContext<WebDriver>) context().getCurrentContext())).getDriver().manage().getCookieNamed(cookieName).getValue());
        return false;

    }

}

interface IProbe extends ISelenium {
    /**
     * Check if the element locator passed to the constructor exists
     *
     * @return {@code true} if the element has been found
     */
    boolean exist();

    /**
     * Probe if {@link SeleniumContext#currentElement} has any text i.e. </br>
     * {@code <div>Text</div>} match will return <b>true</b>.
     * The text to be found can be anywhere in the element text string
     *
     * @return {@code true} if the current WebElement has any text
     */
    boolean hasAnyText();

    /**
     * Probe if {@link SeleniumContext#currentElement} has partial text match e.g.
     * {@code hasPartialText("hello");} matching against {@code <div>hello there<\div>}
     * will return <b>true</b> The text to be found can be a subset of
     * element text string </br> The method can also be used for multiple String elements
     * e.g. {@code hasPartialText("+", "-");
     * </br>(Note once an item is matched it returns true, irrespective of the other checks)
     *
     * @param isCaseSensitiveMatch {@code boolean}
     * @param isPreciseMatch       {@code boolean}
     * @param textElements         the text elements
     * @return {@code true} if the current element contains the text
     */
    boolean hasText(boolean isCaseSensitiveMatch, boolean isPreciseMatch, String... textElements);

    /**
     * Check if {@link SeleniumContext#currentElement} contains specified identifier.
     * The identifier to be found can be a subset of element identifier
     *
     * @param preciseMatch  {@code boolean}
     * @param attributeName the attribute name to check
     * @param attributeText the attribute text to check
     * @return {@code true} if identifier is present and its value matches @param attributeText
     */
    boolean hasAttribute(boolean preciseMatch, String attributeName, String attributeText);

    /**
     * Checks if {@link SeleniumContext#currentElement} of specified HTML tag type.
     *
     * @param tag the tag name to check
     * @return {@code true}, if element of specified tag type
     */
    boolean isOfTagType(String tag);

    /**
     * Checks if {@link SeleniumContext#currentElement} is enabled i.e. can receive focus.
     *
     * @return {@code true} if element is enabled
     */
    boolean isEnabled();

    /**
     * Checks if {@link SeleniumContext#currentElement} is selected.
     * Checkboxes, options in select and radio buttons.
     *
     * @return {@code true} if element is selected
     */
    boolean isSelected();

    /**
     * Checks {@link SeleniumContext#currentElement} is active.
     *
     * @return {@code true} if element is active
     */
    boolean isActive();

    /**
     * Checks {@link SeleniumContext#currentElement} is displayed.
     *
     * @return {@code true} if element is displayed
     */
    boolean isDisplayed();

    /**
     * Check if {@link SeleniumContext#currentElement} has color as expected
     *
     * @param css                  CSS definition {@link CommonPageDefinitions.CSS}
     * @param expectedColorHexCode HEX color code
     * @return {@code true} if the color matches
     */
    boolean hasColour(CommonPageDefinitions.CSS css, String expectedColorHexCode);

    /**
     * Check if the current URL that the browser is looking at is same as {@code @param expectedURL}
     *
     * @param expectedURL    String the expected URL
     * @param isPreciseMatch boolean is precise match
     * @return {@code true} if the url matches
     */
    boolean hasURL(String expectedURL, boolean isPreciseMatch);


    /**
     * Check if {@link SeleniumContext#currentElement} has a title as expected
     *
     * @param expectedTitle  String
     * @param isPreciseMatch boolean
     * @return {@code true} if the title matches
     */
    boolean hasTitle(String expectedTitle, boolean isPreciseMatch);

    /**
     * Contains image reference. Either exact or as a subset i.e. 'image.gif' =
     * 'c:/images/image.gif' = true.
     *
     * @param imagePath String path to image
     * @return true if image found false if not found or the current element is
     * not an <IMG> tag
     */
    boolean hasPartialImagePath(String imagePath);

    /**
     * Check if {@link SeleniumContext#currentElement} has link to url
     *
     * @param url expected url
     * @return {@code true} if it has link to url
     */
    boolean hasLinkToURL(String url);

    /**
     * Check if {@link SeleniumContext#currentElement} has link to url
     * and if it does - navigate to it
     *
     * @param link URL
     * @return {@code true} if it has link to url and it was able to navigate
     */
    boolean followLinkToURL(String link);

    /**
     * Check if {@link SeleniumContext#currentElement} has title with text
     *
     * @param text           expected text
     * @param isPreciseMatch boolean
     * @return {@code true} if it has has title with text
     */
    boolean titleHasText(String text, boolean isPreciseMatch);

    /**
     * Check if the cookie has text
     *
     * @param cookieName
     * @param cookieValue
     * @return
     */
    boolean hasPartialCookieValue(String cookieName, String cookieValue);

}