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

package com.atanas.kanchev.testframework.selenium.element.wrap;

import com.atanas.kanchev.testframework.selenium.element.element.IElement;
import com.atanas.kanchev.testframework.selenium.element.proxy.ElementProxy;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;

import java.lang.reflect.Proxy;

import static java.lang.Thread.currentThread;

/**
 * Created by atanas on 27/09/16.
 */
public class Wrapper {

    @SuppressWarnings("unchecked")
    public static <T extends IElement> T wrap(Class<T> clazz, SearchContext parent, By criteria, int index) {
        return (T) Proxy.newProxyInstance(
            currentThread().getContextClassLoader(),
            new Class<?>[]{clazz},
            new ElementProxy(new Wrapper()));
    }
}
