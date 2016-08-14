package com.atanas.kanchev.testframework.appium.handlers;

import com.atanas.kanchev.testframework.commons.wrappers.IContext;
import io.appium.java_client.FindsByAccessibilityId;
import io.appium.java_client.FindsByAndroidUIAutomator;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDeviceActionShortcuts;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.Connection;
import org.openqa.selenium.*;
import org.openqa.selenium.html5.Location;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Android native app Appium implementation
 *
 * @author Atanas Ksnchev
 * @version 1.0
 */
public class AndroidNativeHandler {

    /**
     * <p>activity.</p>
     *
     * @return a {@link com.atanas.kanchev.testframework.appium.handlers.AndroidNativeHandler.StartsActivity} object.
     */
    public StartsActivity activity() {
        return new StartsActivity();
    }

    /**
     * <p>orientation.</p>
     *
     * @return a {@link com.atanas.kanchev.testframework.appium.handlers.AndroidNativeHandler.Rotatable} object.
     */
    public Rotatable orientation() {
        return new Rotatable();
    }

    /**
     * <p>pushFiles.</p>
     *
     * @return a {@link com.atanas.kanchev.testframework.appium.handlers.AndroidNativeHandler.PushesFiles} object.
     */
    public PushesFiles pushFiles() {
        return new PushesFiles();
    }

    /**
     * <p>actionShortcuts.</p>
     *
     * @return a {@link com.atanas.kanchev.testframework.appium.handlers.AndroidNativeHandler.ActionShortcuts} object.
     */
    public ActionShortcuts actionShortcuts() {
        return new ActionShortcuts();
    }

    /**
     * <p>touchShortcuts.</p>
     *
     * @return a {@link com.atanas.kanchev.testframework.appium.handlers.AndroidNativeHandler.TouchShortcuts} object.
     */
    public TouchShortcuts touchShortcuts() {
        return new TouchShortcuts();
    }

    /**
     * <p>find.</p>
     *
     * @return a {@link com.atanas.kanchev.testframework.appium.handlers.AndroidNativeHandler.Finds} object.
     */
    public Finds find() {
        return new Finds();
    }

    /**
     * <p>appStrings.</p>
     *
     * @return a {@link com.atanas.kanchev.testframework.appium.handlers.AndroidNativeHandler.HasAppStrings} object.
     */
    public HasAppStrings appStrings() {
        return new HasAppStrings();
    }

    /**
     * <p>interactsWithApps.</p>
     *
     * @return a {@link com.atanas.kanchev.testframework.appium.handlers.AndroidNativeHandler.InteractsWithApps} object.
     */
    public InteractsWithApps interactsWithApps() {
        return new InteractsWithApps();
    }

    /**
     * <p>locationContext.</p>
     *
     * @return a {@link com.atanas.kanchev.testframework.appium.handlers.AndroidNativeHandler.LocationContext} object.
     */
    public LocationContext locationContext() {
        return new LocationContext();
    }

    /**
     * <p>contextAware.</p>
     *
     * @return a {@link com.atanas.kanchev.testframework.appium.handlers.AndroidNativeHandler.ContextAware} object.
     */
    public ContextAware contextAware() {
        return new ContextAware();
    }

    /**
     * <p>appiumDriverMethods.</p>
     *
     * @return a {@link com.atanas.kanchev.testframework.appium.handlers.AndroidNativeHandler.AppiumDriverMethods} object.
     */
    public AppiumDriverMethods appiumDriverMethods() {
        return new AppiumDriverMethods();
    }

    /**
     * <p>touchActions.</p>
     *
     * @return a {@link com.atanas.kanchev.testframework.appium.handlers.AndroidNativeHandler.PerformsTouchActions} object.
     */
    public PerformsTouchActions touchActions() {
        return new PerformsTouchActions();
    }

    /**
     * <p>networkConnection.</p>
     *
     * @return a {@link com.atanas.kanchev.testframework.appium.handlers.AndroidNativeHandler.HasNetworkConnection} object.
     */
    public HasNetworkConnection networkConnection() {
        return new HasNetworkConnection();
    }

