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

package com.atanas.kanchev.testframework.selenium.interactions.element.interactions;

import com.atanas.kanchev.testframework.selenium.handlers.CommonPageDefinitions;
import com.atanas.kanchev.testframework.selenium.interactions.element.OmniaElement;
import org.openqa.selenium.WebDriverException;

import static com.atanas.kanchev.testframework.selenium.interactions.element.OmniaElement.omniaElement;

/**
 * @author Atanas Kanchev
 */
public class SendKeys extends AbstractElementInteraction {

    public OmniaElement sendKeys(CharSequence[] charSequences) {
        new ElementExecutor<OmniaElement>() {

            @Override public OmniaElement execute() {
                String tag = element.getTagName();
                if (tag.equals(CommonPageDefinitions.HTML.INPUT.getDefinition()) || tag
                    .equals(CommonPageDefinitions.HTML.TEXTAREA.getDefinition()) || tag
                    .equals(CommonPageDefinitions.HTML.UIA_SECURETEXTFIELD.getDefinition()) || tag
                    .equals(CommonPageDefinitions.HTML.UIA_TEXTFIELD.getDefinition()) || tag
                    .equals("android.widget.EditText")) {
                    element.sendKeys(charSequences);
                } else {
                    logger.error("Cannot type in this element");
                    throwEx(new WebDriverException("Cannot type in this element"));
                }

                return omniaElement;
            }
        };
        return omniaElement;
    }
}
