package com.atanas.kanchev.testframework.selenium.context;

/**
 * Created by atanas on 24/03/2016.
 */
public abstract class AbstractContext<T> extends ContextFactory {

    private String contextName = "context";

    T driver;

    protected abstract T getDriver();

    protected abstract void setDriver(T driver);

    protected String getContextName() {
        return contextName;
    }

    protected void setContextName(String contextName) {
        this.contextName = contextName;
    }
}
