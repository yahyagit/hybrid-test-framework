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
     * Sets the value of {@link AbstractContext#contextName}
     *
     * @param contextName String
     */
    public AbstractContext(String contextName) {
        this.contextName = contextName + new BigInteger(130, new SecureRandom()).toString(32);
        logger.debug("Setting current context name to " + this.contextName);
    }

    @Override
    public T getDriver() {
        if (this.driver == null)
            throw new CustomExceptions.Common.NullReferenceException("Null driver object AbstractContext#driver");
        else
            return this.driver;
    }

    @Override
    public void setDriver(T driver) {
        if (driver != null)
            this.driver = driver;
        else
            throw new CustomExceptions.Common.NullArgumentException("Null driver instance passed as method argument");
    }

    @Override
    public String getContextName() {
        return contextName;
    }

    @Override
    public void setContextName(String contextName) {
        if (contextName == null)
            throw new CustomExceptions.Common.NullArgumentException("Null argument contextName");
        if (contextName.isEmpty())
            throw new CustomExceptions.Common.EmptyArgumentException("Empty argument contextName");
        this.contextName = contextName;
    }

    @Override
    public boolean isContextReusable() {
        return isContextReusable;
    }

    @Override
    public void setContextReusable(boolean contextReusable) {
        isContextReusable = contextReusable;
    }

}