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

package com.atanas.kanchev.testframework.selenium.interactions.wait.interactions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Atanas Kanchev
 */
public class Element extends AbstractWaitInteraction {

    public WaitExecutor presenceOfElementLocated(By input) {
        return new WaitExecutor<WebElement>() {
            @Override public WebElement execute() {
                return exec(ExpectedConditions.presenceOfElementLocated(input));
            }
        };
    }

    public WaitExecutor presenceOfAllElementsLocatedBy(By input) {
        return new WaitExecutor<List<WebElement>>() {
            @Override public List<WebElement> execute() {
                return exec(ExpectedConditions.presenceOfAllElementsLocatedBy(input));
            }
        };
    }

    public WaitExecutor presenceOfNestedElementLocatedBy(By locator, By sub_locator) {
        return new WaitExecutor<WebElement>() {
            @Override public WebElement execute() {
                return exec(
                    ExpectedConditions.presenceOfNestedElementLocatedBy(locator, sub_locator));
            }
        };
    }

    public WaitExecutor presenceOfNestedElementLocatedBy(WebElement element, By sub_locator) {
        return new WaitExecutor<WebElement>() {
            @Override public WebElement execute() {
                return exec(
                    ExpectedConditions.presenceOfNestedElementLocatedBy(element, sub_locator));
            }
        };
    }

    public WaitExecutor presenceOfNestedElementsLocatedBy(By locator, By sub_locator) {
        return new WaitExecutor<List<WebElement>>() {
            @Override public List<WebElement> execute() {
                return exec(
                    ExpectedConditions.presenceOfNestedElementsLocatedBy(locator, sub_locator));
            }
        };
    }

    public WaitExecutor visibilityOfElementLocated(By locator) {
        return new WaitExecutor<WebElement>() {
            @Override public WebElement execute() {
                return exec(ExpectedConditions.visibilityOfElementLocated(locator));
            }
        };
    }

