package com.atanas.kanchev.testframework.selenium.handlers;

import com.atanas.kanchev.testframework.core.exceptions.CustomExceptions;
import com.atanas.kanchev.testframework.selenium.context.AbstractContext;
import com.atanas.kanchev.testframework.selenium.context.WebContext;
import com.atanas.kanchev.testframework.selenium.driverfactory.BrowserConfig;
import com.atanas.kanchev.testframework.selenium.driverfactory.DriverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

import static com.atanas.kanchev.testframework.selenium.context.ContextFactory.getCurrentContext;

/**
 * Navigator Interface
 */
public interface INavigator extends IWrapper {

    // the logger
    Logger logger = LoggerFactory.getLogger(INavigator.class);

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
    default INavigator page(final URL url) {

        if (url == null)
            throw new CustomExceptions.Common.NullArgumentException("Null method argument: URL");
        else {

            try {
                getCurrentContext();
            } catch (CustomExceptions.Common.NullArgumentException e) {
                DriverFactory driverFactory = new DriverFactory();
                driverFactory.setSelectedBrowser(BrowserConfig.CHROME);
                AbstractContext context = new WebContext();
                ((WebContext) context).setDriver(driverFactory.getDriver());
                context.addContext(context);

            }

            ((WebContext) getCurrentContext()).getDriver().navigate().to(url);
        }

        return this;
    }

    /**
     * Overloaded version of {@link INavigator#page(java.net.URL))} that makes it easy to pass in a String URL.
     *
     * @param url The URL to load. It is best to use a fully qualified URL
     * @return this
     */
    default INavigator page(final String url) {

        URL address = null;
        try {
            address = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        page(address);

        return this;
    }

    /**
     * Move back a single "item" in the browser's history.
     *
     * @return this
     */
    default INavigator back() {
        ((WebContext) getCurrentContext()).getDriver().navigate().back();
        return this;
    }

    /**
     * Move a single "item" forward in the browser's history. Does nothing if we are on the latest
     * page viewed.
     *
     * @return this
     */
    default INavigator forward() {
        ((WebContext) getCurrentContext()).getDriver().navigate().forward();
        return this;
    }

    /**
     * Refresh the current page
     *
     * @return this
     */
    default INavigator refresh() {
        ((WebContext) getCurrentContext()).getDriver().navigate().refresh();
        return this;
    }
}
