package com.atanas.kanchev.testframework.selenium.handlers;

/**
 * Web Handler Wrapper Interface
 */
public interface IWrapper extends IBaseHandler {

    default Wait waitFor() {
        return new Wait();
    }

    default Navigator goTo() {
        return new Navigator();
    }

    default Finder find() {
        return new Finder();
    }

    default Driver setup() {
        return new Driver();
    }

    default Probe probe() {
        return new Probe();
    }

    default Context context() {
        return new Context();
    }


    class Wait implements IWait, INavigator {
    }

    class Navigator implements INavigator {
    }

    class Finder implements IFinder {
    }

    class Driver implements IDriver {
    }

    class Probe implements IProbe {
    }

    class Context implements IContext {
    }

}

