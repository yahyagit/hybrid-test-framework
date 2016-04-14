package com.atanas.kanchev.testframework.core.tests;

import com.atanas.kanchev.testframework.core.exceptions.CustomExceptions;
import com.atanas.kanchev.testframework.core.properties.PropertyReader;
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
            assertEquals(new PropertyReader().getProperty(VALID_KEY), VALID_VALUE);
        }

        @Test(expected = CustomExceptions.Common.NullArgumentException.class)
        public void getPropertyWithNullArgument() throws Exception {
            assertNull(new PropertyReader().getProperty(null));
        }

        @Test(expected = CustomExceptions.Common.EmptyArgumentException.class)
        public void getPropertyWithEmptyArgument() throws Exception {
            assertNull(new PropertyReader().getProperty(""));
        }

        @Test(expected = CustomExceptions.PropertiesExceptions.InvalidKeyException.class)
        public void getPropertyWithInvalidKey() throws Exception {
            assertNull(new PropertyReader().getProperty(INVALID_KEY));
        }

        @Test(expected = CustomExceptions.PropertiesExceptions.EmptyValueException.class)
        public void getPropertyWithEmptyValue() throws Exception {
            assertNull(new PropertyReader().getProperty(KEY_WITH_EMPTY_VALUE));
        }

    }

    public static class CustomPropFileTests {

        @Test
        public void getValidProperty() throws Exception {
            assertEquals(new PropertyReader(CUSTOM_PROP_FILE_NAME).getProperty(VALID_KEY), VALID_VALUE);
        }


        @Test(expected = CustomExceptions.Common.NullArgumentException.class)
        public void getValidPropertyNullFileRef() throws Exception {
            assertEquals(new PropertyReader(null).getProperty(VALID_KEY), VALID_VALUE);
        }

        @Test(expected = CustomExceptions.Common.NullArgumentException.class)
        public void getPropertyWithNullArgument() throws Exception {
            assertNull(new PropertyReader(CUSTOM_PROP_FILE_NAME).getProperty(null));
        }

        @Test(expected = CustomExceptions.Common.EmptyArgumentException.class)
        public void getPropertyWithEmptyArgument() throws Exception {
            assertNull(new PropertyReader(CUSTOM_PROP_FILE_NAME).getProperty(""));
        }

        @Test(expected = CustomExceptions.PropertiesExceptions.InvalidKeyException.class)
        public void getPropertyWithInvalidKey() throws Exception {
            assertNull(new PropertyReader(CUSTOM_PROP_FILE_NAME).getProperty(INVALID_KEY));
        }

        @Test(expected = CustomExceptions.PropertiesExceptions.EmptyValueException.class)
        public void getPropertyWithEmptyValue() throws Exception {
            assertNull(new PropertyReader(CUSTOM_PROP_FILE_NAME).getProperty(KEY_WITH_EMPTY_VALUE));
        }

    }


}