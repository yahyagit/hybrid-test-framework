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

import com.atanas.kanchev.testframework.selenium.proxy.elements.base.ElementImpl;
import org.openqa.selenium.WebElement;


/**
 * Wrapper class like Select that wraps basic checkbox functionality.
 */
public class CheckBoxImpl extends ElementImpl implements CheckBox {

    /**
     * Wraps a WebElement with checkbox functionality.
     *
     * @param element to wrap up
     */
    public CheckBoxImpl(WebElement element) {
        super(element);
    }

    public void toggle() {
        getWrappedElement().click();
    }

    public void check() {
        if (!isChecked()) {
            toggle();
        }
    }

    public void uncheck() {
        if (isChecked()) {
            toggle();
        }
    }

    public boolean isChecked() {
        return getWrappedElement().isSelected();
    }
}
