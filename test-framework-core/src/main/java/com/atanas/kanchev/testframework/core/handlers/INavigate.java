package com.atanas.kanchev.testframework.core.handlers;

import org.openqa.selenium.By;

import java.net.URL;

/**
 * Created by atanas on 02/07/2016.
 */
public interface INavigate extends IWrapper{

    INavigate getPage(final URL url);

    INavigate getPage(final String url);

    INavigate back();

    INavigate forward();

    INavigate refresh();

    INavigate navigateToWindowByPartialTitle(String title);

    INavigate navigateToWindow(String windowIdentifier);

    INavigate navigateToOtherWindow();

    INavigate navigateToActivateFrame();

    INavigate navigateToFrameById(String id);

    INavigate navigateToFrameBy(By by);

    INavigate waitForFrameByIdToBeAvailableAndSwitch(String frameId);

    INavigate returnToDefaultWindow();

    INavigate deleteCookies();

    INavigate deleteCookie(String cookieName);

    INavigate setCookie(String cookieName, String cookieValue);

}
