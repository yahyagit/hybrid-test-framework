package com.atanas.kanchev.testframework.selenium.context;

import com.atanas.kanchev.testframework.core.exceptions.CustomExceptions;
import com.atanas.kanchev.testframework.selenium.driverfactory.IRootDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Web Context
 */
public final class WebContext extends AbstractContext<WebDriver> {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(WebContext.class);

    // Current WebElement pointer
    private WebElement currentElement;

    // Current WebElement pointer
    private List<WebElement> webElementsList;

    // Waits
    private long implicitlyWait;
    private long pageLoadTimeout;

    public WebContext() {
        super("webContext_");
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

        if (this.currentElement == null)
            throw new CustomExceptions.Common.NullReferenceException("Null WebContext#currentElement");
        else
            return currentElement;
    }

    /**
     * Get current WebElement list
     *
     * @return reference to {@link  WebContext#webElementsList}
     */
    public List<WebElement> getWebElementsList() {

        if (this.webElementsList == null)
            throw new CustomExceptions.Common.NullReferenceException("Null WebContext#webElementsList");
        else
            return webElementsList;
    }

    /**
     * Get Implicitly Wait
     *
     * @return value of {@link WebContext#implicitlyWait}
     */
    public long getImplicitlyWait() {
        return implicitlyWait;
    }

    /**
     * Get Page Timeout
     *
     * @return value of {@link WebContext#pageLoadTimeout}
     */
    public long getPageLoadTimeout() {
        return pageLoadTimeout;
    }

    //////////////
    // Setters //
    /////////////

    /**
     * Set the current WebElement {@link WebContext#currentElement}
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
     * Set the current WebElement List {@link WebContext#webElementsList}
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
     * Set Implicitly Wait {@link WebContext#implicitlyWait}
     *
     * @param implicitlyWait long
     */
    public void setImplicitlyWait(long implicitlyWait) {
        this.implicitlyWait = implicitlyWait;
    }

    /**
     * Set Page Load Timeout {@link WebContext#pageLoadTimeout}
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