package com.atanas.kanchev.testframework.selenium.tests;

import com.atanas.kanchev.testframework.selenium.handlers.IWebHandler;
import com.atanas.kanchev.testframework.selenium.handlers.LocatorsFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by atanas on 24/03/2016.
 */
public class IWebHandlerTest implements IWebHandler {

    private static URL url;
    private static final Logger logger = LoggerFactory.getLogger(IWebHandlerTest.class);


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
        tearDownContexts();

    }

    @Test
    public void goToURLTest() throws Exception {
        goToURL(url);
    }

    @Test
    public void refreshTest() throws Exception {
        goToURL(url);
        refresh();
    }

    @Test
    public void methodChainingTest() throws Exception {
        goToURL(url).refresh();
    }

    @Test
    public void loc() throws Exception {
        goToURL(url).findElementBy(LocatorsFactory.XPATH, "//*[@id=\"tsf\"]/div[2]/div[3]/center/input[1]");
    }

    @Test
    public void locs() throws Exception {
        goToURL(url).findElementsBy(LocatorsFactory.TAG_NAME, "tr");
    }


    @Test
    public void findElementByNameTest() throws Exception {
        goToURL(url)
                .findElementBy(LocatorsFactory.XPATH, "w");
    }

    @Test
    public void waitingTest() throws Exception {

        goToURL(url)
                .findElementBy(LocatorsFactory.NAME, "x")
                .waitFor()
                .presenseOfElement(LocatorsFactory.NAME, "lst-ib", 5L);

    }
}