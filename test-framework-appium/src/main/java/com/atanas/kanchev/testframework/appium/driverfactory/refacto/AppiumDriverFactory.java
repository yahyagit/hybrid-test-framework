package com.atanas.kanchev.testframework.appium.driverfactory.refacto;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by atanas on 13/07/2016.
 */
public class AppiumDriverFactory extends AppiumLocalServiceBuilder {

    private URL remoteAddress;
    private final DesiredCapabilities caps = new DesiredCapabilities();

    public AppiumDriverFactory(URL remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    public AppiumDriverFactory() {
        try {
            this.remoteAddress = new URL("http://"+AppiumServiceBuilder.DEFAULT_LOCAL_IP_ADDRESS.concat(RandomStringUtils.randomNumeric(4)));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

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

    public IOSDriver<IOSElement> getIOSDriver() {
        return new IOSDriver<>(service, this.caps);
    }

    public AndroidDriver<AndroidElement> getAndroidDriver() {
        return new AndroidDriver<>(service, this.caps);
    }
}
