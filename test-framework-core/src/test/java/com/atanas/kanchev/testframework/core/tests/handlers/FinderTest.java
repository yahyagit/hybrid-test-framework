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

import com.atanas.kanchev.testframework.selenium.handlers.Finder;
import com.atanas.kanchev.testframework.core.handlers.wrappers.IWrapper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class FinderTest implements IWrapper {

    @Mock
    Finder ifind;

    private static URL url;
    private static final Logger logger = LoggerFactory.getLogger(FinderTest.class);

    @Before
    public void setUp() throws Exception {
        try {
            url = new URL("https://www.google.co.uk/");
        } catch (MalformedURLException e) {

        }
        setupSelenium().setBrowser("chrome");
    }

    @After
    public void tearDown() throws Exception {
        context().tearDownContexts();
    }

    @Test
    public void elementByTest() throws Exception {
goTo("https://www.google.co.uk/");


       find().elementBy(By.name("btnK"));

    }

    @Test
    public void test() throws Exception {

//        goTo("https://bbc.co.uk").waitFor(5L).elementToBeClickable(By.id("orb-modules"));

    }
}
