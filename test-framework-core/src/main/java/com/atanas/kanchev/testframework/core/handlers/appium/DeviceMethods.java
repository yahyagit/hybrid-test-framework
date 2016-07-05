package com.atanas.kanchev.testframework.core.handlers.appium;

import com.atanas.kanchev.testframework.core.handlers.Appium;
import com.atanas.kanchev.testframework.core.handlers.wrappers.IInteract;
import io.appium.java_client.*;
import io.appium.java_client.NoSuchContextException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.sikuli.script.Finder;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Atanas Ksnchev
 */
public class DeviceMethods implements IInteract {

    boolean mobileBrowser;

    final static Logger logger = LoggerFactory.getLogger(Appium.class);

    // Device Context
    String currentContext = null;
    // WebDriver
    AppiumDriver driver;

    // Appium Methods
    String getCurrentContext(AppiumDriver driver) {
        currentContext = driver.getContext();
        logger.debug("Context: " + currentContext);
        return currentContext;
    }

    boolean switchToContextNativeApp(AppiumDriver driver) {
        try {

            driver.context("NATIVE_APP");
            logger.info("Switched to context: " + "NATIVE_APP");
            return true;
        } catch (NoSuchContextException var2) {
            logger.error("Wrong context: " + "NATIVE_APP");
            return false;
        }
    }

    boolean switchToContext(AppiumDriver driver, String context) {
        try {
            driver.context(context);
            logger.info("Switched to context: " + context);
            return true;
        } catch (NoSuchContextException var2) {
            logger.error("Wrong context: " + context);
            return false;
        }
    }

    boolean swipeFromElement(AppiumDriver driver, WebElement el, SwipeElementDirection direction, int duration) {

        ((MobileElement) el).swipe(SwipeElementDirection.LEFT, duration);
        return true;
    }

    boolean tapAndroid(AppiumDriver driver, WebElement el) {

        TouchAction action = new TouchAction(driver);
        try {
            if (mobileBrowser) {
                String javascriptToExecute = "var e = document.createEvent('TouchEvent'); e.initUIEvent('touchstart',true,true); e.initUIEvent('touchend',true,true); arguments[0].dispatchEvent(e);";
                js().executeScript(driver, javascriptToExecute, el);

            } else {
                action.tap(el).perform();

            }
            return true;
        } catch (UnsupportedCommandException var2) {
            logger.error("Tap Action not supported for this element");
            return false;
        }
    }

    boolean tapAndroid(ChromeDriver driver, WebElement el) {

        try {
            String javascriptToExecute = "var e = document.createEvent('TouchEvent'); e.initUIEvent('touchstart',true,true); e.initUIEvent('touchend',true,true); arguments[0].dispatchEvent(e);";
            js().executeScript(driver, javascriptToExecute, el);
            return true;
        } catch (UnsupportedCommandException var2) {
            logger.error("Tap Action not supported for this element");
            return false;
        }
    }

    boolean tapAndroid(RemoteWebDriver driver, WebElement el) {

        try {
            String javascriptToExecute = "var e = document.createEvent('TouchEvent'); e.initUIEvent('touchstart',true,true); e.initUIEvent('touchend',true,true); arguments[0].dispatchEvent(e);";
            js().executeScript(driver, javascriptToExecute, el);
            return true;
        } catch (UnsupportedCommandException var2) {
            logger.error("Tap Action not supported for this element");
            return false;
        }
    }

    boolean longPressAndroid(AppiumDriver driver, WebElement el) {

        try {
            if (mobileBrowser) {
                String javascriptToExecute = "var e = document.createEvent('TouchEvent'); e.initUIEvent('touchstart',true,true);arguments[0].dispatchEvent(e);";
                js().executeScript(driver, javascriptToExecute, el);
            } else {

                TouchAction action = new TouchAction(driver);
                action.longPress(el).perform();
            }
            return true;
        } catch (UnsupportedCommandException var2) {
            logger.error("Tap Action not supported for this element");
            return false;
        }
    }

