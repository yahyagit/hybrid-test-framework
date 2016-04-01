package com.atanas.kanchev.testframework.selenium.handlers;

import com.atanas.kanchev.testframework.selenium.context.ContextFactory;
import com.atanas.kanchev.testframework.selenium.context.WebContext;

import static com.atanas.kanchev.testframework.selenium.context.ContextFactory.getCurrentContext;

/**
 * Created by atanas on 24/03/2016.
 */
interface IBaseHandler {

    default void tearDownContexts() {
        ContextFactory.tearDownContext();
    }

    default WebContext getWebContext() {
        return ((WebContext) getCurrentContext());
    }

    default void sleep(long mils) {
        try {
            Thread.sleep(mils);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    default IBaseHandler setImplicitlyWait(long wait) {
        getWebContext().setImplicitlyWait(wait);
        return this;
    }

    default IBaseHandler setPageLoadTimeout(long timeout) {
        getWebContext().setPageLoadTimeout(timeout);
        return this;
    }

}
