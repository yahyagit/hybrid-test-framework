package com.atanas.kanchev.testframework.demo.tests.sikulix;

import com.atanas.kanchev.testframework.core.context.ContextFactory;
import com.atanas.kanchev.testframework.core.handlers.IWrapper;
import com.atanas.kanchev.testframework.sikuli.SikuliXFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sikuli.script.Key;

/**
 * Tests demonstrating the usages of SikuliX implementation
 *
 * @author Atanas Ksnchev
 */
public class SikulixTest implements IWrapper {

    @Before
    public void setUp() throws Exception {

        goTo("https://www.google.co.uk");

    }

    @After
    public void tearDown() throws Exception {
       ContextFactory.getCurrentContext().tearDownContext();

    }

    @Test
    public void findImage() throws Exception {

        image().findImage("search-button.png");

    }

    @Test
    public void click() throws Exception {

        image("search-button.png").click();

    }

    @Test
    public void click1() throws Exception {

        image("search-field.png").click(SikuliXFactory.Directions.ABOVE, 1);
        image("search-field.png").click(SikuliXFactory.Directions.BELOW, 333);
        image("search-field.png").click(SikuliXFactory.Directions.CENTER, 0);
        image("search-field.png").click(SikuliXFactory.Directions.LEFT, 2);
        image("search-field.png").click(SikuliXFactory.Directions.RIGHT, 999);

    }

    @Test
    public void doubleClick() throws Exception {

        image("search-field.png").doubleClick();

    }

    @Test
    public void doubleClick1() throws Exception {

        image("search-field.png").doubleClick(SikuliXFactory.Directions.ABOVE, 1);
        image("search-field.png").doubleClick(SikuliXFactory.Directions.BELOW, 333);
        image("search-field.png").doubleClick(SikuliXFactory.Directions.CENTER, 0);
        image("search-field.png").doubleClick(SikuliXFactory.Directions.LEFT, 2);
        image("search-field.png").doubleClick(SikuliXFactory.Directions.RIGHT, 999);

    }

    @Test
    public void captureImage() throws Exception {

        image("search-field.png").captureImage("image", 101, SikuliXFactory.Directions.ABOVE);
        image("search-field.png").captureImage("image", 999, SikuliXFactory.Directions.BELOW);
        image("search-field.png").captureImage("image", 0, SikuliXFactory.Directions.CENTER);
        image("search-field.png").captureImage("image", 22, SikuliXFactory.Directions.LEFT);
        image("search-field.png").captureImage("image", 1, SikuliXFactory.Directions.RIGHT);

    }

    @Test
    public void type() throws Exception {

        image().findImage("search-field.png").type("text");

    }

    @Test
    public void type1() throws Exception {

        image().findImage("search-field.png").type("text", 5, SikuliXFactory.Directions.RIGHT);
    }

    @Test
    public void swipeBetweenImages() throws Exception {

        image().swipeBetweenImages("search-field.png", "search-button.png");

    }

    @Test
    public void findImageByScrolling() throws Exception {

        goTo("https://www.rightmove.co.uk");
        image().findImageByScrolling("rm-select.png", 15, SikuliXFactory.Directions.BELOW);

    }

    @Test
    public void sendKey() throws Exception {

        image("search-field.png").sendKey(Key.F5);
    }

    @Test
    public void setMinimumSimilarityForImage() throws Exception {

        image().setMinimumSimilarityForImage(0.5);

    }
}
