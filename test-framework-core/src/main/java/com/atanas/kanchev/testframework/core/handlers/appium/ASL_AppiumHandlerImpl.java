package com.atanas.kanchev.testframework.core.handlers.appium;

import com.atanas.kanchev.testframework.appium.driverfactory.AppiumDeviceTypesEnum;
import com.atanas.kanchev.testframework.core.context.AppiumContext;
import com.atanas.kanchev.testframework.core.handlers.wrappers.IAppium;
import com.atanas.kanchev.testframework.core.handlers.wrappers.IContext;
import com.atanas.kanchev.testframework.core.handlers.wrappers.INavigate;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.SwipeElementDirection;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Atanas Ksnchev
 */
public class ASL_AppiumHandlerImpl extends ASL_DeviceMethods implements ASL_IAppiumHandler, IAppium, INavigate, IContext {

    private final static Logger logger = LoggerFactory.getLogger(ASL_AppiumHandlerImpl.class);

    private Map<String, String> appiumContextMap = new HashMap<>();

    @Override
    public ASL_IAppiumHandler startDevice() {
        try {
            ASL_DeviceSetupFromPropFile.setCapabilities();
        } catch (Exception e) {
        }
        try {

            if (ASL_DeviceSetupFromPropFile.getPlatformName().equalsIgnoreCase("Android")) {
                appiumInit()
                        .setupDevice()
                        .setDeviceType(AppiumDeviceTypesEnum.ANDROID_DEVICE)
                        .setPlatformName(ASL_DeviceSetupFromPropFile.getPlatformName())
                        .setPlatformVersion(ASL_DeviceSetupFromPropFile.getPlatFormVersion())
                        .setDeviceName(ASL_DeviceSetupFromPropFile.getDeviceName())
                        .setDeviceUDID(ASL_DeviceSetupFromPropFile.getUdid());
                appiumInit()
                        .setupDeviceServer()
                        .setBrowserName("Chrome")
                        .setFullReset(false)
                        .setAutoLaunch(false);
                ChromeOptions options = new ChromeOptions();
                options.addArguments("disable-translate");
                options.addArguments("disable-popup-blocking");
                options.addArguments("disable-save-password-bubble");
                options.addArguments("enable-automatic-password-saving");
                appiumInit()
                        .setupAndroidDriver()
                        .setChromeOptions(options)
                        .setAndroidDeviceReadyTimeout(60)
                        .setEnablePerformanceLogging(true);
                appiumInit()
                        .initAndroidDriver("http://" + ASL_DeviceSetupFromPropFile.getAppiumServerIP() + ":" + ASL_DeviceSetupFromPropFile.getAppiumServerPort() + "/wd/hub");
            } else if (ASL_DeviceSetupFromPropFile.getPlatformName().equalsIgnoreCase("iOS")) {
                appiumInit()
                        .setupDevice()
                        .setDeviceType(AppiumDeviceTypesEnum.IPHONE_DEVICE)
                        .setPlatformName(ASL_DeviceSetupFromPropFile.getPlatFormVersion())
                        .setPlatformVersion(ASL_DeviceSetupFromPropFile.getPlatFormVersion())
                        .setDeviceName(ASL_DeviceSetupFromPropFile.getDeviceName());
                appiumInit()
                        .setupDeviceServer()
                        .setBrowserName("Safari")
                        .setFullReset(false)
                        .setAutoLaunch(false);
                appiumInit()
                        .initIOSDriver("http://" + ASL_DeviceSetupFromPropFile.getAppiumServerIP() + ":" + ASL_DeviceSetupFromPropFile.getAppiumServerPort() + "/wd/hub");
                try {
                    String[] launchCommand = {"osascript", "-e", "if application \"Simulator\" is running then \n tell application \"Simulator\" to activate\n" +
                            "end if"};
                    Runtime.getRuntime().exec(launchCommand);
                } catch (IOException e) {

                }

            } else {

                Map<String, String> mobileEmulation = new HashMap<>();
                mobileEmulation.put("deviceName", ASL_DeviceSetupFromPropFile.getDeviceName());
                HashMap<String, Object> chromeOptions = new HashMap<>();
                chromeOptions.put("mobileEmulation", mobileEmulation);

                setupSelenium().
                        setBrowser("chrome");
//                        .setCustomCapabilities(chromeOptions)


//                        .setChromeOptions(chromeOptions);

            }
        } catch (UnreachableBrowserException e) {
            logger.error(e.getMessage());

        }
        mobileBrowser = true;

        return this;
    }

