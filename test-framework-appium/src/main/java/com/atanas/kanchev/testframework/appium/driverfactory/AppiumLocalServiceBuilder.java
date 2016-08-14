package com.atanas.kanchev.testframework.appium.driverfactory;

import com.atanas.kanchev.testframework.commons.properties.PropertyReader;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.SystemUtils;

import java.io.File;

/**
 * <p>AppiumLocalServiceBuilder class.</p>
 *
 * @author Atanas Kanchev
 * @version 1.0
 */
public class AppiumLocalServiceBuilder {

    static AppiumDriverLocalService service;
    private static final AppiumServiceBuilder builder =
            new AppiumServiceBuilder();

    private static final File LOG_FILE_NAME =
            new File("target/" + RandomStringUtils.randomAlphabetic(10) + ".log");

    private static final String NODE_JS_EXECUTABLE_PATH =
            PropertyReader.getProp("appium.properties", "node.js.executable.mac");
    private static final String APPIUM_JS_EXECUTABLE_PATH =
            PropertyReader.getProp("appium.properties", "appium.js.executable.win");
    private static final String APPIUM_JS_EXECUTABLE_LINUX =
            PropertyReader.getProp("appium.properties", "appium.js.executable.linux");

    /**
     * <p>configureService.</p>
     *
     * @return a {@link io.appium.java_client.service.local.AppiumServiceBuilder} object.
     */
    public AppiumServiceBuilder configureService() {
        return builder;
    }

    /**
     * Build default Appium local service
     *
     * @return a {@link com.atanas.kanchev.testframework.appium.driverfactory.AppiumLocalServiceBuilder} object.
     */
    public AppiumLocalServiceBuilder buildDefaultService() {

        System.out.print("\t *** Building Appium Local Service on ");

        if (SystemUtils.IS_OS_MAC) {
            System.out.println("MacOS ***");
            service = AppiumDriverLocalService.buildService(
                    builder
                            .usingDriverExecutable(new File(NODE_JS_EXECUTABLE_PATH))
                            .withAppiumJS(new File(APPIUM_JS_EXECUTABLE_PATH))
                            .withIPAddress("127.0.0.1")
                            .usingAnyFreePort()
                            .withLogFile(LOG_FILE_NAME));
        } else if (SystemUtils.IS_OS_LINUX) {
            System.out.println("Linux ***");
            service = AppiumDriverLocalService.buildService(
                    builder
                            .usingAnyFreePort()
                            .withAppiumJS(new File(APPIUM_JS_EXECUTABLE_LINUX))
                            .withIPAddress("127.0.0.1")
                            .withLogFile(LOG_FILE_NAME));
        } else if (SystemUtils.IS_OS_WINDOWS) {
            System.out.println("Windows ***");
            service = AppiumDriverLocalService.buildService(
                    builder
                            .usingAnyFreePort()
                            .withAppiumJS(new File(APPIUM_JS_EXECUTABLE_PATH))
                            .withArgument(GeneralServerFlag.LOG_LEVEL, "info")
                            .withLogFile(LOG_FILE_NAME));
        } else {
            throw new RuntimeException("Unspecified OS found, Appium can't run");
        }

        return this;
    }

    /**
     * <p>startServer.</p>
     *
     * @return a {@link com.atanas.kanchev.testframework.appium.driverfactory.AppiumLocalServiceBuilder} object.
     */
    public AppiumLocalServiceBuilder startServer() {

        if (service != null) {
            System.out.println("\t < < < Starting Appium Server > > >");
            service.start();
            if (!service.isRunning()) {
                throw new RuntimeException("An Appium server node is not started!");
            }
        }
        return this;
    }

    /**
     * <p>stopServer.</p>
     *
     * @return a {@link com.atanas.kanchev.testframework.appium.driverfactory.AppiumLocalServiceBuilder} object.
     */
    public AppiumLocalServiceBuilder stopServer() {
        System.out.println("\t\t\t\t> > > Stopping Appium Server < < <");

        if (service != null && service.isRunning())
            try {
                service.stop();
            } catch (Exception e) {
                System.out.println("Error shutting down Appium server " + e.getMessage());
            }

        return this;
    }
}
