package com.atanas.kanchev.testframework.selenium.handlers;

import com.atanas.kanchev.testframework.selenium.context.ContextFactory;
import com.atanas.kanchev.testframework.selenium.context.WebContext;

import static com.atanas.kanchev.testframework.selenium.context.ContextFactory.getCurrentContext;

/**
 * Context Interface
 *
 * @author Atanas Ksnchev
 */
public interface IContext {

    default void tearDownContexts() {
        ContextFactory.tearDownContext();
    }

    default WebContext getWebContext() {
        return ((WebContext) getCurrentContext());
    }
}
