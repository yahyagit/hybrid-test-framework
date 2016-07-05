package com.atanas.kanchev.testframework.core.handlers.wrappers;

import com.atanas.kanchev.testframework.core.handlers.Appium;
import com.atanas.kanchev.testframework.core.handlers.appium.AppiumHandlerImpl;

/**
 * @author Atanas Ksnchev
 */
public interface IAppium {

    Appium DEVICE_BASED_HANDLER = new Appium();

    default Appium setup() {
        return DEVICE_BASED_HANDLER;
    }

    default AppiumHandlerImpl appium() {
        return new AppiumHandlerImpl();
    }

}
