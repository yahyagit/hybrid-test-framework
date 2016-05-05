package com.atanas.kanchev.testframework.selenium.driverfactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Root Driver Interface
 */
public interface IRootDriver {

    // the logger
    Logger logger = LoggerFactory.getLogger(IRootDriver.class);

    String OS = System.getProperties().getProperty("os.name");
    String ARCHITECTURE = System.getProperties().getProperty("os.arch");

    ////////////////////
    // Property files //
    ////////////////////

    String SELENIUM_PROPS_FILE_PATH = "./src/test/resources/selenium/selenium.properties";

    /**
     * Method that gives access to the Selenium properties file
     *
     * @return instance of Properties
     */
    static Properties getSeleniumPropFile() {

        Properties SELENIUM_PROPERTIES = new Properties();

        Path seleniumPropsFilePath = Paths.get(SELENIUM_PROPS_FILE_PATH);

        if (Files.notExists(seleniumPropsFilePath))
            throw new RuntimeException("selenium.properties file not found in path " + seleniumPropsFilePath);


        try (FileInputStream inputStream = new FileInputStream(SELENIUM_PROPS_FILE_PATH)) {
            SELENIUM_PROPERTIES.load(inputStream);
        } catch (IOException e) {
            logger.error("Error while reading file: " + e.getMessage());
        }

        return SELENIUM_PROPERTIES;
    }
}
