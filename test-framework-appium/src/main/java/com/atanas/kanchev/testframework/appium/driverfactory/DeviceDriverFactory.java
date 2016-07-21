package com.atanas.kanchev.testframework.appium.driverfactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;

import java.net.URL;

/**
 * @author Atanas Kanchev Inner class CreateAppiumDriver Handles Appium
 *         iOSDriver and Android driver initalization
 */
@Deprecated
public final class DeviceDriverFactory {

    private AppiumDevice device;
    private AppiumCapabilities deviceCapabilities;
    private AppiumCapabilities.AppiumServerCapabilities deviceServerCapabilities;
    private AppiumCapabilities.IOSCapabilities iosCapabilities;
    private AppiumCapabilities.AndroidCapabilities androidCapabilities;
    private URL appiumServerURL;

    /**
     * Constructor
     */
    public DeviceDriverFactory() {
        this.device = new AppiumDevice();
        this.deviceCapabilities = new AppiumCapabilities();
        this.deviceServerCapabilities = deviceCapabilities.new AppiumServerCapabilities();
        this.iosCapabilities = deviceCapabilities.new IOSCapabilities();
        this.androidCapabilities = deviceCapabilities.new AndroidCapabilities();
    }

    //////////////
    // SETTERS //
    /////////////

    public AppiumDevice setDevice() {
        return this.device;
    }

    public AppiumDevice setDevice(AppiumDevice appiumDevice) {
        return this.device = appiumDevice;
    }

    public AppiumCapabilities.AppiumServerCapabilities setDeviceServerCapabilities() {
        return this.deviceServerCapabilities;
    }

    public AppiumCapabilities.IOSCapabilities setIOSCapabilities() {
        return this.iosCapabilities;
    }

    public AppiumCapabilities.AndroidCapabilities setAndroidCapabilities() {
        return this.androidCapabilities;
    }

    public DeviceDriverFactory setDeviceServerURL(URL appiumServerURL) {
        this.appiumServerURL = appiumServerURL;
        return this;
    }

    //////////////
    // GETTERS //
    /////////////

    public IOSDriver<IOSElement> getIOSDriver() {
        return new AppiumDriverInit(device, deviceServerCapabilities, iosCapabilities, appiumServerURL).getIOSDriver();
    }

    public AndroidDriver<AndroidElement> getAndroidDriver() {
        return new AppiumDriverInit(device, deviceServerCapabilities, androidCapabilities, appiumServerURL).getAndroidDriver();
    }

    public AndroidDriver<AndroidElement> getAndroidDriver(AppiumDriverLocalService service) {
        return new AppiumDriverInit(device, deviceServerCapabilities, androidCapabilities, appiumServerURL).getAndroidDriver(service);
    }

}