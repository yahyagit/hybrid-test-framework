/*
 * Copyright 2016 Atanas Stoychev Kanchev
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.atanas.kanchev.testframework.selenium.element.interactions.probe;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.regex.Pattern;


/**
 * Implementation of {@link ExpectedConditions}
 * Models a condition that might reasonably be expected to eventually evaluate to something that is
 * neither null nor false. Examples would include determining if a web page has loaded or that an
 * element is visible.
 * <p>
 * Note that it is expected that ExpectedConditions are idempotent. They will be called in a loop by
 * the {@link WebDriverWait} and any modification of the state of the application under test may
 * have unexpected side-effects.
 */
interface IProbeExpectedConditions {

    /**
     * An expectation for checking the title of a page.
     *
     * @param title the expected title, which must be an exact match
     * @return true when the title matches, false otherwise
     */
    boolean titleIs(final String title);

    /**
     * An expectation for checking that the title contains a case-sensitive substring
     *
     * @param title the fragment of title expected
     * @return true when the title matches, false otherwise
     */
    boolean titleContains(final String title);

    /**
     * An expectation for the URL of the current page to be a specific url.
     *
     * @param url the url that the page should be on
     * @return <code>true</code> when the URL is what it should be
     */
    boolean urlToBe(final String url);

    /**
     * An expectation for the URL of the current page to contain specific text.
     *
     * @param fraction the fraction of the url that the page should be on
     * @return <code>true</code> when the URL contains the text
     */
    boolean urlContains(final String fraction);

    /**
     * Expectation for the URL to match a specific regular expression
     *
     * @param regex the regular expression that the URL should match
     * @return <code>true</code> if the URL matches the specified regular expression
     */
    boolean urlMatches(final String regex);

    /**
     * An expectation for checking that an element is present on the DOM of a page. This does not
     * necessarily mean that the element is visible.
     *
     * @param locator used to find the element
     * @return the WebElement once it is located
     */
    IProbeExpectedConditions presenceOfElementLocated(final By locator);

    /**
     * An expectation for checking that an element is present on the DOM of a page and visible.
     * Visibility means that the element is not only displayed but also has a height and width that is
     * greater than 0.
     *
     * @param locator used to find the element
     * @return the WebElement once it is located and visible
     */
    IProbeExpectedConditions visibilityOfElementLocated(final By locator);

    /**
     * An expectation for checking that all elements present on the web page that match the locator
     * are visible. Visibility means that the elements are not only displayed but also have a height
     * and width that is greater than 0.
     *
     * @param locator used to find the element
     * @return the list of WebElements once they are located
     */
    IProbeExpectedConditions visibilityOfAllElementsLocatedBy(final By locator);

    /**
     * An expectation for checking that all elements present on the web page that match the locator
     * are visible. Visibility means that the elements are not only displayed but also have a height
     * and width that is greater than 0.
     *
     * @param elements list of WebElements
     * @return the list of WebElements once they are located
     */
    IProbeExpectedConditions visibilityOfAllElements(final List<WebElement> elements);

    /**
     * An expectation for checking that an element, known to be present on the DOM of a page, is
     * visible. Visibility means that the element is not only displayed but also has a height and
     * width that is greater than 0.
     *
     * @param element the WebElement
     * @return the (same) WebElement once it is visible
     */
    IProbeExpectedConditions visibilityOf(final WebElement element);

    /**
     * An expectation for checking that there is at least one element present on a web page.
     *
     * @param locator used to find the element
     * @return the list of WebElements once they are located
     */
    IProbeExpectedConditions presenceOfAllElementsLocatedBy(final By locator);

    /**
     * An expectation for checking if the given text is present in the specified element.
     *
     * @param element the WebElement
     * @param text    to be present in the element
     * @return true once the element contains the given text
     */
    boolean textToBePresentInElement(final WebElement element, final String text);

