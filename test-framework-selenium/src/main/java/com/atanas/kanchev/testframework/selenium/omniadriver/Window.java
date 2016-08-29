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

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;

import static com.atanas.kanchev.testframework.commons.accessors.ContextsAccessor.context;
import static com.atanas.kanchev.testframework.selenium.accessors.SeleniumAccessorsSingleton.currentContextKey;

/**
 * Created by atanas on 22/08/16.
 */
public class Window<T extends WebDriver> implements WebDriver.Window {

    @Override public void setSize(Dimension dimension) {
        context().getContext(currentContextKey).getDriver().manage().window()
            .setSize(dimension);
    }

    @Override public void setPosition(Point point) {
        context().getContext(currentContextKey).getDriver().manage().window()
            .setPosition(point);
    }

    @Override public Dimension getSize() {
        return context().getContext(currentContextKey).getDriver().manage().window()
            .getSize();
    }

    @Override public Point getPosition() {
        return context().getContext(currentContextKey).getDriver().manage().window()
            .getPosition();
    }

    @Override public void maximize() {
        context().getContext(currentContextKey).getDriver().manage().window().maximize();
    }

    @Override public void fullscreen() {
        context().getContext(currentContextKey).getDriver().manage().window().maximize();
    }
}
