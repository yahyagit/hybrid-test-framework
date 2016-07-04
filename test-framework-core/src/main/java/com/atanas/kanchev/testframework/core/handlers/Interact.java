package com.atanas.kanchev.testframework.core.handlers;

import com.atanas.kanchev.testframework.core.context.WebContext;
import com.atanas.kanchev.testframework.core.handlers.interfaces.IContext;
import com.atanas.kanchev.testframework.selenium.driver_factory.DriverConfig;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.atanas.kanchev.testframework.core.handlers.CommonPageDefinitions.HTML.*;

/**
 * @author Atanas Ksnchev
 */
public class Interact implements IInteract, IContext {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(Interact.class);


    @Override
    public Interact typeIn(CharSequence... keys) {

        String tag = ((WebContext) context().getCurrentContext()).getCurrentElement().getTagName();
        if (tag.equals(INPUT.getDefinition()) ||
                tag.equals(TEXTAREA.getDefinition()) ||
                tag.equals(UIA_SECURETEXTFIELD.getDefinition()) ||
                tag.equals(UIA_TEXTFIELD.getDefinition()) ||
                tag.equals("android.widget.EditText")) {
            ((WebContext) context().getCurrentContext()).getCurrentElement().sendKeys(keys);
        } else {
            logger.error("elementWriteable() : Cannot write to this element ");
        }
        return this;
    }

    /**
     * Clear the text or textarea. Throws an exception if the current element
     * not an input field.
     */
    @Override
    public Interact clear() {
        try {
            ((WebContext) context().getCurrentContext()).getCurrentElement().clear();
            // Fire change event
            ((WebContext) context().getCurrentContext()).getCurrentElement().sendKeys(Keys.BACK_SPACE);
        } catch (NoSuchElementException nsee) {
            logger.error("Clear() : Element not, or within FORM, element");
        }
        return this;
    }

    /**
     * If the current element is a form or has a form as parent, submit it.
     * Otherwise throw an exception.
     *
     * @return true if submission successful or false if element is not, or
     * within, a FORM
     */
    @Override
    public Interact submit() {
        boolean submitted = false;
        try {
            ((WebContext) context().getCurrentContext()).getCurrentElement().submit();
            submitted = true;
        } catch (NoSuchElementException nsee) {
            logger.error("submit() : Element not, or within FORM, element");
        }
        return this;
    }

    @Override
    public Interact click() {
        try {
            if (((WebContext) context().getCurrentContext()).getCurrentElement() != null) {
                ((WebContext) context().getCurrentContext()).getCurrentElement().click();
                logger.debug("Element Clicked");

            } else {
                logger.error("click() : Element is Null");

            }
        } catch (ElementNotVisibleException e) {
            logger.error("click() : Element is Not visible");

        }

        return this;
    }

    @Override
    public Interact sleep(int duration) {

        try {
            Thread.sleep(duration * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return this;
    }

    /**
     * Click and hold a specific amount of time in milliseconds before release
     *
     * @param duration in milliseconds
     * @return false if no Element Present
     */
    @Override
    public Interact clickAndHold(int duration) {
        if (((WebContext) context().getCurrentContext()).getCurrentElement() != null) {
            logger.debug("Click and Hold Element");
            Actions actions = new Actions(((WebContext<WebDriver>) context().getCurrentContext()).getDriver());
            actions.clickAndHold(((WebContext) context().getCurrentContext()).getCurrentElement()).perform();
            sleep(duration);
            actions.release(((WebContext) context().getCurrentContext()).getCurrentElement()).perform();

        } else {
            logger.error("clickAndHold() : Element is Null");

        }
        return this;
    }

    @Override
    public Interact doubleClick() {
        if (((WebContext) context().getCurrentContext()).getCurrentElement() != null) {
            logger.debug("Double Clicking Element");
            Actions actions = new Actions(((WebContext<WebDriver>) context().getCurrentContext()).getDriver());
            actions.doubleClick(((WebContext) context().getCurrentContext()).getCurrentElement()).perform();

        } else {
            logger.error("doubleClick() : Element is Null");

        }
        return this;
    }

    @Override
    public Interact hover() {
        if (((WebContext) context().getCurrentContext()).getCurrentElement() != null) {
            logger.debug("Double Clicking Element");
            Actions actions = new Actions(((WebContext<WebDriver>) context().getCurrentContext()).getDriver());
            actions.moveToElement(((WebContext) context().getCurrentContext()).getCurrentElement()).perform();

        } else {
            logger.error("doubleClick() : Element is Null");

        }
        return this;
    }

    /**
     * Navigate the current Alert and Accept
     *
     * @return false if no Alert Present
     */
    @Override
    public Interact handleAlert(boolean accept) {
        try {
            new WebDriverWait(((WebContext<WebDriver>) context().getCurrentContext()).getDriver(), DriverConfig.DEFAULT_IMPL_WAIT).until(ExpectedConditions.alertIsPresent());
            Alert alert = ((WebContext<WebDriver>) context().getCurrentContext()).getDriver().switchTo().alert();
            if (accept) alert.accept();
            else alert.dismiss();

        } catch (NoAlertPresentException e) {

        }

        return this;
    }

    /**
     * Select all the text or textarea.
     * Sends the keys Ctrl + a
     */
    @Override
    public Interact selectAll() {
        ((WebContext<WebDriver>) context().getCurrentContext()).getCurrentElement().sendKeys(Keys.chord(Keys.CONTROL, "a"));
        return this;
    }

    /**
     * Copies the selected the text or textarea.
     * Sends the keys Ctrl + c
     */
    @Override
    public Interact copy() {
        ((WebContext<WebDriver>) context().getCurrentContext()).getCurrentElement().sendKeys(Keys.chord(Keys.CONTROL, "c"));
        return this;
    }

    /**
     * Pastes the copied the text or textarea.
     * Sends the keys Ctrl + v
     */
    @Override
    public Interact paste() {
        ((WebContext<WebDriver>) context().getCurrentContext()).getCurrentElement().sendKeys(Keys.chord(Keys.CONTROL, "v"));
        return this;
    }

    /**
     * Pastes the copied the text or textarea.
     * Sends the keys Ctrl + v
     */
    @Override
    public String getCssAttribute(String attribute) {

        return ((WebContext<WebDriver>) context().getCurrentContext()).getCurrentElement().getCssValue(attribute);
    }

    /**
     * Pastes the copied the text or textarea.
     * Sends the keys Ctrl + v
     */
    @Override
    public String getttribute(String attribute) {

        return ((WebContext<WebDriver>) context().getCurrentContext()).getCurrentElement().getAttribute(attribute);
    }

    /**
     * Pastes the copied the text or textarea.
     * Sends the keys Ctrl + v
     */
    @Override
    public String getText() {

        return ((WebContext<WebDriver>) context().getCurrentContext()).getCurrentElement().getText();
    }

}
