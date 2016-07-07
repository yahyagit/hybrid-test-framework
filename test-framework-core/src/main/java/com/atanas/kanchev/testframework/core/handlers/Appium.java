package com.atanas.kanchev.testframework.core.handlers;

import com.atanas.kanchev.testframework.appium.driverfactory.AppiumCapabilities;
import com.atanas.kanchev.testframework.appium.driverfactory.AppiumDevice;
import com.atanas.kanchev.testframework.appium.driverfactory.DeviceDriverFactory;
import com.atanas.kanchev.testframework.core.context.AppiumContext;
import com.atanas.kanchev.testframework.core.handlers.wrappers.IWrapper;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import junit.framework.Assert;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
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

    private static AppiumDriverLocalService service;

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
            AppiumContext<AndroidDriver<AndroidElement>> context = null;
            if (service != null)
                context = new AppiumContext<>(this.createAppiumDriver.getAndroidDriver(service));
            else
                context = new AppiumContext<>(this.createAppiumDriver.getAndroidDriver());
            context().addContext(context);
        } catch (MalformedURLException e) {
            logger.error(e.getMessage());
        } catch (WebDriverException ex) {
            logger.error("WebDriverException" + ex.getMessage());
        }
    }

    public void startAppiumServer() {
        String deviceUnderExecution = "oneplusone";

        String osName = System.getProperty("os.name");
        if (osName.contains("Mac")) {
            service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                    .usingDriverExecutable(new File("/Applications/Appium.app/Contents/Resources/node/bin/node"))
                    .withAppiumJS(new File("D:\\appium\\appium\\build\\lib\\appium.js"))
                    .withIPAddress("127.0.0.1")
                    .usingAnyFreePort()
                    .withLogFile(new File("target/" + deviceUnderExecution + ".log")));
        } else if (osName.contains("Windows")) {
            service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                    .usingAnyFreePort()
                    .withAppiumJS(new File("D:\\appium\\appium\\build\\lib\\main.js"))
                    .withArgument(GeneralServerFlag.LOG_LEVEL, "info")
                    .withLogFile(new File("target/" + deviceUnderExecution + ".log")));
        } else {
            Assert.fail("Unspecified OS found, Appium can't run");
        }

        System.out.println("- - - - - - - - Starting Appium Server- - - - - - - - ");
        service.start();
        if (service == null || !service.isRunning()) {
            throw new RuntimeException("An appium server node is not started!");
        }
    }

    public void stopAppiumServer() {
        System.out.println("- - - - - - - - Stopping Appium Server- - - - - - - - ");

        if (service != null && service.isRunning()) {
            service.stop();

        } else throw new RuntimeException("An appium server node is not started!");
    }


}