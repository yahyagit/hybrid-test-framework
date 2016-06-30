package com.atanas.kanchev.testframework.core.handlers.appium;

import io.appium.java_client.AppiumDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Atanas Ksnchev
 */
public class AppiumImpl extends AppiumNativeMethods implements IAppium {

    private final static Logger logger = LoggerFactory.getLogger(AppiumImpl.class);

    @Override
    public IAppium deviceStart() {
//        try {
//            DeviceHandler.setCapabilities();
//        } catch (Exception e) {
//            return false;
//        }
//        try {
//
//            if (DeviceHandler.getPlatformName().equalsIgnoreCase("Android")) {
//                setupDevice().setDeviceType(AppiumDeviceTypesEnum.ANDROID_DEVICE).setPlatformName(DeviceHandler.getPlatformName())
//                        .setPlatformVersion(DeviceHandler.getPlatFormVersion()).setDeviceName(DeviceHandler.getDeviceName()).setDeviceUDID(DeviceHandler.getUdid());
//                setupDeviceServer().setBrowserName("Chrome").setFullReset(false).setAutoLaunch(false);
//                ChromeOptions options = new ChromeOptions();
//                options.addArguments("disable-translate");
//                options.addArguments("disable-popup-blocking");
//                options.addArguments("disable-save-password-bubble");
//                options.addArguments("enable-automatic-password-saving");
//                setupAndroidDriver().setChromeOptions(options).setAndroidDeviceReadyTimeout(60)
//                        .setEnablePerformanceLogging(true);
//                createAndroidDriver("http://" + DeviceHandler.getAppiumServerIP() + ":" + DeviceHandler.getAppiumServerPort() + "/wd/hub");
//            } else if (DeviceHandler.getPlatformName().equalsIgnoreCase("iOS")) {
//                setupDevice().setDeviceType(AppiumDeviceTypesEnum.IPHONE_DEVICE).setPlatformName(DeviceHandler.getPlatFormVersion())
//                        .setPlatformVersion(DeviceHandler.getPlatFormVersion()).setDeviceName(DeviceHandler.getDeviceName());
//                setupDeviceServer().setBrowserName("Safari").setFullReset(false).setAutoLaunch(false);
//                createIOSDriver("http://" + DeviceHandler.getAppiumServerIP() + ":" + DeviceHandler.getAppiumServerPort() + "/wd/hub");
//                try {
//                    String[] launchCommand = {"osascript", "-e", "if application \"Simulator\" is running then \n tell application \"Simulator\" to activate\n" +
//                            "end if"};
//                    Runtime.getRuntime().exec(launchCommand);
//                } catch (IOException e) {
//
//                }
//
//            } else {
//
//                Map<String, String> mobileEmulation = new HashMap<String, String>();
//                mobileEmulation.put("deviceName", DeviceHandler.getDeviceName());
//                HashMap<String, Object> chromeOptions = new HashMap<String, Object>();
//                chromeOptions.put("mobileEmulation", mobileEmulation);
//
//                createBrowser().setBrowserType(BrowserTypes.CHROME).setChromeOptions(chromeOptions);
//            }
//        } catch (UnreachableBrowserException e) {
//            logger.error(e.getMessage());
//            return false;
//        }
//        mobileBrowser = true;
        return this;
    }

    @Override
    public IAppium deviceSwitchToContextNativeApp() {
//        switchToContextNativeApp((AppiumDriver) driver);
        return null;
    }

    @Override
    public IAppium deviceSwitchToContext(String context) {
//        if (AppiumContext.containsKey(context)) {
//            return appiumMethods.switchToContext((AppiumDriver) driver, AppiumContext.get(context));
//        } else {
//            return appiumMethods.switchToContext((AppiumDriver) driver, context);
//        }
        return null;
    }

