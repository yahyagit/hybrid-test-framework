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

package com.atanas.kanchev.testframework.selenium.inv.atanas;

import org.openqa.selenium.WebElement;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * Created by atanas on 23/09/16.
 */
public class ElementProxy implements InvocationHandler {

    Object object;

    private ElementProxy(Object object) {
        this.object = object;
    }

    public static Object newInstance(Object object) {
        return Proxy
            .newProxyInstance(
                object.getClass().getClassLoader(),
                object.getClass().getInterfaces(),
                new ElementProxy(object));
    }

    @Override public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result;
        try {
            System.out.println("before method " + method.getName());
            long start = System.nanoTime();
            result = method.invoke(object, args);
            long end = System.nanoTime();
            System.out.println(String.format("%s took %d ns", method.getName(), (end - start)));
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        } catch (Exception e) {
            throw new RuntimeException("unexpected invocation exception: " + e.getMessage());
        } finally {
            System.out.println("after method " + method.getName());
        }
        return result;
    }

    public static void main(String[] args) {

        IElement iElement = (IElement) newInstance(new Click());
        iElement.click();

    }
}
