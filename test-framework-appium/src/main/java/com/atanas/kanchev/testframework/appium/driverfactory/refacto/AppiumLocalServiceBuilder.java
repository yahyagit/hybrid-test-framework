package com.atanas.kanchev.testframework.appium.driverfactory.refacto;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.File;

/**
 * Created by atanas on 13/07/2016.
 */
public class AppiumLocalServiceBuilder {

    private static AppiumDriverLocalService service;
    private static AppiumServiceBuilder builder = new AppiumServiceBuilder();

    public static final String NODE_JS_EXECUTABLE_PATH =
        "/Applications/AppiumInit.app/Contents/Resources/node/bin/node";
    public static final String APPIUM_JS_EXECUTABLE_PATH =
        "D:\\appium\\appium\\build\\lib\\appium.js";

    public static AppiumServiceBuilder configureService() {
        return builder;
    }

    public static void buildDefaultService() {

        String deviceUnderExecution = "oneplusone";

        String osName = System.getProperty("os.name");
        if (osName.contains("Mac")) {
            service = AppiumDriverLocalService.buildService(
                builder.usingDriverExecutable(new File(NODE_JS_EXECUTABLE_PATH))
                    .withAppiumJS(new File(APPIUM_JS_EXECUTABLE_PATH)).withIPAddress("127.0.0.1")
                    .usingAnyFreePort()
                    .withLogFile(new File("target/" + deviceUnderExecution + ".log")));
        } else if (osName.contains("Windows")) {
            service = AppiumDriverLocalService.buildService(
                builder.usingAnyFreePort().withAppiumJS(new File(APPIUM_JS_EXECUTABLE_PATH))
                    .withArgument(GeneralServerFlag.LOG_LEVEL, "info")
                    .withLogFile(new File("target/" + deviceUnderExecution + ".log")));
        } else {
            throw new RuntimeException("Unspecified OS found, Appium can't run");
        }

    }

    public void startServer() {

        service = AppiumDriverLocalService.buildService(builder);

        if (service != null && service.isRunning()) {
            System.out.println("- - - - - - - - Starting Appium Server - - - - - - - - ");
            service.start();
            if (!service.isRunning()) {
                throw new RuntimeException("An Appium server node is not started!");
            }
        }
    }

    public void stopServer() {
        System.out.println("- - - - - - - - Stopping Appium Server - - - - - - - - ");

        if (service != null && service.isRunning())
            try {
                service.stop();
            } catch (Exception e) {
                System.out.println("Error shutting down Appium server " + e.getMessage());
            }

    }
}
