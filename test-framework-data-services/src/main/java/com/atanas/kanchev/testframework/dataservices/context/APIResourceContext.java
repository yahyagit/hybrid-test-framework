package com.atanas.kanchev.testframework.dataservices.context;

import com.atanas.kanchev.testframework.commons.context.AbstractContext;
import com.atanas.kanchev.testframework.dataservices.api.factory.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.atanas.kanchev.testframework.dataservices.api.factory.Executor.shutdown;

/**
 * The type Api resource context.
 */
public class APIResourceContext extends AbstractContext {

    private static final Logger logger = LoggerFactory.getLogger(APIResourceContext.class);

    private Resource resource;

    /**
     * Instantiates a new Api resource context.
     * Sets the value of {@link AbstractContext#contextName}
     */
    public APIResourceContext() {
        super("apiResourceCtx_");
    }

    /**
     * Instantiates a new Api resource context.
     * Sets the value of {@link AbstractContext#contextName}
     *
     * @param resource the resource
     */
    public APIResourceContext(Resource resource) {
        this();
        this.resource = resource;
    }

    /**
     * Gets resource.
     *
     * @return the resource {@link APIResourceContext#resource}
     */
    public Resource getResource() {
        return resource;
    }

    /**
     * Sets resource {@link APIResourceContext#resource}.
     *
     * @param resource the resource {@code com.atanas.kanchev.testframework.dataservices.apiResource.factory.Resource}
     * @return the resource
     */
    public APIResourceContext setResource(Resource resource) {

        this.resource = resource;

        return this;
    }

    @Override public void tearDownContext(AbstractContext context) {

        logger.debug("Tearing down context " + context.getContextName());

        shutdown();
    }

    @Override public String toString() {
        return "APIResourceContext{" + "resource=" + resource + '}';
    }
}
