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
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;

public interface SelenideElement
    extends WebElement, FindsByLinkText, FindsById, FindsByName, FindsByTagName, FindsByClassName,
    FindsByCssSelector, FindsByXPath, WrapsDriver, WrapsElement, Locatable {

  void followLink();

  SelenideElement setValue(String text);

  SelenideElement val(String text);

  SelenideElement append(String text);

  SelenideElement pressEnter();

  SelenideElement pressTab();

  SelenideElement pressEscape();

  @Override String getText();

  String text();

  String innerText();

  String innerHtml();

  String attr(String attributeName);

  String name();

  String val();

  String getValue();

  SelenideElement selectRadio(String value);

  String data(String dataAttributeName);

  boolean exists();

  @Override
  boolean isDisplayed();


  @Override String toString();

  SelenideElement parent();

  SelenideElement closest(String tagOrClass);

  SelenideElement find(String cssSelector);

  SelenideElement find(String cssSelector, int index);

  SelenideElement find(By selector);

  SelenideElement find(By selector, int index);

  SelenideElement $(String cssSelector);

  SelenideElement $(String cssSelector, int index);

  SelenideElement $(By selector);

  SelenideElement $(By selector, int index);

  File uploadFromClasspath(String... fileName);

  File uploadFile(File... file);

  void selectOption(int index);

  void selectOption(String text);

  void selectOptionByValue(String value);

  SelenideElement getSelectedOption() throws NoSuchElementException;

  String getSelectedValue();

  String getSelectedText();

  SelenideElement scrollTo();

  File download() throws FileNotFoundException;

  WebElement toWebElement();

  @Override WebElement getWrappedElement();

  @Override void click();

  SelenideElement contextClick();

  SelenideElement doubleClick();

  SelenideElement hover();

  SelenideElement dragAndDropTo(String targetCssSelector);

  SelenideElement dragAndDropTo(WebElement target);

  boolean isImage();

  File screenshot();

  BufferedImage screenshotAsImage();
}
