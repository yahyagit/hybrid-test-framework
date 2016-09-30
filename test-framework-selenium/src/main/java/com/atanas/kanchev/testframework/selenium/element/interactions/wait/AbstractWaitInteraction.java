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

package com.atanas.kanchev.testframework.selenium.element.interactions.wait;

import com.atanas.kanchev.testframework.commons.context.ContextKey;
import com.atanas.kanchev.testframework.selenium.context.SeleniumContext;
import com.atanas.kanchev.testframework.selenium.driverfactory.Defaults;
import com.atanas.kanchev.testframework.selenium.element.interactions.AbstractInteraction;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.concurrent.TimeUnit;

/**
 * @author Atanas Kanchev
 */
public class AbstractWaitInteraction extends AbstractInteraction {

    protected long duration;
    protected long polling;
    protected TimeUnit unit;

    public AbstractWaitInteraction(ContextKey<SeleniumContext> currentContextKey) {
        super(currentContextKey);
        this.duration = Defaults.DEF_IMPL_WAIT;
        this.polling = Defaults.DEF_POLLING_INTERVAL;
        this.unit = Defaults.DEF_TIME_UNITS;
        logger.debug(toString());
    }

    public AbstractWaitInteraction(ContextKey<SeleniumContext> currentContextKey, long duration,
        long polling, TimeUnit unit) {
        super(currentContextKey);
        this.duration = duration;
        this.polling = polling;
        this.unit = unit;
    }

    public AbstractWaitInteraction(ContextKey<SeleniumContext> currentContextKey, long duration) {
        super(currentContextKey);
        this.duration = duration;
        this.polling = Defaults.DEF_POLLING_INTERVAL;
        this.unit = Defaults.DEF_TIME_UNITS;
    }

    protected <T> T exec(ExpectedCondition<T> condition) {
        logger.debug("Waiting " + condition.toString());
        try {
            return getFluentWait(duration).until(condition);
        } catch (org.openqa.selenium.TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    protected <T> T wait(ExpectedCondition<T> condition) {
        logger.debug("Waiting " + condition.toString());
        try {
            return getFluentWait(duration).until(condition);
        } catch (org.openqa.selenium.TimeoutException e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    public Wait<WebDriver> getFluentWait(long duration) {
        return new FluentWait<>(driver)
            .withTimeout(duration, unit)
            .pollingEvery(polling, unit)
            .ignoring(NoSuchElementException.class)
            .ignoring(NoSuchFrameException.class)
            .ignoring(StaleElementReferenceException.class)
            .ignoring(ElementNotVisibleException.class);

    }

    @Override public String toString() {
        return "Waits {" + "duration=" + duration + ", polling=" + polling + ", unit=" + unit + '}';
    }

}
