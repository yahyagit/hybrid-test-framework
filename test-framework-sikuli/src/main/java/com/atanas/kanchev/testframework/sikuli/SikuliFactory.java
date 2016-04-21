package com.atanas.kanchev.testframework.sikuli;

import org.sikuli.basics.Settings;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Sukuli Factory
 *
 * @author Atanas Ksnchev
 */
public final class SikuliFactory {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(SikuliFactory.class);

    private Screen screen;
    private String imageFilePath;

    /**
     * Constructor
     */
    public SikuliFactory() {
        try {
            Settings.ActionLogs = false;
            this.screen = new Screen();
            this.screen.setAutoWaitTimeout(5);
            logger.debug("Using screen ID: " + screen.getID());
        } catch (NoClassDefFoundError | ExceptionInInitializerError e) {
            logger.error("No Screen(s) present, image based commands will not work!", e);
        }
    }

    public boolean click() {

        boolean isPossibleToClick = false;

        try {
            int i = screen.click(imageFilePath);
            if (i == 1) isPossibleToClick = true;
        } catch (FindFailed e) {
            logger.error("Unable to click", e);
        }
        return isPossibleToClick;
    }

    public boolean doubleClick() {

        boolean isPossibleToClick = false;

        try {
            int i = screen.doubleClick(imageFilePath);
            if (i == 1) isPossibleToClick = true;
        } catch (FindFailed e) {
            logger.error("Unable to double click", e);
        }
        return isPossibleToClick;
    }

    /**
     * From the current screen find the image defined in the filepath
     *
     * @param imageFileName The image file name
     * @return true if element exists and is clicked
     */
    public SikuliFactory findImage(String imageFileName) {

        try {
            File file = new ImageFinder(imageFileName).getFile();
            imageFilePath = file.getAbsolutePath();
            screen.hover(imageFilePath);
        } catch (IOException e) {
            logger.error("Unable to read file ", e);
        } catch (FindFailed findFailed) {
            logger.error("Unable find match ", findFailed);
        }

        return this;
    }

//    /**
//     * From the current screen double-click the image defined in the filepath
//     *
//     * @param imageFilePath Can be an individual file or a directory of images
//     * @return true if element exists and is clicked
//     * @author Iain Macdonald
//     */
//    public boolean findImageDoubleClick(String imageFilePath) {
//        try {
//            screen.doubleClick(imageFilePath);
//        } catch (FindFailed e) {
//            return false;
//        }
//        return true;
//    }

    /**
     * From the current screen find and image click and type a string param
     *
     * @param text Text to enter in the
     * @return true if element is clicked
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

//    /**
//     * From the current screen Navigate to an image by filepath
//     *
//     * @param imageFilePath Can be an individual file or a directory of images
//     * @return true if element appears
//     * @author Ben Kirby
//     */
//    public boolean findImage(String imageFilePath) {
//
//        try {
//            screen.hover(imageFilePath);
//
//        } catch (FindFailed e) {
//            return false;
//        }
//        return true;
//    }

//    /**
//     * From the current screen wait for an image to be present
//     *
//     * @author Ben Kirby
//     *
//     * @param imageFilePath
//     *            Can be an individual file or a directory of images
//     * @return true if element appears
//     */
//    public boolean waitForImage(String... imageFilePath){
//        int waitTime = 0;
//        boolean found = false;
//        String context = driver.getContext();
//        switchToContextNativeApp(driver);
//        waitForImage = true;
//        while (waitTime < appiumImageWaitTime && !found) {
//            if (findImageAppium(driver, imagePath)) {
//                found = true;
//                logger.debug("Image found: " + imagePath);
//                break;
//            } else {
//                wait(1000);
//                waitTime++;
//            }
//        }
//        switchToContext(driver, context);
//        waitForImage = false;
//        return found;
//    }

    /**
     * From the current screen get the Image to the Right
     *
     * @param savedImagePath imagePath of saved Image
     * @return true if element appears
     */
    public boolean captureImageToRight(String savedImagePath) {
        return captureImageToRight(savedImagePath, 75);
    }

    /**
     * From the current screen get the Image to the Right
     *
     * @param saveImagePath imagePath of saved Image
     * @param pixelSize     Size of the image to be captured
     * @return true if element appears
     */
    public boolean captureImageToRight(String saveImagePath, int pixelSize) {

        try {
            BufferedImage image = screen.capture(screen.find(imageFilePath).right(pixelSize)).getImage();
            ImageIO.write(image, "png", new File(saveImagePath));
        } catch (FindFailed | IOException e1) {
            return false;
        }
        return true;
    }

