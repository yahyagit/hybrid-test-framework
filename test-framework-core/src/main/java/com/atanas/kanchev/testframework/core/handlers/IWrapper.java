package com.atanas.kanchev.testframework.core.handlers;


import com.atanas.kanchev.testframework.appium.handlers.DeviceBasedHandler;
import com.atanas.kanchev.testframework.core.context.ContextFactory;
import com.atanas.kanchev.testframework.selenium.driver_factory.DriverFactory;
import com.atanas.kanchev.testframework.sikuli.SikuliXFactory;
import org.openqa.selenium.By;

import static com.sun.corba.se.spi.activation.IIOP_CLEAR_TEXT.value;


/**
 * Web Handler Wrapper Interface
 */
public interface IWrapper extends IBaseHandler {

    DeviceBasedHandler DEVICE_BASED_HANDLER = new DeviceBasedHandler();
    Finder FINDER = new Finder();
    ContextFactory CONTEXT_FACTORY = new ContextFactory();
    DriverFactory DRIVER_FACTORY = new DriverFactory();

    default DriverFactory setupBrowser(){
        return DRIVER_FACTORY;
    }

    default Wait waitFor(long wait) {
        return new Wait(wait);
    }

    default Nav goTo(final String url) {
        return new Nav(url);
    }

    default Finder find() {
        return FINDER;
    }

    default Probe probe(By locator) {
        return new Probe(locator);
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


}

