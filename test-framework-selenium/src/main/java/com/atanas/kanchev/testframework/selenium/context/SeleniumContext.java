package com.atanas.kanchev.testframework.selenium.context;

import com.atanas.kanchev.testframework.commons.context.AbstractContext;
import com.atanas.kanchev.testframework.commons.exceptions.CustomExceptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Web Context
 */
public class SeleniumContext<T> extends AbstractContext<T> {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(SeleniumContext.class);

    // Current WebElement pointer
    private WebElement currentElement;

    // Current WebElement pointer
    private List<WebElement> webElementsList;

    public SeleniumContext(T driver) {
        this(driver, "webContext_");
    }

    public SeleniumContext(T driver, String contextName) {
        super(contextName);
        super.setDriver(driver);
    }

    @Override
    public void tearDownContext(AbstractContext context) {
        if (context instanceof SeleniumContext) {
            if (context.getDriver() instanceof WebDriver)
                ((SeleniumContext<WebDriver>) context).getDriver().quit();
            if (context.getDriver() instanceof RemoteWebDriver)
                ((SeleniumContext<RemoteWebDriver>) context).getDriver().quit();
        }
    }

    //////////////
    // Getters //
    /////////////

    /**
     * Get current WebElement
     *
     * @return reference to {@link SeleniumContext#currentElement}
     */
    public WebElement getCurrentElement() {

        if (this.currentElement == null)
            throw new CustomExceptions.Common.NullReferenceException("Null SeleniumContext#currentElement");
        else
            return currentElement;
    }

    /**
     * Get current WebElement list
     *
     * @return reference to {@link  SeleniumContext#webElementsList}
     */
    public List<WebElement> getWebElementsList() {

        if (this.webElementsList == null)
            throw new CustomExceptions.Common.NullReferenceException("Null SeleniumContext#webElementsList");
        else
            return webElementsList;
    }

    //////////////
    // Setters //
    /////////////

    /**
     * Set the current WebElement {@link SeleniumContext#currentElement}
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
     * Set the current WebElement List {@link SeleniumContext#webElementsList}
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
        return getClass().getSimpleName();
    }

}