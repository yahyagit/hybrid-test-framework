package com.atanas.kanchev.testframework.appium.handlers;

import com.atanas.kanchev.testframework.appium.driverfactory.AppiumCapabilities;
import com.atanas.kanchev.testframework.appium.driverfactory.AppiumDevice;
import com.atanas.kanchev.testframework.appium.driverfactory.DeviceDriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Appium Methods - These methods only work with the AppiumDriver driver,
 * therefore, I created them on the AppiumMethods class. And they will be called
 * from the ASL. This way, only this class needs to be extended to be able to
 * use all the Appium Methods
 */
public class DeviceBasedHandler extends DeviceMethods {

    private final static Logger logger = LoggerFactory.getLogger(DeviceBasedHandler.class);

    private DeviceDriverFactory createAppiumDriver;

    /**
     * Setup Appium Device capabilities
     *
     * @return AppiumDevice instance
     */
    public AppiumDevice setupDevice() {
        if (createAppiumDriver == null)
            this.createAppiumDriver = new DeviceDriverFactory();
        return createAppiumDriver.setDevice();
    }

    /**
     * Setup Appium Device capabilities using appiumDevice instance
     *
     * @param appiumDevice AppiumDevice
     * @return AppiumDevice instance
     */
    public AppiumDevice setupDevice(AppiumDevice appiumDevice) {
        if (createAppiumDriver == null)
            this.createAppiumDriver = new DeviceDriverFactory();
        return createAppiumDriver.setDevice(appiumDevice);
    }

    /**
     * Setup Appium Server capabilities
     *
     * @return AppiumCapabilities.AppiumServerCapabilities instance
     */
    public AppiumCapabilities.AppiumServerCapabilities setupDeviceServer() {
        if (createAppiumDriver == null)
            this.createAppiumDriver = new DeviceDriverFactory();
        return createAppiumDriver.setDeviceServerCapabilities();
    }

    /**
     * Setup IOSDriver capabilities
     *
     * @return AppiumCapabilities.IOSCapabilities instance
     */
    public AppiumCapabilities.IOSCapabilities setupIOSDriver() {
        if (createAppiumDriver == null)
            this.createAppiumDriver = new DeviceDriverFactory();
        return createAppiumDriver.setIOSCapabilities();
    }

    /**
     * Setup AndroidDriver capabilities
     *
     * @return AppiumCapabilities.AndroidCapabilities instance
     */
    public AppiumCapabilities.AndroidCapabilities setupAndroidDriver() {
        if (createAppiumDriver == null)
            this.createAppiumDriver = new DeviceDriverFactory();
        return createAppiumDriver.setAndroidCapabilities();
    }

    /**
     * Create IOSDriver
     *
     * @param appiumServerURL Appium server URL String, e.g. "http://127.0.0.1:4723/wd/hub"
     * @return IOSDriver instance
     */
    public IOSDriver createIOSDriver(String appiumServerURL) {
        try {
            this.createAppiumDriver.setDeviceServerURL(new URL(appiumServerURL));
            return this.createAppiumDriver.getIOSDriver();
        } catch (MalformedURLException e) {
            logger.error(e.getMessage());
        }

        return null;
    }

