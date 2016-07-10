package com.atanas.kanchev.testframework.core.handlers;

import com.atanas.kanchev.testframework.commons.exceptions.CustomExceptions;
import com.atanas.kanchev.testframework.core.context.SeleniumContext;
import com.atanas.kanchev.testframework.core.handlers.wrappers.IContext;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Quotes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Selenium Finder Factory
 *
 * @author Atanas Ksnchev
 */
public final class Finder implements IFinder, IContext {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(Finder.class);

    private final WebDriver driver;

    public Finder() {
        driver = (WebDriver) context().getCurrentContext().getDriver();
        goToRootElement();
    }

    public Finder(Class<?> clazz) {
        this();
        PageFactory.initElements(driver, clazz);
    }

    public Finder(WebElement element) {
        this();
        ((SeleniumContext) context().getCurrentContext()).setCurrentElement(element);
    }

    @Override
    public Finder elementBy(final By locator) {
        if (locator == null)
            throw new CustomExceptions.Common.NullArgumentException();
        else
            ((SeleniumContext) context().getCurrentContext()).setCurrentElement(findElement(locator));
        return this;
    }

    @Override
    public Finder elementsBy(final By locator) {

        if (locator == null)
            throw new CustomExceptions.Common.NullArgumentException();
        else
            ((SeleniumContext) context().getCurrentContext()).setWebElementsList(findElements(locator));
        return this;

    }

    @Override
    public Finder byWebElement(final WebElement element) {

        if (element == null)
            throw new CustomExceptions.Common.NullArgumentException("Null method argument: WebElement element");
        else
            ((SeleniumContext) context().getCurrentContext()).setCurrentElement(element);

        return this;
    }

    @Override
    public Finder containingText(final String text) {

        if (text == null)
            throw new CustomExceptions.Common.NullArgumentException();
        else
            ((SeleniumContext) context().getCurrentContext()).setCurrentElement(
                    findElement(By.xpath(".//*/text()[contains(normalize-space(.), " + Quotes.escape(text) + ")]/parent::*")));
        return this;

    }

    @Override
    public Finder havingText(final String text) {

        if (text == null)
            throw new CustomExceptions.Common.NullArgumentException();
        else
            ((SeleniumContext) context().getCurrentContext()).setCurrentElement(
                    findElement(By.xpath(".//*/text()[normalize-space(.) = " + Quotes.escape(text) + "]/parent::*")));
        return this;

    }

    @Override
    public Finder byScrollingToElement(final By locator) {

        if (locator == null)
            throw new CustomExceptions.Common.NullArgumentException();
        else {
            ((SeleniumContext) context().getCurrentContext()).setCurrentElement(findElement(locator));
            new JSExecutor().executeScript(driver, "arguments[0].scrollIntoView(true);",
                    ((SeleniumContext) context().getCurrentContext()).getCurrentElement());
        }
        return this;
    }

    @Override
    public Finder byScrollingByAttribute(String attribute, String value) {

        ((SeleniumContext) context().getCurrentContext()).setCurrentElement(findElement(By.xpath("//*[contains(@" + attribute + ", '" + value + "')]")));
        new JSExecutor().executeScript(driver, "arguments[0].scrollIntoView(true);",
                ((SeleniumContext) context().getCurrentContext()).getCurrentElement());

        return this;
    }

    @Override
    public Finder byScrollingByTag(String tag, String value) {

        ((SeleniumContext) context().getCurrentContext()).setCurrentElement(findElement(By.xpath("//*[contains(" + tag + ", '" + value + "')]")));
        new JSExecutor().executeScript(driver, "arguments[0].scrollIntoView(true);",
                ((SeleniumContext) context().getCurrentContext()).getCurrentElement());

        return this;
    }

    @Override
    public Finder byScrollingByText(String text, boolean isExactMatch) {

        if (isExactMatch)
            ((SeleniumContext) context().getCurrentContext()).setCurrentElement(findElement(By.xpath("//*[.=\"" + text + "\"]")));
        else
            ((SeleniumContext) context().getCurrentContext()).setCurrentElement(findElement(By.xpath("//*[contains(text(), \"" + text + "\")]")));

        new JSExecutor().executeScript(driver, "arguments[0].scrollIntoView(true);",
                ((SeleniumContext) context().getCurrentContext()).getCurrentElement());

        return this;
    }

    @Override
    public Finder byAttributeValue(String attribute, String value) {

        if (attribute == null || value == null)
            throw new CustomExceptions.Common.NullArgumentException();
        else
            ((SeleniumContext) context().getCurrentContext()).setCurrentElement(
                    findElement(By.cssSelector("[" + attribute + "='" + value + "']")));

        return this;
    }

    @Override
    public Finder byLabelForId(String id) {
        boolean labelFound = false;

        ((SeleniumContext) context().getCurrentContext()).setWebElementsList(
                findElements(By.tagName(CommonPageDefinitions.HTML.LABEL.getDefinition())));
        if (!((SeleniumContext) context().getCurrentContext()).getWebElementsList().isEmpty()) {
            for (WebElement labelElement : ((SeleniumContext<WebDriver>) context().getCurrentContext()).getWebElementsList()) {
                String s = labelElement.getAttribute(CommonPageDefinitions.HTML.ATTRIBUTE_FOR.getDefinition());
                if (s.equals(id)) {
                    ((SeleniumContext) context().getCurrentContext()).setCurrentElement(labelElement);
                    labelFound = true;
                    break;
                }
            }
            if (!labelFound) throw new RuntimeException("Label not found");
        }
        return this;
    }

