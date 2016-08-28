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

package com.atanas.kanchev.testframework.appium.tests.android.native_app_tests;

import io.appium.java_client.android.Connection;
import org.junit.Test;

import static com.atanas.kanchev.testframework.appium.accessors.AppiumAccessors.$appium;
import static org.junit.Assert.assertEquals;

public class AndroidConnectionTest extends BaseTest {

    @Test public void setWiFi() {
        $appium().android().networkConnection().setConnection(Connection.WIFI);
        assertEquals(Connection.WIFI, $appium().android().networkConnection().getConnection());
    }

    @Test public void setNoneAndAirplane() {
        $appium().android().networkConnection().setConnection(Connection.NONE);
        assertEquals(Connection.NONE, $appium().android().networkConnection().getConnection());
        $appium().android().networkConnection().setConnection(Connection.AIRPLANE);
        assertEquals(Connection.AIRPLANE, $appium().android().networkConnection().getConnection());
    }

    @Test public void setAll() {
        $appium().android().networkConnection().setConnection(Connection.ALL);
        assertEquals(Connection.ALL, $appium().android().networkConnection().getConnection());
    }
}
