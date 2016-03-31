package com.atanas.kanchev.testframework.selenium.context;

import com.atanas.kanchev.testframework.core.CustomExceptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Web Context
 */
public final class WebContext extends AbstractContext<WebDriver> {

    // Current WebElement pointer
    private WebElement currentElement;

    // Current WebElement pointer
    private List<WebElement> webElementsList;

    //////////////
    // Getters //
    /////////////

    /**
     * Get WebDriver instance
     *
     * @return refrence to com.atanas.kanchev.testframework.selenium.context.AbstractContext#driver
     */
    public WebDriver getDriver() {

        if (super.driver == null) throw new RuntimeException("Null driver");
        else return super.driver;
    }

    /**
     * Get current WebElement
     *
     * @return reference to com.atanas.kanchev.testframework.selenium.context.WebContext#currentElement
     */
    public WebElement getCurrentElement() {
        return currentElement;
    }

    /**
     * Get current WebElement list
     *
     * @return reference to com.atanas.kanchev.testframework.selenium.context.WebContext#webElementsList
     */
    public List<WebElement> getWebElementsList() {
        return webElementsList;
    }

    //////////////
    // Setters //
    /////////////

    /**
     * Set the com.atanas.kanchev.testframework.selenium.context.AbstractContext#driver
     *
     * @param driver instance of WebDriver
     */
    public void setDriver(WebDriver driver) {
        if (driver != null)
            super.driver = driver;
        else
            throw new CustomExceptions.Common.NullArgumentException("Null driver instance passed as method argument");
    }

    /**
     * Set the current WebElement com.atanas.kanchev.testframework.selenium.context.WebContext#currentElement
     *
     * @param currentElement instance of WebElement
     */
    public void setCurrentElement(WebElement currentElement) {
        if (currentElement != null)
            this.currentElement = currentElement;
        else
            throw new CustomExceptions.Common.NullArgumentException("Null WebElement instance passed as method argument");

    }

    /**
     * Set the current WebElement List com.atanas.kanchev.testframework.selenium.context.WebContext#webElementsList
     *
     * @param webElementsList instance of List<WebElement>
     */
    public void setWebElementsList(List<WebElement> webElementsList) {
        if (webElementsList != null)
            this.webElementsList = webElementsList;
        else
            throw new CustomExceptions.Common.NullArgumentException("Null WebElement instance passed as method argument");

    }

    @Override
    public String toString() {
        return getClass().getName();
    }
}
