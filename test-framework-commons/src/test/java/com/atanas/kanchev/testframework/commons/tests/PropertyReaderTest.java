package com.atanas.kanchev.testframework.commons.tests;

import com.atanas.kanchev.testframework.commons.exceptions.CustomExceptions;
import com.atanas.kanchev.testframework.commons.properties.PropertyReader;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Atanas Ksnchev
 */
@RunWith(Enclosed.class)
public class PropertyReaderTest {

    private static final String VALID_KEY = "valid.key";
    private static final String VALID_VALUE = "valid.value";
    private static final String KEY_WITH_EMPTY_VALUE = "key.with.empty.value";
    private static final String INVALID_FILE_NAME = "invalid.filename";
    private static final String INVALID_KEY = "invalid.key";
    private static final String CUSTOM_PROP_FILE_NAME = "custom.properties";

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
            PropertyReader.readFile(CUSTOM_PROP_FILE_NAME);
            assertEquals(PropertyReader.getProp(VALID_KEY), VALID_VALUE);
        }

        @Test(expected = CustomExceptions.Common.NullArgumentException.class)
        public void getPropertyWithNullArgument() throws Exception {
            PropertyReader.readFile(CUSTOM_PROP_FILE_NAME);
            assertNull(PropertyReader.getProp(null));
        }

        @Test(expected = CustomExceptions.Common.EmptyArgumentException.class)
        public void getPropertyWithEmptyArgument() throws Exception {
            PropertyReader.readFile(CUSTOM_PROP_FILE_NAME);
            assertNull(PropertyReader.getProp(""));
        }

        @Test(expected = CustomExceptions.Properties.InvalidKeyException.class)
        public void getPropertyWithInvalidKey() throws Exception {
            PropertyReader.readFile(CUSTOM_PROP_FILE_NAME);
            assertNull(PropertyReader.getProp(INVALID_KEY));
        }

        @Test(expected = CustomExceptions.Properties.EmptyValueException.class)
        public void getPropertyWithEmptyValue() throws Exception {
            PropertyReader.readFile(CUSTOM_PROP_FILE_NAME);
            assertNull(PropertyReader.getProp(KEY_WITH_EMPTY_VALUE));
        }

    }


}