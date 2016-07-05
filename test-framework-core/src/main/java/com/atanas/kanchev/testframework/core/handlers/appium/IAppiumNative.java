package com.atanas.kanchev.testframework.core.handlers.appium;

import com.atanas.kanchev.testframework.core.handlers.wrappers.IContext;
import io.appium.java_client.*;
import io.appium.java_client.android.*;
import org.openqa.selenium.*;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.html5.LocationContext;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Atanas Ksnchev
 */
public class IAppiumNative {

    public class StartsActivityClazz implements IStartsActivity {
    }

    public StartsActivityClazz startActivity() {
        return new StartsActivityClazz();
    }

    public class IRotatableClazz implements IRotatable {
    }

    public IRotatableClazz rotate() {
        return new IRotatableClazz();
    }

    public interface IStartsActivity extends StartsActivity, IContext {

        @Override
        default void startActivity(String appPackage, String appActivity, String appWaitPackage, String appWaitActivity, boolean stopApp) {
            ((AndroidDriver) context().getCurrentContext().getDriver()).startActivity(appPackage, appActivity, appWaitPackage, appWaitActivity, stopApp);
        }

        @Override
        default void startActivity(String appPackage, String appActivity, String appWaitPackage, String appWaitActivity) {
            ((AndroidDriver) context().getCurrentContext().getDriver()).startActivity(appPackage, appActivity, appWaitPackage, appWaitActivity);
        }

        @Override
        default void startActivity(String appPackage, String appActivity) {
            ((AndroidDriver) context().getCurrentContext().getDriver()).startActivity(appPackage, appActivity);
        }

        @Override
        default void startActivity(String appPackage, String appActivity, String appWaitPackage, String appWaitActivity, String intentAction, String intentCategory, String intentFlags, String intentOptionalArgs) {
            ((AndroidDriver) context().getCurrentContext().getDriver()).startActivity(appPackage, appActivity, appWaitPackage, appWaitActivity, intentAction, intentCategory, intentFlags, intentOptionalArgs);
        }

        @Override
        default void startActivity(String appPackage, String appActivity, String appWaitPackage, String appWaitActivity, String intentAction, String intentCategory, String intentFlags, String optionalIntentArguments, boolean stopApp) {
            ((AndroidDriver) context().getCurrentContext().getDriver()).startActivity(appPackage, appActivity, appWaitPackage, appWaitActivity, intentAction, intentCategory, intentFlags, optionalIntentArguments, stopApp);
        }
    }

    interface IPushesFiles extends PushesFiles, IContext {

        @Override
        default void pushFile(String remotePath, byte[] base64Data) {
            ((AndroidDriver) context().getCurrentContext().getDriver()).pushFile(remotePath, base64Data);
        }

        @Override
        default void pushFile(String remotePath, File file) throws IOException {
            ((AndroidDriver) context().getCurrentContext().getDriver()).pushFile(remotePath, file);
        }

        @Override
        default byte[] pullFile(String remotePath) {
            return ((AndroidDriver) context().getCurrentContext().getDriver()).pullFile(remotePath);
        }

        @Override
        default byte[] pullFolder(String remotePath) {
            return ((AndroidDriver) context().getCurrentContext().getDriver()).pullFile(remotePath);
        }
    }

    interface IActionShortcuts extends AndroidDeviceActionShortcuts, IContext {

        @Override
        default void pressKeyCode(int key) {
            ((AndroidDriver) context().getCurrentContext().getDriver()).pressKeyCode(key);
        }

        @Override
        default void pressKeyCode(int key, Integer metastate) {
            ((AndroidDriver) context().getCurrentContext().getDriver()).pressKeyCode(key, metastate);
        }

        @Override
        default void longPressKeyCode(int key) {
            ((AndroidDriver) context().getCurrentContext().getDriver()).longPressKeyCode(key);
        }

        @Override
        default void longPressKeyCode(int key, Integer metastate) {
            ((AndroidDriver) context().getCurrentContext().getDriver()).longPressKeyCode(key, metastate);
        }

        @Override
        default void hideKeyboard() {
            ((AndroidDriver) context().getCurrentContext().getDriver()).hideKeyboard();
        }

        @Override
        default String getDeviceTime() {
            return ((AndroidDriver) context().getCurrentContext().getDriver()).getDeviceTime();
        }
    }

    interface ITouchShortcuts extends TouchShortcuts, IContext {

        @Override
        default void zoom(int x, int y) {
            ((AndroidDriver) context().getCurrentContext().getDriver()).zoom(x, y);
        }

        @Override
        default void zoom(WebElement el) {
            ((AndroidDriver) context().getCurrentContext().getDriver()).zoom(el);
        }

        @Override
        default void tap(int fingers, int x, int y, int duration) {
            ((AndroidDriver) context().getCurrentContext().getDriver()).tap(fingers, x, y, duration);
        }

        @Override
        default void tap(int fingers, WebElement element, int duration) {
            ((AndroidDriver) context().getCurrentContext().getDriver()).tap(fingers, element, duration);
        }

        @Override
        default void swipe(int startx, int starty, int endx, int endy, int duration) {
            ((AndroidDriver) context().getCurrentContext().getDriver()).swipe(startx, starty, endx, endy, duration);
        }

