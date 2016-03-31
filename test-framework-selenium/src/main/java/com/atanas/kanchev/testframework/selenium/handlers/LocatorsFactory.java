package com.atanas.kanchev.testframework.selenium.handlers;

import com.atanas.kanchev.testframework.core.CustomExceptions;
import com.atanas.kanchev.testframework.selenium.context.WebContext;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.atanas.kanchev.testframework.selenium.context.ContextFactory.getCurrentContext;

/**
 * Locators Factory
 */
public enum LocatorsFactory implements IElementLocatorFactory, IWaitFactory {

    XPATH {
        @Override
        public WebElement locateElement(LocatorsFactory locType, String loc) {
            return findElement(LocatorsFactory.XPATH, loc);
        }

        @Override
        public List<WebElement> locateElements(LocatorsFactory locType, String loc) {
            return findElements(LocatorsFactory.XPATH, loc);
        }

        @Override
        public boolean presenceOfElement(LocatorsFactory locType, String loc, long wait) {
            return isElementPresent(LocatorsFactory.XPATH, loc, wait);
        }

        @Override
        public boolean visibilityOfElement(LocatorsFactory locType, String loc, long wait) {
            return isElementVisible(LocatorsFactory.XPATH, loc, true, wait);
        }

        @Override
        public boolean invisibilityOfElement(LocatorsFactory locType, String loc, long wait) {
            return isElementVisible(LocatorsFactory.XPATH, loc, false, wait);
        }

        @Override
        public boolean elementToBeClickable(LocatorsFactory locType, String loc, long wait) {
            return isElementClickable(LocatorsFactory.XPATH, loc, wait);
        }
    },

    CSS_SELECTOR {
        @Override
        public WebElement locateElement(LocatorsFactory locType, String loc) {
            return findElement(LocatorsFactory.CSS_SELECTOR, loc);
        }

        @Override
        public List<WebElement> locateElements(LocatorsFactory locType, String loc) {
            return findElements(LocatorsFactory.CSS_SELECTOR, loc);
        }

        @Override
        public boolean presenceOfElement(LocatorsFactory locType, String loc, long wait) {
            return isElementPresent(LocatorsFactory.CSS_SELECTOR, loc, wait);
        }

        @Override
        public boolean visibilityOfElement(LocatorsFactory locType, String loc, long wait) {
            return isElementVisible(LocatorsFactory.CSS_SELECTOR, loc, true, wait);
        }

        @Override
        public boolean invisibilityOfElement(LocatorsFactory locType, String loc, long wait) {
            return isElementVisible(LocatorsFactory.CSS_SELECTOR, loc, false, wait);
        }

        @Override
        public boolean elementToBeClickable(LocatorsFactory locType, String loc, long wait) {
            return isElementClickable(LocatorsFactory.CSS_SELECTOR, loc, wait);
        }
    },


    ID {
        @Override
        public WebElement locateElement(LocatorsFactory locType, String loc) {
            return findElement(LocatorsFactory.ID, loc);
        }

        @Override
        public List<WebElement> locateElements(LocatorsFactory locType, String loc) {
            return findElements(LocatorsFactory.ID, loc);
        }

        @Override
        public boolean presenceOfElement(LocatorsFactory locType, String loc, long wait) {
            return isElementPresent(LocatorsFactory.ID, loc, wait);
        }

        @Override
        public boolean visibilityOfElement(LocatorsFactory locType, String loc, long wait) {
            return isElementVisible(LocatorsFactory.ID, loc, true, wait);
        }

        @Override
        public boolean invisibilityOfElement(LocatorsFactory locType, String loc, long wait) {
            return isElementVisible(LocatorsFactory.ID, loc, false, wait);
        }

        @Override
        public boolean elementToBeClickable(LocatorsFactory locType, String loc, long wait) {
            return isElementClickable(LocatorsFactory.ID, loc, wait);
        }
    },


    CLASS_NAME {
        @Override
        public WebElement locateElement(LocatorsFactory locType, String loc) {
            return findElement(LocatorsFactory.CLASS_NAME, loc);
        }

        @Override
        public List<WebElement> locateElements(LocatorsFactory locType, String loc) {
            return findElements(LocatorsFactory.CLASS_NAME, loc);
        }

        @Override
        public boolean presenceOfElement(LocatorsFactory locType, String loc, long wait) {
            return isElementPresent(LocatorsFactory.CLASS_NAME, loc, wait);
        }

        @Override
        public boolean visibilityOfElement(LocatorsFactory locType, String loc, long wait) {
            return isElementVisible(LocatorsFactory.CLASS_NAME, loc, true, wait);
        }

        @Override
        public boolean invisibilityOfElement(LocatorsFactory locType, String loc, long wait) {
            return isElementVisible(LocatorsFactory.CLASS_NAME, loc, false, wait);
        }

        @Override
        public boolean elementToBeClickable(LocatorsFactory locType, String loc, long wait) {
            return isElementClickable(LocatorsFactory.CLASS_NAME, loc, wait);
        }
    },


