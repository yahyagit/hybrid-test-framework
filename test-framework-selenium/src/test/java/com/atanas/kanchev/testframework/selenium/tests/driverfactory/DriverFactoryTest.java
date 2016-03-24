package com.atanas.kanchev.testframework.selenium.tests.driverfactory;

import com.atanas.kanchev.testframework.selenium.driverfactory.DriverFactory;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

/**
 * Created by atanas on 24/03/2016.
 */
public class DriverFactoryTest {

    @Test
    public void getDriverTest() throws Exception {

        DriverFactory driverFactory = new DriverFactory();
        driverFactory.setSessionName("atanas");


        WebDriver driver = driverFactory.getDriver();
        driver.get("https://www.google.co.uk");


    }
}
