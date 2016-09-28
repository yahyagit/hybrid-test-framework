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
import com.atanas.kanchev.testframework.selenium.exceptions.ElementEx;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.atanas.kanchev.testframework.commons.accessors.ContextsAccessor.$context;

/**
 * Created by atanas on 27/09/16.
 */
public abstract class AbstractCommand {

    protected final ContextKey<SeleniumContext> currentContextKey;
    protected final WebElement element;
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    protected Actions actions;

    public AbstractCommand(ContextKey<SeleniumContext> currentContextKey) {
        this.element = $context().getContext(currentContextKey).getCurrentElement();
        this.currentContextKey = currentContextKey;
    }

    protected void throwEx(Throwable throwable) throws RuntimeException {
        logger.error("Unable to execute " + throwable.toString());
        throw new ElementEx(throwable);
    }

    protected Actions getActions() {
        if (actions == null)
            return this.actions = new Actions($context().getContext(currentContextKey).getDriver());
        else
            return this.actions;
    }

}
