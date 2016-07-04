package com.atanas.kanchev.testframework.core.handlers.interfaces;

import com.atanas.kanchev.testframework.core.context.ContextFactory;

/**
 * Created by atanas on 02/07/2016.
 */
public interface IContext {

    ContextFactory CONTEXT_FACTORY = new ContextFactory();

    default ContextFactory context() {
        return CONTEXT_FACTORY;
    }
}
