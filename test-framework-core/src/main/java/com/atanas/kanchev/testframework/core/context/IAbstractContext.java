package com.atanas.kanchev.testframework.core.context;

/**
 * @author Atanas Ksnchev
 */
interface IAbstractContext<T> {

    /**
     * Get driver reference
     *
     * @return reference of {@link AbstractContext#driver}
     */
    T getDriver();

    /**
     * Set the {@link AbstractContext#driver}
     *
     * @param driver T
     */
    void setDriver(T driver);

    /**
     * Get context name
     *
     * @return the value of {@link AbstractContext#contextName}
     */
    String getContextName();

    /**
     * Set the value of {@link AbstractContext#contextName}
     *
     * @param contextName String
     */
    void setContextName(String contextName);

    boolean isContextReusable();

    void setContextReusable(boolean contextReusable);

    /**
     * Tear down all active contexts stored in {@link ContextFactory#contextMap}
     *
     * @param context
     * @param <U>
     */
    <U extends AbstractContext> void tearDownContext(U context);

}
