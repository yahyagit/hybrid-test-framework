package com.atanas.kanchev.testframework.appium.tests.android.native_app_tests;

import io.appium.java_client.AppiumSetting;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.html5.Location;

import java.io.File;
import java.util.Map;

import static org.junit.Assert.*;

public class AndroidDriverTest extends BaseTest {

    @Test
    public void getDeviceTimeTest() {
        String time = android()
                .actionShortcuts()
                .getDeviceTime();
        assertTrue(time.length() == 28);
    }

    @Test
    public void isAppInstalledTest() {
        assertTrue(android()
                .interactsWithApps()
                .isAppInstalled("io.appium.android.apis"));
    }

    @Test
    public void isAppNotInstalledTest() {
        assertFalse(android()
                .interactsWithApps()
                .isAppInstalled("foo"));
    }


    @Test
    public void closeAppTest() throws InterruptedException {
        android()
                .interactsWithApps()
                .closeApp();
        android()
                .interactsWithApps()
                .launchApp();
        assertEquals(".ApiDemos", android()
                .appiumDriverMethods()
                .currentActivity());
    }

    @Test
    public void pushFileTest() {
        byte[] data = Base64.encodeBase64("The eventual code is no more than the deposit of your understanding. ~E. W. Dijkstra"
                .getBytes());
        android()
                .pushFiles()
                .pushFile("/data/local/tmp/remote.txt", data);
        byte[] returnData = android()
                .pushFiles()
                .pullFile("/data/local/tmp/remote.txt");
        String returnDataDecoded = new String(Base64.decodeBase64(returnData));
        assertEquals("The eventual code is no more than the deposit of your understanding. ~E. W. Dijkstra",
                returnDataDecoded);
    }

    @Test
    public void pushTempFileTest() throws Exception {
        File temp = File.createTempFile("Temp_", "_test");
        try {
            FileUtils.writeStringToFile(temp, "The eventual code is no "
                    + "more than the deposit of your understanding. ~E. W. Dijkstra", "UTF-8", true);
            android()
                    .pushFiles()
                    .pushFile("/data/local/tmp/remote2.txt", temp);
            byte[] returnData = android()
                    .pushFiles()
                    .pullFile("/data/local/tmp/remote2.txt");
            String returnDataDecoded = new String(Base64.decodeBase64(returnData));
            assertEquals(
                    "The eventual code is no more than the deposit of "
                            + "your understanding. ~E. W. Dijkstra",
                    returnDataDecoded);
        } finally {
            FileUtils.forceDelete(temp);
        }
    }

    @Test
    public void ignoreUnimportantViews() {
        android()
                .appiumDriverMethods()
                .ignoreUnimportantViews(true);
        boolean ignoreViews = ((AndroidDriver) context().getCurrentContext().getDriver())
                .getSettings().get(AppiumSetting.IGNORE_UNIMPORTANT_VIEWS.toString()).getAsBoolean();
        assertTrue(ignoreViews);
        ((AndroidDriver) context().getCurrentContext().getDriver()).ignoreUnimportantViews(false);
        ignoreViews = ((AndroidDriver) context().getCurrentContext().getDriver()).getSettings().get(AppiumSetting.IGNORE_UNIMPORTANT_VIEWS.toString()).getAsBoolean();
        assertFalse(ignoreViews);
    }

    @Test
    public void toggleLocationServicesTest() {
        ((AndroidDriver) context().getCurrentContext().getDriver()).toggleLocationServices();
    }

    @Test
    public void geolocationTest() {
        Location location = new Location(45, 45, 100);
        ((AndroidDriver) context().getCurrentContext().getDriver()).setLocation(location);
    }

    @Test
    public void orientationTest() {
        assertEquals(ScreenOrientation.PORTRAIT, android()
                .orientation()
                .getOrientation());
        android()
                .orientation()
                .rotate(ScreenOrientation.LANDSCAPE);
        assertEquals(ScreenOrientation.LANDSCAPE, android()
                .orientation()
                .getOrientation());
        android()
                .orientation()
                .rotate(ScreenOrientation.PORTRAIT);
    }

    @Test
    public void lockTest() {
        ((AndroidDriver) context().getCurrentContext().getDriver()).lockDevice();
        assertEquals(true, ((AndroidDriver) context().getCurrentContext().getDriver()).isLocked());
        ((AndroidDriver) context().getCurrentContext().getDriver()).unlockDevice();
        assertEquals(false, ((AndroidDriver) context().getCurrentContext().getDriver()).isLocked());
    }

    @Test
    public void runAppInBackgroundTest() {
        long time = System.currentTimeMillis();
        android().interactsWithApps().runAppInBackground(4);
        long timeAfter = System.currentTimeMillis();
        assert (timeAfter - time > 3000);
    }

    @Test
    public void pullFileTest() {
        byte[] data =
                android()
                        .pushFiles()
                        .pullFile("data/system/registered_services/android.content.SyncAdapter.xml");
        assert (data.length > 0);
    }

    @Test
    public void resetTest() {
        android()
                .interactsWithApps()
                .resetApp();
    }

    @Test
    public void endTestCoverage() {
        android()
                .appiumDriverMethods()
                .endTestCoverage("android.intent.action.MAIN", "");
    }

    @Test public void getDeviceUDIDTest() {
        String deviceSerial = android()
                .appiumDriverMethods().getSessionDetails().get("deviceUDID").toString();
        assertNotNull(deviceSerial);
    }

    @Test public void getSessionMapData() {
        Map<?,?> map = (Map<?, ?>) android()
                .appiumDriverMethods().getSessionDetails().get("desired");
        assertNotEquals(map.size(), 0);
    }

}
