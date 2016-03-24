package com.atanas.kanchev.testframework.selenium.context;

import com.atanas.kanchev.testframework.core.CustomExceptions;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by atanas on 24/03/2016.
 */
public class ContextFactory {

    private static AbstractContext currentContext;

    private static Map<String, AbstractContext> contextMap = new LinkedHashMap<>();

    public static void addContext(AbstractContext context) {
        contextMap.put("context" + contextMap.size(), context);
        currentContext = context;
    }

    public static AbstractContext switchContext(String name) {
        AbstractContext context = contextMap.get(name);
        currentContext = context;
        return context;
    }

    static Map<String, AbstractContext> getContextMap() {
        return contextMap;
    }

    public static AbstractContext getCurrentContext() throws CustomExceptions.Common.NullArgumentException {

        if (currentContext == null)
            throw new CustomExceptions.Common.NullArgumentException("The current context is null");
        else
            return currentContext;
    }

    public static void tearDownContext() {

        for (AbstractContext context : contextMap.values()) {
            if (context instanceof WebContext) {
                ((WebContext) context).getDriver().quit();
            }
        }
        contextMap.clear();

    }
}
