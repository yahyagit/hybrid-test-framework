package com.atanas.kanchev.testframework.selenium.context;

import com.atanas.kanchev.testframework.core.exceptions.CustomExceptions;
import com.atanas.kanchev.testframework.selenium.driverfactory.IRootDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Web Context
 */
public final class WebContext extends AbstractContext<WebDriver> {

    private static final String CONTEXT_NAME = "webContext";

    // Current WebElement pointer
    private WebElement currentElement;

    // Current WebElement pointer
    private List<WebElement> webElementsList;

    // Waits
    private long implicitlyWait;
    private long pageLoadTimeout;

    public WebContext() {
        super(CONTEXT_NAME);
        this.implicitlyWait = IRootDriver.DEFAULT_IMPLICITLY_WAIT;
        this.pageLoadTimeout = IRootDriver.DEFAULT_PAGE_TIMEOUT;
    }


    //////////////
    // Getters //
    /////////////

    /**
     * Get current WebElement
     *
     * @return reference to {@link WebContext#currentElement}
     */
    public WebElement getCurrentElement() {
        if (this.currentElement == null) throw new CustomExceptions.Common.NullReferenceException();
        else return currentElement;
    }

    /**
     * Get current WebElement list
     *
     * @return reference to com.atanas.kanchev.testframework.selenium.context.WebContext#webElementsList
     */
    public List<WebElement> getWebElementsList() {
        return webElementsList;
    }

    /**
     * Get Implicitly Wait
     *
     * @return value of com.atanas.kanchev.testframework.selenium.context.WebContext#implicitlyWait
     */
    public long getImplicitlyWait() {
        return implicitlyWait;
    }

    /**
     * Get Page Timeout
     *
     * @return value of com.atanas.kanchev.testframework.selenium.context.WebContext#pageLoadTimeout
     */
    public long getPageLoadTimeout() {
        return pageLoadTimeout;
    }

    //////////////
    // Setters //
    /////////////

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

    /**
     * Set Implicitly Wait com.atanas.kanchev.testframework.selenium.context.WebContext#implicitlyWait
     *
     * @param implicitlyWait long
     */
    public void setImplicitlyWait(long implicitlyWait) {
        this.implicitlyWait = implicitlyWait;
    }

    /**
     * Set Page Load Timeout com.atanas.kanchev.testframework.selenium.context.WebContext#pageLoadTimeout
     *
     * @param pageLoadTimeout long
     */
    public void setPageLoadTimeout(long pageLoadTimeout) {
        this.pageLoadTimeout = pageLoadTimeout;
    }

    @Override
    public String toString() {
        return getClass().getName();
    }
}
