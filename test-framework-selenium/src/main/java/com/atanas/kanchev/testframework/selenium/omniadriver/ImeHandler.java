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
import org.openqa.selenium.WebDriver;

import java.util.List;

/**
 * Created by atanas on 22/08/16.
 */
public class ImeHandler<T extends WebDriver> implements WebDriver.ImeHandler, IContext<SeleniumContext<T>> {

    @Override
    public List<String> getAvailableEngines() {
        return context().getCurrentContext().getDriver().manage().ime().getAvailableEngines();
    }

    @Override
    public String getActiveEngine() {
        return context().getCurrentContext().getDriver().manage().ime().getActiveEngine();
    }

    @Override
    public boolean isActivated() {
        return context().getCurrentContext().getDriver().manage().ime().isActivated();
    }

    @Override
    public void deactivate() {
        context().getCurrentContext().getDriver().manage().ime().deactivate();
    }

    @Override
    public void activateEngine(String s) {
        context().getCurrentContext().getDriver().manage().ime().activateEngine(s);
    }
}
