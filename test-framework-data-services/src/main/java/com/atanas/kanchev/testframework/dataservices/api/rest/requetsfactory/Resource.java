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

package com.atanas.kanchev.testframework.dataservices.api.rest.requetsfactory;

import com.atanas.kanchev.testframework.dataservices.api.rest.executor.ResourceExecutor;
import com.mashape.unirest.http.HttpMethod;
import com.atanas.kanchev.testframework.commons.wrappers.IContext;
import com.atanas.kanchev.testframework.dataservices.context.APIResourceContext;
import com.mashape.unirest.http.HttpMethod;
import com.mashape.unirest.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The type Resource.
 *
 * @author Atanas Kanchev
 */
public class Resource {
public class Resource implements IContext {

    private static final Logger logger = LoggerFactory.getLogger(Resource.class);

    protected final StringBuilder endpoint = new StringBuilder();
    protected final StringBuilder url = new StringBuilder();

    private Request request;
    private Response response;

    private HttpMethod httpMethod;

    /**
     * Instantiates a new Resource.
     *
     * @param httpMethod the http method type {@code com.mashape.unirest.http.HttpMethod}
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
     * Gets request
     *
     * @return the request {@link Resource#request}
     */
    public Request getRequest() {

        return this.request;

    }

    /**
     * Gets response.
     *
     * @return the response {@link Resource#response}
     */
    public Response getResponse() {

        return this.response;
    }

    public String getEndpoint() {
        return endpoint.toString();
    }

    public String getUrl() {
        return url.toString();
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    // SETTERS //

    /**
     * Sets request {@link Resource#request}.
     *
     * @param request the request
     */
    public Resource setRequest(Request request) {

        this.request = request;

        return this;
    }

    public Resource setURL(String url) {
        this.url.append(url);
        logger.debug("Setting url to " + this.url.toString());
        return this;
    }

    /**
     * Append to {@link Resource#endpoint}
     *
     * @param append {@code java.lang.String}
     * @return this
     */
    public Resource setEndpoint(String append) {
        this.endpoint.append(append);
        logger.debug("Setting endpoint to " + this.endpoint.toString());
        return this;
    }

    /**
     * Execute.
     */
    public Resource exec() {

        url.append(endpoint.toString());

        logger.debug("> Request URL: " + url);
        logger.debug("> Request headers: " + getRequest().getHeaders());
        logger.debug("> Request cookies: " + getRequest().getCookies());
        logger.debug("> Request body: " + getRequest().getBody());

        HttpResponse<String> response = new ResourceExecutor(this).executeResource();
        if (response != null) setResponse(response);
        else logger.debug("Null response");

        return this;
    }

    private void setResponse(HttpResponse<String> response) {
        // set response status code
        this.response.setStatusCode(response.getStatus());
        this.response.setReason(response.getStatusText());
        logger.debug("> Response status and text: " + "{" + response.getStatus() + "," + response.getStatusText() + "}");

    }

    private void setRespMessage(HttpResponse<String> response) {

        if (response.getHeaders().getFirst("Content-Type") != null &&
                response.getHeaders().getFirst("Content-Type").contains("application/json")) {
            this.response.setBody(response.getBody());
        }
        logger.debug("> Response body: " + this.response.getBody());
    }
}
