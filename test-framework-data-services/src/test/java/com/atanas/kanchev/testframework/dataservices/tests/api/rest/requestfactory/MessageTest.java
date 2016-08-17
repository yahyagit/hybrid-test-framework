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

package com.atanas.kanchev.testframework.dataservices.tests.api.rest.requestfactory;

import com.atanas.kanchev.testframework.dataservices.api.rest.requetsfactory.Message;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class MessageTest {

    @Test public void getPayload() throws Exception {

        Message message = new Message();
        message.setPayload("payload");
        assertThat(message.getPayload(), is(equalTo("payload")));
    }

    @Test public void setPayload() throws Exception {

        Message message = new Message();
        message.setPayload("payload");
        assertThat(message.getPayload(), is(equalTo("payload")));

    }

    @Test public void getHeaders() throws Exception {

    }

    @Test public void getCookies() throws Exception {

    }

    @Test public void setHeaders() throws Exception {

    }

    @Test public void setCookies() throws Exception {

    }

    @Test public void setHeader() throws Exception {

        Message message = new Message();
        message.setHeader("key", "value");
        assertTrue(message.getHeaders().containsKey("key"));
        assertTrue(message.getHeaders().containsValue("value"));
    }

    @Test public void setCookie() throws Exception {

    }

}
