package com.atanas.kanchev.testframework.core;

/**
 * Created by atanas on 24/03/2016.
 */
public final class CustomExceptions {

    /**
     * The type Common.
     */
    public static class Common {

        /**
         * The type Null argument exception.
         */
        public static class NullArgumentException extends RuntimeException {

            /**
             * Instantiates a new Null argument exception.
             *
             * @param message the message
             */
            public NullArgumentException(String message) {
                super(message);
            }

            /**
             * Instantiates a new Null argument exception.
             */
            public NullArgumentException() {
                super("Null Argument Exception");
            }
        }

        /**
         * The type Null reference exception.
         */
        public static class NullReferenceException extends RuntimeException {

            /**
             * Instantiates a new Null reference exception.
             *
             * @param message the message
             */
            public NullReferenceException(String message) {
                super(message);
            }

            /**
             * Instantiates a new Null reference exception.
             */
            public NullReferenceException() {
                super("Null Reference Exception");
            }
        }

        /**
         * The type Illegal argument exception.
         */
        public static class IllegalArgumentException extends RuntimeException {

            /**
             * Instantiates a new Illegal argument exception.
             */
            public IllegalArgumentException() {
                super("Illegal Argument Exception");
            }

            /**
             * Instantiates a new Illegal argument exception.
             *
             * @param message the message
             */
            public IllegalArgumentException(String message) {
                super(message);
            }
        }
    }

    /**
     * The type Web handler.
     */
    public static class WebHandler {
    }


}
