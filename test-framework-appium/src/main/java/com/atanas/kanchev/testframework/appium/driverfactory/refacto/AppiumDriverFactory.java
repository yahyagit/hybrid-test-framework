package com.atanas.kanchev.testframework.appium.driverfactory.refacto;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.Capabilities;

import java.net.URL;

/**
 * Created by atanas on 13/07/2016.
 */
public class AppiumDriverFactory extends AppiumCapabilitiesFactory {

    private final URL remoteAddress;
    private final Capabilities desiredCapabilities;

    public AppiumDriverFactory(URL remoteAddress, Capabilities desiredCapabilities) {
        this.remoteAddress = remoteAddress;
        this.desiredCapabilities = desiredCapabilities;
    }

    public IOSDriver<IOSElement> getIOSDriver() {
        return new IOSDriver<>(this.remoteAddress, this.desiredCapabilities);
    }

    public AndroidDriver<AndroidElement> getAndroidDriver(AppiumDriverLocalService service) {
        return new AndroidDriver<>(service, this.desiredCapabilities);
    }
}
