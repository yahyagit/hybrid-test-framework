package com.atanas.kanchev.testframework.selenium.handlers;

import com.atanas.kanchev.testframework.core.exceptions.CustomExceptions;
import com.atanas.kanchev.testframework.selenium.context.WebContext;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.atanas.kanchev.testframework.selenium.context.ContextFactory.getCurrentContext;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Locators Factory
 */
public enum Locator implements IElementLocatorFactory, IWaitFactory {

    XPATH {
        @Override
        public WebElement locateElement(Locator locator, String value) {
            return findElement(Locator.XPATH, value);
        }

        @Override
        public List<WebElement> locateElements(Locator locator, String value) {
            return findElements(Locator.XPATH, value);
        }

        @Override
        public boolean presenceOfElement(Locator locator, String value, long wait) {
            return isElementPresent(Locator.XPATH, value, wait);
        }

        @Override
        public boolean visibilityOfElement(Locator locator, String value, long wait) {
            return isElementVisible(Locator.XPATH, value, true, wait);
        }

        @Override
        public boolean invisibilityOfElement(Locator locator, String value, long wait) {
            return isElementVisible(Locator.XPATH, value, false, wait);
        }

        @Override
        public boolean elementToBeClickable(Locator locator, String value, long wait) {
            return isElementClickable(Locator.XPATH, value, wait);
        }
    },

    CSS_SELECTOR {
        @Override
        public WebElement locateElement(Locator locator, String value) {
            return findElement(Locator.CSS_SELECTOR, value);
        }

        @Override
        public List<WebElement> locateElements(Locator locator, String value) {
            return findElements(Locator.CSS_SELECTOR, value);
        }

        @Override
        public boolean presenceOfElement(Locator locator, String value, long wait) {
            return isElementPresent(Locator.CSS_SELECTOR, value, wait);
        }

        @Override
        public boolean visibilityOfElement(Locator locator, String value, long wait) {
            return isElementVisible(Locator.CSS_SELECTOR, value, true, wait);
        }

        @Override
        public boolean invisibilityOfElement(Locator locator, String value, long wait) {
            return isElementVisible(Locator.CSS_SELECTOR, value, false, wait);
        }

        @Override
        public boolean elementToBeClickable(Locator locator, String value, long wait) {
            return isElementClickable(Locator.CSS_SELECTOR, value, wait);
        }
    },


    ID {
        @Override
        public WebElement locateElement(Locator locator, String value) {
            return findElement(Locator.ID, value);
        }

        @Override
        public List<WebElement> locateElements(Locator locator, String value) {
            return findElements(Locator.ID, value);
        }

        @Override
        public boolean presenceOfElement(Locator locator, String value, long wait) {
            return isElementPresent(Locator.ID, value, wait);
        }

        @Override
        public boolean visibilityOfElement(Locator locator, String value, long wait) {
            return isElementVisible(Locator.ID, value, true, wait);
        }

        @Override
        public boolean invisibilityOfElement(Locator locator, String value, long wait) {
            return isElementVisible(Locator.ID, value, false, wait);
        }

        @Override
        public boolean elementToBeClickable(Locator locator, String value, long wait) {
            return isElementClickable(Locator.ID, value, wait);
        }
    },


    CLASS_NAME {
        @Override
        public WebElement locateElement(Locator locator, String value) {
            return findElement(Locator.CLASS_NAME, value);
        }

        @Override
        public List<WebElement> locateElements(Locator locator, String value) {
            return findElements(Locator.CLASS_NAME, value);
        }

        @Override
        public boolean presenceOfElement(Locator locator, String value, long wait) {
            return isElementPresent(Locator.CLASS_NAME, value, wait);
        }

        @Override
        public boolean visibilityOfElement(Locator locator, String value, long wait) {
            return isElementVisible(Locator.CLASS_NAME, value, true, wait);
        }

        @Override
        public boolean invisibilityOfElement(Locator locator, String value, long wait) {
            return isElementVisible(Locator.CLASS_NAME, value, false, wait);
        }

        @Override
        public boolean elementToBeClickable(Locator locator, String value, long wait) {
            return isElementClickable(Locator.CLASS_NAME, value, wait);
        }
    },


