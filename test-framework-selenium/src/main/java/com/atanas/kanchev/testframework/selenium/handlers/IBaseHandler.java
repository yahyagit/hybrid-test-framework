package com.atanas.kanchev.testframework.selenium.handlers;

import com.atanas.kanchev.testframework.selenium.context.ContextFactory;
import com.atanas.kanchev.testframework.selenium.context.WebContext;

import static com.atanas.kanchev.testframework.core.CustomExceptions.Common.NullArgumentException;
import static com.atanas.kanchev.testframework.selenium.context.ContextFactory.getCurrentContext;

/**
 * Created by atanas on 24/03/2016.
 */
interface IBaseHandler {

    default void tearDownContexts() {
        ContextFactory.tearDownContext();
    }

    static WebContext getCurrentWebContext() throws NullArgumentException {
        return ((WebContext) getCurrentContext());
    }
}
