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

package com.atanas.kanchev.testframework.selenium.driverfactory;

import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.internal.Killable;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Atanas Kanchev
 */
public class DriverManager {

    private static final Logger logger = LoggerFactory.getLogger(DriverManager.class);

    /**
     * @param driver WebDriver object
     * @return
     */
    public static boolean isBrowserStillOpen(WebDriver driver) {
        try {
            driver.getTitle();
            return true;
        } catch (UnreachableBrowserException e) {
            logger.warn("Browser is unreachable", e);
            return false;
        } catch (NoSuchWindowException e) {
            logger.warn("Browser window is not found", e);
            return false;
        } catch (NoSuchSessionException e) {
            logger.warn("Browser session is not found", e);
            return false;
        }
    }

    public static void quitDriver(WebDriver driver) {

        if (driver != null && !DriverConfiguration.isReuseBrowser()) {
            logger.info("Quitting " + driver.toString());

            long begin = System.currentTimeMillis();
            try {
                driver.quit();
            } catch (UnreachableBrowserException e) {
                logger.warn("Browser is unreachable", e);
            } catch (WebDriverException cannotCloseBrowser) {
                logger.error("Cannot close browser normally");
            } finally {
                if (driver instanceof Killable)
                    killBrowser(driver);
            }

            long duration = System.currentTimeMillis() - begin;
            if (duration >= DriverConfiguration.getCloseBrowserTimeout()) {
                logger.warn(
                    "Failed to close driver in " + DriverConfiguration.getCloseBrowserTimeout()
                        + Defaults.DEF_TIME_UNITS);
            } else {
                logger.info(
                    String.format("Closed driver in %d %s", duration, Defaults.DEF_TIME_UNITS));
            }
        } else {
            logger.info("Skipping closing the driver");
        }
    }

    private static void killBrowser(WebDriver webdriver) {
        logger.warn("Trying to kill the browser forcibly");
        if (webdriver instanceof Killable) {
            try {
                ((Killable) webdriver).kill();
                logger.warn("Browser killed");
            } catch (Exception e) {
                logger.error("Failed to kill browser " + webdriver + ':', e);
            }
        }
    }
}

