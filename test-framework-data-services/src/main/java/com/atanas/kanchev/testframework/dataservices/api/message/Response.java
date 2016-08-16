package com.atanas.kanchev.testframework.dataservices.api.message;

/**
 * The type Response.
 *
 * @author Atanas Kanchev
 */
public class Response extends Message {

    private int statusCode;
    private String reason;

    /**
     * Gets status code.
     *
     * @return the status code {@link Response#statusCode}
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Sets status code.
     *
     * @param statusCode the status code
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * Gets reason.
     *
     * @return the reason {@link Response#reason}
     */
    public String getReason() {
        return reason;
    }

    /**
     * Sets reason.
     *
     * @param reason the reason
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

}