    boolean longPressAndroid(ChromeDriver driver, WebElement el) {

        try {
            String javascriptToExecute = "var e = document.createEvent('TouchEvent'); e.initUIEvent('touchstart',true,true);arguments[0].dispatchEvent(e);";
            js().executeScript(driver, javascriptToExecute, el);
            return true;
        } catch (UnsupportedCommandException var2) {
            this.logger.error("Tap Action not supported for this element");
            return false;
        }
    }

    boolean longPressAndroid(RemoteWebDriver driver, WebElement el) {

        try {
            String javascriptToExecute = "var e = document.createEvent('TouchEvent'); e.initUIEvent('touchstart',true,true);arguments[0].dispatchEvent(e);";
            js().executeScript(driver, javascriptToExecute, el);
            return true;
        } catch (UnsupportedCommandException var2) {
            logger.error("Tap Action not supported for this element");
            return false;
        }
    }

    boolean scrollToElementByText(AppiumDriver driver, String value) {
        currentContext = getCurrentContext(driver);
        if (!currentContext.equals("NATIVE_APP")) {
            switchToContextNativeApp(driver);
        }
        try {
            driver.scrollTo(value);
            switchToContext(driver, currentContext);
            return true;
        } catch (Exception var2) {
            this.logger.error("Not possible to Scroll to element by text: " + value);
            switchToContext(driver, currentContext);
            return false;
        }
    }

    // Capture an image in the Appium Driver, compare that image using
    // sikuli to get X and Y coordinates, and use them
    // With the Tap() function on the Appium Driver.

    /*
     * This function captures takes a screenshot of the Appium Driver
     */
    Screen appiumScreen = null;
    File appiumSikuliImageFile = null;
    File appiumScreenShot = null;
    String appiumImageFolder = System.getProperty("user.dir");
    String appiumSikuliScreenshot = "/appiumSikuliScreenshot";
    Finder finder;
    Match match;
    int x;
    int y;
    int appiumImageWaitTime = 30;
    boolean waitForImage;

    boolean captureImageAppium(AppiumDriver driver, String imageName) {
        driver = (AppiumDriver) new Augmenter().augment(driver);
        // Get the screenshot
        appiumSikuliImageFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        logger.debug("Appium Screenshot Completed");
        try {
            if (waitForImage) {
                appiumScreenShot = new File(appiumImageFolder + "/" + imageName + ".png");
            } else {
                appiumScreenShot = createAppiumFile(imageName);
            }
            // Copy the file to screenshot folder
            FileUtils.copyFile(appiumSikuliImageFile, appiumScreenShot);
            if (driver.getOrientation().toString().contains("LANDSCAPE")) {
                rotateScreenshotToLandscape();
            }
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Take Appium Driver screenshot failed");
            return false;
        }
    }

    File createAppiumFile(String imageName) {
        String screenshotsDir = System.getProperty("screenshotDirectory");
        String screenshotFileName = imageName.concat(RandomStringUtils.randomAlphanumeric(10));

        File outputFolder = null;

        if (!GraphicsEnvironment.isHeadless()) {
//
//            if (GenericUtilities.isJenkinsExecution() || screenshotsDir == null) {
//                screenshotsDir = "src/test/resources/screenshots/mobile/";
//            }

            String environment = System.getProperty("env");

            String folderString = "screenshot";
//                    ? screenshotsDir + Date.from().GenericUtilities.CURRENT_DATE + "/ " + imageName.replaceAll("[^a-zA-Z0-9.-]", "")
//                    : screenshotsDir + GenericUtilities.CURRENT_DATE + "com.williamhill.whgtf"
//                    + imageName.replaceAll("[^a-zA-Z0-9.-]", "") + "/" + environment + "/" + GenericUtilities.CURRENT_TIME;
            try {

                outputFolder = new File(folderString);
                if (!outputFolder.exists()) {
                    outputFolder.mkdirs();
                }
            } catch (Exception e1) {
                logger.error("Unable to create outputFolder!");
            }
            appiumImageFolder = folderString;
            appiumSikuliScreenshot = "/" + screenshotFileName + ".png";
        }

        return new File(outputFolder + "/" + screenshotFileName + ".png");
    }


