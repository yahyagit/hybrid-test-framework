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
public final class ConcurrentContextContainer implements IContextContainer {

    private static final Logger logger = LoggerFactory.getLogger(ConcurrentContextContainer.class);
    private static final Map<ContextKey<?>, Object> typesafeHeterogeneousContextContainer =
        new ConcurrentHashMap<>(5);

    public ConcurrentContextContainer() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override public void run() {
                tearDownContexts();
            }
        });
    }

    @Override
    public <T extends AbstractContext> ContextKey<T> addContext(ContextKey<T> key, T context) {

        if (key == null || context == null)
            throw new CustomExceptions.Common.NullArgumentException("Null method argument");
        logger.debug("Adding " + context.toString());
        typesafeHeterogeneousContextContainer.put(key, context);

        return key;
    }

    @Override public <T extends AbstractContext> T getContext(ContextKey<T> key) {

        if (key == null)
            throw new CustomExceptions.Common.NullArgumentException("Null method argument");

        return key.contextType.cast(typesafeHeterogeneousContextContainer.get(key));
    }

    @Override
    public <T extends AbstractContext> IContextContainer removeContext(ContextKey<T> key) {

        if (key == null)
            throw new CustomExceptions.Common.NullArgumentException("Null method argument");

        if (typesafeHeterogeneousContextContainer.containsKey(key)) {
            typesafeHeterogeneousContextContainer.remove(key);
            logger.debug("Removing " + key.toString() + " from map");
        } else {
            throw new RuntimeException("The map doesn't contain a key " + key.toString());
        }

        return this;
    }

    @Override public <T extends AbstractContext> T switchContext(ContextKey<T> key) {

        if (key == null)
            throw new CustomExceptions.Common.NullArgumentException("Null method argument");

        return getContext(key);

    }

    @Override
    public <T extends AbstractContext> IContextContainer tearDownContext(ContextKey<T> key) {

        getContext(key).tearDownContext();
        removeContext(key);

        return this;
    }

    @Override public IContextContainer tearDownContexts() {

        logger.debug("Tearing down " + typesafeHeterogeneousContextContainer.size() + " contexts");
        for (ContextKey key : typesafeHeterogeneousContextContainer.keySet()) {
            tearDownContext(key);
        }

        return this;
    }

}