    /**
     * From the current screen get the Image to the Left
     *
     * @param savedImagePath imagePath of saved Image
     * @return true if element appears
     */
    public SikuliFactory captureImageToLeft(String savedImagePath) {
        return captureImageToLeft(savedImagePath, 75);
    }

    /**
     * From the current screen get the Image to the Left
     *
     * @param saveImagePath imagePath of saved Image
     * @param pixelSize     Size of the image to be captured
     * @return true if element appears
     */
    public SikuliFactory captureImageToLeft(String saveImagePath, int pixelSize) {

        try {
            BufferedImage image = screen.capture(screen.find(imageFilePath).left(pixelSize)).getImage();
            Path p = Paths.get(System.getProperty("user.dir").concat("/screenshots"));
            logger.debug("Saving image to " + p);
            ImageIO.write(image, "png", new File(p.toString()));
        } catch (FindFailed findFailed) {
            logger.error("Unable to find image: " + findFailed);
        } catch (IOException ioe) {
            logger.error("Unable to save file: " + ioe);
        }

        return this;
    }

    /**
     * From the current screen get the Image above
     *
     * @param saveImagePath imagePath of saved Image
     * @return true if element appears
     */
    public boolean captureImageAbove(String saveImagePath) {
        return captureImageAbove(saveImagePath, 75);
    }

    /**
     * From the current screen get the Image above
     *
     * @param saveImagePath imagePath of saved Image
     * @param pixelSize     Size of the image to be captured
     * @return true if element appears
     */
    public boolean captureImageAbove(String saveImagePath, int pixelSize) {

        try {
            BufferedImage image = screen.capture(screen.find(imageFilePath).above(pixelSize)).getImage();
            ImageIO.write(image, "png", new File(saveImagePath));
        } catch (FindFailed | IOException e1) {
            return false;
        }
        return true;
    }

    /**
     * From the current screen get the Image to the below
     *
     * @param savedImagePath imagePath of saved Image
     * @return true if element appears
     */
    public boolean captureImageBelow(String savedImagePath) {
        return captureImageBelow(savedImagePath, 75);
    }

    /**
     * From the current screen get the Image to the below
     *
     * @param savedImagePath imagePath of saved Image
     * @param pixelSize      Size of the image to be captured
     * @return true if element appears
     */
    public boolean captureImageBelow(String savedImagePath, int pixelSize) {

        try {
            BufferedImage image = screen.capture(screen.find(imageFilePath).below(pixelSize)).getImage();
            ImageIO.write(image, "png", new File(savedImagePath));
        } catch (FindFailed | IOException e1) {
            return false;
        }
        return true;
    }

    /**
     * From the current screen navigate to image and type text to the right.
     *
     * @param text Text to type
     * @return true if element appears
     */
    public boolean typeToRight(String text) {

        try {
            screen.find(imageFilePath).right(50).type(text);
        } catch (FindFailed e) {
            return false;
        }
        return true;
    }

    /**
     * From the current screen navigate to image and type text to the left.
     *
     * @param text Text to type
     * @return true if element appears
     */
    public boolean typeToLeft(String text) {

        try {
            screen.find(imageFilePath).left(50).type(text);
        } catch (FindFailed e) {
            return false;
        }
        return true;
    }

    /**
     * From the current screen navigate to image and click to the left.
     *
     * @return true if element appears
     */
    public boolean clickToLeft() {

        try {
            screen.find(imageFilePath).left(10).click();
        } catch (FindFailed e) {
            return false;
        }
        return true;
    }

    /**
     * From the current screen navigate to image and click to the left.
     *
     * @return true if element appears
     */
    boolean clickToRight() {

        try {
            screen.find(imageFilePath).right(10).click();
        } catch (FindFailed e) {
            return false;
        }
        return true;
    }

    /**
     * From the current screen navigate to image and type text underneath.
     *
     * @param text Text to type
     * @return true if element appears
     */
    public boolean typeToBottom(String text) {
        try {
            screen.find(imageFilePath).below(50).type(text);
        } catch (FindFailed e) {
            return false;
        }
        return true;
    }

