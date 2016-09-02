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

import com.atanas.kanchev.testframework.selenium.proxy.elements.base.Element;
import com.atanas.kanchev.testframework.selenium.proxy.elements.base.ImplementedBy;
import com.atanas.kanchev.testframework.selenium.proxy.elements.widget.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


/**
 * Fragment of options just for demonstration purpose.
 * @author niels
 *
 */
@ImplementedBy(SelectFragmentImpl.class)
public interface SelectFragment extends Element {
    
    final String ID_LOCATOR = "options";
    
    Select getOption1();
    
    WebElement getSubElement(By by);

}
