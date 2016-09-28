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

package com.atanas.kanchev.testframework.selenium.inv.atanas.refactoring.element;

import com.atanas.kanchev.testframework.selenium.inv.atanas.refactoring.ImplementedBy;
import com.atanas.kanchev.testframework.selenium.inv.atanas.refactoring.commands.Alert;
import com.atanas.kanchev.testframework.selenium.inv.atanas.refactoring.commands.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.internal.WrapsElement;

import java.util.List;

/**
 * Created by atanas on 23/09/16.
 */
public interface IElement extends WebElement, WrapsElement, Locatable {

    @ImplementedBy(Hover.class) IElement hover();

    @ImplementedBy(CopyPaste.class) CopyPaste copyPaste(String command);

    @ImplementedBy(Alert.class) Alert handleAlert(boolean accept);

    @ImplementedBy(Click.class) IElement clickAndHold(long duration);

    @ImplementedBy(Click.class) IElement contextClick();

    @ImplementedBy(Click.class) IElement doubleClick();

    @ImplementedBy(Click.class) @Override void click();

    @ImplementedBy(Submit.class) @Override void submit();

    @ImplementedBy(SendKeys.class) @Override void sendKeys(CharSequence... keysToSend);

    @ImplementedBy(Clear.class) @Override void clear();

    @ImplementedBy(GetTagName.class) @Override String getTagName();

    @ImplementedBy(GetAttribute.class) @Override String getAttribute(String name);

    @ImplementedBy(IsSelected.class) @Override boolean isSelected();

    @ImplementedBy(IsEnabled.class) @Override boolean isEnabled();

    @ImplementedBy(GetText.class) @Override String getText();

    @ImplementedBy(FindElement.class) @Override List<WebElement> findElements(By by);

    @ImplementedBy(FindElement.class) @Override WebElement findElement(By by);

    @ImplementedBy(IsDisplayed.class) @Override boolean isDisplayed();

    @ImplementedBy(GetLocation.class) @Override Point getLocation();

    @ImplementedBy(GetSize.class) @Override Dimension getSize();

    @ImplementedBy(GetRect.class) @Override Rectangle getRect();

    @ImplementedBy(GetCssValue.class) @Override String getCssValue(String propertyName);

    @ImplementedBy(Click.class) @Override <X> X getScreenshotAs(OutputType<X> target)
        throws WebDriverException;

    @ImplementedBy(GetCoordinates.class) @Override Coordinates getCoordinates();

    @ImplementedBy(GetWrappedElement.class) @Override WebElement getWrappedElement();
}

