package com.atanas.kanchev.testframework.selenium.context;

import com.atanas.kanchev.testframework.core.CustomExceptions;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Context Factory
 */
public abstract class ContextFactory {

    // Current context
    private static AbstractContext currentContext;

    // Context map
    private static final Map<String, AbstractContext> contextMap = new LinkedHashMap<>();

    /**
     * Add context to com.atanas.kanchev.testframework.selenium.context.ContextFactory#contextMap
     *
     * @param context instance of AbstractContext
     */
    public void addContext(AbstractContext context) {
        contextMap.put(context.getContextName() + contextMap.size(), context);
        currentContext = context;
    }

    /**
     * Switch context by name
     * Sets the com.atanas.kanchev.testframework.selenium.context.ContextFactory#currentContext
     *
     * @param name String
     * @return instance of the current context
     */
    public static AbstractContext switchContext(String name) {
        AbstractContext context = contextMap.get(name);
        currentContext = context;
        return context;
    }

    /**
     * Get the contents map
     *
     * @return reference of com.atanas.kanchev.testframework.selenium.context.ContextFactory#contextMap
     */
    protected static Map<String, AbstractContext> getContextMap() {
        return contextMap;
    }

    /**
     * Get current context
     *
     * @return reference to com.atanas.kanchev.testframework.selenium.context.ContextFactory#currentContext
     */
    public static AbstractContext getCurrentContext() {

        if (currentContext == null)
            throw new CustomExceptions.Common.NullArgumentException("The current context is null");
        else
            return currentContext;
    }

    /**
     * Tear down all active contexts stored in com.atanas.kanchev.testframework.selenium.context.ContextFactory#contextMap
     */
    public static void tearDownContext() {

        for (AbstractContext context : contextMap.values()) {
            if (context instanceof WebContext) {
                ((WebContext) context).getDriver().quit();
            }
        }
        contextMap.clear();
        currentContext = null;

    }
}
