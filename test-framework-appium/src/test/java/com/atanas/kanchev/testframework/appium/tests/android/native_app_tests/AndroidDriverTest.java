/*
 * Copyright 2016 Atanas Stoychev Kanchev
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.atanas.kanchev.testframework.appium.tests.android.native_app_tests;

import io.appium.java_client.AppiumSetting;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.html5.Location;

import java.io.File;
import java.util.Map;

import static com.atanas.kanchev.testframework.appium.accessors.AppiumAccessors.$appium;

import static com.atanas.kanchev.testframework.appium.handlers.AndroidNativeHandler.currentContextKey;
import static com.atanas.kanchev.testframework.commons.accessors.ContextsAccessor.context;
import static org.junit.Assert.*;

public class AndroidDriverTest extends BaseTest {

    @Test public void getDeviceTimeTest() {
        String time = $appium().$androidNative().actionShortcuts().getDeviceTime();
        assertTrue(time.length() == 28);
    }

    @Test public void isAppInstalledTest() {
        assertTrue($appium().$androidNative().interactsWithApps().isAppInstalled("io.appium.$androidNative.apis"));
    }

    @Test public void isAppNotInstalledTest() {
        assertFalse($appium().$androidNative().interactsWithApps().isAppInstalled("foo"));
    }


    @Test public void closeAppTest() throws InterruptedException {
        $appium().$androidNative().interactsWithApps().closeApp();
        $appium().$androidNative().interactsWithApps().launchApp();
        assertEquals(".ApiDemos", $appium().$androidNative().appiumDriverMethods().currentActivity());
    }

    @Test public void pushFileTest() {
        byte[] data = Base64.encodeBase64(
            "The eventual code is no more than the deposit of your understanding. ~E. W. Dijkstra"
                .getBytes());
        $appium().$androidNative().pushFiles().pushFile("/data/local/tmp/remote.txt", data);
        byte[] returnData = $appium().$androidNative().pushFiles().pullFile("/data/local/tmp/remote.txt");
        String returnDataDecoded = new String(Base64.decodeBase64(returnData));
        assertEquals(
            "The eventual code is no more than the deposit of your understanding. ~E. W. Dijkstra",
            returnDataDecoded);
    }

    @Test public void pushTempFileTest() throws Exception {
        File temp = File.createTempFile("Temp_", "_test");
        try {
            FileUtils.writeStringToFile(temp, "The eventual code is no "
                + "more than the deposit of your understanding. ~E. W. Dijkstra", "UTF-8", true);
            $appium().$androidNative().pushFiles().pushFile("/data/local/tmp/remote2.txt", temp);
            byte[] returnData = $appium().$androidNative().pushFiles().pullFile("/data/local/tmp/remote2.txt");
            String returnDataDecoded = new String(Base64.decodeBase64(returnData));
            assertEquals("The eventual code is no more than the deposit of "
                + "your understanding. ~E. W. Dijkstra", returnDataDecoded);
        } finally {
            FileUtils.forceDelete(temp);
        }
    }

    @Test public void ignoreUnimportantViews() {
        $appium().$androidNative().appiumDriverMethods().ignoreUnimportantViews(true);
        boolean ignoreViews =
            context().getContext(currentContextKey).getDriver().getSettings()
                .get(AppiumSetting.IGNORE_UNIMPORTANT_VIEWS.toString()).getAsBoolean();
        assertTrue(ignoreViews);
        context().getContext(currentContextKey).getDriver().ignoreUnimportantViews(false);
        ignoreViews = context().getContext(currentContextKey).getDriver().getSettings()
            .get(AppiumSetting.IGNORE_UNIMPORTANT_VIEWS.toString()).getAsBoolean();
        assertFalse(ignoreViews);
    }

    @Test public void toggleLocationServicesTest() {
        context().getContext(currentContextKey).getDriver().toggleLocationServices();
    }

    @Test public void geolocationTest() {
        Location location = new Location(45, 45, 100);
        context().getContext(currentContextKey).getDriver().setLocation(location);
    }

    @Test public void orientationTest() {
        assertEquals(ScreenOrientation.PORTRAIT, $appium().$androidNative().orientation().getOrientation());
        $appium().$androidNative().orientation().rotate(ScreenOrientation.LANDSCAPE);
        assertEquals(ScreenOrientation.LANDSCAPE, $appium().$androidNative().orientation().getOrientation());
        $appium().$androidNative().orientation().rotate(ScreenOrientation.PORTRAIT);
    }

    @Test public void lockTest() {
        context().getContext(currentContextKey).getDriver().lockDevice();
        assertEquals(true, context().getContext(currentContextKey).getDriver().isLocked());
        context().getContext(currentContextKey).getDriver().unlockDevice();
        assertEquals(false, context().getContext(currentContextKey).getDriver().isLocked());
    }

    @Test public void runAppInBackgroundTest() {
        long time = System.currentTimeMillis();
        $appium().$androidNative().interactsWithApps().runAppInBackground(4);
        long timeAfter = System.currentTimeMillis();
        assert (timeAfter - time > 3000);
    }

    @Test public void pullFileTest() {
        byte[] data = $appium().$androidNative().pushFiles()
            .pullFile("data/system/registered_services/$androidNative.content.SyncAdapter.xml");
        assert (data.length > 0);
    }

    @Test public void resetTest() {
        $appium().$androidNative().interactsWithApps().resetApp();
    }

    @Test public void endTestCoverage() {
        $appium().$androidNative().appiumDriverMethods().endTestCoverage("$androidNative.intent.action.MAIN", "");
    }

    @Test public void getDeviceUDIDTest() {
        String deviceSerial =
            $appium().$androidNative().appiumDriverMethods().getSessionDetails().get("deviceUDID").toString();
        assertNotNull(deviceSerial);
    }

    @Test public void getSessionMapData() {
        Map<?, ?> map =
            (Map<?, ?>) $appium().$androidNative().appiumDriverMethods().getSessionDetails().get("desired");
        assertNotEquals(map.size(), 0);
    }

}
