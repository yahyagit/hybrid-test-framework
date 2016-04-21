package com.atanas.kanchev.testframework.sikuli.tests;

import com.atanas.kanchev.testframework.selenium.handlers.IWrapper;
import com.atanas.kanchev.testframework.sikuli.SikuliFactory;
import org.junit.After;
import org.junit.Test;

/**
 * @author Atanas Ksnchev
 */
public class SikuliFactoryTest implements IWrapper {

    @Test
    public void findImageClick() throws Exception {

        goTo("https://www.google.co.uk");
        SikuliFactory sikuliFactory = new SikuliFactory();
        sikuliFactory.findImage("search-field.png");
//        sikuliFactory.click();
//        sikuliFactory.typeOnCentre("hello");
        sikuliFactory.captureImageToLeft("ss.png");


    }

    @After
    public void tearDown() throws Exception {
        context().tearDownContexts();
    }
}
