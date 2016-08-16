package com.atanas.kanchev.testframework.dataservices.tests.api;

import com.atanas.kanchev.testframework.commons.properties.PropertyReader;
import com.atanas.kanchev.testframework.dataservices.api.message.Resource;

/**
 * Created by atanas on 15/08/16.
 */
public class PutResource extends Resource {

    public PutResource() {
        super(HttpMethodsEnum.PUT);
        super.url.append(PropertyReader.getProp("test.env.properties", "root"));
        super.endpoint.append(PropertyReader.getProp("endpoints.properties", "put"));
    }

}
