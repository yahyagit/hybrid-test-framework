package com.atanas.kanchev.testframework.selenium.tests;

import com.atanas.kanchev.testframework.selenium.context.AbstractContext;
import com.atanas.kanchev.testframework.selenium.context.WebContext;
import com.atanas.kanchev.testframework.selenium.handlers.IWebHandler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

import static com.atanas.kanchev.testframework.selenium.context.ContextFactory.addContext;

/**
 * Created by atanas on 24/03/2016.
 */
public class IWebHandlerTest implements IWebHandler {

    private static URL url;
    private static final Logger logger = LoggerFactory.getLogger(IWebHandlerTest.class);


    @Before
    public void setUp() throws Exception {
        WebDriver driver = new FirefoxDriver();
        AbstractContext context = new WebContext();
        ((WebContext) context).setDriver(driver);
        addContext(context);
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
    public void findElementByNameTest() throws Exception {
        goToURL(url)
                .findElementByName("w");
    }
}