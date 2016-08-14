package com.atanas.kanchev.testframework.commons.wrappers;

import com.atanas.kanchev.testframework.commons.context.ContextFactory;

/**
 * The interface Context.
 */
public interface IContext {
    /**
     * The constant CONTEXT_FACTORY.
     */
    ContextFactory CONTEXT_FACTORY = new ContextFactory();

    /**
     * Context factory.
     *
     * @return the context factory
     */
    default ContextFactory context() {
        return CONTEXT_FACTORY;
    }
}

