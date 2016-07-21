package com.atanas.kanchev.testframework.commons.properties;

import com.atanas.kanchev.testframework.commons.exceptions.CustomExceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.atanas.kanchev.testframework.commons.exceptions.CustomExceptions.Properties.EmptyValueException;
import static com.atanas.kanchev.testframework.commons.exceptions.CustomExceptions.Properties.InvalidKeyException;

/**
 * Property Reader
 *
 * @author Atanas Ksnchev
 */
public abstract class PropertyReader {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(PropertyReader.class);

    private static Properties properties = null;
    private static final String ENV_PROP_FILE_SUFFIX = ".env.properties";
    private static final String ENV_JVM_ARG = "env";

    private static void loadProperties() {

        final String env = System.getProperty(ENV_JVM_ARG);
        if (env != null) {
            if (env.isEmpty())
                throw new CustomExceptions.Common.EmptyArgumentException("Empty JVM arg " + ENV_JVM_ARG);
            else {
                String propFileName = env.toLowerCase().trim() + ENV_PROP_FILE_SUFFIX;
                properties = new Properties();
                loadPropFile(propFileName);
            }
        } else throw new CustomExceptions.Common.NullArgumentException("Null JVM argument \"env\"");

    }

    /**
     * Read from a properties fule by given {@code param propFileName}
     *
     * @param propFileName the name of the properties file, e.g. test.env.properties
     */
    public static void readFile(final String propFileName) {
        if (propFileName == null)
            throw new CustomExceptions.Common.NullArgumentException("Null argument propFileName");
        if (propFileName.isEmpty())
            throw new CustomExceptions.Common.EmptyArgumentException("Empty argument propFileName");

        loadPropFile(propFileName);
    }

    /**
     * Load property file
     */
    private static void loadPropFile(String propFilePath) {

        try (InputStream inputStream = PropertyReader.class.getClassLoader().getResourceAsStream(propFilePath)) {
            if (inputStream != null) {
                if (properties == null) properties = new Properties();
                properties.load(inputStream);
                logger.debug("Loaded property file " + propFilePath);
            }
        } catch (FileNotFoundException e) {
            logger.error("****** Unable to find property file " + propFilePath);
        } catch (IOException e) {
            logger.error("****** Unable to read Property file " + propFilePath, e);
        }

    }

    /**
     * Get property by key
     *
     * @param propKey String property key
     * @return propValue String property value
     */
    public static String getProp(final String propKey) {

        if (properties == null) loadProperties();

        String key, value;

        if (propKey == null)
            throw new CustomExceptions.Common.NullArgumentException("Null argument: property key");
        else if (propKey.isEmpty())
            throw new CustomExceptions.Common.EmptyArgumentException("Empty argument: property key");
        else
            key = propKey;

        value = properties.getProperty(key);
        if (value == null)
            throw new InvalidKeyException("The property file doesn't contain property with key " + propKey);
        else if (value.isEmpty())
            throw new EmptyValueException("The value for property key " + propKey + " is empty");
        else
            return value;
    }
}