    /**
     * An expectation for checking if the given text is present in the element that matches the given
     * locator.
     *
     * @param locator used to find the element
     * @param text    to be present in the element found by the locator
     * @return true once the first element located by locator contains the given text
     */
    boolean textToBePresentInElementLocated(final By locator, final String text);

    /**
     * An expectation for checking if the given text is present in the specified elements value
     * attribute.
     *
     * @param element the WebElement
     * @param text    to be present in the element's value attribute
     * @return true once the element's value attribute contains the given text
     */
    IProbeExpectedConditions textToBePresentInElementValue(final WebElement element,
        final String text);

    /**
     * An expectation for checking if the given text is present in the specified elements value
     * attribute.
     *
     * @param locator used to find the element
     * @param text    to be present in the value attribute of the element found by the locator
     * @return true once the value attribute of the first element located by locator contains the
     * given text
     */
    boolean textToBePresentInElementValue(final By locator, final String text);

    /**
     * An expectation for checking whether the given frame is available to switch to. <p> If the frame
     * is available it switches the given driver to the specified frame.
     *
     * @param frameLocator used to find the frame (id or name)
     * @return WebDriver omniaDriver after frame has been switched
     */
    IProbeExpectedConditions frameToBeAvailableAndSwitchToIt(final String frameLocator);

    /**
     * An expectation for checking whether the given frame is available to switch to. <p> If the frame
     * is available it switches the given driver to the specified frame.
     *
     * @param locator used to find the frame
     * @return WebDriver omniaDriver after frame has been switched
     */
    IProbeExpectedConditions frameToBeAvailableAndSwitchToIt(final By locator);

    /**
     * An expectation for checking whether the given frame is available to switch to. <p> If the frame
     * is available it switches the given driver to the specified frameIndex.
     *
     * @param frameLocator used to find the frame (index)
     * @return WebDriver omniaDriver after frame has been switched
     */
    IProbeExpectedConditions frameToBeAvailableAndSwitchToIt(final int frameLocator);

    /**
     * An expectation for checking whether the given frame is available to switch to. <p> If the frame
     * is available it switches the given driver to the specified webelement.
     *
     * @param frameLocator used to find the frame (webelement)
     * @return WebDriver omniaDriver after frame has been switched
     */
    IProbeExpectedConditions frameToBeAvailableAndSwitchToIt(final WebElement frameLocator);

    /**
     * An expectation for checking that an element is either invisible or not present on the DOM.
     *
     * @param locator used to find the element
     * @return true if the element is not displayed or the element doesn't exist or stale element
     */
    boolean invisibilityOfElementLocated(final By locator);

    /**
     * An expectation for checking that an element with text is either invisible or not present on the
     * DOM.
     *
     * @param locator used to find the element
     * @param text    of the element
     * @return true if no such element, stale element or displayed text not equal that provided
     */
    boolean invisibilityOfElementWithText(final By locator, final String text);

    /**
     * An expectation for checking an element is visible and enabled such that you can click it.
     *
     * @param locator used to find the element
     * @return the WebElement once it is located and clickable (visible and enabled)
     */
    IProbeExpectedConditions elementToBeClickable(final By locator);

    /**
     * An expectation for checking an element is visible and enabled such that you can click it.
     *
     * @param element the WebElement
     * @return the (same) WebElement once it is clickable (visible and enabled)
     */
    IProbeExpectedConditions elementToBeClickable(final WebElement element);

    /**
     * IWait until an element is no longer attached to the DOM.
     *
     * @param element The element to wait for.
     * @return false is the element is still attached to the DOM, true otherwise.
     */
    boolean stalenessOf(final WebElement element);

