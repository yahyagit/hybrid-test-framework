package com.atanas.kanchev.testframework.selenium.driverfactory;

import org.openqa.selenium.chrome.ChromeDriverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Selenium ChromeDriver binaries resolver
 *
 * @author Atanas Kanchev
 */
final class BinariesResolver implements IRootDriver {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(BinariesResolver.class);

    // Prop file keys
    private final static String ROOT_DOWNLOAD_URI_PROP = "https://chromedriver.storage.googleapis.com/";
    private final static String CHROME_BIN_VERSION_PROP = IRootDriver.getSeleniumPropFile().getProperty("chrome.bin.version");

    // JVM args
    private static final String CHROME_BIN_VERSION_JVM_ARG = JVMArgsFactory.getChromeBinaryVersion();

    // Remote URI
    private final String downloadUri;
    private final String downloadVersion;
    private final String downloadFileName;

    // Local URI
    private final static String LOCAL_TMP_DOWNLOAD_PATH = "./target/selenium/";
    private final String targetExtrPath;

    // Target extraction path
    private static final String BIN_ROOT = "src/test/resources/selenium/";
    private static final String CHROME_BIN_WIN_X86_PATH = BIN_ROOT.concat("win/chrome/x86/");
    private static final String CHROME_BIN_LINUX_X64_PATH = BIN_ROOT.concat("linux/chrome/x64/");
    private static final String CHROME_BIN_LINUX_X86_PATH = BIN_ROOT.concat("linux/chrome/x86/");
    private static final String CHROME_BIN_MAC_X86_PATH = BIN_ROOT.concat("mac/chrome/x86/");

    // Selenium System property names
    private static final String CHROME_BIN_SYS_PROP_NAME = "webdriver.chrome.driver";

    /**
     * Constructor
     */
    BinariesResolver() {

        // configure target chrome binary version
        if (CHROME_BIN_VERSION_JVM_ARG != null) {
            logger.debug("Using JVM arg to configure Chrome binary version");
            downloadVersion = CHROME_BIN_VERSION_JVM_ARG;
        } else {
            logger.debug("Using selenium.properties file to configure chrome binary version");
            downloadVersion = CHROME_BIN_VERSION_PROP;
            if (downloadVersion == null)
                throw new NullPointerException("Missing property " + "chrome.bin.version" + " in selenium.properties file");
        }

        downloadUri = ROOT_DOWNLOAD_URI_PROP.concat(downloadVersion).concat("/");
        String[] arr = getBinPathAndFileName();
        targetExtrPath = arr[0];
        downloadFileName = arr[1];
    }

    /**
     * Configure Chrome Binary system property </br>
     * The binary version to be used is controlled in two ways:</br>
     * - using the value of {@link BinariesResolver#CHROME_BIN_VERSION_PROP} in {@link IRootDriver#SELENIUM_PROPS_FILE_PATH}
     * - using the value of JVM argument {@link BinariesResolver#CHROME_BIN_VERSION_JVM_ARG}
     * If no JVM argument is specified the selenium properties file setup will be used
     *
     * @return the configured path to the chrome binary based on the current system architecture and OS
     */
    private String[] getBinPathAndFileName() {


        // configure the target path for the extracted binary and its file name based on current OS architecture
        String targetExtrPath;
        String prefix = "chromedriver_";
        String fileName;
        if (ARCHITECTURE.equals("x86_64") || ARCHITECTURE.equals("amd64")) {
            if (OS.toLowerCase().contains("windows"))  // Set x64 ChromeDriver on Windows
            {
                targetExtrPath = CHROME_BIN_WIN_X86_PATH + downloadVersion;
                fileName = prefix + "win32.zip";
            } else if (OS.toLowerCase().contains("linux"))  // Set x64 ChromeDriver on Linux
            {
                targetExtrPath = CHROME_BIN_LINUX_X64_PATH + downloadVersion;
                fileName = prefix + "linux64.zip";
            } else if (OS.toLowerCase().contains("mac")) // Set x64 ChromeDriver on mac
            {
                targetExtrPath = CHROME_BIN_MAC_X86_PATH + downloadVersion;
                fileName = prefix + "mac32.zip";
            } else
                throw new RuntimeException("Unable to set chrome binary path: Unsupported OS" + OS);

        } else {// The current system architecture is x86
            if (OS.toLowerCase().contains("windows"))  // Set x86 ChromeDriver on Windows
            {
                targetExtrPath = CHROME_BIN_WIN_X86_PATH + downloadVersion;
                fileName = prefix + "win32.zip";
            } else if (OS.toLowerCase().contains("mac"))  // Set x86 ChromeDriver on Mac
            {
                targetExtrPath = CHROME_BIN_MAC_X86_PATH + downloadVersion;
                fileName = prefix + "mac32.zip";
            } else if (OS.toLowerCase().contains("linux"))  // Set x86 ChromeDriver on Linux
            {
                targetExtrPath = CHROME_BIN_LINUX_X86_PATH + downloadVersion;
                fileName = prefix + "linux32.zip";
            } else
                throw new RuntimeException("Unable to set chrome binary path: Unsupported OS " + OS);
        }

        logger.debug("File name " + fileName);
        logger.debug("File extraction path " + targetExtrPath);

        return new String[]{targetExtrPath, fileName};
    }



