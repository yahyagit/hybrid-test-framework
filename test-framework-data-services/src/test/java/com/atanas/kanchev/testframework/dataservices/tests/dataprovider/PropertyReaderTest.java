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

package com.atanas.kanchev.testframework.dataservices.tests.dataprovider;

import com.atanas.kanchev.testframework.commons.exceptions.CustomExceptions;

import com.atanas.kanchev.testframework.dataservices.dataprovider.properties.PropertyReader;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(Enclosed.class)
public class PropertyReaderTest {

    private static final String VALID_KEY = "valid.key";
    private static final String VALID_VALUE = "valid.value";
    private static final String KEY_WITH_EMPTY_VALUE = "key.with.empty.value";
    private static final String INVALID_FILE_NAME = "invalid.filename";
    private static final String INVALID_KEY = "invalid.key";
    private static final String CUSTOM_PROP_FILE_NAME = "custom";

    public static class EnvironmentPropFileTests {

        @BeforeClass
        public static void setUp() throws Exception {
            System.setProperty("env", "test");
        }

        @Test
        public void getValidProperty() throws Exception {
            assertEquals(PropertyReader.getProp(VALID_KEY), VALID_VALUE);
        }

        @Test(expected = CustomExceptions.Common.NullArgumentException.class)
        public void getPropertyWithNullArgument() throws Exception {
            assertNull(PropertyReader.getProp(null));
        }

        @Test(expected = CustomExceptions.Common.EmptyArgumentException.class)
        public void getPropertyWithEmptyArgument() throws Exception {
            assertNull(PropertyReader.getProp(""));
        }

        @Test(expected = CustomExceptions.Properties.InvalidKeyException.class)
        public void getPropertyWithInvalidKey() throws Exception {
            assertNull(PropertyReader.getProp(INVALID_KEY));
        }

        @Test(expected = CustomExceptions.Properties.EmptyValueException.class)
        public void getPropertyWithEmptyValue() throws Exception {
            assertNull(PropertyReader.getProp(KEY_WITH_EMPTY_VALUE));
        }

    }

    public static class CustomPropFileTests {

        @Test
        public void getValidProperty() throws Exception {
            assertEquals(PropertyReader.getProp(CUSTOM_PROP_FILE_NAME, VALID_KEY), VALID_VALUE);
        }

        @Test
        public void getValidPropertyWithExtension() throws Exception {
            assertEquals(PropertyReader.getProp(CUSTOM_PROP_FILE_NAME.concat(".properties"), VALID_KEY), VALID_VALUE);
        }

        @Test(expected = CustomExceptions.Common.NullArgumentException.class)
        public void getPropertyWithNullArgument() throws Exception {
            assertNull(PropertyReader.getProp(CUSTOM_PROP_FILE_NAME, null));
        }

        @Test(expected = CustomExceptions.Common.EmptyArgumentException.class)
        public void getPropertyWithEmptyArgument() throws Exception {
            assertNull(PropertyReader.getProp(CUSTOM_PROP_FILE_NAME, ""));
        }

        @Test(expected = CustomExceptions.Properties.InvalidKeyException.class)
        public void getPropertyWithInvalidKey() throws Exception {

            assertNull(PropertyReader.getProp(CUSTOM_PROP_FILE_NAME, INVALID_KEY));
        }

        @Test(expected = CustomExceptions.Properties.EmptyValueException.class)
        public void getPropertyWithEmptyValue() throws Exception {
            assertNull(PropertyReader.getProp(CUSTOM_PROP_FILE_NAME, KEY_WITH_EMPTY_VALUE));
        }

    }


}
