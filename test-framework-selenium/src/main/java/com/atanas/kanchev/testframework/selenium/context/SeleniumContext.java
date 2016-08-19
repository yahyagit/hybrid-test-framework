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

package com.atanas.kanchev.testframework.selenium.context;

import com.atanas.kanchev.testframework.commons.context.AbstractContext;
import com.atanas.kanchev.testframework.commons.exceptions.CustomExceptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * <p>SeleniumContext class.</p>
 *
 * @author Atanas Kanchev
 */
public class SeleniumContext<T> extends AbstractContext<T> {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(SeleniumContext.class);

    // Current WebElement pointer
    private WebElement currentElement;

    // Current WebElement pointer
    private List<WebElement> webElementsList;

    /**
     * <p>Constructor for SeleniumContext.</p>
     *
     * @param
     */
    public SeleniumContext(String contextName) {
        super(contextName);
    }

    /**
     * <p>Constructor for SeleniumContext.</p>
     *
     * @param driver a T object.
     */
    public SeleniumContext(T driver) {
        this(driver, "seleniumCtx_");
    }

    /**
     * <p>Constructor for SeleniumContext.</p>
     *
     * @param driver      a T object.
     * @param contextName a {@link java.lang.String} object.
     */
    public SeleniumContext(T driver, String contextName) {
        super(contextName);
        super.setDriver(driver);
    }

    /**
     * {@inheritDoc}
     */
    @Override public void tearDownContext(AbstractContext context) {

        logger.debug("Tearing down context " + context.getContextName());


        if (((SeleniumContext<WebDriver>) context).getDriver() != null) {

            if (context.getDriver() instanceof WebDriver)
                ((SeleniumContext<WebDriver>) context).getDriver().quit();

            if (context.getDriver() instanceof RemoteWebDriver)
                ((SeleniumContext<RemoteWebDriver>) context).getDriver().quit();
        }
    }

    //////////////
    // Getters //
    /////////////

    /**
     * <p>Getter for the field <code>currentElement</code>.</p>
     *
     * @return a {@link org.openqa.selenium.WebElement} object.
     */
    public WebElement getCurrentElement() {

        if (this.currentElement == null)
            throw new CustomExceptions.Common.NullReferenceException(
                "Null SeleniumContext#currentElement");
        else
            return currentElement;
    }

    /**
     * <p>Getter for the field <code>webElementsList</code>.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<WebElement> getWebElementsList() {

        if (this.webElementsList == null)
            throw new CustomExceptions.Common.NullReferenceException(
                "Null SeleniumContext#webElementsList");
        else
            return webElementsList;
    }

    //////////////
    // Setters //
    /////////////

    /**
     * <p>Setter for the field <code>currentElement</code>.</p>
     *
     * @param currentElement a {@link org.openqa.selenium.WebElement} object.
     */
    public void setCurrentElement(WebElement currentElement) {
        if (currentElement != null)
            this.currentElement = currentElement;
        else
            throw new CustomExceptions.Common.NullArgumentException(
                "Null WebElement instance passed as method argument");

    }

    /**
     * <p>Setter for the field <code>webElementsList</code>.</p>
     *
     * @param webElementsList a {@link java.util.List} object.
     */
    public void setWebElementsList(List<WebElement> webElementsList) {
        if (webElementsList != null)
            this.webElementsList = webElementsList;
        else
            throw new CustomExceptions.Common.NullArgumentException(
                "Null WebElement instance passed as method argument");

    }

    /**
     * {@inheritDoc}
     */
    @Override public String toString() {
        return getClass().getSimpleName();
    }

}
