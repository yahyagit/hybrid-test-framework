package com.atanas.kanchev.testframework.selenium.tests;

import com.atanas.kanchev.testframework.selenium.driverfactory.BrowserConfig;
import com.atanas.kanchev.testframework.selenium.driverfactory.DriverFactory;
import com.atanas.kanchev.testframework.selenium.handlers.IWrapper;
import com.atanas.kanchev.testframework.selenium.handlers.Locator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by atanas on 24/03/2016.
 */
public class IWrapperTest implements IWrapper {

    private static URL url;
    private static final Logger logger = LoggerFactory.getLogger(IWrapperTest.class);


    @Before
    public void setUp() throws Exception {

//        DriverFactory driverFactory = new DriverFactory();
//        driverFactory.setSelectedBrowser(BrowserConfig.CHROME);
//
//        AbstractContext context = new WebContext();
//        ((WebContext) context).setDriver(driverFactory.getWebDriverDriver());
//        context.addContext(context);

        try {
            url = new URL("https://www.google.co.uk/");
        } catch (MalformedURLException e) {

        }

    }

    @After
    public void tearDown() throws Exception {
        context().tearDownContexts();

    }

    @Test
    public void goToURLTest() throws Exception {
        goTo().page(url);
    }

    @Test
    public void refreshTest() throws Exception {
        goTo().page(url).
                refresh();
    }

    @Test
    public void methodChainingTest() throws Exception {
        goTo().page(url).refresh();
    }

    @Test
    public void loc() throws Exception {
        goTo().page(url).find().elementBy(Locator.XPATH, "//*[@id=\"tsf\"]/div[2]/div[3]/center/input[1]");
    }

    @Test
    public void locs() throws Exception {
        goTo().page(url).find().elementBy(Locator.TAG_NAME, "tr");
    }

    @Test
    public void multiple() throws Exception {
        goTo().page(url);
        goTo().page(new URL("HTTPS://BBC.CO.UK"));
    }


    @Test
    public void findElementByNameTest() throws Exception {
        goTo().page(url).
                find().elementBy(Locator.XPATH, "w");
    }

    @Test
    public void waitingTest() throws Exception {

        goTo().page(url)
        .find().elementBy(Locator.NAME, "x");
        waitFor()
                .presenceOfElement(Locator.NAME, "lst-ib", 5L);

    }

    @Test
    public void probeEl() throws Exception {


        Assert.assertTrue(goTo().page(url).find().elementBy(Locator.XPATH, "//*[@id=\"tsf\"]/div[2]/div[3]/center/input[1]").probe().isOfTagType("input"));


    }

    @Test
    public void driver() throws Exception {
        DriverFactory driverFactory = setup().getDriverFactory();
        driverFactory.setSelectedBrowser(BrowserConfig.CHROME);
        setup().configureContext(driverFactory);
        goTo().page(url);
    }
}