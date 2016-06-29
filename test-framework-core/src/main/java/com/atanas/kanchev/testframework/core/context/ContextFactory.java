package com.atanas.kanchev.testframework.core.context;

import com.atanas.kanchev.testframework.commons.exceptions.CustomExceptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
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
     * @param context AbstractContext
     */
    public static void addContext(AbstractContext context) {

        if (context == null) throw new CustomExceptions.Common.NullArgumentException("Null method argument context");

        if (contextMap.containsKey(context.getContextName())) {
            context.setContextName(context.getContextName() + contextMap.size());
            logger.warn("Duplicated key in ContextFactory#contextMap, renaming to " + context.getContextName());
        }

        logger.warn("Adding context " + context.getContextName() + " to the map");
        contextMap.put(context.getContextName(), context);
        currentContext = context;
    }

    /**
     * Remove context instance from {@link ContextFactory#contextMap}
     *
     * @param context AbstractContext
     */
    public static void removeContext(AbstractContext context) {

        if (context == null) throw new CustomExceptions.Common.NullArgumentException("Null method argument context");

        if (contextMap.containsKey(context.getContextName())) {
            contextMap.remove(context);
            logger.debug("Removing context " + context.getContextName() + " from ContextFactory#contextMap");
            if (currentContext == context)
                currentContext = null;
        } else {
            logger.error("Error removing context " + context.getContextName() + " from ContextFactory#contextMap");
            throw new RuntimeException("Error removing context " + context.getContextName() + " from ContextFactory#contextMap");
        }

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
     * @param context AbstractContext
     */
    public static void setCurrentContext(AbstractContext context) {
        logger.debug("Setting current context " + context.toString());
        currentContext = context;
    }

    public static void tearDownContexts() {

        logger.debug("Tearing down contexts " + getContextMap().values().size());

        for (AbstractContext context : getContextMap().values()) {
            context.tearDownContexts();
        }

    }

    public static AbstractContext<?> getCtx(){

        AbstractContext<?> context = getCurrentContext();

        if (context instanceof WebContext) {
            if (context.getDriver() instanceof WebDriver)
                return context;
            if (context.getDriver() instanceof RemoteWebDriver)
                return context;
        }


        return context;
    }

}