    LINK_TEXT {
        @Override
        public WebElement locateElement(LocatorsFactory locType, String loc) {
            return findElement(LocatorsFactory.LINK_TEXT, loc);
        }

        @Override
        public List<WebElement> locateElements(LocatorsFactory locType, String loc) {
            return findElements(LocatorsFactory.LINK_TEXT, loc);
        }

        @Override
        public boolean presenceOfElement(LocatorsFactory locType, String loc, long wait) {
            return isElementPresent(LocatorsFactory.LINK_TEXT, loc, wait);
        }

        @Override
        public boolean visibilityOfElement(LocatorsFactory locType, String loc, long wait) {
            return isElementVisible(LocatorsFactory.LINK_TEXT, loc, true, wait);
        }

        @Override
        public boolean invisibilityOfElement(LocatorsFactory locType, String loc, long wait) {
            return isElementVisible(LocatorsFactory.LINK_TEXT, loc, false, wait);
        }

        @Override
        public boolean elementToBeClickable(LocatorsFactory locType, String loc, long wait) {
            return isElementClickable(LocatorsFactory.LINK_TEXT, loc, wait);
        }
    },


    PARTIAL_LINK_TEXT {
        @Override
        public WebElement locateElement(LocatorsFactory locType, String loc) {
            return findElement(LocatorsFactory.PARTIAL_LINK_TEXT, loc);
        }

        @Override
        public List<WebElement> locateElements(LocatorsFactory locType, String loc) {
            return findElements(LocatorsFactory.PARTIAL_LINK_TEXT, loc);
        }

        @Override
        public boolean presenceOfElement(LocatorsFactory locType, String loc, long wait) {
            return isElementPresent(LocatorsFactory.PARTIAL_LINK_TEXT, loc, wait);
        }

        @Override
        public boolean visibilityOfElement(LocatorsFactory locType, String loc, long wait) {
            return isElementVisible(LocatorsFactory.PARTIAL_LINK_TEXT, loc, true, wait);
        }

        @Override
        public boolean invisibilityOfElement(LocatorsFactory locType, String loc, long wait) {
            return isElementVisible(LocatorsFactory.PARTIAL_LINK_TEXT, loc, false, wait);
        }

        @Override
        public boolean elementToBeClickable(LocatorsFactory locType, String loc, long wait) {
            return isElementClickable(LocatorsFactory.PARTIAL_LINK_TEXT, loc, wait);
        }
    },


    TAG_NAME {
        @Override
        public WebElement locateElement(LocatorsFactory locType, String loc) {
            return findElement(LocatorsFactory.TAG_NAME, loc);
        }

        @Override
        public List<WebElement> locateElements(LocatorsFactory locType, String loc) {
            return findElements(LocatorsFactory.TAG_NAME, loc);
        }

        @Override
        public boolean presenceOfElement(LocatorsFactory locType, String loc, long wait) {
            return isElementPresent(LocatorsFactory.TAG_NAME, loc, wait);
        }

        @Override
        public boolean visibilityOfElement(LocatorsFactory locType, String loc, long wait) {
            return isElementVisible(LocatorsFactory.TAG_NAME, loc, true, wait);
        }

        @Override
        public boolean invisibilityOfElement(LocatorsFactory locType, String loc, long wait) {
            return isElementVisible(LocatorsFactory.TAG_NAME, loc, false, wait);
        }

        @Override
        public boolean elementToBeClickable(LocatorsFactory locType, String loc, long wait) {
            return isElementClickable(LocatorsFactory.TAG_NAME, loc, wait);
        }
    },


    NAME {
        @Override
        public WebElement locateElement(LocatorsFactory locType, String loc) {
            return findElement(LocatorsFactory.NAME, loc);
        }

        @Override
        public List<WebElement> locateElements(LocatorsFactory locType, String loc) {
            return findElements(LocatorsFactory.NAME, loc);
        }

        @Override
        public boolean presenceOfElement(LocatorsFactory locType, String loc, long wait) {
            return isElementPresent(LocatorsFactory.NAME, loc, wait);
        }

        @Override
        public boolean visibilityOfElement(LocatorsFactory locType, String loc, long wait) {
            return isElementVisible(LocatorsFactory.NAME, loc, true, wait);
        }

        @Override
        public boolean invisibilityOfElement(LocatorsFactory locType, String loc, long wait) {
            return isElementVisible(LocatorsFactory.NAME, loc, false, wait);
        }

        @Override
        public boolean elementToBeClickable(LocatorsFactory locType, String loc, long wait) {
            return isElementClickable(LocatorsFactory.NAME, loc, wait);
        }
    };

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(LocatorsFactory.class);

