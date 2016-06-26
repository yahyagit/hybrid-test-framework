package com.atanas.kanchev.testframework.core.handlers;

import com.atanas.kanchev.testframework.appium.handlers.DeviceBasedHandler;
import com.atanas.kanchev.testframework.core.context.ContextFactory;
import com.atanas.kanchev.testframework.selenium.driver_factory.DriverFactory;
import com.atanas.kanchev.testframework.sikuli.SikuliXFactory;
import org.openqa.selenium.By;

/**
 * Web Handler Wrapper Interface
 */
public interface IWrapper extends IBaseHandler {

    DriverFactory DRIVER_FACTORY = new DriverFactory();

    default DriverFactory setupBrowser() {
        return new DriverFactory();
    }

    default Wait waitFor(long wait) {
        return new Wait(wait);
    }

    default Navigate goTo(final String url) {
        return new Navigate().getPage(url);
    }

    default Finder find() {
        return new Finder();
    }

    default Probe probe(By locator) {
        return new Probe(locator);
    }

    default ContextFactory context() {
        return new ContextFactory();
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

}

