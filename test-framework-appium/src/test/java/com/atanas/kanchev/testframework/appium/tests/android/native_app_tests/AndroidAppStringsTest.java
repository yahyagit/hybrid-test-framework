package com.atanas.kanchev.testframework.appium.tests.android.native_app_tests;

import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

public class AndroidAppStringsTest extends BaseTest {

    @Test
    public void getAppStrings() {
        assertNotEquals(0, android()
                .appStrings()
                .getAppStringMap().size());
    }

    @Test
    public void getGetAppStringsUsingLang() {
        assertNotEquals(0, android()
                .appStrings()
                .getAppStringMap("en").size());
    }
}
