package com.atanas.kanchev.testframework.demo.tests.sikulix;

import com.atanas.kanchev.testframework.commons.wrappers.IContext;
import com.atanas.kanchev.testframework.selenium.wrappers.ISelenium;
import com.atanas.kanchev.testframework.sikuli.handlers.SikuliXFactory;
import com.atanas.kanchev.testframework.sikuli.wrappers.ISikulix;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sikuli.script.Key;

/**
 * Tests demonstrating the usages of SikuliX implementation
 *
 * @author Atanas Ksnchev
 */
public class SikulixTest implements ISelenium, ISikulix, IContext {

    @Before
    public void setUp() throws Exception {

        goTo("https://www.google.co.uk");

    }

    @After
    public void tearDown() throws Exception {
        context().tearDownContexts();

    }

    @Test
    public void findImage() throws Exception {

        sikulix().findImage("search-button.png");

    }

    @Test
    public void click() throws Exception {

        sikulix("search-button.png").click();

    }

    @Test
    public void click1() throws Exception {

        sikulix("search-field.png").click(SikuliXFactory.Directions.ABOVE, 1);
        sikulix("search-field.png").click(SikuliXFactory.Directions.BELOW, 333);
        sikulix("search-field.png").click(SikuliXFactory.Directions.CENTER, 0);
        sikulix("search-field.png").click(SikuliXFactory.Directions.LEFT, 2);
        sikulix("search-field.png").click(SikuliXFactory.Directions.RIGHT, 999);

    }

    @Test
    public void doubleClick() throws Exception {

        sikulix("search-field.png").doubleClick();

    }

    @Test
    public void doubleClick1() throws Exception {

        sikulix("search-field.png").doubleClick(SikuliXFactory.Directions.ABOVE, 1);
        sikulix("search-field.png").doubleClick(SikuliXFactory.Directions.BELOW, 333);
        sikulix("search-field.png").doubleClick(SikuliXFactory.Directions.CENTER, 0);
        sikulix("search-field.png").doubleClick(SikuliXFactory.Directions.LEFT, 2);
        sikulix("search-field.png").doubleClick(SikuliXFactory.Directions.RIGHT, 999);

    }

    @Test
    public void captureImage() throws Exception {

        sikulix("search-field.png").captureImage("image1", 101, SikuliXFactory.Directions.ABOVE);
        sikulix("search-field.png").captureImage("image2", 999, SikuliXFactory.Directions.BELOW);
        sikulix("search-field.png").captureImage("image3", 0, SikuliXFactory.Directions.CENTER);
        sikulix("search-field.png").captureImage("image4", 22, SikuliXFactory.Directions.LEFT);
        sikulix("search-field.png").captureImage("image5", 1, SikuliXFactory.Directions.RIGHT);

    }

    @Test
    public void type() throws Exception {

        sikulix().findImage("search-field.png").type("text");

    }

    @Test
    public void type1() throws Exception {

        sikulix().findImage("search-field.png").type("text", 5, SikuliXFactory.Directions.RIGHT);
    }

    @Test
    public void swipeBetweenImages() throws Exception {

        sikulix().swipeBetweenImages("search-field.png", "search-button.png");

    }

    @Test
    public void findImageByScrolling() throws Exception {

        goTo("https://www.rightmove.co.uk");
        sikulix().findImageByScrolling("rm-select.png", 15, SikuliXFactory.Directions.BELOW);

    }

    @Test
    public void sendKey() throws Exception {

        sikulix("search-field.png").sendKey(Key.F5);
    }

    @Test
    public void setMinimumSimilarityForImage() throws Exception {

        sikulix().setMinimumSimilarityForImage(0.5);

    }
}
