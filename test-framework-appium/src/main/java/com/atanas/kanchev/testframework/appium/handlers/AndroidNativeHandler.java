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

package com.atanas.kanchev.testframework.appium.handlers;

import com.atanas.kanchev.testframework.commons.accessors.ContextsAccessor;
import io.appium.java_client.FindsByAccessibilityId;
import io.appium.java_client.FindsByAndroidUIAutomator;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDeviceActionShortcuts;
import io.appium.java_client.android.Connection;
import org.openqa.selenium.*;
import org.openqa.selenium.html5.Location;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.atanas.kanchev.testframework.appium.accessors.AppiumAccessorsSingleton.currentContextKey;
import static com.atanas.kanchev.testframework.commons.accessors.ContextsAccessor.context;

/**
 * <p>AndroidNativeHandler class.</p>
 *
 * @author Atanas Kanchev
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

    public class StartsActivity implements io.appium.java_client.android.StartsActivity {

        @Override
        public void startActivity(String appPackage, String appActivity, String appWaitPackage,
            String appWaitActivity, boolean stopApp) {
            context().getContext(currentContextKey).getDriver()
                .startActivity(appPackage, appActivity, appWaitPackage, appWaitActivity, stopApp);
        }

        @Override
        public void startActivity(String appPackage, String appActivity, String appWaitPackage,
            String appWaitActivity) {
            context().getContext(currentContextKey).getDriver()
                .startActivity(appPackage, appActivity, appWaitPackage, appWaitActivity);
        }

        @Override public void startActivity(String appPackage, String appActivity) {
            context().getContext(currentContextKey).getDriver()
                .startActivity(appPackage, appActivity);
        }

        @Override
        public void startActivity(String appPackage, String appActivity, String appWaitPackage,
            String appWaitActivity, String intentAction, String intentCategory, String intentFlags,
            String intentOptionalArgs) {
            context().getContext(currentContextKey).getDriver()
                .startActivity(appPackage, appActivity, appWaitPackage, appWaitActivity,
                    intentAction, intentCategory, intentFlags, intentOptionalArgs);
        }

        @Override
        public void startActivity(String appPackage, String appActivity, String appWaitPackage,
            String appWaitActivity, String intentAction, String intentCategory, String intentFlags,
            String optionalIntentArguments, boolean stopApp) {
            context().getContext(currentContextKey).getDriver()
                .startActivity(appPackage, appActivity, appWaitPackage, appWaitActivity,
                    intentAction, intentCategory, intentFlags, optionalIntentArguments, stopApp);
        }
    }


    public class PushesFiles implements io.appium.java_client.android.PushesFiles {

        @Override public void pushFile(String remotePath, byte[] base64Data) {
            context().getContext(currentContextKey).getDriver().pushFile(remotePath, base64Data);
        }

        @Override public void pushFile(String remotePath, File file) throws IOException {
            context().getContext(currentContextKey).getDriver().pushFile(remotePath, file);
        }

        @Override public byte[] pullFile(String remotePath) {
            return context().getContext(currentContextKey).getDriver().pullFile(remotePath);
        }

        @Override public byte[] pullFolder(String remotePath) {
            return context().getContext(currentContextKey).getDriver().pullFile(remotePath);
        }
    }


    public class ActionShortcuts implements AndroidDeviceActionShortcuts {

        @Override public void pressKeyCode(int key) {
            context().getContext(currentContextKey).getDriver().pressKeyCode(key);
        }

        @Override public void pressKeyCode(int key, Integer metastate) {
            context().getContext(currentContextKey).getDriver().pressKeyCode(key, metastate);
        }

        @Override public void longPressKeyCode(int key) {
            context().getContext(currentContextKey).getDriver().longPressKeyCode(key);
        }

        @Override public void longPressKeyCode(int key, Integer metastate) {
            context().getContext(currentContextKey).getDriver().longPressKeyCode(key, metastate);
        }

        @Override public void hideKeyboard() {
            context().getContext(currentContextKey).getDriver().hideKeyboard();
        }

        @Override public String getDeviceTime() {
            return context().getContext(currentContextKey).getDriver().getDeviceTime();
        }
    }


    public class TouchShortcuts implements io.appium.java_client.TouchShortcuts {

        @Override public void zoom(int x, int y) {
            context().getContext(currentContextKey).getDriver().zoom(x, y);
        }

        @Override public void zoom(WebElement el) {
            context().getContext(currentContextKey).getDriver().zoom(el);
        }

        @Override public void tap(int fingers, int x, int y, int duration) {
            context().getContext(currentContextKey).getDriver().tap(fingers, x, y, duration);
        }

        @Override public void tap(int fingers, WebElement element, int duration) {
            context().getContext(currentContextKey).getDriver().tap(fingers, element, duration);
        }

        @Override public void swipe(int startx, int starty, int endx, int endy, int duration) {
            context().getContext(currentContextKey).getDriver()
                .swipe(startx, starty, endx, endy, duration);
        }

        @Override public void pinch(int x, int y) {
            context().getContext(currentContextKey).getDriver().pinch(x, y);
        }

        @Override public void pinch(WebElement el) {
            context().getContext(currentContextKey).getDriver().pinch(el);
        }
    }


    public class Finds implements FindsByAccessibilityId, FindsByAndroidUIAutomator, SearchContext {

        @Override public WebElement findElementByAccessibilityId(String using) {
            return context().getContext(currentContextKey).getDriver()
                .findElementByAccessibilityId(using);
        }

        @Override public List findElementsByAccessibilityId(String using) {
            return context().getContext(currentContextKey).getDriver()
                .findElementsByAccessibilityId(using);
        }

        @Override public WebElement findElementByAndroidUIAutomator(String using) {
            return context().getContext(currentContextKey).getDriver()
                .findElementByAndroidUIAutomator(using);
        }

        @Override public List findElementsByAndroidUIAutomator(String using) {
            return context().getContext(currentContextKey).getDriver()
                .findElementsByAndroidUIAutomator(using);
        }

        @Override public List findElements(By by) {
            return context().getContext(currentContextKey).getDriver().findElements(by);
        }

        @Override public WebElement findElement(By by) {
            return context().getContext(currentContextKey).getDriver().findElement(by);
        }

        //TODO Override MobileDriver class after it's been implemented in 5.0.0
        public WebElement findElementById(String id) {
            return context().getContext(currentContextKey).getDriver().findElementById(id);
        }

        public List findElementsById(String id) {
            return context().getContext(currentContextKey).getDriver().findElementsById(id);
        }


    }


    public class HasAppStrings implements io.appium.java_client.HasAppStrings {

        @Override public Map<String, String> getAppStringMap() {
            return context().getContext(currentContextKey).getDriver().getAppStringMap();
        }

        @Override public Map<String, String> getAppStringMap(String language) {
            return context().getContext(currentContextKey).getDriver().getAppStringMap(language);
        }

        @Override public Map<String, String> getAppStringMap(String language, String stringFile) {
            return context().getContext(currentContextKey).getDriver()
                .getAppStringMap(language, stringFile);
        }

    }


    public class InteractsWithApps implements io.appium.java_client.InteractsWithApps {

        @Override public void launchApp() {
            context().getContext(currentContextKey).getDriver().launchApp();
        }

        @Override public void installApp(String appPath) {
            context().getContext(currentContextKey).getDriver().installApp(appPath);
        }

        @Override public boolean isAppInstalled(String bundleId) {
            return context().getContext(currentContextKey).getDriver().isAppInstalled(bundleId);
        }

        @Override public void resetApp() {
            context().getContext(currentContextKey).getDriver().resetApp();
        }

        @Override public void runAppInBackground(int seconds) {
            context().getContext(currentContextKey).getDriver().runAppInBackground(seconds);
        }

        @Override public void removeApp(String bundleId) {
            context().getContext(currentContextKey).getDriver().resetApp();
        }

        @Override public void closeApp() {
            context().getContext(currentContextKey).getDriver().closeApp();
        }
    }


    public class LocationContext implements org.openqa.selenium.html5.LocationContext {

        @Override public Location location() {
            return context().getContext(currentContextKey).getDriver().location();
        }

        @Override public void setLocation(Location location) {
            context().getContext(currentContextKey).getDriver().setLocation(location);
        }
    }


    public class Rotatable implements org.openqa.selenium.Rotatable {

        @Override public void rotate(ScreenOrientation orientation) {
            context().getContext(currentContextKey).getDriver().rotate(orientation);
        }

        @Override public ScreenOrientation getOrientation() {
            return context().getContext(currentContextKey).getDriver().getOrientation();
        }

        @Override public void rotate(DeviceRotation deviceRotation) {
            //TODO Implement me!
        }

        @Override public DeviceRotation rotation() {
            //TODO Implement me!
            return null;
        }
    }


    public class ContextAware implements org.openqa.selenium.ContextAware {

        @Override public WebDriver context(String name) {
            return ContextsAccessor.context().getContext(currentContextKey).getDriver()
                .context(name);
        }

        @Override public Set<String> getContextHandles() {
            return ContextsAccessor.context().getContext(currentContextKey).getDriver()
                .getContextHandles();
        }

        @Override public String getContext() {
            return ContextsAccessor.context().getContext(currentContextKey).getDriver()
                .getContext();
        }
    }


    public class HasNetworkConnection
        implements io.appium.java_client.android.HasNetworkConnection {

        @Override public Connection getConnection() {
            return context().getContext(currentContextKey).getDriver().getConnection();
        }

        @Override public void setConnection(Connection connection) {
            context().getContext(currentContextKey).getDriver().setConnection(connection);
        }
    }


    public class AppiumDriverMethods {

        public boolean isLocked() {
            return context().getContext(currentContextKey).getDriver().isLocked();
        }

        public void lockDevice() {
            context().getContext(currentContextKey).getDriver().lockDevice();
        }

        public void unlockDevice() {
            context().getContext(currentContextKey).getDriver().unlockDevice();
        }

        public void toggleLocationServices() {
            context().getContext(currentContextKey).getDriver().toggleLocationServices();
        }

        public void openNotifications() {
            context().getContext(currentContextKey).getDriver().openNotifications();
        }

        public String currentActivity() {
            return context().getContext(currentContextKey).getDriver().currentActivity();
        }

        public void ignoreUnimportantViews(boolean compress) {
            context().getContext(currentContextKey).getDriver().ignoreUnimportantViews(compress);
        }

        public void endTestCoverage(String intent, String path) {
            context().getContext(currentContextKey).getDriver().endTestCoverage(intent, path);
        }

        public Map<String, Object> getSessionDetails() {
            return context().getContext(currentContextKey).getDriver().getSessionDetails();
        }
    }


    public class PerformsTouchActions implements io.appium.java_client.PerformsTouchActions {

        @Override public TouchAction performTouchAction(TouchAction touchAction) {
            return context().getContext(currentContextKey).getDriver()
                .performTouchAction(touchAction);
        }

        @Override public void performMultiTouchAction(MultiTouchAction multiAction) {
            context().getContext(currentContextKey).getDriver()
                .performMultiTouchAction(multiAction);
        }
    }

}
