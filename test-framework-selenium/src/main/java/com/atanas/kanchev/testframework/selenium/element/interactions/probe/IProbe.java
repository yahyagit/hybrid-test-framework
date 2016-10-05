/*
 * Copyright 2016 Atanas Stoychev Kanchev
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.atanas.kanchev.testframework.selenium.element.interactions.probe;

import com.atanas.kanchev.testframework.selenium.handlers.CommonPageDefinitions;

interface IProbe {
    /**
     * <p>exist.</p>
     *
     * @return a boolean.
     */
    boolean exist();

    /**
     * <p>hasAnyText.</p>
     *
     * @return a boolean.
     */
    boolean hasAnyText();

    /**
     * <p>hasText.</p>
     *
     * @param isCaseSensitiveMatch a boolean.
     * @param isPreciseMatch       a boolean.
     * @param textElements         a {@link String} object.
     * @return a boolean.
     */
    boolean hasText(boolean isCaseSensitiveMatch, boolean isPreciseMatch, String... textElements);

    /**
     * <p>hasAttribute.</p>
     *
     * @param preciseMatch  a boolean.
     * @param attributeName a {@link String} object.
     * @param attributeText a {@link String} object.
     * @return a boolean.
     */
    boolean hasAttribute(String attributeName, String attributeText, boolean preciseMatch);

    /**
     * <p>isOfTagType.</p>
     *
     * @param tag a {@link String} object.
     * @return a boolean.
     */
    boolean isOfTagType(String tag);

    /**
     * <p>isEnabled.</p>
     *
     * @return a boolean.
     */
    boolean isEnabled();

    /**
     * <p>isSelected.</p>
     *
     * @return a boolean.
     */
    boolean isSelected();

    /**
     * <p>isActive.</p>
     *
     * @return a boolean.
     */
    boolean isActive();

    /**
     * <p>isDisplayed.</p>
     *
     * @return a boolean.
     */
    boolean isDisplayed();

    /**
     * <p>hasColour.</p>
     *
     * @param css                  a {@link CommonPageDefinitions.CSS} object.
     * @param expectedColorHexCode a {@link String} object.
     * @return a boolean.
     */
    boolean hasColour(CommonPageDefinitions.CSS css, String expectedColorHexCode);

    /**
     * <p>hasPartialImagePath.</p>
     *
     * @param imagePath a {@link String} object.
     * @return a boolean.
     */
    boolean hasPartialImagePath(String imagePath);

//    /**
//     * <p>hasLinkToURL.</p>
//     *
//     * @param url a {@link java.lang.String} object.
//     * @return a boolean.
//     */
////    boolean hasLinkToURL(String url);
//
//    /**
//     * <p>followLinkToURL.</p>
//     *
//     * @param link a {@link java.lang.String} object.
//     * @return a boolean.
//     */
//    boolean followLinkToURL(String link);

    /**
     * <p>hasPartialCookieValue.</p>
     *
     * @param cookieName  a {@link String} object.
     * @param cookieValue a {@link String} object.
     * @return a boolean.
     */
    boolean hasPartialCookieValue(String cookieName, String cookieValue);
}


