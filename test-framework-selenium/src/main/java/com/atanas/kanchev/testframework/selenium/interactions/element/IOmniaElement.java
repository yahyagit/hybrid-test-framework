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

package com.atanas.kanchev.testframework.selenium.interactions.element;

import com.atanas.kanchev.testframework.selenium.handlers.Finder;
import com.atanas.kanchev.testframework.selenium.interactions.wait.Waiting;
import org.openqa.selenium.*;

import java.util.List;

/**
 * @author Atanas Kanchev
 */
public interface IOmniaElement extends WebElement {

    @Override void click();

    @Override void submit();

    @Override void sendKeys(CharSequence... keysToSend);

    @Override void clear();

    @Override String getTagName();

    @Override String getAttribute(String name);

    @Override boolean isSelected();

    @Override boolean isEnabled();

    @Override String getText();

    @Override List<WebElement> findElements(By by);

    @Override WebElement findElement(By by);

    @Override boolean isDisplayed();

    @Override Point getLocation();

    @Override Dimension getSize();

    @Override Rectangle getRect();

    @Override String getCssValue(String propertyName);

    void clickAndHold(int duration);

    void doubleClick();

    void contextClick();

    IOmniaElement hover();

    IOmniaElement selectAll();

    IOmniaElement copy();

    IOmniaElement paste();

    IOmniaElement handleAlert(boolean accept);

    Finder find();

    Finder find(WebElement element);

    Finder find(Class<?> clazz);

    Waiting waitFor();

}