    private boolean isExtractionDirExisting() {
        Path path = Paths.get(targetExtrPath);
        logger.debug("Checking if the target extraction dir exists in path: " + path);
        return Files.exists(path);

    }

    /**
     * Set WebDriver System property
     *
     * @param propName  name
     * @param propValue value
     */
    private void setWebDriverSystemProperty(final String propName, final String propValue) {

        if (propName == null || propValue == null)
            throw new NullPointerException("Null argument is not permitted");
        else if (propName.isEmpty() || propValue.isEmpty())
            throw new IllegalArgumentException("Empty argument is not permitted");
        else {
            logger.debug("Configuring System property: " + propName + " with value: " + propValue);
            System.setProperty(propName, propValue);
        }

    }

    void download() {

        logger.debug("Checking if tmp download folder exists in path " + LOCAL_TMP_DOWNLOAD_PATH);
        if (Files.notExists(Paths.get(LOCAL_TMP_DOWNLOAD_PATH))) {
            logger.debug("The folder doesn't exist, creating " + LOCAL_TMP_DOWNLOAD_PATH);
            try {
                Files.createDirectory(Paths.get(LOCAL_TMP_DOWNLOAD_PATH));
            } catch (IOException e) {
                logger.error("Unable to create folder ", e);
            }
        }

        logger.debug("Target download folder exists, checking for existing file " + downloadFileName);
        if (Files.exists(Paths.get(LOCAL_TMP_DOWNLOAD_PATH.concat(downloadFileName))))
            logger.debug("Binary already exists in path " + LOCAL_TMP_DOWNLOAD_PATH + " , skipping download");
        else {

            URL url = null;
            try {
                url = new URL(downloadUri.concat(downloadFileName));
                logger.debug("Configured download URL " + url);
            } catch (MalformedURLException e) {
                logger.error("Malformed URL Exception ", e);
            }
            try (ReadableByteChannel rbc = Channels.newChannel(url.openStream())) {
                FileOutputStream fos = new FileOutputStream(LOCAL_TMP_DOWNLOAD_PATH.concat(downloadFileName));
                logger.debug("Downloading file " + url);
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
                fos.close();
                rbc.close();
                logger.debug("File successfully downloaded");
            } catch (IOException e) {
                logger.error("Error while downloading the file", e);
            }

        }
    }

    File extract(final String zipFilePath, final String extractPath) {

        File newFile = null;
        try {
            //get the zip file content
            ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath));
            //get the zipped file list entry
            ZipEntry ze = zis.getNextEntry();
            byte[] buffer = new byte[1024];

            while (ze != null) {

                String fileName = ze.getName();
                newFile = new File(extractPath + File.separator + fileName);

                logger.debug("Unzipping file: " + newFile.getAbsoluteFile());

                //create all non exists folders
                //else you will hit FileNotFoundException for compressed folder
                new File(newFile.getParent()).mkdirs();

                FileOutputStream fos = new FileOutputStream(newFile);

                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }

                fos.close();
                ze = zis.getNextEntry();
            }

            zis.closeEntry();
            zis.close();

            logger.debug("Unzipping completed");

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return newFile;

    }

    ChromeDriverService getChromeBinaries() {

        logger.debug("Configuring chromedriver binaries");

        ChromeDriverService.Builder builder = new ChromeDriverService.Builder();

        File file = null;
        if (isExtractionDirExisting()) {
            logger.debug("The chromedriver binary already exists");
            builder.usingDriverExecutable(file);
        } else {
            download();
            file = extract(LOCAL_TMP_DOWNLOAD_PATH.concat(downloadFileName), targetExtrPath);
            builder.usingDriverExecutable(file);

        }



        builder.withSilent(true);
        ChromeDriverService service = builder.build();

        return service;


    }
}
