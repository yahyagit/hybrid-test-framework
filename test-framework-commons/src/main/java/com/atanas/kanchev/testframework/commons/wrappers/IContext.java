package com.atanas.kanchev.testframework.commons.wrappers;

import com.atanas.kanchev.testframework.commons.context.ContextFactory;

/**
 * Created by atanas on 02/07/2016.
 */
public interface IContext extends IContextFactory {
}

interface IContextFactory {
    ContextFactory CONTEXT_FACTORY = new ContextFactory();

    default ContextFactory context() {
        return CONTEXT_FACTORY;
    }

}
