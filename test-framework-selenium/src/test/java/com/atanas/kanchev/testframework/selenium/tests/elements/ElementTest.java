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

package com.atanas.kanchev.testframework.selenium.tests.elements;

import com.atanas.kanchev.testframework.selenium.tests.elements.helpers.PageLoader;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 * @author Atanas Kanchev
 */
@RunWith(JUnit4.class)
public class ElementTest {

    static WebDriver driver;
//    static FormTestObject testObject;

    @BeforeClass
    public static void beforeClass() {
        driver = new HtmlUnitDriver();
//        testObject = FormTestObject.initialize(driver);
    }

    @AfterClass
    public static void afterClass() {
        driver.close();
    }

    public void get() {
        PageLoader.get(driver, "forms.html");
    }

    @Before
    public void beforeTest() {
        get();
    }

    @Test public void name() throws Exception {
//        ElementHandler elementHandler = new ElementHandler(ClickImpl.class, By.id("texta"));


    }
}
