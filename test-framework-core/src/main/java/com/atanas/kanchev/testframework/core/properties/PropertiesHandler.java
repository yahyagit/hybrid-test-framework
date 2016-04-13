package com.atanas.kanchev.testframework.core.properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static com.atanas.kanchev.testframework.core.exceptions.CustomExceptions.PropertiesExceptions.*;

/**
 * @author Atanas Ksnchev
 */
public abstract class PropertiesHandler {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(PropertiesHandler.class);

    private final static Properties PROP_FILE = new Properties();
    private static final String PROP_FILE_ROOT = "src/test/resources/";
    private static final String PROP_FILE_SUFFIX = ".env.properties";
    private static String propFilePath;

    private static final String ENV_JVM_ARG = "env";

    static {
        final String environment = System.getProperty(ENV_JVM_ARG);

        if (environment != null) {
            if (environment.isEmpty())
                throw new EmptyArgumentException("Empty JVM arg " + ENV_JVM_ARG);
            else {
                propFilePath = PROP_FILE_ROOT + environment.toLowerCase().trim() + PROP_FILE_SUFFIX;
                if (isPropFileThere(propFilePath)) loadPropFile();
                else throw new PropFileNotFoundException("Property file doesn't exist in path " + propFilePath);
            }
        }
    }

    /**
     * Check if file exists in given path
     *
     * @param path file path
     * @return boolean
     */
    private static boolean isPropFileThere(final String path) {

        if (new File(path).exists()) {
            logger.debug("****** Property file found in path: " + propFilePath);
            return true;
        } else {
            logger.debug("****** Property file not found in path: " + propFilePath);
            return false;
        }

    }

    /**
     * Load property file
     */
    private static void loadPropFile() {

        try (FileReader fileReader = new FileReader(propFilePath)) {
            PROP_FILE.load(fileReader);
        } catch (FileNotFoundException e) {
            logger.error("****** Unable to find property file " + propFilePath, e);
        } catch (IOException e) {
            logger.error("****** Unable to read Property file " + propFilePath, e);
        }

    }

    /**
     * Get property by key
     *
     * @param propKey String
     * @return propValue String
     */
    public static String getProperty(final String propKey) {

        String key, value;

        if (propKey == null)
            throw new NullArgumentException("Null argument: property key");
        else if (propKey.isEmpty())
            throw new EmptyArgumentException("Empty argument: property key");
        else
            key = propKey;

        value = PROP_FILE.getProperty(key);
        if (value == null)
            throw new InvalidKeyException("The property file doesn't contain property with key " + propKey);
        else if (value.isEmpty())
            throw new EmptyValueException("The value for property key " + propKey + " is empty");
        else
            return value;
    }
}