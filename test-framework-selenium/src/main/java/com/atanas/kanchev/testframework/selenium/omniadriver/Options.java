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

package com.atanas.kanchev.testframework.selenium.omniadriver;

import com.atanas.kanchev.testframework.selenium.context.SeleniumContext;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.Logs;

import java.util.Set;

import static com.atanas.kanchev.testframework.commons.init.OmniaInit.context;

/**
 * Created by atanas on 22/08/16.
 */
public class Options<T extends WebDriver> implements WebDriver.Options {

    @Override public void addCookie(Cookie cookie) {
        context().<SeleniumContext<T>>getCurrentContext().getDriver().manage().addCookie(cookie);
    }

    @Override public void deleteCookieNamed(String s) {
        context().<SeleniumContext<T>>getCurrentContext().getDriver().manage().deleteCookieNamed(s);
    }

    @Override public void deleteCookie(Cookie cookie) {
        context().<SeleniumContext<T>>getCurrentContext().getDriver().manage().deleteCookie(cookie);
    }

    @Override public void deleteAllCookies() {
        context().<SeleniumContext<T>>getCurrentContext().getDriver().manage().deleteAllCookies();
    }

    @Override public Set<Cookie> getCookies() {
        return context().<SeleniumContext<T>>getCurrentContext().getDriver().manage().getCookies();
    }

    @Override public Cookie getCookieNamed(String s) {
        return context().<SeleniumContext<T>>getCurrentContext().getDriver().manage()
            .getCookieNamed(s);
    }

    @Override public WebDriver.Timeouts timeouts() {
        return new Timeouts<T>();
    }

    @Override public WebDriver.ImeHandler ime() {
        return new ImeHandler<T>();
    }

    @Override public WebDriver.Window window() {
        return new Window<T>();
    }

    @Override public Logs logs() {
        return null;
    }
}