        @Override
        default void pinch(int x, int y) {
            ((AndroidDriver) context().getCurrentContext().getDriver()).pinch(x, y);
        }

        @Override
        default void pinch(WebElement el) {
            ((AndroidDriver) context().getCurrentContext().getDriver()).pinch(el);
        }
    }

    interface IFinds extends FindsByAccessibilityId, FindsByAndroidUIAutomator, IContext {

        @Override
        default WebElement findElementByAccessibilityId(String using) {
            return (WebElement) ((AndroidDriver) context().getCurrentContext().getDriver()).findElementsByAccessibilityId(using);
        }

        @Override
        default List findElementsByAccessibilityId(String using) {
            return ((AndroidDriver) context().getCurrentContext().getDriver()).findElementsByAccessibilityId(using);
        }

        @Override
        default WebElement findElementByAndroidUIAutomator(String using) {
            return ((AndroidDriver) context().getCurrentContext().getDriver()).findElementByAndroidUIAutomator(using);
        }

        @Override
        default List findElementsByAndroidUIAutomator(String using) {
            return ((AndroidDriver) context().getCurrentContext().getDriver()).findElementsByAndroidUIAutomator(using);
        }
    }

    interface IHasAppStrings extends HasAppStrings, IContext {

        @Override
        default Map<String, String> getAppStringMap() {
            return ((AndroidDriver) context().getCurrentContext().getDriver()).getAppStringMap();
        }

        @Override
        default Map<String, String> getAppStringMap(String language) {
            return ((AndroidDriver) context().getCurrentContext().getDriver()).getAppStringMap(language);
        }

        @Override
        default Map<String, String> getAppStringMap(String language, String stringFile) {
            return ((AndroidDriver) context().getCurrentContext().getDriver()).getAppStringMap(language, stringFile);
        }

    }

    interface IInteractsWithApps extends InteractsWithApps, IContext {

        @Override
        default void launchApp() {
            ((AndroidDriver) context().getCurrentContext().getDriver()).launchApp();
        }

        @Override
        default void installApp(String appPath) {
            ((AndroidDriver) context().getCurrentContext().getDriver()).installApp(appPath);
        }

        @Override
        default boolean isAppInstalled(String bundleId) {
            return ((AndroidDriver) context().getCurrentContext().getDriver()).isAppInstalled(bundleId);
        }

        @Override
        default void resetApp() {
            ((AndroidDriver) context().getCurrentContext().getDriver()).resetApp();
        }

        @Override
        default void runAppInBackground(int seconds) {
            ((AndroidDriver) context().getCurrentContext().getDriver()).runAppInBackground(seconds);
        }

        @Override
        default void removeApp(String bundleId) {
            ((AndroidDriver) context().getCurrentContext().getDriver()).resetApp();
        }

        @Override
        default void closeApp() {
            ((AndroidDriver) context().getCurrentContext().getDriver()).closeApp();
        }
    }

    interface ILocationContext extends LocationContext, IContext {

        @Override
        default Location location() {
            return ((AndroidDriver) context().getCurrentContext().getDriver()).location();
        }

        @Override
        default void setLocation(Location location) {
            ((AndroidDriver) context().getCurrentContext().getDriver()).setLocation(location);
        }
    }

    interface IRotatable extends Rotatable, IContext {

        @Override
        default void rotate(ScreenOrientation orientation) {
            ((AndroidDriver) context().getCurrentContext().getDriver()).rotate(orientation);
        }

        @Override
        default ScreenOrientation getOrientation() {
            return ((AndroidDriver) context().getCurrentContext().getDriver()).getOrientation();
        }
    }

    interface IContextAware extends ContextAware, IContext {

        @Override
        default WebDriver context(String name) {
            return ((AndroidDriver) context().getCurrentContext().getDriver()).context(name);
        }

        @Override
        default Set<String> getContextHandles() {
            return ((AndroidDriver) context().getCurrentContext().getDriver()).getContextHandles();
        }

        @Override
        default String getContext() {
            return ((AndroidDriver) context().getCurrentContext().getDriver()).getContext();
        }
    }

    interface IHasNetworkConnection extends HasNetworkConnection, IContext {

        @Override
        default void setConnection(Connection connection) {
            ((AndroidDriver) context().getCurrentContext().getDriver()).setConnection(connection);
        }

        @Override
        default Connection getConnection() {
            return ((AndroidDriver) context().getCurrentContext().getDriver()).getConnection();
        }
    }

    interface IAppiumDriverMethods extends IContext {

        default boolean isLocked() {
            return ((AndroidDriver) context().getCurrentContext().getDriver()).isLocked();
        }

        default void lockDevice() {
            ((AndroidDriver) context().getCurrentContext().getDriver()).lockDevice();
        }

        default void unlockDevice() {
            ((AndroidDriver) context().getCurrentContext().getDriver()).unlockDevice();
        }

        default void toggleLocationServices() {
            ((AndroidDriver) context().getCurrentContext().getDriver()).toggleLocationServices();
        }

        default void openNotifications() {
            ((AndroidDriver) context().getCurrentContext().getDriver()).openNotifications();
        }
    }

}