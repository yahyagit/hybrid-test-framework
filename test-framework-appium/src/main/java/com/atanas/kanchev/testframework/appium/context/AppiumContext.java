package com.atanas.kanchev.testframework.appium.context;

import com.atanas.kanchev.testframework.core.context.AbstractContext;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Atanas Ksnchev
 */
public final class AppiumContext extends AbstractContext<AppiumDriver> {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(AppiumContext.class);

    // Current element pointer
    private WebElement currentElement;
    // Global saved element pointer - see saveCurrentElementPosition()
    private WebElement elementStore;
    // Internal web element list - see getListByTag() getListByClass() ...
    private List<WebElement> elementList;

    public AppiumContext() {
        super("appiumContext_");
    }

    //////////////
    // Getters //
    /////////////

    public WebElement getCurrentElement() {
        return currentElement;
    }

    public WebElement getElementStore() {
        return elementStore;
    }

    public List<WebElement> getElementList() {
        return elementList;
    }

    //////////////
    // Setters //
    /////////////


    public void setCurrentElement(WebElement currentElement) {
        this.currentElement = currentElement;
    }

    public void setElementStore(WebElement elementStore) {
        this.elementStore = elementStore;
    }

    public void setElementList(List<WebElement> elementList) {
        this.elementList = elementList;
    }
}
