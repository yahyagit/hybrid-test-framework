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

package com.atanas.kanchev.testframework.dataservices.tests.api.rest.objectmapper;

import com.atanas.kanchev.testframework.dataservices.api.rest.objectmapper.JacksonMapper;
import com.atanas.kanchev.testframework.dataservices.tests.api.rest.executor.JSONPlaceholderResource;
import org.junit.Assert;
import org.junit.Test;

import static com.atanas.kanchev.testframework.dataservices.accessors.DataServicesAccessors.$data;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Test for {@link JacksonMapper}
 */
public class JacksonMapperTest {

    @Test public void objectToJson() throws Exception {
        Posts posts = new Posts();
        posts.setId(1);
        posts.setUserId(1);
        posts.setTitle("title");

        String json = JacksonMapper.objectToJson(posts);

        $data().apiResource(new JSONPlaceholderResource().new JSONPlaceholderPUTResource("/posts"));
        $data().apiResource().appendToEndpoint("/1");
        $data().apiResource().getRequest().setPayload(json);
        $data().apiResource().exec();
        Assert.assertTrue($data().apiResource().getRequest().getPayload().contains("title"));
        Assert.assertEquals(200, $data().apiResource().getResponse().getStatusCode());
        Assert.assertEquals(1, posts.getId().longValue());

    }

    @Test public void jsonToObject() throws Exception {
        $data().apiResource(new JSONPlaceholderResource().new JSONPlaceholderPUTResource("/posts"));
        $data().apiResource().appendToEndpoint("/1");
        $data().apiResource().exec();

        assertThat($data().apiResource().getResponse().getStatusCode(), equalTo(200));
        assertThat($data().apiResource().getResponse().getReason(), equalTo("OK"));

        Posts posts = JacksonMapper
            .jsonToObject($data().apiResource().getResponse().getPayload(), Posts.class);
        Assert.assertEquals(1, posts.getId().longValue());

    }

}
