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

import com.atanas.kanchev.testframework.commons.exceptions.CustomExceptions;
import com.atanas.kanchev.testframework.commons.wrappers.IContext;
import com.atanas.kanchev.testframework.sikuli.context.SikuliXContext;
import org.sikuli.basics.Settings;
import org.sikuli.script.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * <p>SikuliXFactory class.</p>
 *
 * @author Atanas Kanchev
 */
public final class SikuliXFactory implements IContext{

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(SikuliXFactory.class);

    // the screen
    private final static Screen screen = new Screen();

    /**
     * <p>Constructor for SikuliXFactory.</p>
     */
    public SikuliXFactory() {

        Settings.ActionLogs = true;
        screen.setAutoWaitTimeout(5);
        logger.debug("Using screen ID: " + screen.getID());
        SikuliXContext sikuliXContext = new SikuliXContext(this);
        context().addContext(sikuliXContext);

    }

    /**
     * <p>Constructor for SikuliXFactory.</p>
     *
     * @param fileName a {@link java.lang.String} object.
     */
    public SikuliXFactory(final String fileName) {

        this();
        if (fileName != null)
            findImage(fileName);

    }

    /**
     * <p>findImage.</p>
     *
     * @param fileName a {@link java.lang.String} object.
     * @return a {@link com.atanas.kanchev.testframework.sikuli.handlers.SikuliXFactory} object.
     */
    public SikuliXFactory findImage(String fileName) {

        match(fileName);

        return this;
    }

    /**
     * <p>click.</p>
     *
     * @return a {@link com.atanas.kanchev.testframework.sikuli.handlers.SikuliXFactory} object.
     */
    public SikuliXFactory click() {

        if (screen.click() == 1) {
            logger.debug("Successfully clicked");
        } else {
            logger.error("Unable to perform single click");
            throw new CustomExceptions.Sikuli.UnableToInteractException(
                "Unable to perform single click");
        }

        return this;
    }

    /**
     * <p>click.</p>
     *
     * @param direction a {@link com.atanas.kanchev.testframework.sikuli.handlers.SikuliXFactory.Directions} object.
     * @param px        a int.
     * @return a {@link com.atanas.kanchev.testframework.sikuli.handlers.SikuliXFactory} object.
     */
    public SikuliXFactory click(final Directions direction, final int px) {

        switch (direction) {
            case ABOVE:
                if (((SikuliXContext) context().getCurrentContext()).getMatch().above(px).click() == 1)
                    logger.debug("Successfully clicked " + px + " " + direction);
                else
                    throw new CustomExceptions.Sikuli.UnableToInteractException(
                        "Unable to perform click");
                break;
            case LEFT:
                if (((SikuliXContext) context().getCurrentContext()).getMatch().left(px).click() == 1)
                    logger.debug("Successfully clicked " + px + " " + direction);
                else
                    throw new CustomExceptions.Sikuli.UnableToInteractException(
                        "Unable to perform click");
                break;
            case CENTER:
                try {
                    ((SikuliXContext) context().getCurrentContext()).getMatch().getCenter().click();
                    logger.debug("Successfully clicked " + px + " " + direction);
                } catch (Exception e) {
                    throw new CustomExceptions.Sikuli.UnableToInteractException(
                        "Unable to perform click");
                }
                break;
            case RIGHT:
                if (((SikuliXContext) context().getCurrentContext()).getMatch().right(px).click() == 1)
                    logger.debug("Successfully clicked " + px + " " + direction);
                else
                    throw new CustomExceptions.Sikuli.UnableToInteractException(
                        "Unable to perform click");
                break;
            case BELOW:
                if (((SikuliXContext) context().getCurrentContext()).getMatch().below(px).click() == 1)
                    logger.debug("Successfully clicked " + px + " " + direction);
                else
                    throw new CustomExceptions.Sikuli.UnableToInteractException(
                        "Unable to perform click");
                break;
            default:
                throw new CustomExceptions.Common.IllegalArgumentException(
                    "Not supported direction: " + direction);
        }

        return this;
    }

    /**
     * <p>doubleClick.</p>
     *
     * @return a {@link com.atanas.kanchev.testframework.sikuli.handlers.SikuliXFactory} object.
     */
    public SikuliXFactory doubleClick() {

        if (screen.doubleClick() == 1) {
            logger.debug("Successfully clicked");
        } else {
            logger.error("Unable to perform double click");
            throw new CustomExceptions.Sikuli.UnableToInteractException(
                "Unable to perform double click");
        }
        return this;
    }

