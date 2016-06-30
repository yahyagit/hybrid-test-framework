package com.atanas.kanchev.testframework.core.handlers;

import com.atanas.kanchev.testframework.commons.exceptions.CustomExceptions;
import com.atanas.kanchev.testframework.core.context.ContextFactory;
import com.atanas.kanchev.testframework.core.context.WebContext;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Quotes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.atanas.kanchev.testframework.core.context.ContextFactory.getCurrentContext;

/**
 * @author Atanas Ksnchev
 */
public final class Finder implements IWrapper {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(Finder.class);

    public Finder() {
        goToRootElement();
    }

    public Finder(Class<?> clasz) {
        PageFactory.initElements((WebDriver) ContextFactory.getCurrentContext().getDriver(), clasz);
    }

    public Finder(WebElement element) {
        ((WebContext) getCurrentContext()).setCurrentElement(element);
    }

    /**
     * Go to the root page element /html/body
     *
     * @return this
     */
    public Finder goToRootElement() {

        try {
            ((WebContext) getCurrentContext()).setCurrentElement(new LocatorFactory().findElement(By.xpath("/html/body")));
        } catch (NoSuchElementException nsee) {
            logger.error("Unable to return to Root Element - Body");
        }

        return this;
    }

    /**
     * Go to WebElement
     *
     * @param locator String
     * @return this
     */
    public Finder elementBy(final By locator) {
        if (locator == null)
            throw new CustomExceptions.Common.NullArgumentException();
        else
            ((WebContext) getCurrentContext()).setCurrentElement(new LocatorFactory().findElement(locator));
        return this;
    }

    /**
     * Finder elements by
     *
     * @param locator locator
     * @return this
     */
    public Finder elementsBy(final By locator) {

        if (locator == null)
            throw new CustomExceptions.Common.NullArgumentException();
        else
            ((WebContext) getCurrentContext()).setWebElementsList(new LocatorFactory().findElements(locator));
        return this;

    }

    /**
     * Go to element using WebElement instance
     *
     * @param element WebElement
     * @return this
     */
    public Finder webElement(final WebElement element) {

        if (element == null)
            throw new CustomExceptions.Common.NullArgumentException("Null method argument: WebElement element");
        else
            ((WebContext) getCurrentContext()).setCurrentElement(element);

        return this;
    }

    /**
     * Finder element containing given text
     *
     * @param text Text to search
     * @return this
     */
    public Finder containsText(final String text) {

        if (text == null)
            throw new CustomExceptions.Common.NullArgumentException();
        else
            ((WebContext) getCurrentContext()).setCurrentElement(
                    new LocatorFactory().findElement(By.xpath(".//*/text()[contains(normalize-space(.), " + Quotes.escape(text) + ")]/parent::*")));
        return this;

    }

    /**
     * Finder element having exact text
     *
     * @param text Text to search
     * @return this
     */
    public Finder havingText(final String text) {

        if (text == null)
            throw new CustomExceptions.Common.NullArgumentException();
        else
            ((WebContext) getCurrentContext()).setCurrentElement(
                    new LocatorFactory().findElement(By.xpath(".//*/text()[normalize-space(.) = " + Quotes.escape(text) + "]/parent::*")));
        return this;

    }

    public Finder scrollToElement(final By locator) {

        if (locator == null)
            throw new CustomExceptions.Common.NullArgumentException();
        else {
            ((WebContext) getCurrentContext()).setCurrentElement(new LocatorFactory().findElement(locator));
            new JSExecutor().executeScript(
                    ((WebContext<WebDriver>) getCurrentContext()).getDriver(),
                    "arguments[0].scrollIntoView(true);",
                    ((WebContext) getCurrentContext()).getCurrentElement());
        }
        return this;
    }

    public Finder scrollToElementByAttribute(String attribute, String value) {

        ((WebContext) getCurrentContext()).setCurrentElement(new LocatorFactory().findElement(By.xpath("//*[contains(@" + attribute + ", '" + value + "')]")));
        new JSExecutor().executeScript(
                ((WebContext<WebDriver>) getCurrentContext()).getDriver(),
                "arguments[0].scrollIntoView(true);",
                ((WebContext) getCurrentContext()).getCurrentElement());

        return this;
    }

