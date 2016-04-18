package com.atanas.kanchev.testframework.sikuli;

import org.sikuli.basics.Settings;
import org.sikuli.script.FindFailed;
import org.sikuli.script.ImagePath;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.Thread.sleep;

/**
 * @author Atanas Ksnchev
 */
public class Sikuli {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(Sikuli.class);

    private Screen screenRegion;

    public Sikuli() {
        try {
            Settings.ActionLogs = false;
            this.screenRegion = new Screen();
            screenRegion.setAutoWaitTimeout(5);
            ImagePath.setBundlePath(System.getProperty("user.dir"));
        } catch (NoClassDefFoundError | ExceptionInInitializerError e) {
            logger.debug("No Screen(s) present, image based commands will not work!");
        }
    }

    /**
     * From the current screen click the image defined in the filepath
     *
     * @author Iain Macdonald
     *
     * @param filePath
     *            Can be an individual file or a directory of images
     * @return true if element exists and is clicked
     */
    public boolean findImageClick(String filePath){
        if (new File(filePath).listFiles() != null) {
            return searchDirectory(filePath, "click");
        }
        try {
            screenRegion.click(filePath);
        } catch (FindFailed e) {
            return false;
        }
        return true;
    }

    /**
     * From the current screen double-click the image defined in the filepath
     *
     * @author Iain Macdonald
     *
     * @param filePath
     *            Can be an individual file or a directory of images
     * @return true if element exists and is clicked
     */
    public boolean findImageDoubleClick(String filePath){
        if (new File(filePath).listFiles() != null) {
            return searchDirectory(filePath, "doubleClick");
        }
        try {
            screenRegion.doubleClick(filePath);
        } catch (FindFailed e) {
            return false;
        }
        return true;
    }

    /**
     * From the current screen find and image click and type a string param
     *
     * @author Ben Kirby
     *
     * @param filePath
     *           Can be an individual file or a directory of images
     * @param text
     *            Text to enter in the
     * @return true if element is clicked
     */
    public boolean inputText(String filePath, String text){
        if (new File(filePath).listFiles() != null) {
            return searchDirectory(filePath, "inputText", text);
        }
        try {
            screenRegion.type(filePath, text, 0);
        } catch (FindFailed e) {
            return false;
        }
        return true;
    }

    /**
     * From the current screen Navigate to an image by filepath
     *
     * @author Ben Kirby
     *
     * @param filePath
     *           Can be an individual file or a directory of images
     * @return true if element appears
     */
    public boolean findImage(String filePath){
        if (new File(filePath).listFiles() != null) {
            return searchDirectory(filePath, "hover");
        }
        try {
            screenRegion.hover(filePath);

        } catch (FindFailed e) {
            return false;
        }
        return true;
    }

//    /**
//     * From the current screen wait for an image to be present
//     *
//     * @author Ben Kirby
//     *
//     * @param filePath
//     *            Can be an individual file or a directory of images
//     * @return true if element appears
//     */
//    public boolean waitForImage(String... filePath){
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
     * @author Ben Kirby
     *
     * @param filePath
     *            Can be an individual file or a directory of images
     * @param savedImagePath
     *            imagePath of saved Image
     * @return true if element appears
     */
    public boolean findImageCaptureImageToRight(String filePath, String savedImagePath){
        return findImageCaptureImageToRight(filePath, savedImagePath, 75);
    }

    /**
     * From the current screen get the Image to the Right
     *
     * @author Ben Kirby
     *
     * @param filePath
     *            Can be an individual file or a directory of images
     * @param savedImagePath
     *            imagePath of saved Image
     * @param pixelSize
     * 				Size of the image to be captured
     * @return true if element appears
     */
    public boolean findImageCaptureImageToRight(String filePath, String savedImagePath, int pixelSize){
        if (new File(filePath).listFiles() != null) {
            return searchDirectory(filePath, "findImageCaptureImageToRight", savedImagePath);
        }
        try {
            BufferedImage image = screenRegion.capture(screenRegion.find(filePath).right(pixelSize)).getImage();

            ImageIO.write(image, "png", new File(savedImagePath));
        } catch (FindFailed | IOException e1) {
            return false;
        }
        return true;
    }

