package com.atanas.kanchev.testframework.selenium.tests.driverfactory;

import com.atanas.kanchev.testframework.selenium.driverfactory.BrowserConfig;
import com.atanas.kanchev.testframework.selenium.driverfactory.DriverFactory;
import org.junit.Test;

/**
 * Created by atanas on 24/03/2016.
 */
public class DriverFactoryTest {

    @Test
    public void getDriverTest() throws Exception {


        DriverFactory driverFactory = new DriverFactory();
        driverFactory.setSelectedBrowser(BrowserConfig.CHROME);
        driverFactory.getDriver();


    }


}
