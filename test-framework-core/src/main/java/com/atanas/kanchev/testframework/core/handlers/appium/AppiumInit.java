package com.atanas.kanchev.testframework.core.handlers.appium;

import com.atanas.kanchev.testframework.appium.driverfactory.AppiumCapabilities;
import com.atanas.kanchev.testframework.appium.driverfactory.AppiumDevice;
import com.atanas.kanchev.testframework.appium.driverfactory.DeviceDriverFactory;
import com.atanas.kanchev.testframework.core.context.AppiumContext;
import com.atanas.kanchev.testframework.core.handlers.wrappers.IContext;
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
 * AppiumInit Methods - These methods only work with the AppiumDriver driver,
 * therefore, IWrapper created them on the AppiumMethods class.
 */
public class AppiumInit implements IContext {

    private final static Logger logger = LoggerFactory.getLogger(AppiumInit.class);

    private DeviceDriverFactory createAppiumDriver;

    private static AppiumDriverLocalService service;

    /**
     * Setup AppiumInit Device capabilities
     *
     * @return AppiumDevice instance
     */
    public AppiumDevice setupDevice() {
        if (createAppiumDriver == null)
            this.createAppiumDriver = new DeviceDriverFactory();
        return createAppiumDriver.setDevice();
    }

    /**
     * Setup AppiumInit Device capabilities using appiumDevice instance
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
     * Setup AppiumInit Server capabilities
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
     * @param appiumServerURL AppiumInit server URL String, e.g. "http://127.0.0.1:4723/wd/hub"
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
     * @param appiumServerURL AppiumInit server URL String, e.g. "http://127.0.0.1:4723/wd/hub"
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
                    .usingDriverExecutable(new File("/Applications/AppiumInit.app/Contents/Resources/node/bin/node"))
                    .withAppiumJS(new File("D:\\APPIUM_INIT\\APPIUM_INIT\\build\\lib\\APPIUM_INIT.js"))
                    .withIPAddress("127.0.0.1")
                    .usingAnyFreePort()
                    .withLogFile(new File("target/" + deviceUnderExecution + ".log")));
        } else if (osName.contains("Windows")) {
            service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                    .usingAnyFreePort()
                    .withAppiumJS(new File("D:\\APPIUM_INIT\\APPIUM_INIT\\build\\lib\\main.js"))
                    .withArgument(GeneralServerFlag.LOG_LEVEL, "info")
                    .withLogFile(new File("target/" + deviceUnderExecution + ".log")));
        } else {
            Assert.fail("Unspecified OS found, AppiumInit can't run");
        }

        System.out.println("- - - - - - - - Starting AppiumInit Server- - - - - - - - ");
        service.start();
        if (service == null || !service.isRunning()) {
            throw new RuntimeException("An APPIUM_INIT server node is not started!");
        }
    }

    public void stopAppiumServer() {
        System.out.println("- - - - - - - - Stopping AppiumInit Server- - - - - - - - ");

        if (service != null && service.isRunning())
            try {
                service.stop();
            } catch (Exception e) {
                logger.debug("Error shutting down APPIUM_INIT server " + e.getMessage());
            }

    }

}