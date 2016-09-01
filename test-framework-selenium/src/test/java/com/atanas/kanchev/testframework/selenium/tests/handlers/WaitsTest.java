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

import com.atanas.kanchev.testframework.selenium.context.SeleniumContext;
import com.atanas.kanchev.testframework.selenium.interactions.wait.Waiting;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

//TODO Complete all tests


/**
 * Test for {@link Waits}
 */
public class WaitsTest{

//    Waiting waits;
//
//    @Before public void setUp() throws Exception {
//        setupSelenium().setBrowser("chrome");
//        waits = new Waiting();
//    }
//
//    @After public void tearDown() throws Exception {
//        $context().tearDownContexts();
//    }
//
//    @Test public void titleIs() throws Exception {
//        goTo("https://www.google.co.uk");
//        waits.titleIs("Google");
//    }
//
//    @Test public void titleContains() throws Exception {
//        goTo("https://www.google.co.uk");
//        waits.titleContains("Goog");
//    }
//
//    @Test public void urlToBe() throws Exception {
//        goTo("https://www.google.co.uk");
//        waits.urlToBe("https://www.google.co.uk/");
//    }
//
//    @Test public void urlContains() throws Exception {
//        goTo("https://www.google.co.uk");
//        waits.urlContains("google");
//    }
//
//    @Test public void urlMatches() throws Exception {
//        goTo("https://www.google.co.uk");
//        waitFor().urlMatches("google");
//    }
//
//    @Test public void presenceOfElementLocated() throws Exception {
//        goTo("https://www.google.co.uk");
//        waits.presenceOfElementLocated(By.name("btnK"));
//        ((SeleniumContext) $context().getCurrentContext()).getCurrentElement().click();
//    }
//
//    @Test public void visibilityOfElementLocated() throws Exception {
//        goTo("https://www.google.co.uk");
//        waits.visibilityOfElementLocated(By.name("btnK"));
//    }
//
//    @Test public void visibilityOfAllElementsLocatedBy() throws Exception {
//        goTo("https://www.google.co.uk");
//        waitFor().visibilityOfAllElementsLocatedBy(By.name("btnK"));
//    }
//
//    @Test public void visibilityOfAllElements() throws Exception {
//        goTo("https://www.google.co.uk");
//        List<WebElement> elements = new ArrayList<>();
//        elements.add(
//            ((WebDriver) $context().getCurrentContext().getDriver()).findElement(By.name("btnK")));
//        waits.visibilityOfAllElements(elements);
//    }
//
//    @Test public void visibilityOf() throws Exception {
//        goTo("https://www.google.co.uk");
//        waits.visibilityOf(
//            ((WebDriver) $context().getCurrentContext().getDriver()).findElement(By.name("btnK")));
//    }
//
//    @Test public void presenceOfAllElementsLocatedBy() throws Exception {
//        goTo("https://www.google.co.uk");
//        waits.presenceOfAllElementsLocatedBy(By.name("btnK"));
//    }
//
//    @Test public void textToBePresentInElement() throws Exception {
//        goTo("https://www.google.co.uk");
//        waits.textToBePresentInElement(((WebDriver) $context().getCurrentContext().getDriver())
//            .findElement(By.className("gb_P")), "Gmail");
//    }
//
//    @Test public void textToBePresentInElementLocated() throws Exception {
//        goTo("https://www.google.co.uk");
//        waits.textToBePresentInElementLocated(By.className("gb_P"), "Gmail");
//    }
//
//    @Test public void textToBePresentInElementValue() throws Exception {
//        goTo("https://www.google.co.uk");
//        waits.textToBePresentInElementValue(By.name("btnK"), "Google Search");
//    }
//
//    @Test public void textToBePresentInElementValue1() throws Exception {
//        goTo("https://www.google.co.uk");
//        waits.textToBePresentInElementValue(
//            ((WebDriver) $context().getCurrentContext().getDriver()).findElement(By.name("btnK")),
//            "Google Search");
//    }
//
//    @Test public void frameToBeAvailableAndSwitchToIt() throws Exception {
//        goTo("https://www.w3.org/WAI/UA/TS/html401/cp0101/0101-FRAME-TEST.html");
//        waits.frameToBeAvailableAndSwitchToIt("target1");
//    }
//
//    @Test public void frameToBeAvailableAndSwitchToIt1() throws Exception {
//        goTo("https://www.w3.org/WAI/UA/TS/html401/cp0101/0101-FRAME-TEST.html");
//        waits.frameToBeAvailableAndSwitchToIt(By.name("target2"));
//    }
//
//    @Test public void frameToBeAvailableAndSwitchToIt2() throws Exception {
//        goTo("https://www.w3.org/WAI/UA/TS/html401/cp0101/0101-FRAME-TEST.html");
//        waits.frameToBeAvailableAndSwitchToIt(1);
//    }
//
//    @Test public void frameToBeAvailableAndSwitchToIt3() throws Exception {
//        goTo("https://www.w3.org/WAI/UA/TS/html401/cp0101/0101-FRAME-TEST.html");
//        waits.frameToBeAvailableAndSwitchToIt(
//            ((WebDriver) $context().getCurrentContext().getDriver())
//                .findElement(By.name("target2")));
//    }
//
//    @Test public void invisibilityOfElementLocated() throws Exception {
//        goTo("https://www.google.co.uk");
//        ((WebDriver) $context().getCurrentContext().getDriver()).findElement(By.className("gb_P"))
//            .click();
//        waits.invisibilityOfElementLocated(By.name("btnK"));
//
//    }
//
//    @Test public void invisibilityOfElementWithText() throws Exception {
//        goTo("https://www.google.co.uk");
//        ((WebDriver) $context().getCurrentContext().getDriver()).findElement(By.className("gb_P"))
//            .click();
//        waits.invisibilityOfElementWithText(By.name("btnK"), "Gmail");
//    }
//
//    @Test public void elementToBeClickable() throws Exception {
//        goTo("https://www.google.co.uk");
//        waits.elementToBeClickable(By.name("btnK"));
//    }
//
//    @Test public void elementToBeClickable1() throws Exception {
//        goTo("https://www.google.co.uk");
//        waits.elementToBeClickable(((WebDriver) $context().getCurrentContext().getDriver())
//            .findElement(By.className("gb_P")));
//    }
//
//    @Test public void stalenessOf() throws Exception {
//        goTo("https://www.google.co.uk");
//        waits.stalenessOf(
//            ((WebDriver) $context().getCurrentContext().getDriver()).findElement(By.name("btnK")));
//    }
//
//    @Test public void refreshed() throws Exception {
//        goTo("https://www.google.co.uk");
//        ((WebDriver) $context().getCurrentContext().getDriver()).findElement(By.className("gb_P"))
//            .click();
//        waits.refreshed(ExpectedConditions.invisibilityOfElementLocated(By.name("btnK")));
//    }
//
//    @Test public void elementToBeSelected() throws Exception {
//
//    }
//
//    @Test public void elementSelectionStateToBe() throws Exception {
//
//    }
//
//    @Test public void elementToBeSelected1() throws Exception {
//
//    }
//
//    @Test public void elementSelectionStateToBe1() throws Exception {
//
//    }
//
//    @Test public void alertIsPresent() throws Exception {
//
//    }
//
//    @Test public void numberOfWindowsToBe() throws Exception {
//
//    }
//
//    @Test public void not() throws Exception {
//
//    }
//
//    @Test public void attributeToBe() throws Exception {
//
//    }
//
//    @Test public void textToBe() throws Exception {
//
//    }
//
//    @Test public void textMatches() throws Exception {
//
//    }
//
//    @Test public void numberOfElementsToBeMoreThan() throws Exception {
//
//    }
//
//    @Test public void numberOfElementsToBeLessThan() throws Exception {
//
//    }
//
//    @Test public void numberOfElementsToBe() throws Exception {
//
//    }
//
//    @Test public void attributeToBe1() throws Exception {
//
//    }
//
//    @Test public void attributeContains() throws Exception {
//
//    }
//
//    @Test public void attributeContains1() throws Exception {
//
//    }
//
//    @Test public void attributeToBeNotEmpty() throws Exception {
//
//    }
//
//    @Test public void visibilityOfNestedElementsLocatedBy() throws Exception {
//
//    }
//
//    @Test public void visibilityOfNestedElementsLocatedBy1() throws Exception {
//
//    }
//
//    @Test public void presenceOfNestedElementLocatedBy() throws Exception {
//
//    }
//
//    @Test public void presenceOfNestedElementLocatedBy1() throws Exception {
//
//    }
//
//    @Test public void presenceOfNestedElementsLocatedBy() throws Exception {
//
//    }
//
//    @Test public void invisibilityOfAllElements() throws Exception {
//
//    }
//
//    @Test public void or() throws Exception {
//
//    }
//
//    @Test public void and() throws Exception {
//
//    }
//
//    @Test public void javaScriptThrowsNoExceptions() throws Exception {
//
//    }
//
//    @Test public void jsReturnsValue() throws Exception {
//
//    }
//
//    @Test public void hardWait() throws Exception {
//
//    }

}
