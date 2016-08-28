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

package com.atanas.kanchev.testframework.selenium.omniadriver;

import com.atanas.kanchev.testframework.commons.exceptions.CustomExceptions;
import com.atanas.kanchev.testframework.dataservices.dataprovider.file.FileFinder;
import com.atanas.kanchev.testframework.selenium.context.SeleniumContext;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static com.atanas.kanchev.testframework.commons.accessors.ContextsAccessor.context;

public class Navigation<T extends WebDriver> implements WebDriver.Navigation {

    private static final Logger logger = LoggerFactory.getLogger(Navigation.class);

    @Override public void back() {
        logger.debug("Navigating back");
        context().<SeleniumContext<T>>getCurrentContext().getDriver().navigate().back();
    }

    @Override public void forward() {
        logger.debug("Navigating forward");
        context().<SeleniumContext<T>>getCurrentContext().getDriver().navigate().forward();
    }

    @Override public void to(String s) {
        goTo(s);
    }

    @Override public void to(URL url) {
        goTo(url.toExternalForm());
    }

    @Override public void refresh() {
        logger.debug("Refreshing page");
        context().<SeleniumContext<T>>getCurrentContext().getDriver().navigate().refresh();
    }

    private void goTo(String uri) {

        if (uri != null && !uri.isEmpty()) {
            logger.debug("Navigating to " + uri);

            if (uri.startsWith("http:") || uri.startsWith("https:")) {
                try {
                    context().<SeleniumContext<T>>getCurrentContext().getDriver().navigate()
                        .to(new URL(uri));
                } catch (MalformedURLException e) {
                    logger.error("Invalid URL " + e.getMessage());
                }
            } else {
                try {
                    File file = new FileFinder(uri).getFile();
                    context().<SeleniumContext<T>>getCurrentContext().getDriver()
                        .get("file://" + file.getAbsolutePath());
                } catch (IOException | TimeoutException e) {
                    throw new RuntimeException(e.getCause());
                }
            } /*else
                throw new CustomExceptions.Common.IllegalArgumentException("Invalid URI");*/
        } else
            throw new CustomExceptions.Common.IllegalArgumentException("Null or empty uri");
    }


}
