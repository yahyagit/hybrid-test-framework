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

package com.atanas.kanchev.testframework.selenium.element.interactions;

import com.atanas.kanchev.testframework.commons.context.ContextKey;
import com.atanas.kanchev.testframework.commons.exceptions.CustomExceptions;
import com.atanas.kanchev.testframework.selenium.context.SeleniumContext;
import com.atanas.kanchev.testframework.selenium.exceptions.ElementEx;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.atanas.kanchev.testframework.commons.accessors.ContextsAccessor.$context;

/**
 * @author Atanas Kanchev
 */
public abstract class AbstractInteraction {

    protected final ContextKey<SeleniumContext> currentContextKey;
    protected final WebElement element;
    protected final WebDriver driver;
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected AbstractInteraction(ContextKey<SeleniumContext> currentContextKey) {

        this.currentContextKey = currentContextKey;
        this.element = $context().getContext(currentContextKey).getCurrentElement();
        if ($context().getContext(currentContextKey).getDriver() == null)
            throw new CustomExceptions.Common.NullReferenceException(
                "Null driver in the current $context");
        else
            this.driver = $context().getContext(currentContextKey).getDriver();

    }

    protected void throwEx(Throwable throwable) throws RuntimeException {
        logger.error("Unable to execute " + throwable.toString());
        throw new ElementEx(throwable);
    }

}
