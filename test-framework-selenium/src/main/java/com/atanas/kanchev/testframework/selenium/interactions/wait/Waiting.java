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

package com.atanas.kanchev.testframework.selenium.interactions.wait;

import com.atanas.kanchev.testframework.selenium.handlers.Waits;
import com.atanas.kanchev.testframework.selenium.interactions.wait.interactions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Atanas Kanchev
 */
public class Waiting extends AbstractWaitInteraction implements IExpectedCondition {

    static final Logger logger = LoggerFactory.getLogger(Waits.class);

    /**
     * Hard exec for fixed time period
     *
     * @param waitInSec exec time in seconds
     * @return this
     */
    public Waiting hardWait(int waitInSec) throws InterruptedException {
        Thread.sleep(waitInSec);
        return this;
    }

    @Override public IExpectedCondition titleIs(String title) {
        new Title().titleIs(title).execute();
        return this;
    }

    @Override public IExpectedCondition titleContains(String title) {
        new Title().titleContains(title).execute();
        return this;
    }

    @Override public IExpectedCondition urlToBe(String url) {
        new Url().urlToBe(url).execute();
        return this;
    }

    @Override public IExpectedCondition urlContains(String fraction) {
        new Url().urlContains(fraction).execute();
        return this;
    }

    @Override public IExpectedCondition urlMatches(String regex) {
        new Url().urlMatches(regex).execute();
        return this;
    }

    @Override public IExpectedCondition presenceOfElementLocated(By locator) {
        new Element().presenceOfElementLocated(locator).execute();
        return this;
    }

    @Override public IExpectedCondition presenceOfAllElementsLocatedBy(By locator) {
        new Element().presenceOfAllElementsLocatedBy(locator).execute();
        return this;
    }

    @Override
    public IExpectedCondition presenceOfNestedElementLocatedBy(By locator, By sub_locator) {
        new Element().presenceOfNestedElementLocatedBy(locator, sub_locator).execute();
        return this;
    }

    @Override
    public IExpectedCondition presenceOfNestedElementLocatedBy(WebElement element, By sub_locator) {
        new Element().presenceOfNestedElementLocatedBy(element, sub_locator).execute();
        return this;
    }

    @Override
    public IExpectedCondition presenceOfNestedElementsLocatedBy(By locator, By sub_locator) {
        new Element().presenceOfNestedElementsLocatedBy(locator, sub_locator).execute();
        return this;
    }

    @Override public IExpectedCondition visibilityOfElementLocated(By locator) {
        new Element().visibilityOfElementLocated(locator).execute();
        return this;
    }

    @Override public IExpectedCondition visibilityOfAllElementsLocatedBy(By locator) {
        new Element().visibilityOfAllElementsLocatedBy(locator).execute();
        return this;
    }

    @Override public IExpectedCondition visibilityOfAllElements(List<WebElement> elements) {
        new Element().visibilityOfAllElements(elements).execute();
        return this;
    }

    @Override public IExpectedCondition visibilityOf(WebElement element) {
        new Element().visibilityOf(element).execute();
        return this;
    }

    @Override public IExpectedCondition textToBePresentInElement(WebElement element, String text) {
        new Element().textToBePresentInElement(element, text).execute();
        return this;
    }

    @Override public IExpectedCondition textToBePresentInElementLocated(By locator, String text) {
        new Element().textToBePresentInElementLocated(locator, text).execute();
        return this;
    }

    @Override
    public IExpectedCondition textToBePresentInElementValue(WebElement element, String text) {
        new Element().textToBePresentInElementValue(element, text).execute();
        return this;
    }

    @Override public IExpectedCondition textToBePresentInElementValue(By locator, String text) {
        new Element().textToBePresentInElementValue(locator, text).execute();
        return this;
    }

    @Override public IExpectedCondition invisibilityOfElementLocated(By locator) {
        new Element().invisibilityOfElementLocated(locator).execute();
        return this;
    }

    @Override public IExpectedCondition invisibilityOfElementWithText(By locator, String text) {
        new Element().invisibilityOfElementWithText(locator, text).execute();
        return this;
    }

    @Override public IExpectedCondition elementToBeClickable(By locator) {
        new Element().elementToBeClickable(locator).execute();
        return this;
    }

    @Override public IExpectedCondition elementToBeClickable(WebElement element) {
        new Element().elementToBeClickable(element).execute();
        return this;
    }

    @Override public IExpectedCondition stalenessOf(WebElement element) {
        new Element().stalenessOf(element).execute();
        return this;
    }

    @Override public IExpectedCondition elementToBeSelected(WebElement element) {
        new Element().elementToBeSelected(element).execute();
        return this;
    }

    @Override
    public IExpectedCondition elementSelectionStateToBe(WebElement element, boolean selected) {
        new Element().elementSelectionStateToBe(element, selected).execute();
        return this;
    }

