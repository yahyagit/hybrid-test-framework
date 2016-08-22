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
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by atanas on 22/08/16.
 */
public class TargetLocator<T extends WebDriver> implements WebDriver.TargetLocator, IContext<SeleniumContext<T>> {

    @Override
    public WebDriver frame(int i) {
        return context().getCurrentContext().getDriver().switchTo().frame(i);
    }

    @Override
    public WebDriver frame(String s) {
        return context().getCurrentContext().getDriver().switchTo().frame(s);
    }

    @Override
    public WebDriver frame(WebElement webElement) {
        return context().getCurrentContext().getDriver().switchTo().frame(webElement);
    }

    @Override
    public WebDriver parentFrame() {
        return context().getCurrentContext().getDriver().switchTo().parentFrame();
    }

    @Override
    public WebDriver window(String s) {
        return context().getCurrentContext().getDriver().switchTo().window(s);
    }

    @Override
    public WebDriver defaultContent() {
        return context().getCurrentContext().getDriver().switchTo().defaultContent();
    }

    @Override
    public WebElement activeElement() {
        return context().getCurrentContext().getDriver().switchTo().activeElement();
    }

    @Override
    public Alert alert() {
        return context().getCurrentContext().getDriver().switchTo().alert();
    }
}