    /**
     * From the current screen get the Image to the Left
     *
     * @author Tony Lawson
     *
     * @param filePath
     *            Can be an individual file or a directory of images
     * @param savedImagePath
     *            imagePath of saved Image
     * @return true if element appears
     */
    public boolean findImageCaptureImageToLeft(String filePath, String savedImagePath){
        return findImageCaptureImageToLeft(filePath, savedImagePath, 75);
    }

    /**
     * From the current screen get the Image to the Left
     *
     * @author Tony Lawson
     *
     * @param filePath
     *            Can be an individual file or a directory of images
     * @param savedImagePath
     *            imagePath of saved Image
     * @param pixelSize
     * 				Size of the image to be captured
     * @return true if element appears
     */
    public boolean findImageCaptureImageToLeft(String filePath, String savedImagePath, int pixelSize){
        if (new File(filePath).listFiles() != null) {
            return searchDirectory(filePath, "findImageCaptureImageToLeft", savedImagePath);
        }
        try {
            BufferedImage image = screenRegion.capture(screenRegion.find(filePath).left(pixelSize)).getImage();

            ImageIO.write(image, "png", new File(savedImagePath));
        } catch (FindFailed | IOException e1) {
            return false;
        }
        return true;
    }

    /**
     * From the current screen get the Image above
     *
     * @author Tony Lawson
     *
     * @param filePath
     *            Can be an individual file or a directory of images
     * @param savedImagePath
     *            imagePath of saved Image
     * @return true if element appears
     */
    public boolean findImageCaptureImageAbove(String filePath, String savedImagePath){
        return findImageCaptureImageAbove(filePath, savedImagePath, 75);
    }

    /**
     * From the current screen get the Image above
     *
     * @author Tony Lawson
     *
     * @param filePath
     *            Can be an individual file or a directory of images
     * @param savedImagePath
     *            imagePath of saved Image
     * @param pixelSize
     * 				Size of the image to be captured
     * @return true if element appears
     */
    public boolean findImageCaptureImageAbove(String filePath, String savedImagePath, int pixelSize){
        if (new File(filePath).listFiles() != null) {
            return searchDirectory(filePath, "findImageCaptureImageAbove", savedImagePath);
        }
        try {
            BufferedImage image = screenRegion.capture(screenRegion.find(filePath).above(pixelSize)).getImage();

            ImageIO.write(image, "png", new File(savedImagePath));
        } catch (FindFailed | IOException e1) {
            return false;
        }
        return true;
    }

    /**
     * From the current screen get the Image to the below
     *
     * @author Tony Lawson
     *
     * @param filePath
     *            Can be an individual file or a directory of images
     * @param savedImagePath
     *            imagePath of saved Image
     * @return true if element appears
     */
    public boolean findImageCaptureImageBelow(String filePath, String savedImagePath){
        return  findImageCaptureImageBelow(filePath, savedImagePath, 75);
    }

    /**
     * From the current screen get the Image to the below
     *
     * @author Tony Lawson
     *
     * @param filePath
     *            Can be an individual file or a directory of images
     * @param savedImagePath
     *            imagePath of saved Image
     * @param pixelSize
     * 				Size of the image to be captured
     * @return true if element appears
     */
    public boolean findImageCaptureImageBelow(String filePath, String savedImagePath, int pixelSize){
        if (new File(filePath).listFiles() != null) {
            return searchDirectory(filePath, "findImageCaptureImageBelow", savedImagePath);
        }
        try {
            BufferedImage image = screenRegion.capture(screenRegion.find(filePath).below(pixelSize)).getImage();

            ImageIO.write(image, "png", new File(savedImagePath));
        } catch (FindFailed | IOException e1) {
            return false;
        }
        return true;
    }