    public WaitExecutor visibilityOfAllElementsLocatedBy(By locator) {
        return new WaitExecutor<List<WebElement>>() {
            @Override public List<WebElement> execute() {
                return exec(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
            }
        };
    }

    public WaitExecutor visibilityOfAllElements(List<WebElement> elements) {
        return new WaitExecutor<List<WebElement>>() {
            @Override public List<WebElement> execute() {
                return exec(ExpectedConditions.visibilityOfAllElements(elements));
            }
        };
    }

    public WaitExecutor visibilityOf(WebElement element) {
        return new WaitExecutor<WebElement>() {
            @Override public WebElement execute() {
                return exec(ExpectedConditions.visibilityOf(element));
            }
        };
    }

    public WaitExecutor textToBePresentInElement(WebElement element, String text) {
        return new WaitExecutor<Boolean>() {
            @Override public Boolean execute() {
                return exec(ExpectedConditions.textToBePresentInElement(element, text));
            }
        };
    }

    public WaitExecutor textToBePresentInElementLocated(By locator, String text) {
        return new WaitExecutor<Boolean>() {
            @Override public Boolean execute() {
                return exec(ExpectedConditions.textToBePresentInElementLocated(locator, text));
            }
        };
    }

    public WaitExecutor textToBePresentInElementValue(WebElement element, String text) {
        return new WaitExecutor<Boolean>() {
            @Override public Boolean execute() {
                return exec(ExpectedConditions.textToBePresentInElementValue(element, text));
            }
        };
    }

    public WaitExecutor textToBePresentInElementValue(By locator, String text) {
        return new WaitExecutor<Boolean>() {
            @Override public Boolean execute() {
                return exec(ExpectedConditions.textToBePresentInElementValue(locator, text));
            }
        };
    }

    public WaitExecutor invisibilityOfElementLocated(By locator) {
        return new WaitExecutor<Boolean>() {
            @Override public Boolean execute() {
                return exec(ExpectedConditions.invisibilityOfElementLocated(locator));
            }
        };
    }

    public WaitExecutor invisibilityOfElementWithText(By locator, String text) {
        return new WaitExecutor<Boolean>() {
            @Override public Boolean execute() {
                return exec(ExpectedConditions.invisibilityOfElementWithText(locator, text));
            }
        };
    }

    public WaitExecutor elementToBeClickable(By locator) {
        return new WaitExecutor<WebElement>() {
            @Override public WebElement execute() {
                return exec(ExpectedConditions.elementToBeClickable(locator));
            }
        };
    }

    public WaitExecutor elementToBeClickable(WebElement element) {
        return new WaitExecutor<WebElement>() {
            @Override public WebElement execute() {
                return exec(ExpectedConditions.elementToBeClickable(element));
            }
        };
    }

    public WaitExecutor stalenessOf(WebElement element) {
        return new WaitExecutor<Boolean>() {
            @Override public Boolean execute() {
                return exec(ExpectedConditions.stalenessOf(element));
            }
        };
    }

    public <T> WaitExecutor refreshed(ExpectedCondition<T> condition) {
        return new WaitExecutor<T>() {
            @Override public T execute() {
                return exec(ExpectedConditions.refreshed(condition));
            }
        };
    }

    public WaitExecutor elementToBeSelected(WebElement element) {
        return new WaitExecutor<Boolean>() {
            @Override public Boolean execute() {
                return exec(ExpectedConditions.elementToBeSelected(element));
            }
        };
    }

    public WaitExecutor elementSelectionStateToBe(WebElement element, boolean selected) {
        return new WaitExecutor<Boolean>() {
            @Override public Boolean execute() {
                return exec(ExpectedConditions.elementSelectionStateToBe(element, selected));
            }
        };
    }

    public WaitExecutor elementToBeSelected(By locator) {
        return new WaitExecutor<Boolean>() {
            @Override public Boolean execute() {
                return exec(ExpectedConditions.elementToBeSelected(locator));
            }
        };
    }

    public WaitExecutor elementSelectionStateToBe(By locator, boolean selected) {
        return new WaitExecutor<Boolean>() {
            @Override public Boolean execute() {
                return exec(ExpectedConditions.elementSelectionStateToBe(locator, selected));
            }
        };
    }

    public WaitExecutor attributeToBe(By locator, String attribute, String value) {
        return new WaitExecutor<Boolean>() {
            @Override public Boolean execute() {
                return exec(ExpectedConditions.attributeToBe(locator, attribute, value));
            }
        };
    }

    public WaitExecutor textToBe(By locator, String value) {
        return new WaitExecutor<Boolean>() {
            @Override public Boolean execute() {
                return exec(ExpectedConditions.textToBe(locator, value));
            }
        };
    }

    public WaitExecutor textMatches(By locator, Pattern pattern) {
        return new WaitExecutor<Boolean>() {
            @Override public Boolean execute() {
                return exec(ExpectedConditions.textMatches(locator, pattern));
            }
        };
    }

    public WaitExecutor numberOfElementsToBeMoreThan(By locator, Integer number) {
        return new WaitExecutor<List<WebElement>>() {
            @Override public List<WebElement> execute() {
                return exec(ExpectedConditions.numberOfElementsToBeMoreThan(locator, number));
            }
        };
    }

    public WaitExecutor numberOfElementsToBeLessThan(By locator, Integer number) {
        return new WaitExecutor<List<WebElement>>() {
            @Override public List<WebElement> execute() {
                return exec(ExpectedConditions.numberOfElementsToBeLessThan(locator, number));
            }
        };
    }

    public WaitExecutor numberOfElementsToBe(By locator, Integer number) {
        return new WaitExecutor<List<WebElement>>() {
            @Override public List<WebElement> execute() {
                return exec(ExpectedConditions.numberOfElementsToBe(locator, number));
            }
        };
    }

    public WaitExecutor attributeToBe(WebElement element, String attribute, String value) {
        return new WaitExecutor<Boolean>() {
            @Override public Boolean execute() {
                return exec(ExpectedConditions.attributeToBe(element, attribute, value));
            }
        };
    }

    public WaitExecutor attributeContains(WebElement element, String attribute, String value) {
        return new WaitExecutor<Boolean>() {
            @Override public Boolean execute() {
                return exec(ExpectedConditions.attributeContains(element, attribute, value));
            }
        };
    }

    public WaitExecutor attributeContains(By locator, String attribute, String value) {
        return new WaitExecutor<Boolean>() {
            @Override public Boolean execute() {
                return exec(ExpectedConditions.attributeContains(locator, attribute, value));
            }
        };
    }

    public WaitExecutor attributeToBeNotEmpty(WebElement element, String attribute) {
        return new WaitExecutor<Boolean>() {
            @Override public Boolean execute() {
                return exec(ExpectedConditions.attributeToBeNotEmpty(element, attribute));
            }
        };
    }

    public WaitExecutor visibilityOfNestedElementsLocatedBy(By locator, By sub_locator) {
        return new WaitExecutor<List<WebElement>>() {
            @Override public List<WebElement> execute() {
                return exec(
                    ExpectedConditions.visibilityOfNestedElementsLocatedBy(locator, sub_locator));
            }
        };
    }

    public WaitExecutor visibilityOfNestedElementsLocatedBy(WebElement element, By sub_locator) {
        return new WaitExecutor<List<WebElement>>() {
            @Override public List<WebElement> execute() {
                return exec(
                    ExpectedConditions.visibilityOfNestedElementsLocatedBy(element, sub_locator));
            }
        };
    }

    public WaitExecutor invisibilityOfAllElements(List<WebElement> elements) {
        return new WaitExecutor<Boolean>() {
            @Override public Boolean execute() {
                return exec(ExpectedConditions.invisibilityOfAllElements(elements));
            }
        };
    }
}
