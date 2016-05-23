package com.atanas.kanchev.testframework.selenium.driverfactory;

import static com.atanas.kanchev.testframework.selenium.driverfactory.DefaultProperties.DEFAULT_IMPLICIT_WAIT;
import static com.atanas.kanchev.testframework.selenium.driverfactory.DefaultProperties.DEFAULT_PAGE_LOAD_TIMEOUT;

/**
 * @author Atanas Ksnchev
 */
abstract class AbstractDriver<T> {

    protected T driver;

    protected long implicitlyWait = DEFAULT_IMPLICIT_WAIT;
    protected long pageLoadTimeout = DEFAULT_PAGE_LOAD_TIMEOUT;

    protected AbstractDriver() {
        if (JVMArgsFactory.getImplicitlyWait() != null)
            this.implicitlyWait = Long.parseLong(JVMArgsFactory.getImplicitlyWait());
        if (JVMArgsFactory.getPageLoadTimeout() != null)
            this.pageLoadTimeout = Long.parseLong(JVMArgsFactory.getPageLoadTimeout());
    }

    protected abstract String getExecutionIP();

    protected AbstractDriver setDriver(T driver) {
        this.driver = driver;
        return this;
    }

    protected abstract T getDriver();

    protected abstract AbstractDriver setImplicitlyWait(long wait);

    protected abstract AbstractDriver setPageLoadTimeout(long timeout);

    protected abstract AbstractDriver configureDriver();
}
