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

package com.atanas.kanchev.testframework.selenium.inv.atanas.refactoring.commands;

import com.atanas.kanchev.testframework.commons.context.ContextKey;
import com.atanas.kanchev.testframework.selenium.context.SeleniumContext;
import com.atanas.kanchev.testframework.selenium.handlers.CommonPageDefinitions;
import com.atanas.kanchev.testframework.selenium.inv.atanas.refactoring.AbstractCommand;
import org.openqa.selenium.WebDriverException;

import java.util.Arrays;

/**
 * Created by atanas on 27/09/16.
 */
public class SendKeys extends AbstractCommand {

    public SendKeys(ContextKey<SeleniumContext> currentContextKey) {
        super(currentContextKey);
    }

    void sendKeys(CharSequence... keysToSend) {
        String tag = element.getTagName();
        if (tag.equals(CommonPageDefinitions.HTML.INPUT.getDefinition()) || tag
            .equals(CommonPageDefinitions.HTML.TEXTAREA.getDefinition()) || tag
            .equals(CommonPageDefinitions.HTML.UIA_SECURETEXTFIELD.getDefinition()) || tag
            .equals(CommonPageDefinitions.HTML.UIA_TEXTFIELD.getDefinition()) || tag
            .equals("android.widget.EditText")) {
            logger.debug("Typing " + Arrays.toString(keysToSend));
            element.sendKeys(keysToSend);
        } else {
            logger.error("Cannot type in this element");
            throwEx(new WebDriverException("Cannot type in this element"));
        }
    }
}
