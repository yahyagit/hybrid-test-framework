package com.atanas.kanchev.testframework.core.tests.handlers;

import com.atanas.kanchev.testframework.core.handlers.Finder;
import com.atanas.kanchev.testframework.core.handlers.IWrapper;
import com.atanas.kanchev.testframework.core.handlers.Locator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Atanas Ksnchev
 */
public class FinderTest implements IWrapper {

    @Mock
    Finder ifind;

    private static URL url;
    private static final Logger logger = LoggerFactory.getLogger(FinderTest.class);

    @Before
    public void setUp() throws Exception {
        try {
            url = new URL("https://www.google.co.uk/");
        } catch (MalformedURLException e) {

        }
    }

    @After
    public void tearDown() throws Exception {
        context().tearDownContexts();
    }

    @Test
    public void elementByTest() throws Exception {

        ifind.elementBy(Locator.XPATH, "//");

    }

    @Test
    public void test() throws Exception {

        goTo("https://bbc.co.uk").waitFor(5L).elementToBeClickable(Locator.ID, "orb-modules");

    }
}
