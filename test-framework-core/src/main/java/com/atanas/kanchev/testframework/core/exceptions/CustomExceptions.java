package com.atanas.kanchev.testframework.core.exceptions;

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

        /**
         * Custom exception - empty argument
         */
        public static class EmptyArgumentException extends RuntimeException {

            public EmptyArgumentException(String message) {
                super(message);
            }
        }
    }

    /**
     * The type Web handler.
     */
    public static class WebHandler {
    }


    /**
     * Custom exceptions
     */
    public static class Properties {

        /**
         * Custom exception - prop file not found
         */
        public static class PropFileNotFoundException extends RuntimeException {

            public PropFileNotFoundException(String message) {
                super(message);
            }
        }

        /**
         * Custom exception - null value
         */
        public static class EmptyValueException extends RuntimeException {

            public EmptyValueException(String message) {
                super(message);
            }
        }

        /**
         * Custom exception - empty key
         */
        public static class InvalidKeyException extends RuntimeException {

            public InvalidKeyException(String message) {
                super(message);
            }
        }

    }

    /**
     * Sikuli related custom exceptions
     */
    public static class Sikuli {

        public static class ImageMatchNotFoundException extends RuntimeException {

            public ImageMatchNotFoundException(String message) {
                super(message);
            }
        }

        public static class UnableToInteractException extends RuntimeException {

            public UnableToInteractException(String message) {
                super(message);
            }
        }

    }
}
