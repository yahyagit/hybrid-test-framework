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

package com.atanas.kanchev.testframework.dataservices.api.rest.requetsfactory;

/**
 * <p>Response class.</p>
 *
 * @author Atanas Kanchev
 */
public class Response extends Message {

    private int statusCode;
    private String reason;

    /**
     * <p>Getter for the field <code>statusCode</code>.</p>
     *
     * @return a int.
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * <p>Setter for the field <code>statusCode</code>.</p>
     *
     * @param statusCode a int.
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * <p>Getter for the field <code>reason</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getReason() {
        return reason;
    }

    /**
     * <p>Setter for the field <code>reason</code>.</p>
     *
     * @param reason a {@link java.lang.String} object.
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "Response{" +
                "statusCode=" + statusCode +
                ", reason='" + reason + '\'' +
                '}';
    }

}