    static WebContext getWebContext() {
        return ((WebContext) getCurrentContext());
    }

    private static WebElement findElement(LocatorsFactory locType, String loc) {
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

    private static List<WebElement> findElements(LocatorsFactory locType, String loc) {
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

    private static boolean isElementPresent(LocatorsFactory locType, String loc, long wait) {

        WebDriverWait webDriverWait = new WebDriverWait(getWebContext().getDriver(), wait);

        try {

            switch (locType) {
                case CLASS_NAME:
                    webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.className(loc)));
                    break;
                case CSS_SELECTOR:
                    webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(loc)));
                    break;
                case ID:
                    webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(loc)));
                    break;
                case LINK_TEXT:
                    webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(loc)));
                    break;
                case NAME:
                    webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.name(loc)));
                    break;
                case PARTIAL_LINK_TEXT:
                    webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(loc)));
                    break;
                case TAG_NAME:
                    webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(loc)));
                    break;
                case XPATH:
                    webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(loc)));
                    break;
            }

            return true;

        } catch (TimeoutException e) {
            logger.error("Timeout after waiting for " + getWebContext().getImplicitlyWait()
                    + " s. for presence of element by:  " + locType);
            return false;

        }

    }

    private static boolean isElementVisible(LocatorsFactory locType, String loc, boolean visibility, long wait) {

        WebDriverWait webDriverWait = new WebDriverWait(getWebContext().getDriver(), wait);

        try {

            switch (locType) {
                case CLASS_NAME:
                    if (visibility) webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.className(loc)));
                    else webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.className(loc)));
                    break;
                case CSS_SELECTOR:
                    if (visibility) webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(loc)));
                    else webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.className(loc)));
                    break;
                case ID:
                    if (visibility) webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(loc)));
                    else webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(loc)));
                    break;
                case LINK_TEXT:
                    if (visibility) webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(loc)));
                    else webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.linkText(loc)));
                    break;
                case NAME:
                    if (visibility) webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.name(loc)));
                    else webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name(loc)));
                    break;
                case PARTIAL_LINK_TEXT:
                    if (visibility) webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(loc)));
                    else webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.partialLinkText(loc)));
                    break;
                case TAG_NAME:
                    if (visibility) webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(loc)));
                    else webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.tagName(loc)));
                    break;
                case XPATH:
                    if (visibility) webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc)));
                    else webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(loc)));
                    break;
            }

            return true;

        } catch (TimeoutException e) {
            logger.error("Timeout after waiting for " + getWebContext().getImplicitlyWait()
                    + " s. for visibility of element by:  " + locType);
            return false;

        }

    }

    private static boolean isElementClickable(LocatorsFactory locType, String loc, long wait) {

        WebDriverWait webDriverWait = new WebDriverWait(getWebContext().getDriver(), wait);

        try {

            switch (locType) {
                case CLASS_NAME:
                    webDriverWait.until(ExpectedConditions.elementToBeClickable(By.className(loc)));
                    break;
                case CSS_SELECTOR:
                    webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(loc)));
                    break;
                case ID:
                    webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id(loc)));
                    break;
                case LINK_TEXT:
                    webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText(loc)));
                    break;
                case NAME:
                    webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name(loc)));
                    break;
                case PARTIAL_LINK_TEXT:
                    webDriverWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(loc)));
                    break;
                case TAG_NAME:
                    webDriverWait.until(ExpectedConditions.elementToBeClickable(By.tagName(loc)));
                    break;
                case XPATH:
                    webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc)));
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

interface IElementLocatorFactory {

    WebElement locateElement(LocatorsFactory locType, String loc);

    List<WebElement> locateElements(LocatorsFactory locType, String loc);

}

interface IWaitFactory {

    boolean presenceOfElement(LocatorsFactory locType, String loc, long wait);

    boolean visibilityOfElement(LocatorsFactory locType, String loc, long wait);

    boolean invisibilityOfElement(LocatorsFactory locType, String loc, long wait);

    boolean elementToBeClickable(LocatorsFactory locType, String loc, long wait);

}