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

package com.atanas.kanchev.testframework.selenium.inv.atanas;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Atanas Kanchev
 */
public class ElementHandler implements InvocationHandler {

    private final ElementLocator locator;
    private final Class<?> interfaceType;

    public <T> ElementHandler(ElementLocator locator, Class<T> interfaceType) {
        this.locator = locator;

        if (!ElementImpl.class.isAssignableFrom(interfaceType))
            throw new RuntimeException("interface not assignable to ElementImpl.");
        this.interfaceType = interfaceType;
    }


    @Override public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        WebElement element = locator.findElement();
        return null;
    }
}
