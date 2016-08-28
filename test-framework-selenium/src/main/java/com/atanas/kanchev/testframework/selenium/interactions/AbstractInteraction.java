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

package com.atanas.kanchev.testframework.selenium.interactions;

import com.atanas.kanchev.testframework.commons.exceptions.CustomExceptions;
import com.atanas.kanchev.testframework.selenium.context.SeleniumContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import static com.atanas.kanchev.testframework.commons.init.OmniaInit.context;

/**
 * @author Atanas Kanchev
 */
public abstract class AbstractInteraction {

    protected static final Marker executorMarker = MarkerFactory.getMarker("INTERACTIONS_EXECUTOR");
    protected final WebElement element;
    protected final WebDriver driver;
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected AbstractInteraction() {
        this.element = context().<SeleniumContext>getCurrentContext().getCurrentElement();
        if (this.element != null)
            logger.debug(executorMarker, String
                .format("Executing command [%s] on element located by [%s",
                    this.getClass().getSimpleName(), element.toString().split("->")[1].trim()));
        else
            throw new CustomExceptions.Common.NullReferenceException(
                "Unable to interact with null element");
        this.driver = context().<SeleniumContext<WebDriver>>getCurrentContext().getDriver();
        if (this.driver == null)
            throw new CustomExceptions.Common.NullReferenceException(
                "Null driver in the current context");

    }

    protected abstract void validate(Object... args);

    protected abstract void throwEx(Throwable throwable) throws RuntimeException;
}
