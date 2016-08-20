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

package com.atanas.kanchev.testframework.selenium.tests.handlers;

import com.atanas.kanchev.testframework.commons.wrappers.IContext;
import com.atanas.kanchev.testframework.selenium.handlers.Waits;
import com.atanas.kanchev.testframework.selenium.wrappers.ISelenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for {@link Waits}
 */
public class WaitsTest implements ISelenium, IContext {

    @Before
    public void setUp() throws Exception {
        setupSelenium().setBrowser("chrome");
        goTo("https://www.google.co.uk");

    }

    @After
    public void tearDown() throws Exception {
        context().tearDownContexts();

    }

    @Test public void titleIs() throws Exception {

        waitFor().titleIs("Google");

    }

    @Test public void titleContains() throws Exception {

    }

    @Test public void urlToBe() throws Exception {

    }

    @Test public void urlContains() throws Exception {

    }

    @Test public void urlMatches() throws Exception {

    }

    @Test public void presenceOfElementLocated() throws Exception {

    }

    @Test public void visibilityOfElementLocated() throws Exception {

    }

    @Test public void visibilityOfAllElementsLocatedBy() throws Exception {

    }

    @Test public void visibilityOfAllElements() throws Exception {

    }

    @Test public void visibilityOf() throws Exception {

    }

    @Test public void presenceOfAllElementsLocatedBy() throws Exception {

    }

    @Test public void textToBePresentInElement() throws Exception {

    }

    @Test public void textToBePresentInElementLocated() throws Exception {

    }

    @Test public void textToBePresentInElementValue() throws Exception {

    }

    @Test public void textToBePresentInElementValue1() throws Exception {

    }

    @Test public void frameToBeAvailableAndSwitchToIt() throws Exception {

    }

    @Test public void frameToBeAvailableAndSwitchToIt1() throws Exception {

    }

    @Test public void frameToBeAvailableAndSwitchToIt2() throws Exception {

    }

    @Test public void frameToBeAvailableAndSwitchToIt3() throws Exception {

    }

    @Test public void invisibilityOfElementLocated() throws Exception {

    }

    @Test public void invisibilityOfElementWithText() throws Exception {

    }

    @Test public void elementToBeClickable() throws Exception {

    }

    @Test public void elementToBeClickable1() throws Exception {

    }

    @Test public void stalenessOf() throws Exception {

    }

    @Test public void refreshed() throws Exception {

    }

    @Test public void elementToBeSelected() throws Exception {

    }

    @Test public void elementSelectionStateToBe() throws Exception {

    }

    @Test public void elementToBeSelected1() throws Exception {

    }

    @Test public void elementSelectionStateToBe1() throws Exception {

    }

    @Test public void alertIsPresent() throws Exception {

    }

    @Test public void numberOfWindowsToBe() throws Exception {

    }

    @Test public void not() throws Exception {

    }

    @Test public void attributeToBe() throws Exception {

    }

    @Test public void textToBe() throws Exception {

    }

    @Test public void textMatches() throws Exception {

    }

    @Test public void numberOfElementsToBeMoreThan() throws Exception {

    }

    @Test public void numberOfElementsToBeLessThan() throws Exception {

    }

    @Test public void numberOfElementsToBe() throws Exception {

    }

    @Test public void attributeToBe1() throws Exception {

    }

    @Test public void attributeContains() throws Exception {

    }

    @Test public void attributeContains1() throws Exception {

    }

    @Test public void attributeToBeNotEmpty() throws Exception {

    }

    @Test public void visibilityOfNestedElementsLocatedBy() throws Exception {

    }

    @Test public void visibilityOfNestedElementsLocatedBy1() throws Exception {

    }

    @Test public void presenceOfNestedElementLocatedBy() throws Exception {

    }

    @Test public void presenceOfNestedElementLocatedBy1() throws Exception {

    }

    @Test public void presenceOfNestedElementsLocatedBy() throws Exception {

    }

    @Test public void invisibilityOfAllElements() throws Exception {

    }

    @Test public void or() throws Exception {

    }

    @Test public void and() throws Exception {

    }

    @Test public void javaScriptThrowsNoExceptions() throws Exception {

    }

    @Test public void jsReturnsValue() throws Exception {

    }

    @Test public void hardWait() throws Exception {

    }

}
