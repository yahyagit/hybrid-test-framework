package com.atanas.kanchev.testframework.selenium.context;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by atanas on 24/03/2016.
 */
public final class WebContext extends AbstractContext {

    // Driver object
    private WebDriver driver;

    // Current WebElement pointer
    private WebElement currentElement;

    // Current WebElement pointer
    private List<WebElement> webElementsList;

    //////////////
    // Getters //
    /////////////

    public WebDriver getDriver() {

        if (driver == null) throw new RuntimeException("Null driver");
        else return driver;
    }

    public WebElement getCurrentElement() {
        return currentElement;
    }

    public List<WebElement> getWebElementsList() {
        return webElementsList;
    }

    //////////////
    // Setters //
    /////////////

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void setCurrentElement(WebElement currentElement) {
        this.currentElement = currentElement;
    }

    public void setWebElementsList(List<WebElement> webElementsList) {
        this.webElementsList = webElementsList;
    }
}
