package com.atanas.kanchev.testframework.selenium.tests.context;

import com.atanas.kanchev.testframework.core.exceptions.CustomExceptions;
import com.atanas.kanchev.testframework.core.context.AbstractContext;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Tests for {@link AbstractContext}
 *
 * @author Atanas Ksnchev
 */
public class AbstractContextTest extends AbstractContext<WebDriver> {

    @Test
    public void getAndSetDriver() throws Exception {

        WebDriver driver = mock(FirefoxDriver.class);
        setDriver(driver);
        assertNotNull(super.getDriver());
        assertTrue(super.getDriver() instanceof WebDriver);

    }

    @Test(expected = CustomExceptions.Common.NullReferenceException.class)
    public void getNullDriver() throws Exception {

        assertNull(super.getDriver());

    }

    @Test(expected = CustomExceptions.Common.NullArgumentException.class)
    public void setNullDriver() throws Exception {

        super.setDriver(null);
        assertNull(super.getDriver());

    }

    @Test
    public void getDefaultContextName() throws Exception {

        assertNotNull(super.getContextName());

    }

    @Test
    public void getContextObject() throws Exception {

        assertNotNull(super.getContext());
        assertTrue(super.getContext() instanceof AbstractContext);

    }

    @Test
    public void setContextNameTest() throws Exception {

        super.setContextName("new.context");
        assertEquals("new.context", getContextName());

    }

    @Test(expected = CustomExceptions.Common.NullArgumentException.class)
    public void setNullContextNameTest() throws Exception {

        super.setContextName(null);

    }

    @Test(expected = CustomExceptions.Common.EmptyArgumentException.class)
    public void setEmptyContextNameTest() throws Exception {

        super.setContextName("");

    }
}