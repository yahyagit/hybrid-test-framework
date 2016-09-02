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

/**
 *
 */
package com.atanas.kanchev.testframework.selenium.tests.elements.helpers;

import com.atanas.kanchev.testframework.selenium.proxy.elements.base.ElementImpl;
import com.atanas.kanchev.testframework.selenium.proxy.elements.factory.api.ElementFactory;
import com.atanas.kanchev.testframework.selenium.proxy.elements.widget.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Implementation of {@link SelectFragment}.
 *
 * @author niels
 */
public class SelectFragmentImpl extends ElementImpl implements SelectFragment {


    @FindBy(id = "option1") private Select option1;

    public SelectFragmentImpl(WebElement element) {
        super(element);
        ElementFactory.initElements(element, this);
    }

    @Override public Select getOption1() {
        return option1;
    }

    @Override public WebElement getSubElement(By by) {
        return findElement(by);
    }



}
