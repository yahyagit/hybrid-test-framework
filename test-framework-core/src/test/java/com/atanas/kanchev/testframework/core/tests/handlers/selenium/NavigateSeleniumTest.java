package com.atanas.kanchev.testframework.core.tests.handlers.selenium;

import com.atanas.kanchev.testframework.core.handlers.wrappers.IAppium;
import com.atanas.kanchev.testframework.core.handlers.wrappers.ISelenium;
import org.junit.Test;

/**
 * Created by atanas on 10/07/2016.
 */
public class NavigateSeleniumTest extends BaseTest implements IAppium, ISelenium {

    public static final String url = "https://bbc.co.uk";

    @Test
    public void getPage() throws Exception {
        goTo(url);
    }

    @Test
    public void back() throws Exception {
        navigateToActivateFrame();goTo(url).back();
    }

    @Test
    public void forward() throws Exception {
        goTo(url).forward();
    }

    @Test
    public void refresh() throws Exception {
        goTo(url).refresh();
    }

    @Test
    public void navigateToWindowByPartialTitle() throws Exception {

    }

    @Test
    public void navigateToWindow() throws Exception {

    }

    @Test
    public void navigateToOtherWindow() throws Exception {

    }

    @Test
    public void navigateToActivateFrame() throws Exception {

    }

    @Test
    public void navigateToFrameById() throws Exception {

    }

    @Test
    public void navigateToFrameBy() throws Exception {

    }

    @Test
    public void waitForFrameByIdToBeAvailableAndSwitch() throws Exception {

    }

    @Test
    public void returnToDefaultWindow() throws Exception {

    }

    @Test
    public void deleteCookies() throws Exception {

    }

    @Test
    public void deleteCookie() throws Exception {

    }

    @Test
    public void setCookie() throws Exception {

    }

}