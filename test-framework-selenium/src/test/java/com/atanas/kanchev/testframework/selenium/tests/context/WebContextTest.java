package com.atanas.kanchev.testframework.selenium.tests.context;

import com.atanas.kanchev.testframework.commons.exceptions.CustomExceptions;
import com.atanas.kanchev.testframework.selenium.context.WebContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Tests for {@link WebContext}
 *
 * @author Atanas Ksnchev
 */
public class WebContextTest {

    private WebContext webContext;

    @Before
    public void setUp() throws Exception {
        webContext = new WebContext();
    }

    @After
    public void tearDown() throws Exception {
        webContext = null;

    }

    @Test
    public void getCurrentElementTest() throws Exception {
        WebElement el = mock(WebElement.class);
        webContext.setCurrentElement(el);
        assertNotNull(webContext.getCurrentElement());

    }

    @Test(expected = CustomExceptions.Common.NullReferenceException.class)
    public void getCurrentElementNullTest() throws Exception {
        assertNull(webContext.getCurrentElement());

    }

    @Test
    public void getWebElementsListTest() throws Exception {
        List<WebElement> el = mock(ArrayList.class);
        webContext.setWebElementsList(el);
        assertNotNull(webContext.getWebElementsList());
        assertTrue(webContext.getWebElementsList() instanceof ArrayList);
    }

    @Test(expected = CustomExceptions.Common.NullReferenceException.class)
    public void getWebElementsListNull() throws Exception {
        assertNull(webContext.getWebElementsList());

    }


    @Test
    public void setCurrentElementTest() throws Exception {
        WebElement el = mock(WebElement.class);
        webContext.setCurrentElement(el);
        assertNotNull(webContext.getCurrentElement());
        assertTrue(webContext.getCurrentElement() instanceof WebElement);
    }

    @Test(expected = CustomExceptions.Common.NullArgumentException.class)
    public void setCurrentElementNullTest() throws Exception {
        webContext.setCurrentElement(null);
        assertNull(webContext.getCurrentElement());
    }

    @Test
    public void setWebElementsListTest() throws Exception {
        List<WebElement> el = mock(ArrayList.class);
        webContext.setWebElementsList(el);
        assertNotNull(webContext.getWebElementsList());
        assertTrue(webContext.getWebElementsList() instanceof ArrayList);
    }

    @Test(expected = CustomExceptions.Common.NullArgumentException.class)
    public void setWebElementsListNullTest() throws Exception {
        webContext.setWebElementsList(null);
        assertNull(webContext.getWebElementsList());

    }



}