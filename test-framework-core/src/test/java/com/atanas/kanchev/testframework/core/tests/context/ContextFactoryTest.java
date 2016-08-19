/*
 * Copyright 2016 Atanas Stoychev Kanchev
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.atanas.kanchev.testframework.core.tests.context;


import com.atanas.kanchev.testframework.commons.context.ContextFactory;

public class ContextFactoryTest /*extends ContextFactory*/ {

//    @Caps
//    public void addContextTest() throws Exception {
//
//        AbstractContext com.atanas.kanchev.testframework.selenium.context = new SeleniumContext();
//        super.addContext(com.atanas.kanchev.testframework.selenium.context);
//        assertTrue(getContextMap().containsValue(com.atanas.kanchev.testframework.selenium.context));
//        assertTrue(getCurrentContext() == com.atanas.kanchev.testframework.selenium.context);
//
//    }
//
//    @Caps
//    public void addContextWithDuplicatedNameTest() throws Exception {
//
//        AbstractContext context1 = new SeleniumContext();
//        String contextName = context1.getContextName();
//        super.addContext(context1);
//        super.addContext(context1);
//        assertTrue(getContextMap().containsKey(contextName + (getContextMap().size() - 1)));
//
//
//    }
//
//    @Caps(expected = CustomExceptions.Common.NullArgumentException.class)
//    public void addNullContextTest() throws Exception {
//
//        super.addContext(null);
//        assertTrue(getContextMap().size() == 0);
//        assertTrue(getCurrentContext() == null);
//
//    }
//
//    @Caps
//    public void switchContextTest() throws Exception {
//
//        AbstractContext context1 = new SeleniumContext();
//        super.addContext(context1);
//        assertTrue(getContextMap().containsValue(context1));
//        assertTrue(getCurrentContext() == context1);
//
//        AbstractContext context2 = new SeleniumContext();
//        super.addContext(context2);
//        assertTrue(getContextMap().containsValue(context2));
//        assertTrue(getCurrentContext() == context2);
//
//        assertNotNull(switchContext(context1.getContextName()));
//        assertTrue(getCurrentContext() == context1);
//    }
//
//    @Caps(expected = CustomExceptions.Common.NullArgumentException.class)
//    public void switchContextNullTest() throws Exception {
//
//        assertNull(switchContext(null));
//
//    }
//
//    @Caps(expected = CustomExceptions.Common.EmptyArgumentException.class)
//    public void switchContextEmptyTest() throws Exception {
//
//        assertNull(switchContext(""));
//
//    }
//
//    @Caps
//    public void getContextMapTest() throws Exception {
//
//        assertTrue(getContextMap() != null);
//        assertTrue(getContextMap() instanceof ConcurrentHashMap);
//
//    }
//
//    @Caps
//    public void getCurrentContextTest() throws Exception {
//        AbstractContext com.atanas.kanchev.testframework.selenium.context = new SeleniumContext();
//        super.addContext(com.atanas.kanchev.testframework.selenium.context);
//        assertTrue(getContextMap().containsValue(com.atanas.kanchev.testframework.selenium.context));
//        assertTrue(getCurrentContext() == com.atanas.kanchev.testframework.selenium.context);
//        assertTrue(getCurrentContext() instanceof SeleniumContext);
//    }
//
//    @Caps(expected = CustomExceptions.Common.NullArgumentException.class)
//    public void getCurrentContextNullTest() throws Exception {
//        assertNull(getCurrentContext());
//    }
//
//    @Caps
//    public void tearDownContextTest() throws Exception {
//
//    }


}