    /**
     * <p>doubleClick.</p>
     *
     * @param direction a {@link com.atanas.kanchev.testframework.sikuli.handlers.SikuliXFactory.Directions} object.
     * @param px        a int.
     * @return a {@link com.atanas.kanchev.testframework.sikuli.handlers.SikuliXFactory} object.
     */
    public SikuliXFactory doubleClick(final Directions direction, final int px) {

        switch (direction) {
            case ABOVE:
                if (((SikuliXContext) context().getCurrentContext()).getMatch().above(px).doubleClick() == 1)
                    logger.debug("Successfully clicked " + px + " " + direction);
                else
                    throw new CustomExceptions.Sikuli.UnableToInteractException(
                        "Unable to perform click");
                break;
            case LEFT:
                if (((SikuliXContext) context().getCurrentContext()).getMatch().left(px).doubleClick() == 1)
                    logger.debug("Successfully clicked " + px + " " + direction);
                else
                    throw new CustomExceptions.Sikuli.UnableToInteractException(
                        "Unable to perform click");
                break;
            case CENTER:
                if (((SikuliXContext) context().getCurrentContext()).getMatch().doubleClick() == 1)
                    logger.debug("Successfully clicked " + px + " " + direction);
                else
                    throw new CustomExceptions.Sikuli.UnableToInteractException(
                        "Unable to perform click");
                break;
            case RIGHT:
                if (((SikuliXContext) context().getCurrentContext()).getMatch().right(px).doubleClick() == 1)
                    logger.debug("Successfully clicked " + px + " " + direction);
                else
                    throw new CustomExceptions.Sikuli.UnableToInteractException(
                        "Unable to perform click");
                break;
            case BELOW:
                if (((SikuliXContext) context().getCurrentContext()).getMatch().below(px).doubleClick() == 1)
                    logger.debug("Successfully clicked " + px + " " + direction);
                else
                    throw new CustomExceptions.Sikuli.UnableToInteractException(
                        "Unable to perform click");
                break;
            default:
                throw new CustomExceptions.Common.IllegalArgumentException(
                    "Not supported direction: " + direction);
        }
        return this;
    }

    /**
     * <p>captureImage.</p>
     *
     * @param fileName  a {@link java.lang.String} object.
     * @param pixelSize a int.
     * @param direction a {@link com.atanas.kanchev.testframework.sikuli.handlers.SikuliXFactory.Directions} object.
     * @return a {@link com.atanas.kanchev.testframework.sikuli.handlers.SikuliXFactory} object.
     */
    public SikuliXFactory captureImage(final String fileName, final int pixelSize,
        final Directions direction) {

        BufferedImage image = null;

        try {
            switch (direction) {
                case ABOVE:
                    image = screen.capture(((SikuliXContext) context().getCurrentContext()).getMatch().right(pixelSize)).getImage();
                    break;
                case BELOW:
                    image = screen.capture(((SikuliXContext) context().getCurrentContext()).getMatch().below(pixelSize)).getImage();
                    break;
                case CENTER:
                    image = screen.capture(((SikuliXContext) context().getCurrentContext()).getMatch()).getImage();
                case LEFT:
                    image = screen.capture(((SikuliXContext) context().getCurrentContext()).getMatch().left(pixelSize)).getImage();
                    break;
                case RIGHT:
                    image = screen.capture(((SikuliXContext) context().getCurrentContext()).getMatch().right(pixelSize)).getImage();
                    break;
                default:
                    throw new CustomExceptions.Common.IllegalArgumentException(
                        "Unsupported direction");
            }
        } catch (Exception e) {
            logger.error("Unable to capture image ", e);
        } finally {
            if (image != null)
                ImageFinder.saveImage(image, fileName);
            logger.debug("Saved image ", fileName);
        }

        return this;
    }

    /**
     * <p>type.</p>
     *
     * @param text a {@link java.lang.String} object.
     * @return a {@link com.atanas.kanchev.testframework.sikuli.handlers.SikuliXFactory} object.
     */
    public SikuliXFactory type(String text) {

        try {
            if (screen.type(((SikuliXContext) context().getCurrentContext()).getMatch(), text, 0) == 1)
                logger.debug("Successfully typed " + text);
        } catch (FindFailed findFailed) {
            logger.error("Unable to type in ", findFailed);
            throw new CustomExceptions.Sikuli.UnableToInteractException("Unable to type in");
        }


        return this;
    }

