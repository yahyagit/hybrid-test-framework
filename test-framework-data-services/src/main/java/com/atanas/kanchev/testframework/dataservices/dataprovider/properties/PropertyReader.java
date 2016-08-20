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

package com.atanas.kanchev.testframework.dataservices.dataprovider.properties;

import com.atanas.kanchev.testframework.commons.exceptions.CustomExceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import static com.atanas.kanchev.testframework.commons.exceptions.CustomExceptions.Properties.EmptyValueException;
import static com.atanas.kanchev.testframework.commons.exceptions.CustomExceptions.Properties.InvalidKeyException;

/**
 * <p>PropertyReader class.</p>
 *
 * @author Atanas Kanchev
 */
public final class PropertyReader {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(PropertyReader.class);

    private static final Properties properties = new Properties();
    private static final String PROP_FILE_EXTENSION = ".properties";
    private static final String ENV_PROP_FILE_SUFFIX = ".env".concat(PROP_FILE_EXTENSION);
    private static final String ENV_JVM_ARG = "env";

    private static void loadProperties() {

        final String env = System.getProperty(ENV_JVM_ARG);
        if (env != null) {
            if (env.isEmpty())
                throw new CustomExceptions.Common.EmptyArgumentException(
                    "Empty JVM arg " + ENV_JVM_ARG);
            else {
                String propFileName = env.toLowerCase().trim() + ENV_PROP_FILE_SUFFIX;
                loadPropFile(propFileName);
            }
        } else
            throw new CustomExceptions.Common.NullArgumentException("Null JVM argument \"env\"");
    }

    private static void loadPropFile(String propFileName) {

        if (propFileName == null)
            throw new CustomExceptions.Common.NullArgumentException("Null argument propFileName");
        if (propFileName.isEmpty())
            throw new CustomExceptions.Common.EmptyArgumentException("Empty argument propFileName");

        try {
            InputStream inputStream =
                PropertyReader.class.getClassLoader().getResourceAsStream(propFileName);
            logger.debug("Reading property file " + propFileName);
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                logger.error("****** Unable to find property file " + propFileName);
                throw new FileNotFoundException("Unable to find property file" + propFileName);
            }
        } catch (IOException e) {
            logger.error("****** Unable to read Property file " + propFileName, e);
        }

    }

    /**
     * <p>Get property.</p>
     *
     * @param propKey a {@link java.lang.String} object.
     * @return a {@link java.lang.String} object.
     */
    public static String getProp(final String propKey) {

        loadProperties();

        String key, value;

        if (propKey == null)
            throw new CustomExceptions.Common.NullArgumentException("Null argument: property key");
        else if (propKey.isEmpty())
            throw new CustomExceptions.Common.EmptyArgumentException(
                "Empty argument: property key");
        else
            key = propKey;

        value = getPropertyIgnoreCase(key);
        if (value == null)
            throw new InvalidKeyException(
                "The property file doesn't contain property with key " + propKey);
        else if (value.isEmpty())
            throw new EmptyValueException("The value for property key " + propKey + " is empty");
        else
            return value;
    }

    /**
     * <p>Get Property.</p>
     *
     * @param propFileName a {@link java.lang.String} object.
     * @param propKey      a {@link java.lang.String} object.
     * @return a {@link java.lang.String} object.
     */
    public static String getProp(final String propFileName, final String propKey) {

        String fileName = propFileName.endsWith(PROP_FILE_EXTENSION) ?
            propFileName :
            propFileName.concat(PROP_FILE_EXTENSION);

        loadPropFile(fileName);

        String key, value;

        if (propKey == null)
            throw new CustomExceptions.Common.NullArgumentException("Null argument: property key");
        else if (propKey.isEmpty())
            throw new CustomExceptions.Common.EmptyArgumentException(
                "Empty argument: property key");
        else
            key = propKey;

        value = getPropertyIgnoreCase(key);
        if (value == null)
            throw new InvalidKeyException(
                "The property file doesn't contain property with a key [" + propKey + "]");
        else if (value.isEmpty())
            throw new EmptyValueException("The value for property key [" + propKey + "] is empty");
        else
            return value;
    }

    /**
     * Get value from {Properties}
     *
     * @param key a {@link java.lang.String} object.
     * @return a {@link java.lang.String} object.
     */
    private static String getPropertyIgnoreCase(String key) {
        String value = properties.getProperty(key);
        if (null != value)
            return value;

        // Not matching with the actual key then
        Set<Entry<Object, Object>> s = properties.entrySet();
        for (Entry<Object, Object> entry : s) {
            if (key.equalsIgnoreCase((String) entry.getKey())) {
                value = (String) entry.getValue();
                break;
            }
        }
        return value;
    }
}
