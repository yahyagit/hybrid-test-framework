package com.atanas.kanchev.testframework.selenium.handlers;

/**
 * @author Atanas Ksnchev
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