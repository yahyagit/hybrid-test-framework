package com.atanas.kanchev.testframework.appium.driverfactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.Capabilities;

import java.net.URL;

/**
 * @author Atanas Kanchev
 *         Appium driver factory
 */
public final class AppiumDriverFactory {

    private final URL remoteAddress;
    private final Capabilities desiredCapabilities;

    /**
     * Constructor
     *
     * @param remoteAddress       URL
     * @param desiredCapabilities Capabilities
     */
    public AppiumDriverFactory(URL remoteAddress, Capabilities desiredCapabilities) {
        this.remoteAddress = remoteAddress;
        this.desiredCapabilities = desiredCapabilities;
    }

    public IOSDriver getIOSDriver() {
        return new IOSDriver(this.remoteAddress, this.desiredCapabilities);
    }

    public AppiumDriver<AndroidElement> getAndroidDriver() {
        return new AndroidDriver<>(this.remoteAddress, this.desiredCapabilities);
    }
}
