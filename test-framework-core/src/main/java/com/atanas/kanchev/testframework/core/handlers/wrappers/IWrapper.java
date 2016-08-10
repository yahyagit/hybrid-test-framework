package com.atanas.kanchev.testframework.core.handlers.wrappers;

import com.atanas.kanchev.testframework.appium.wrappers.IAppium;
import com.atanas.kanchev.testframework.commons.wrappers.IContext;
import com.atanas.kanchev.testframework.selenium.wrappers.ISelenium;

/**
 * Web Handler Wrapper Interface
 */
public interface IWrapper extends IContext, ISikulix, IAppium, ISelenium {
}

