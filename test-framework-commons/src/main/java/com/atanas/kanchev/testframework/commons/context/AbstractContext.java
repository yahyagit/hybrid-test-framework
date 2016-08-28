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

import static java.lang.Thread.currentThread;

/**
 * <p>AbstractContext class.</p>
 *
 * @author Atanas Kanchev
 */
public abstract class AbstractContext<T> {

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
        this.contextName = contextName + currentThread().getId();
        logger.debug("Creating context from type " + toString());
    }

    public T getDriver() {
        if (this.driver == null)
            throw new CustomExceptions.Common.NullReferenceException(
                "Null driver object AbstractContext#driver");
        else
            return this.driver;
    }

    public void setDriver(T driver) {
        if (driver != null)
            this.driver = driver;
        else
            throw new CustomExceptions.Common.NullArgumentException(
                "Null driver instance passed as method argument");
    }

    public String getContextName() {
        return contextName;
    }

    public void setContextName(String contextName) {
        if (contextName == null)
            throw new CustomExceptions.Common.NullArgumentException("Null argument contextName");
        if (contextName.isEmpty())
            throw new CustomExceptions.Common.EmptyArgumentException("Empty argument contextName");
        this.contextName = contextName;
    }

    public boolean isContextReusable() {
        return isContextReusable;
    }

    public void setContextReusable(boolean contextReusable) {
        isContextReusable = contextReusable;
    }

    public abstract void tearDownContext(AbstractContext<T> context);

    @Override public String toString() {
        return "AbstractContext{" + "contextName='" + contextName + '\'' + ", isContextReusable="
            + isContextReusable + ", driver=" + driver + ", contextReusable=" + isContextReusable()
            + '}';
    }
}
