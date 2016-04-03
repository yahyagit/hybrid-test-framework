package com.atanas.kanchev.testframework.selenium.driverfactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Selenium ChromeDriver binaries resolver
 *
 * @author Atanas Kanchev
 */
public class BinariesResolver implements IRootDriver {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(BinariesResolver.class);

    private String BASE_URI;

    private String downloadPath = "./target/selenium/";

    public BinariesResolver() {
        BASE_URI = IRootDriver.getSeleniumPropFile().getProperty("chrome.base.uri");
        String CHROME_BIN_VERSION = IRootDriver.getSeleniumPropFile().getProperty("chrome.bin.version");
        BASE_URI = BASE_URI.concat(CHROME_BIN_VERSION);
    }

    private String getFileName() {

        String prefix = "chromedriver_";

        String fileName;

        if (ARCHITECTURE.equals("x86_64") || ARCHITECTURE.equals("amd64")) {
            if (OS.toLowerCase().contains("windows"))  // Set x64 ChromeDriver on Windows
                fileName = prefix + "win32.zip";
            else if (OS.toLowerCase().contains("linux"))  // Set x64 ChromeDriver on Linux
                fileName = prefix + "linux64.zip";
            else if (OS.toLowerCase().contains("mac")) // Set x64 ChromeDriver on mac
                fileName = prefix + "mac32.zip";
            else
                throw new RuntimeException("Unable to set chrome binary downloadPath: Unsupported OS" + OS);

        } else {// The current system architecture is x86
            if (OS.toLowerCase().contains("windows"))  // Set x86 ChromeDriver on Windows
                fileName = prefix + "win32.zip";
            else if (OS.toLowerCase().contains("mac"))  // Set x86 ChromeDriver on Mac
                fileName = prefix + "/chromedriver";
            else if (OS.toLowerCase().contains("mac32.zip"))  // Set x86 ChromeDriver on Linux
                fileName = prefix + "linux32.zip";
            else
                throw new RuntimeException("Unable to set chrome binary downloadPath: Unsupported OS " + OS);
        }
        return fileName;
    }


    public void download() {

        String fileName = getFileName();

        logger.debug("Checking if target download folder exists in path " + downloadPath);
        if (Files.notExists(Paths.get(downloadPath))) {
            logger.debug("The folder doesn't exist, creating " + downloadPath);
            try {
                Files.createDirectory(Paths.get(downloadPath));
            } catch (IOException e) {
                logger.error("Unable to create folder ", e);
            }
        }

        logger.debug("Target download folder exists, checking for existing file " + fileName);
        if (!Files.notExists(Paths.get(downloadPath.concat(getFileName()))))
            logger.debug("Binary already exists in path " + downloadPath + " , skipping download");
        else {

            URL url = null;
            try {
                url = new URL(BASE_URI.concat("/").concat(getFileName()));
            } catch (MalformedURLException e) {
                logger.error("Malformed URL Exception ", e);
            }
            try (ReadableByteChannel rbc = Channels.newChannel(url.openStream())) {
                FileOutputStream fos = new FileOutputStream(downloadPath.concat(getFileName()));
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

    public void extract(final String zipFilePath, final String extractPath) {
        try {
            //get the zip file content
            ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath));
            //get the zipped file list entry
            ZipEntry ze = zis.getNextEntry();
            byte[] buffer = new byte[1024];

            while (ze != null) {

                String fileName = ze.getName();
                File newFile = new File(extractPath + File.separator + fileName);

                logger.debug("Unzipping file: " + newFile.getAbsoluteFile());

                //create all non exists folders
                //else you will hit FileNotFoundException for compressed folder
               // new File(newFile.getParent()).mkdirs();

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

    }

    public void getChromeBinaries(String extractPath){
        logger.debug("Configuring chromedriver binaries");

        download();
        extract(downloadPath.concat(getFileName()), extractPath);
    }
}