    public class StartsActivity implements io.appium.java_client.android.StartsActivity, IContext {

        @Override
        public void startActivity(String appPackage, String appActivity, String appWaitPackage,
            String appWaitActivity, boolean stopApp) {
            ((AndroidDriver) context().getCurrentContext().getDriver())
                .startActivity(appPackage, appActivity, appWaitPackage, appWaitActivity, stopApp);
        }

        @Override
        public void startActivity(String appPackage, String appActivity, String appWaitPackage,
            String appWaitActivity) {
            ((AndroidDriver) context().getCurrentContext().getDriver())
                .startActivity(appPackage, appActivity, appWaitPackage, appWaitActivity);
        }

        @Override public void startActivity(String appPackage, String appActivity) {
            ((AndroidDriver) context().getCurrentContext().getDriver())
                .startActivity(appPackage, appActivity);
        }

        @Override
        public void startActivity(String appPackage, String appActivity, String appWaitPackage,
            String appWaitActivity, String intentAction, String intentCategory, String intentFlags,
            String intentOptionalArgs) {
            ((AndroidDriver) context().getCurrentContext().getDriver())
                .startActivity(appPackage, appActivity, appWaitPackage, appWaitActivity,
                    intentAction, intentCategory, intentFlags, intentOptionalArgs);
        }

        @Override
        public void startActivity(String appPackage, String appActivity, String appWaitPackage,
            String appWaitActivity, String intentAction, String intentCategory, String intentFlags,
            String optionalIntentArguments, boolean stopApp) {
            ((AndroidDriver) context().getCurrentContext().getDriver())
                .startActivity(appPackage, appActivity, appWaitPackage, appWaitActivity,
                    intentAction, intentCategory, intentFlags, optionalIntentArguments, stopApp);
        }
    }


    public class PushesFiles implements io.appium.java_client.android.PushesFiles, IContext {

        @Override public void pushFile(String remotePath, byte[] base64Data) {
            ((AndroidDriver) context().getCurrentContext().getDriver())
                .pushFile(remotePath, base64Data);
        }

        @Override public void pushFile(String remotePath, File file) throws IOException {
            ((AndroidDriver) context().getCurrentContext().getDriver()).pushFile(remotePath, file);
        }

        @Override public byte[] pullFile(String remotePath) {
            return ((AndroidDriver) context().getCurrentContext().getDriver()).pullFile(remotePath);
        }

        @Override public byte[] pullFolder(String remotePath) {
            return ((AndroidDriver) context().getCurrentContext().getDriver()).pullFile(remotePath);
        }
    }


    public class ActionShortcuts implements AndroidDeviceActionShortcuts, IContext {

        @Override public void pressKeyCode(int key) {
            ((AndroidDriver) context().getCurrentContext().getDriver()).pressKeyCode(key);
        }

        @Override public void pressKeyCode(int key, Integer metastate) {
            ((AndroidDriver) context().getCurrentContext().getDriver())
                .pressKeyCode(key, metastate);
        }

        @Override public void longPressKeyCode(int key) {
            ((AndroidDriver) context().getCurrentContext().getDriver()).longPressKeyCode(key);
        }

        @Override public void longPressKeyCode(int key, Integer metastate) {
            ((AndroidDriver) context().getCurrentContext().getDriver())
                .longPressKeyCode(key, metastate);
        }

        @Override public void hideKeyboard() {
            ((AndroidDriver) context().getCurrentContext().getDriver()).hideKeyboard();
        }

        @Override public String getDeviceTime() {
            return ((AndroidDriver) context().getCurrentContext().getDriver()).getDeviceTime();
        }
    }


    public class TouchShortcuts implements io.appium.java_client.TouchShortcuts, IContext {

        @Override public void zoom(int x, int y) {
            ((AndroidDriver) context().getCurrentContext().getDriver()).zoom(x, y);
        }

        @Override public void zoom(WebElement el) {
            ((AndroidDriver) context().getCurrentContext().getDriver()).zoom(el);
        }

