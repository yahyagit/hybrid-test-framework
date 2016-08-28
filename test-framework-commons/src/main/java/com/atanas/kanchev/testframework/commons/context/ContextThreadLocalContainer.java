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

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import static java.lang.Thread.currentThread;

/**
 * @author Atanas Kanchev
 */
public class ContextThreadLocalContainer implements ContextContainer {

    private static final Logger logger = LoggerFactory.getLogger(ContextThreadLocalContainer.class);

    protected static final Collection<Thread> ALL_CONTEXT_CONTAINERS_THREADS =
        new ConcurrentLinkedQueue<>();

    public static final Map<String, AbstractContext> THREAD_CONTEXT = new ConcurrentHashMap<>(4);

    private String currentContextName;

    @Override public <T extends AbstractContext> ContextThreadLocalContainer addContext(T context) {

        if (context == null)
            throw new CustomExceptions.Common.NullArgumentException("Null method argument context");

        ALL_CONTEXT_CONTAINERS_THREADS.add(currentThread());

        if (THREAD_CONTEXT.containsKey(context.getContextName())) {
            context.setContextName(context.getContextName() + THREAD_CONTEXT.size());
            logger.warn("Duplicated key, renaming to " + context.getContextName());
        }

        logger.debug("Adding " + context.toString());
        this.currentContextName = context.getContextName();
        THREAD_CONTEXT.put(context.getContextName(), context);

        Runtime.getRuntime().addShutdownHook(new ContextShutdownHook());

        return this;
    }

    @Override public <T extends AbstractContext> ContextContainer removeContext(T context) {
        if (context == null)
            throw new CustomExceptions.Common.NullArgumentException("Null method argument context");
        ALL_CONTEXT_CONTAINERS_THREADS.remove(currentThread());
        if (THREAD_CONTEXT.containsValue(context)) {
            THREAD_CONTEXT.remove(context);
            logger.debug("Removing context " + context.toString()
                + " from ContextThreadLocalContainer.THREAD_CONTEXT");
        } else {
            throw new RuntimeException("Error removing context " + context.toString()
                + " from ContextThreadLocalContainer.THREAD_CONTEXT");
        }
        return this;
    }

    @Override public <T extends AbstractContext> T getCurrentContext() {
        if (currentContextName == null) {
            logger.error("The current context is null");
            throw new CustomExceptions.Common.NullReferenceException("The current context is null");
        } else
            return (T) THREAD_CONTEXT.get(currentContextName);
    }

    @Override public <T extends AbstractContext> ContextContainer tearDownContext(T context) {
        logger.debug("Tearing down context " + context.toString());

        context.tearDownContext(context);
        removeContext(context);
        if (currentContextName.equals(context.getContextName()))
            currentContextName = null;

        return this;
    }

    @Override public ContextContainer tearDownContexts() {
        logger.debug("Tearing down " + THREAD_CONTEXT.size() + " contexts");

        for (Map.Entry<String, AbstractContext> context : THREAD_CONTEXT.entrySet()) {
            logger.debug("Tearing down context of type " + context.toString());
            context.getValue().tearDownContext(context.getValue());
            removeContext(context.getValue());

        }
        return this;
    }

//    protected void closeUnusedWebdrivers() {
//        for (Thread thread : ALL_CONTEXT_CONTAINERS_THREADS) {
//            if (thread.isAlive()) {
//                logger.info("Thread " + thread.getId() + " is dead. Let's close webdriver ");
//                tearDownContexts();
//            }
//        }
//    }

    private final class ContextShutdownHook extends Thread {

        @Override public void run() {
            tearDownContexts();
        }
    }

}
