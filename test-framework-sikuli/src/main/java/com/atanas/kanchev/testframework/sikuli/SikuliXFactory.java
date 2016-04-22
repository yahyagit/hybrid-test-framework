package com.atanas.kanchev.testframework.sikuli;

import com.atanas.kanchev.testframework.core.exceptions.CustomExceptions;
import org.sikuli.basics.Settings;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * SukuliX Implementation Factory
 *
 * @author Atanas Ksnchev
 */
public final class SikuliXFactory {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(SikuliXFactory.class);

    // the screen
    private Screen screen;

    // the source image file path
    private String imageFilePath;

    /**
     * Constructor
     */
    public SikuliXFactory() {
        try {
            Settings.ActionLogs = false;
            this.screen = new Screen();
            this.screen.setAutoWaitTimeout(5);
            logger.debug("Using screen ID: " + screen.getID());
        } catch (NoClassDefFoundError | ExceptionInInitializerError e) {
            logger.error("No Screen(s) present, image based commands will not work!", e);
        }
    }

    /**
     * Left click at the given target location {@link Region#click()}
     *
     * @return boolean result isPossibleToClick
     */
    public boolean click() {

        boolean isPossibleToClick = false;

        if (screen.click() == 1) {
            isPossibleToClick = true;
            logger.debug("Successfully clicked");
        } else {
            logger.error("Unable to perform click");
        }

        return isPossibleToClick;
    }

    /**
     * From the current screen navigate to image and click according to {@param directions}
     *
     * @param directions Directions
     * @return boolean result
     */
    public boolean click(Directions directions) {

        switch (directions) {
            case LEFT:
                try {
                    screen.find(imageFilePath).left(10).click();
                } catch (FindFailed findFailed) {
                    logger.error("Unable to find image: " + findFailed);
                    return false;
                }
                break;
            case RIGHT:
                try {
                    screen.find(imageFilePath).right(10).click();
                } catch (FindFailed findFailed) {
                    logger.error("Unable to find image: " + findFailed);
                    return false;
                }
                break;
            default:
                throw new CustomExceptions.Common.IllegalArgumentException("Not supported directions" + directions);
        }

        return true;
    }

    /**
     * Double click at the given target location {@link Region#doubleClick()}
     *
     * @return boolean result isPossibleToClick
     */
    public boolean doubleClick() {

        boolean isPossibleToClick = false;

        if (screen.doubleClick() == 1) {
            isPossibleToClick = true;
            logger.debug("Successfully double clicked");
        } else {
            logger.error("Unable to perform double click");
        }

        return isPossibleToClick;
    }

    /**
     * From the current screen double click on image according to {@param directions}
     *
     * @param direction Directions
     * @return boolean result isPossibleToClick
     */
    public boolean doubleClick(Directions direction) {
        switch (direction) {
            case LEFT:
                try {
                    screen.find(imageFilePath).left(10).click();
                } catch (FindFailed findFailed) {
                    logger.error("Unable to find image: " + findFailed);
                    return false;
                }
                break;
            case RIGHT:
                try {
                    screen.find(imageFilePath).right(10).click();
                } catch (FindFailed findFailed) {
                    logger.error("Unable to find image: " + findFailed);
                    return false;
                }
                break;
            default:
                throw new CustomExceptions.Common.IllegalArgumentException("Not supported directions" + direction);
        }
        return true;
    }

    /**
     * From the current screen find the image defined in the filepath
     * Move the mouse pointer to the given target location
     *
     * @param imageFileName The image file name
     * @return this
     */
    public SikuliXFactory findImage(String imageFileName) {

        try {
            File file = new ImageFinder(imageFileName).getFile();
            imageFilePath = file.getAbsolutePath();
            try {
                screen.hover(imageFilePath);
                logger.debug("Match found for image " + imageFileName);
            } catch (FindFailed ffe) {
                logger.error("Unable find a match for image", ffe);
            }
        } catch (FileNotFoundException fnfe) {
            logger.error("File Not Found Exception ", fnfe);
        } catch (IOException ioe) {
            logger.error("Unable to read file ", ioe);
        }

        return this;
    }

    /**
     * From the current screen type in a string param {@link Region#type(java.lang.Object, java.lang.String, int)}
     *
     * @param text Text to enter in the
     * @return true if isPossibleToType
     */
    public boolean inputText(String text) {

        boolean isPossibleToType = false;

        try {
            int i = screen.type(imageFilePath, text, 0);
            if (i == 1) isPossibleToType = true;
        } catch (FindFailed e) {
            logger.error("Unable to type in ", e);
        }
        return isPossibleToType;
    }

    /**
     * From the current screen get the Image {@link Screen#capture(org.sikuli.script.Region)}
     *
     * @param imageName file name for the saved image
     * @param pixelSize size of the image to be captured in px
     * @param direction direction
     * @return this
     */
    public SikuliXFactory captureImage(final String imageName, final int pixelSize, final Directions direction) {

        BufferedImage image;

        switch (direction) {
            case ABOVE:
                try {
                    image = screen.capture(screen.find(imageFilePath).right(pixelSize)).getImage();
                    saveImage(image, imageName);
                } catch (FindFailed findFailed) {
                    logger.error("Unable to find image: " + findFailed);
                }
                break;
            case BELOW:
                try {
                    image = screen.capture(screen.find(imageFilePath).below(pixelSize)).getImage();
                    saveImage(image, imageName);
                } catch (FindFailed findFailed) {
                    logger.error("Unable to find image: " + findFailed);
                }
                break;
            case LEFT:
                try {
                    image = screen.capture(screen.find(imageFilePath).left(pixelSize)).getImage();
                    saveImage(image, imageName);
                } catch (FindFailed findFailed) {
                    logger.error("Unable to find image: " + findFailed);
                }
                break;
            case RIGHT:
                try {
                    image = screen.capture(screen.find(imageFilePath).right(pixelSize)).getImage();
                    saveImage(image, imageName);
                } catch (FindFailed findFailed) {
                    logger.error("Unable to find image: " + findFailed);
                }
                break;
            default:
                throw new CustomExceptions.Common.IllegalArgumentException("Unsupported direction");

        }

        return this;
    }

