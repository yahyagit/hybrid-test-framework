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

package com.atanas.kanchev.testframework.selenium.element;

import com.atanas.kanchev.testframework.selenium.element.interactions.Alert;
import com.atanas.kanchev.testframework.selenium.element.interactions.*;
import com.atanas.kanchev.testframework.selenium.handlers.Finds;
import org.openqa.selenium.*;

import java.util.List;

/**
 * @author Atanas Kanchev
 */
public class OmniaElement implements IOmniaElement {

    public static OmniaElement omniaElement = new OmniaElement();

    @Override public void click() {
        new Click().execute();
    }

    @Override public void submit() {
        new Submit().execute();
    }

    @Override public void sendKeys(CharSequence... keysToSend) {
        new SendKeys().execute(keysToSend);
    }

    @Override public void clear() {
        new Clear().execute();
    }

    @Override public String getTagName() {
        return new GetTagName().execute();
    }

    @Override public String getAttribute(String name) {
        return new GetAttribute().execute(name);
    }

    @Override public boolean isSelected() {
        return new IsSelected().execute();
    }

    @Override public boolean isEnabled() {
        return new IsEnabled().execute();
    }

    @Override public String getText() {
        return new GetText().execute();
    }

    @Override public List<WebElement> findElements(By by) {
        return null;
    }

    @Override public WebElement findElement(By by) {
        return null;
    }

    @Override public boolean isDisplayed() {
        return new IsDisplayed().execute();
    }

    @Override public Point getLocation() {
        return new GetLocation().execute();
    }

    @Override public Dimension getSize() {
        return new GetSize().execute();
    }

    @Override public Rectangle getRect() {
        return new GetRect().execute();
    }

    @Override public String getCssValue(String propertyName) {
        return new GetCssValue().execute();
    }

    @Override public void clickAndHold(int duration) {
        new ClickAndHold().execute(duration);
    }

    @Override public void doubleClick() {
        new DoubleClick().execute();
    }

    @Override public void contextClick() {
        new ContextClick().execute();
    }

    @Override public IOmniaElement hover() {
        return new Hover().execute();
    }

    @Override public IOmniaElement selectAll() {
        return new CopyPaste().execute("a");
    }

    @Override public IOmniaElement copy() {
        return new CopyPaste().execute("c");
    }

    @Override public IOmniaElement paste() {
        return new CopyPaste().execute("v");
    }

    @Override public IOmniaElement handleAlert(boolean accept) {
        return new Alert().execute(accept);
    }

    @Override public Finds find() {
        return new Finds();
    }

    @Override public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return null;
    }


}
