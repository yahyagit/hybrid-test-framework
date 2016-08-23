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

package com.atanas.kanchev.testframework.selenium.element.interactions;

import com.atanas.kanchev.testframework.commons.wrappers.IContext;
import com.atanas.kanchev.testframework.selenium.context.SeleniumContext;
import com.atanas.kanchev.testframework.selenium.element.Executor;
import com.atanas.kanchev.testframework.selenium.element.OmniaElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import static com.atanas.kanchev.testframework.selenium.element.OmniaElement.omniaElement;


/**
 * @author Atanas Kanchev
 */
public class ContextClick extends AbstractInteraction
    implements Executor<OmniaElement>, IContext<SeleniumContext<WebDriver>> {

    @Override public OmniaElement execute(Object... args) {

        try {
            Actions actions = new Actions(context().getCurrentContext().getDriver());
            actions.contextClick(element).perform();
        } catch (Exception e) {
            throwEx(e);
        }
        return omniaElement;
    }

}




