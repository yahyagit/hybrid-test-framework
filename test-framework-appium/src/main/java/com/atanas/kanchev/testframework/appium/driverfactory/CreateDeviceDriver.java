package com.atanas.kanchev.testframework.appium.driverfactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;

import java.net.URL;

/**
 * @author Atanas Kanchev Inner class CreateAppiumDriver Handles Appium
 *         iOSDriver and Android driver initalization
 */
public final class CreateDeviceDriver {

    private AppiumDevice device;
    private AppiumCapabilities deviceCapabilities;
    private AppiumCapabilities.AppiumServerCapabilities deviceServerCapabilities;
    private AppiumCapabilities.IOSCapabilities iosCapabilities;
    private AppiumCapabilities.AndroidCapabilities androidCapabilities;
    private URL appiumServerURL;

    /**
     * Constructor
     */
    public CreateDeviceDriver() {
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

    public CreateDeviceDriver setDeviceServerURL(URL appiumServerURL) {
        this.appiumServerURL = appiumServerURL;
        return this;
    }

    //////////////
    // GETTERS //
    /////////////

    public IOSDriver getIOSDriver() {
        return new AppiumDriverInit(device, deviceServerCapabilities, iosCapabilities, appiumServerURL).getIOSDriver();
    }

    public AppiumDriver<AndroidElement> getAndroidDriver() {
        return new AppiumDriverInit(device, deviceServerCapabilities, androidCapabilities, appiumServerURL).getAndroidDriver();
    }

}