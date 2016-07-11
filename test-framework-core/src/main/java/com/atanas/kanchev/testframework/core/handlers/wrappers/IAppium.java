package com.atanas.kanchev.testframework.core.handlers.wrappers;

import com.atanas.kanchev.testframework.core.handlers.appium.AppiumInit;
import com.atanas.kanchev.testframework.core.handlers.appium.AppiumNative;

/**
 * @author Atanas Ksnchev
 */
public interface IAppium extends IAppiumInit, IAndroid {
}

interface IAppiumInit {
    AppiumInit APPIUM_INIT = new AppiumInit();

    default AppiumInit appiumInit() {
        return APPIUM_INIT;
    }
}

interface IAndroid {
    default AppiumNative android() {
        return new AppiumNative();
    }
}