    /**
     * <p>type.</p>
     *
     * @param text      a {@link java.lang.String} object.
     * @param pixelSize a int.
     * @param direction a {@link com.atanas.kanchev.testframework.sikuli.handlers.SikuliXFactory.Directions} object.
     * @return a {@link com.atanas.kanchev.testframework.sikuli.handlers.SikuliXFactory} object.
     */
    public SikuliXFactory type(final String text, final int pixelSize, final Directions direction) {

        switch (direction) {
            case RIGHT:
                ((SikuliXContext) context().getCurrentContext()).getMatch().right(pixelSize).type(text);
                break;
            case LEFT:
                ((SikuliXContext) context().getCurrentContext()).getMatch().left(pixelSize).type(text);
                break;
            case CENTER:
                ((SikuliXContext) context().getCurrentContext()).getMatch().type(text);
                break;
            case BELOW:
                ((SikuliXContext) context().getCurrentContext()).getMatch().below(pixelSize).type(text);
                break;
            case ABOVE:
                ((SikuliXContext) context().getCurrentContext()).getMatch().above(pixelSize).type(text);
                break;

        }
        return this;
    }

    /**
     * <p>swipeBetweenImages.</p>
     *
     * @param startPointImagePath a {@link java.lang.String} object.
     * @param endPointImagePath   a {@link java.lang.String} object.
     * @return a {@link com.atanas.kanchev.testframework.sikuli.handlers.SikuliXFactory} object.
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
     * <p>findImageByScrolling.</p>
     *
     * @param fileName   a {@link java.lang.String} object.
     * @param iterations a int.
     * @param direction  a {@link com.atanas.kanchev.testframework.sikuli.handlers.SikuliXFactory.Directions} object.
     * @return a {@link com.atanas.kanchev.testframework.sikuli.handlers.SikuliXFactory} object.
     */
    public SikuliXFactory findImageByScrolling(final String fileName, final int iterations,
        final Directions direction) {

        screen.setAutoWaitTimeout(2.5);
        int i = 0;
        boolean imageFound = false;
        do {
            findImage(fileName);
            if (((SikuliXContext) context().getCurrentContext()).getMatch() != null) {
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
            throw new CustomExceptions.Sikuli.UnableToInteractException(
                "Image not found via scrolling within timeout limit");
        }
        return this;
    }

    /**
     * <p>sendKey.</p>
     *
     * @param specialKey a {@link java.lang.String} object.
     * @return a {@link com.atanas.kanchev.testframework.sikuli.handlers.SikuliXFactory} object.
     */
    public SikuliXFactory sendKey(final String specialKey) {

        if (screen.type(String.valueOf(specialKey)) == 1) {
            logger.debug("Special key successfully sent");
        } else {
            logger.error("Unable to send special key " + specialKey);
            throw new CustomExceptions.Sikuli.UnableToInteractException(
                "Unable to send special key " + specialKey);
        }

        return this;
    }

    /**
     * <p>setMinimumSimilarityForImage.</p>
     *
     * @param minSimilarity a double.
     * @return a {@link com.atanas.kanchev.testframework.sikuli.handlers.SikuliXFactory} object.
     */
    public SikuliXFactory setMinimumSimilarityForImage(final double minSimilarity) {
        if (minSimilarity > 1.0 || minSimilarity < 0) {
            logger.error(
                "The value entered for minSimilarity is invalid, enter a value between 0 & 1");
            throw new CustomExceptions.Common.IllegalArgumentException(
                "The value entered for minSimilarity is invalid, enter a value between 0 & 1");
        } else {
            Settings.MinSimilarity = minSimilarity;
            logger.debug("MinSimilarity value set to " + Settings.MinSimilarity);
        }

        return this;
    }

    private Match match(final String imageFileName) {

        try {
            ((SikuliXContext) context().getCurrentContext()).setMatch(screen.find(getImageFilePath(imageFileName)));
            ((SikuliXContext) context().getCurrentContext()).getMatch().hover();
            logger.debug(
                "Match found for image " + imageFileName + " in location " + ((SikuliXContext) context().getCurrentContext()).getMatch().getTarget());
        } catch (FindFailed ffe) {
            logger.error("Unable getImageFilePath a match for image");
        }
        return ((SikuliXContext) context().getCurrentContext()).getMatch();
    }

    private String getImageFilePath(final String imageFileName) {

        if (imageFileName != null) {
            try {
                return new ImageFinder(imageFileName).getFile().getAbsolutePath();
            } catch (IOException e) {
                logger.error("Unable to getImageFilePath image file", e);
            }

        }
        return null;
    }

    public enum Directions {
        LEFT, RIGHT, CENTER, ABOVE, BELOW
    }

}
