package com.atanas.kanchev.testframework.appium.driverfactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import java.net.URL;

/**
 * @author Atanas Kanchev
 *         <p/>
 *         Appium driver initialisation
 */
public final class AppiumDriverInit {

    private final AppiumDevice appiumDevice;
    private final AppiumCapabilities.AppiumServerCapabilities appiumServerCapabilities;
    private AppiumCapabilities.IOSCapabilities iosCapabilities;
    private AppiumCapabilities.AndroidCapabilities androidCapabilities;

    //private MultiThreadedDriverFactory multiThreadedDriverFactory;

    private final URL remoteAddress;

    ///////////////////
    // CONSTRUCTORS //
    //////////////////

    /**
     * Constructor
     * Instantiate iOS driver
     *
     * @param appiumDevice             AppiumDevice
     * @param appiumServerCapabilities ppiumCapabilities.AppiumServerCapabilities
     * @param iosCapabilities          AppiumCapabilities.IOSCapabilities
     * @param remoteAddress            URL
     */
    public AppiumDriverInit(AppiumDevice appiumDevice, AppiumCapabilities.AppiumServerCapabilities appiumServerCapabilities,
                            AppiumCapabilities.IOSCapabilities iosCapabilities, URL remoteAddress) {
        this.appiumDevice = appiumDevice;
        this.appiumServerCapabilities = appiumServerCapabilities;
        this.iosCapabilities = iosCapabilities;
        this.remoteAddress = remoteAddress;
        //this.multiThreadedDriverFactory = new MultiThreadedDriverFactory();
    }

    /**
     * Constructor
     * Instantiate Android driver
     *
     * @param appiumDevice             AppiumDevice
     * @param appiumServerCapabilities AppiumCapabilities.AppiumServerCapabilities
     * @param androidCapabilities      AppiumCapabilities.AndroidCapabilities
     * @param remoteAddress            URL
     */
    public AppiumDriverInit(AppiumDevice appiumDevice, AppiumCapabilities.AppiumServerCapabilities appiumServerCapabilities,
                            AppiumCapabilities.AndroidCapabilities androidCapabilities, URL remoteAddress) {
        this.appiumDevice = appiumDevice;
        this.appiumServerCapabilities = appiumServerCapabilities;
        this.androidCapabilities = androidCapabilities;
        this.remoteAddress = remoteAddress;
        //this.multiThreadedDriverFactory = new MultiThreadedDriverFactory();
    }

    //////////////
    // GETTERS //
    /////////////

    /**
     * Get IOSDriver
     *
     * @return driver
     */
    public final IOSDriver<IOSElement> getIOSDriver() {

        IOSDriver<IOSElement> iosDriver = new AppiumDriverFactory(remoteAddress, new AppiumCapabilitiesFactory(appiumDevice, iosCapabilities, appiumServerCapabilities)
                .getGeneratedCaps()).getIOSDriver();

        //multiThreadedDriverFactory.setMultiThreadedDriver(iosDriver);

        return iosDriver;
    }

    /**
     * Get AndroidDriver
     *
     * @return driver
     */
    public final AndroidDriver<AndroidElement> getAndroidDriver() {

        return new AppiumDriverFactory(remoteAddress, new AppiumCapabilitiesFactory(appiumDevice, androidCapabilities, appiumServerCapabilities)
                .getGeneratedCaps()).getAndroidDriver();
    }
}
