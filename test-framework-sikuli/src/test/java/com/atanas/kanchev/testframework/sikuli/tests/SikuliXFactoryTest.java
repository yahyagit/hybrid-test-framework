package com.atanas.kanchev.testframework.sikuli.tests;

import com.atanas.kanchev.testframework.sikuli.SikuliXFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.sikuli.script.Key;

/**
 * Tests for {@link SikuliXFactory} methods
 *
 * @author Atanas Ksnchev
 */
public class SikuliXFactoryTest {

    private SikuliXFactory sikuliXFactory;
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        driver.get("https://www.google.co.uk");
        sikuliXFactory = new SikuliXFactory("search-field.png");
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
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
        sikuliXFactory.captureImage("image", 0, SikuliXFactory.Directions.CENTER);
        sikuliXFactory.captureImage("image", 22, SikuliXFactory.Directions.LEFT);
        sikuliXFactory.captureImage("image", 1, SikuliXFactory.Directions.RIGHT);

    }

    @Test
    public void type() throws Exception {

        sikuliXFactory.findImage("search-field.png").type("text");

    }

    @Test
    public void type1() throws Exception {

        sikuliXFactory.findImage("search-field.png").type("text", 5, SikuliXFactory.Directions.RIGHT);
    }

    @Test
    public void swipeBetweenImages() throws Exception {

        sikuliXFactory.swipeBetweenImages("search-field.png", "search-button.png");

    }

    @Test
    public void findImageByScrolling() throws Exception {

        driver.get("https://www.rightmove.co.uk");
        sikuliXFactory.findImageByScrolling("rm-select.png", 15, SikuliXFactory.Directions.BELOW);

    }

    @Test
    public void sendKey() throws Exception {

        sikuliXFactory.sendKey(Key.F5);
    }

    @Test
    public void setMinimumSimilarityForImage() throws Exception {

        sikuliXFactory.setMinimumSimilarityForImage(0.5);

    }

}