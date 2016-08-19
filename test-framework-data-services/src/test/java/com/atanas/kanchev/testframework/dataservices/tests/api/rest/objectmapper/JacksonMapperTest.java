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

package com.atanas.kanchev.testframework.dataservices.tests.api.rest.objectmapper;

import com.atanas.kanchev.testframework.dataservices.api.rest.objectmapper.JacksonMapper;
import com.atanas.kanchev.testframework.dataservices.tests.api.JSONPlaceholderGetResource;
import com.atanas.kanchev.testframework.dataservices.tests.api.JSONPlaceholderPutResource;
import com.atanas.kanchev.testframework.dataservices.tests.api.rest.Example;
import com.atanas.kanchev.testframework.dataservices.wrappers.IDataResource;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * Test for {@link JacksonMapper}
 */
public class JacksonMapperTest implements IDataResource {

    @Test
    public void objectToJson() throws Exception {
        Posts posts = new Posts();
        posts.setId(1);
        posts.setUserId(1);
        posts.setTitle("title");

        String json = JacksonMapper.objectToJson(posts);

        apiResource(new JSONPlaceholderPutResource("/posts"));
        apiResource().appendToEndpoint("/1");
        apiResource().getRequest().setPayload(json);
        apiResource().exec();
        Assert.assertTrue(apiResource().getRequest().getPayload().contains("title"));
        Assert.assertEquals(200, apiResource().getResponse().getStatusCode());
        Assert.assertEquals(1, posts.getId().longValue());

    }

    @Test
    public void jsonToObject() throws Exception {
        apiResource(new JSONPlaceholderGetResource("/posts"));
        apiResource().appendToEndpoint("/1");
        apiResource().exec();

        assertThat(apiResource().getResponse().getStatusCode(), equalTo(200));
        assertThat(apiResource().getResponse().getReason(), equalTo("OK"));

        Posts posts = JacksonMapper.jsonToObject(apiResource().getResponse().getPayload(), Posts.class);
        Assert.assertEquals(1, posts.getUserId().longValue());
        Assert.assertEquals(1, posts.getId().longValue());

    }

}