    LINK_TEXT {
        @Override
        public WebElement locateElement(Locator locator, String value) {
            return findElement(Locator.LINK_TEXT, value);
        }

        @Override
        public List<WebElement> locateElements(Locator locator, String value) {
            return findElements(Locator.LINK_TEXT, value);
        }

        @Override
        public boolean presenceOfElement(Locator locator, String value, long wait) {
            return isElementPresent(Locator.LINK_TEXT, value, wait);
        }

        @Override
        public boolean visibilityOfElement(Locator locator, String value, long wait) {
            return isElementVisible(Locator.LINK_TEXT, value, true, wait);
        }

        @Override
        public boolean invisibilityOfElement(Locator locator, String value, long wait) {
            return isElementVisible(Locator.LINK_TEXT, value, false, wait);
        }

        @Override
        public boolean elementToBeClickable(Locator locator, String value, long wait) {
            return isElementClickable(Locator.LINK_TEXT, value, wait);
        }
    },


    PARTIAL_LINK_TEXT {
        @Override
        public WebElement locateElement(Locator locator, String value) {
            return findElement(Locator.PARTIAL_LINK_TEXT, value);
        }

        @Override
        public List<WebElement> locateElements(Locator locator, String value) {
            return findElements(Locator.PARTIAL_LINK_TEXT, value);
        }

        @Override
        public boolean presenceOfElement(Locator locator, String value, long wait) {
            return isElementPresent(Locator.PARTIAL_LINK_TEXT, value, wait);
        }

        @Override
        public boolean visibilityOfElement(Locator locator, String value, long wait) {
            return isElementVisible(Locator.PARTIAL_LINK_TEXT, value, true, wait);
        }

        @Override
        public boolean invisibilityOfElement(Locator locator, String value, long wait) {
            return isElementVisible(Locator.PARTIAL_LINK_TEXT, value, false, wait);
        }

        @Override
        public boolean elementToBeClickable(Locator locator, String value, long wait) {
            return isElementClickable(Locator.PARTIAL_LINK_TEXT, value, wait);
        }
    },


    TAG_NAME {
        @Override
        public WebElement locateElement(Locator locator, String value) {
            return findElement(Locator.TAG_NAME, value);
        }

        @Override
        public List<WebElement> locateElements(Locator locator, String value) {
            return findElements(Locator.TAG_NAME, value);
        }

        @Override
        public boolean presenceOfElement(Locator locator, String value, long wait) {
            return isElementPresent(Locator.TAG_NAME, value, wait);
        }

        @Override
        public boolean visibilityOfElement(Locator locator, String value, long wait) {
            return isElementVisible(Locator.TAG_NAME, value, true, wait);
        }

        @Override
        public boolean invisibilityOfElement(Locator locator, String value, long wait) {
            return isElementVisible(Locator.TAG_NAME, value, false, wait);
        }

        @Override
        public boolean elementToBeClickable(Locator locator, String value, long wait) {
            return isElementClickable(Locator.TAG_NAME, value, wait);
        }
    },


    NAME {
        @Override
        public WebElement locateElement(Locator locator, String value) {
            return findElement(Locator.NAME, value);
        }

        @Override
        public List<WebElement> locateElements(Locator locator, String value) {
            return findElements(Locator.NAME, value);
        }

        @Override
        public boolean presenceOfElement(Locator locator, String value, long wait) {
            return isElementPresent(Locator.NAME, value, wait);
        }

        @Override
        public boolean visibilityOfElement(Locator locator, String value, long wait) {
            return isElementVisible(Locator.NAME, value, true, wait);
        }

        @Override
        public boolean invisibilityOfElement(Locator locator, String value, long wait) {
            return isElementVisible(Locator.NAME, value, false, wait);
        }

        @Override
        public boolean elementToBeClickable(Locator locator, String value, long wait) {
            return isElementClickable(Locator.NAME, value, wait);
        }
    };

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(Locator.class);

    static WebContext getWebContext() {
        return ((WebContext) getCurrentContext());
    }

