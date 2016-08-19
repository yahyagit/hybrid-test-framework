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

package com.atanas.kanchev.testframework.dataservices.context;

import com.atanas.kanchev.testframework.commons.context.AbstractContext;
import com.atanas.kanchev.testframework.dataservices.api.rest.requetsfactory.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.atanas.kanchev.testframework.dataservices.api.rest.executor.Executor.shutdown;

/**
 * <p>APIResourceContext class.</p>
 *
 * @author Atanas Kanchev
 */
public class APIResourceContext extends AbstractContext {

    private static final Logger logger = LoggerFactory.getLogger(APIResourceContext.class);

    private Resource resource;

    /**
     * <p>Constructor for APIResourceContext.</p>
     */
    public APIResourceContext() {
        super("apiResourceCtx_");
    }

    /**
     * <p>Constructor for APIResourceContext.</p>
     *
     * @param resource a {@link com.atanas.kanchev.testframework.dataservices.api.rest.requetsfactory.Resource} object.
     */
    public APIResourceContext(Resource resource) {
        this();
        this.resource = resource;
    }

    /**
     * <p>Getter for the field <code>resource</code>.</p>
     *
     * @return a {@link com.atanas.kanchev.testframework.dataservices.api.rest.requetsfactory.Resource} object.
     */
    public Resource getResource() {
        return resource;
    }

    /**
     * <p>Setter for the field <code>resource</code>.</p>
     *
     * @param resource a {@link com.atanas.kanchev.testframework.dataservices.api.rest.requetsfactory.Resource} object.
     * @return a {@link com.atanas.kanchev.testframework.dataservices.context.APIResourceContext} object.
     */
    public APIResourceContext setResource(Resource resource) {

        this.resource = resource;

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override public void tearDownContext(AbstractContext context) {

        logger.debug("Tearing down context " + context.getContextName());

        shutdown();
    }

    /**
     * {@inheritDoc}
     */
    @Override public String toString() {
        return "APIResourceContext{" + "resource=" + resource + '}';
    }
}
