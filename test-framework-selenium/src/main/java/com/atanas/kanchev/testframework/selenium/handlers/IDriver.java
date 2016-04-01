package com.atanas.kanchev.testframework.selenium.handlers;

import com.atanas.kanchev.testframework.selenium.context.AbstractContext;
import com.atanas.kanchev.testframework.selenium.context.WebContext;
import com.atanas.kanchev.testframework.selenium.driverfactory.DriverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by atanaskanchev on 31/03/2016.
 */
public interface IDriver extends IWebHandler{

    // the logger
    Logger logger = LoggerFactory.getLogger(IDriver.class);

    default DriverFactory getDriverFactory(){
        return new DriverFactory();
    }

    default void configureContext(DriverFactory driverFactory){
        AbstractContext context = new WebContext();
        ((WebContext) context).setDriver(driverFactory.getDriver());
        context.addContext(context);
    }


}
