package com.atanas.kanchev.testframework.dataservices.wrappers;

import com.atanas.kanchev.testframework.commons.wrappers.IContext;
import com.atanas.kanchev.testframework.dataservices.api.factory.Resource;
import com.atanas.kanchev.testframework.dataservices.context.APIResourceContext;

/**
 * The interface API resource.
 */
public interface IAPIResource extends IContext {

    /**
     * Api resource.
     *
     * @param resource the resource {@code com.atanas.kanchev.testframework.dataservices.apiResource.factory.Resource}
     * @return the resource
     */
    default Resource apiResource(Resource resource) {
        return ((APIResourceContext) context().getCurrentContext()).setResource(resource)
            .getResource();
    }

    /**
     * Gives access to the current API resource.
     *
     * @return the resource {@link APIResourceContext#resource}
     */
    default Resource apiResource() {
        return ((APIResourceContext) context().getCurrentContext()).getResource();
    }
}
