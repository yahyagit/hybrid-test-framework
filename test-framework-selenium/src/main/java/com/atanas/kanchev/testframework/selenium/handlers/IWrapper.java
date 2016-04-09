package com.atanas.kanchev.testframework.selenium.handlers;

/**
 * Web Handler Wrapper Interface
 */
public interface IWrapper extends IBaseHandler {

    default Wait waitFor() {
        return new Wait();
    }

    default Nav goTo(final String url) {
        return new Nav(url);
    }

    default Finder find() {
        return new Finder();
    }

    default Driver setup() {
        return new Driver();
    }

    default Prober probe(Locator locator, String value) {
        return new Prober(locator, value);
    }

    default Context context() {
        return new Context();
    }


    class Wait implements IWait {
    }

    class Nav extends Navigate implements IWrapper {
        public Nav(String url) {
            super(url);
        }
    }

    class Finder implements IFinder {
    }

    class Driver implements IDriver {
    }

    class Prober extends Probe {
        /**
         * Constructor
         *
         * @param locator
         * @param value   Locator type
         */
        Prober(Locator locator, String value) {
            super(locator, value);
        }
    }

    class Context implements IContext {
    }

}

