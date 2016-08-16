package com.atanas.kanchev.testframework.dataservices.api.message;

import java.util.HashMap;

/**
 * The type Message.
 *
 * @author Atanas Kanchev
 */
public class Message {

    private static final HashMap<String, String> HEADERS = new HashMap<>();
    private static final HashMap<String, String> COOKIES = new HashMap<>();

    private String body;

    /**
     * Gets the message body.
     *
     * @return the body {@link Message#body}
     */
    public String getBody() {
        return body;
    }

    /**
     * Sets the message body {@link Message#body}.
     *
     * @param body the body
     */
    public Message setBody(String body) {

        this.body = body;

        return this;
    }

    /**
     * Get shallow copy of {@link Message#HEADERS}
     *
     * @return headers
     */
    public HashMap<String, String> getHeaders() {
        return new HashMap<>(HEADERS);
    }

    /**
     * Get shallow copy of {@link Message#COOKIES}
     *
     * @return cookies
     */
    public HashMap<String, String> getCookies() {
        return new HashMap<>(COOKIES);
    }

    /**
     * Sets headers.
     *
     * @param headers the headers
     */
    public Message setHeaders(HashMap<String, String> headers) {

        HEADERS.putAll(headers);

        return this;
    }

    /**
     * Sets cookies.
     *
     * @param cookies the cookies
     */
    public Message setCookies(HashMap<String, String> cookies) {

        HEADERS.putAll(cookies);

        return this;
    }

    /**
     * Sets a header.
     *
     * @param key   the key
     * @param value the value
     */
    public Message setHeader(String key, String value) {

        HEADERS.put(key, value);

        return this;
    }

    /**
     * Sets a cookie.
     *
     * @param key   the key
     * @param value the value
     */
    public Message setCookie(String key, String value) {

        COOKIES.put(key, value);

        return this;
    }

}