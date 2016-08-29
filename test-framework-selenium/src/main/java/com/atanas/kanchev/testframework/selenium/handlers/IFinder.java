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

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface IFinder {

    IFinder goToRootElement();

    IFinder elementBy(By locator);

    IFinder elementsBy(By locator);

    IFinder byWebElement(WebElement element);

    IFinder containingText(String text);

    IFinder havingText(String text);

    IFinder byScrollingToElement(By locator);

    IFinder byScrollingByAttribute(String attribute, String value);

    IFinder byScrollingByTag(String tag, String value);

    IFinder byScrollingByText(String text, boolean isExactMatch);

    IFinder byAttributeValue(String attribute, String value);

//    IFinder byLabelForId(String id);
//
//    IFinder byHref(String href);

    IFinder goToChild();

    IFinder goToParent();

}
