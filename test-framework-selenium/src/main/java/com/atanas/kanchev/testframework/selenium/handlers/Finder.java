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
 * <p>Finder class.</p>
 *
 * @author Atanas Kanchev
 */
public final class Finder<T extends WebDriver> implements IFinder, IContext<SeleniumContext> {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(Finder.class);

    private final WebDriver driver;

    /**
     * <p>Constructor for Finder.</p>
     */
    public Finder() {
        driver = (WebDriver) context().getCurrentContext().getDriver();
        goToRootElement();
    }

    /**
     * <p>Constructor for Finder.</p>
     *
     * @param clazz a {@link java.lang.Class} object.
     */
    public Finder(Class<?> clazz) {
        this();
        PageFactory.initElements(driver, clazz);
    }

    /**
     * <p>Constructor for Finder.</p>
     *
     * @param element a {@link org.openqa.selenium.WebElement} object.
     */
    public Finder(WebElement element) {
        this();
        context().getCurrentContext().setCurrentElement(element);
    }

    /**
     * {@inheritDoc}
     */
    @Override public Finder elementBy(final By locator) {
        if (locator == null)
            throw new CustomExceptions.Common.NullArgumentException();
        else
            context().getCurrentContext()
                .setCurrentElement(findElement(locator));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public Finder elementsBy(final By locator) {

        if (locator == null)
            throw new CustomExceptions.Common.NullArgumentException();
        else
            context().getCurrentContext()
                .setWebElementsList(findElements(locator));
        return this;

    }

    /**
     * {@inheritDoc}
     */
    @Override public Finder byWebElement(final WebElement element) {

        if (element == null)
            throw new CustomExceptions.Common.NullArgumentException(
                "Null method argument: WebElement element");
        else
            context().getCurrentContext().setCurrentElement(element);

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public Finder containingText(final String text) {

        if (text == null)
            throw new CustomExceptions.Common.NullArgumentException();
        else
            context().getCurrentContext().setCurrentElement(findElement(
                By.xpath(".//*/text()[contains(normalize-space(.), " + Quotes.escape(text)
                    + ")]/parent::*")));
        return this;

    }

    /**
     * {@inheritDoc}
     */
    @Override public Finder havingText(final String text) {

        if (text == null)
            throw new CustomExceptions.Common.NullArgumentException();
        else
            context().getCurrentContext().setCurrentElement(findElement(
                By.xpath(
                    ".//*/text()[normalize-space(.) = " + Quotes.escape(text) + "]/parent::*")));
        return this;

    }

    /**
     * {@inheritDoc}
     */
    @Override public Finder byScrollingToElement(final By locator) {

        if (locator == null)
            throw new CustomExceptions.Common.NullArgumentException();
        else {
            context().getCurrentContext()
                .setCurrentElement(findElement(locator));
            new JSExecutor().executeScript(driver, "arguments[0].scrollIntoView(true);",
                context().getCurrentContext().getCurrentElement());
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public Finder byScrollingByAttribute(String attribute, String value) {

        context().getCurrentContext().setCurrentElement(
            findElement(By.xpath("//*[contains(@" + attribute + ", '" + value + "')]")));
        new JSExecutor().executeScript(driver, "arguments[0].scrollIntoView(true);",
            context().getCurrentContext().getCurrentElement());

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public Finder byScrollingByTag(String tag, String value) {

        context().getCurrentContext().setCurrentElement(
            findElement(By.xpath("//*[contains(" + tag + ", '" + value + "')]")));
        new JSExecutor().executeScript(driver, "arguments[0].scrollIntoView(true);",
            context().getCurrentContext().getCurrentElement());

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public Finder byScrollingByText(String text, boolean isExactMatch) {

        if (isExactMatch)
            context().getCurrentContext()
                .setCurrentElement(findElement(By.xpath("//*[.=\"" + text + "\"]")));
        else
            context().getCurrentContext().setCurrentElement(
                findElement(By.xpath("//*[contains(text(), \"" + text + "\")]")));

        new JSExecutor().executeScript(driver, "arguments[0].scrollIntoView(true);",
            context().getCurrentContext().getCurrentElement());

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public Finder byAttributeValue(String attribute, String value) {

        if (attribute == null || value == null)
            throw new CustomExceptions.Common.NullArgumentException();
        else
            context().getCurrentContext().setCurrentElement(
                findElement(By.cssSelector("[" + attribute + "='" + value + "']")));

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public Finder byLabelForId(String id) {
        boolean labelFound = false;

        context().getCurrentContext().setWebElementsList(
            findElements(By.tagName(CommonPageDefinitions.HTML.LABEL.getDefinition())));
        if (!context().getCurrentContext().getWebElementsList().isEmpty()) {
            for (WebElement labelElement : ((SeleniumContext<T>)context().getCurrentContext()).getWebElementsList()) {
                String s = labelElement
                    .getAttribute(CommonPageDefinitions.HTML.ATTRIBUTE_FOR.getDefinition());
                if (s.equals(id)) {
                    context().getCurrentContext()
                        .setCurrentElement(labelElement);
                    labelFound = true;
                    break;
                }
            }
            if (!labelFound)
                throw new RuntimeException("Label not found");
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public Finder byHref(String href) {

        boolean navigated = false;
        context().getCurrentContext().setWebElementsList(
            findElements(By.tagName(CommonPageDefinitions.HTML.ANCHOR.getDefinition())));
        if (!context().getCurrentContext().getWebElementsList().isEmpty()) {
            for (WebElement anchor : ((SeleniumContext<T>)context().getCurrentContext()).getWebElementsList()) {
                if (anchor.getAttribute("href").equals(href)) {
                    context().getCurrentContext().setCurrentElement(anchor);
                    navigated = true;
                    break;
                }
            }
            if (!navigated)
                throw new RuntimeException("Label not found");
        }

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public Finder goToRootElement() {

        try {
            context().getCurrentContext()
                .setCurrentElement(findElement(By.xpath("/html/body")));
        } catch (NoSuchElementException nsee) {
            logger.error("Unable to return to Root Element - Body");
        }

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public Finder goToChild() {

        // Step into child element
        context().getCurrentContext()
            .setCurrentElement(findElement(By.xpath("./*[1]")));

        return this;

    }

    /**
     * {@inheritDoc}
     */
    @Override public Finder goToParent() {

        // Step up to parent element
        context().getCurrentContext()
            .setCurrentElement(findElement(By.xpath("..")));
        return this;
    }

    private WebElement findElement(By locator) {
        logger.debug("Locating element using " + locator.toString());
        try {
            return driver.findElement(locator);
        } catch (NoSuchElementException nsee) {
            throw new NoSuchElementException("Unable to locate element using " + locator.toString(),
                nsee);
        }
    }

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
            throw new NoSuchElementException(
                "Unable to locate any elements using " + locator.toString(), nsee);
        }
    }

}

interface IFinder extends ISelenium {

    /**
     * <p>goToRootElement.</p>
     *
     * @return a {@link com.atanas.kanchev.testframework.selenium.handlers.IFinder} object.
     */
    IFinder goToRootElement();

    /**
     * <p>elementBy.</p>
     *
     * @param locator a {@link org.openqa.selenium.By} object.
     * @return a {@link com.atanas.kanchev.testframework.selenium.handlers.IFinder} object.
     */
    IFinder elementBy(final By locator);

    /**
     * <p>elementsBy.</p>
     *
     * @param locator a {@link org.openqa.selenium.By} object.
     * @return a {@link com.atanas.kanchev.testframework.selenium.handlers.IFinder} object.
     */
    IFinder elementsBy(final By locator);

    /**
     * <p>byWebElement.</p>
     *
     * @param element a {@link org.openqa.selenium.WebElement} object.
     * @return a {@link com.atanas.kanchev.testframework.selenium.handlers.IFinder} object.
     */
    IFinder byWebElement(final WebElement element);

    /**
     * <p>containingText.</p>
     *
     * @param text a {@link java.lang.String} object.
     * @return a {@link com.atanas.kanchev.testframework.selenium.handlers.IFinder} object.
     */
    IFinder containingText(final String text);

    /**
     * <p>havingText.</p>
     *
     * @param text a {@link java.lang.String} object.
     * @return a {@link com.atanas.kanchev.testframework.selenium.handlers.IFinder} object.
     */
    IFinder havingText(final String text);

    /**
     * <p>byScrollingToElement.</p>
     *
     * @param locator a {@link org.openqa.selenium.By} object.
     * @return a {@link com.atanas.kanchev.testframework.selenium.handlers.IFinder} object.
     */
    IFinder byScrollingToElement(final By locator);

    /**
     * <p>byScrollingByAttribute.</p>
     *
     * @param attribute a {@link java.lang.String} object.
     * @param value     a {@link java.lang.String} object.
     * @return a {@link com.atanas.kanchev.testframework.selenium.handlers.IFinder} object.
     */
    IFinder byScrollingByAttribute(String attribute, String value);

    /**
     * <p>byScrollingByTag.</p>
     *
     * @param tag   a {@link java.lang.String} object.
     * @param value a {@link java.lang.String} object.
     * @return a {@link com.atanas.kanchev.testframework.selenium.handlers.IFinder} object.
     */
    IFinder byScrollingByTag(String tag, String value);

    /**
     * <p>byScrollingByText.</p>
     *
     * @param text         a {@link java.lang.String} object.
     * @param isExactMatch a boolean.
     * @return a {@link com.atanas.kanchev.testframework.selenium.handlers.IFinder} object.
     */
    IFinder byScrollingByText(String text, boolean isExactMatch);

    /**
     * <p>byAttributeValue.</p>
     *
     * @param attribute a {@link java.lang.String} object.
     * @param value     a {@link java.lang.String} object.
     * @return a {@link com.atanas.kanchev.testframework.selenium.handlers.IFinder} object.
     */
    IFinder byAttributeValue(String attribute, String value);

    /**
     * <p>byLabelForId.</p>
     *
     * @param id a {@link java.lang.String} object.
     * @return a {@link com.atanas.kanchev.testframework.selenium.handlers.IFinder} object.
     */
    IFinder byLabelForId(String id);

    /**
     * <p>byHref.</p>
     *
     * @param href a {@link java.lang.String} object.
     * @return a {@link com.atanas.kanchev.testframework.selenium.handlers.IFinder} object.
     */
    IFinder byHref(String href);

    /**
     * <p>goToChild.</p>
     *
     * @return a {@link com.atanas.kanchev.testframework.selenium.handlers.IFinder} object.
     */
    IFinder goToChild();


    /**
     * <p>goToParent.</p>
     *
     * @return a {@link com.atanas.kanchev.testframework.selenium.handlers.IFinder} object.
     */
    IFinder goToParent();
}
