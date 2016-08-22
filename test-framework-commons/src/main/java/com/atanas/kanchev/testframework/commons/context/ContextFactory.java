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

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>ContextFactory class.</p>
 *
 * @author Atanas Kanchev
 */
public final class ContextFactory<T extends AbstractContext> {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(ContextFactory.class);
    // Context map
    private static final Map<String, AbstractContext> contextMap = new ConcurrentHashMap<>();
    // Current context
    private T currentContext;

    /**
     * <p>Getter for the field <code>contextMap</code>.</p>
     *
     * @return a {@link java.util.Map} object.
     */
    public static Map<String, AbstractContext> getContextMap() {
        return contextMap;
    }

    public ContextFactory<T> addContext(T context) {

        if (context == null)
            throw new CustomExceptions.Common.NullArgumentException("Null method argument context");

        if (contextMap.containsKey(context.getContextName())) {
            context.setContextName(context.getContextName() + contextMap.size());
            logger.warn("Duplicated key in ContextFactory#contextMap, renaming to " + context
                .getContextName());
        }

        logger.debug("Adding " + context.getContextName() + " to the map");
        contextMap.put(context.getContextName(), context);
        currentContext = context;

        return this;

    }

    public ContextFactory<T> removeContext(T context) {

        if (context == null)
            throw new CustomExceptions.Common.NullArgumentException("Null method argument context");

        if (contextMap.containsKey(context.getContextName())) {
            contextMap.remove(context.getContextName());
            logger.debug(
                "Removing context " + context.getContextName() + " from ContextFactory#contextMap");
            if (currentContext == context)
                currentContext = null;
        } else {
            logger.error("Error removing context " + context.getContextName()
                + " from ContextFactory#contextMap");
            throw new RuntimeException("Error removing context " + context.getContextName()
                + " from ContextFactory#contextMap");
        }

        return this;
    }


    public T switchContext(String contextName) {

        if (contextName == null)
            throw new CustomExceptions.Common.NullArgumentException("Null method argument context");
        if (contextName.isEmpty())
            throw new CustomExceptions.Common.EmptyArgumentException(
                "Empty method argument context");

        if (!contextMap.containsKey(contextName))
            throw new CustomExceptions.Common.IllegalArgumentException(
                "The map ContextFactory#contextMap doesn't contain a key with value "
                    + contextName);

        setCurrentContext((T) contextMap.get(contextName));

        return currentContext;
    }

    /**
     * {@inheritDoc}
     */
    public T getCurrentContext() throws CustomExceptions.Common.NullReferenceException {

        if (currentContext == null) {
            logger.warn("The current context is null");
            throw new CustomExceptions.Common.NullReferenceException("The current context is null");
        } else
            return currentContext;

    }

    /**
     * {@inheritDoc}
     */
    public ContextFactory<T> setCurrentContext(T context) {

        logger.debug(
            "Setting current context to " + (context == null ? "null" : context.getContextName()));
        currentContext = context;

        return this;
    }

    /**
     * {@inheritDoc}
     */
    public ContextFactory<T> tearDownContext(T context) {

        logger.debug("Tearing down contexts " + contextMap.values().size());

        context.tearDownContext(context);
        removeContext(context);

        return this;
    }

    /**
     * {@inheritDoc}
     */
    public ContextFactory<T> tearDownContexts() {

        logger.debug("Tearing down " + getContextMap().size() + " contexts");

        for (AbstractContext context : getContextMap().values()) {
            logger.debug("Tearing down context of type " + context.toString());
            context.tearDownContext(context);
            removeContext((T) context);

        }
        return this;
    }

}
