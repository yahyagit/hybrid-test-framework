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
        sikuliXFactory = new SikuliXFactory("search-field.png");
    }

    @After
    public void tearDown() throws Exception {
        ContextFactory.tearDownContext();
    }

    @Test
    public void findImage() throws Exception {

        sikuliXFactory.findImage("search-button.png");

    }

    @Test
    public void click() throws Exception {

        sikuliXFactory.click();

    }

    @Test
    public void click1() throws Exception {

        sikuliXFactory.click(SikuliXFactory.Directions.ABOVE, 1);
        sikuliXFactory.click(SikuliXFactory.Directions.BELOW, 333);
        sikuliXFactory.click(SikuliXFactory.Directions.CENTER, 0);
        sikuliXFactory.click(SikuliXFactory.Directions.LEFT, 2);
        sikuliXFactory.click(SikuliXFactory.Directions.RIGHT, 999);

    }

    @Test
    public void doubleClick() throws Exception {

        sikuliXFactory.doubleClick();

    }

    @Test
    public void doubleClick1() throws Exception {

        sikuliXFactory.doubleClick(SikuliXFactory.Directions.ABOVE, 1);
        sikuliXFactory.doubleClick(SikuliXFactory.Directions.BELOW, 333);
        sikuliXFactory.doubleClick(SikuliXFactory.Directions.CENTER, 0);
        sikuliXFactory.doubleClick(SikuliXFactory.Directions.LEFT, 2);
        sikuliXFactory.doubleClick(SikuliXFactory.Directions.RIGHT, 999);

    }

    @Test
    public void captureImage() throws Exception {

        sikuliXFactory.captureImage("image", 101, SikuliXFactory.Directions.ABOVE);
        sikuliXFactory.captureImage("image", 999, SikuliXFactory.Directions.BELOW);
        sikuliXFactory.captureImage("image", 500, SikuliXFactory.Directions.CENTER);
        sikuliXFactory.captureImage("image", 22, SikuliXFactory.Directions.LEFT);
        sikuliXFactory.captureImage("image", 1, SikuliXFactory.Directions.RIGHT);

    }

    @Test
    public void type() throws Exception {

    }

    @Test
    public void type1() throws Exception {

    }

    @Test
    public void swipeBetweenImages() throws Exception {

    }

    @Test
    public void findImageByScrolling() throws Exception {

    }

    @Test
    public void sendKey() throws Exception {

    }

    @Test
    public void setMinimumSimilarityForImage() throws Exception {

    }



}