    /**
     * From the current screen navigate to image and type text to the right.
     *
     * @author Iain Macdonald
     *
     * @param filePath
     *            Can be an individual file or a directory of images
     * @param text
     *            Text to type
     * @return true if element appears
     */
    public boolean findImageTypeToRight(String filePath, String text){
        if (new File(filePath).listFiles() != null) {
            return searchDirectory(filePath, "findImageTypeToRight", text);
        }
        try {
            screenRegion.find(filePath).right(50).type(text);
        } catch (FindFailed e) {
            return false;
        }
        return true;
    }

    /**
     * From the current screen navigate to image and type text to the left.
     *
     * @author Iain Macdonald
     *
     * @param filePath
     *            Can be an individual file or a directory of images
     * @param text
     *            Text to type
     * @return true if element appears
     */
    public boolean findImageTypeToLeft(String filePath, String text){
        if (new File(filePath).listFiles() != null) {
            return searchDirectory(filePath, "findImageTypeToLeft", text);
        }

        try {
            screenRegion.find(filePath).left(50).type(text);
        } catch (FindFailed e) {
            return false;
        }
        return true;
    }

    /**
     * From the current screen navigate to image and click to the left.
     *
     * @author Iain Macdonald
     *
     * @param filePath
     *           Can be an individual file or a directory of images
     * @return true if element appears
     */
    public boolean findImageClickToLeft(String filePath){
        if (new File(filePath).listFiles() != null) {
            return searchDirectory(filePath, "findImageClickToLeft");
        }
        try {
            screenRegion.find(filePath).left(10).click();
        } catch (FindFailed e) {
            return false;
        }
        return true;
    }

    /**
     * From the current screen navigate to image and click to the left.
     *
     * @author Iain Macdonald
     *
     * @param filePath
     *            Can be an individual file or a directory of images
     * @return true if element appears
     */
    boolean findImageClickToRight(String filePath){
        if (new File(filePath).listFiles() != null) {
            return searchDirectory(filePath, "findImageClickToRight");
        }
        try {
            screenRegion.find(filePath).right(10).click();
        } catch (FindFailed e) {
            return false;
        }
        return true;
    }

    /**
     * From the current screen navigate to image and type text underneath.
     *
     * @author Iain Macdonald
     *
     * @param filePath
     *            Can be an individual file or a directory of images
     * @param text
     *            Text to type
     * @return true if element appears
     */
    public boolean findImageTypeToBottom(String filePath, String text){
        if (new File(filePath).listFiles() != null) {
            return searchDirectory(filePath, "findImageTypeToBottom", text);
        }
        try {
            screenRegion.find(filePath).below(50).type(text);
        } catch (FindFailed e) {
            return false;
        }
        return true;
    }

