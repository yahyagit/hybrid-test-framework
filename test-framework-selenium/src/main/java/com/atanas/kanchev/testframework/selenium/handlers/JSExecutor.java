package com.atanas.kanchev.testframework.selenium.handlers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * <p>JSExecutor class.</p>
 *
 * @author Atanas Ksnchev
 * @version $Id: $Id
 */
public class JSExecutor implements IJSExecutor {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(JSExecutor.class);

    /** {@inheritDoc} */
    @Override
    public <T> T executeScript(WebDriver webdriver, String script) {
        return (executeScript(webdriver, script, (Object) null));
    }

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T executeScript(WebDriver webdriver, String script, Object... args) {
        try {
            return (T) ((JavascriptExecutor) webdriver).executeScript(script, args);
        } catch (IllegalArgumentException iae) {
            logger.error("IJSExecutor : executeScript : Argument list must contain objects - Number, Boolean, String, WebElement, List or any combination thereof");
            logger.error("IJSExecutor : args : " + args.toString());
        }
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public <T> T executeAsyncScript(WebDriver webdriver, String script) {
        return (executeAsyncScript(webdriver, script, (Object) null));
    }

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T executeAsyncScript(WebDriver webdriver, String script, Object... args) {
        try {
            return (T) ((JavascriptExecutor) webdriver).executeAsyncScript(script, args);
        } catch (IllegalArgumentException iae) {
            logger.error("IJSExecutor : executeScript : Argument list must contain objects - Number, Boolean, String, WebElement, List or any combination thereof");
            logger.error("IJSExecutor : args : " + Arrays.toString(args));
        }
        return null;
    }

}
/**
 * Execute Javascript, with optional argument list, in current page com.atanas.kanchev.testframework.selenium.context
 *
 * @return Number, Boolean, String, WebElement, List or any combination
 *         thereof Null if argument list invalid
 */
interface IJSExecutor {

    /**
     * <p>executeScript.</p>
     *
     * @param webdriver a {@link org.openqa.selenium.WebDriver} object.
     * @param script a {@link java.lang.String} object.
     * @param <T> a T object.
     * @return a T object.
     */
    <T> T executeScript(WebDriver webdriver, String script);

    /**
     * <p>executeScript.</p>
     *
     * @param webdriver a {@link org.openqa.selenium.WebDriver} object.
     * @param script a {@link java.lang.String} object.
     * @param args a {@link java.lang.Object} object.
     * @param <T> a T object.
     * @return a T object.
     */
    <T> T executeScript(WebDriver webdriver, String script, Object... args);

    /**
     * <p>executeAsyncScript.</p>
     *
     * @param webdriver a {@link org.openqa.selenium.WebDriver} object.
     * @param script a {@link java.lang.String} object.
     * @param <T> a T object.
     * @return a T object.
     */
    <T> T executeAsyncScript(WebDriver webdriver, String script);

    /**
     * <p>executeAsyncScript.</p>
     *
     * @param webdriver a {@link org.openqa.selenium.WebDriver} object.
     * @param script a {@link java.lang.String} object.
     * @param args a {@link java.lang.Object} object.
     * @param <T> a T object.
     * @return a T object.
     */
    <T> T executeAsyncScript(WebDriver webdriver, String script, Object... args);
}
