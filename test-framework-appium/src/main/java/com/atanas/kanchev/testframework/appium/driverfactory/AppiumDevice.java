package com.atanas.kanchev.testframework.appium.driverfactory;

/**
 * @author Atanas Kanchev
 *         Appium device capabilities pojo
 */
public final class AppiumDevice {

    private AppiumDeviceTypesEnum deviceType;
    private String platformName;
    private String platformVersion;
    private String deviceName;
    private String deviceUDID;
    private String app;

    //////////////
    // SETTERS //
    /////////////

    public AppiumDevice setDeviceType(AppiumDeviceTypesEnum deviceType) {
        this.deviceType = deviceType;
        return this;
    }

    /**
     * Which mobile OS platform to use	iOS, Android, or FirefoxOS
     */
    public AppiumDevice setPlatformName(String platformName) {
        this.platformName = platformName;
        return this;
    }

    /**
     * Mobile OS version	e.g., 7.1, 4.4
     */
    public AppiumDevice setPlatformVersion(String platformVersion) {
        this.platformVersion = platformVersion;
        return this;
    }

    /**
     * The kind of mobile device or emulator to use	iPhone Simulator, iPad Simulator, iPhone Retina 4-inch,
     * Android Emulator, Galaxy S4, etc…. On iOS, this should be one of the valid devices returned by instruments with
     * instruments -s devices. On Android this capability is currently ignored.
     */
    public AppiumDevice setDeviceName(String deviceName) {
        this.deviceName = deviceName;
        return this;
    }

    /**
     * Unique device identifier of the connected physical device
     * e.g. 1ae203187fc012g
     */
    public AppiumDevice setDeviceUDID(String deviceUDID) {
        this.deviceUDID = deviceUDID;
        return this;
    }

    /**
     * The absolute local path or remote http URL to an .ipa or .apk file, or a .zip containing one of these.
     * Appium will attempt to install this app binary on the appropriate device first.
     * Note that this capability is not required for Android if you specify appPackage and appActivity capabilities (see below).
     * Incompatible with browserName.
     * e.g. /abs/path/to/my.apk or http://myapp.com/app.ipa
     */
    public AppiumDevice setApp(String app) {
        this.app = app;
        return this;
    }

    /**
     * Set appium device from AppiumDevice instance
     *
     * @param appimDevice AppiumDevice instance
     */
    public void setAppimDevice(AppiumDevice appimDevice) {
        this.deviceType = appimDevice.getDeviceType();
        this.deviceName = appimDevice.getDeviceName();
        this.app = appimDevice.getApp();
        this.platformName = appimDevice.getPlatformName();
        this.platformVersion = appimDevice.getPlatformVersion();
        this.deviceUDID = appimDevice.getDeviceUDID();
    }

    //////////////
    // GETTERS //
    /////////////

    AppiumDeviceTypesEnum getDeviceType() {
        return deviceType;
    }

    String getPlatformName() {
        return platformName;
    }

    String getPlatformVersion() {
        return platformVersion;
    }

    String getDeviceName() {
        return deviceName;
    }

    String getDeviceUDID() {
        return deviceUDID;
    }

    String getApp() {
        return app;
    }
}