        @Override public void tap(int fingers, int x, int y, int duration) {
            ((AndroidDriver) context().getCurrentContext().getDriver())
                .tap(fingers, x, y, duration);
        }

        @Override public void tap(int fingers, WebElement element, int duration) {
            ((AndroidDriver) context().getCurrentContext().getDriver())
                .tap(fingers, element, duration);
        }

        @Override public void swipe(int startx, int starty, int endx, int endy, int duration) {
            ((AndroidDriver) context().getCurrentContext().getDriver())
                .swipe(startx, starty, endx, endy, duration);
        }

        @Override public void pinch(int x, int y) {
            ((AndroidDriver) context().getCurrentContext().getDriver()).pinch(x, y);
        }

        @Override public void pinch(WebElement el) {
            ((AndroidDriver) context().getCurrentContext().getDriver()).pinch(el);
        }
    }


    public class Finds
        implements FindsByAccessibilityId, FindsByAndroidUIAutomator, SearchContext, IContext {

        @Override public AndroidElement findElementByAccessibilityId(String using) {
            return ((AndroidDriver<AndroidElement>) context().getCurrentContext().getDriver())
                .findElementByAccessibilityId(using);
        }

        @Override public List findElementsByAccessibilityId(String using) {
            return ((AndroidDriver<AndroidElement>) context().getCurrentContext().getDriver())
                .findElementsByAccessibilityId(using);
        }

        @Override public AndroidElement findElementByAndroidUIAutomator(String using) {
            return ((AndroidDriver<AndroidElement>) context().getCurrentContext().getDriver())
                .findElementByAndroidUIAutomator(using);
        }

        @Override public List findElementsByAndroidUIAutomator(String using) {
            return ((AndroidDriver<AndroidElement>) context().getCurrentContext().getDriver())
                .findElementsByAndroidUIAutomator(using);
        }

        @Override public List findElements(By by) {
            return ((AndroidDriver<AndroidElement>) context().getCurrentContext().getDriver())
                .findElements(by);
        }

        @Override public AndroidElement findElement(By by) {
            return ((AndroidDriver<AndroidElement>) context().getCurrentContext().getDriver())
                .findElement(by);
        }

        //TODO Override MobileDriver class after it's been implemented in 5.0.0
        public AndroidElement findElementById(String id) {
            return ((AndroidDriver<AndroidElement>) context().getCurrentContext().getDriver())
                .findElementById(id);
        }

        public List findElementsById(String id) {
            return ((AndroidDriver<AndroidElement>) context().getCurrentContext().getDriver())
                .findElementsById(id);
        }


    }


    public class HasAppStrings implements io.appium.java_client.HasAppStrings, IContext {

        @Override public Map<String, String> getAppStringMap() {
            return ((AndroidDriver<AndroidElement>) context().getCurrentContext().getDriver())
                .getAppStringMap();
        }

        @Override public Map<String, String> getAppStringMap(String language) {
            return ((AndroidDriver<AndroidElement>) context().getCurrentContext().getDriver())
                .getAppStringMap(language);
        }

        @Override public Map<String, String> getAppStringMap(String language, String stringFile) {
            return ((AndroidDriver<AndroidElement>) context().getCurrentContext().getDriver())
                .getAppStringMap(language, stringFile);
        }

    }


    public class InteractsWithApps implements io.appium.java_client.InteractsWithApps, IContext {

        @Override public void launchApp() {
            ((AndroidDriver) context().getCurrentContext().getDriver()).launchApp();
        }

        @Override public void installApp(String appPath) {
            ((AndroidDriver) context().getCurrentContext().getDriver()).installApp(appPath);
        }

        @Override public boolean isAppInstalled(String bundleId) {
            return ((AndroidDriver) context().getCurrentContext().getDriver())
                .isAppInstalled(bundleId);
        }

        @Override public void resetApp() {
            ((AndroidDriver) context().getCurrentContext().getDriver()).resetApp();
        }

