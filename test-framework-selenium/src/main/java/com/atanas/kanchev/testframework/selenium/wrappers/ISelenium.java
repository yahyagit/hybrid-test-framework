package com.atanas.kanchev.testframework.selenium.wrappers;

import com.atanas.kanchev.testframework.selenium.driverfactory.DriverFactory;
import com.atanas.kanchev.testframework.selenium.handlers.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * @author Atanas Ksnchev
 */
public interface ISelenium extends IFind, IInteract, INavigate, IProbe, IWait {}

interface IFind {

    default Finder find() {
        return new Finder();
    }

    default Finder find(WebElement e) {
        return new Finder(e);
    }

    default Finder find(Class<?> clasz) {
        return new Finder(clasz);
    }
}

interface IInteract {

    default Interact interact() {
        return new Interact();
    }

    default JSExecutor js() {
        return new JSExecutor();
    }
}

interface INavigate {
    DriverFactory DRIVER_FACTORY = new DriverFactory();

    default DriverFactory setupSelenium() {
        return DRIVER_FACTORY;
    }

    default NavigateSelenium goTo(final String url) {
        return new NavigateSelenium(DRIVER_FACTORY).getPage(url);
    }
}

interface IProbe {

    default Probe probe(By locator) {
        return new Probe(locator);
    }
}

interface IWait {

    default Wait waitFor(long wait) {
        return new Wait(wait);
    }
}
