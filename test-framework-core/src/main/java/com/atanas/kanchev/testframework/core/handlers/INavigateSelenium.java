package com.atanas.kanchev.testframework.core.handlers;

import org.openqa.selenium.WebDriver;

import java.net.URL;

import static com.atanas.kanchev.testframework.core.context.ContextFactory.getCurrentContext;

/**
 * @author Atanas Ksnchev
 */
public interface INavigateSelenium {

    static void getPage(final URL url) {
        ((WebDriver) getCurrentContext().getDriver()).navigate().to(url);
    }

    static void back() {
        ((WebDriver) getCurrentContext().getDriver()).navigate().back();
    }

    static void forward() {
        ((WebDriver) getCurrentContext().getDriver()).navigate().forward();
    }

    static void refresh() {
        ((WebDriver) getCurrentContext().getDriver()).navigate().refresh();
    }
}
