package com.atanas.kanchev.testframework.selenium.driverfactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Root Driver Interface
 */
interface IRootDriver {

    int DEFAULT_IMPLICITLY_WAIT = 5;
    int DEFAULT_PAGE_TIMEOUT = 30;

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

        try (FileInputStream inputStream = new FileInputStream(SELENIUM_PROPS_FILE_PATH)) {
            if (inputStream.available() > 0) {
                SELENIUM_PROPERTIES.load(inputStream);
            } else
                throw new FileNotFoundException("selenium.properties file not found in path " + SELENIUM_PROPS_FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return SELENIUM_PROPERTIES;
    }
}