    /**
     * From the current screen navigate to image and type text at Centre point.
     *
     * @author Ben Kirby
     *
     * @param filePath
     *            Can be an individual file or a directory of images
     * @param text
     *            Text to type
     * @return true if element appears
     */
    public boolean findImageTypeOnCentre(String filePath, String text){
        if (new File(filePath).listFiles() != null) {
            return searchDirectory(filePath, "findImageTypeOnCentre", text);
        }
        try {
            screenRegion.type(screenRegion.find(filePath).getCenter(), text);
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
//     * @param filePath
//     *            Can be an individual file or a directory of images
//     * @param text
//     *            Text to enter in the
//     * @return true if element text matches
//     */
//    public boolean findPartialTextInImage(String filePath, String text) throws Exception{
//        if (new File(filePath).listFiles() != null) {
//            return searchDirectory(filePath, "findPartialTextInImage", text);
//        }
//        AbbyyImageParser abbyy = new AbbyyImageParser();
//        abbyy.setImgDir(filePath);
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
//     * @param filePath
//     *           Can be an individual file or a directory of images
//     * @param text
//     *            Text to enter in the
//     * @return true if element text matches
//     */
//    public boolean findPreciseTextInImage(String filePath, String text) throws Exception{
//        if (new File(filePath).listFiles() != null) {
//            return searchDirectory(filePath, "findPreciseTextInImage", text);
//        }
//        AbbyyImageParser abbyy = new AbbyyImageParser();
//        abbyy.setImgDir(filePath);
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
     * @author Ben Kirby
     *
     * @param startPointImagePath
     * @param endPointImagePath
     * @return true if element exists and is clicked
     */
    public boolean swipeBetweenImages(String startPointImagePath, String endPointImagePath){
        try {
            screenRegion.dragDrop(startPointImagePath, endPointImagePath);
        } catch (FindFailed e) {
            return false;
        }
        return true;
    }

    /**
     * @author Ben Kirby
     *
     * @param imagePath
     * @param iterations
     * @return true if image is found
     */
    public boolean findImageUsingScrollDown(String imagePath, int iterations){
        screenRegion.setAutoWaitTimeout(2.5);
        int i = 0;
        boolean imageFound = false;
        do{
            if(findImage(imagePath)){
                imageFound = true;
                break;
            }
            screenRegion.type(Key.DOWN);
            screenRegion.type(Key.DOWN);
            screenRegion.type(Key.DOWN);
            i++;
        }while(i < iterations);
        screenRegion.setAutoWaitTimeout(30);
        if(!imageFound){
            logger.error("Image not found via scrolling within timeout limit");
        }
        return imageFound;
    }

    /**
     * @author Ben Kirby
     *
     * @param imagePath
     * @param iterations
     * @return true if image is found
     */
    public boolean findImageUsingScrollUp(String imagePath, int iterations){
        screenRegion.setAutoWaitTimeout(2.5);
        int i = 0;
        boolean imageFound = false;
        do{
            if(findImage(imagePath)){
                imageFound = true;
                break;
            }
            screenRegion.type(Key.UP);
            screenRegion.type(Key.UP);
            screenRegion.type(Key.UP);
            i++;
        }while(i < iterations);
        screenRegion.setAutoWaitTimeout(30);
        if(!imageFound){
            logger.error("Image not found via scrolling within timeout limit");
        }
        return imageFound;
    }

    /**
     * @author Ben Kirby
     *
     * @param imagePath
     * @param iterations
     * @return true if image is found
     */
    public boolean findImageUsingScrollRight(String imagePath, int iterations){

        screenRegion.setAutoWaitTimeout(2.5);
        int i = 0;
        boolean imageFound = false;
        do{
            if(findImage(imagePath)){
                imageFound = true;
                break;
            }
            screenRegion.type(Key.RIGHT);
            screenRegion.type(Key.RIGHT);
            screenRegion.type(Key.RIGHT);
            i++;
        }while(i < iterations);
        screenRegion.setAutoWaitTimeout(30);
        if(!imageFound){
            logger.error("Image not found via scrolling within timeout limit");
        }
        return imageFound;
    }

    /**
     * From the existing Screen press a set of generic shortcut keys i.e. SPACE,
     * F12, ESC
     *
     * Example usage: findPressButton(Key.F12) or findPressButton(Key.SPACE)
     *
     * @author Iain Macdonald
     *
     * @param specialKey
     *            - to be used with the Key class {@link Key}
     *
     * @return true if button is pressed
     */
    boolean findPressButton(String specialKey){
        int result = screenRegion.type(specialKey);

        if (result != 1) {

            return false;
        }
        return true;
    }

    /**
     *
     * @param minSimilarity
     * @return true if value is set with no errors
     */
    boolean setMinimumSimilarityForImage(double minSimilarity){
        if (minSimilarity > 1.0 || minSimilarity < 0) {
            logger.error("The value entered for minSimilarity is invalid, enter a value between 0 & 1");
            return false;
        }
        Settings.MinSimilarity = minSimilarity;
        logger.debug("MinSimilarity value set to " + Settings.MinSimilarity);
        return true;
    }

    private boolean searchDirectory(String filePath, final String action) {

        final File[] folder = new File(filePath).listFiles();
        if (folder == null) {
            logger.error("Check folder exists and contains images");
            return false;
        }
        int j = 0;
        final AtomicBoolean bool = new AtomicBoolean();
        bool.set(false);
        ExecutorService executor = Executors.newFixedThreadPool(folder.length);
        do {
            for (final File file : folder) {

                executor.submit(new Runnable() {

                    @Override
                    public void run() {
                        if (!file.getName().contains(".DS_Store") && screenRegion.exists(file.getAbsolutePath(), 0) != null) {
                            bool.set(true);
                            if(action != null){
                                switch (action){
                                    case "hover":
                                        findImage(file.getAbsolutePath());
                                        break;
                                    case "click":
                                        findImageClick(file.getAbsolutePath());
                                        break;
                                    case "doubleClick":
                                        findImageDoubleClick(file.getAbsolutePath());
                                        break;
                                    case "findImageClickToLeft":
                                        findImageClickToLeft(file.getAbsolutePath());
                                        break;
                                    case "findImageClickToRight":
                                        findImageClickToRight(file.getAbsolutePath());
                                        break;
                                }
                            }

                        }

                    }
                });
            }
            if (bool.get() != false) {
                executor.shutdown();
                return true;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            j++;
        } while (screenRegion.getAutoWaitTimeout() > j);
        logger.error("Unable to find any of the images in the specified timeframe");
        executor.shutdown();
        return false;
    }

    private boolean searchDirectory(String filePath, final String action, final String text) {

        final File[] folder = new File(filePath).listFiles();
        if (folder == null) {
            logger.error("Check folder exists and contains images");
            return false;
        }
        int j = 0;
        final AtomicBoolean bool = new AtomicBoolean();
        bool.set(false);
        ExecutorService executor = Executors.newFixedThreadPool(folder.length);
        do {
            for (final File file : folder) {

                executor.submit(new Runnable() {

                    @Override
                    public void run() {
                        if (!file.getName().contains(".DS_Store") && screenRegion.exists(file.getAbsolutePath(), 0) != null) {
                            bool.set(true);
                            if(action != null){
                                switch (action){
                                    case "inputText":
                                        inputText(file.getAbsolutePath(), text);
                                        break;
                                    case "findImageCaptureImageToRight":
                                        findImageCaptureImageToRight(file.getAbsolutePath(), text);
                                        break;
                                    case "findImageCaptureImageToLeft":
                                        findImageCaptureImageToLeft(file.getAbsolutePath(), text);
                                        break;
                                    case "findImageCaptureImageAbove":
                                        findImageCaptureImageAbove(file.getAbsolutePath(), text);
                                        break;
                                    case "findImageCaptureImageBelow":
                                        findImageCaptureImageBelow(file.getAbsolutePath(), text);
                                        break;
//                                    case "findPartialTextInImage":
//                                        findPartialTextInImage(file.getAbsolutePath(), text));
//                                        break;
//                                    case "findPreciseTextInImage":
//                                        findPreciseTextInImage(file.getAbsolutePath(), text));
//                                        break;
                                    case "findImageTypeToRight":
                                        findImageTypeToRight(file.getAbsolutePath(), text);
                                        break;
                                    case "findImageTypeOnCentre":
                                        findImageTypeOnCentre(file.getAbsolutePath(), text);
                                        break;
                                    case "findImageTypeToBottom":
                                        findImageTypeToBottom(file.getAbsolutePath(), text);
                                        break;
                                    case "findImageTypeToLeft":
                                        findImageTypeToLeft(file.getAbsolutePath(), text);
                                        break;
                                }
                            }
                        }

                    }
                });
            }if (bool.get() != false) {
                executor.shutdown();
                return true;
            }
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            j++;
        } while (screenRegion.getAutoWaitTimeout() > j);
        logger.error("Unable to find any of the images in the specified timeframe");
        executor.shutdown();
        return false;
    }


}
