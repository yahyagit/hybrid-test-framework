package com.atanas.kanchev.testframework.sikuli;

import com.atanas.kanchev.testframework.commons.exceptions.CustomExceptions;
import org.sikuli.basics.Settings;
import org.sikuli.script.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * SukuliX Implementation
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

    //holds the result of the setImageFilePath operation
    private Match match;

    /**
     * Constructor
     */
    public SikuliXFactory(final String imageFileName) {

        try {
            Settings.ActionLogs = false;
            this.screen = new Screen();
            logger.debug("Using screen ID: " + screen.getID());
            this.screen.setAutoWaitTimeout(5);
            this.imageFilePath = setImageFilePath(imageFileName);
        } catch (NoClassDefFoundError | ExceptionInInitializerError e) {
            logger.error("No Screen(s) present, image based commands will not work!", e);
        }
    }

    /**
     * From the current screen setImageFilePath the image defined in the {@param imageFileName}
     * Hover the mouse pointer to the matched image location on the screen
     *
     * @param imageFileName The image file name
     * @return this
     */
    public SikuliXFactory findImage(String imageFileName) {

        try {
            File file = new ImageFinder(imageFileName).getFile();
            imageFilePath = file.getAbsolutePath();
            this.match = match(imageFileName);
        } catch (FileNotFoundException fnfe) {
            logger.error("Image file Not Found Exception ", fnfe);
        } catch (IOException ioe) {
            logger.error("Unable to read file ", ioe);
        }

        return this;
    }

    /**
     * Left click at the region's last successful match
     * Click center if no lastMatch
     *
     * @return this
     */
    public SikuliXFactory click() {

        if (screen.click() == 1) {
            logger.debug("Successfully clicked");
        } else {
            logger.error("Unable to perform single click");
            throw new CustomExceptions.Sikuli.UnableToInteractException("Unable to perform single click");
        }

        return this;
    }

    /**
     * From the current screen navigate to image and click according to {@param directions}
     *
     * @param direction the direction to click on, ex Directions.LEFT
     * @param px        the pixels to click on
     * @return this
     */
    public SikuliXFactory click(final Directions direction, final int px) {

        switch (direction) {
            case ABOVE:
                if (match.above(px).click() == 1) logger.debug("Successfully clicked " + px + " " + direction);
                else throw new CustomExceptions.Sikuli.UnableToInteractException("Unable to perform click");
                break;
            case LEFT:
                if (match.left(px).click() == 1) logger.debug("Successfully clicked " + px + " " + direction);
                else throw new CustomExceptions.Sikuli.UnableToInteractException("Unable to perform click");
                break;
            case CENTER:
                try {
                    match.getCenter().click();
                    logger.debug("Successfully clicked " + px + " " + direction);
                } catch (Exception e) {
                    throw new CustomExceptions.Sikuli.UnableToInteractException("Unable to perform click");
                }
                break;
            case RIGHT:
                if (match.right(px).click() == 1) logger.debug("Successfully clicked " + px + " " + direction);
                else throw new CustomExceptions.Sikuli.UnableToInteractException("Unable to perform click");
                break;
            case BELOW:
                if (match.below(px).click() == 1) logger.debug("Successfully clicked " + px + " " + direction);
                else throw new CustomExceptions.Sikuli.UnableToInteractException("Unable to perform click");
                break;
            default:
                throw new CustomExceptions.Common.IllegalArgumentException("Not supported direction: " + direction);
        }

        return this;
    }

    /**
     * Double click at the given target location {@link Region#doubleClick()}
     *
     * @return this
     */
    public SikuliXFactory doubleClick() {

        if (screen.doubleClick() == 1) {
            logger.debug("Successfully clicked");
        } else {
            logger.error("Unable to perform double click");
            throw new CustomExceptions.Sikuli.UnableToInteractException("Unable to perform double click");
        }
        return this;
    }

    /**
     * From the current screen double click on image according to {@param directions}
     *
     * @param direction Directions
     * @param px        the pixels to click on
     * @return this
     */
    public SikuliXFactory doubleClick(final Directions direction, final int px) {

        switch (direction) {
            case ABOVE:
                if (match.above(px).doubleClick() == 1)
                    logger.debug("Successfully clicked " + px + " " + direction);
                else throw new CustomExceptions.Sikuli.UnableToInteractException("Unable to perform click");
                break;
            case LEFT:
                if (match.left(px).doubleClick() == 1)
                    logger.debug("Successfully clicked " + px + " " + direction);
                else throw new CustomExceptions.Sikuli.UnableToInteractException("Unable to perform click");
                break;
            case CENTER:
                if (match.doubleClick() == 1) logger.debug("Successfully clicked " + px + " " + direction);
                else throw new CustomExceptions.Sikuli.UnableToInteractException("Unable to perform click");
                break;
            case RIGHT:
                if (match.right(px).doubleClick() == 1)
                    logger.debug("Successfully clicked " + px + " " + direction);
                else throw new CustomExceptions.Sikuli.UnableToInteractException("Unable to perform click");
                break;
            case BELOW:
                if (match.below(px).doubleClick() == 1)
                    logger.debug("Successfully clicked " + px + " " + direction);
                else throw new CustomExceptions.Sikuli.UnableToInteractException("Unable to perform click");
                break;
            default:
                throw new CustomExceptions.Common.IllegalArgumentException("Not supported direction: " + direction);
        }
        return this;
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

        BufferedImage image = null;

        try {
            switch (direction) {
                case ABOVE:
                    image = screen.capture(match.right(pixelSize)).getImage();
                    break;
                case BELOW:
                    image = screen.capture(match.below(pixelSize)).getImage();
                    break;
                case CENTER:
                    image = screen.capture(match).getImage();
                case LEFT:
                    image = screen.capture(match.left(pixelSize)).getImage();
                    break;
                case RIGHT:
                    image = screen.capture(match.right(pixelSize)).getImage();
                    break;
                default:
                    throw new CustomExceptions.Common.IllegalArgumentException("Unsupported direction");
            }
        } catch (Exception e) {
            logger.error("Unable to capture image ", e);
        } finally {
            if (image != null) ImageFinder.saveImage(image, imageName);
        }

        return this;
    }

    /**
     * From the current screen type in a string param {@link Region#type(java.lang.Object, java.lang.String, int)}
     *
     * @param text Text to enter in the
     * @return this
     */

    public SikuliXFactory type(String text) {

        try {
            if (screen.type(match, text, 0) == 1)
                logger.debug("Successfully typed " + text);
        } catch (FindFailed findFailed) {
            logger.error("Unable to type in ", findFailed);
            throw new CustomExceptions.Sikuli.UnableToInteractException("Unable to type in");
        }


        return this;
    }

    /**
     * Type in text in the current {@link SikuliXFactory#match}
     *
     * @param text      value to be typed in
     * @param pixelSize px to shift
     * @param direction relative to the current position
     * @return this
     */
    public SikuliXFactory type(final String text, final int pixelSize, final Directions direction) {

        switch (direction) {
            case RIGHT:
                match.right(pixelSize).type(text);
                break;
            case LEFT:
                match.left(pixelSize).type(text);
                break;
            case CENTER:
                match.type(text);
                break;
            case BELOW:
                match.below(pixelSize).type(text);
                break;
            case ABOVE:
                match.above(pixelSize).type(text);
                break;

        }
        return this;
    }

    /**
     * From the current screen create a swipe/click and drag motion relative
     * from Img1 to Img2
     * Drag from a position and drop to another using left mouse button
     *
     * @param startPointImagePath
     * @param endPointImagePath
     * @return this
     */
    public SikuliXFactory swipeBetweenImages(String startPointImagePath, String endPointImagePath) {
        try {
            screen.dragDrop(match(startPointImagePath), match(endPointImagePath));
        } catch (FindFailed findFailed) {
            logger.error("Unable to swipe");
            throw new CustomExceptions.Sikuli.UnableToInteractException("Unable to swipe");
        }
        return this;
    }

    /**
     * Find image by scrolling
     *
     * @param imagePath  image to setImageFilePath
     * @param iterations number of swipes
     * @param direction  direction of swipes
     * @return imageFound
     */
    public SikuliXFactory findImageByScrolling(final String imagePath, final int iterations, final Directions direction) {

        screen.setAutoWaitTimeout(2.5);
        int i = 0;
        boolean imageFound = false;
        do {
            findImage(imagePath);
            if (this.match != null) {
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
            throw new CustomExceptions.Sikuli.UnableToInteractException("Image not found via scrolling within timeout limit");
        }
        return this;
    }

    /**
     * From the existing Screen press a set of generic shortcut keys i.e. SPACE, F12, ESC
     * {@link Key}
     * <p>
     * Example usage: press(Key.F12) or press(Key.SPACE)
     *
     * @param specialKey - to be used with the Key class
     * @return true if button is pressed
     */
    public SikuliXFactory sendKey(final String specialKey) {

        if (screen.type(String.valueOf(specialKey)) == 1) {
            logger.debug("Special key successfully sent");
        } else {
            logger.error("Unable to send special key " + specialKey);
            throw new CustomExceptions.Sikuli.UnableToInteractException("Unable to send special key " + specialKey);
        }

        return this;
    }

    /**
     * Set Minimum Similarity For Image
     *
     * @param minSimilarity double
     * @return true if value is set with no errors
     */
    public SikuliXFactory setMinimumSimilarityForImage(final double minSimilarity) {
        if (minSimilarity > 1.0 || minSimilarity < 0) {
            logger.error("The value entered for minSimilarity is invalid, enter a value between 0 & 1");
            throw new CustomExceptions.Common.IllegalArgumentException("The value entered for minSimilarity is invalid, enter a value between 0 & 1");
        } else {
            Settings.MinSimilarity = minSimilarity;
            logger.debug("MinSimilarity value set to " + Settings.MinSimilarity);
        }

        return this;
    }

    private Match match(final String imageFileName) {

        Match match = null;

        try {
            setImageFilePath(imageFileName);
            match = screen.find(imageFileName);
            match.hover();
            logger.debug("Match found for image " + imageFileName + " in location " + match.getTarget());
        } catch (FindFailed ffe) {
            logger.error("Unable setImageFilePath a match for image");
        }
        return match;
    }

    private String setImageFilePath(final String imageFileName) {

        if (imageFileName != null) {
            try {
                File file = new ImageFinder(imageFileName).getFile();
                findImage(imageFileName);
                return file.getAbsolutePath();
            } catch (IOException e) {
                logger.error("Unable to setImageFilePath image file", e);
            }

        }
        return null;
    }

    public enum Directions {
        LEFT,
        RIGHT,
        CENTER,
        ABOVE,
        BELOW
    }

}