package com.atanas.kanchev.testframework.core.handlers.wrappers;

import com.atanas.kanchev.testframework.core.handlers.*;
import com.atanas.kanchev.testframework.selenium.driver_factory.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by atanas on 10/07/2016.
 */
public interface ISelenium extends IFind, IInteract, INavigate, IProbe, IWait {}

/**
 * @author Atanas Ksnchev
 */
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

/**
 * @author Atanas Ksnchev
 */
interface IInteract {

    default Interact interact() {
        return new Interact();
    }

    default JSExecutor js() {
        return new JSExecutor();
    }
}

/**
 * @author Atanas Ksnchev
 */
interface INavigate {
    DriverFactory DRIVER_FACTORY = new DriverFactory();

    default DriverFactory setupSelenium() {
        return DRIVER_FACTORY;
    }

    default NavigateSelenium goTo(final String url) {
        return new NavigateSelenium(DRIVER_FACTORY).getPage(url);
    }
}

/**
 * @author Atanas Ksnchev
 */
interface IProbe {

    default Probe probe(By locator) {
        return new Probe(locator);
    }
}

/**
 * @author Atanas Ksnchev
 */
interface IWait {

    default Wait waitFor(long wait) {
        return new Wait(wait);
    }
}