    @Override
    public Finder byHref(String href) {

        boolean navigated = false;
        ((SeleniumContext) context().getCurrentContext()).setWebElementsList(
                findElements(By.tagName(CommonPageDefinitions.HTML.ANCHOR.getDefinition())));
        if (!((SeleniumContext) context().getCurrentContext()).getWebElementsList().isEmpty()) {
            for (WebElement anchor : ((SeleniumContext<WebDriver>) context().getCurrentContext()).getWebElementsList()) {
                if (anchor.getAttribute("href").equals(href)) {
                    ((SeleniumContext) context().getCurrentContext()).setCurrentElement(anchor);
                    navigated = true;
                    break;
                }
            }
            if (!navigated) throw new RuntimeException("Label not found");
        }

        return this;
    }

    @Override
    public Finder goToRootElement() {

        try {
            ((SeleniumContext) context().getCurrentContext()).setCurrentElement(findElement(By.xpath("/html/body")));
        } catch (NoSuchElementException nsee) {
            logger.error("Unable to return to Root Element - Body");
        }

        return this;
    }

    @Override
    public Finder goToChild() {

        // Step into child element
        ((SeleniumContext) context().getCurrentContext()).setCurrentElement(findElement(By.xpath("./*[1]")));

        return this;

    }

    @Override
    public Finder goToParent() {

        // Step up to parent element
        ((SeleniumContext) context().getCurrentContext()).setCurrentElement(findElement(By.xpath("..")));
        return this;
    }

    /**
     * Locate an element by locator @param org.openqa.selenium.By
     *
     * @param locator Locator type
     * @return WebElement element
     */
    private WebElement findElement(By locator) {
        logger.debug("Locating element using " + locator.toString());
        try {
            return driver.findElement(locator);
        } catch (NoSuchElementException nsee) {
            throw new NoSuchElementException("Unable to locate element using " + locator.toString(), nsee);
        }
    }

    /**
     * Locate all WebElement by locator @param org.openqa.selenium.By
     *
     * @param locator Locator type
     * @return Collection List<WebElement> having all elements found
     */
    private List<WebElement> findElements(By locator) {
        logger.debug("Locating elements using " + locator.toString());
        try {
            List<WebElement> e = driver.findElements(locator);
            int numberOfElementsFound = e.size();
            if (numberOfElementsFound == 0)
                throw new NoSuchElementException("Elements found: " + numberOfElementsFound);
            logger.debug("Elements found: " + numberOfElementsFound);
            return e;
        } catch (NoSuchElementException nsee) {
            throw new NoSuchElementException("Unable to locate any elements using " + locator.toString(), nsee);
        }
    }

}

interface IFinder {

    /**
     * Sets the current element pointer {@link SeleniumContext#currentElement}
     * to the the root page element /html/body
     *
     * @return this
     */
    IFinder goToRootElement();

    /**
     * Find WebElement using {@code locator}
     * <p>
     * Example {@code elementBy(By.id("id_to_search_for"))}
     * Sets the current element pointer {@link SeleniumContext#currentElement} if element is found
     *
     * @param locator {@link By}
     * @return this
     */
    IFinder elementBy(final By locator);

    /**
     * Find all elements using {@code locator}
     * <p>
     * Example {@code elementsBy(By.ByClassName("class_name_to_search_for"))}
     * Sets the elements list pointer {@link SeleniumContext#webElementsList} if sny element is found
     *
     * @param locator {@link By}
     * @return this
     */
    IFinder elementsBy(final By locator);

    /**
     * Set the current element pointer {@link SeleniumContext#currentElement} with {@param element}
     *
     * @param element WebElement
     * @return this
     */
    IFinder byWebElement(final WebElement element);

    /**
     * Find element containing given {@param text}
     * <p>
     * Example {@code containingText("text_to_search")}
     * Sets the current element pointer {@link SeleniumContext#currentElement} if the element is found
     *
     * @param text Text to search
     * @return this
     */
    IFinder containingText(final String text);

    /**
     * Find element having exact {@param text}
     * <p>
     * Example {@code havingText("text_to_search")}
     * Sets the current element pointer {@link SeleniumContext#currentElement} if the element is found
     *
     * @param text Text to search
     * @return this
     */
    IFinder havingText(final String text);

    IFinder byScrollingToElement(final By locator);

    IFinder byScrollingByAttribute(String attribute, String value);

    IFinder byScrollingByTag(String tag, String value);

    IFinder byScrollingByText(String text, boolean isExactMatch);

    /**
     * Navigate to element by ATTRIBUTE and Value
     * <p>
     * Example {@code <input placeholder="Username" type="text" autocapitalize="off" name="username" class="input" data-trk-fieldname="Username">}
     * {@code byAttributeValue("placeholder", "Username")}
     * <p>
     * Sets the current element pointer {@link SeleniumContext#currentElement} if the element is found
     *
     * @param attribute the attribute name
     * @param value     the value of the attribute
     * @return this
     */
    IFinder byAttributeValue(String attribute, String value);

    /**
     * From the current element, navigate to the label element for the element
     * that has this identifier.
     *
     * @param id Element identifier
     * @return true if element exists and a label for this element exists. The
     * current element pointer is set to this label element location,
     * false otherwise and the current pointer element is unchanged.
     */
    IFinder byLabelForId(String id);

    /**
     * @param href
     * @return this
     */
    IFinder byHref(String href);

    /**
     * Step into child element. Current element pointer unaffected if the
     * element has no child.
     *
     * @return this
     */
    IFinder goToChild();


    /**
     * Step out to parent element. Current element pointer unaffected if the
     * element has no parent.
     *
     * @return this
     */
    IFinder goToParent();
}