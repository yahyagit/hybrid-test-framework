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

package com.atanas.kanchev.testframework.core.tests.handlers.selenium;

import org.junit.Test;

import static com.atanas.kanchev.testframework.selenium.accessors.SeleniumAccessors.$selenium;

public class NavigateSeleniumTest extends BaseTest {

    public static final String url = "https://bbc.co.uk";

    @Test
    public void getPage() throws Exception {
        $selenium().goTo(url);
    }

    @Test
    public void back() throws Exception {
        navigateToActivateFrame();
        $selenium().goTo(url).back();
    }

    @Test
    public void forward() throws Exception {
        $selenium().goTo(url).forward();
    }

    @Test
    public void refresh() throws Exception {
        $selenium().goTo(url).refresh();
    }

    @Test
    public void navigateToWindowByPartialTitle() throws Exception {

    }

    @Test
    public void navigateToWindow() throws Exception {

    }

    @Test
    public void navigateToOtherWindow() throws Exception {

    }

    @Test
    public void navigateToActivateFrame() throws Exception {

    }

    @Test
    public void navigateToFrameById() throws Exception {

    }

    @Test
    public void navigateToFrameBy() throws Exception {

    }

    @Test
    public void waitForFrameByIdToBeAvailableAndSwitch() throws Exception {

    }

    @Test
    public void returnToDefaultWindow() throws Exception {

    }

    @Test
    public void deleteCookies() throws Exception {

    }

    @Test
    public void deleteCookie() throws Exception {

    }

    @Test
    public void setCookie() throws Exception {

    }

}
