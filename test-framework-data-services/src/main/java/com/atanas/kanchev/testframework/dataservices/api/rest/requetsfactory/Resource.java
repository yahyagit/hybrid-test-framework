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

import com.atanas.kanchev.testframework.commons.wrappers.IContext;
import com.atanas.kanchev.testframework.dataservices.api.rest.executor.ResourceExecutor;
import com.atanas.kanchev.testframework.dataservices.context.APIResourceContext;
import com.mashape.unirest.http.HttpMethod;
import com.mashape.unirest.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Resource class.</p>
 *
 * @author Atanas Kanchev
 */
public class Resource implements IContext {

    private static final Logger logger = LoggerFactory.getLogger(Resource.class);

    protected final StringBuilder endpoint = new StringBuilder();
    protected final StringBuilder url = new StringBuilder();

    private Request request;
    private Response response;

    private HttpMethod httpMethod;

    /**
     * <p>Constructor for Resource.</p>
     *
     * @param httpMethod a {@link com.mashape.unirest.http.HttpMethod} object.
     */
    public Resource(HttpMethod httpMethod) {

        this.httpMethod = httpMethod;
        this.request = new Request();
        this.response = new Response();
        APIResourceContext apiResourceContext = new APIResourceContext(this);
        context().addContext(apiResourceContext);

    }

    // GETTERS //

    /**
     * <p>Getter for the field <code>request</code>.</p>
     *
     * @return a {@link com.atanas.kanchev.testframework.dataservices.api.rest.requetsfactory.Request} object.
     */
    public Request getRequest() {

        return this.request;

    }

    /**
     * <p>Getter for the field <code>response</code>.</p>
     *
     * @return a {@link com.atanas.kanchev.testframework.dataservices.api.rest.requetsfactory.Response} object.
     */
    public Response getResponse() {

        return this.response;
    }

    /**
     * <p>Getter for the field <code>endpoint</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getEndpoint() {
        return endpoint.toString();
    }

    /**
     * <p>Getter for the field <code>url</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getUrl() {
        return url.toString();
    }

    /**
     * <p>Getter for the field <code>httpMethod</code>.</p>
     *
     * @return a {@link com.mashape.unirest.http.HttpMethod} object.
     */
    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    // SETTERS //

    /**
     * <p>Setter for the field <code>request</code>.</p>
     *
     * @param request a {@link com.atanas.kanchev.testframework.dataservices.api.rest.requetsfactory.Request} object.
     * @return a {@link com.atanas.kanchev.testframework.dataservices.api.rest.requetsfactory.Resource} object.
     */
    public Resource setRequest(Request request) {

        this.request = request;

        return this;
    }

    /**
     * <p>setURL.</p>
     *
     * @param url a {@link java.lang.String} object.
     * @return a {@link com.atanas.kanchev.testframework.dataservices.api.rest.requetsfactory.Resource} object.
     */
    public Resource setURL(String url) {
        this.url.append(url);
        logger.debug("Setting url to " + this.url.toString());
        return this;
    }

    /**
     * <p>Setter for the field <code>endpoint</code>.</p>
     *
     * @param append a {@link java.lang.String} object.
     * @return a {@link com.atanas.kanchev.testframework.dataservices.api.rest.requetsfactory.Resource} object.
     */
    public Resource setEndpoint(String append) {
        this.endpoint.append(append);
        logger.debug("Setting endpoint to " + this.endpoint.toString());
        return this;
    }

    /**
     * <p>exec.</p>
     *
     * @return a {@link com.atanas.kanchev.testframework.dataservices.api.rest.requetsfactory.Resource} object.
     */
    public Resource exec() {

        url.append(endpoint.toString());

        logger.debug("> Request URL: " + url);
        logger.debug("> Request headers: " + getRequest().getHeaders());
        logger.debug("> Request cookies: " + getRequest().getCookies());
        logger.debug("> Request body: " + getRequest().getBody());

        HttpResponse<String> response = new ResourceExecutor(this).executeResource();
        if (response != null)
            setResponse(response);
        else
            logger.debug("Null response");

        return this;
    }

    private void setResponse(HttpResponse<String> response) {
        // set response status code
        this.response.setStatusCode(response.getStatus());
        this.response.setReason(response.getStatusText());
        logger.debug("> Response status and text: " + "{" + response.getStatus() + "," + response
            .getStatusText() + "}");

    }

    private void setRespMessage(HttpResponse<String> response) {

        if (response.getHeaders().getFirst("Content-Type") != null && response.getHeaders()
            .getFirst("Content-Type").contains("application/json")) {
            this.response.setBody(response.getBody());
        }
        logger.debug("> Response body: " + this.response.getBody());
    }
}
