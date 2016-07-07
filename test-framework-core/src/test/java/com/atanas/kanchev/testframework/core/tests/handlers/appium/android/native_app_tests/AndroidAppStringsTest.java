package com.atanas.kanchev.testframework.core.tests.handlers.appium.android.native_app_tests;

import com.atanas.kanchev.testframework.core.handlers.wrappers.IAppium;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

public class AndroidAppStringsTest extends BaseTest implements IAppium {

    @Test
    public void getAppStrings() {
        assertNotEquals(0, appium().methods().hasAppStrings().getAppStringMap().size());
    }

    @Test
    public void getGetAppStringsUsingLang() {
        assertNotEquals(0, appium().methods().hasAppStrings().getAppStringMap("en").size());
    }
}
