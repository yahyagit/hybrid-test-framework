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

package com.atanas.kanchev.testframework.selenium.handlers;

import com.atanas.kanchev.testframework.commons.exceptions.CustomExceptions;
import com.atanas.kanchev.testframework.selenium.context.SeleniumContext;
import com.atanas.kanchev.testframework.selenium.driverfactory.DriverFactory;
import com.atanas.kanchev.testframework.selenium.omniadriver.Navigation;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

import static com.atanas.kanchev.testframework.commons.accessors.ContextsAccessor.context;

/**
 * <p>Navigates class.</p>
 *
 * @author Atanas Kanchev
 */
public class Navigates/* implements *//*INavigates,*//* IContext<SeleniumContext>*/ {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(Navigates.class);

    Navigation navigation = new Navigation();

    public Navigates() {

        try {
            context().getCurrentContext();
        } catch (CustomExceptions.Common.NullReferenceException e) {
            SeleniumContext<WebDriver> context =
                new SeleniumContext<>(new DriverFactory().getDriver());
            context().addContext(context);
        }

    }

    /**
     * {@inheritDoc}
     */
    public Navigates getPage(URL url) {
        navigation.to(url.toExternalForm());
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public Navigates getPage(String url) {
        navigation.to(url);
        return this;
    }


    public Navigates back() {
        navigation.back();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public Navigates forward() {
        navigation.forward();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public Navigates refresh() {
        navigation.refresh();
        return this;
    }

    public WebDriver.Options manage() {
        return new com.atanas.kanchev.testframework.selenium.omniadriver.Options<>();
    }

    //    /**
    //     * {@inheritDoc}
    //     */
    //    public Navigates navigateToWindowByPartialTitle(String title) {
    //        try {
    //            WebDriver popup;
    //            Iterator<String> windowIterator = driver.getWindowHandles().iterator();
    //            while (windowIterator.hasNext()) {
    //                String windowHandle = windowIterator.next();
    //                popup = driver.switchTo().window(windowHandle);
    //                if (popup.getTitle().contains(title)) {
    //                    break;
    //                }
    //            }
    //            logger.debug("navigateToWindowByPartialTitle : Navigated to window by:" + title);
    //
    //        } catch (NoSuchWindowException nswe) {
    //            logger.error("navigateToWindowByPartialTitle : No Window found");
    //            throw new NoSuchWindowException("navigateToWindowByPartialTitle : No Window found");
    //
    //        }
    //        return this;
    //    }
    //
    //    /**
    //     * {@inheritDoc}
    //     */
    //    public Navigates navigateToWindow(String windowIdentifier) {
    //        try {
    //            driver.switchTo().window(windowIdentifier);
    //        } catch (NoSuchWindowException nswe) {
    //
    //        }
    //        return this;
    //    }
    //
    //    /**
    //     * {@inheritDoc}
    //     */
    //    public Navigates navigateToOtherWindow() {
    //        Set<String> handles = driver.getWindowHandles();
    //        String currentWindow = driver.getWindowHandle();
    //        for (String handle : handles) {
    //            if (!handle.equals(currentWindow)) {
    //                driver.switchTo().window(handle);
    //                logger.debug("Switched to Other Window");
    //            }
    //        }
    //        return this;
    //    }
    //
    //    /**
    //     * {@inheritDoc}
    //     */
    //    public Navigates navigateToActivateFrame() {
    //        try {
    //            driver.switchTo().frame(0);
    //            logger.debug("Switched to Active Frame");
    //
    //        } catch (NoSuchFrameException nsfe) {
    //            logger.error("Unable to Switch Frame - No Such Frame");
    //
    //        }
    //        return this;
    //    }
    //
    //    /**
    //     * {@inheritDoc}
    //     */
    //    public Navigates navigateToFrameById(String id) {
    //        try {
    //            driver.switchTo().frame(id);
    //            logger.debug("Switched to Active Frame by Id");
    //
    //        } catch (NoSuchFrameException nsfe) {
    //            logger.error("Unable to Switch Frame - No Such Frame");
    //
    //        }
    //        return this;
    //    }
    //
    //    /**
    //     * {@inheritDoc}
    //     */
    //    public Navigates navigateToFrameBy(By by) {
    //        try {
    //            driver.switchTo().frame(driver.findElement(by));
    //            logger.debug("Switched to Active Frame by className");
    //
    //        } catch (NoSuchFrameException nsfe) {
    //            logger.error("Unable to Switch Frame - No Such Frame");
    //
    //        }
    //        return this;
    //    }
    //
    //    /**
    //     * {@inheritDoc}
    //     */
    //    public Navigates waitForFrameByIdToBeAvailableAndSwitch(String frameId) {
    //        try {
    //            new WebDriverWait(driver, 5)
    //                    .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameId));
    //
    //        } catch (NoSuchFrameException nsfe) {
    //            logger.error("Unable to Switch Frame - No Such Frame");
    //
    //        }
    //        return this;
    //    }
    //
    //    /**
    //     * {@inheritDoc}
    //     */
    //    public Navigates returnToDefaultWindow() {
    //        driver.switchTo().defaultContent();
    //        return this;
    //    }
    //
    //
    //    /**
    //     * {@inheritDoc}
    //     */
    //    public Navigates deleteCookies() {
    //        try {
    //            driver.manage().deleteAllCookies();
    //        } catch (NoSuchWindowException nsw) {
    //
    //        }
    //        return this;
    //    }
    //
    //    /**
    //     * {@inheritDoc}
    //     */
    //    public Navigates deleteCookie(String cookieName) {
    //        try {
    //            driver.manage().deleteCookieNamed(cookieName);
    //        } catch (NoSuchWindowException nsw) {
    //
    //        }
    //        return this;
    //    }
    //
    //    /**
    //     * {@inheritDoc}
    //     */
    //    public Navigates setCookie(String cookieName, String cookieValue) {
    //        try {
    //            driver.manage().addCookie(new Cookie(cookieName, cookieValue));
    //        } catch (NoSuchWindowException nsw) {
    //
    //        }
    //        return this;
    //    }
}
