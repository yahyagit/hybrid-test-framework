package com.atanas.kanchev.testframework.appium.driverfactory;

import com.atanas.kanchev.testframework.appium.context.AppiumContext;
import com.atanas.kanchev.testframework.commons.exceptions.CustomExceptions;
import com.atanas.kanchev.testframework.commons.wrappers.IContext;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

/**
 * @author Atanas Kanchev
 *         Appium Driver Factory
 */
public class AppiumDriverFactory extends AppiumLocalServiceBuilder implements IContext {

    private final DesiredCapabilities caps = new DesiredCapabilities();

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

        AndroidDriver<AndroidElement> driver = null;
        try {
            context().getCurrentContext();
        } catch (CustomExceptions.Common.NullArgumentException e) {
            driver = new AndroidDriver<>(service, this.caps);
            AppiumContext<AndroidDriver> context = new AppiumContext<>(driver);
            context().addContext(context);
        }
        return driver;
    }

    public AndroidDriver getAndroidDriverVanilla(URL node) {
        return new AndroidDriver(node, this.caps);
    }
}
