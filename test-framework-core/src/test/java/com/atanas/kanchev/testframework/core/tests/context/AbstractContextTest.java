package com.atanas.kanchev.testframework.core.tests.context;

import com.atanas.kanchev.testframework.commons.context.AbstractContext;
import org.openqa.selenium.WebDriver;

/**
 * Tests for {@link AbstractContext}
 *
 * @author Atanas Ksnchev
 */
public class AbstractContextTest extends AbstractContext<WebDriver> {
    /**
     * Constructor
     * Sets the value of {@link AbstractContext#contextName}
     *
     * @param contextName String
     */
    public AbstractContextTest(String contextName) {
        super(contextName);
    }

    @Override
    public WebDriver getDriver() {
        return null;
    }

    @Override
    public void setDriver(WebDriver driver) {

    }

    @Override
    public <U extends AbstractContext> void tearDownContext(U context) {

    }

//    /**
//     * Constructor
//     * Sets the value of {@link AbstractContext#contextName}
//     *
//     * @param contextName String
//     */
//    public AbstractContextTest(String contextName) {
//        super(contextName);
//    }
//
//    @Override
//    public void tearDownContext(AbstractContext com.atanas.kanchev.testframework.selenium.context) {
//
//    }
//
//
//    @Caps
//    public void getAndSetDriver() throws Exception {
//
//        WebDriver driver = mock(FirefoxDriver.class);
//        setDriver(driver);
//        assertNotNull(super.getDriver());
//        assertTrue(super.getDriver() instanceof WebDriver);
//
//    }
//
//    @Caps(expected = CustomExceptions.Common.NullReferenceException.class)
//    public void getNullDriver() throws Exception {
//
//        assertNull(super.getDriver());
//
//    }
//
//    @Caps(expected = CustomExceptions.Common.NullArgumentException.class)
//    public void setNullDriver() throws Exception {
//
//        super.setDriver(null);
//        assertNull(super.getDriver());
//
//    }
//
//    @Caps
//    public void getDefaultContextName() throws Exception {
//
//        assertNotNull(super.getContextName());
//
//    }
//
////    @Caps
////    public void getContextObject() throws Exception {
////
////        assertNotNull(super.getContext());
////        assertTrue(super.getContext() instanceof AbstractContext);
////
////    }
//
//    @Caps
//    public void setContextNameTest() throws Exception {
//
//        super.setContextName("new.com.atanas.kanchev.testframework.selenium.context");
//        assertEquals("new.com.atanas.kanchev.testframework.selenium.context", getContextName());
//
//    }
//
//    @Caps(expected = CustomExceptions.Common.NullArgumentException.class)
//    public void setNullContextNameTest() throws Exception {
//
//        super.setContextName(null);
//
//    }
//
//    @Caps(expected = CustomExceptions.Common.EmptyArgumentException.class)
//    public void setEmptyContextNameTest() throws Exception {
//
//        super.setContextName("");
//
//    }

}