package com.atanas.kanchev.testframework.core.handlers;

import com.atanas.kanchev.testframework.core.context.ContextFactory;
import com.atanas.kanchev.testframework.core.context.WebContext;
import com.atanas.kanchev.testframework.selenium.driver_factory.DriverFactory;
import com.atanas.kanchev.testframework.sikuli.SikuliXFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Web Handler Wrapper Interface
 */
public interface IWrapper extends IBaseHandler {

    DriverFactory DRIVER_FACTORY = new DriverFactory();
    DeviceBasedHandler DEVICE_BASED_HANDLER = new DeviceBasedHandler();

    default DriverFactory setupSelenium() {
        return DRIVER_FACTORY;
    }

    default DeviceBasedHandler setupAppium() {
        return DEVICE_BASED_HANDLER;
    }

    default Wait waitFor(long wait) {
        return new Wait(wait);
    }

    default Navigate goTo(final String url) {
        return new Navigate(DRIVER_FACTORY).getPage(url);
    }

    default Finder find() {
        return new Finder();
    }

    default Finder find(WebElement e) {
        return new Finder(e);
    }

    default Finder find(Class<?> clasz) {
        return new Finder(clasz);
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
        return new SikuliXFactory();
    }

    default WebElement currentElement() {
        return ((WebContext) ContextFactory.getCurrentContext()).getCurrentElement();
    }

}

