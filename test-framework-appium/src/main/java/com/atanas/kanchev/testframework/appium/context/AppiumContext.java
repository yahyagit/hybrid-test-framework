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

package com.atanas.kanchev.testframework.appium.context;

import com.atanas.kanchev.testframework.commons.context.AbstractContext;
import com.atanas.kanchev.testframework.selenium.context.SeleniumContext;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Appium Context class.</p>
 *
 * @author Atanas Kanchev
 */
public final class AppiumContext<T> extends SeleniumContext<T> {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(AppiumContext.class);

    /**
     * <p>Constructor for Appium Context.</p>
     *
     * @param driver a T object.
     */
    public AppiumContext(T driver) {
        this(driver, "appiumCtx_");
    }

    /**
     * <p>Constructor for Appium Context.</p>
     *
     * @param driver      a T object.
     * @param contextName a {@link java.lang.String} object.
     */
    public AppiumContext(T driver, String contextName) {
        super(driver, contextName);
    }

    /**
     * {@inheritDoc}
     */
    @Override public void tearDownContext(AbstractContext context) {

        logger.debug("Tearing down context " + context.getContextName());

        if (context instanceof AppiumContext) {

            if (((AppiumContext<AndroidDriver>) context).getDriver() != null) {

                if (context.getDriver() instanceof AndroidDriver)
                    try {
                        ((AppiumContext<AndroidDriver>) context).getDriver().closeApp();
                        ((AppiumContext<AndroidDriver>) context).getDriver().quit();
                    } catch (Exception e) {
                        logger.error(e.getMessage());
                    }

                if (context.getDriver() instanceof IOSDriver)
                    ((AppiumContext<IOSDriver>) context).getDriver().quit();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override public String toString() {
        return getClass().getSimpleName();
    }

}
