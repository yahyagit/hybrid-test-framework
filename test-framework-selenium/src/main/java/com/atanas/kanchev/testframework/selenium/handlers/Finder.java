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
import com.atanas.kanchev.testframework.selenium.accessors.SeleniumAccessorsSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Quotes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.atanas.kanchev.testframework.commons.accessors.ContextsAccessor.context;
import static com.atanas.kanchev.testframework.selenium.accessors.SeleniumAccessorsSingleton.currentContextKey;

public final class Finder implements IFinder {

    private static final Logger logger = LoggerFactory.getLogger(Finder.class.getName());

    public Finder() {

        goToRootElement();
    }

    public Finder(Class<?> clazz) {
        this();
        PageFactory.initElements(
            context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver(), clazz);
    }

    public Finder(By locator) {
        this();
        context().getContext(currentContextKey).setCurrentElement(findElement(locator));
    }

    public Finder(WebElement element) {
        this();
        context().getContext(currentContextKey).setCurrentElement(element);
    }

    @Override public Finder elementBy(final By locator) {
        if (locator == null)
            throw new CustomExceptions.Common.NullArgumentException();
        else
            context().getContext(currentContextKey).setCurrentElement(findElement(locator));
        return this;
    }

    @Override public Finder elementsBy(final By locator) {

        if (locator == null)
            throw new CustomExceptions.Common.NullArgumentException();
        else
            context().getContext(currentContextKey).setWebElementsList(findElements(locator));
        return this;

    }

    @Override public Finder byWebElement(final WebElement element) {

        if (element == null)
            throw new CustomExceptions.Common.NullArgumentException(
                "Null method argument: WebElement element");
        else
            context().getContext(currentContextKey).setCurrentElement(element);

        return this;
    }

    @Override public Finder containingText(final String text) {

        if (text == null)
            throw new CustomExceptions.Common.NullArgumentException();
        else
            context().getContext(currentContextKey).setCurrentElement(findElement(By.xpath(
                ".//*/text()[contains(normalize-space(.), " + Quotes.escape(text)
                    + ")]/parent::*")));
        return this;

    }

    @Override public Finder havingText(final String text) {

        if (text == null)
            throw new CustomExceptions.Common.NullArgumentException();
        else
            context().getContext(currentContextKey).setCurrentElement(findElement(By.xpath(
                ".//*/text()[normalize-space(.) = " + Quotes.escape(text) + "]/parent::*")));
        return this;

    }

    @Override public Finder byScrollingToElement(final By locator) {

        if (locator == null)
            throw new CustomExceptions.Common.NullArgumentException();
        else {
            context().getContext(currentContextKey).setCurrentElement(findElement(locator));
            new JSExecutor().executeScript(
                context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver(),
                "arguments[0].scrollIntoView(true);",
                context().getContext(currentContextKey).getCurrentElement());
        }
        return this;
    }

    @Override public Finder byScrollingByAttribute(String attribute, String value) {

        context().getContext(currentContextKey).setCurrentElement(
            findElement(By.xpath("//*[contains(@" + attribute + ", '" + value + "')]")));
        new JSExecutor().executeScript(
            context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver(),
            "arguments[0].scrollIntoView(true);",
            context().getContext(currentContextKey).getCurrentElement());

        return this;
    }

    @Override public Finder byScrollingByTag(String tag, String value) {

        context().getContext(currentContextKey).setCurrentElement(
            findElement(By.xpath("//*[contains(" + tag + ", '" + value + "')]")));
        new JSExecutor().executeScript(
            context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver(),
            "arguments[0].scrollIntoView(true);",
            context().getContext(currentContextKey).getCurrentElement());

        return this;
    }

    @Override public Finder byScrollingByText(String text, boolean isExactMatch) {

        if (isExactMatch)
            context().getContext(currentContextKey)
                .setCurrentElement(findElement(By.xpath("//*[.=\"" + text + "\"]")));
        else
            context().getContext(currentContextKey).setCurrentElement(
                findElement(By.xpath("//*[contains(text(), \"" + text + "\")]")));

        new JSExecutor().executeScript(
            context().getContext(SeleniumAccessorsSingleton.currentContextKey).getDriver(),
            "arguments[0].scrollIntoView(true);",
            context().getContext(currentContextKey).getCurrentElement());

        return this;
    }

    @Override public Finder byAttributeValue(String attribute, String value) {

        if (attribute == null || value == null)
            throw new CustomExceptions.Common.NullArgumentException();
        else
            context().getContext(currentContextKey).setCurrentElement(
                findElement(By.cssSelector("[" + attribute + "='" + value + "']")));

        return this;
    }

    //    /**
    //     * {@inheritDoc}
    //     */
    //    @Override public Finder byLabelForId(String id) {
    //
    //        boolean labelFound = false;
    //        context().getContext(currentContextKey).setWebElementsList(findElements(By.tagName(CommonPageDefinitions.HTML.LABEL.getDefinition())));
    //        if (!context().getContext(currentContextKey).getWebElementsList().isEmpty()) {
    //            for (WebElement labelElement : context().getContext(currentContextKey).getWebElementsList()) {
    //                String s = labelElement.getAttribute(CommonPageDefinitions.HTML.ATTRIBUTE_FOR.getDefinition());
    //                if (s.equals(id)) {
    //                    context().getContext(currentContextKey).setCurrentElement(labelElement);
    //                    labelFound = true;
    //                    break;
    //                }
    //            }
    //            if (!labelFound)
    //                throw new RuntimeException("Label not found");
    //        }
    //        return this;
    //    }
    //
    //    /**
    //     * {@inheritDoc}
    //     */
    //    @Override public Finder byHref(String href) {
    //
    //        boolean navigated = false;
    //        context().getContext(currentContextKey).setWebElementsList(
    //            findElements(By.tagName(CommonPageDefinitions.HTML.ANCHOR.getDefinition())));
    //        if (!context().getContext(currentContextKey).getWebElementsList().isEmpty()) {
    //            for (WebElement anchor : context().getContext(currentContextKey).getWebElementsList()) {
    //                if (anchor.getAttribute("href").equals(href)) {
    //                    context().getContext(currentContextKey).setCurrentElement(anchor);
    //                    navigated = true;
    //                    break;
    //                }
    //            }
    //            if (!navigated)
    //                throw new RuntimeException("Label not found");
    //        }
    //
    //        return this;
    //    }

    @Override public Finder goToRootElement() {

        try {
            context().getContext(currentContextKey)
                .setCurrentElement(findElement(By.xpath("/html/body")));
        } catch (NoSuchElementException nsee) {
            logger.error("Unable to return to Root Element - Body");
        }

        return this;
    }

    @Override public Finder goToChild() {

        // Step into child element
        context().getContext(currentContextKey).setCurrentElement(findElement(By.xpath("./*[1]")));

        return this;

    }

    @Override public Finder goToParent() {

        // Step up to parent element
        context().getContext(currentContextKey).setCurrentElement(findElement(By.xpath("..")));
        return this;
    }

    private WebElement findElement(By locator) {
        logger.debug("Locating element using " + locator.toString());
        try {
            return context().getContext(currentContextKey).getDriver().findElement(locator);
        } catch (NoSuchElementException nsee) {
            throw new NoSuchElementException("Unable to locate element using " + locator.toString(),
                nsee);
        }
    }

    private List<WebElement> findElements(By locator) {
        logger.debug("Locating elements using " + locator.toString());
        try {
            List<WebElement> e =
                context().getContext(currentContextKey).getDriver().findElements(locator);
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