    @Override
    public ASL_IAppiumHandler startDevice(String... options) {

        try {
            ASL_DeviceSetupFromPropFile.setCapabilities();
        } catch (Exception e) {

        }
        try {

            if (ASL_DeviceSetupFromPropFile.getPlatformName().equalsIgnoreCase("Android")) {
                appiumInit()
                        .setupDevice()
                        .setDeviceType(AppiumDeviceTypesEnum.ANDROID_DEVICE)
                        .setPlatformName(ASL_DeviceSetupFromPropFile.getPlatformName())
                        .setPlatformVersion(ASL_DeviceSetupFromPropFile.getPlatFormVersion())
                        .setApp("Chrome")
                        .setDeviceName(ASL_DeviceSetupFromPropFile.getDeviceName())
                        .setDeviceUDID(ASL_DeviceSetupFromPropFile.getUdid());

                appiumInit()
                        .setupDeviceServer()
                        .setBrowserName("Chrome")
                        .setFullReset(false)
                        .setAutoLaunch(false);
                ChromeOptions chromeOptions = new ChromeOptions();
                for (int i = 0; i < options.length; i++) {
                    chromeOptions.addArguments(options[i]);
                }
                appiumInit()
                        .setupAndroidDriver()
                        .setChromeOptions(chromeOptions)
                        .setAndroidDeviceReadyTimeout(60)
                        .setEnablePerformanceLogging(true);
                appiumInit().initAndroidDriver("http://" + ASL_DeviceSetupFromPropFile.getAppiumServerIP() + ":" + ASL_DeviceSetupFromPropFile.getAppiumServerPort() + "/wd/hub");
            } else {
                if (ASL_DeviceSetupFromPropFile.getPlatformName().equalsIgnoreCase("iOS")) {
                    appiumInit()
                            .setupDevice()
                            .setDeviceType(AppiumDeviceTypesEnum.IPHONE_DEVICE)
                            .setPlatformName(ASL_DeviceSetupFromPropFile.getPlatFormVersion())
                            .setPlatformVersion(ASL_DeviceSetupFromPropFile.getPlatFormVersion())
                            .setApp("Safari")
                            .setDeviceName(ASL_DeviceSetupFromPropFile.getDeviceName());
                    appiumInit()
                            .setupDeviceServer()
                            .setBrowserName("Safari")
                            .setFullReset(false)
                            .setAutoLaunch(false);
                    appiumInit().initIOSDriver("http://" + ASL_DeviceSetupFromPropFile.getAppiumServerIP() + ":" + ASL_DeviceSetupFromPropFile.getAppiumServerPort() + "/wd/hub");
                } else {

                    Map<String, String> mobileEmulation = new HashMap<>();
                    mobileEmulation.put("deviceName", ASL_DeviceSetupFromPropFile.getDeviceName());
                    HashMap<String, Object> chromeOptions = new HashMap<>();
                    chromeOptions.put("mobileEmulation", mobileEmulation);

                    setupSelenium()
                            .setBrowser("chrome");
//                            .setChromeOptions(chromeOptions);

                }
            }
        } catch (UnreachableBrowserException e) {
            logger.error(e.getMessage());

        }
        mobileBrowser = true;


        return this;
    }

    @Override
    public ASL_IAppiumHandler installApp(String appPath) {
        ((AppiumContext<AndroidDriver>) context().getCurrentContext()).getDriver().installApp(appPath);
        if (((AppiumContext<AndroidDriver>) context().getCurrentContext()).getDriver().isAppInstalled(appPath))
            logger.debug("App installed");
        else
            logger.error("App was not installed");
        return this;
    }