    /**
     * From the current screen navigate to image and type text
     *
     * @param text      value to be typed in
     * @param direction relative to the current position
     * @return boolean result
     */
    public boolean type(String text, final Directions direction) {

        switch (direction) {
            case RIGHT:
                try {
                    screen.find(imageFilePath).right(50).type(text);
                } catch (FindFailed findFailed) {
                    logger.error("Unable to find image: " + findFailed);
                    return false;
                }
                break;
            case LEFT:
                try {
                    screen.find(imageFilePath).left(50).type(text);
                } catch (FindFailed findFailed) {
                    logger.error("Unable to find image: " + findFailed);
                    return false;
                }
                break;
            case CENTER:
                try {
                    screen.type(screen.find(imageFilePath).getCenter(), text);
                } catch (FindFailed findFailed) {
                    logger.error("Unable to find image: " + findFailed);
                    return false;
                }
                break;
            case BELOW:
                try {
                    screen.find(imageFilePath).below(50).type(text);
                } catch (FindFailed findFailed) {
                    logger.error("Unable to find image: " + findFailed);
                    return false;
                }
                break;
            case ABOVE:
                try {
                    screen.find(imageFilePath).above(50).type(text);
                } catch (FindFailed findFailed) {
                    logger.error("Unable to find image: " + findFailed);
                    return false;
                }
                break;

        }
        return true;
    }

    /**
     * From the current screen create a swipe/click and drag motion relative
     * from Img1 to Img2
     *
     * @param startPointImagePath
     * @param endPointImagePath
     * @return true if element exists and is clicked
     */
    public boolean swipeBetweenImages(String startPointImagePath, String endPointImagePath) {
        try {
            screen.dragDrop(startPointImagePath, endPointImagePath);
        } catch (FindFailed findFailed) {
            logger.error("Unable to find image: " + findFailed);
        }
        return true;
    }

    /**
     * Find image by scrolling
     *
     * @param imagePath  image to find
     * @param iterations number of swipes
     * @param direction  direction of swipes
     * @return imageFound
     */
    public boolean findImageByScrolling(final String imagePath, final int iterations, final Directions direction) {

        screen.setAutoWaitTimeout(2.5);
        int i = 0;
        boolean imageFound = false;
        do {
            if (findImage(imagePath).imageFilePath != null) {
                imageFound = true;
                break;
            }

            switch (direction) {
                case ABOVE:
                    screen.type(Key.UP);
                    screen.type(Key.UP);
                    screen.type(Key.UP);
                    break;
                case BELOW:
                    screen.type(Key.DOWN);
                    screen.type(Key.DOWN);
                    screen.type(Key.DOWN);
                    break;
                case LEFT:
                    screen.type(Key.LEFT);
                    screen.type(Key.LEFT);
                    screen.type(Key.LEFT);
                    break;
                case RIGHT:
                    screen.type(Key.RIGHT);
                    screen.type(Key.RIGHT);
                    screen.type(Key.RIGHT);
                    break;
                default:
                    throw new CustomExceptions.Common.IllegalArgumentException("Illegal direction");
            }

            i++;
        } while (i < iterations);
        screen.setAutoWaitTimeout(30);
        if (!imageFound) {
            logger.error("Image not found via scrolling within timeout limit");
        }
        return imageFound;
    }

    /**
     * From the existing Screen press a set of generic shortcut keys i.e. SPACE,
     * F12, ESC
     * <p>
     * Example usage: press(Key.F12) or press(Key.SPACE)
     *
     * @param specialKey - to be used with the Key class {@link Key}
     * @return true if button is pressed
     */
    boolean press(String specialKey) {
        int result = screen.type(specialKey);
        return result == 1;
    }

    /**
     * @param minSimilarity
     * @return true if value is set with no errors
     */
    boolean setMinimumSimilarityForImage(double minSimilarity) {
        if (minSimilarity > 1.0 || minSimilarity < 0) {
            logger.error("The value entered for minSimilarity is invalid, enter a value between 0 & 1");
            return false;
        }
        Settings.MinSimilarity = minSimilarity;
        logger.debug("MinSimilarity value set to " + Settings.MinSimilarity);
        return true;
    }

    private static void saveImage(BufferedImage image, String imageName) {

        String path = "./target/sikuli-screenshots/";

        Path tmpDownloadFolder = Paths.get(path);
        logger.debug("Checking if sikulix screenshots folder exists in path " + tmpDownloadFolder);
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
            imageFilePath = Paths.get(tmpDownloadFolder + "/" + imageName + System.currentTimeMillis() + ".png");

        logger.debug("Saving image to: " + imageFilePath);
        try {
            ImageIO.write(image, "png", new File(imageFilePath.toString()));
        } catch (IOException e) {
            logger.error("Unable to save image: " + e);
        }
    }

    public enum Directions {
        LEFT,
        RIGHT,
        CENTER,
        ABOVE,
        BELOW

    }

}