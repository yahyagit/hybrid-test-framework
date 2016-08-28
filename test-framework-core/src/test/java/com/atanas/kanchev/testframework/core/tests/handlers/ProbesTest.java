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

package com.atanas.kanchev.testframework.core.tests.handlers;

import org.junit.Before;

import static com.atanas.kanchev.testframework.selenium.accessors.SeleniumAccessors.$selenium;


public class ProbesTest {

    @Before
    public void setUp() throws Exception {
        $selenium().driverSetup()
                .setBrowser("chrome");
    }


//    @Test
//    public void probeNotExistingElementTest() throws Exception {
//        Assert.assertFalse(goTo("https://bbc.co.uk").probe(By.id("non-existent")).exist());
//    }
//
//    @Test
//    public void probeExistingElementTest() throws Exception {
//        Assert.assertTrue(goTo("https://bbc.co.uk").probe(By.linkText("Homepage")).exist());
//    }
//
//    @Test
//    public void probeHasAnyTextTest() throws Exception {
//        Assert.assertTrue(goTo("https://bbc.co.uk").probe(By.linkText("Homepage")).hasAnyText());
//    }
//
//    @Test
//    public void probeDoesntHaveAnyTextTest() throws Exception {
//        Assert.assertFalse(goTo("https://bbc.co.uk").probe(By.id("orb-search-button")).hasAnyText());
//    }
//
//    @Test
//    public void probeHasTextTest() throws Exception {
//        Assert.assertTrue(goTo("https://bbc.co.uk").probe(By.linkText("Homepage")).hasText(false, false, "Home"));
//    }
//
//    @Test
//    public void probeHasTextCaseSensitiveTest() throws Exception {
//        Assert.assertFalse(goTo("https://bbc.co.uk").probe(By.linkText("Homepage")).hasText(true, false, "home"));
//    }
//
//    @Test
//    public void probeHasPreciseTextCaseSensitiveTest() throws Exception {
//        Assert.assertFalse(goTo("https://bbc.co.uk").probe(By.linkText("Homepage")).hasText(true, true, "homepage"));
//    }
//
//    @Test
//    public void probeHasPreciseAttributeTest() throws Exception {
//        Assert.assertTrue(goTo("https://bbc.co.uk").probe(By.id("orb-search-button")).hasAttribute(true, "type", "submit"));
//    }
//
//    @Test
//    public void probeHasPartialAttributeTest() throws Exception {
//        Assert.assertTrue(goTo("https://bbc.co.uk").probe(By.id("orb-search-button")).hasAttribute(false, "type", "sub"));
//    }
//
//    @Test
//    public void probeNotExistingAttributeTest() throws Exception {
//        Assert.assertFalse(goTo("https://bbc.co.uk").probe(By.id("orb-search-button")).hasAttribute(true, "not-existing", "submit"));
//    }
//
//    @Test
//    public void probeIsOfTypeTest() throws Exception {
//        Assert.assertTrue(goTo("https://bbc.co.uk").probe(By.linkText("Homepage")).isOfTagType("a"));
//    }
//
//    @Test
//    public void probeIsEnabledTest() throws Exception {
//        Assert.assertTrue(goTo("https://bbc.co.uk").probe(By.linkText("Homepage")).isEnabled());
//    }
//
//    @Test
//    public void probeIsSelectedTest() throws Exception {
//        Assert.assertTrue(goTo("https://bbc.co.uk").probe(By.linkText("Homepage")).isSelected());
//    }
//
//    @Test
//    public void probeIsActiveTest() throws Exception {
//        Assert.assertFalse(goTo("https://bbc.co.uk").probe(By.linkText("Homepage")).isActive());
//    }
//
//    @Test
//    public void probeHasColorTest() throws Exception {
//        Assert.assertTrue(goTo("https://bbc.co.uk").probe(By.linkText("Homepage")).hasColour(CommonPageDefinitions.CSS.CSS_BACKGROUND_COLOUR, "#000000"));
//    }
}
