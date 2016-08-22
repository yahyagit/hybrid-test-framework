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

package com.atanas.kanchev.testframework.selenium.handlers;

import com.atanas.kanchev.testframework.commons.wrappers.IContext;
import com.atanas.kanchev.testframework.selenium.context.SeleniumContext;
import com.atanas.kanchev.testframework.selenium.driverfactory.Defaults;
import com.atanas.kanchev.testframework.selenium.driverfactory.DriverConfiguration;
import com.atanas.kanchev.testframework.selenium.wrappers.ISelenium;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Interact class.</p>
 *
 * @author Atanas Kanchev
 */
public class Interact implements IInteract, IContext<SeleniumContext> {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(Interact.class);

    /**
     * {@inheritDoc}
     */
    @Override public Interact typeIn(CharSequence... keys) {

        String tag =
            ((SeleniumContext) context().getCurrentContext()).getCurrentElement().getTagName();
        if (tag.equals(CommonPageDefinitions.HTML.INPUT.getDefinition()) || tag
            .equals(CommonPageDefinitions.HTML.TEXTAREA.getDefinition()) || tag
            .equals(CommonPageDefinitions.HTML.UIA_SECURETEXTFIELD.getDefinition()) || tag
            .equals(CommonPageDefinitions.HTML.UIA_TEXTFIELD.getDefinition()) || tag
            .equals("android.widget.EditText")) {
            ((SeleniumContext) context().getCurrentContext()).getCurrentElement().sendKeys(keys);
        } else {
            logger.error("elementWriteable() : Cannot write to this element ");
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public Interact clear() {
        try {
            ((SeleniumContext) context().getCurrentContext()).getCurrentElement().clear();
            // Fire change event
            ((SeleniumContext) context().getCurrentContext()).getCurrentElement()
                .sendKeys(Keys.BACK_SPACE);
        } catch (NoSuchElementException nsee) {
            logger.error("Clear() : Element not, or within FORM, element");
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public Interact submit() {
        boolean submitted = false;
        try {
            ((SeleniumContext) context().getCurrentContext()).getCurrentElement().submit();
            submitted = true;
        } catch (NoSuchElementException nsee) {
            logger.error("submit() : Element not, or within FORM, element");
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public Interact click() {
        try {
            if (((SeleniumContext) context().getCurrentContext()).getCurrentElement() != null) {

                ((SeleniumContext) context().getCurrentContext()).getCurrentElement().click();
                logger.debug("Element Clicked");

            } else {
                logger.error("click() : Element is Null");

            }
        } catch (ElementNotVisibleException e) {
            logger.error("click() : Element is Not visible");
        } catch (WebDriverException e) {
            logger.debug("WebDriverException ", e.getMessage());
        }

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public Interact sleep(int duration) {

        try {
            Thread.sleep(duration * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public Interact clickAndHold(int duration) {
        if (((SeleniumContext) context().getCurrentContext()).getCurrentElement() != null) {
            logger.debug("Click and Hold Element");
            Actions actions = new Actions(
                ((SeleniumContext<WebDriver>) context().getCurrentContext()).getDriver());
            actions
                .clickAndHold(((SeleniumContext) context().getCurrentContext()).getCurrentElement())
                .perform();
            sleep(duration);
            actions.release(((SeleniumContext) context().getCurrentContext()).getCurrentElement())
                .perform();

        } else {
            logger.error("clickAndHold() : Element is Null");

        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public Interact doubleClick() {
        if (((SeleniumContext) context().getCurrentContext()).getCurrentElement() != null) {
            logger.debug("Double Clicking Element");
            Actions actions = new Actions(
                ((SeleniumContext<WebDriver>) context().getCurrentContext()).getDriver());
            actions
                .doubleClick(((SeleniumContext) context().getCurrentContext()).getCurrentElement())
                .perform();

        } else {
            logger.error("doubleClick() : Element is Null");

        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public Interact hover() {
        if (((SeleniumContext) context().getCurrentContext()).getCurrentElement() != null) {
            logger.debug("Double Clicking Element");
            Actions actions = new Actions(
                ((SeleniumContext<WebDriver>) context().getCurrentContext()).getDriver());
            actions.moveToElement(
                ((SeleniumContext) context().getCurrentContext()).getCurrentElement()).perform();

        } else {
            logger.error("doubleClick() : Element is Null");

        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public Interact handleAlert(boolean accept) {
        try {
            new WebDriverWait(
                ((SeleniumContext<WebDriver>) context().getCurrentContext()).getDriver(),
                Defaults.DEF_IMPL_WAIT).until(ExpectedConditions.alertIsPresent());
            Alert alert =
                ((SeleniumContext<WebDriver>) context().getCurrentContext()).getDriver().switchTo()
                    .alert();
            if (accept)
                alert.accept();
            else
                alert.dismiss();

        } catch (NoAlertPresentException e) {

        }

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public Interact selectAll() {
        ((SeleniumContext<WebDriver>) context().getCurrentContext()).getCurrentElement()
            .sendKeys(Keys.chord(Keys.CONTROL, "a"));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public Interact copy() {
        ((SeleniumContext<WebDriver>) context().getCurrentContext()).getCurrentElement()
            .sendKeys(Keys.chord(Keys.CONTROL, "c"));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public Interact paste() {
        ((SeleniumContext<WebDriver>) context().getCurrentContext()).getCurrentElement()
            .sendKeys(Keys.chord(Keys.CONTROL, "v"));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public String getCssAttribute(String attribute) {

        return ((SeleniumContext<WebDriver>) context().getCurrentContext()).getCurrentElement()
            .getCssValue(attribute);
    }

    /**
     * {@inheritDoc}
     */
    @Override public String getttribute(String attribute) {

        return ((SeleniumContext<WebDriver>) context().getCurrentContext()).getCurrentElement()
            .getAttribute(attribute);
    }

    /**
     * {@inheritDoc}
     */
    @Override public String getText() {

        return ((SeleniumContext<WebDriver>) context().getCurrentContext()).getCurrentElement()
            .getText();
    }


}


interface IInteract extends ISelenium {

    /**
     * <p>typeIn.</p>
     *
     * @param keys a {@link java.lang.CharSequence} object.
     * @param <T>  a T object.
     * @return a {@link com.atanas.kanchev.testframework.selenium.handlers.IInteract} object.
     */
    <T> IInteract typeIn(CharSequence... keys);

    /**
     * <p>clear.</p>
     *
     * @return a {@link com.atanas.kanchev.testframework.selenium.handlers.IInteract} object.
     */
    IInteract clear();

    /**
     * <p>submit.</p>
     *
     * @return a {@link com.atanas.kanchev.testframework.selenium.handlers.IInteract} object.
     */
    IInteract submit();

    /**
     * <p>click.</p>
     *
     * @return a {@link com.atanas.kanchev.testframework.selenium.handlers.IInteract} object.
     */
    IInteract click();

    /**
     * <p>sleep.</p>
     *
     * @param duration a int.
     * @return a {@link com.atanas.kanchev.testframework.selenium.handlers.IInteract} object.
     */
    IInteract sleep(int duration);

    /**
     * <p>clickAndHold.</p>
     *
     * @param duration a int.
     * @return a {@link com.atanas.kanchev.testframework.selenium.handlers.IInteract} object.
     */
    IInteract clickAndHold(int duration);

    /**
     * <p>doubleClick.</p>
     *
     * @return a {@link com.atanas.kanchev.testframework.selenium.handlers.IInteract} object.
     */
    IInteract doubleClick();

    /**
     * <p>hover.</p>
     *
     * @return a {@link com.atanas.kanchev.testframework.selenium.handlers.IInteract} object.
     */
    IInteract hover();

    /**
     * <p>handleAlert.</p>
     *
     * @param accept a boolean.
     * @return a {@link com.atanas.kanchev.testframework.selenium.handlers.IInteract} object.
     */
    IInteract handleAlert(boolean accept);

    /**
     * <p>selectAll.</p>
     *
     * @return a {@link com.atanas.kanchev.testframework.selenium.handlers.IInteract} object.
     */
    IInteract selectAll();

    /**
     * <p>copy.</p>
     *
     * @return a {@link com.atanas.kanchev.testframework.selenium.handlers.IInteract} object.
     */
    IInteract copy();

    /**
     * <p>paste.</p>
     *
     * @return a {@link com.atanas.kanchev.testframework.selenium.handlers.IInteract} object.
     */
    IInteract paste();

    /**
     * <p>getCssAttribute.</p>
     *
     * @param attribute a {@link java.lang.String} object.
     * @return a {@link java.lang.String} object.
     */
    String getCssAttribute(String attribute);

    /**
     * <p>getttribute.</p>
     *
     * @param attribute a {@link java.lang.String} object.
     * @return a {@link java.lang.String} object.
     */
    String getttribute(String attribute);

    /**
     * <p>getText.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getText();

}
