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

package com.atanas.kanchev.testframework.selenium.element.proxy;

import com.atanas.kanchev.testframework.commons.context.ContextKey;
import com.atanas.kanchev.testframework.selenium.accessors.SeleniumAccessorsSingleton;
import com.atanas.kanchev.testframework.selenium.context.SeleniumContext;
import com.atanas.kanchev.testframework.selenium.element.OmniaWebElement;
import com.atanas.kanchev.testframework.selenium.element.wrap.Wrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.*;
import java.util.Arrays;

import static com.atanas.kanchev.testframework.commons.accessors.ContextsAccessor.$context;
import static com.atanas.kanchev.testframework.selenium.accessors.SeleniumAccessors.$selenium;

/**
 * Created by atanas on 23/09/16.
 */
public class ElementProxy implements InvocationHandler {

    private static final Logger logger = LoggerFactory.getLogger(ElementProxy.class.getName());

    static ContextKey<SeleniumContext> currentContextKey;
    Object object;

    public ElementProxy(Object object) {
        this.object = object;
    }

    public static Object newInstance(Object object) {
        return Proxy
            .newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(),
                new ElementProxy(object));
    }

    public static void main(String[] args) {

        $selenium().conf().setBrowser("chrome");
        SeleniumContext<WebDriver> c = new SeleniumContext<>($selenium().conf().getDriver());
        currentContextKey = $context().addContext(c.getContextKey(), c);
        SeleniumAccessorsSingleton.currentContextKey = currentContextKey;
        $selenium().goTo("https://www.google.co.uk");
        //        $selenium().find(By.className("gsfi"));

        OmniaWebElement wrapper = Wrapper.wrap(OmniaWebElement.class, null, null, 0);

        //        OmniaWebElement iElement = (OmniaWebElement) newInstance(
        //            new ElementImpl());

        wrapper.findElement(By.className("gsfi"));

        //        System.out.println(wrapper.isDisplayed());
//        wrapper.click();
//        wrapper.doubleClick();
//        wrapper.waits().titleContains("google");
                wrapper.sendKeys("hello");

                try {
                    Thread.sleep(89999999);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


    }

    @Override public Object invoke(Object proxy, Method method, Object... args) throws Throwable {

        Object result = null;

        logger.debug("Before method " + method.getName());
        long start = System.nanoTime();

        try {
            Class<?> implementingClazz = method.getAnnotation(ImplementedBy.class).value();
            logger.debug("Implementing class " + implementingClazz);

            Constructor<?> constructor = implementingClazz.getConstructor(ContextKey.class);
            Object thing = constructor.newInstance(currentContextKey);

            Method[] allMethods = implementingClazz.getDeclaredMethods();
            logger.debug("Methods in implementing class " + Arrays.toString(allMethods));

            if (args == null) {
                Method m = implementingClazz.getMethod(method.getName());
                logger.debug("Invoking method " + m.getName());
                result = m.invoke(thing);
            } else {
                Class<?>[] c = method.getParameterTypes();
                logger.debug("Method param type " + c);
                Method m = implementingClazz.getDeclaredMethod(method.getName(), c);
                logger.debug(
                    "Invoking method " + m.getName() + " with params " + Arrays.toString(args));
                result = m.invoke(thing, args);
            }

            long end = System.nanoTime();
            logger.debug(String.format("%s took %d ns", method.getName(), (end - start)));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //        for (Class<?> clazz : classes) {
        //
        //            if (clazz.getSimpleName().toLowerCase().equals(method.getName().toLowerCase())) {
        //
        //                try {
        //                    Constructor<?> constructor = clazz.getConstructor(ContextKey.class);
        //                    Object thing = constructor.newInstance(currentContextKey);
        //
        //                    Method[] allMethods = clazz.getDeclaredMethods();
        //                    System.out.println("All methods "  +Arrays.toString(allMethods));
        //
        //                    if (args == null) {
        //                        Method m = clazz.getMethod(method.getName());
        //                        logger.debug("Invoking method " + m);
        //                        result = m.invoke(thing);
        //                    } else {
        //                        Class<?>[] c = method.getParameterTypes();
        //                        System.out.println(c[0]);
        //                        Method m = clazz.getMethod(method.getName(),  c[0]);
        //                        logger.debug("Invoking method " + m.getName());
        //                        result = m.invoke(thing, args);
        //                    }
        //
        //                } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
        //                    e.printStackTrace();
        //                }
        //
        //                break;
        //            }
        //        }

        //        Object result;
        //        try {
        //            System.out.println("before method " + method.getName());
        //            long start = System.nanoTime();
        //            result = method.invoke(object, args);
        //            long end = System.nanoTime();
        //            System.out.println(String.format("%s took %d ns", method.getName(), (end - start)));
        //        } catch (InvocationTargetException e) {
        //            throw e.getTargetException();
        //        } catch (Exception e) {
        //            throw new RuntimeException("unexpected invocation exception: " + e.getMessage());
        //        } finally {
        //            System.out.println("after method " + method.getName());
        //        }
        //        return result;

        return result;
    }

}