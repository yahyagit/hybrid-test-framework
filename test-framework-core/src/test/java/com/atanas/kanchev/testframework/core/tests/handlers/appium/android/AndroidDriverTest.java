package com.atanas.kanchev.testframework.core.tests.handlers.appium.android;

import com.atanas.kanchev.testframework.core.handlers.wrappers.IWrapper;
import io.appium.java_client.AppiumSetting;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.html5.Location;

import java.io.File;

import static org.junit.Assert.*;

/**
 * @author Atanas Ksnchev
 */
public class AndroidDriverTest extends BaseTest implements IWrapper {

    @Test
    public void isAppInstalledTest() {
        assertTrue(appium().methods().interactsWithApps().isAppInstalled("io.appium.android.apis"));
    }

    @Test
    public void getDeviceTimeTest() {
        String time = appium().methods().actionShortcuts().getDeviceTime();
        assertTrue(time.length() == 28);
    }

    @Test
    public void isAppNotInstalledTest() {
        assertFalse(appium().methods().interactsWithApps().isAppInstalled("foo"));
    }

    @Test
    public void closeAppTest() throws InterruptedException {
        appium().methods().interactsWithApps().closeApp();
        appium().methods().interactsWithApps().launchApp();
        assertEquals(".ApiDemos", appium().methods().appiumDriverMethods().currentActivity());
    }

    @Test
    public void pushFileTest() {
        byte[] data = Base64.encodeBase64("The eventual code is no more than the deposit of your understanding. ~E. W. Dijkstra"
                .getBytes());
        appium().methods().pushesFiles().pushFile("/data/local/tmp/remote.txt", data);
        byte[] returnData = appium().methods().pushesFiles().pullFile("/data/local/tmp/remote.txt");
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
            appium().methods().pushesFiles().pushFile("/data/local/tmp/remote2.txt", temp);
            byte[] returnData = appium().methods().pushesFiles().pullFile("/data/local/tmp/remote2.txt");
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
        appium().methods().appiumDriverMethods().ignoreUnimportantViews(true);
        boolean ignoreViews = ((AndroidDriver) context().getCurrentContext().getDriver()).getSettings().get(AppiumSetting.IGNORE_UNIMPORTANT_VIEWS.toString()).getAsBoolean();
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
        assertEquals(ScreenOrientation.PORTRAIT, appium().methods().rotate().getOrientation());
        appium().methods().rotate().rotate(ScreenOrientation.LANDSCAPE);
        assertEquals(ScreenOrientation.LANDSCAPE, appium().methods().rotate().getOrientation());
        appium().methods().rotate().rotate(ScreenOrientation.PORTRAIT);
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
        appium().methods().interactsWithApps().runAppInBackground(4);
        long timeAfter = System.currentTimeMillis();
        assert (timeAfter - time > 3000);
    }

    @Test
    public void pullFileTest() {
        byte[] data =
                appium().methods().pushesFiles().pullFile("data/system/registered_services/android.content.SyncAdapter.xml");
        assert (data.length > 0);
    }

    @Test
    public void resetTest() {
        appium().methods().interactsWithApps().resetApp();
    }

    @Test
    public void endTestCoverage() {
        appium().methods().appiumDriverMethods().endTestCoverage("android.intent.action.MAIN", "");
    }

}
