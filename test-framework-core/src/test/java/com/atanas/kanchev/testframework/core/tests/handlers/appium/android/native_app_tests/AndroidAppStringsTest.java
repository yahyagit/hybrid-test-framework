package com.atanas.kanchev.testframework.core.tests.handlers.appium.android.native_app_tests;

import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

public class AndroidAppStringsTest extends BaseTest {

    @Test
    public void getAppStrings() {
        assertNotEquals(0, android()
                .hasAppStrings()
                .getAppStringMap().size());
    }

    @Test
    public void getGetAppStringsUsingLang() {
        assertNotEquals(0, android()
                .hasAppStrings()
                .getAppStringMap("en").size());
    }
}
