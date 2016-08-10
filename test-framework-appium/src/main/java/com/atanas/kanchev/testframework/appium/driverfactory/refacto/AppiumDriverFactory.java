package com.atanas.kanchev.testframework.appium.driverfactory.refacto;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * @author Atanas Kanchev
 *         Appium Driver Factory
 */
public class AppiumDriverFactory extends AppiumLocalServiceBuilder {

    private final DesiredCapabilities caps = DesiredCapabilities.android();

    /**
     * Set an Appium  compatibility
     *
     * @param capabilityType  Compatibility type to set. Value from:
     *                        Android compatibility {@link io.appium.java_client.remote.AndroidMobileCapabilityType}
     *                        IOS compatibility {@link io.appium.java_client.remote.IOSMobileCapabilityType}
     *                        Appium server capabilities {@link io.appium.java_client.remote.MobileCapabilityType}
     * @param capabilityValue Compatibility value
     */
    public AppiumDriverFactory setCap(final String capabilityType, final Object capabilityValue) {
        caps.setCapability(capabilityType, capabilityValue);
        return this;
    }

    public DesiredCapabilities getCaps() {
        return caps;
    }

    public IOSDriver<IOSElement> getIOSDriver() {
        return new IOSDriver<>(service, caps);
    }

    public AndroidDriver<AndroidElement> getAndroidDriver() {
        return new AndroidDriver<>(service, this.caps);
    }
}
