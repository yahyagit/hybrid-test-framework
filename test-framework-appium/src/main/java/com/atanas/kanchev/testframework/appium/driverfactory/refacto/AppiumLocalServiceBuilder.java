package com.atanas.kanchev.testframework.appium.driverfactory.refacto;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.SystemUtils;

import java.io.File;

/**
 * Created by atanas on 13/07/2016.
 */
public class AppiumLocalServiceBuilder {

    protected static AppiumDriverLocalService service;
//    private static final AppiumServiceBuilder builder = new AppiumServiceBuilder();

    private static final File LOG_FILE_NAME = new File("target/" + RandomStringUtils.randomAlphabetic(10) + ".log");

    public static final String NODE_JS_EXECUTABLE_PATH =
            "/Applications/AppiumInit.app/Contents/Resources/node/bin/node";
    public static final String APPIUM_JS_EXECUTABLE_PATH =
            "D:\\appium\\appium\\build\\lib\\appium.js";

    public static final String APPIUM_JS_EXECUTABLE_LINUX =
            "/opt/appium/build/lib/main.js";

//    public static AppiumServiceBuilder configureService() {
//        return builder;
//    }

    public static void buildService() {

        if (SystemUtils.IS_OS_MAC) {
            System.out.println("Running on MAC");
            service = AppiumDriverLocalService.buildService(
                    new AppiumServiceBuilder().usingDriverExecutable(new File(NODE_JS_EXECUTABLE_PATH))
                            .withAppiumJS(new File(APPIUM_JS_EXECUTABLE_PATH))
                            .withIPAddress("127.0.0.1")
                            .usingAnyFreePort()
                            .withLogFile(LOG_FILE_NAME));
        } else if (SystemUtils.IS_OS_LINUX) {
            System.out.println("Running on LINUX");
            service = AppiumDriverLocalService.buildService(
                    new AppiumServiceBuilder()
                            .usingAnyFreePort()
                            .withAppiumJS(new File(APPIUM_JS_EXECUTABLE_LINUX))
                            .withIPAddress("127.0.0.1")
                            .withLogFile(LOG_FILE_NAME));
        } else if (SystemUtils.IS_OS_WINDOWS) {
            service = AppiumDriverLocalService.buildService(
                    new AppiumServiceBuilder()
                            .usingAnyFreePort()
                            .withAppiumJS(new File(APPIUM_JS_EXECUTABLE_PATH))
                            .withArgument(GeneralServerFlag.LOG_LEVEL, "info")
                            .withLogFile(LOG_FILE_NAME));
        } else {
            throw new RuntimeException("Unspecified OS found, Appium can't run");
        }

    }

    public static void startServer() {

//        service = AppiumDriverLocalService.buildService(builder);

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
