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

package com.atanas.kanchev.testframework.selenium.proxy.omnia;

import com.atanas.kanchev.testframework.selenium.interactions.element.OmniaElement;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static java.lang.Thread.currentThread;

/**
 * @author Atanas Kanchev
 */
public class ElementProxy implements java.lang.reflect.InvocationHandler {



    private Object obj;

    public static Object newInstance(Object obj) {
        return java.lang.reflect.Proxy
            .newProxyInstance(
                currentThread().getContextClassLoader(),
                obj.getClass().getInterfaces(),
                new ElementProxy());
    }

    @SuppressWarnings("unchecked")
    public static <T extends OmniaElement> T wrap(Class<T> clazz, SearchContext parent, By criteria, int index) {
        return (T) Proxy.newProxyInstance(
            currentThread().getContextClassLoader(),
            new Class<?>[]{clazz},
            new ElementProxy());
    }
//
//    private ElementProxy(Object obj) {
//        this.obj = obj;
//    }

    public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
        Object result;
        try {
            System.out.println("before method " + m.getName());
            long start = System.nanoTime();
            result = m.invoke(obj, args);
            long end = System.nanoTime();
            System.out.println(String.format("%s took %d ns", m.getName(), (end - start)));
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        } catch (Exception e) {
            throw new RuntimeException("unexpected invocation exception: " + e.getMessage());
        } finally {
            System.out.println("after method " + m.getName());
        }
        return result;
    }
}

