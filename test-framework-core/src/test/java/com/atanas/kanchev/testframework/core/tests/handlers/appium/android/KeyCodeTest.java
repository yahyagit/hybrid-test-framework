package com.atanas.kanchev.testframework.core.tests.handlers.appium.android;

import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.android.AndroidKeyMetastate;
import org.junit.Before;
import org.junit.Test;

public class KeyCodeTest extends BaseTest {

    @Before
    public void before() throws Exception {
        appium()
                .methods()
                .interactsWithApps()
                .resetApp();
    }

    @Test
    public void pressKeyCodeTest() {
        appium()
                .methods()
                .actionShortcuts()
                .pressKeyCode(AndroidKeyCode.HOME);
    }

    @Test
    public void pressKeyCodeWithMetastateTest() {
        appium()
                .methods()
                .actionShortcuts()
                .pressKeyCode(AndroidKeyCode.SPACE, AndroidKeyMetastate.META_SHIFT_ON);
    }

    @Test
    public void longPressKeyCodeTest() {
        appium()
                .methods()
                .actionShortcuts()
                .longPressKeyCode(AndroidKeyCode.HOME);
    }

    @Test
    public void longPressKeyCodeWithMetastateTest() {
        appium()
                .methods()
                .actionShortcuts()
                .longPressKeyCode(AndroidKeyCode.HOME, AndroidKeyMetastate.META_SHIFT_ON);
    }
}