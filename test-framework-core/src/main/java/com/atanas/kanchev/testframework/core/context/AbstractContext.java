package com.atanas.kanchev.testframework.core.context;

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
 */
public abstract class AbstractContext<T> {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(AbstractContext.class);

    // the context name
    private String contextName;

    boolean isContextReusable;

    // the driver object
    private T driver;

    /**
     * Constructor
     * Sets the value of {@link AbstractContext#contextName}
     *
     * @param contextName String
     */
    public AbstractContext(String contextName) {
        this.contextName = contextName + new BigInteger(130, new SecureRandom()).toString(32);
        logger.debug("Setting current context name to " + this.contextName);
    }

    /**
     * Get driver reference
     *
     * @return reference of {@link AbstractContext#driver}
     */
    public T getDriver() {
        if (this.driver == null)
            throw new CustomExceptions.Common.NullReferenceException("Null driver object AbstractContext#driver");
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
    public String getContextName() {
        return contextName;
    }

    /**
     * Get reference to the current context object
     *
     * @return this
     */
    public AbstractContext getContext() {
        return this;
    }

    /**
     * Set the value of {@link AbstractContext#contextName}
     *
     * @param contextName String
     */
    public void setContextName(String contextName) {
        if (contextName == null)
            throw new CustomExceptions.Common.NullArgumentException("Null argument contextName");
        if (contextName.isEmpty())
            throw new CustomExceptions.Common.EmptyArgumentException("Empty argument contextName");
        this.contextName = contextName;
    }


    public void setContextReusable(boolean contextReusable) {
        isContextReusable = contextReusable;
    }

    public abstract void tearDownContext();

}