        @Override public void runAppInBackground(int seconds) {
            ((AndroidDriver) context().getCurrentContext().getDriver()).runAppInBackground(seconds);
        }

        @Override public void removeApp(String bundleId) {
            ((AndroidDriver) context().getCurrentContext().getDriver()).resetApp();
        }

        @Override public void closeApp() {
            ((AndroidDriver) context().getCurrentContext().getDriver()).closeApp();
        }
    }


    public class LocationContext implements org.openqa.selenium.html5.LocationContext, IContext {

        @Override public Location location() {
            return ((AndroidDriver) context().getCurrentContext().getDriver()).location();
        }

        @Override public void setLocation(Location location) {
            ((AndroidDriver) context().getCurrentContext().getDriver()).setLocation(location);
        }
    }


    public class Rotatable implements org.openqa.selenium.Rotatable, IContext {

        @Override public void rotate(ScreenOrientation orientation) {
            ((AndroidDriver) context().getCurrentContext().getDriver()).rotate(orientation);
        }

        @Override public ScreenOrientation getOrientation() {
            return ((AndroidDriver) context().getCurrentContext().getDriver()).getOrientation();
        }

        @Override public void rotate(DeviceRotation deviceRotation) {
            //TODO Implement me!
        }

        @Override public DeviceRotation rotation() {
            //TODO Implement me!
            return null;
        }
    }


    public class ContextAware implements org.openqa.selenium.ContextAware, IContext {

        @Override public WebDriver context(String name) {
            return ((AndroidDriver) context().getCurrentContext().getDriver()).context(name);
        }

        @Override public Set<String> getContextHandles() {
            return ((AndroidDriver) context().getCurrentContext().getDriver()).getContextHandles();
        }

        @Override public String getContext() {
            return ((AndroidDriver) context().getCurrentContext().getDriver()).getContext();
        }
    }


    public class HasNetworkConnection
        implements io.appium.java_client.android.HasNetworkConnection, IContext {

        @Override public void setConnection(Connection connection) {
            ((AndroidDriver) context().getCurrentContext().getDriver()).setConnection(connection);
        }

        @Override public Connection getConnection() {
            return ((AndroidDriver) context().getCurrentContext().getDriver()).getConnection();
        }
    }


    public class AppiumDriverMethods implements IContext {

        public boolean isLocked() {
            return ((AndroidDriver) context().getCurrentContext().getDriver()).isLocked();
        }

        public void lockDevice() {
            ((AndroidDriver) context().getCurrentContext().getDriver()).lockDevice();
        }

        public void unlockDevice() {
            ((AndroidDriver) context().getCurrentContext().getDriver()).unlockDevice();
        }

        public void toggleLocationServices() {
            ((AndroidDriver) context().getCurrentContext().getDriver()).toggleLocationServices();
        }

        public void openNotifications() {
            ((AndroidDriver) context().getCurrentContext().getDriver()).openNotifications();
        }

        public String currentActivity() {
            return ((AndroidDriver) context().getCurrentContext().getDriver()).currentActivity();
        }

        public void ignoreUnimportantViews(boolean compress) {
            ((AndroidDriver) context().getCurrentContext().getDriver())
                .ignoreUnimportantViews(compress);
        }

        public void endTestCoverage(String intent, String path) {
            ((AndroidDriver) context().getCurrentContext().getDriver())
                .endTestCoverage(intent, path);
        }

        public Map<String, Object> getSessionDetails() {
            return ((AndroidDriver<AndroidElement>) context().getCurrentContext().getDriver())
                .getSessionDetails();
        }
    }


    public class PerformsTouchActions
        implements io.appium.java_client.PerformsTouchActions, IContext {

        @Override public TouchAction performTouchAction(TouchAction touchAction) {
            return ((AndroidDriver) context().getCurrentContext().getDriver())
                .performTouchAction(touchAction);
        }

        @Override public void performMultiTouchAction(MultiTouchAction multiAction) {
            ((AndroidDriver) context().getCurrentContext().getDriver())
                .performMultiTouchAction(multiAction);
        }
    }

}
