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

package com.atanas.kanchev.testframework.dataservices.api.rest.requetsfactory;

import java.util.HashMap;

/**
 * <p>Message class.</p>
 *
 * @author Atanas Kanchev
 */
public class Message {

    private static final HashMap<String, String> HEADERS = new HashMap<>();
    private static final HashMap<String, String> COOKIES = new HashMap<>();

    private String body;

    /**
     * <p>Getter for the field <code>body</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getBody() {
        return body;
    }

    /**
     * <p>Setter for the field <code>body</code>.</p>
     *
     * @param body a {@link java.lang.String} object.
     * @return a {@link com.atanas.kanchev.testframework.dataservices.api.rest.requetsfactory.Message} object.
     */
    public Message setBody(String body) {

        this.body = body;

        return this;
    }

    /**
     * <p>getHeaders.</p>
     *
     * @return a {@link java.util.HashMap} object.
     */
    public HashMap<String, String> getHeaders() {
        return new HashMap<>(HEADERS);
    }

    /**
     * <p>getCookies.</p>
     *
     * @return a {@link java.util.HashMap} object.
     */
    public HashMap<String, String> getCookies() {
        return new HashMap<>(COOKIES);
    }

    /**
     * <p>setHeaders.</p>
     *
     * @param headers a {@link java.util.HashMap} object.
     * @return a {@link com.atanas.kanchev.testframework.dataservices.api.rest.requetsfactory.Message} object.
     */
    public Message setHeaders(HashMap<String, String> headers) {

        HEADERS.putAll(headers);

        return this;
    }

    /**
     * <p>setCookies.</p>
     *
     * @param cookies a {@link java.util.HashMap} object.
     * @return a {@link com.atanas.kanchev.testframework.dataservices.api.rest.requetsfactory.Message} object.
     */
    public Message setCookies(HashMap<String, String> cookies) {

        HEADERS.putAll(cookies);

        return this;
    }

    /**
     * <p>setHeader.</p>
     *
     * @param key a {@link java.lang.String} object.
     * @param value a {@link java.lang.String} object.
     * @return a {@link com.atanas.kanchev.testframework.dataservices.api.rest.requetsfactory.Message} object.
     */
    public Message setHeader(String key, String value) {

        HEADERS.put(key, value);

        return this;
    }

    /**
     * <p>setCookie.</p>
     *
     * @param key a {@link java.lang.String} object.
     * @param value a {@link java.lang.String} object.
     * @return a {@link com.atanas.kanchev.testframework.dataservices.api.rest.requetsfactory.Message} object.
     */
    public Message setCookie(String key, String value) {

        COOKIES.put(key, value);

        return this;
    }

}
