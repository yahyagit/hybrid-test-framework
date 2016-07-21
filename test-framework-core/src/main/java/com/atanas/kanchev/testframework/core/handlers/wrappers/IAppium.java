package com.atanas.kanchev.testframework.core.handlers.wrappers;

import com.atanas.kanchev.testframework.core.handlers.appium.AppiumInitRefacto;
import com.atanas.kanchev.testframework.core.handlers.appium.AppiumNative;

/**
 * @author Atanas Ksnchev
 */
public interface IAppium extends IAndroid, RefactoInit {
}


//interface IAppiumInit {
//    AppiumInit APPIUM_INIT = new AppiumInit();
//
//    default AppiumInit appiumInit() {
//        return APPIUM_INIT;
//    }
//}


interface IAndroid {
    default AppiumNative android() {
        return new AppiumNative();
    }
}


interface RefactoInit {

    AppiumInitRefacto APPIUM_INIT_REFACTO = new AppiumInitRefacto();

    default AppiumInitRefacto appiumInit() {
        return APPIUM_INIT_REFACTO;
    }

    default AppiumInitRefacto appiumService() {
        return APPIUM_INIT_REFACTO;
    }
}
