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
 * <p>AppiumDriverFactory class.</p>
 *
 * @author Atanas Kanchev
 * @version 1.0
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
     * @param capabilityValue a {@link java.lang.String} object.
     * @return a {@link com.atanas.kanchev.testframework.appium.driverfactory.AppiumDriverFactory} object.
     */
    public AppiumDriverFactory setCap(final String capabilityType, final Object capabilityValue) {
        caps.setCapability(capabilityType, capabilityValue);
        return this;
    }

    /**
     * <p>Getter for the field <code>caps</code>.</p>
     *
     * @return a {@link org.openqa.selenium.remote.DesiredCapabilities} object.
     */
    public DesiredCapabilities getCaps() {
        return caps;
    }

    /**
     * <p>getIOSDriver.</p>
     *
     * @return a {@link io.appium.java_client.ios.IOSDriver} object.
     */
    public IOSDriver<IOSElement> getIOSDriver() {
        return new IOSDriver<>(service, caps);
    }

    /**
     * <p>getAndroidDriver.</p>
     *
     * @return a {@link io.appium.java_client.android.AndroidDriver} object.
     */
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

    /**
     * <p>getAndroidDriverVanilla.</p>
     *
     * @param node a {@link java.net.URL} object.
     * @return a {@link io.appium.java_client.android.AndroidDriver} object.
     */
    public AndroidDriver getAndroidDriverVanilla(URL node) {
        return new AndroidDriver(node, this.caps);
    }
}
