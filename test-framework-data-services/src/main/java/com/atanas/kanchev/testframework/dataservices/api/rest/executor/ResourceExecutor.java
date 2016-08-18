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

package com.atanas.kanchev.testframework.dataservices.api.rest.executor;

import com.atanas.kanchev.testframework.dataservices.api.rest.requetsfactory.Resource;
import com.mashape.unirest.http.HttpMethod;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * <p>ResourceExecutor class.</p>
 *
 * @author Atanas Kanchev
 */
public class ResourceExecutor extends Executor{

    private static final Logger logger = LoggerFactory.getLogger(ResourceExecutor.class);

    private final Resource resource;

    /**
     * <p>Constructor for ResourceExecutor.</p>
     *
     * @param resource a {@link com.atanas.kanchev.testframework.dataservices.api.rest.requetsfactory.Resource} object.
     */
    public ResourceExecutor(Resource resource) {
        this.resource = resource;
    }

    /**
     * <p>Execute Resource.</p>
     *
     * @return a {@link com.mashape.unirest.http.HttpResponse} object.
     */
    public HttpResponse<String> executeResource() {

        HttpRequest req;

        if (resource.getHttpMethod() == HttpMethod.GET)
            req = new GetRequest(resource.getHttpMethod(), resource.getUrl());
        else
            req = new HttpRequestWithBody(resource.getHttpMethod(), resource.getUrl());
        if (resource.getRequest().getHeaders() != null)
            req.headers(resource.getRequest().getHeaders());
        if (resource.getRequest().getPayload() != null)
            ((HttpRequestWithBody) req).body(resource.getRequest().getPayload());


        if (httpClientBuilder == null)
            httpClientBuilder = HttpClients.custom()
                    .setConnectionTimeToLive(30, TimeUnit.SECONDS);

        CloseableHttpClient httpClient = httpClientBuilder.build();
        Unirest.setHttpClient(httpClient);

        try {
            return req.asString();
        } catch (UnirestException e) {
            logger.error("Unirest exception ", e);
        }

        return null;
    }

}
