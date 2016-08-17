/*
 * Copyright 2016 Atanas Stoychev Kanchev
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.atanas.kanchev.testframework.commons.context;

import com.atanas.kanchev.testframework.commons.exceptions.CustomExceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * <p>Abstract AbstractContext class.</p>
 *
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
     * <p>Constructor for AbstractContext.</p>
     *
     * @param contextName a {@link java.lang.String} object.
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

interface IAbstractContext<T> {

    /**
     * <p>getDriver.</p>
     *
     * @return a T object.
     */
    T getDriver();

    /**
     * <p>setDriver.</p>
     *
     * @param driver a T object.
     */
    void setDriver(T driver);

    /**
     * <p>getContextName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getContextName();

    /**
     * <p>setContextName.</p>
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
     * <p>setContextReusable.</p>
     *
     * @param contextReusable a boolean.
     */
    void setContextReusable(boolean contextReusable);

    /**
     * <p>tearDownContext.</p>
     *
     * @param context a U object.
     * @param <U>     a U object.
     */
    <U extends AbstractContext> void tearDownContext(U context);

}
