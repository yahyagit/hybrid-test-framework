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

package com.atanas.kanchev.testframework.dataservices.api.rest.requetsfactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

/**
 * <p>Request class.</p>
 *
 * @author Atanas Kanchev
 */
public class Request extends Message {

    private static final Logger logger = LoggerFactory.getLogger(Request.class);

    private static final HashMap<String, Object> QUERY_PARAMETERS = new HashMap<>();

    /**
     * <p>setContentType.</p>
     *
     * @param type a {@link com.atanas.kanchev.testframework.dataservices.api.rest.requetsfactory.Request.ApplicationType} object.
     * @return a {@link com.atanas.kanchev.testframework.dataservices.api.rest.requetsfactory.Request} object.
     */
    public Request setContentType(ApplicationType type) {
        setHeader("Content-Type", type.toString());
        return this;
    }

    /**
     * <p>addQueryParameter.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param value a {@link java.lang.String} object.
     * @return a {@link com.atanas.kanchev.testframework.dataservices.api.rest.requetsfactory.Request} object.
     */
    public Request addQueryParameter(String name, String value) {
        QUERY_PARAMETERS.put(name, value);
        return this;
    }

    /**
     * <p>getQueryParameters.</p>
     *
     * @return a {@link java.util.HashMap} object.
     */
    public HashMap<String, Object> getQueryParameters() {
        return new HashMap<>(QUERY_PARAMETERS);
    }

    public enum ApplicationType {

        JSON("application/json"),
        XML("application/xml");

        private String value;

        ApplicationType(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            logger.debug("ApplicationType{" +
                    "value='" + value + '\'' +
                    '}');
            return this.value;
        }
    }
}
