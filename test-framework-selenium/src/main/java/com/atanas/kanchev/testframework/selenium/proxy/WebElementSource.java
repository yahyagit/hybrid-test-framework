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

package com.atanas.kanchev.testframework.selenium.proxy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static java.util.Collections.singletonList;

public abstract class WebElementSource {
  public abstract WebElement getWebElement();

  public abstract String getSearchCriteria();

  public SelenideElement find(SelenideElement proxy, Object arg, int index) {
    return ElementFinder.wrap(proxy, getSelector(arg), index);
  }

  public List<WebElement> findAll() throws IndexOutOfBoundsException {
    return singletonList(getWebElement());
  }

//  public ElementNotFound createElementNotFoundError(Condition condition, Throwable lastError) {
//    return new ElementNotFound(getSearchCriteria(), condition, lastError);
//  }

  public static By getSelector(Object arg) {
    return arg instanceof By ? (By) arg : By.cssSelector((String) arg);
  }

//  public WebElement checkCondition(String prefix, String message, Condition condition, boolean invert) {
//    Condition check = invert ? not(condition) : condition;
//
//    RuntimeException lastError = null;
//    WebElement element = null;
//    try {
//      element = getWebElement();
//      if (element != null && check.apply(element)) {
//        return element;
//      }
//    }
//    catch (RuntimeException e) {
//      lastError = e;
//    }
//
//    if (Cleanup.of.isInvalidSelectorError(lastError)) {
//      throw Cleanup.of.wrap(lastError);
//    }
//
//    if (element == null) {
//      if (!check.applyNull()) {
//        throw createElementNotFoundError(check, lastError);
//      }
//    }
//    else if (invert) {
//      throw new ElementShouldNot(getSearchCriteria(), prefix, message, condition, element, lastError);
//    }
//    else {
//      throw new ElementShould(getSearchCriteria(), prefix, message, condition, element, lastError);
//    }
//    return null;
//  }

//  public WebElement findAndAssertElementIsVisible() {
//    return checkCondition("be ", null, visible, false);
//  }
}
