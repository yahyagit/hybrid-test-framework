package com.atanas.kanchev.testframework.core.handlers.appium;

/**
 * Appium Methods - These methods only work with the AppiumDriver driver
 *
 * @author Atanas Ksnchev
 */
public interface IAppium {

    IAppium deviceStart();

    IAppium deviceSwitchToContextNativeApp();

    IAppium deviceSwitchToContext(String context);

    IAppium deviceTap();

    IAppium deviceLongPress();

    IAppium deviceTapOnCoordinates(int x, int y);

    IAppium deviceScrollToElementByText(String value);

    IAppium deviceFindImage(String imagePath);

    /**
     * Captures a Screenshot on the Appium Driver (Real Device or Emulator)
     * Compares with a given sub image and takes the Coordinates X,Y using
     * Sikuli Taps on coordinates X,Y on the Appium Driver
     */
    IAppium deviceTapImage(String imagePath);

    /**
     * Waits for an image and stores the coordinates
     */
    IAppium deviceWaitForImageStoreCoordinates(String imagePath);

    /**
     * Taps on the Stored Coordinates
     */
    IAppium deviceTapOnStoredCoordinates();

    /**
     * This method can be used to control the Wait Time for a particular
     * Scenario If this is not used, default Wait Time will be used (Ex: int
     * appiumImageWaitTime = 30)
     */
    IAppium deviceSetImageWaitTime(int time);

    IAppium deviceWaitForImage(String imagePath);

    IAppium deviceTypeInImage(String imagePath, String text);

    /**
     * Method not tested
     */
    IAppium deviceScrollToImage(String imagePath);

    /**
     * This method only works on Native Context, use the
     * switchToContextNativeApp() method first
     */
    IAppium deviceRotateLandscape();

    /**
     * This method only works on Native Context, use the
     * switchToContextNativeApp() method first
     */
    IAppium deviceRotatePortrait();

    /**
     * This method only works on Native Context, use the
     * switchToContextNativeApp() method first
     */
    IAppium deviceScreenOrientation();

    // Native Apps Only Methods

    /**
     * Method not tested
     */
    IAppium deviceInstallApp(String appPath);

    /**
     * Swipe Android - Device needs to be on Native App Context Duration in
     * milliseconds - Min recommended = 300
     *
     * @return
     */
    IAppium deviceSwipe(int startX, int startY, int endX, int endY, int duration);

    /**
     * Gets the Android device's screen height
     */
    int deviceGetHeight();

    /**
     * Gets the Android device's screen width
     */
    int deviceGetWidth();

    IAppium deviceStoreCurrentContext(String contextName);
}
