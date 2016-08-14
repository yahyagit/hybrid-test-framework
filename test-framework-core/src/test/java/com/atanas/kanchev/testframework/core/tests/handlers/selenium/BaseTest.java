package com.atanas.kanchev.testframework.core.tests.handlers.selenium;

import com.atanas.kanchev.testframework.commons.wrappers.IContext;
import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 * @author Atanas Ksnchev
 */
public class BaseTest implements IContext {

    @BeforeClass
    public static void setUp() throws Exception {

    }

    @AfterClass
    public static void tearDown() throws Exception {
        CONTEXT_FACTORY.tearDownContexts();
    }
}
