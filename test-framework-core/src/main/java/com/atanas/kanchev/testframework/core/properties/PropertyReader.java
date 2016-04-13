package com.atanas.kanchev.testframework.core.properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static com.atanas.kanchev.testframework.core.exceptions.CustomExceptions.PropertiesExceptions.*;

/**
 * Property Reader
 *
 * @author Atanas Ksnchev
 */
public final class PropertyReader {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(PropertyReader.class);

    private Properties propFile = null;
    private static String propFilePath;

    private static final String ENV_PROP_FILE_ROOT = "src/test/resources/";
    private static final String ENV_PROP_FILE_SUFFIX = ".env.properties";
    private static final String ENV_JVM_ARG = "env";

    public PropertyReader() {

        final String environment = System.getProperty(ENV_JVM_ARG);

        if (environment != null) {
            if (environment.isEmpty())
                throw new EmptyArgumentException("Empty JVM arg " + ENV_JVM_ARG);
            else {
                propFilePath = ENV_PROP_FILE_ROOT + environment.toLowerCase().trim() + ENV_PROP_FILE_SUFFIX;
                if (isPropFileThere(propFilePath)) {
                    propFile = new Properties();
                    loadPropFile(propFilePath);
                } else throw new PropFileNotFoundException("Property file doesn't exist in path " + propFilePath);
            }
        } else throw new NullArgumentException("Null JVM argument \"env\"");
    }

    public PropertyReader(final String propertyFileName) {

        if (propertyFileName == null) throw new NullArgumentException("Null argument propertyFileName");
        if (propertyFileName.isEmpty()) throw new EmptyArgumentException("Empty argument propertyFileName");

        propFile = new Properties();
        propFilePath = ENV_PROP_FILE_ROOT + propertyFileName;
        if (isPropFileThere(propFilePath)) loadPropFile(propFilePath);
        else throw new PropFileNotFoundException("Property file doesn't exist in path " + propFilePath);
    }

    /**
     * Check if file exists in given path
     *
     * @param path file path
     * @return boolean
     */
    private static boolean isPropFileThere(final String path) {

        if (Files.exists(Paths.get(path))) {
            logger.debug("Property file exists in path: " + propFilePath);
            return true;
        } else {
            logger.debug("****** Property file not found in path: " + propFilePath);
            return false;
        }

    }

    /**
     * Load property file
     */
    private PropertyReader loadPropFile(String propFilePath) {

        try (FileReader fileReader = new FileReader(propFilePath)) {
            propFile.load(fileReader);
            logger.debug("Loaded property file from path " + propFilePath);
        } catch (FileNotFoundException e) {
            logger.error("****** Unable to find property file " + propFilePath, e);
        } catch (IOException e) {
            logger.error("****** Unable to read Property file " + propFilePath, e);
        }

        return this;
    }

    /**
     * Get property by key
     *
     * @param propKey String property key
     * @return propValue String property value
     */
    public final String getProperty(final String propKey) {

        String key, value;

        if (propKey == null)
            throw new NullArgumentException("Null argument: property key");
        else if (propKey.isEmpty())
            throw new EmptyArgumentException("Empty argument: property key");
        else
            key = propKey;

        value = propFile.getProperty(key);
        if (value == null)
            throw new InvalidKeyException("The property file doesn't contain property with key " + propKey);
        else if (value.isEmpty())
            throw new EmptyValueException("The value for property key " + propKey + " is empty");
        else
            return value;
    }
}