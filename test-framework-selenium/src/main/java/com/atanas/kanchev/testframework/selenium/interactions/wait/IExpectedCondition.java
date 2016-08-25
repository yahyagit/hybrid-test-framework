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

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Atanas Kanchev
 */
public interface IExpectedCondition {
    /**
     * An expectation for checking the title of a page.
     *
     * @param title the expected title, which must be an exact match
     * @return true when the title matches, false otherwise
     */
    IExpectedCondition titleIs(String title);

    /**
     * An expectation for checking that the title contains a case-sensitive substring
     *
     * @param title the fragment of title expected
     * @return true when the title matches, false otherwise
     */
    IExpectedCondition titleContains(String title);

    /**
     * An expectation for the URL of the current page to be a specific url.
     *
     * @param url the url that the page should be on
     * @return <code>true</code> when the URL is what it should be
     */
    IExpectedCondition urlToBe(String url);

    /**
     * An expectation for the URL of the current page to contain specific text.
     *
     * @param fraction the fraction of the url that the page should be on
     * @return <code>true</code> when the URL contains the text
     */
    IExpectedCondition urlContains(String fraction);

    /**
     * Expectation for the URL to match a specific regular expression
     *
     * @param regex the regular expression that the URL should match
     * @return <code>true</code> if the URL matches the specified regular expression
     */
    IExpectedCondition urlMatches(String regex);

    /**
     * An expectation for checking that an element is present on the DOM of a page. This does not
     * necessarily mean that the element is visible.
     *
     * @param locator used to find the element
     * @return the WebElement once it is located
     */
    IExpectedCondition presenceOfElementLocated(By locator);

    /**
     * An expectation for checking that an element is present on the DOM of a page and visible.
     * Visibility means that the element is not only displayed but also has a height and width that is
     * greater than 0.
     *
     * @param locator used to find the element
     * @return the WebElement once it is located and visible
     */
    IExpectedCondition visibilityOfElementLocated(By locator);

    /**
     * An expectation for checking that all elements present on the web page that match the locator
     * are visible. Visibility means that the elements are not only displayed but also have a height
     * and width that is greater than 0.
     *
     * @param locator used to find the element
     * @return the list of WebElements once they are located
     */
    IExpectedCondition visibilityOfAllElementsLocatedBy(By locator);

    /**
     * An expectation for checking that all elements present on the web page that match the locator
     * are visible. Visibility means that the elements are not only displayed but also have a height
     * and width that is greater than 0.
     *
     * @param elements list of WebElements
     * @return the list of WebElements once they are located
     */
    IExpectedCondition visibilityOfAllElements(List<WebElement> elements);

    /**
     * An expectation for checking that an element, known to be present on the DOM of a page, is
     * visible. Visibility means that the element is not only displayed but also has a height and
     * width that is greater than 0.
     *
     * @param element the WebElement
     * @return the (same) WebElement once it is visible
     */
    IExpectedCondition visibilityOf(WebElement element);

    /**
     * An expectation for checking that there is at least one element present on a web page.
     *
     * @param locator used to find the element
     * @return the list of WebElements once they are located
     */
    IExpectedCondition presenceOfAllElementsLocatedBy(By locator);

    /**
     * An expectation for checking if the given text is present in the specified element.
     *
     * @param element the WebElement
     * @param text    to be present in the element
     * @return true once the element contains the given text
     */
    IExpectedCondition textToBePresentInElement(WebElement element, String text);

    /**
     * An expectation for checking if the given text is present in the element that matches the given
     * locator.
     *
     * @param locator used to find the element
     * @param text    to be present in the element found by the locator
     * @return true once the first element located by locator contains the given text
     */
    IExpectedCondition textToBePresentInElementLocated(By locator, String text);

    /**
     * An expectation for checking if the given text is present in the specified elements value
     * attribute.
     *
     * @param element the WebElement
     * @param text    to be present in the element's value attribute
     * @return true once the element's value attribute contains the given text
     */
    IExpectedCondition textToBePresentInElementValue(WebElement element, String text);

    /**
     * An expectation for checking if the given text is present in the specified elements value
     * attribute.
     *
     * @param locator used to find the element
     * @param text    to be present in the value attribute of the element found by the locator
     * @return true once the value attribute of the first element located by locator contains the
     * given text
     */
    IExpectedCondition textToBePresentInElementValue(By locator, String text);

    /**
     * An expectation for checking whether the given frame is available to switch to. <p> If the frame
     * is available it switches the given driver to the specified frame.
     *
     * @param frameLocator used to find the frame (id or name)
     * @return WebDriver omniaDriver after frame has been switched
     */
    IExpectedCondition frameToBeAvailableAndSwitchToIt(String frameLocator);

