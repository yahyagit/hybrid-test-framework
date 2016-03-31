package com.atanas.kanchev.testframework.core;

/**
 * Created by atanas on 24/03/2016.
 */
public final class CustomExceptions {

    public static class Common {

        public static class NullArgumentException extends RuntimeException {

            public NullArgumentException(String message) {
                super(message);
            }

            public NullArgumentException() {
                super("Null Argument Exception");
            }
        }

        public static class IllegalArgumentException extends RuntimeException {

            public IllegalArgumentException() {
                super("Illegal Argument Exception");
            }

            public IllegalArgumentException(String message) {
                super(message);
            }
        }
    }

    public static class WebHandler {
    }


}