    void rotateScreenshotToLandscape() {
        try {
            BufferedImage bufferedImage = (BufferedImage) ImageIO
                    .read(appiumScreenShot);
            // Rotate the image to landscape
            AffineTransform tx = new AffineTransform();
            tx.translate(bufferedImage.getHeight() / 2, bufferedImage.getWidth() / 2);
            tx.rotate(Math.toRadians(90));
            tx.translate(-bufferedImage.getWidth() / 2, -bufferedImage.getHeight() / 2);
            wait(500);

            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            wait(500);
            bufferedImage = op.filter(bufferedImage, null);
            wait(500);
            File outputfile = appiumScreenShot;
            ImageIO.write(bufferedImage, "png", outputfile);
            wait(1000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    boolean findImageAppium(AppiumDriver driver, String imagePath) {
        captureImageAppium(driver, appiumSikuliScreenshot);
        org.sikuli.script.Image.reset();
        org.sikuli.script.Image image = org.sikuli.script.Image.create(appiumImageFolder + appiumSikuliScreenshot);
        logger.debug("Sikuli Image Similarity: " + image.getSimilarity());
        this.finder = new Finder(image);
        this.finder.find(imagePath);
        this.match = null;
        if (this.finder.hasNext()) {
            this.match = this.finder.next();
            return true;
        } else {
            return false;
        }

    }

    boolean setImageAsScreen(AppiumDriver driver, String imagePath) {
        boolean found = findImageAppium(driver, imagePath);
        if (this.match == null) {
            logger.error("Sub Image: '" + imagePath
                    + "' does not exist on the Appium Driver Screen. Be sure of capturing the sub image on a 100% resolution");
        }
        return found;
    }

    boolean getCoordinatesAppium(AppiumDriver driver, String imagePath) {
        try {

            setImageAsScreen(driver, imagePath);
            this.x = this.match.getX();
            this.y = this.match.getY();
            boolean validCoordinates = (this.x >= 0 && this.y >= 0);
            logger.debug("X: " + x);
            logger.debug("Y: " + y);

            return validCoordinates;
        } catch (NullPointerException e) {
            return false;
        }
    }

    /*
     * Captures a Screenshot on the Appium Driver (Real Device or Emulator)
     * Compares with a given sub image and takes the Coordinates X,Y using
     * Sikuli Taps on coordinates X,Y on the Appium Driver
     */
    boolean tapImageAppium(AppiumDriver driver, String imagePath) {
        String context = driver.getContext();
        switchToContextNativeApp(driver);
        boolean coordinates = getCoordinatesAppium(driver, imagePath);
        if (coordinates) {
            logger.debug("Tapping on Coordinates X,Y: " + this.x + "," + this.y);
            driver.tap(1, this.x, this.y, 100);
            switchToContext(driver, context);
            return true;
        } else {
            logger.error("Coordinates are not valid");
            return false;
        }
    }

    boolean tapOnCoordinatesAppium(AppiumDriver driver, int x, int y) {
        String context = driver.getContext();
        switchToContextNativeApp(driver);
        logger.debug("Tapping on Coordinates X,Y: " + x + "," + y);
        driver.tap(1, x, y, 100);
        switchToContext(driver, context);

        return true;
    }


    /*
     * This method can be used to control the Wait Time for a particular
     * Scenario If this is not used, default Wait Time will be used (Ex: int
     * appiumImageWaitTime = 30)
     */
    boolean setAppiumImageWaitTime(int time) {
        if (time > 0) {
            this.appiumImageWaitTime = time;
            logger.debug("Appium Image Wait Time changed to: " + this.appiumImageWaitTime);
            return true;
        } else {
            logger.error("Appium Image Wait Time needs to be positive");
            logger.debug("Using default wait time: " + this.appiumImageWaitTime);
            return false;
        }
    }

    boolean wait(int time) {
        try {
            Thread.sleep(time);
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    boolean waitForImageAppium(AppiumDriver driver, String imagePath) {
        int waitTime = 0;
        boolean found = false;
        String context = driver.getContext();
        switchToContextNativeApp(driver);
        waitForImage = true;
        while (waitTime < appiumImageWaitTime && !found) {
            if (findImageAppium(driver, imagePath)) {
                found = true;
                logger.debug("Image found: " + imagePath);
                break;
            } else {
                wait(1000);
                waitTime++;
            }
        }
        switchToContext(driver, context);
        waitForImage = false;
        return found;
    }

    boolean waitForImageAndStoreCoordinatesAppium(AppiumDriver driver, String imagePath) {
        int waitTime = 0;
        boolean found = false;
        String context = driver.getContext();
        switchToContextNativeApp(driver);
        waitForImage = true;
        while (waitTime < appiumImageWaitTime && !found) {
            if (getCoordinatesAppium(driver, imagePath)) {
                found = true;
                logger.debug("Image found: " + imagePath);
                logger.debug("Storing coordinates - X: " + this.x);
                logger.debug("Storing coordinates - Y: " + this.y);
                break;
            } else {
                wait(1000);
                waitTime++;
            }
        }
        switchToContext(driver, context);
        waitForImage = false;
        return found;
    }

    boolean tapOnStoredCoordinatesAppium(AppiumDriver driver) {
        String context = driver.getContext();
        switchToContextNativeApp(driver);
        try {
            logger.debug("Tapping on Coordinates X,Y: " + this.x + "," + this.y);
            driver.tap(1, this.x, this.y, 100);
            switchToContext(driver, context);
            return true;
        } catch (NullPointerException e) {
            logger.error("Coordinates are not valid");
            return false;
        }
    }

    boolean typeInImageAppium(AppiumDriver driver, String imagePath, String text) {
        String context = driver.getContext();
        switchToContextNativeApp(driver);
        boolean validCoordinates = getCoordinatesAppium(driver, imagePath);
        logger.debug("Tapping on Coordinates X,Y: " + this.x + "," + this.y);
        driver.tap(1, this.x, this.y, 100);
        Actions actions = new Actions(driver);
        actions.sendKeys(new CharSequence[]{text});
        switchToContext(driver, context);
        return validCoordinates;
    }

    /*
     * This method will only work for Web Apps at the moment !!! WORK IN
     * PROGRESS !!!
     */
    boolean scrollToImageAppium(AppiumDriver driver, String imagePath) {
        boolean found = false;
        int originalScrollPosition = 0;
        int scrollPositionWeb = getScrollPositionAppium(driver);
        String context = driver.getContext();
        switchToContextNativeApp(driver);
        int nativeX = getDimensionsAppium(driver).getWidth();
        int nativeY = getDimensionsAppium(driver).getHeight();
        logger.debug("Native X,Y:" + nativeX + "," + nativeY);
        switchToContext(driver, context);
        while (!found && scrollPositionWeb != originalScrollPosition) {
            if (findImageAppium(driver, imagePath)) {
                found = true;
                logger.debug("Image found: " + imagePath);
                break;
            } else {
                originalScrollPosition = getScrollPositionAppium(driver);
                switchToContextNativeApp(driver);
                driver.swipe(nativeX / 2, Math.round(nativeY / 1.25f), nativeX / 2, Math.round(nativeY / 2.5f),
                        500);
                switchToContext(driver, context);
                scrollPositionWeb = getScrollPositionAppium(driver);
                logger.debug("Scroll Position: " + scrollPositionWeb);
                wait(3000);

            }
        }
        return found;
    }

    int getScrollPositionAppium(AppiumDriver driver) {
        return Integer.parseInt(driver.findElement(By.xpath("/html/body")).getAttribute("scrollTop"));
    }

    int getPageHeightAppium(AppiumDriver driver) {
        return Integer.parseInt(driver.findElement(By.xpath("/html")).getAttribute("clientHeight"));
    }

    org.openqa.selenium.Dimension getDimensionsAppium(AppiumDriver driver) {
        logger.debug("Position Y: " + driver.manage().window().getPosition().getY());
        logger.debug("Size: " + driver.manage().window().getSize());
        return driver.manage().window().getSize();
    }

    /*
     * This method only works on Native Context, use the
     * switchToContextNativeApp() method first
     */
    boolean rotateDeviceLandscape(AppiumDriver driver) {
        currentContext = getCurrentContext(driver);
        if (!currentContext.equals("NATIVE_APP")) {
            switchToContextNativeApp(driver);
        }
        driver.rotate(ScreenOrientation.LANDSCAPE);
        if (screenOrientation(driver) == ScreenOrientation.LANDSCAPE) {
            logger.debug("Screen Orientation switched to Landscape");
            switchToContext(driver, currentContext);
            return true;
        } else {
            logger.debug("Couldn't switch the Screen Orientation to Landscape");
            switchToContext(driver, currentContext);
            return false;
        }
    }

    /**
     * Reset App Method
     *
     * @param driver
     * @return true when successful
     */
    boolean resetApp(AppiumDriver driver) {
        driver.resetApp();
        return true;
    }

    /**
     * Run App in Background
     *
     * @param driver
     * @param seconds length of time to run app for
     * @return true when successful
     */
    boolean backgroundApp(AppiumDriver driver, int seconds) {
        driver.runAppInBackground(seconds);
        return true;
    }

    /**
     * Launch App Method
     *
     * @param driver
     * @return true when successful
     */
    boolean launchApp(AppiumDriver driver) {
        driver.launchApp();
        return true;
    }

    /**
     * Remove App Method
     *
     * @param driver
     * @param bundleId
     * @return
     */
    public boolean removeApp(AppiumDriver driver, String bundleId) {
        driver.removeApp(bundleId);
        return true;
    }

    /**
     * Close App Method
     *
     * @param driver
     * @return true when successful
     */
    boolean closeApp(AppiumDriver driver) {
        driver.closeApp();
        return true;
    }


    /*
     * This method only works on Native Context, use the
     * switchToContextNativeApp() method first
     */
    boolean rotateDevicePortrait(AppiumDriver driver) {
        currentContext = getCurrentContext(driver);
        if (!currentContext.equals("NATIVE_APP")) {
            switchToContextNativeApp(driver);
        }
        driver.rotate(ScreenOrientation.PORTRAIT);
        if (screenOrientation(driver) == ScreenOrientation.PORTRAIT) {
            logger.debug("Screen Orientation switched to Portrait");
            switchToContext(driver, currentContext);
            return true;
        } else {
            logger.debug("Couldn't switch the Screen Orientation to Portrait");
            switchToContext(driver, currentContext);
            return false;
        }
    }

    /*
     * This method only works on Native Context, use the
     * switchToContextNativeApp() method first
     */
    ScreenOrientation screenOrientation(AppiumDriver driver) {
        return driver.getOrientation();
    }

    boolean installAppAppium(String appPath) {
        driver.installApp(appPath);
        if (driver.isAppInstalled(appPath)) {
            logger.debug("App installed");
            return true;
        } else {
            logger.debug("App was not installed");
            return false;
        }
    }

    void swipeAndroid(AppiumDriver driver, int startX, int startY, int endX, int endY, int duration) {
//        deviceStoreCurrentContext("currentContext");
//        deviceSwitchToContextNativeApp();
        driver.swipe(startX, startY, endX, endY, duration);
//        deviceSwitchToContext("currentContext");

    }

    int getDeviceHeightAndroid(AppiumDriver driver) {
        return driver.manage().window().getSize().getHeight();
    }

    int getDeviceWidthAndroid(AppiumDriver driver) {
        return driver.manage().window().getSize().getWidth();
    }
}
