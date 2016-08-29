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

package com.atanas.kanchev.testframework.dataservices.context;

import com.atanas.kanchev.testframework.commons.context.AbstractContext;
import com.atanas.kanchev.testframework.commons.context.ContextKey;
import com.atanas.kanchev.testframework.dataservices.api.rest.requetsfactory.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.atanas.kanchev.testframework.dataservices.api.rest.executor.Executor.shutdown;

public class APIResourceContext extends AbstractContext {

    private static final Logger logger =
        LoggerFactory.getLogger(APIResourceContext.class.getName());

    private Resource resource;

    public APIResourceContext() {
        super("apiResourceCtx_");
    }

    public APIResourceContext(Resource resource) {
        this();
        this.resource = resource;
    }

    public Resource getResource() {
        return resource;
    }

    public APIResourceContext setResource(Resource resource) {

        this.resource = resource;

        return this;
    }

    @Override public ContextKey<APIResourceContext> getContextKey() {
        return new ContextKey<>(getContextName(), APIResourceContext.class);
    }

    @Override public void tearDownContext() {

        logger.debug("Tearing down context " + this.toString());
        shutdown();
    }

    @Override public String toString() {
        return "APIResourceContext{" + "resource=" + resource + '}';
    }
}