    @Override
    public ASL_IAppiumHandler openApp(String... appArguments) {
        try {
            ASL_DeviceSetupFromPropFile.setCapabilities();
        } catch (Exception e) {

        }
        try {

            if (ASL_DeviceSetupFromPropFile.getPlatformName().equalsIgnoreCase("Android")) {
                appiumInit()
                        .setupDevice()
                        .setDeviceType(AppiumDeviceTypesEnum.ANDROID_DEVICE)
                        .setPlatformName(ASL_DeviceSetupFromPropFile.getPlatformName())
                        .setPlatformVersion(ASL_DeviceSetupFromPropFile.getPlatFormVersion())
                        .setApp(appArguments[0])
                        .setDeviceName(ASL_DeviceSetupFromPropFile.getDeviceName())
                        .setDeviceUDID(ASL_DeviceSetupFromPropFile.getUdid());
                try {
                    appiumInit()
                            .setupAndroidDriver()
                            .setAndroidDeviceReadyTimeout(60)
                            .setAppActivity(appArguments[1]);
                } catch (IndexOutOfBoundsException e) {
                    logger.error("When launching an android app you need to provide the app launch activity as the second argument");

                }
                appiumInit()
                        .initAndroidDriver("http://" + ASL_DeviceSetupFromPropFile.getAppiumServerIP() + ":" + ASL_DeviceSetupFromPropFile.getAppiumServerPort() + "/wd/hub");
            } else {
                appiumInit()
                        .setupDevice()
                        .setDeviceType(AppiumDeviceTypesEnum.IPHONE_DEVICE)
                        .setPlatformName(ASL_DeviceSetupFromPropFile.getPlatFormVersion())
                        .setPlatformVersion(ASL_DeviceSetupFromPropFile.getPlatFormVersion())
                        .setApp(appArguments[0])
                        .setDeviceName(ASL_DeviceSetupFromPropFile.getDeviceName());
                appiumInit()
                        .setupDeviceServer()
                        .setBrowserName("");
                appiumInit().initIOSDriver("http://" + ASL_DeviceSetupFromPropFile.getAppiumServerIP() + ":" + ASL_DeviceSetupFromPropFile.getAppiumServerPort() + "/wd/hub");
                try {
                    String[] launchCommand = {"osascript", "-e", "if application \"Simulator\" is running then \n tell application \"Simulator\" to activate\n" +
                            "end if"};
                    Runtime.getRuntime().exec(launchCommand);
                } catch (IOException e) {
                }
            }
        } catch (UnreachableBrowserException e) {
            logger.error(e.getMessage());

        }
        return this;
    }

    @Override
    public ASL_IAppiumHandler resetApp() {
        ((AppiumContext<AndroidDriver>) context().getCurrentContext()).getDriver().resetApp();
        return this;
    }

    @Override
    public boolean deviceUninstallApp(String packageName) {
        return removeApp((AppiumDriver) driver, packageName);
    }

    @Override
    public boolean deviceLaunchApp() {
        return launchApp((AppiumDriver) driver);
    }

    @Override
    public boolean deviceBackgroundApp(int seconds) {
        return backgroundApp((AppiumDriver) driver, seconds);
    }

    @Override
    public boolean deviceCloseApp() {
        return closeApp((AppiumDriver) driver);
    }


    @Override
    public boolean deviceStoreCurrentContext(String contextName) {
        appiumContextMap.put(contextName, getCurrentContext((AppiumDriver) driver));
        return true;
    }


    @Override
    public boolean deviceSwitchToContextNativeApp() {
        return switchToContextNativeApp((AppiumDriver) driver);
    }

    @Override
    public boolean deviceSwitchToContext(String context) {
        if (appiumContextMap.containsKey(context)) {
            return switchToContext((AppiumDriver) driver, appiumContextMap.get(context));
        } else {
            return switchToContext((AppiumDriver) driver, context);
        }
    }

    public String getCurrentContext() {
        return getCurrentContext((AppiumDriver) driver);
    }


    @Override
    public boolean deviceTap() {
//        if (driver instanceof AppiumDriver) {
//            return tapAndroid((AppiumDriver) driver, currentElement);
//        } else if (driver instanceof ChromeDriver) {
//            return tapAndroid((ChromeDriver) driver, currentElement);
//        } else {
//            return tapAndroid((RemoteWebDriver) driver, currentElement);
//        }

        tapAndroid(((AppiumContext<RemoteWebDriver>) context().getCurrentContext()).getDriver(), ((AppiumContext) context().getCurrentContext()).getCurrentElement());

        return false;
    }

