package com.atanas.kanchev.testframework.core.handlers;

import com.atanas.kanchev.testframework.core.handlers.interfaces.IContext;
import com.atanas.kanchev.testframework.selenium.driver_factory.DriverFactory;
import com.atanas.kanchev.testframework.sikuli.SikuliXFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Web Handler Wrapper Interface
 */
public interface IWrapper extends IBaseHandler, IContext {

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

    default INavigate goTo(final String url) {
        return new NavigateSelenium(DRIVER_FACTORY).getPage(url);
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

    default SikuliXFactory image(final String image) {
        return new SikuliXFactory(image);
    }

    default SikuliXFactory image() {
        return new SikuliXFactory();
    }

}

