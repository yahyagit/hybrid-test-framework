package com.atanas.kanchev.testframework.appium.driverfactory.refacto;

import com.atanas.kanchev.testframework.commons.properties.PropertyReader;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.SystemUtils;

import java.io.File;

/**
 * @author Atanas Kanchev
 *         Appium Local Service Builder
 */
public class AppiumLocalServiceBuilder {

    static AppiumDriverLocalService service;
    private static final AppiumServiceBuilder builder =
            new AppiumServiceBuilder();

    private static final File LOG_FILE_NAME =
            new File("target/" + RandomStringUtils.randomAlphabetic(10) + ".log");

    private static final String NODE_JS_EXECUTABLE_PATH =
            PropertyReader.getProp("appium.properties", "node.js.executable.path");
    private static final String APPIUM_JS_EXECUTABLE_PATH =
            PropertyReader.getProp("appium.properties", "appium.js.executable.path");
    private static final String APPIUM_JS_EXECUTABLE_LINUX =
            PropertyReader.getProp("appium.properties", "appium.js.executable.linux");

    public static AppiumServiceBuilder configureService() {
        return builder;
    }

    /**
     * Build default Appium local service
     */
    public static void buildDefaultService() {

        System.out.print("- - - - - - - -Building Appium Local Service on ");

        if (SystemUtils.IS_OS_MAC) {
            System.out.println("MacOS- - - - - - - -");
            service = AppiumDriverLocalService.buildService(
                    builder
                            .usingDriverExecutable(new File(NODE_JS_EXECUTABLE_PATH))
                            .withAppiumJS(new File(APPIUM_JS_EXECUTABLE_PATH))
                            .withIPAddress("127.0.0.1")
                            .usingAnyFreePort()
                            .withLogFile(LOG_FILE_NAME));
        } else if (SystemUtils.IS_OS_LINUX) {
            System.out.println("Linux- - - - - - - -");
            service = AppiumDriverLocalService.buildService(
                    builder
                            .usingAnyFreePort()
                            .withAppiumJS(new File(APPIUM_JS_EXECUTABLE_LINUX))
                            .withIPAddress("127.0.0.1")
                            .withLogFile(LOG_FILE_NAME));
        } else if (SystemUtils.IS_OS_WINDOWS) {
            System.out.println("Windows- - - - - - - -");
            service = AppiumDriverLocalService.buildService(
                    builder
                            .usingAnyFreePort()
                            .withAppiumJS(new File(APPIUM_JS_EXECUTABLE_PATH))
                            .withArgument(GeneralServerFlag.LOG_LEVEL, "info")
                            .withLogFile(LOG_FILE_NAME));
        } else {
            throw new RuntimeException("Unspecified OS found, Appium can't run");
        }

    }

    public static void startServer() {

        if (service != null) {
            System.out.println("- - - - - - - - Starting Appium Server - - - - - - - - ");
            service.start();
            if (!service.isRunning()) {
                throw new RuntimeException("An Appium server node is not started!");
            }
        }
    }

    public static void stopServer() {
        System.out.println("- - - - - - - - Stopping Appium Server - - - - - - - - ");

        if (service != null && service.isRunning())
            try {
                service.stop();
            } catch (Exception e) {
                System.out.println("Error shutting down Appium server " + e.getMessage());
            }

    }
}
