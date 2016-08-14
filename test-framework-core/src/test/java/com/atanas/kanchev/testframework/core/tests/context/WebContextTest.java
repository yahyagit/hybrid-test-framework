package com.atanas.kanchev.testframework.core.tests.context;


import com.atanas.kanchev.testframework.selenium.context.SeleniumContext;

/**
 * Tests for {@link SeleniumContext}
 *
 * @author Atanas Ksnchev
 */
public class WebContextTest {

    private SeleniumContext seleniumContext;

//    @Before
//    public void setUp() throws Exception {
//        seleniumContext = new SeleniumContext();
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        seleniumContext = null;
//
//    }
//
//    @Caps
//    public void getCurrentElementTest() throws Exception {
//        WebElement el = mock(WebElement.class);
//        seleniumContext.setCurrentElement(el);
//        assertNotNull(seleniumContext.getCurrentElement());
//
//    }
//
//    @Caps(expected = CustomExceptions.Common.NullReferenceException.class)
//    public void getCurrentElementNullTest() throws Exception {
//        assertNull(seleniumContext.getCurrentElement());
//
//    }
//
//    @Caps
//    public void getWebElementsListTest() throws Exception {
//        List<WebElement> el = mock(ArrayList.class);
//        seleniumContext.setWebElementsList(el);
//        assertNotNull(seleniumContext.getWebElementsList());
//        assertTrue(seleniumContext.getWebElementsList() instanceof ArrayList);
//    }
//
//    @Caps(expected = CustomExceptions.Common.NullReferenceException.class)
//    public void getWebElementsListNull() throws Exception {
//        assertNull(seleniumContext.getWebElementsList());
//
//    }
//
//
//    @Caps
//    public void setCurrentElementTest() throws Exception {
//        WebElement el = mock(WebElement.class);
//        seleniumContext.setCurrentElement(el);
//        assertNotNull(seleniumContext.getCurrentElement());
//        assertTrue(seleniumContext.getCurrentElement() instanceof WebElement);
//    }
//
//    @Caps(expected = CustomExceptions.Common.NullArgumentException.class)
//    public void setCurrentElementNullTest() throws Exception {
//        seleniumContext.setCurrentElement(null);
//        assertNull(seleniumContext.getCurrentElement());
//    }
//
//    @Caps
//    public void setWebElementsListTest() throws Exception {
//        List<WebElement> el = mock(ArrayList.class);
//        seleniumContext.setWebElementsList(el);
//        assertNotNull(seleniumContext.getWebElementsList());
//        assertTrue(seleniumContext.getWebElementsList() instanceof ArrayList);
//    }
//
//    @Caps(expected = CustomExceptions.Common.NullArgumentException.class)
//    public void setWebElementsListNullTest() throws Exception {
//        seleniumContext.setWebElementsList(null);
//        assertNull(seleniumContext.getWebElementsList());
//
//    }



}