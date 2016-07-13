package com.atanas.kanchev.testframework.appium.driverfactory.refacto;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by atanas on 13/07/2016.
 */
public class AppiumCapabilitiesFactory {

    private final DesiredCapabilities capabilities;

    public AppiumCapabilitiesFactory() {
        capabilities = new DesiredCapabilities();
    }

    public final AppiumCapabilitiesFactory setAndroidCaps(AndroidMobileCapabilityType cap,
        String value) {
        capabilities.setCapability(String.valueOf(cap), value);
        return this;

    }

    public final AppiumCapabilitiesFactory setIOSCaps(IOSMobileCapabilityType cap,
        String value) {
        capabilities.setCapability(String.valueOf(cap), value);
        return this;

    }

    public final AppiumCapabilitiesFactory setServerCaps(MobileCapabilityType cap,
        String value) {
        capabilities.setCapability(String.valueOf(cap), value);
        return this;

    }

    public DesiredCapabilities getCapabilities() {
        return new DesiredCapabilities(capabilities);
    }
}
