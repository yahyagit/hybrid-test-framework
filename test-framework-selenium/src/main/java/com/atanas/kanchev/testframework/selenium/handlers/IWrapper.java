package com.atanas.kanchev.testframework.selenium.handlers;

import com.atanas.kanchev.testframework.appium.handlers.DeviceBasedHandler;
import com.atanas.kanchev.testframework.sikuli.SikuliXFactory;

/**
 * Web Handler Wrapper Interface
 */
public interface IWrapper extends IBaseHandler {

    default DriverConfig setupBrowser() {
        return new DriverConfig();
    }

    default Wait waitFor() {
        return new Wait();
    }

    default Nav goTo(final String url) {
        return new Nav(url);
    }

    default Finder find() {
        return new Finder();
    }


    default Prober probe(Locator locator, String value) {
        return new Prober(locator, value);
    }

    default Context context() {
        return new Context();
    }

    default SikuliXFactory image(final String image) {
        return new SikuliXFactory(image);
    }

    default SikuliXFactory image() {
        return new SikuliXFactory(null);
    }

    default DeviceBasedHandler setupAppium() {
        return new DeviceBasedHandler();
    }

    class Wait implements IWait {
    }

    class Nav extends Navigate implements IWrapper {
        Nav(String url) {
            super(url);
        }
    }

    class Finder implements IFinder {
    }

    class DriverConfig extends com.atanas.kanchev.testframework.selenium.handlers.DriverConfig {
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

