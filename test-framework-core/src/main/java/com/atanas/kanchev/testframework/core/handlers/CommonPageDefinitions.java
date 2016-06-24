package com.atanas.kanchev.testframework.core.handlers;

/**
 * @author Atanas Ksnchev
 */
class CommonPageDefinitions {

    enum HTML {

        HTML_UNORDERED_LIST("ul"),
        HTML_ORDERED_LIST("ol"),
        HTML_LIST_ITEM("li"),
        HTML_ANCHOR("a"),
        HTML_TABLE("table"),
        HTML_TABLE_BODY("tbody"),
        HTML_TABLE_ROW("tr"),
        HTML_TABLE_DATA("td"),
        HTML_TABLE_HEADER("th"),
        HTML_STRONG("strong"),
        HTML_SPAN("span"),
        HTML_IFRAME("iframe"),
        HTML_PARAGRAPH("p"),
        HTML_LABEL("label"),
        HTML_BUTTON("button"),
        HTML_DIVISION("div"),
        HTML_INPUT("input"),
        HTML_SECTION("section"),
        HTML_HEADER("header"),
        HTML_FORM("form"),
        HTML_SELECT("select"),
        HTML_ICON("i"),
        HTML_FIELDSET("fieldset"),
        HTML_TITLE("title"),
        HTML_ASIDE("aside"),
        HTML_IMAGE("img"),
        HTML_TEXTAREA("textarea"),
        UIA_TEXTFIELD("UIATextField"),
        UIA_SECURETEXTFIELD("UIASecureTextField"),

        // HTML headers
        HTML_HEADER_ONE("h1"),
        HTML_HEADER_TWO("h2"),
        HTML_HEADER_THREE("h3"),

        // Element attributes
        HTML_ATTRIBUTE_ONCLICK("onclick"),
        HTML_ATTRIBUTE_TITLE("title"),
        HTML_ATTRIBUTE_CLASS("class"),
        HTML_ATTRIBUTE_ID("id"),
        HTML_ATTRIBUTE_FOR("for"),
        HTML_ATTRIBUTE_TEXT("text"),
        HTML_ATTRIBUTE_PASSWORD("password");

        private final String definition;
        HTML(String definition) {
            this.definition = definition;
        }
        private String getDefinition() {
            return definition;
        }
    }

    enum CSS {

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
        private String getDefinition() {
            return definition;
        }
    }

    enum COLOR {
        COLOUR_WHITE("#FFFFFF"),
        COLOUR_BLACK("#000000");

        private final String definition;
        COLOR(String definition) {
            this.definition = definition;
        }
        private String getDefinition() {
            return definition;
        }
    }
}