package com.atanas.kanchev.testframework.core.tests.handlers;

import com.atanas.kanchev.testframework.core.handlers.CommonPageDefinitions;
import com.atanas.kanchev.testframework.core.handlers.wrappers.IWrapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

/**
 * Created by atanas on 26/06/2016.
 */
public class ProbeTest implements IWrapper {

    @Before
    public void setUp() throws Exception {
        setupSelenium()
                .setBrowser("chrome");
    }

    @After
    public void tearDown() throws Exception {
        context().tearDownContexts();
    }

    @Test
    public void probeNotExistingElementTest() throws Exception {
        Assert.assertFalse(goTo("https://bbc.co.uk").probe(By.id("non-existent")).exist());
    }

    @Test
    public void probeExistingElementTest() throws Exception {
        Assert.assertTrue(goTo("https://bbc.co.uk").probe(By.linkText("Homepage")).exist());
    }

    @Test
    public void probeHasAnyTextTest() throws Exception {
        Assert.assertTrue(goTo("https://bbc.co.uk").probe(By.linkText("Homepage")).hasAnyText());
    }

    @Test
    public void probeDoesntHaveAnyTextTest() throws Exception {
        Assert.assertFalse(goTo("https://bbc.co.uk").probe(By.id("orb-search-button")).hasAnyText());
    }

    @Test
    public void probeHasTextTest() throws Exception {
        Assert.assertTrue(goTo("https://bbc.co.uk").probe(By.linkText("Homepage")).hasText(false, false, "Home"));
    }

    @Test
    public void probeHasTextCaseSensitiveTest() throws Exception {
        Assert.assertFalse(goTo("https://bbc.co.uk").probe(By.linkText("Homepage")).hasText(true, false, "home"));
    }

    @Test
    public void probeHasPreciseTextCaseSensitiveTest() throws Exception {
        Assert.assertFalse(goTo("https://bbc.co.uk").probe(By.linkText("Homepage")).hasText(true, true, "homepage"));
    }

    @Test
    public void probeHasPreciseAttributeTest() throws Exception {
        Assert.assertTrue(goTo("https://bbc.co.uk").probe(By.id("orb-search-button")).hasAttribute(true, "type", "submit"));
    }

    @Test
    public void probeHasPartialAttributeTest() throws Exception {
        Assert.assertTrue(goTo("https://bbc.co.uk").probe(By.id("orb-search-button")).hasAttribute(false, "type", "sub"));
    }

    @Test
    public void probeNotExistingAttributeTest() throws Exception {
        Assert.assertFalse(goTo("https://bbc.co.uk").probe(By.id("orb-search-button")).hasAttribute(true, "not-existing", "submit"));
    }

    @Test
    public void probeIsOfTypeTest() throws Exception {
        Assert.assertTrue(goTo("https://bbc.co.uk").probe(By.linkText("Homepage")).isOfTagType("a"));
    }

    @Test
    public void probeIsEnabledTest() throws Exception {
        Assert.assertTrue(goTo("https://bbc.co.uk").probe(By.linkText("Homepage")).isEnabled());
    }

    @Test
    public void probeIsSelectedTest() throws Exception {
        Assert.assertTrue(goTo("https://bbc.co.uk").probe(By.linkText("Homepage")).isSelected());
    }

    @Test
    public void probeIsActiveTest() throws Exception {
        Assert.assertFalse(goTo("https://bbc.co.uk").probe(By.linkText("Homepage")).isActive());
    }

    @Test
    public void probeHasColorTest() throws Exception {
        Assert.assertTrue(goTo("https://bbc.co.uk").probe(By.linkText("Homepage")).hasColour(CommonPageDefinitions.CSS.CSS_BACKGROUND_COLOUR, "#000000"));
    }
}