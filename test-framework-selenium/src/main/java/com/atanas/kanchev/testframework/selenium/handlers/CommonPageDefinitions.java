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

/**
 * <p>CommonPageDefinitions class.</p>
 *
 * @author Atanas Kanchev
 */
public class CommonPageDefinitions {

    public enum HTML {

        UNORDERED_LIST("ul"),
        ORDERED_LIST("ol"),
        LIST_ITEM("li"),
        ANCHOR("a"),
        TABLE("table"),
        TABLE_BODY("tbody"),
        TABLE_ROW("tr"),
        TABLE_DATA("td"),
        TABLE_HEADER("th"),
        STRONG("strong"),
        SPAN("span"),
        IFRAME("iframe"),
        PARAGRAPH("p"),
        LABEL("label"),
        BUTTON("button"),
        DIVISION("div"),
        INPUT("input"),
        SECTION("section"),
        HEADER("header"),
        FORM("form"),
        SELECT("select"),
        ICON("i"),
        FIELDSET("fieldset"),
        TITLE("title"),
        ASIDE("aside"),
        IMAGE("img"),
        TEXTAREA("textarea"),
        UIA_TEXTFIELD("UIATextField"),
        UIA_SECURETEXTFIELD("UIASecureTextField"),

        // HTML headers
        HEADER_ONE("h1"),
        HEADER_TWO("h2"),
        HEADER_THREE("h3"),

        // Element attributes
        ATTRIBUTE_ONCLICK("onclick"),
        ATTRIBUTE_TITLE("title"),
        ATTRIBUTE_CLASS("class"),
        ATTRIBUTE_ID("id"),
        ATTRIBUTE_FOR("for"),
        ATTRIBUTE_TEXT("text"),
        ATTRIBUTE_PASSWORD("password");

        private final String definition;

        HTML(String definition) {
            this.definition = definition;
        }

        public String getDefinition() {
            return definition;
        }
    }

    public enum CSS {

        CSS_BORDER_TOP_COLOUR("border-top-color"),
        CSS_BORDER_LEFT_COLOUR("border-left-color"),
        CSS_BORDER_RIGHT_COLOUR("border-right-color"),
        CSS_BORDER_BOTTOM_COLOUR("border-bottom-color"),
        CSS_TEXT_COLOUR("color"),
        CSS_BACKGROUND_COLOUR("background-color");

        private final String definition;

        CSS(String definition) {
            this.definition = definition;
        }

        public String getDefinition() {
            return definition;
        }
    }

    public enum COLOR {
        COLOUR_WHITE("#FFFFFF"),
        COLOUR_BLACK("#000000");

        private final String definition;

        COLOR(String definition) {
            this.definition = definition;
        }

        public String getDefinition() {
            return definition;
        }
    }
}