    /**
     * Wrapper for a condition, which allows for elements to update by redrawing.
     * <p>
     * This works around the problem of conditions which have two parts: find an element and then
     * check for some condition on it. For these conditions it is possible that an element is located
     * and then subsequently it is redrawn on the client. When this happens a {@link
     * StaleElementReferenceException} is thrown when the second part of the condition is checked.
     *
     * @param condition ExpectedCondition to wrapElement
     * @param <T>       return type of the condition provided
     * @return the result of the provided condition
     */
    <T> IProbeExpectedConditions refreshed(final ExpectedCondition<T> condition);

    /**
     * An expectation for checking if the given element is selected.
     *
     * @param element WebElement to be selected
     * @return true once the element is selected
     */
    boolean elementToBeSelected(final WebElement element);

    /**
     * An expectation for checking if the given element is selected.
     *
     * @param element  WebElement to be selected
     * @param selected boolean state of the selection state of the element
     * @return true once the element's selection stated is that of selected
     */
    boolean elementSelectionStateToBe(final WebElement element, final boolean selected);

    boolean elementToBeSelected(final By locator);

    boolean elementSelectionStateToBe(final By locator, final boolean selected);

    IProbeExpectedConditions alertIsPresent();

    boolean numberOfWindowsToBe(final int expectedNumberOfWindows);

    /**
     * An expectation with the logical opposite condition of the given condition.
     * <p>
     * Note that if the Condition your are inverting throws an exception that is caught by the Ignored
     * Exceptions, the inversion will not take place and lead to confusing results.
     *
     * @param condition ExpectedCondition to be inverted
     * @return true once the condition is satisfied
     */
    boolean not(final ExpectedCondition<?> condition);

    /**
     * An expectation for checking WebElement with given locator has attribute with a specific value
     *
     * @param locator   used to find the element
     * @param attribute used to define css or html attribute
     * @param value     used as expected attribute value
     * @return Boolean true when element has css or html attribute with the value
     */
    boolean attributeToBe(final By locator, final String attribute, final String value);

    /**
     * An expectation for checking WebElement with given locator has specific text
     *
     * @param locator used to find the element
     * @param value   used as expected text
     * @return Boolean true when element has text value equal to @value
     */
    boolean textToBe(final By locator, final String value);

    /**
     * An expectation for checking WebElement with given locator has text with a value as a part of
     * it
     *
     * @param locator used to find the element
     * @param pattern used as expected text matcher pattern
     * @return Boolean true when element has text value containing @value
     */
    boolean textMatches(final By locator, final Pattern pattern);

    /**
     * An expectation for checking number of WebElements with given locator
     *
     * @param locator used to find the element
     * @param number  user to define exact number of elements
     * @return Boolean true when size of elements list is equal to defined
     */
    IProbeExpectedConditions numberOfElementsToBeMoreThan(final By locator, final Integer number);

    /**
     * An expectation for checking number of WebElements with given locator being less than defined
     * number
     *
     * @param locator used to find the element
     * @param number  user to define maximum number of elements
     * @return Boolean true when size of elements list is less than defined
     */
    IProbeExpectedConditions numberOfElementsToBeLessThan(final By locator, final Integer number);

    /**
     * An expectation for checking number of WebElements with given locator
     *
     * @param locator used to find the element
     * @param number  user to define number of elements
     * @return Boolean true when size of elements list is equal to defined
     */
    IProbeExpectedConditions numberOfElementsToBe(final By locator, final Integer number);

    /**
     * An expectation for checking given WebElement has attribute with a specific value
     *
     * @param element   used to check it's parameters
     * @param attribute used to define css or html attribute
     * @param value     used as expected attribute value
     * @return Boolean true when element has css or html attribute with the value
     */
    boolean attributeToBe(final WebElement element, final String attribute, final String value);

    /**
     * An expectation for checking WebElement with given locator has attribute which contains specific
     * value
     *
     * @param element   used to check it's parameters
     * @param attribute used to define css or html attribute
     * @param value     used as expected attribute value
     * @return Boolean true when element has css or html attribute which contains the value
     */
    boolean attributeContains(final WebElement element, final String attribute, final String value);

