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
        new Click().click();
    }

    @Override public void submit() {
        new Submit().submit();
    }

    @Override public void sendKeys(CharSequence... keysToSend) {
        new SendKeys().sendKeys(keysToSend);
    }

    @Override public void clear() {
        new Clear().clear();
    }

    @Override public String getTagName() {
        return new GetTagName().getTagName();
    }

    @Override public String getAttribute(String name) {
        return new GetAttribute().getAttribute(name);
    }

    @Override public boolean isSelected() {
        return new IsSelected().isSelected();
    }

    @Override public boolean isEnabled() {
        return new IsEnabled().isEnabled();
    }

    @Override public String getText() {
        return new GetText().getText();
    }

    @Override public List<WebElement> findElements(By by) {
        return null;
    }

    @Override public WebElement findElement(By by) {
        return null;
    }

    @Override public boolean isDisplayed() {
        return new IsDisplayed().isDisplayed();
    }

    @Override public Point getLocation() {
        return new GetLocation().getLocation();
    }

    @Override public Dimension getSize() {
        return new GetSize().getSize();
    }

    @Override public Rectangle getRect() {
        return new GetRect().getRect();
    }

    @Override public String getCssValue(String propertyName) {
        return new GetCssValue().getCssValue(propertyName);
    }

    @Override public void clickAndHold(int duration) {
        new ClickAndHold().clickAndHold(duration);
    }

    @Override public void doubleClick() {
        new DoubleClick().doubleClick();
    }

    @Override public void contextClick() {
        new ContextClick().contextClick();
    }

    @Override public IOmniaElement hover() {
        return new Hover().hover();
    }

    @Override public IOmniaElement selectAll() {
        return new CopyPaste().command("a");
    }

    @Override public IOmniaElement copy() {
        return new CopyPaste().command("c");
    }

    @Override public IOmniaElement paste() {
        return new CopyPaste().command("v");
    }

    @Override public IOmniaElement handleAlert(boolean accept) {
        return new Alert().handleAlert(accept);
    }

    @Override public Finds find() {
        return new Finds();
    }

    @Override public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return null;
    }


}
