package com.atanas.kanchev.testframework.selenium.handlers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @author Atanas Ksnchev
 */
public class JSExecutor implements IJSExecutor {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(JSExecutor.class);

    @Override
    public <T> T executeScript(WebDriver webdriver, String script) {
        return (executeScript(webdriver, script, (Object) null));
    }

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

    @Override
    public <T> T executeAsyncScript(WebDriver webdriver, String script) {
        return (executeAsyncScript(webdriver, script, (Object) null));
    }

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
 * @param script
 *            : text
 * @param args
 *            : variable size object list consisting of Number, Boolean,
 *            String, WebElement, List or any combination thereof
 * @return Number, Boolean, String, WebElement, List or any combination
 *         thereof Null if argument list invalid
 */
interface IJSExecutor {

    <T> T executeScript(WebDriver webdriver, String script);

    <T> T executeScript(WebDriver webdriver, String script, Object... args);

    <T> T executeAsyncScript(WebDriver webdriver, String script);

    <T> T executeAsyncScript(WebDriver webdriver, String script, Object... args);
}