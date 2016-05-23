package com.atanas.kanchev.testframework.selenium.tests.handlers;

import com.atanas.kanchev.testframework.core.context.ContextFactory;
import com.atanas.kanchev.testframework.selenium.handlers.IWrapper;
import org.junit.After;
import org.junit.Test;

/**
 * @author Atanas Ksnchev
 */
public class NavigateTest implements IWrapper{

    @Test
    public void getPage() throws Exception {
        goTo("https://www.google.co.uk");
    }

    @Test
    public void back() throws Exception {

    }

    @Test
    public void forward() throws Exception {

    }

    @Test
    public void refresh() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
        ContextFactory.tearDownContexts();

    }
}