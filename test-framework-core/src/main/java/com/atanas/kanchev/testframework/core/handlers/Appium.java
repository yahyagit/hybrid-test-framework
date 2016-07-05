package com.atanas.kanchev.testframework.core.handlers;

import com.atanas.kanchev.testframework.appium.driverfactory.AppiumCapabilities;
import com.atanas.kanchev.testframework.appium.driverfactory.AppiumDevice;
import com.atanas.kanchev.testframework.appium.driverfactory.DeviceDriverFactory;
import com.atanas.kanchev.testframework.core.context.AppiumContext;
import com.atanas.kanchev.testframework.core.handlers.appium.IAppiumNative;
import com.atanas.kanchev.testframework.core.handlers.wrappers.IWrapper;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Appium Methods - These methods only work with the AppiumDriver driver,
 * therefore, IWrapper created them on the AppiumMethods class. And they will be called
 * from the ASL. This way, only this class needs to be extended to be able to
 * use all the Appium Methods
 */
public class Appium implements IWrapper {

    private final static Logger logger = LoggerFactory.getLogger(Appium.class);

    private DeviceDriverFactory createAppiumDriver;

    /**
     * Setup Appium Device capabilities
     *
     * @return AppiumDevice instance
     */
    public AppiumDevice setupDevice() {
        if (createAppiumDriver == null)
            this.createAppiumDriver = new DeviceDriverFactory();
        return createAppiumDriver.setDevice();
    }

    /**
     * Setup Appium Device capabilities using appiumDevice instance
     *
     * @param appiumDevice AppiumDevice
     * @return AppiumDevice instance
     */
    public AppiumDevice setupDevice(AppiumDevice appiumDevice) {
        if (createAppiumDriver == null)
            this.createAppiumDriver = new DeviceDriverFactory();
        return createAppiumDriver.setDevice(appiumDevice);
    }

    /**
     * Setup Appium Server capabilities
     *
     * @return AppiumCapabilities.AppiumServerCapabilities instance
     */
    public AppiumCapabilities.AppiumServerCapabilities setupDeviceServer() {
        if (createAppiumDriver == null)
            this.createAppiumDriver = new DeviceDriverFactory();
        return createAppiumDriver.setDeviceServerCapabilities();
    }

    /**
     * Setup IOSDriver capabilities
     *
     * @return AppiumCapabilities.IOSCapabilities instance
     */
    public AppiumCapabilities.IOSCapabilities setupIOSDriver() {
        if (createAppiumDriver == null)
            this.createAppiumDriver = new DeviceDriverFactory();
        return createAppiumDriver.setIOSCapabilities();
    }

    /**
     * Setup AndroidDriver capabilities
     *
     * @return AppiumCapabilities.AndroidCapabilities instance
     */
    public AppiumCapabilities.AndroidCapabilities setupAndroidDriver() {
        if (createAppiumDriver == null)
            this.createAppiumDriver = new DeviceDriverFactory();
        return createAppiumDriver.setAndroidCapabilities();
    }

    /**
     * Create IOSDriver
     *
     * @param appiumServerURL Appium server URL String, e.g. "http://127.0.0.1:4723/wd/hub"
     * @return IOSDriver instance
     */
    public void initIOSDriver(String appiumServerURL) {
        try {
            this.createAppiumDriver.setDeviceServerURL(new URL(appiumServerURL));
            AppiumContext<IOSDriver<IOSElement>> context = new AppiumContext<>(this.createAppiumDriver.getIOSDriver());
            context().addContext(context);
        } catch (MalformedURLException e) {
            logger.error(e.getMessage());
        }

    }

    /**
     * Create AndroidDriver
     *
     * @param appiumServerURL Appium server URL String, e.g. "http://127.0.0.1:4723/wd/hub"
     * @return AndroidDriver instance
     */
    public void initAndroidDriver(String appiumServerURL) {
        try {
            this.createAppiumDriver.setDeviceServerURL(new URL(appiumServerURL));
            AppiumContext<AndroidDriver<AndroidElement>> context = new AppiumContext<>(this.createAppiumDriver.getAndroidDriver());
            context().addContext(context);
        } catch (MalformedURLException e) {
            logger.error(e.getMessage());
        } catch (WebDriverException ex) {
            logger.error("WebDriverException" + ex.getMessage());
        }
    }



}
