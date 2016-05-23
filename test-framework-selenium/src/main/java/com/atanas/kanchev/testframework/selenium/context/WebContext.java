package com.atanas.kanchev.testframework.selenium.context;

import com.atanas.kanchev.testframework.core.context.AbstractContext;
import com.atanas.kanchev.testframework.core.context.ContextFactory;
import com.atanas.kanchev.testframework.core.exceptions.CustomExceptions;
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

    public WebContext() {
        super("webContext_");
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
     * Tear down all active contexts stored in {@link ContextFactory#contextMap}
     */
    @Override
    public void tearDownContext() {



        for (AbstractContext context : ContextFactory.getContextMap().values()) {
            if (context instanceof WebContext) {
                ((WebContext) context).getDriver().quit();
            }
            logger.debug("Teared down context " + context.getContextName());
            ContextFactory.getContextMap().remove(context);
            ContextFactory.setCurrentContext(null);
        }

    }


    /**
     * Tear down a context stored in {@link ContextFactory#contextMap}
     *
     * @param context AbstractContext instance
     */
    public static void tearDownContext(final AbstractContext context) {

        AbstractContext ct = null;

        if (context instanceof WebContext) {
            ct = context.getContext();
            ((WebContext) context).getDriver().quit();

        }
        ContextFactory.getContextMap().remove(ct);

        if (ContextFactory.getCurrentContext() == context)
            ContextFactory.setCurrentContext(null);

    }

    @Override
    public String toString() {
        return getClass().getName();
    }
}