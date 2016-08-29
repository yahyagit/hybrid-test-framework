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
import com.atanas.kanchev.testframework.commons.context.ContextKey;
import com.atanas.kanchev.testframework.commons.context.IDriverContext;
import com.atanas.kanchev.testframework.commons.context.IElement;
import com.atanas.kanchev.testframework.commons.exceptions.CustomExceptions;
import com.atanas.kanchev.testframework.selenium.driverfactory.DriverManager;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public final class AppiumContext<T extends AndroidDriver> extends AbstractContext
    implements IElement<MobileElement>, IDriverContext<T> {

    private static final Logger logger = LoggerFactory.getLogger(AppiumContext.class.getName());

    private T driver;
    private MobileElement currentElement;
    private List<MobileElement> webElementsList;

    public AppiumContext() {
        this("appiumCtx_");
    }

    public AppiumContext(String contextName) {
        super(contextName);
    }

    public AppiumContext(T driver) {
        this(driver, "appiumCtx_");
    }

    public AppiumContext(T driver, String contextName) {
        super(contextName);
        this.setDriver(driver);
    }

    @Override public T getDriver() {
        if (this.driver == null)
            throw new CustomExceptions.Common.NullReferenceException(
                "Null driver object AbstractContext#driver");
        else
            return this.driver;
    }

    @Override public void setDriver(T driver) {
        if (driver != null)
            this.driver = driver;
        else
            throw new CustomExceptions.Common.NullArgumentException(
                "Null driver instance passed as method argument");
    }

    @Override public MobileElement getCurrentElement() {

        if (this.currentElement == null)
            throw new CustomExceptions.Common.NullReferenceException(
                "Null SeleniumContext#currentElement");
        else
            return currentElement;
    }

    @Override public void setCurrentElement(MobileElement currentElement) {
        if (currentElement != null) {
            logger.debug(
                "Setting current element to " + ((RemoteWebElement) currentElement).toString());
            this.currentElement = currentElement;
        } else
            throw new CustomExceptions.Common.NullArgumentException(
                "Null WebElement passed as method argument");

    }

    @Override public List<MobileElement> getWebElementsList() {

        if (this.webElementsList == null)
            throw new CustomExceptions.Common.NullReferenceException(
                "Null SeleniumContext#webElementsList");
        else
            return webElementsList;
    }

    @Override public void setWebElementsList(List<MobileElement> webElementsList) {

        if (webElementsList != null) {
            this.webElementsList = webElementsList;
            logger.debug(
                "Setting current element list to \n" + Arrays.toString(webElementsList.toArray()));
        } else
            throw new CustomExceptions.Common.NullArgumentException(
                "Null WebElement passed as method argument");

    }

    @Override public ContextKey<AppiumContext> getContextKey() {
        return new ContextKey<>(getContextName(), AppiumContext.class);
    }

    @Override public void tearDownContext() {

        logger.debug("Tearing down context " + getContextName());
        if (getDriver() != null) {
            if (DriverManager.isBrowserStillOpen(getDriver())) {
                DriverManager.quitDriver(getDriver());
            }
        }
    }

    @Override public String toString() {
        return "AppiumContext{" + "driver=" + driver + ", currentElement=" + currentElement
            + ", webElementsList=" + webElementsList + ", contextKey=" + getContextKey() + "} "
            + super.toString();
    }
}
