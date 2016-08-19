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

package com.atanas.kanchev.testframework.selenium.handlers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * <p>JSExecutor class.</p>
 *
 * @author Atanas Kanchev
 */
public class JSExecutor implements IJSExecutor {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(JSExecutor.class);

    /**
     * {@inheritDoc}
     */
    @Override public <T> T executeScript(WebDriver webdriver, String script) {
        return (executeScript(webdriver, script, (Object) null));
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked") @Override public <T> T executeScript(WebDriver webdriver,
        String script, Object... args) {
        try {
            return (T) ((JavascriptExecutor) webdriver).executeScript(script, args);
        } catch (IllegalArgumentException iae) {
            logger.error(
                "IJSExecutor : executeScript : Argument list must contain objects - Number, Boolean, String, WebElement, List or any combination thereof");
            logger.error("IJSExecutor : args : " + args.toString());
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override public <T> T executeAsyncScript(WebDriver webdriver, String script) {
        return (executeAsyncScript(webdriver, script, (Object) null));
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked") @Override public <T> T executeAsyncScript(WebDriver webdriver,
        String script, Object... args) {
        try {
            return (T) ((JavascriptExecutor) webdriver).executeAsyncScript(script, args);

        } catch (IllegalArgumentException iae) {
            logger.error(
                "IJSExecutor : executeScript : Argument list must contain objects - Number, Boolean, String, WebElement, List or any combination thereof");
            logger.error("IJSExecutor : args : " + Arrays.toString(args));

        }

        return null;
    }
}

interface IJSExecutor {

    <T> T executeScript(WebDriver webdriver, String script);

    <T> T executeScript(WebDriver webdriver, String script, Object... args);

    <T> T executeAsyncScript(WebDriver webdriver, String script);

    <T> T executeAsyncScript(WebDriver webdriver, String script, Object... args);
}