    @Override
    public boolean deviceLongPress() {
//        if (driver instanceof AppiumDriver) {
//            return longPressAndroid((AppiumDriver) driver, currentElement);
//        } else if (driver instanceof ChromeDriver) {
//            return longPressAndroid((ChromeDriver) driver, currentElement);
//        } else {
//            return longPressAndroid((RemoteWebDriver) driver, currentElement);
//        }

        return false;
    }

    @Override
    public boolean deviceSwipeOnElement(String direction, int duration) {

        SwipeElementDirection swipe = null;

        switch (direction.toLowerCase()) {
            case "left":
                swipe = SwipeElementDirection.LEFT;
                break;
            case "right":
                swipe = SwipeElementDirection.RIGHT;
                break;
            case "up":
                swipe = SwipeElementDirection.UP;
                break;
            case "down":
                swipe = SwipeElementDirection.DOWN;
                break;
            default:
                logger.debug("You can only pass up, down, left or right!");
                return false;
        }

//        swipeFromElement((AppiumDriver) driver, currentElement, swipe, duration);
        return true;
    }

    @Override
    public boolean deviceScrollToElementByText(String value) {
//        if (driver instanceof AppiumDriver) {
//            return scrollToElementByText((AppiumDriver) driver, value);
//        } else {
//            return scrollToElementByText(value);
//        }
        return false;
    }

    // Internal Method just to seperate web and device captures
    private boolean deviceCaptureImage(String imageName) {

        return captureImageAppium((AppiumDriver) driver, imageName);
    }

    @Override
    public boolean deviceFindImage(String imagePath) {
        return findImageAppium((AppiumDriver) driver, imagePath);
    }

    @Override
    public boolean deviceTapImage(String imagePath) {
        return tapImageAppium((AppiumDriver) driver, imagePath);
    }

    @Override
    public boolean deviceSetImageWaitTime(int time) {
        return setAppiumImageWaitTime(time);
    }

    @Override
    public boolean deviceWaitForImage(String imagePath) {
        return waitForImageAppium((AppiumDriver) driver, imagePath);
    }

    @Override
    public boolean deviceWaitForImageStoreCoordinates(String imagePath) {
        return waitForImageAndStoreCoordinatesAppium((AppiumDriver) driver, imagePath);
    }

    @Override
    public boolean deviceTapOnStoredCoordinates() {
        return tapOnStoredCoordinatesAppium((AppiumDriver) driver);
    }


    @Override
    public boolean deviceTapOnCoordinates(int x, int y) {
        return tapOnCoordinatesAppium((AppiumDriver) driver, x, y);
    }

    @Override
    public boolean deviceTypeInImage(String imagePath, String text) {
        return typeInImageAppium((AppiumDriver) driver, imagePath, text);
    }

    @Override
    public boolean deviceScrollToImage(String imagePath) {
        return scrollToImageAppium((AppiumDriver) driver, imagePath);
    }

    @Override
    public boolean deviceRotateLandscape() {
        if (driver instanceof AppiumDriver) {
            return rotateDeviceLandscape((AppiumDriver) driver);
        } else {
            logger.warn("Screen Rotation Not Supported when using Chrome");
            return true;
        }

    }

    @Override
    public boolean deviceRotatePortrait() {
        if (driver instanceof AppiumDriver) {
            return rotateDevicePortrait((AppiumDriver) driver);
        } else {
            logger.warn("Screen Rotation Not Supported when using Chrome");
            return true;
        }
    }

    @Override
    public boolean deviceScreenOrientation() {

        screenOrientation((AppiumDriver) driver);
        return true;
    }

    @Override
    public boolean deviceSwipe(int startX, int startY, int endX, int endY, int duration) {

        swipeAndroid((AppiumDriver) driver, startX, startY, endX, endY, duration);
        return true;
    }

    @Override
    public int deviceGetHeight() {
        deviceStoreCurrentContext("currentContext");
        deviceSwitchToContextNativeApp();
        int height = driver.manage().window().getSize().getHeight();
        deviceSwitchToContext("currentContext");
        return height;

    }

    @Override
    public int deviceGetWidth() {
        deviceStoreCurrentContext("currentContext");
        deviceSwitchToContextNativeApp();
        int width = driver.manage().window().getSize().getWidth();
        deviceSwitchToContext("currentContext");
        return width;
    }

}