    @Override
    public IAppium deviceTap() {
//        if (driver instanceof AppiumDriver) {
//            return appiumMethods.tapAndroid((AppiumDriver) driver, currentElement);
//        } else if (driver instanceof ChromeDriver) {
//            return appiumMethods.tapAndroid((ChromeDriver) driver, currentElement);
//        } else {
//            return appiumMethods.tapAndroid((RemoteWebDriver) driver, currentElement);
//        }
        return null;
    }

    @Override
    public IAppium deviceLongPress() {
//        if (driver instanceof AppiumDriver) {
//            return appiumMethods.longPressAndroid((AppiumDriver) driver, currentElement);
//        } else if (driver instanceof ChromeDriver) {
//            return appiumMethods.longPressAndroid((ChromeDriver) driver, currentElement);
//        } else {
//            return appiumMethods.longPressAndroid((RemoteWebDriver) driver, currentElement);
//        }
        return null;
    }

    @Override
    public IAppium deviceTapOnCoordinates(int x, int y) {
//        tapOnCoordinatesAppium((AppiumDriver) driver, x, y);
        return null;
    }

    @Override
    public IAppium deviceScrollToElementByText(String value) {
//        if (driver instanceof AppiumDriver) {
//            return scrollToElementByText((AppiumDriver) driver, value);
//        } else {
//            return scrollToElementByText(value);
//        }
        return null;
    }

    @Override
    public IAppium deviceFindImage(String imagePath) {
//        findImageAppium((AppiumDriver) driver, imagePath);
        return null;
    }

    @Override
    public IAppium deviceTapImage(String imagePath) {
//        tapImageAppium((AppiumDriver) driver, imagePath);
        return null;
    }

    @Override
    public IAppium deviceWaitForImageStoreCoordinates(String imagePath) {
//        waitForImageAndStoreCoordinatesAppium((AppiumDriver) driver, imagePath);
        return null;
    }

    @Override
    public IAppium deviceTapOnStoredCoordinates() {
//        tapOnStoredCoordinatesAppium((AppiumDriver) driver);
        return null;
    }

    @Override
    public IAppium deviceSetImageWaitTime(int time) {
        setAppiumImageWaitTime(time);
        return null;
    }

    @Override
    public IAppium deviceWaitForImage(String imagePath) {
//        waitForImageAppium((AppiumDriver) driver, imagePath);
        return null;
    }

    @Override
    public IAppium deviceTypeInImage(String imagePath, String text) {
        typeInImageAppium((AppiumDriver) driver, imagePath, text);
        return null;
    }

    @Override
    public IAppium deviceScrollToImage(String imagePath) {
        scrollToImageAppium((AppiumDriver) driver, imagePath);
        return null;
    }

    @Override
    public IAppium deviceRotateLandscape() {
        if (driver instanceof AppiumDriver) {
            rotateDeviceLandscape((AppiumDriver) driver);
        } else {
            logger.warn("Screen Rotation Not Supported when using Chrome");

        }
        return null;
    }

    @Override
    public IAppium deviceRotatePortrait() {
        if (driver instanceof AppiumDriver) {
            rotateDevicePortrait((AppiumDriver) driver);
        } else {
            logger.warn("Screen Rotation Not Supported when using Chrome");

        }
        return null;
    }

    @Override
    public IAppium deviceScreenOrientation() {
        screenOrientation((AppiumDriver) driver);
        return null;
    }

    @Override
    public IAppium deviceInstallApp(String appPath) {
        installAppAppium(appPath);
        return null;
    }

    @Override
    public IAppium deviceSwipe(int startX, int startY, int endX, int endY, int duration) {
        swipeAndroid((AppiumDriver) driver, startX, startY, endX, endY, duration);
        return null;
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
        return 0;
    }

    @Override
    public IAppium deviceStoreCurrentContext(String contextName) {
        deviceStoreCurrentContext("currentContext");
        deviceSwitchToContextNativeApp();
        int width = driver.manage().window().getSize().getWidth();
        deviceSwitchToContext("currentContext");

        return null;
    }

}