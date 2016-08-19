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

package com.atanas.kanchev.testframework.dataservices.api.rest.executor;

import com.atanas.kanchev.testframework.dataservices.api.rest.requetsfactory.Resource;
import com.mashape.unirest.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>ResourceExecutor class.</p>
 *
 * @author Atanas Kanchev
 */
public class ResourceExecutor extends Executor {

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
        logger.debug("Executing resource " + resource.toString());
        return exec
                (
                        resource.getHttpMethod(),
                        resource.getUrl().toString(),
                        resource.getRequest().getHeaders(),
                        resource.getRequest().getPayload()
                );
    }

    @Override
    public String toString() {
        return "ResourceExecutor{" +
                "resource=" + resource.toString() +
                '}';
    }
}
