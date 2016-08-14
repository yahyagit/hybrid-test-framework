package com.atanas.kanchev.testframework.commons.context;

import com.atanas.kanchev.testframework.commons.exceptions.CustomExceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Abstract Context
 *
 * @param <T> Context type
 * @author Atanas Kanchev
 * @version 1.0
 */
public abstract class AbstractContext<T> implements IAbstractContext<T> {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(AbstractContext.class);

    // the context name
    private String contextName;

    private boolean isContextReusable;

    // the driver object
    private T driver;

    /**
     * Constructor
     * Sets the value of {@link com.atanas.kanchev.testframework.commons.context.AbstractContext#contextName}
     *
     * @param contextName String
     */
    public AbstractContext(String contextName) {
        this.contextName = contextName + new BigInteger(130, new SecureRandom()).toString(32);
        logger.debug("Setting current com.atanas.kanchev.testframework.selenium.context name to "
            + this.contextName);
    }

    /**
     * {@inheritDoc}
     */
    @Override public T getDriver() {
        if (this.driver == null)
            throw new CustomExceptions.Common.NullReferenceException(
                "Null driver object AbstractContext#driver");
        else
            return this.driver;
    }

    /**
     * {@inheritDoc}
     */
    @Override public void setDriver(T driver) {
        if (driver != null)
            this.driver = driver;
        else
            throw new CustomExceptions.Common.NullArgumentException(
                "Null driver instance passed as method argument");
    }

    /**
     * {@inheritDoc}
     */
    @Override public String getContextName() {
        return contextName;
    }

    /**
     * {@inheritDoc}
     */
    @Override public void setContextName(String contextName) {
        if (contextName == null)
            throw new CustomExceptions.Common.NullArgumentException("Null argument contextName");
        if (contextName.isEmpty())
            throw new CustomExceptions.Common.EmptyArgumentException("Empty argument contextName");
        this.contextName = contextName;
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean isContextReusable() {
        return isContextReusable;
    }

    /**
     * {@inheritDoc}
     */
    @Override public void setContextReusable(boolean contextReusable) {
        isContextReusable = contextReusable;
    }

}

/**
 * Abstract Context Interface
 *
 * @param <T>
 */
interface IAbstractContext<T> {

    /**
     * Get the driver reference
     */
    T getDriver();

    /**
     * Set the {@link com.atanas.kanchev.testframework.commons.context.AbstractContext#driver}
     */
    void setDriver(T driver);

    /**
     * Get {@link com.atanas.kanchev.testframework.commons.context.AbstractContext#contextName}
     *
     * @return contextName a {@link java.lang.String} object.
     */
    String getContextName();

    /**
     * Set the value of {@link com.atanas.kanchev.testframework.commons.context.AbstractContext#contextName}
     *
     * @param contextName a {@link java.lang.String} object.
     */
    void setContextName(String contextName);

    /**
     * <p>isContextReusable.</p>
     *
     * @return a boolean.
     */
    boolean isContextReusable();

    /**
     * @param contextReusable a {co}
     */
    void setContextReusable(boolean contextReusable);

    /**
     * Tear down all active contexts stored in {@link ContextFactory#contextMap}
     *
     * @param context
     * @param <U>
     */
    <U extends AbstractContext> void tearDownContext(U context);

}