    /**
     * Create AndroidDriver
     *
     * @param appiumServerURL Appium server URL String, e.g. "http://127.0.0.1:4723/wd/hub"
     * @return AndroidDriver instance
     */
    public AppiumDriver<AndroidElement> createAndroidDriver(String appiumServerURL) {
        try {
            this.createAppiumDriver.setDeviceServerURL(new URL(appiumServerURL));
            return this.createAppiumDriver.getAndroidDriver();
        } catch (MalformedURLException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

//	boolean deviceStart(){
//		try {
//			DeviceHandler.setCapabilities();
//		} catch (Exception e) {
//			return false;
//		}
//		try {
//
//			if (DeviceHandler.getPlatformName().equalsIgnoreCase("Android")) {
//				setupDevice().setDeviceType(AppiumDeviceTypesEnum.ANDROID_DEVICE).setPlatformName(DeviceHandler.getPlatformName())
//						.setPlatformVersion(DeviceHandler.getPlatFormVersion()).setDeviceName(DeviceHandler.getDeviceName()).setDeviceUDID(DeviceHandler.getUdid());
//				setupDeviceServer().setBrowserName("Chrome").setFullReset(false).setAutoLaunch(false);
//				ChromeOptions options = new ChromeOptions();
//				options.addArguments("disable-translate");
//				options.addArguments("disable-popup-blocking");
//				options.addArguments("disable-save-password-bubble");
//				options.addArguments("enable-automatic-password-saving");
//				setupAndroidDriver().setChromeOptions(options).setAndroidDeviceReadyTimeout(60)
//						.setEnablePerformanceLogging(true);
//				createAndroidDriver("http://" + DeviceHandler.getAppiumServerIP() + ":" + DeviceHandler.getAppiumServerPort() + "/wd/hub");
//			} else if (DeviceHandler.getPlatformName().equalsIgnoreCase("iOS")) {
//				setupDevice().setDeviceType(AppiumDeviceTypesEnum.IPHONE_DEVICE).setPlatformName(DeviceHandler.getPlatFormVersion())
//						.setPlatformVersion(DeviceHandler.getPlatFormVersion()).setDeviceName(DeviceHandler.getDeviceName());
//				setupDeviceServer().setBrowserName("Safari").setFullReset(false).setAutoLaunch(false);
//				createIOSDriver("http://" + DeviceHandler.getAppiumServerIP() + ":" + DeviceHandler.getAppiumServerPort() + "/wd/hub");
//				try {
//					String[] launchCommand = {"osascript", "-e", "if application \"Simulator\" is running then \n tell application \"Simulator\" to activate\n" +
//							"end if"};
//					Runtime.getRuntime().exec(launchCommand);
//				} catch (IOException e) {
//
//				}
//
//			} else {
//
//				Map<String, String> mobileEmulation = new HashMap<String, String>();
//				mobileEmulation.put("deviceName", DeviceHandler.getDeviceName());
//				HashMap<String, Object> chromeOptions = new HashMap<String, Object>();
//				chromeOptions.put("mobileEmulation", mobileEmulation);
//
//				createBrowser().setBrowserType(BrowserTypes.CHROME).setChromeOptions(chromeOptions);
//			}
//		} catch (UnreachableBrowserException e) {
//			logger.error(e.getMessage());
//			return false;
//		}
//		mobileBrowser = true;
//		return true;
//	}
//
//	boolean deviceSwitchToContextNativeApp(){
//        return switchToContextNativeApp((AppiumDriver) driver);
//    }
//
//	boolean deviceSwitchToContext(String context){
//        if (AppiumContext.containsKey(context)) {
//            return switchToContext((AppiumDriver) driver, AppiumContext.get(context));
//        } else {
//            return switchToContext((AppiumDriver) driver, context);
//        }
//    }
//
//    public String getCurrentContext() {
//        return getCurrentContext((AppiumDriver) driver);
//    }
//
//	boolean deviceTap(){
//        if (driver instanceof AppiumDriver) {
//            return tapAndroid((AppiumDriver) driver, currentElement);
//        } else if (driver instanceof ChromeDriver) {
//            return tapAndroid((ChromeDriver) driver, currentElement);
//        } else {
//            return tapAndroid((RemoteWebDriver) driver, currentElement);
//        }
//    }
//
//	boolean deviceLongPress(){
//        if (driver instanceof AppiumDriver) {
//            return longPressAndroid((AppiumDriver) driver, currentElement);
//        } else if (driver instanceof ChromeDriver) {
//            return longPressAndroid((ChromeDriver) driver, currentElement);
//        } else {
//            return longPressAndroid((RemoteWebDriver) driver, currentElement);
//        }
//    }
//
//	boolean deviceTapOnCoordinates(int x, int y){
//        return tapOnCoordinatesAppium((AppiumDriver) driver, x, y);
//    }
//
//	boolean deviceScrollToElementByText(String value){
//        if (driver instanceof AppiumDriver) {
//            return scrollToElementByText((AppiumDriver) driver, value);
//        } else {
//            return scrollToElementByText(value);
//        }
//    }
//
//
//	boolean deviceFindImage(String imagePath){
//        return findImageAppium((AppiumDriver) driver, imagePath);
//    }
//
//	/**
//	 * Captures a Screenshot on the Appium Driver (Real Device or Emulator)
//	 * Compares with a given sub image and takes the Coordinates X,Y using
//	 * Sikuli Taps on coordinates X,Y on the Appium Driver
//	 */
//	boolean deviceTapImage(String imagePath){
//        return tapImageAppium((AppiumDriver) driver, imagePath);
//    }
//
//	/**
//	 * Waits for an image and stores the coordinates
//	 */
//
//	boolean deviceWaitForImageStoreCoordinates(String imagePath){
//        return waitForImageAndStoreCoordinatesAppium((AppiumDriver) driver, imagePath);
//    }
//
//	/**
//	 * Taps on the Stored Coordinates
//	 */
//
//	boolean deviceTapOnStoredCoordinates(){
//        return tapOnStoredCoordinatesAppium((AppiumDriver) driver);
//    }
//
//	/**
//	 * This method can be used to control the Wait Time for a particular
//	 * Scenario If this is not used, default Wait Time will be used (Ex: int
//	 * appiumImageWaitTime = 30)
//	 */
//
//
//	boolean deviceSetImageWaitTime(int time){
//        return setAppiumImageWaitTime(time);
//    }
//
//	boolean deviceWaitForImage(String imagePath){
//        return waitForImageAppium((AppiumDriver) driver, imagePath);
//    }
//
//	boolean deviceTypeInImage(String imagePath, String text){
//        return typeInImageAppium((AppiumDriver) driver, imagePath, text);
//    }
//
//	/**
//	 * Method not tested
//	 */
//	boolean deviceScrollToImage(String imagePath){
//        return scrollToImageAppium((AppiumDriver) driver, imagePath);
//    }
//
//	/**
//	 * This method only works on Native Context, use the
//	 * switchToContextNativeApp() method first
//	 */
//	boolean deviceRotateLandscape(){
//        if (driver instanceof AppiumDriver) {
//            return rotateDeviceLandscape((AppiumDriver) driver);
//        } else {
//            logger.warn("Screen Rotation Not Supported when using Chrome");
//            return true;
//        }
//    }
//
//	/**
//	 * This method only works on Native Context, use the
//	 * switchToContextNativeApp() method first
//	 */
//	boolean deviceRotatePortrait(){
//        if (driver instanceof AppiumDriver) {
//            return rotateDevicePortrait((AppiumDriver) driver);
//        } else {
//            logger.warn("Screen Rotation Not Supported when using Chrome");
//            return true;
//        }
//    }
//
//	/**
//	 * This method only works on Native Context, use the
//	 * switchToContextNativeApp() method first
//	 */
//	boolean deviceScreenOrientation(){
//        screenOrientation((AppiumDriver) driver);
//        return true;
//    }
//
//	// Native Apps Only Methods
//
//	/**
//	 * Method not tested
//	 */
//	boolean deviceInstallApp(String appPath){
//        return installAppAppium(appPath);
//    }
//
//	/**
//	 * Swipe Android - Device needs to be on Native App Context Duration in
//	 * milliseconds - Min recommended = 300
//	 * @return
//	 */
//	boolean deviceSwipe(int startX, int startY, int endX, int endY, int duration){
//        swipeAndroid((AppiumDriver) driver, startX, startY, endX, endY, duration);
//        return true;
//    }
//
//	/**
//	 * Gets the Android device's screen height
//	 */
//	int deviceGetHeight(){
//        assertTrue(deviceStoreCurrentContext("currentContext"));
//        assertTrue(deviceSwitchToContextNativeApp());
//        int height = driver.manage().window().getSize().getHeight();
//        assertTrue(deviceSwitchToContext("currentContext"));
//        return height;
//    }
//
//	/**
//	 * Gets the Android device's screen width
//	 */
//	int deviceGetWidth(){
//        assertTrue(deviceStoreCurrentContext("currentContext"));
//        assertTrue(deviceSwitchToContextNativeApp());
//        int width = driver.manage().window().getSize().getWidth();
//        assertTrue(deviceSwitchToContext("currentContext"));
//        return width;
//    }
//
//	boolean deviceStoreCurrentContext(String contextName){
//        AppiumContext.put(contextName, getCurrentContext((AppiumDriver) driver));
//        return true;
//    }
}
