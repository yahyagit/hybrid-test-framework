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
 * @author Atanas Kanchev
 */
public final class ConcurrentContextContainer implements ContextContainer {

    private static final Logger logger = LoggerFactory.getLogger(ConcurrentContextContainer.class);

    public static final Map<String, AbstractContext> CONTEXT_MAP = new ConcurrentHashMap<>(4);
    private String currentContextName;

    public ConcurrentContextContainer() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override public void run() {
                tearDownContexts();
            }
        });
    }

    @Override public <T extends AbstractContext> ContextContainer addContext(T context) {

        if (context == null)
            throw new CustomExceptions.Common.NullArgumentException("Null method argument context");

        if (CONTEXT_MAP.containsKey(context.getContextName())) {
            context.setContextName(context.getContextName() + CONTEXT_MAP.size());
            logger.warn("Duplicated key, renaming to " + context.getContextName());
        }

        logger.debug("Adding " + context.toString());
        this.currentContextName = context.getContextName();
        CONTEXT_MAP.put(context.getContextName(), context);

        return this;
    }

    @Override public <T extends AbstractContext> ContextContainer removeContext(T context) {

        if (context == null)
            throw new CustomExceptions.Common.NullArgumentException("Null method argument context");

        if (CONTEXT_MAP.containsValue(context)) {
            CONTEXT_MAP.remove(context.getContextName());
            logger.debug(
                "Removing " + context.toString() + " from ConcurrentContextContainer.CONTEXT_MAP");
        } else {
            throw new RuntimeException(
                "ConcurrentContextContainer.CONTEXT_MAP doesn't contain a key " + context
                    .toString());
        }

        return this;
    }

    @Override public <T extends AbstractContext> T getCurrentContext() {

        if (currentContextName == null) {
            logger.error("The current context is null");
            throw new CustomExceptions.Common.NullReferenceException("The current context is null");
        } else
            return (T) CONTEXT_MAP.get(currentContextName);
    }

    @Override public <T extends AbstractContext> ContextContainer tearDownContext(T context) {

        logger.debug("Tearing down context " + context.toString());
        context.tearDownContext(context);
        removeContext(context);
        if (currentContextName != null && currentContextName.equals(context.getContextName()))
            currentContextName = null;

        return this;
    }

    @Override public ContextContainer tearDownContexts() {

        logger.debug("Tearing down " + CONTEXT_MAP.size() + " contexts");
        for (Map.Entry<String, AbstractContext> context : CONTEXT_MAP.entrySet()) {
            logger.debug("Tearing down context of type " + context.toString());
            tearDownContext(context.getValue());
        }

        return this;
    }

}
