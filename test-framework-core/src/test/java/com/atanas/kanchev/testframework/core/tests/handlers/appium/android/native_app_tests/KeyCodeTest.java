package com.atanas.kanchev.testframework.core.tests.handlers.appium.android.native_app_tests;

import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.android.AndroidKeyMetastate;
import org.junit.Before;
import org.junit.Test;

public class KeyCodeTest extends BaseTest {

    @Before
    public void before() throws Exception {
        android()
                .interactsWithApps()
                .resetApp();
    }

    @Test
    public void pressKeyCodeTest() {
        android()
                .actionShortcuts()
                .pressKeyCode(AndroidKeyCode.HOME);
    }

    @Test
    public void pressKeyCodeWithMetastateTest() {
        android()
                .actionShortcuts()
                .pressKeyCode(AndroidKeyCode.SPACE, AndroidKeyMetastate.META_SHIFT_ON);
    }

    @Test
    public void longPressKeyCodeTest() {
        android()
                .actionShortcuts()
                .longPressKeyCode(AndroidKeyCode.HOME);
    }

    @Test
    public void longPressKeyCodeWithMetastateTest() {
        android()
                .actionShortcuts()
                .longPressKeyCode(AndroidKeyCode.HOME, AndroidKeyMetastate.META_SHIFT_ON);
    }
}