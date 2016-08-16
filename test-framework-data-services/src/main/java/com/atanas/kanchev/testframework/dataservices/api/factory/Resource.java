package com.atanas.kanchev.testframework.dataservices.api.factory;

import com.atanas.kanchev.testframework.commons.wrappers.IContext;
import com.atanas.kanchev.testframework.dataservices.context.APIResourceContext;
import com.mashape.unirest.http.HttpMethod;
import com.mashape.unirest.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.atanas.kanchev.testframework.dataservices.api.factory.Executor.*;

/**
 * The type Resource.
 *
 * @author Atanas Kanchev
 */
public class Resource implements IContext {

    private static final Logger logger = LoggerFactory.getLogger(Resource.class);

    protected final StringBuilder endpoint = new StringBuilder();
    protected final StringBuilder url = new StringBuilder();

    private Request request;
    private Response response;

    private HttpMethod httpMethod;

    /**
     * Instantiates a new Resource.
     *
     * @param httpMethod the http method type {@code com.mashape.unirest.http.HttpMethod}
     */
    public Resource(HttpMethod httpMethod) {

        this.httpMethod = httpMethod;
        this.request = new Request();
        this.response = new Response();
        APIResourceContext apiResourceContext = new APIResourceContext(this);
        context().addContext(apiResourceContext);

    }

    // GETTERS //

    /**
     * Gets request
     *
     * @return the request {@link Resource#request}
     */
    public Request getRequest() {

        return this.request;

    }

    /**
     * Gets response.
     *
     * @return the response {@link Resource#response}
     */
    public Response getResponse() {

        return this.response;
    }

    public String getEndpoint() {
        return endpoint.toString();
    }

    // SETTERS //

    /**
     * Sets request {@link Resource#request}.
     *
     * @param request the request
     */
    public Resource setRequest(Request request) {

        this.request = request;

        return this;
    }

    /**
     * Append to {@link Resource#endpoint}
     *
     * @param append {@code java.lang.String}
     * @return this
     */
    public Resource appendToEndpoint(String append) {

        this.endpoint.append(append);

        return this;
    }

    /**
     * Execute.
     */
    public Resource exec() {

        url.append(endpoint.toString());

        logger.debug("> Request URL: " + url);
        logger.debug("> Request headers: " + getRequest().getHeaders());
        logger.debug("> Request cookies: " + getRequest().getCookies());
        logger.debug("> Request body: " + getRequest().getBody());

        HttpResponse<String> response;
        switch (httpMethod) {
            case GET:
                response = GET(url.toString(), getRequest().getHeaders());
                break;
            case POST:
                response = POST(url.toString(), getRequest().getHeaders(), getRequest().getBody());
                break;
            case PUT:
                response = PUT(url.toString(), getRequest().getHeaders(), getRequest().getBody());
                break;
            case DELETE:
                response = DELETE(url.toString(), getRequest().getHeaders(), getRequest().getBody());
                break;
            default:
                throw new RuntimeException("Implement me " + httpMethod);
        }
        if (response != null) {
            setRespStatus(response);
            setRespMessage(response);
        } else {
            logger.debug("Null response");
        }
        return this;
    }

    private void setRespStatus(HttpResponse<String> response) {

        this.response.setStatusCode(response.getStatus());
        this.response.setReason(response.getStatusText());
        logger.debug("> Response status and text: " + "{" + response.getStatus() + "," + response.getStatusText() + "}");

    }

    private void setRespMessage(HttpResponse<String> response) {

        if (response.getHeaders().getFirst("Content-Type") != null &&
                response.getHeaders().getFirst("Content-Type").contains("application/json")) {
            this.response.setBody(response.getBody());
        }
        logger.debug("> Response body: " + this.response.getBody());
    }
}
