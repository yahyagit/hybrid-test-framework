package com.atanas.kanchev.testframework.core.handlers;

import io.appium.java_client.AppiumDriver;

import java.net.URL;

import static com.atanas.kanchev.testframework.core.context.ContextFactory.getCurrentContext;

/**
 * @author Atanas Ksnchev
 */
public interface INavigateAppium {

    static void getPage(final URL url) {
        ((AppiumDriver) getCurrentContext().getDriver()).navigate().to(url);
    }

    static void back() {
        ((AppiumDriver) getCurrentContext().getDriver()).navigate().back();
    }

    static void forward() {
        ((AppiumDriver) getCurrentContext().getDriver()).navigate().forward();
    }

    static void refresh() {
        ((AppiumDriver) getCurrentContext().getDriver()).navigate().refresh();
    }

}

