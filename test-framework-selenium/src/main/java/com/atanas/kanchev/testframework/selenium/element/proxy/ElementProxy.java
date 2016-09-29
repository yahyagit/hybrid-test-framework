/*
 * Copyright 2016 Atanas Stoychev Kanchev
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.atanas.kanchev.testframework.selenium.element.proxy;

import com.atanas.kanchev.testframework.commons.context.ContextKey;
import com.atanas.kanchev.testframework.selenium.context.SeleniumContext;
import com.atanas.kanchev.testframework.selenium.element.OmniaWebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.*;
import java.util.Arrays;

import static java.lang.Thread.currentThread;

/**
 * Created by atanas on 23/09/16.
 */
public final class ElementProxy implements InvocationHandler {

    private static final Logger logger = LoggerFactory.getLogger(ElementProxy.class.getName());

    private final ContextKey<SeleniumContext> currentContextKey;

    private ElementProxy(ContextKey<SeleniumContext> currentContextKey) {
        this.currentContextKey = currentContextKey;
    }

    @SuppressWarnings("unchecked") public static <T extends OmniaWebElement> T wrap(Class<T> clazz,
        ContextKey<SeleniumContext> currentContextKey) {
        return (T) Proxy
            .newProxyInstance(currentThread().getContextClassLoader(), new Class<?>[] {clazz},
                new ElementProxy(currentContextKey));
    }

    @Override public Object invoke(Object proxy, Method method, Object... args) throws Throwable {

        Object result = null;

        logger.debug("Before method " + method.getName());
        long start = System.currentTimeMillis();

        try {
            Class<?> implementingClazz = method.getAnnotation(ImplementedBy.class).value();
            logger.debug("Implementing " + implementingClazz);

            Constructor<?> constructor = implementingClazz.getConstructor(ContextKey.class);
            Object thing = constructor.newInstance(currentContextKey);

            if (args == null) {
                Method m = implementingClazz.getMethod(method.getName());
                logger.debug("Invoking method " + m.getName());
                result = m.invoke(thing);
            } else {
                Class<?>[] c = method.getParameterTypes();
                logger.debug("Method param type(s) " + Arrays.toString(c));
                Method m = implementingClazz.getDeclaredMethod(method.getName(), c);
                logger.debug(
                    "Invoking method " + m.getName() + " with params " + Arrays.toString(args));
                result = m.invoke(thing, args);
            }

            logger.debug(String
                .format("%s took %d ms", method.getName(), (System.currentTimeMillis() - start)));
        } catch (NoSuchMethodException e) {
            logger.error(e.getMessage());
        } catch (SecurityException e) {
            logger.error(e.getMessage());
        } catch (InstantiationException e) {
            logger.error(e.getMessage());
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
        } catch (InvocationTargetException e) {
            logger.error(e.getMessage());
        }

        return result;
    }

}
