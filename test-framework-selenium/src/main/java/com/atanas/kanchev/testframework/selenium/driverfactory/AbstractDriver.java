package com.atanas.kanchev.testframework.selenium.driverfactory;

/**
 * @author Atanas Ksnchev
 */
abstract class AbstractDriver<T> {

    private T driver;

    abstract T configureTimeouts(long implicitlyWait, long pageLoadTimeout);

    abstract String getExecutionIP();

    public AbstractDriver setDriver(T driver) {
        this.driver = driver;
        return this;
    }

    public T getDriver() {
        return driver;
    }
}