    @Override public IExpectedCondition elementToBeSelected(By locator) {
        new Element().elementToBeSelected(locator).execute();
        return this;
    }

    @Override public IExpectedCondition elementSelectionStateToBe(By locator, boolean selected) {
        new Element().elementSelectionStateToBe(locator, selected).execute();
        return this;
    }

    @Override public IExpectedCondition attributeToBe(By locator, String attribute, String value) {
        new Element().attributeToBe(locator, attribute, value).execute();
        return this;
    }

    @Override public IExpectedCondition textToBe(By locator, String value) {
        new Element().textToBe(locator, value).execute();
        return this;
    }

    @Override public IExpectedCondition textMatches(By locator, Pattern pattern) {
        new Element().textMatches(locator, pattern).execute();
        return this;
    }

    @Override public IExpectedCondition numberOfElementsToBeMoreThan(By locator, Integer number) {
        new Element().numberOfElementsToBeMoreThan(locator, number).execute();
        return this;
    }

    @Override public IExpectedCondition numberOfElementsToBeLessThan(By locator, Integer number) {
        new Element().numberOfElementsToBeLessThan(locator, number).execute();
        return this;
    }

    @Override public IExpectedCondition numberOfElementsToBe(By locator, Integer number) {
        new Element().numberOfElementsToBe(locator, number).execute();
        return this;
    }

    @Override
    public IExpectedCondition attributeToBe(WebElement element, String attribute, String value) {
        new Element().attributeToBe(element, attribute, value).execute();
        return this;
    }

    @Override public IExpectedCondition attributeContains(WebElement element, String attribute,
        String value) {
        new Element().attributeContains(element, attribute, value).execute();
        return this;
    }

    @Override
    public IExpectedCondition attributeContains(By locator, String attribute, String value) {
        new Element().attributeContains(locator, attribute, value).execute();
        return this;
    }

    @Override
    public IExpectedCondition attributeToBeNotEmpty(WebElement element, String attribute) {
        new Element().attributeToBeNotEmpty(element, attribute).execute();
        return this;
    }

    @Override
    public IExpectedCondition visibilityOfNestedElementsLocatedBy(By locator, By sub_locator) {
        new Element().visibilityOfNestedElementsLocatedBy(locator, sub_locator).execute();
        return this;
    }

    @Override public IExpectedCondition visibilityOfNestedElementsLocatedBy(WebElement element,
        By sub_locator) {
        new Element().visibilityOfNestedElementsLocatedBy(element, sub_locator).execute();
        return this;
    }

    @Override public IExpectedCondition invisibilityOfAllElements(List<WebElement> elements) {
        new Element().invisibilityOfAllElements(elements).execute();
        return this;
    }

    @Override public IExpectedCondition or(ExpectedCondition<?>... conditions) {
        new Compose().or(conditions).execute();
        return this;
    }

    @Override public IExpectedCondition and(ExpectedCondition<?>... conditions) {
        new Compose().and(conditions).execute();
        return this;
    }

    @Override public IExpectedCondition not(ExpectedCondition<?> condition) {
        new Compose().not(condition);
        return this;
    }

    @Override public <T> IExpectedCondition refreshed(ExpectedCondition<T> condition) {
        new Element().refreshed(condition).execute();
        return this;
    }

    @Override public IExpectedCondition javaScriptThrowsNoExceptions(String javaScript) {
        new JS().javaScriptThrowsNoExceptions(javaScript).execute();
        return this;
    }

    @Override public IExpectedCondition jsReturnsValue(String javaScript) {
        new JS().jsReturnsValue(javaScript).execute();
        return this;
    }

    @Override public IExpectedCondition frameToBeAvailableAndSwitchToIt(String frameLocator) {
        new Frame().frameToBeAvailableAndSwitchToIt(frameLocator).execute();
        return this;
    }

    @Override public IExpectedCondition frameToBeAvailableAndSwitchToIt(By locator) {
        new Frame().frameToBeAvailableAndSwitchToIt(locator).execute();
        return this;
    }

    @Override public IExpectedCondition frameToBeAvailableAndSwitchToIt(int frameLocator) {
        new Frame().frameToBeAvailableAndSwitchToIt(frameLocator).execute();
        return this;
    }

    @Override public IExpectedCondition frameToBeAvailableAndSwitchToIt(WebElement frameLocator) {
        new Frame().frameToBeAvailableAndSwitchToIt(frameLocator).execute();
        return this;
    }

    @Override public IExpectedCondition alertIsPresent() {
        new Alert().alertIsPresent().execute();
        return this;
    }

    @Override public IExpectedCondition numberOfWindowsToBe(int expectedNumberOfWindows) {
        new Window().numberOfWindowsToBe(expectedNumberOfWindows).execute();
        return this;
    }


}
