package com.atanas.kanchev.testframework.core.handlers.selenium;

import org.openqa.selenium.WebDriver;

import java.net.URL;

import static com.atanas.kanchev.testframework.core.context.ContextFactory.getCurrentContext;

/**
 * @author Atanas Ksnchev
 */
public interface ISelenium {

    interface IInteract {

        <T>IInteract typeIn(CharSequence... keys);

        /**
         * Clear the text or text area. Throws an exception if the current element
         * not an input field.
         */
        IInteract clear();

        /**
         * If the current element is a form or has a form as parent, submit it.
         * Otherwise throw an exception.
         *
         * @return true if submission successful or false if element is not, or
         * within, a FORM
         */
        IInteract submit();

        IInteract click();

        IInteract sleep(int duration);

        /**
         * Click and hold a specific amount of time in milliseconds before release
         *
         * @param duration in milliseconds
         * @return false if no Element Present
         */
        IInteract clickAndHold(int duration);

        IInteract doubleClick();

        IInteract hover();

        /**
         * Navigate the current Alert and Accept
         *
         * @return false if no Alert Present
         */
        IInteract handleAlert(boolean accept);

        /**
         * Select all the text or textarea.
         * Sends the keys Ctrl + a
         */
        IInteract selectAll();

        /**
         * Copies the selected the text or textarea.
         * Sends the keys Ctrl + c
         */
        IInteract copy();

        /**
         * Pastes the copied the text or textarea.
         * Sends the keys Ctrl + v
         */
        IInteract paste();

        /**
         * Pastes the copied the text or textarea.
         * Sends the keys Ctrl + v
         */
        String getCssAttribute(String attribute);

        /**
         * Pastes the copied the text or textarea.
         * Sends the keys Ctrl + v
         */
        String getttribute(String attribute);

        /**
         * Pastes the copied the text or textarea.
         * Sends the keys Ctrl + v
         */
        String getText();

    }

    /**
     * @author Atanas Ksnchev
     */
    interface INavigate {

        static void getPage(final URL url) {
            ((WebDriver) getCurrentContext().getDriver()).navigate().to(url);
        }

        static void back() {
            ((WebDriver) getCurrentContext().getDriver()).navigate().back();
        }

        static void forward() {
            ((WebDriver) getCurrentContext().getDriver()).navigate().forward();
        }

        static void refresh() {
            ((WebDriver) getCurrentContext().getDriver()).navigate().refresh();
        }
    }
}
