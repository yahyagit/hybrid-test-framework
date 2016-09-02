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

package com.atanas.kanchev.testframework.selenium.tests.elements.helpers;

import org.openqa.selenium.WebDriver;

import java.net.URL;

public final class PageLoader {
    private PageLoader() {
    }

    /**
     * Get a page by its name. page is stored in test resources.
     *
     * @param driver   WebDriver fed in by the test.
     * @param resource String containing the name of your test html.
     */
    public static void get(WebDriver driver, String resource) {
        URL formsHtmlUrl = PageLoader.class.getClassLoader().getResource(resource);
        if (formsHtmlUrl == null) {
            throw new RuntimeException();
        }
        driver.get(formsHtmlUrl.toString());
    }
}
