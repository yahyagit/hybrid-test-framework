package com.atanas.kanchev.testframework.selenium.tests;

import com.atanas.kanchev.testframework.selenium.driverfactory.BrowserConfig;
import com.atanas.kanchev.testframework.selenium.driverfactory.DriverFactory;
import com.atanas.kanchev.testframework.selenium.handlers.IWrapper;
import com.atanas.kanchev.testframework.selenium.handlers.Locator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by atanas on 24/03/2016.
 */
public class IWrapperTest implements IWrapper {

    private static String url = "https://www.google.co.uk/";
    private static final Logger logger = LoggerFactory.getLogger(IWrapperTest.class);

    @After
    public void tearDown() throws Exception {
        context().tearDownContexts();

    }

    @Test
    public void goToURLTest() throws Exception {
        goTo(url);
    }

    @Test
    public void refreshTest() throws Exception {
        goTo(url).
                refresh();
    }

    @Test
    public void methodChainingTest() throws Exception {
        goTo(url).refresh();
    }

    @Test
    public void loc() throws Exception {
        goTo(url).find().elementBy(Locator.XPATH, "//*[@id=\"tsf\"]/div[2]/div[3]/center/input[1]");
    }

    @Test
    public void locs() throws Exception {
        goTo(url).find().elementBy(Locator.TAG_NAME, "tr");
    }

    @Test
    public void multiple() throws Exception {
        goTo(url);
        goTo("HTTPS://BBC.CO.UK");
    }


    @Test
    public void findElementByNameTest() throws Exception {
        goTo(url).
                find().elementBy(Locator.XPATH, "w");
    }

    @Test
    public void waitingTest() throws Exception {

        goTo(url)
                .find().elementBy(Locator.NAME, "x");
        waitFor()
                .presenceOfElement(Locator.NAME, "lst-ib", 5L);

    }

    @Test
    public void probeEl() throws Exception {

        Assert.assertTrue(
                goTo(url)
                        .probe(Locator.XPATH, "//*[@id=\"tsf\"]/div[2]/div[3]/center/input[1]")
                        .exist());

    }

    @Test
    public void driver() throws Exception {
        DriverFactory driverFactory = setup().configureBrowser();
        driverFactory.setSelectedBrowser(BrowserConfig.CHROME);
        setup().configureContext(driverFactory);
        goTo(url);
    }

    @Test
    public void prop() throws Exception {
//        System.setProperty("env", "dev");
//        System.out.println(new PropertyReader().getValidProperty("url"));
       // System.out.println(new PropertyReader("dev.env.properties").getProperty("url"));
        System.out.println(new BigInteger(130, new SecureRandom()).toString(32));

    }

    @Test
    public void conf() throws Exception {


    }
}