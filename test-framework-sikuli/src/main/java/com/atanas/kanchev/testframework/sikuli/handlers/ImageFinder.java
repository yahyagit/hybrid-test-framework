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

package com.atanas.kanchev.testframework.sikuli.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * <p>ImageFinder class.</p>
 *
 * @author Atanas Kanchev
 */
public final class ImageFinder {

    private static final Logger logger = LoggerFactory.getLogger(ImageFinder.class);

    public static void saveImage(BufferedImage image, String imageName) {

        String path = "./target/sikuli-screenshotOnFailure/";

        Path tmpDownloadFolder = Paths.get(path);
        logger.debug(
            "Checking if sikulix screenshotOnFailure folder exists in path " + tmpDownloadFolder);
        if (Files.notExists(tmpDownloadFolder)) {
            logger.debug("The folder doesn't exist, creating " + tmpDownloadFolder);
            try {
                Files.createDirectory(Paths.get(path));
                logger.debug("Tmp folder successfully created");
            } catch (IOException e) {
                logger.error("Unable to create folder ", e);
            }
        }

        Path imageFilePath = Paths.get(tmpDownloadFolder + "/" + imageName + ".png");
        logger.debug("Checking for existing image file in path " + imageFilePath);
        if (Files.exists(imageFilePath))
            imageFilePath = Paths
                .get(tmpDownloadFolder + "/" + imageName + System.currentTimeMillis() + ".png");

        logger.debug("Saving image to: " + imageFilePath);
        try {
            ImageIO.write(image, "png", new File(imageFilePath.toString()));
        } catch (IOException e) {
            logger.error("Unable to save image: " + e);
        }
    }

}
