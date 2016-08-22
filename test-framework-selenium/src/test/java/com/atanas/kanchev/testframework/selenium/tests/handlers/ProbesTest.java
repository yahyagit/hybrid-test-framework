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
import com.atanas.kanchev.testframework.selenium.handlers.Probes;
import com.atanas.kanchev.testframework.selenium.wrappers.ISelenium;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;

/**
 * Tests for {@link Probes}
 */
public class ProbesTest implements ISelenium, IContext {

    @Before public void setUp() throws Exception {
        setupSelenium()
            .setBrowser("chrome")
           /* .setReuseBrowser(true)
        .setCustomCapabilities(DesiredCapabilities.android())*/;
    }

    @After public void tearDown() throws Exception {
        context().tearDownContexts();

    }

    @Test public void exist() throws Exception {
        goTo("https://www.google.co.uk");

        WebDriverRunner.setWebDriver((WebDriver) context().getCurrentContext().getDriver());
        $(By.name("btnK")).click();
//        Assert.assertTrue(new Probes(By.name("btnK")).exist());
    }

    @Test public void hasAnyText() throws Exception {
        goTo("https://www.google.co.uk");
        Assert.assertTrue(new Probes(By.className("gb_P")).hasAnyText());
    }

    @Test public void hasText() throws Exception {
        goTo("https://www.google.co.uk");
        Assert.assertTrue(new Probes(By.className("gb_P")).hasText(true, true, "Gmail"));
    }

    @Test public void hasAttribute() throws Exception {
        goTo("https://www.google.co.uk");
        Assert.assertTrue(new Probes(By.name("btnK")).hasAttribute("type", "submit", false));
    }

    @Test public void isOfTagType() throws Exception {
        goTo("https://www.google.co.uk");
        Assert.assertTrue(new Probes(By.name("btnK")).isOfTagType("input"));
    }

    @Test public void isEnabled() throws Exception {
        goTo("https://www.google.co.uk");
        Assert.assertTrue(new Probes(By.name("btnK")).isEnabled());
    }

    @Test public void isSelected() throws Exception {
        goTo("https://www.google.co.uk");
        Assert.assertFalse(new Probes(By.name("btnK")).isSelected());
    }

    @Test public void isActive() throws Exception {
        goTo("https://www.google.co.uk");
        Assert.assertTrue(new Probes(By.name("btnK")).isActive());
    }

    @Test public void isDisplayed() throws Exception {
        goTo("https://www.google.co.uk");
        Assert.assertTrue(new Probes(By.name("btnK")).isDisplayed());
    }

    @Test public void hasColour() throws Exception {

    }

    @Test public void hasURL() throws Exception {
        goTo("https://www.google.co.uk");
//        Assert.assertTrue(
//            new Probes(By.className("gb_P")).hasURL("google.co", false));
    }

    @Test public void hasTitle() throws Exception {

    }

    @Test public void hasPartialImagePath() throws Exception {

    }

    @Test public void hasLinkToURL() throws Exception {

    }

    @Test public void followLinkToURL() throws Exception {

    }

    @Test public void titleHasText() throws Exception {

    }

    @Test public void hasPartialCookieValue() throws Exception {

    }

    @Test public void titleIs() throws Exception {
        goTo("https://www.google.co.uk");
        Assert.assertTrue(new Probes(By.name("btnK")).titleContains("Google"));
    }

}
