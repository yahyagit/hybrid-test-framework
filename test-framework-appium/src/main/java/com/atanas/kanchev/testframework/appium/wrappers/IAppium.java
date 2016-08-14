package com.atanas.kanchev.testframework.appium.wrappers;

import com.atanas.kanchev.testframework.appium.driverfactory.AppiumDriverFactory;
import com.atanas.kanchev.testframework.appium.handlers.AndroidNativeHandler;

/**
 * @author Atanas Ksnchev
 */
public interface IAppium extends IRefactoInit {
}

interface IRefactoInit {

    AppiumDriverFactory APPIUM_DRIVER_FACTORY = new AppiumDriverFactory();

    default AppiumDriverFactory appiumInit() {
        return APPIUM_DRIVER_FACTORY;
    }

    default AndroidNativeHandler android() {
        return new AndroidNativeHandler();
    }

}
