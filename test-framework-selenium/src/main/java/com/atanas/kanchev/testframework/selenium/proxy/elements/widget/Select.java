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

package com.atanas.kanchev.testframework.selenium.proxy.elements.widget;

import com.atanas.kanchev.testframework.selenium.proxy.elements.base.Element;
import com.atanas.kanchev.testframework.selenium.proxy.elements.base.ImplementedBy;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Interface for a select element.
 */
@ImplementedBy(SelectImpl.class)
public interface Select extends Element {

    /**
     * Wraps Selenium's method.
     *
     * @return boolean if this is a multiselect.
     * @see org.openqa.selenium.support.ui.Select#isMultiple()
     */
    boolean isMultiple();

    /**
     * Wraps Selenium's method.
     *
     * @param index index to select
     * @see org.openqa.selenium.support.ui.Select#deselectByIndex(int)
     */
    void deselectByIndex(int index);

    /**
     * Wraps Selenium's method.
     *
     * @param value the value to select.
     * @see org.openqa.selenium.support.ui.Select#selectByValue(String)
     */
    void selectByValue(String value);

    /**
     * Wraps Selenium's method.
     *
     * @return WebElement of the first selected option.
     * @see org.openqa.selenium.support.ui.Select#getFirstSelectedOption()
     */
    WebElement getFirstSelectedOption();

    /**
     * Wraps Selenium's method.
     *
     * @param text visible text to select
     * @see org.openqa.selenium.support.ui.Select#selectByVisibleText(String)
     */
    void selectByVisibleText(String text);

    /**
     * Wraps Selenium's method.
     *
     * @param value value to deselect
     * @see org.openqa.selenium.support.ui.Select#deselectByValue(String)
     */
    void deselectByValue(String value);

    /**
     * Wraps Selenium's method.
     *
     * @see org.openqa.selenium.support.ui.Select#deselectAll()
     */
    void deselectAll();

    /**
     * Wraps Selenium's method.
     *
     * @return List of WebElements selected in the select
     * @see org.openqa.selenium.support.ui.Select#getAllSelectedOptions()
     */
    List<WebElement> getAllSelectedOptions();

    /**
     * Wraps Selenium's method.
     *
     * @return list of all options in the select.
     * @see org.openqa.selenium.support.ui.Select#getOptions()
     */
    List<WebElement> getOptions();

    /**
     * Wraps Selenium's method.
     *
     * @param text text to deselect by visible text
     * @see org.openqa.selenium.support.ui.Select#deselectByVisibleText(String)
     */
    void deselectByVisibleText(String text);

    /**
     * Wraps Selenium's method.
     *
     * @param index index to select
     * @see org.openqa.selenium.support.ui.Select#selectByIndex(int)
     */
    void selectByIndex(int index);

}