    private static WebElement findElement(Locator locType, String loc) {
        WebElement e;
        try {

            logger.debug("Locating element using " + locType + " locator: " + loc);
            WebDriver driver = ((WebContext) getCurrentContext()).getDriver();

            switch (locType) {
                case CLASS_NAME:
                    e = driver.findElement(By.name(loc));
                    break;
                case CSS_SELECTOR:
                    e = driver.findElement(By.cssSelector(loc));
                    break;
                case ID:
                    e = driver.findElement(By.id(loc));
                    break;
                case LINK_TEXT:
                    e = driver.findElement(By.linkText(loc));
                    break;
                case NAME:
                    e = driver.findElement(By.name(loc));
                    break;
                case PARTIAL_LINK_TEXT:
                    e = driver.findElement(By.partialLinkText(loc));
                    break;
                case TAG_NAME:
                    e = driver.findElement(By.tagName(loc));
                    break;
                case XPATH:
                    e = driver.findElement(By.xpath(loc));
                    break;
                default:
                    throw new CustomExceptions.Common.IllegalArgumentException();
            }


        } catch (NoSuchElementException nsee) {
            throw new NoSuchElementException(nsee.getMessage());
        }

        logger.debug("Element found!");

        return e;
    }

    private static List<WebElement> findElements(Locator locType, String loc) {
        List<WebElement> e;
        try {

            logger.debug("Locating elements using " + locType + " locator: " + loc);
            WebDriver driver = ((WebContext) getCurrentContext()).getDriver();

            switch (locType) {
                case CLASS_NAME:
                    e = driver.findElements(By.name(loc));
                    break;
                case CSS_SELECTOR:
                    e = driver.findElements(By.cssSelector(loc));
                    break;
                case ID:
                    e = driver.findElements(By.id(loc));
                    break;
                case LINK_TEXT:
                    e = driver.findElements(By.linkText(loc));
                    break;
                case NAME:
                    e = driver.findElements(By.name(loc));
                    break;
                case PARTIAL_LINK_TEXT:
                    e = driver.findElements(By.partialLinkText(loc));
                    break;
                case TAG_NAME:
                    e = driver.findElements(By.tagName(loc));
                    break;
                case XPATH:
                    e = driver.findElements(By.xpath(loc));
                    break;
                default:
                    throw new CustomExceptions.Common.IllegalArgumentException();
            }

        } catch (NoSuchElementException nsee) {
            throw new NoSuchElementException("Unable to locate any elements using " + loc, nsee);
        }

        int numberOfElementsFound = e.size();

        if (numberOfElementsFound == 0)
            throw new NoSuchElementException("Elements found: " + numberOfElementsFound);

        logger.debug("Elements found: " + numberOfElementsFound);

        return e;
    }

