package com.atanas.kanchev.testframework.dataservices.api.factory;

import com.mashape.unirest.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The type Resource.
 *
 * @author Atanas Kanchev
 */
public class Resource extends Executor {

    private static final Logger logger = LoggerFactory.getLogger(Resource.class);

    protected final StringBuilder endpoint = new StringBuilder();
    protected final StringBuilder url = new StringBuilder();

    private Request request;
    private Response response;

    private HttpMethodsEnum methodEnum;

    /**
     * Instantiates a new Resource.
     *
     * @param methodEnum the http method type {@link HttpMethodsEnum}
     */
    public Resource(HttpMethodsEnum methodEnum) {

        this.methodEnum = methodEnum;
        this.request = new Request();
        this.response = new Response();

    }

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
     * Execute.
     */
    public Resource exec() {

        url.append(endpoint.toString());

        logger.debug("> Request URL: " + url);
        logger.debug("> Request headers: " + getRequest().getHeaders());
        logger.debug("> Request cookies: " + getRequest().getCookies());
        logger.debug("> Request body: " + getRequest().getBody());

        HttpResponse<String> response = null;
        switch (methodEnum) {
            case GET:
                response = GET(url.toString(), getRequest().getHeaders());
                break;
            case POST:
                if (getRequest().getBody() == null)
                    response = POST(url.toString(), getRequest().getHeaders());
                else
                    response = POST(url.toString(), getRequest().getHeaders(), getRequest().getBody());
                break;
            case PUT:
                response = PUT(url.toString(), getRequest().getHeaders(), getRequest().getBody());
                break;
            case DELETE:
                response = DELETE(url.toString(), getRequest().getHeaders(), getRequest().getBody());
                break;
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

    /**
     * The enum http method types.
     */
    protected enum HttpMethodsEnum {

        GET,
        POST,
        PUT,
        DELETE

    }
}