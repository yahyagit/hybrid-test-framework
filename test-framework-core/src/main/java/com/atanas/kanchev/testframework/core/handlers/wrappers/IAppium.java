package com.atanas.kanchev.testframework.core.handlers.wrappers;

import com.atanas.kanchev.testframework.appium.driverfactory.refacto.AppiumDriverFactory;
import com.atanas.kanchev.testframework.core.handlers.appium.AppiumInit;
import com.atanas.kanchev.testframework.core.handlers.appium.AppiumNative;
import org.openqa.selenium.Capabilities;

import java.net.URL;

/**
 * @author Atanas Ksnchev
 */
public interface IAppium extends IAppiumInit, IAndroid, RefactoInit {
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


interface RefactoInit {

    default AppiumDriverFactory initAppium(URL url, Capabilities caps) {
        return new AppiumDriverFactory(url, caps);
    }
}
