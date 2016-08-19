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

package com.atanas.kanchev.testframework.dataservices.api.rest.objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

/**
 * <p>JacksonMapper class.</p>
 *
 * @author Atanas Kanchev
 */
public final class JacksonMapper {

    /**
     * <p>objectToJson.</p>
     *
     * @param obj a {@link java.lang.Object} object.
     * @return a {@link java.lang.String} object.
     * @throws com.fasterxml.jackson.core.JsonProcessingException if any.
     */
    public static String objectToJson(Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }

    /**
     * <p>jsonToObject.</p>
     *
     * @param json a {@link java.lang.String} object.
     * @param clazz a {@link java.lang.Class} object.
     * @param <T> a T object.
     * @return a T object.
     * @throws java.io.IOException if any.
     */
    public static <T> T jsonToObject(String json, Class<T> clazz) throws IOException {
        return new ObjectMapper().readValue(json, clazz);
    }

    /**
     * <p>jsonToList.</p>
     *
     * @param json a {@link java.lang.String} object.
     * @param clazz a {@link java.lang.Class} object.
     * @param <T> a T object.
     * @return a {@link java.util.List} object.
     * @throws java.io.IOException if any.
     */
    public static <T> List jsonToList(String json, Class<T> clazz) throws IOException {
        return new ObjectMapper().readValue(json, new ObjectMapper()
                .getTypeFactory().constructCollectionType(List.class, clazz));
    }
}