    private static boolean isElementPresent(Locator locType, String loc, long wait) {

        FluentWait<WebDriver> fluentWait = new FluentWait<>(getWebContext().getDriver())
                .withTimeout(wait, SECONDS)
                .pollingEvery(5, SECONDS)
                .ignoring(NoSuchElementException.class);

        try {

            switch (locType) {
                case CLASS_NAME:
                    fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.className(loc)));
                    break;
                case CSS_SELECTOR:
                    fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(loc)));
                    break;
                case ID:
                    fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.id(loc)));
                    break;
                case LINK_TEXT:
                    fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(loc)));
                    break;
                case NAME:
                    fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.name(loc)));
                    break;
                case PARTIAL_LINK_TEXT:
                    fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(loc)));
                    break;
                case TAG_NAME:
                    fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(loc)));
                    break;
                case XPATH:
                    fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(loc)));
                    break;
            }

            return true;

        } catch (TimeoutException e) {
            logger.error("Timeout after waiting for " + getWebContext().getImplicitlyWait()
                    + " s. for presence of element by:  " + locType);
            return false;

        }

    }

    private static boolean isElementVisible(Locator locType, String loc, boolean visibility, long wait) {

        FluentWait<WebDriver> fluentWait = new FluentWait<>(getWebContext().getDriver())
                .withTimeout(wait, SECONDS)
                .pollingEvery(5, SECONDS)
                .ignoring(NoSuchElementException.class);

        try {

            switch (locType) {
                case CLASS_NAME:
                    if (visibility)
                        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.className(loc)));
                    else fluentWait.until(ExpectedConditions.invisibilityOfElementLocated(By.className(loc)));
                    break;
                case CSS_SELECTOR:
                    if (visibility)
                        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(loc)));
                    else fluentWait.until(ExpectedConditions.invisibilityOfElementLocated(By.className(loc)));
                    break;
                case ID:
                    if (visibility) fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(loc)));
                    else fluentWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(loc)));
                    break;
                case LINK_TEXT:
                    if (visibility)
                        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(loc)));
                    else fluentWait.until(ExpectedConditions.invisibilityOfElementLocated(By.linkText(loc)));
                    break;
                case NAME:
                    if (visibility) fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.name(loc)));
                    else fluentWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name(loc)));
                    break;
                case PARTIAL_LINK_TEXT:
                    if (visibility)
                        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(loc)));
                    else fluentWait.until(ExpectedConditions.invisibilityOfElementLocated(By.partialLinkText(loc)));
                    break;
                case TAG_NAME:
                    if (visibility) fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(loc)));
                    else fluentWait.until(ExpectedConditions.invisibilityOfElementLocated(By.tagName(loc)));
                    break;
                case XPATH:
                    if (visibility) fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc)));
                    else fluentWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(loc)));
                    break;
            }

            return true;

        } catch (TimeoutException e) {
            logger.error("Timeout after waiting for " + getWebContext().getImplicitlyWait()
                    + " s. for visibility of element by:  " + locType);
            return false;

        }

    }

    private static boolean isElementClickable(Locator locType, String loc, long wait) {

        FluentWait<WebDriver> fluentWait = new FluentWait<>(getWebContext().getDriver())
                .withTimeout(wait, SECONDS)
                .pollingEvery(5, SECONDS)
                .ignoring(NoSuchElementException.class);

        try {

            switch (locType) {
                case CLASS_NAME:
                    fluentWait.until(ExpectedConditions.elementToBeClickable(By.className(loc)));
                    break;
                case CSS_SELECTOR:
                    fluentWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(loc)));
                    break;
                case ID:
                    fluentWait.until(ExpectedConditions.elementToBeClickable(By.id(loc)));
                    break;
                case LINK_TEXT:
                    fluentWait.until(ExpectedConditions.elementToBeClickable(By.linkText(loc)));
                    break;
                case NAME:
                    fluentWait.until(ExpectedConditions.elementToBeClickable(By.name(loc)));
                    break;
                case PARTIAL_LINK_TEXT:
                    fluentWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(loc)));
                    break;
                case TAG_NAME:
                    fluentWait.until(ExpectedConditions.elementToBeClickable(By.tagName(loc)));
                    break;
                case XPATH:
                    fluentWait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc)));
                    break;
            }

            return true;

        } catch (TimeoutException e) {
            logger.error("Timeout after waiting for " + getWebContext().getImplicitlyWait()
                    + " s. for element to be clickable located by:  " + locType);
            return false;

        }

    }

}

/**
 * Element Locator Factory Interface
 */
interface IElementLocatorFactory {

    /**
     * Locate a element by @param Locator and it's @param value
     *
     * @param locator Locator type
     * @param value   Locator value
     * @return WebElement element
     */
    WebElement locateElement(Locator locator, String value);

    /**
     * Locate WebElements by @param Locator and it's @param value
     *
     * @param locator Locator type
     * @param value   Locator value
     * @return Collection List<WebElement> having all elements found
     */
    List<WebElement> locateElements(Locator locator, String value);

}

/**
 * Wait Factory Interface
 */
interface IWaitFactory {

    boolean presenceOfElement(Locator locator, String value, long wait);

    boolean visibilityOfElement(Locator locator, String value, long wait);

    boolean invisibilityOfElement(Locator locator, String value, long wait);

    boolean elementToBeClickable(Locator locator, String value, long wait);

}