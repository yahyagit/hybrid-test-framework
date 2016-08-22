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

package com.atanas.kanchev.testframework.selenium.omniadriver;

import com.atanas.kanchev.testframework.commons.wrappers.IContext;
import com.atanas.kanchev.testframework.selenium.context.SeleniumContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

/**
 * Created by atanas on 22/08/16.
 */
public final class OmniaDriver implements WebDriver, IContext<SeleniumContext<OmniaDriver>> {

    private OmniaDriver() {
    }

    public static OmniaDriver instance = new OmniaDriver();

    @Override
    public void get(String s) {
        context().getCurrentContext().getDriver().get(s);
    }

    @Override
    public String getCurrentUrl() {
        return context().getCurrentContext().getDriver().getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return context().getCurrentContext().getDriver().getTitle();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return new SearchContext<>().findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return new SearchContext<>().findElement(by);
    }

    @Override
    public String getPageSource() {
        return context().getCurrentContext().getDriver().getPageSource();
    }

    @Override
    public void close() {
        context().getCurrentContext().getDriver().close();
    }

    @Override
    public void quit() {
        context().getCurrentContext().getDriver().quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return context().getCurrentContext().getDriver().getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return context().getCurrentContext().getDriver().getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        return new com.atanas.kanchev.testframework.selenium.omniadriver.TargetLocator<OmniaDriver>();
    }

    @Override
    public Navigation navigate() {
        return new com.atanas.kanchev.testframework.selenium.omniadriver.Navigation<OmniaDriver>();
    }

    @Override
    public Options manage() {
        return new com.atanas.kanchev.testframework.selenium.omniadriver.Options<OmniaDriver>();
    }
}