    /**
     * An expectation for checking WebElement with given locator has attribute which contains specific
     * value
     *
     * @param locator   used to define WebElement to check it's parameters
     * @param attribute used to define css or html attribute
     * @param value     used as expected attribute value
     * @return Boolean true when element has css or html attribute which contains the value
     */
    boolean attributeContains(final By locator, final String attribute, final String value);

    /**
     * An expectation for checking WebElement any non empty value for given attribute
     *
     * @param element   used to check it's parameters
     * @param attribute used to define css or html attribute
     * @return Boolean true when element has css or html attribute with non empty value
     */
    boolean attributeToBeNotEmpty(final WebElement element, final String attribute);

    /**
     * An expectation for checking child WebElement as a part of parent element to be visible
     *
     * @param locator     used to check parent element. For example table with locator
     *                    By.xpath("//table")
     * @param sub_locator used to find child element. For example td By.xpath("./tr/td")
     * @return visible nested element
     */
    IProbeExpectedConditions visibilityOfNestedElementsLocatedBy(final By locator,
        final By sub_locator);

    /**
     * An expectation for checking child WebElement as a part of parent element to be visible
     *
     * @param element     used as parent element. For example table with locator By.xpath("//table")
     * @param sub_locator used to find child element. For example td By.xpath("./tr/td")
     * @return visible subelement
     */
    IProbeExpectedConditions visibilityOfNestedElementsLocatedBy(final WebElement element,
        final By sub_locator);

    /**
     * An expectation for checking child WebElement as a part of parent element to present
     *
     * @param locator     used to check parent element. For example table with locator
     *                    By.xpath("//table")
     * @param sub_locator used to find child element. For example td By.xpath("./tr/td")
     * @return subelement
     */
    IProbeExpectedConditions presenceOfNestedElementLocatedBy(final By locator,
        final By sub_locator);

    /**
     * An expectation for checking child WebElement as a part of parent element to be present
     *
     * @param element     used as parent element
     * @param sub_locator used to find child element. For example td By.xpath("./tr/td")
     * @return subelement
     */
    IProbeExpectedConditions presenceOfNestedElementLocatedBy(final WebElement element,
        final By sub_locator);

    /**
     * An expectation for checking child WebElement as a part of parent element to present
     *
     * @param locator     used to check parent element. For example table with locator
     *                    By.xpath("//table")
     * @param sub_locator used to find child element. For example td By.xpath("./tr/td")
     * @return subelement
     */
    IProbeExpectedConditions presenceOfNestedElementsLocatedBy(final By locator,
        final By sub_locator);

    /**
     * An expectation for checking all elements from given list to be invisible
     *
     * @param elements used to check their invisibility
     * @return Boolean true when all elements are not visible anymore
     */
    boolean invisibilityOfAllElements(final List<WebElement> elements);

    /**
     * An expectation with the logical or condition of the given list of conditions.
     * <p>
     * Each condition is checked until at leas one of them returns true or not null
     *
     * @param conditions ExpectedCondition is a list of alternative conditions
     * @return true once one of conditions is satisfied
     */
    boolean or(final ExpectedCondition<?>... conditions);

    /**
     * An expectation with the logical and condition of the given list of conditions.
     * <p>
     * Each condition is checked until all of them return true or not null
     *
     * @param conditions ExpectedCondition is a list of alternative conditions
     * @return true once all conditions are satisfied
     */
    boolean and(final ExpectedCondition<?>... conditions);

    /**
     * An expectation to check if js executable
     * <p>
     * Usefull when  you know, that there should be js val or something at the stage
     *
     * @param javaScript used as executable script
     * @return true once javaScript executed without errors
     */
    boolean javaScriptThrowsNoExceptions(final String javaScript);

    /**
     * An expectation for String value from javascript
     *
     * @param javaScript as executable js line
     * @return true once js return string
     */
    Object jsReturnsValue(final String javaScript);

}


