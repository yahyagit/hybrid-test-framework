package com.atanas.kanchev.testframework.dataservices.api.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

/**
 * The type Request.
 *
 * @author Atanas Kanchev
 */
public class Request extends Message {

    private static final Logger logger = LoggerFactory.getLogger(Request.class);

    private static final HashMap<String, String> QUERY_PARAMETERS = new HashMap<>();

    /**
     * Sets content type.
     *
     * @param type the type {@link ApplicationType}
     * @return this
     */
    public Request setContentType(ApplicationType type) {
        setHeader("Content-Type", type.toString());
        return this;
    }

    /**
     * Add query parameter to the request.
     *
     * @param name  the name
     * @param value the value
     * @return this
     */
    public Request addQueryParameter(String name, String value) {
        QUERY_PARAMETERS.put(name, value);
        return this;
    }

    /**
     * Get shallow copy of {@link Request#QUERY_PARAMETERS}
     *
     * @return the query parameters
     */
    public static HashMap<String, String> getQueryParameters() {
        return new HashMap<>(QUERY_PARAMETERS);
    }

    /**
     * The enum Application type.
     */
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