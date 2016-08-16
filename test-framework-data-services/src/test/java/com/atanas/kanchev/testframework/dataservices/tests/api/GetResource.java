package com.atanas.kanchev.testframework.dataservices.tests.api;

import com.atanas.kanchev.testframework.commons.properties.PropertyReader;
import com.atanas.kanchev.testframework.dataservices.api.factory.Resource;

/**
 * Created by atanas on 15/08/16.
 */
public class GetResource extends Resource {

    public GetResource() {
        super(HttpMethodsEnum.GET);
        super.url.append(PropertyReader.getProp("test.env.properties", "root"));
        super.endpoint.append(PropertyReader.getProp("endpoints.properties", "get"));
    }

}
