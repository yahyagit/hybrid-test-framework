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

package com.atanas.kanchev.testframework.selenium.tests.elements.helpers;

import com.atanas.kanchev.testframework.selenium.proxy.elements.base.Element;
import com.atanas.kanchev.testframework.selenium.proxy.elements.factory.api.ElementFactory;
import com.atanas.kanchev.testframework.selenium.proxy.elements.widget.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * declare elements of a form.
 */
public class FormTestObject {

    private WebDriver driver;

    public TextInput texta;

    @FindBy(id = "test1")
    public Element element;

    @FindBy(id = "test1")
    public WebElement webElement;
    
    public Select option1;

    @FindBy(id = SelectFragment.ID_LOCATOR)
    public SelectFragment selectFragment;

    @FindBy(id = "checkbox")
    public CheckBox checkbox;
    
    @FindBy(id = "table")
    public Table table;

    @FindBy(tagName = "label")
    public List<Label> labels;

    @FindBy(tagName = "label")
    public List<Element> elementLabels;

    @FindBy(tagName = "label")
    public List<WebElement> webElementLabels;

    @FindBy(css = "label[for='textb']")
    public WebElement labelForTextB;

    public FormTestObject(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Static factory for generating the class.
     *
     * @param driver The WebDriver for the session.
     * @return FormTestObject.
     */
    public static FormTestObject initialize(WebDriver driver) {
        return ElementFactory.initElements(driver, FormTestObject.class);
    }

    public void get() {
        PageLoader.get(driver, "forms.html");
    }

    public void close() {
        driver.close();
    }

}