    /**
     * An expectation for checking whether the given frame is available to switch to. <p> If the frame
     * is available it switches the given driver to the specified frame.
     *
     * @param locator used to find the frame
     * @return WebDriver omniaDriver after frame has been switched
     */
    IExpectedCondition frameToBeAvailableAndSwitchToIt(By locator);

    /**
     * An expectation for checking whether the given frame is available to switch to. <p> If the frame
     * is available it switches the given driver to the specified frameIndex.
     *
     * @param frameLocator used to find the frame (index)
     * @return WebDriver omniaDriver after frame has been switched
     */
    IExpectedCondition frameToBeAvailableAndSwitchToIt(int frameLocator);

    /**
     * An expectation for checking whether the given frame is available to switch to. <p> If the frame
     * is available it switches the given driver to the specified webelement.
     *
     * @param frameLocator used to find the frame (webelement)
     * @return WebDriver omniaDriver after frame has been switched
     */
    IExpectedCondition frameToBeAvailableAndSwitchToIt(WebElement frameLocator);

    /**
     * An expectation for checking that an element is either invisible or not present on the DOM.
     *
     * @param locator used to find the element
     * @return true if the element is not displayed or the element doesn't exist or stale element
     */
    IExpectedCondition invisibilityOfElementLocated(By locator);

    /**
     * An expectation for checking that an element with text is either invisible or not present on the
     * DOM.
     *
     * @param locator used to find the element
     * @param text    of the element
     * @return true if no such element, stale element or displayed text not equal that provided
     */
    IExpectedCondition invisibilityOfElementWithText(By locator, String text);

    /**
     * An expectation for checking an element is visible and enabled such that you can click it.
     *
     * @param locator used to find the element
     * @return the WebElement once it is located and clickable (visible and enabled)
     */
    IExpectedCondition elementToBeClickable(By locator);

    /**
     * An expectation for checking an element is visible and enabled such that you can click it.
     *
     * @param element the WebElement
     * @return the (same) WebElement once it is clickable (visible and enabled)
     */
    IExpectedCondition elementToBeClickable(WebElement element);

    /**
     * IWait until an element is no longer attached to the DOM.
     *
     * @param element The element to wait for.
     * @return false is the element is still attached to the DOM, true otherwise.
     */
    IExpectedCondition stalenessOf(WebElement element);

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
    <T> IExpectedCondition refreshed(ExpectedCondition<T> condition);

    /**
     * An expectation for checking if the given element is selected.
     *
     * @param element WebElement to be selected
     * @return true once the element is selected
     */
    IExpectedCondition elementToBeSelected(WebElement element);

    /**
     * An expectation for checking if the given element is selected.
     *
     * @param element  WebElement to be selected
     * @param selected boolean state of the selection state of the element
     * @return true once the element's selection stated is that of selected
     */
    IExpectedCondition elementSelectionStateToBe(WebElement element, boolean selected);

    IExpectedCondition elementToBeSelected(By locator);

    IExpectedCondition elementSelectionStateToBe(By locator, boolean selected);

    IExpectedCondition alertIsPresent();

    IExpectedCondition numberOfWindowsToBe(int expectedNumberOfWindows);

    /**
     * An expectation with the logical opposite condition of the given condition.
     * <p>
     * Note that if the Condition your are inverting throws an exception that is caught by the Ignored
     * Exceptions, the inversion will not take place and lead to confusing results.
     *
     * @param condition ExpectedCondition to be inverted
     * @return true once the condition is satisfied
     */
    IExpectedCondition not(ExpectedCondition<?> condition);

    /**
     * An expectation for checking WebElement with given locator has attribute with a specific value
     *
     * @param locator   used to find the element
     * @param attribute used to define css or html attribute
     * @param value     used as expected attribute value
     * @return Boolean true when element has css or html attribute with the value
     */
    IExpectedCondition attributeToBe(By locator, String attribute, String value);

    /**
     * An expectation for checking WebElement with given locator has specific text
     *
     * @param locator used to find the element
     * @param value   used as expected text
     * @return Boolean true when element has text value equal to @value
     */
    IExpectedCondition textToBe(By locator, String value);

    /**
     * An expectation for checking WebElement with given locator has text with a value as a part of
     * it
     *
     * @param locator used to find the element
     * @param pattern used as expected text matcher pattern
     * @return Boolean true when element has text value containing @value
     */
    IExpectedCondition textMatches(By locator, Pattern pattern);

    /**
     * An expectation for checking number of WebElements with given locator
     *
     * @param locator used to find the element
     * @param number  user to define exact number of elements
     * @return Boolean true when size of elements list is equal to defined
     */
    IExpectedCondition numberOfElementsToBeMoreThan(By locator, Integer number);

    /**
     * An expectation for checking number of WebElements with given locator being less than defined
     * number
     *
     * @param locator used to find the element
     * @param number  user to define maximum number of elements
     * @return Boolean true when size of elements list is less than defined
     */
    IExpectedCondition numberOfElementsToBeLessThan(By locator, Integer number);

