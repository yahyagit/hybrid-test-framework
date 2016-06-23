package com.atanas.kanchev.testframework.selenium.driver_factory;

import org.openqa.selenium.WebDriver;

/**
 * @author Atanas Ksnchev
 */
public class DriverBase {

    public WebDriver getDriver(){
        return new DriverFactory().getDriver();
    }

}
