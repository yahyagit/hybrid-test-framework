package com.atanas.kanchev.testframework.core.context;

/**
 * @author Atanas Ksnchev
 */
public interface IContexts {

    <T extends AbstractContext> IContexts addContext(T context);

    <T extends AbstractContext> IContexts removeContext(T context);

    <T extends AbstractContext> T switchContext(String contextName);

    <T extends AbstractContext> IContexts setCurrentContext(T context);

    <T extends AbstractContext> T getCurrentContext();

    <T extends AbstractContext> IContexts tearDownContext(T context);

    IContexts tearDownContexs();

}
