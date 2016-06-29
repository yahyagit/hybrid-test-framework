package com.atanas.kanchev.testframework.core.handlers;

import com.atanas.kanchev.testframework.commons.exceptions.CustomExceptions;
import com.atanas.kanchev.testframework.core.context.AppiumContext;
import com.atanas.kanchev.testframework.core.context.ContextFactory;
import com.atanas.kanchev.testframework.core.context.WebContext;
import com.atanas.kanchev.testframework.selenium.driver_factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

import static com.atanas.kanchev.testframework.core.context.ContextFactory.getCurrentContext;

/**
 * Nav Interface
 */
public final class Navigate implements IWrapper {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(Navigate.class);

    DriverFactory driverFactory;

    public Navigate(DriverFactory driverFactory) {
        this.driverFactory = driverFactory;
    }

    /**
     * Load a new web page in the current browser window. This is done using an HTTP GET operation,
     * and the method will block until the load is complete. This will follow redirects issued
     * either by the server or as a meta-redirect from within the returned HTML. Should a
     * meta-redirect "rest" for any duration of time, it is best to wait until this timeout is over,
     * since should the underlying page change whilst your test is executing the results of future
     * calls against this interface will be against the freshly loaded page.
     *
     * @param url valid URL instance
     * @return this
     */
    public Navigate getPage(final String url) {

        if (url == null)
            throw new CustomExceptions.Common.NullArgumentException("Null method argument: URL");
        else {
            URL address = null;
            try {
                address = new URL(url);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            try {
                getCurrentContext();
            } catch (CustomExceptions.Common.NullArgumentException e) {
                WebContext<WebDriver> context = new WebContext<>(driverFactory.getDriver());
                if (driverFactory.isReuseBrowser())
                    context.setContextReusable(true);
                ContextFactory.addContext(context);
            }

            logger.debug("Navigating to " + url);

            if (getCurrentContext() instanceof AppiumContext)
                INavigateAppium.getPage(address);
            if (getCurrentContext() instanceof WebContext)
                INavigateSelenium.getPage(address);
        }
        return this;

    }

    /**
     * Move back a single "item" in the browser's history.
     *
     * @return this
     */
    public Navigate back() {
        logger.debug("Navigating back");
        if (getCurrentContext() instanceof AppiumContext) INavigateAppium.back();
        if (getCurrentContext() instanceof WebContext) INavigateSelenium.back();
        return this;
    }

    /**
     * Move a single "item" forward in the browser's history. Does nothing if we are on the latest
     * page viewed.
     *
     * @return this
     */
    public Navigate forward() {
        logger.debug("Navigating forward");
        if (getCurrentContext() instanceof AppiumContext) INavigateAppium.forward();
        if (getCurrentContext() instanceof WebContext) INavigateSelenium.forward();
        return this;
    }

    /**
     * Refresh the current page
     *
     * @return this
     */
    public Navigate refresh() {
        logger.debug("Refreshing page");
        if (getCurrentContext() instanceof AppiumContext) INavigateAppium.refresh();
        if (getCurrentContext() instanceof WebContext) INavigateSelenium.refresh();
        return this;
    }

}