    public Finder scrollToElementByTag(String tag, String value) {

        ((WebContext) getCurrentContext()).setCurrentElement(new LocatorFactory().findElement(By.xpath("//*[contains(" + tag + ", '" + value + "')]")));
        new JSExecutor().executeScript(
                ((WebContext<WebDriver>) getCurrentContext()).getDriver(),
                "arguments[0].scrollIntoView(true);",
                ((WebContext) getCurrentContext()).getCurrentElement());

        return this;
    }

    public Finder scrollToElementByText(String text, boolean isExactMatch) {

        if (isExactMatch)
            ((WebContext) getCurrentContext()).setCurrentElement(new LocatorFactory().findElement(By.xpath("//*[.=\"" + text + "\"]")));
        else
            ((WebContext) getCurrentContext()).setCurrentElement(new LocatorFactory().findElement(By.xpath("//*[contains(text(), \"" + text + "\")]")));

        new JSExecutor().executeScript(
                ((WebContext<WebDriver>) getCurrentContext()).getDriver(),
                "arguments[0].scrollIntoView(true);",
                ((WebContext) getCurrentContext()).getCurrentElement());

        return this;
    }

    /**
     * Navigate to element by ATTRIBUTE and Value
     * example usage i.e. {@code <input placeholder="Username" type="text" autocapitalize="off" name="username" class="input" data-trk-fieldname="Username">}
     * </br> </br>
     * byAttributeValue("placeholder", "Username")
     *
     * @param attribute the attribute
     * @param value     the value of the attribute
     * @return this
     */
    public Finder byAttributeValue(String attribute, String value) {

        if (attribute == null || value == null)
            throw new CustomExceptions.Common.NullArgumentException();
        else
            ((WebContext) getCurrentContext()).setCurrentElement(
                    new LocatorFactory().findElement(By.cssSelector("[" + attribute + "='" + value + "']")));

        return this;
    }

    /**
     * From the current element, navigate to the label element for the element
     * that has this identifier.
     *
     * @param id Element identifier
     * @return true if element exists and a label for this element exists. The
     * current element pointer is set to this label element location,
     * false otherwise and the current pointer element is unchanged.
     */
    public Finder labelForId(String id) {
        boolean labelFound = false;

        ((WebContext) getCurrentContext()).setWebElementsList(
                new LocatorFactory().findElements(By.tagName(CommonPageDefinitions.HTML.LABEL.getDefinition())));
        if (!((WebContext) getCurrentContext()).getWebElementsList().isEmpty()) {
            for (WebElement labelElement : ((WebContext<WebDriver>) getCurrentContext()).getWebElementsList()) {
                String s = labelElement.getAttribute(CommonPageDefinitions.HTML.ATTRIBUTE_FOR.getDefinition());
                if (s.equals(id)) {
                    ((WebContext) getCurrentContext()).setCurrentElement(labelElement);
                    labelFound = true;
                    break;
                }
            }
            if (!labelFound) throw new RuntimeException("Label not found");
        }
        return this;
    }

    public Finder byHref(String href) {

        boolean navigated = false;
        ((WebContext) getCurrentContext()).setWebElementsList(
                new LocatorFactory().findElements(By.tagName(CommonPageDefinitions.HTML.ANCHOR.getDefinition())));
        if (!((WebContext) getCurrentContext()).getWebElementsList().isEmpty()) {
            for (WebElement anchor : ((WebContext<WebDriver>) getCurrentContext()).getWebElementsList()) {
                if (anchor.getAttribute("href").equals(href)) {
                    ((WebContext) getCurrentContext()).setCurrentElement(anchor);
                    navigated = true;
                    break;
                }
            }
            if (!navigated) throw new RuntimeException("Label not found");
        }

        return this;
    }

    /**
     * Step into child element. Current element pointer unaffected if the
     * element has no child.
     *
     * @return
     */
    public Finder navigateToChild() {

        // Step into child element
        ((WebContext) getCurrentContext()).setCurrentElement(new LocatorFactory().findElement(By.xpath("./*[1]")));

        return this;

    }

    /**
     * Step out to parent element. Current element pointer unaffected if the
     * element has no parent.
     */
    public Finder navigateToParent() {

        // Step up to parent element
        ((WebContext) getCurrentContext()).setCurrentElement(new LocatorFactory().findElement(By.xpath("..")));
        return this;
    }

}