    /**
     * An expectation for checking number of WebElements with given locator
     *
     * @param locator used to find the element
     * @param number  user to define number of elements
     * @return Boolean true when size of elements list is equal to defined
     */
    IExpectedCondition numberOfElementsToBe(By locator, Integer number);

    /**
     * An expectation for checking given WebElement has attribute with a specific value
     *
     * @param element   used to check it's parameters
     * @param attribute used to define css or html attribute
     * @param value     used as expected attribute value
     * @return Boolean true when element has css or html attribute with the value
     */
    IExpectedCondition attributeToBe(WebElement element, String attribute, String value);

    /**
     * An expectation for checking WebElement with given locator has attribute which contains specific
     * value
     *
     * @param element   used to check it's parameters
     * @param attribute used to define css or html attribute
     * @param value     used as expected attribute value
     * @return Boolean true when element has css or html attribute which contains the value
     */
    IExpectedCondition attributeContains(WebElement element, String attribute, String value);

    /**
     * An expectation for checking WebElement with given locator has attribute which contains specific
     * value
     *
     * @param locator   used to define WebElement to check it's parameters
     * @param attribute used to define css or html attribute
     * @param value     used as expected attribute value
     * @return Boolean true when element has css or html attribute which contains the value
     */
    IExpectedCondition attributeContains(By locator, String attribute, String value);

    /**
     * An expectation for checking WebElement any non empty value for given attribute
     *
     * @param element   used to check it's parameters
     * @param attribute used to define css or html attribute
     * @return Boolean true when element has css or html attribute with non empty value
     */
    IExpectedCondition attributeToBeNotEmpty(WebElement element, String attribute);

    /**
     * An expectation for checking child WebElement as a part of parent element to be visible
     *
     * @param locator     used to check parent element. For example table with locator
     *                    By.xpath("//table")
     * @param sub_locator used to find child element. For example td By.xpath("./tr/td")
     * @return visible nested element
     */
    IExpectedCondition visibilityOfNestedElementsLocatedBy(By locator, By sub_locator);

    /**
     * An expectation for checking child WebElement as a part of parent element to be visible
     *
     * @param element     used as parent element. For example table with locator By.xpath("//table")
     * @param sub_locator used to find child element. For example td By.xpath("./tr/td")
     * @return visible subelement
     */
    IExpectedCondition visibilityOfNestedElementsLocatedBy(WebElement element, By sub_locator);

    /**
     * An expectation for checking child WebElement as a part of parent element to present
     *
     * @param locator     used to check parent element. For example table with locator
     *                    By.xpath("//table")
     * @param sub_locator used to find child element. For example td By.xpath("./tr/td")
     * @return subelement
     */
    IExpectedCondition presenceOfNestedElementLocatedBy(By locator, By sub_locator);

    /**
     * An expectation for checking child WebElement as a part of parent element to be present
     *
     * @param element     used as parent element
     * @param sub_locator used to find child element. For example td By.xpath("./tr/td")
     * @return subelement
     */
    IExpectedCondition presenceOfNestedElementLocatedBy(WebElement element, By sub_locator);

    /**
     * An expectation for checking child WebElement as a part of parent element to present
     *
     * @param locator     used to check parent element. For example table with locator
     *                    By.xpath("//table")
     * @param sub_locator used to find child element. For example td By.xpath("./tr/td")
     * @return subelement
     */
    IExpectedCondition presenceOfNestedElementsLocatedBy(By locator, By sub_locator);

    /**
     * An expectation for checking all elements from given list to be invisible
     *
     * @param elements used to check their invisibility
     * @return Boolean true when all elements are not visible anymore
     */
    IExpectedCondition invisibilityOfAllElements(List<WebElement> elements);

    /**
     * An expectation with the logical or condition of the given list of conditions.
     * <p>
     * Each condition is checked until at leas one of them returns true or not null
     *
     * @param conditions ExpectedCondition is a list of alternative conditions
     * @return true once one of conditions is satisfied
     */
    IExpectedCondition or(ExpectedCondition<?>... conditions);

    /**
     * An expectation with the logical and condition of the given list of conditions.
     * <p>
     * Each condition is checked until all of them return true or not null
     *
     * @param conditions ExpectedCondition is a list of alternative conditions
     * @return true once all conditions are satisfied
     */
    IExpectedCondition and(ExpectedCondition<?>... conditions);

    /**
     * An expectation to check if js executable
     * <p>
     * Usefull when  you know, that there should be js val or something at the stage
     *
     * @param javaScript used as executable script
     * @return true once javaScript executed without errors
     */
    IExpectedCondition javaScriptThrowsNoExceptions(String javaScript);

    /**
     * An expectation for String value from javascript
     *
     * @param javaScript as executable js line
     * @return true once js return string
     */
    IExpectedCondition jsReturnsValue(String javaScript);
}