    /**
     * From the current screen navigate to image and type text at Centre point.
     *
     * @param text Text to type
     * @return true if element appears
     */
    public boolean typeOnCentre(String text) {

        try {
            screen.type(screen.find(imageFilePath).getCenter(), text);
        } catch (FindFailed e) {
            return false;
        }
        return true;
    }

//    /**
//     * From the current screen an image is checked against the Abbyy API for its
//     * Text Value This is Matched against the string param
//     *
//     * @author Iain Macdonald
//     *
//     * @param imageFilePath
//     *            Can be an individual file or a directory of images
//     * @param text
//     *            Text to enter in the
//     * @return true if element text matches
//     */
//    public boolean findPartialTextInImage(String imageFilePath, String text) throws Exception{
//        if (new File(imageFilePath).listFiles() != null) {
//            return searchDirectory(imageFilePath, "findPartialTextInImage", text);
//        }
//        AbbyyImageParser abbyy = new AbbyyImageParser();
//        abbyy.setImgDir(imageFilePath);
//        String value = "";
//        try {
//            value = abbyy.performRecognition();
//        } catch (Exception e) {
//
//            logger.debug("Unable To Process Image");
//        }
//
//        logger.debug("Fount Text in Image: " + value);
//
//        if (value.contains(text)) {
//            logger.debug("Text on Image: " + value + " matches the input: " + text);
//            return true;
//        } else {
//            logger.debug("Text on Image: " + value + " does not match the input: " + text);
//            return false;
//        }
//
//    }

//    /**
//     * From the current screen an image is checked against the Abbyy API for its
//     * Text Value This is Matched against the string param
//     *
//     * @author Iain Macdonald
//     *
//     * @param imageFilePath
//     *           Can be an individual file or a directory of images
//     * @param text
//     *            Text to enter in the
//     * @return true if element text matches
//     */
//    public boolean findPreciseTextInImage(String imageFilePath, String text) throws Exception{
//        if (new File(imageFilePath).listFiles() != null) {
//            return searchDirectory(imageFilePath, "findPreciseTextInImage", text);
//        }
//        AbbyyImageParser abbyy = new AbbyyImageParser();
//        abbyy.setImgDir(imageFilePath);
//        String value = "";
//        try {
//            value = abbyy.performRecognition();
//        } catch (Exception e) {
//
//            logger.debug("Unable To Process Image");
//        }
//
//        logger.debug("Fount Text in Image: " + value);
//
//        if (value.equals(text)) {
//            logger.debug("Text on Image: " + value + " matches the input: " + text);
//            return true;
//        } else {
//            logger.debug("Text on Image: " + value + " does not match the input: " + text);
//            return false;
//        }
//    }

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
        } catch (FindFailed e) {
            return false;
        }
        return true;
    }

    /**
     * @param imagePath
     * @param iterations
     * @return true if image is found
     */
    public boolean findImageUsingScrollDown(String imagePath, int iterations) {
        screen.setAutoWaitTimeout(2.5);
        int i = 0;
        boolean imageFound = false;
        do {
            if (findImage(imagePath).imageFilePath != null) {
                imageFound = true;
                break;
            }
            screen.type(Key.DOWN);
            screen.type(Key.DOWN);
            screen.type(Key.DOWN);
            i++;
        } while (i < iterations);
        screen.setAutoWaitTimeout(30);
        if (!imageFound) {
            logger.error("Image not found via scrolling within timeout limit");
        }
        return imageFound;
    }

    /**
     * @param imagePath
     * @param iterations
     * @return true if image is found
     */
    public boolean findImageUsingScrollUp(String imagePath, int iterations) {
        screen.setAutoWaitTimeout(2.5);
        int i = 0;
        boolean imageFound = false;
        do {
            if (findImage(imagePath).imageFilePath != null) {
                imageFound = true;
                break;
            }
            screen.type(Key.UP);
            screen.type(Key.UP);
            screen.type(Key.UP);
            i++;
        } while (i < iterations);
        screen.setAutoWaitTimeout(30);
        if (!imageFound) {
            logger.error("Image not found via scrolling within timeout limit");
        }
        return imageFound;
    }

    /**
     * @param imagePath
     * @param iterations
     * @return true if image is found
     */
    public boolean findImageUsingScrollRight(String imagePath, int iterations) {

        screen.setAutoWaitTimeout(2.5);
        int i = 0;
        boolean imageFound = false;
        do {
            if (findImage(imagePath).imageFilePath != null) {
                imageFound = true;
                break;
            }
            screen.type(Key.RIGHT);
            screen.type(Key.RIGHT);
            screen.type(Key.RIGHT);
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

        if (result != 1) {

            return false;
        }
        return true;
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

}