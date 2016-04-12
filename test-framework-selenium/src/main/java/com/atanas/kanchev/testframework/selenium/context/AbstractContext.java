package com.atanas.kanchev.testframework.selenium.context;

import com.atanas.kanchev.testframework.core.exceptions.CustomExceptions;

/**
 * Abstract Context
 *
 * @author Atanas Kanchev
 */
public abstract class AbstractContext<T> extends ContextFactory {

    private final String contextName;

    private T driver;

    /**
     * Constructor
     * Sets the value of {@link AbstractContext#contextName}
     *
     * @param contextName String
     */
    public AbstractContext(String contextName) {
        this.contextName = contextName;
    }

    /**
     * Constructor
     * Set default value for {@link AbstractContext#contextName}
     */
    public AbstractContext() {
        this.contextName = "context";
    }

    /**
     * Get driver reference
     *
     * @return reference of {@link AbstractContext#driver}
     */
    public T getDriver() {
        if (this.driver == null)
            throw new RuntimeException("Null driver");
        else
            return this.driver;
    }

    /**
     * Set the {@link AbstractContext#driver}
     *
     * @param driver
     */
    public void setDriver(T driver) {
        if (driver != null)
            this.driver = driver;
        else
            throw new CustomExceptions.Common.NullArgumentException("Null driver instance passed as method argument");
    }

    /**
     * Get context name
     *
     * @return the value of {@link AbstractContext#contextName}
     */
    protected String getContextName() {
        return contextName;
    }

}
