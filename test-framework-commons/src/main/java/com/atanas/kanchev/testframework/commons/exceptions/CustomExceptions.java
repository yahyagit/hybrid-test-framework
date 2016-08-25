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

package com.atanas.kanchev.testframework.commons.exceptions;

/**
 * <p>CustomExceptions class.</p>
 *
 * @author Atanas Kanchev
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


        public static class NullReferenceException extends RuntimeException {

            public NullReferenceException(String message) {
                super(message);
            }

            public NullReferenceException() {
                super("Null Reference Exception");
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


        public static class EmptyArgumentException extends RuntimeException {

            public EmptyArgumentException(String message) {
                super(message);
            }
        }
    }


    public static class Properties {

        public static class PropFileNotFoundException extends RuntimeException {

            public PropFileNotFoundException(String message) {
                super(message);
            }
        }


        public static class EmptyValueException extends RuntimeException {

            public EmptyValueException(String message) {
                super(message);
            }
        }


        public static class InvalidKeyException extends RuntimeException {

            public InvalidKeyException(String message) {
                super(message);
            }
        }

    }


    public static class Sikuli {

        public static class ImageMatchNotFoundException extends RuntimeException {

            public ImageMatchNotFoundException(String message) {
                super(message);
            }

            public ImageMatchNotFoundException(String message, Throwable cause) {
                super(message, cause);
            }
        }


        public static class UnableToInteractException extends RuntimeException {

            public UnableToInteractException(String message) {
                super(message);
            }

            public UnableToInteractException(String message, Throwable cause) {
                super(message, cause);
            }
        }

    }
}
