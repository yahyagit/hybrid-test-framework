package com.atanas.kanchev.testframework.core.context;

/**
 * @author Atanas Ksnchev
 */
public interface IAbstractContext<T> {

    /**
     * Get driver reference
     *
     * @return reference of {@link AbstractContext#driver}
     */
    T getDriver();

    /**
     * Set the {@link AbstractContext#driver}
     *
     * @param driver
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

    <U extends AbstractContext> void tearDownContext(U context);

}
