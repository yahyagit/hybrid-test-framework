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

package com.atanas.kanchev.testframework.selenium.inv.atanas;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.internal.WrapsElement;

import java.util.List;

/**
 * Created by atanas on 23/09/16.
 */
public interface IElement extends WebElement, WrapsElement, Locatable {

    public default void click(String arg){}

    @Override default void click() {

        System.out.println("click in interface");

    }

    @Override default void submit() {

    }

    @Override default void sendKeys(CharSequence... keysToSend) {
        System.out.println("sendKeys in interface");
    }

    @Override default void clear() {

    }

    @Override default String getTagName() {
        return null;
    }

    @Override default String getAttribute(String name) {
        return null;
    }

    @Override default boolean isSelected() {
        return false;
    }

    @Override default boolean isEnabled() {
        return false;
    }

    @Override default String getText() {
        return null;
    }

    @Override default List<WebElement> findElements(By by) {
        return null;
    }

    @Override default WebElement findElement(By by) {
        return null;
    }

    @Override default boolean isDisplayed() {
        return false;
    }

    @Override default Point getLocation() {
        return null;
    }

    @Override default Dimension getSize() {
        return null;
    }

    @Override default Rectangle getRect() {
        return null;
    }

    @Override default String getCssValue(String propertyName) {
        return null;
    }

    @Override default <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return null;
    }

    @Override default Coordinates getCoordinates() {
        return null;
    }

    @Override default WebElement getWrappedElement() {
        return null;
    }
}

