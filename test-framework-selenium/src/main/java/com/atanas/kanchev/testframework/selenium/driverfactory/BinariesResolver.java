package com.atanas.kanchev.testframework.selenium.driverfactory;

import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;

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

    private final String path;
    private final String binaryFileName;
    private final String archiveFileName;
    // Local URI
    private final static String LOCAL_TMP_DOWNLOAD_PATH = "./target/selenium/";

    // Target extraction path
    private static final String BIN_ROOT = "src/test/resources/selenium/";
    private static final String CHROME_BIN_WIN_X86_PATH = BIN_ROOT.concat("win/chrome/x86/");
    private static final String CHROME_BIN_LINUX_X64_PATH = BIN_ROOT.concat("linux/chrome/x64/");
    private static final String CHROME_BIN_LINUX_X86_PATH = BIN_ROOT.concat("linux/chrome/x86/");
    private static final String CHROME_BIN_MAC_X86_PATH = BIN_ROOT.concat("mac/chrome/x86/");

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

        this.downloadUri = ROOT_DOWNLOAD_URI_PROP.concat(downloadVersion).concat("/");
        String[] arr = getBinPathAndFileName();
        this.path = arr[0];
        this.binaryFileName = arr[1];
        this.archiveFileName = arr[2];
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
        String path;
        String binaryFileName;
        String prefix = "chromedriver_";
        String archiveFileName;
        if (ARCHITECTURE.equals("x86_64") || ARCHITECTURE.equals("amd64")) {
            if (OS.toLowerCase().contains("windows"))  // Set x64 ChromeDriver on Windows
            {
                path = CHROME_BIN_WIN_X86_PATH + downloadVersion;
                archiveFileName = prefix + "win32.zip";
            } else if (OS.toLowerCase().contains("linux"))  // Set x64 ChromeDriver on Linux
            {
                path = CHROME_BIN_LINUX_X64_PATH + downloadVersion;
                archiveFileName = prefix + "linux64.zip";
            } else if (OS.toLowerCase().contains("mac")) // Set x64 ChromeDriver on mac
            {
                path = CHROME_BIN_MAC_X86_PATH + downloadVersion;
                archiveFileName = prefix + "mac32.zip";
            } else
                throw new RuntimeException("Unable to set chrome binary path: Unsupported OS" + OS);

            binaryFileName = "chromedriver.exe";

        } else {// The current system architecture is x86
            if (OS.toLowerCase().contains("windows"))  // Set x86 ChromeDriver on Windows
            {
                path = CHROME_BIN_WIN_X86_PATH + downloadVersion;
                archiveFileName = prefix + "win32.zip";
            } else if (OS.toLowerCase().contains("mac"))  // Set x86 ChromeDriver on Mac
            {
                path = CHROME_BIN_MAC_X86_PATH + downloadVersion;
                archiveFileName = prefix + "mac32.zip";
            } else if (OS.toLowerCase().contains("linux"))  // Set x86 ChromeDriver on Linux
            {
                path = CHROME_BIN_LINUX_X86_PATH + downloadVersion;
                archiveFileName = prefix + "linux32.zip";
            } else
                throw new RuntimeException("Unable to set chrome binary path: Unsupported OS " + OS);

            binaryFileName = "chromedriver";
        }

        return new String[]{path, binaryFileName, archiveFileName};
    }

    private void download() {

        Path tmpDownloadFolder = Paths.get(LOCAL_TMP_DOWNLOAD_PATH);
        logger.debug("Checking if tmp download folder exists in path " + tmpDownloadFolder);
        if (Files.notExists(tmpDownloadFolder)) {
            logger.debug("The tmp folder doesn't exist, creating " + tmpDownloadFolder);
            try {
                Files.createDirectory(Paths.get(LOCAL_TMP_DOWNLOAD_PATH));
                logger.debug("Tmp folder successfully created");
            } catch (IOException e) {
                logger.error("Unable to create tmp folder ", e);
            }
        }

        Path archiveFilePath = Paths.get(tmpDownloadFolder + "/" + archiveFileName);
        logger.debug("Checking for existing archive file in path " + archiveFilePath);
        if (Files.exists(archiveFilePath))
            logger.debug("Archive already exists in path " + archiveFilePath + ", skipping download");
        else {

            URL url = null;
            try {
                url = new URL(downloadUri.concat(archiveFileName));
                logger.debug("Configured download URL " + url);
            } catch (MalformedURLException e) {
                logger.error("Malformed URL Exception ", e);
            }
            try (ReadableByteChannel rbc = Channels.newChannel(url.openStream())) {
                FileOutputStream fos = new FileOutputStream(LOCAL_TMP_DOWNLOAD_PATH.concat(archiveFileName));
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

    /**
     * Unzip a downloaded zip file (this will implicitly overwrite any existing files)
     *
     * @param archiveFile              The downloaded zip file
     * @param targetFileExtractionPath Path to extracted file
     * @return boolean
     * @throws IOException
     */
    private File unzipFile(File archiveFile, String targetFileExtractionPath, String fileName) throws IOException {
        logger.debug("Extracting binary from the archive file...");
        try (ZipFile zip = new ZipFile(archiveFile)) {
            try {
                zip.getEntries().nextElement();
                return copyFileToDisk(zip.getInputStream(zip.getEntries().nextElement()), targetFileExtractionPath, fileName);
            } catch (NoSuchElementException nsee) {
                throw new NoSuchElementException("The archive file doesn't contain any files");
            }
        }
    }

    /**
     * Copy a file from an inputsteam to disk
     *
     * @param inputStream     A valid iput stream to read
     * @param pathToExtractTo Path of the file we want to create
     * @param filename        Filename of the file we want to create
     * @return Absolute path of the newly created file (Or existing file if overwriteFilesThatExist is set to false)
     * @throws IOException
     */
    private File copyFileToDisk(InputStream inputStream, String pathToExtractTo, String filename) throws IOException {
//        if (!overwriteFilesThatExist) {
        File[] existingFiles = new File(pathToExtractTo).listFiles();
        if (null != existingFiles && existingFiles.length > 0) {
            for (File existingFile : existingFiles) {
                String existingFilename = existingFile.getName();
                if (existingFilename.equals(filename)) {
                    logger.info("Binary '" + existingFilename + "' Exists: true");
                    logger.info("Using existing '" + existingFilename + "'binary.");
                    return existingFile;
                }
            }
        }
//        }

        File outputFile = new File(pathToExtractTo, filename);
        try {
            if (!outputFile.exists() && !outputFile.getParentFile().mkdirs() && !outputFile.createNewFile()) {
                throw new IOException("Unable to create " + outputFile.getAbsolutePath());
            }
            logger.debug("Extracting binary '" + filename + "'...");
            FileUtils.copyInputStreamToFile(inputStream, outputFile);
            logger.debug("Binary copied to " + outputFile.getAbsolutePath());
            if (!outputFile.setExecutable(true) && !outputFile.canExecute()) {
                logger.warn("Unable to set executable flag (+x) on " + filename);
            }
        } finally {
            inputStream.close();
        }

        return outputFile;
    }

    ChromeDriverService configureChromeDriverService() {

        logger.debug("Configuring chromedriver binaries");

        ChromeDriverService.Builder builder = new ChromeDriverService.Builder();
        File file = null;
        if (Files.exists(Paths.get(path + File.separator + binaryFileName))) {
            file = new File(path + File.separator + binaryFileName);
            logger.debug("Using driver executable " + file.getAbsolutePath());
            builder.usingDriverExecutable(file);
        } else {
            if (Files.exists(Paths.get(path))) {
                file = new File(path.concat("/").concat(archiveFileName));
                logger.debug("Using extracted file " + file.getAbsolutePath());
                builder.usingDriverExecutable(file);
            } else {
                download();
                try {
                    file = unzipFile(new File(LOCAL_TMP_DOWNLOAD_PATH.concat("/").concat(archiveFileName)), path, binaryFileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                builder.usingDriverExecutable(file);
            }
        }

        builder.withSilent(true);
        return builder.build();

    }

    class ChromeBinariesResolver {
    }
}

