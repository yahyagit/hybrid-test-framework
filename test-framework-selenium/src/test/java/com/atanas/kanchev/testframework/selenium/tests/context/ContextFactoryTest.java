package com.atanas.kanchev.testframework.selenium.tests.context;

import com.atanas.kanchev.testframework.core.exceptions.CustomExceptions;
import com.atanas.kanchev.testframework.selenium.context.AbstractContext;
import com.atanas.kanchev.testframework.selenium.context.ContextFactory;
import com.atanas.kanchev.testframework.selenium.context.WebContext;
import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.*;

/**
 * Test for {@link ContextFactory}
 *
 * @author Atanas Ksnchev
 */
public class ContextFactoryTest extends ContextFactory {

    @Test
    public void addContextTest() throws Exception {

        AbstractContext context = new WebContext();
        super.addContext(context);
        assertTrue(getContextMap().containsValue(context));
        assertTrue(getCurrentContext() == context);

    }

    @Test
    public void addContextWithDuplicatedNameTest() throws Exception {

        AbstractContext context1 = new WebContext();
        String contextName = context1.getContextName();
        super.addContext(context1);
        super.addContext(context1);
        assertTrue(getContextMap().containsKey(contextName + (getContextMap().size() - 1)));


    }

    @Test(expected = CustomExceptions.Common.NullArgumentException.class)
    public void addNullContextTest() throws Exception {

        super.addContext(null);
        assertTrue(getContextMap().size() == 0);
        assertTrue(getCurrentContext() == null);

    }

    @Test
    public void switchContextTest() throws Exception {

        AbstractContext context1 = new WebContext();
        super.addContext(context1);
        assertTrue(getContextMap().containsValue(context1));
        assertTrue(getCurrentContext() == context1);

        AbstractContext context2 = new WebContext();
        super.addContext(context2);
        assertTrue(getContextMap().containsValue(context2));
        assertTrue(getCurrentContext() == context2);

        assertNotNull(switchContext(context1.getContextName()));
        assertTrue(getCurrentContext() == context1);
    }

    @Test(expected = CustomExceptions.Common.NullArgumentException.class)
    public void switchContextNullTest() throws Exception {

        assertNull(switchContext(null));

    }

    @Test(expected = CustomExceptions.Common.EmptyArgumentException.class)
    public void switchContextEmptyTest() throws Exception {

        assertNull(switchContext(""));

    }

    @Test
    public void getContextMapTest() throws Exception {

        assertTrue(getContextMap() != null);
        assertTrue(getContextMap() instanceof ConcurrentHashMap);

    }

    @Test
    public void getCurrentContextTest() throws Exception {
        AbstractContext context = new WebContext();
        super.addContext(context);
        assertTrue(getContextMap().containsValue(context));
        assertTrue(getCurrentContext() == context);
        assertTrue(getCurrentContext() instanceof WebContext);
    }

    @Test(expected = CustomExceptions.Common.NullArgumentException.class)
    public void getCurrentContextNullTest() throws Exception {
        assertNull(getCurrentContext());
    }

    @Test
    public void tearDownContextTest() throws Exception {

    }


}