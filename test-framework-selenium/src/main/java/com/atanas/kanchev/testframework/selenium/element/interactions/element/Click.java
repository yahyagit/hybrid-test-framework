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

package com.atanas.kanchev.testframework.selenium.element.interactions.element;

import com.atanas.kanchev.testframework.commons.context.ContextKey;
import com.atanas.kanchev.testframework.selenium.context.SeleniumContext;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.StaleElementReferenceException;

/**
 * Created by atanas on 27/09/16.
 */
public class Click extends AbstractElementInteraction {

    public Click(ContextKey<SeleniumContext> currentContextKey) {
        super(currentContextKey);
    }

    public void click() {
        try {
            element.click();
        } catch (StaleElementReferenceException e) {
            logger.error("Stale element");
            throwEx(e);
        } catch (ElementNotVisibleException e) {
            logger.error("Element is Not visible");
            throwEx(e);
        }
    }

    public void doubleClick() {
        try {
            getActions().doubleClick(element).perform();
        } catch (Exception e) {
            throwEx(e);
        }
    }

    public void clickAndHold(long duration) {

        logger.debug("Click and Hold Element");
        try {
            getActions().clickAndHold(element).perform();
            Thread.sleep(duration);
            getActions().release(element).perform();
        } catch (Exception e) {
            throwEx(e);
        }

    }

    public void contextClick() {

        logger.debug("Click and Hold Element");
        try {
            getActions().contextClick(element).perform();
        } catch (Exception e) {
            throwEx(e);
        }

    }


}