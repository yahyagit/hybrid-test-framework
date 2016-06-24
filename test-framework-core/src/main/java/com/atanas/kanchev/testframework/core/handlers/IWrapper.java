package com.atanas.kanchev.testframework.core.handlers;


import com.atanas.kanchev.testframework.appium.handlers.DeviceBasedHandler;
import com.atanas.kanchev.testframework.core.context.ContextFactory;
import com.atanas.kanchev.testframework.sikuli.SikuliXFactory;


/**
 * Web Handler Wrapper Interface
 */
public interface IWrapper extends IBaseHandler {

    DeviceBasedHandler DEVICE_BASED_HANDLER = new DeviceBasedHandler();
    Finder FINDER = new Finder();
    ContextFactory CONTEXT_FACTORY = new ContextFactory();
//
//    DriverBase BASE = new DriverBase();
//
//    default DriverBase setupBrowser() {
//        return BASE;
//    }

    default Wait waitFor(long wait) {
        return new Wait(wait);
    }

    default Nav goTo(final String url) {
        return new Nav(url);
    }

    default Finder find() {
        return FINDER;
    }

    default Prober probe(Locator locator, String value) {
        return new Prober(locator, value);
    }

    default ContextFactory context() {
        return CONTEXT_FACTORY;
    }

    default SikuliXFactory image(final String image) {
        return new SikuliXFactory(image);
    }

    default SikuliXFactory image() {
        return new SikuliXFactory(null);
    }

    default DeviceBasedHandler setupAppium() {
        return DEVICE_BASED_HANDLER;
    }

    class Nav extends Navigate implements IWrapper {
        Nav(String url) {
            super(url);
        }
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


}

