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
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Thanks to http://selenium.polteq.com/en/injecting-the-sizzle-css-selector-library/
 */
public class WebElementSelector {
  public static WebElementSelector instance = new WebElementSelector();
  
  protected String sizzleSource;

  public WebElement findElement(SearchContext context, By selector) {
//    if (selectorMode == CSS || !(selector instanceof ByCssSelector)) {
//      return $context.findElement(selector);
//    }

    List<WebElement> webElements = new ArrayList<>();
    return webElements.isEmpty() ? null : webElements.get(0);
  }

//  public List<WebElement> findElements(SearchContext $context, By selector) {
//    if (selectorMode == CSS || !(selector instanceof ByCssSelector)) {
//      return $context.findElements(selector);
//    }
//
//    return evaluateSizzleSelector($context, (ByCssSelector) selector);
//  }
//
//  protected List<WebElement> evaluateSizzleSelector(SearchContext $context, ByCssSelector sizzleCssSelector) {
////    injectSizzleIfNeeded();
//
//    String sizzleSelector = sizzleCssSelector.toString()
//        .replace("By.selector: ", "")
//        .replace("By.cssSelector: ", "");
//
//    if ($context instanceof WebElement)
//      return executeJavaScript("return Sizzle(arguments[0], arguments[1])", sizzleSelector, $context);
//    else
//      return executeJavaScript("return Sizzle(arguments[0])", sizzleSelector);
//  }

//  protected void injectSizzleIfNeeded() {
//    if (!sizzleLoaded()) {
//      injectSizzle();
//    }
//  }
//
//  protected Boolean sizzleLoaded() {
//    try {
//      return executeJavaScript("return typeof Sizzle != 'undefined'");
//    } catch (WebDriverException e) {
//      return false;
//    }
//  }
//
//  protected synchronized void injectSizzle() {
//    if (sizzleSource == null) {
//      try {
//        sizzleSource = IOUtils.toString(currentThread().getContextClassLoader().getResource("sizzle.js"));
//      } catch (IOException e) {
//        throw new RuntimeException("Cannot load sizzle.js from classpath", e);
//      }
//    }
//    executeJavaScript(sizzleSource);
//  }
}
