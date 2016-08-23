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

import com.atanas.kanchev.testframework.commons.exceptions.CustomExceptions;
import com.atanas.kanchev.testframework.commons.wrappers.IContext;
import com.atanas.kanchev.testframework.selenium.context.SeleniumContext;
import com.atanas.kanchev.testframework.selenium.exceptions.ElementEx;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * @author Atanas Kanchev
 */
public abstract class AbstractInteraction implements IContext<SeleniumContext<WebDriver>> {

    protected static final Marker executorMarker = MarkerFactory.getMarker("INTERACTIONS_EXECUTOR");
    protected final WebElement element;
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public AbstractInteraction() {
        this.element = context().getCurrentContext().getCurrentElement();
        if (this.element != null)
            logger.debug(executorMarker, String
                .format("Executing command [%s] on element located by [%s",
                    this.getClass().getSimpleName().toLowerCase(),
                    element.toString().split("->")[1].trim()));
        else
            throw new CustomExceptions.Common.NullReferenceException(
                "Unable to interact with null element");
    }

    protected void validate(Object... args) {
        if (args != null)
            throw new CustomExceptions.Common.NullArgumentException("Null agrs");
    }

    protected void throwEx(Throwable throwable) throws ElementEx {
        logger.error(executorMarker, "Unable to execute " + throwable.toString());
        throw new ElementEx(throwable);
    }
}
