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

package com.atanas.kanchev.testframework.selenium.interactions.element;

import com.atanas.kanchev.testframework.selenium.handlers.Finds;
import com.atanas.kanchev.testframework.selenium.interactions.element.interactions.Alert;
import com.atanas.kanchev.testframework.selenium.interactions.element.interactions.*;
import com.atanas.kanchev.testframework.selenium.interactions.wait.Waiting;
import org.openqa.selenium.*;

import java.util.List;

/**
 * @author Atanas Kanchev
 */
public class OmniaElement implements IOmniaElement {

    public static OmniaElement omniaElement = new OmniaElement();

    public OmniaElement(By by) {
        find().elementBy(by);
    }

    public OmniaElement() {
    }

    @Override public void click() {
        new Click().click().execute();
    }

    @Override public void submit() {
        new Submit().submit().execute();
    }

    @Override public void sendKeys(CharSequence... keysToSend) {
        new SendKeys().sendKeys(keysToSend).execute();
    }

    @Override public void clear() {
        new Clear().clear().execute();
    }

    @Override public String getTagName() {
        return new GetTagName().getTagName().execute();
    }

    @Override public String getAttribute(String name) {
        return new GetAttribute().getAttribute(name).execute();
    }

    @Override public boolean isSelected() {
        return new IsSelected().isSelected().execute();
    }

    @Override public boolean isEnabled() {
        return new IsEnabled().isEnabled().execute();
    }

    @Override public String getText() {
        return new GetText().getText().execute();
    }

    @Override public List<WebElement> findElements(By by) {
        find().elementsBy(by);
        return null;
    }

    @Override public WebElement findElement(By by) {
        return null;
    }

    @Override public boolean isDisplayed() {
        return new IsDisplayed().isDisplayed().execute();
    }

    @Override public Point getLocation() {
        return new GetLocation().getLocation().execute();
    }

    @Override public Dimension getSize() {
        return new GetSize().getSize().execute();
    }

    @Override public Rectangle getRect() {
        return new GetRect().getRect().execute();
    }

    @Override public String getCssValue(String propertyName) {
        return new GetCssValue().getCssValue(propertyName).execute();
    }

    @Override public void clickAndHold(int duration) {
        new ClickAndHold().clickAndHold(duration).execute();
    }

    @Override public void doubleClick() {
        new DoubleClick().doubleClick().execute();
    }

    @Override public void contextClick() {
        new ContextClick().contextClick().execute();
    }

    @Override public IOmniaElement hover() {
        return new Hover().hover().execute();
    }

    @Override public IOmniaElement selectAll() {
        return new CopyPaste().command("a").execute();
    }

    @Override public IOmniaElement copy() {
        return new CopyPaste().command("c").execute();
    }

    @Override public IOmniaElement paste() {
        return new CopyPaste().command("v").execute();
    }

    @Override public IOmniaElement handleAlert(boolean accept) {
        new Alert().handleAlert(accept).execute();
        return this;
    }

    @Override public Finds find() {
        return new Finds();
    }

    @Override public Finds find(WebElement element) {
        return new Finds(element);
    }

    @Override public Finds find(Class<?> clazz) {
        return new Finds<>(clazz);
    }

    @Override public Waiting waitFor() {
        return new Waiting();
    }

    @Override public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return null;
    }

}
