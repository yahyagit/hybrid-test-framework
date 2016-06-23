package com.atanas.kanchev.testframework.core.context;

import com.atanas.kanchev.testframework.core.exceptions.CustomExceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Context Factory
 */
public class ContextFactory {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(ContextFactory.class);

    // Current context
    private static AbstractContext currentContext;

    // Context map
    private static final Map<String, AbstractContext> contextMap = new ConcurrentHashMap<>();

    /**
     * Add context to {@link ContextFactory#contextMap}
     *
     * @param context instance of AbstractContext
     */
    public static void addContext(AbstractContext context) {
        if (context == null) throw new CustomExceptions.Common.NullArgumentException("Null method argument context");

        if (contextMap.containsKey(context.getContextName())) {
            context.setContextName(context.getContextName() + contextMap.size());
            logger.warn("Duplicated key in ContextFactory#contextMap, renaming to " + context.getContextName());
        }

        contextMap.put(context.getContextName(), context);
        currentContext = context;
    }

    /**
     * Switch context by name
     * Sets the {@link ContextFactory#currentContext}
     *
     * @param name String
     * @return instance of the current context
     */
    public static AbstractContext switchContext(String name) {
        if (name == null) throw new CustomExceptions.Common.NullArgumentException("Null method argument context");
        if (name.isEmpty()) throw new CustomExceptions.Common.EmptyArgumentException("Empty method argument context");

        if (!contextMap.containsKey(name))
            throw new CustomExceptions.Common.IllegalArgumentException("The map ContextFactory#contextMap doesn't contain a key with value " + name);
        AbstractContext context = contextMap.get(name);
        currentContext = context;
        return context;
    }

    /**
     * Get the contents map
     *
     * @return reference of {@link ContextFactory#contextMap}
     */
    public static Map<String, AbstractContext> getContextMap() {
        return contextMap;
    }

    /**
     * Get current context
     *
     * @return reference to {@link ContextFactory#currentContext}
     */
    public static AbstractContext getCurrentContext() {

        if (currentContext == null)
            throw new CustomExceptions.Common.NullArgumentException("The current context is null");
        else
            return currentContext;
    }

    /**
     * Set the current context
     *
     * @param currentContext AbstractContext
     */
    public static void setCurrentContext(AbstractContext currentContext) {
        ContextFactory.currentContext = currentContext;
    }

    public static void tearDownContexts() {

        logger.debug("Tearing down contexts " + getContextMap().values().size());

        for (AbstractContext context : getContextMap().values()) {
            logger.debug("Tearing down context type " + context.toString());
            context.tearDownContext();
            logger.debug("Removing context from map" + context.getContextName());
            getContextMap().remove(context);
            setCurrentContext(null);

        }


    }


}