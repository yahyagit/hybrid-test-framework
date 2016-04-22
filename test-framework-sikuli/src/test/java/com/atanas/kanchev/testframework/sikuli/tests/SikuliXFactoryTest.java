package com.atanas.kanchev.testframework.sikuli.tests;

import com.atanas.kanchev.testframework.selenium.context.ContextFactory;
import com.atanas.kanchev.testframework.selenium.handlers.IWrapper;
import com.atanas.kanchev.testframework.sikuli.SikuliXFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Atanas Ksnchev
 */
public class SikuliXFactoryTest implements IWrapper {

    private SikuliXFactory sikuliXFactory;

    @Before
    public void setUp() throws Exception {
        goTo("https://www.google.co.uk");
        sikuliXFactory = new SikuliXFactory();
    }

    @After
    public void tearDown() throws Exception {
        ContextFactory.tearDownContext();
    }

    @Test
    public void click() throws Exception {

    }

    @Test
    public void click1() throws Exception {

    }

    @Test
    public void doubleClick() throws Exception {

    }

    @Test
    public void doubleClick1() throws Exception {

    }

    @Test
    public void findImage() throws Exception {

    }

    @Test
    public void inputText() throws Exception {

    }

    @Test
    public void captureImage() throws Exception {

    }

    @Test
    public void type() throws Exception {

    }

    @Test
    public void swipeBetweenImages() throws Exception {

    }

    @Test
    public void findImageByScrolling() throws Exception {

    }

    @Test
    public void press() throws Exception {

    }

    @Test
    public void setMinimumSimilarityForImage() throws Exception {

    }


}
