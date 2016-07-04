package com.atanas.kanchev.testframework.core.handlers.wrappers;

import com.atanas.kanchev.testframework.core.handlers.NavigateSelenium;
import com.atanas.kanchev.testframework.selenium.driver_factory.DriverFactory;

/**
 * @author Atanas Ksnchev
 */
public interface INavigate {
    DriverFactory DRIVER_FACTORY = new DriverFactory();

    default DriverFactory setupSelenium() {
        return DRIVER_FACTORY;
    }

    default NavigateSelenium goTo(final String url) {
        return new NavigateSelenium(DRIVER_FACTORY).getPage(url);
    }
}
