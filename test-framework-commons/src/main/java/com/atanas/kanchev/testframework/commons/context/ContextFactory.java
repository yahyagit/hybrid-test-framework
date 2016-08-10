package com.atanas.kanchev.testframework.commons.context;

import com.atanas.kanchev.testframework.commons.exceptions.CustomExceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Context Factory
 */
public final class ContextFactory implements IContextFactory {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(ContextFactory.class);

    // Current context
    private AbstractContext currentContext;

    // Context map
    private static final Map<String, AbstractContext> contextMap = new ConcurrentHashMap<>();

    @Override
    public <T extends AbstractContext> IContextFactory addContext(T context) {

        if (context == null) throw new CustomExceptions.Common.NullArgumentException("Null method argument context");

        if (contextMap.containsKey(context.getContextName())) {
            context.setContextName(context.getContextName() + contextMap.size());
            logger.warn("Duplicated key in ContextFactory#contextMap, renaming to " + context.getContextName());
        }

        logger.debug("Adding " + context.getContextName() + " to the map");
        contextMap.put(context.getContextName(), context);
        currentContext = context;

        return this;

    }

    @Override
    public <T extends AbstractContext> IContextFactory removeContext(T context) {

        if (context == null) throw new CustomExceptions.Common.NullArgumentException("Null method argument context");

        if (contextMap.containsKey(context.getContextName())) {
            contextMap.remove(context.getContextName());
            logger.debug("Removing context " + context.getContextName() + " from ContextFactory#contextMap");
            if (currentContext == context)
                currentContext = null;
        } else {
            logger.error("Error removing context " + context.getContextName() + " from ContextFactory#contextMap");
            throw new RuntimeException("Error removing context " + context.getContextName() + " from ContextFactory#contextMap");
        }

        return this;
    }

    @Override
    public <T extends AbstractContext> T switchContext(String contextName) {

        if (contextName == null)
            throw new CustomExceptions.Common.NullArgumentException("Null method argument context");
        if (contextName.isEmpty())
            throw new CustomExceptions.Common.EmptyArgumentException("Empty method argument context");

        if (!contextMap.containsKey(contextName))
            throw new CustomExceptions.Common.IllegalArgumentException("The map ContextFactory#contextMap doesn't contain a key with value " + contextName);
        currentContext = contextMap.get(contextName);

        return (T) currentContext;
    }

    @Override
    public <T extends AbstractContext> IContextFactory setCurrentContext(T context) {

        logger.debug("Setting current context to " + (context == null ? "null" : context.getContextName()));
        currentContext = context;

        return this;
    }

    @Override
    public <T extends AbstractContext> T getCurrentContext() {

        if (currentContext == null)
            throw new CustomExceptions.Common.NullArgumentException("The current context is null");
        else
            return (T) currentContext;

    }

    @Override
    public <T extends AbstractContext> IContextFactory tearDownContext(T context) {

        logger.debug("Tearing down contexts " + contextMap.values().size());

        context.tearDownContext(context);
        removeContext(context);

        return this;
    }

    @Override
    public IContextFactory tearDownContexts() {

        logger.debug("Tearing down " + getContextMap().size() + " contexts");

        for (AbstractContext context : getContextMap().values()) {
            logger.debug("Tearing down context type " + context.toString());
            context.tearDownContext(context);
            logger.debug("Removing context from map " + context.getContextName());
            getContextMap().remove(context.getContextName());
            setCurrentContext(null);

        }
        return this;
    }

    /**
     * Get the contents map
     *
     * @return reference of {@link ContextFactory#contextMap}
     */
    public static Map<String, AbstractContext> getContextMap() {
        return contextMap;
    }

}

/**
 * @author Atanas Ksnchev
 */
interface IContextFactory {

    <T extends AbstractContext> IContextFactory addContext(T context);

    <T extends AbstractContext> IContextFactory removeContext(T context);

    <T extends AbstractContext> T switchContext(String contextName);

    <T extends AbstractContext> IContextFactory setCurrentContext(T context);

    <T extends AbstractContext> T getCurrentContext();

    <T extends AbstractContext> IContextFactory tearDownContext(T context);

    IContextFactory tearDownContexts();

}