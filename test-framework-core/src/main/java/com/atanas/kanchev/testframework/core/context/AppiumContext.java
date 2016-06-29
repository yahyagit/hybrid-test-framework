package com.atanas.kanchev.testframework.core.context;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Appium Context
 *
 * @param <T>
 * @author Atanas Ksnchev
 */
public final class AppiumContext<T> extends AbstractContext<T> {

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

    @Override
    public void tearDownContext() {
        for (AbstractContext context : ContextFactory.getContextMap().values()) {
            if (context instanceof AppiumContext) {
                if (context.getDriver() instanceof AndroidDriver)
                    ((AppiumContext<AndroidDriver>) context).getDriver().quit();
                if (context.getDriver() instanceof IOSDriver)
                    ((AppiumContext<IOSDriver>) context).getDriver().quit();

            }
            logger.debug("Success tearing down context " + context.getContextName());
        }
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