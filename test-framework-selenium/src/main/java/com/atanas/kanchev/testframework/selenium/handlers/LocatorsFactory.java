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
import static com.atanas.kanchev.testframework.selenium.handlers.IWait.getWebContext;

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
        public boolean presenceOfElementLocatedBy(LocatorsFactory locType, String loc) {
            return probePresenceOfElement(LocatorsFactory.XPATH, loc);
        }

        @Override
        public boolean visibilityOfElementBy(LocatorsFactory locType, String loc) {
            return probeVisibilityOfElement(LocatorsFactory.XPATH, loc, true);
        }

        @Override
        public boolean invisibilityOfElementBy(LocatorsFactory locType, String loc) {
            return probeVisibilityOfElement(LocatorsFactory.XPATH, loc, false);
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
        public boolean presenceOfElementLocatedBy(LocatorsFactory locType, String loc) {
            return probePresenceOfElement(LocatorsFactory.CSS_SELECTOR, loc);
        }

        @Override
        public boolean visibilityOfElementBy(LocatorsFactory locType, String loc) {
            return probeVisibilityOfElement(LocatorsFactory.CSS_SELECTOR, loc, true);
        }

        @Override
        public boolean invisibilityOfElementBy(LocatorsFactory locType, String loc) {
            return probeVisibilityOfElement(LocatorsFactory.CSS_SELECTOR, loc, false);
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
        public boolean presenceOfElementLocatedBy(LocatorsFactory locType, String loc) {
            return probePresenceOfElement(LocatorsFactory.ID, loc);
        }

        @Override
        public boolean visibilityOfElementBy(LocatorsFactory locType, String loc) {
            return probeVisibilityOfElement(LocatorsFactory.ID, loc, true);
        }

        @Override
        public boolean invisibilityOfElementBy(LocatorsFactory locType, String loc) {
            return probeVisibilityOfElement(LocatorsFactory.ID, loc, false);
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
        public boolean presenceOfElementLocatedBy(LocatorsFactory locType, String loc) {
            return probePresenceOfElement(LocatorsFactory.CLASS_NAME, loc);
        }

        @Override
        public boolean visibilityOfElementBy(LocatorsFactory locType, String loc) {
            return probeVisibilityOfElement(LocatorsFactory.CLASS_NAME, loc, true);
        }

        @Override
        public boolean invisibilityOfElementBy(LocatorsFactory locType, String loc) {
            return probeVisibilityOfElement(LocatorsFactory.CLASS_NAME, loc, false);
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
        public boolean presenceOfElementLocatedBy(LocatorsFactory locType, String loc) {
            return probePresenceOfElement(LocatorsFactory.LINK_TEXT, loc);
        }

        @Override
        public boolean visibilityOfElementBy(LocatorsFactory locType, String loc) {
            return probeVisibilityOfElement(LocatorsFactory.LINK_TEXT, loc, true);
        }

        @Override
        public boolean invisibilityOfElementBy(LocatorsFactory locType, String loc) {
            return probeVisibilityOfElement(LocatorsFactory.LINK_TEXT, loc, false);
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
        public boolean presenceOfElementLocatedBy(LocatorsFactory locType, String loc) {
            return probePresenceOfElement(LocatorsFactory.PARTIAL_LINK_TEXT, loc);
        }

        @Override
        public boolean visibilityOfElementBy(LocatorsFactory locType, String loc) {
            return probeVisibilityOfElement(LocatorsFactory.PARTIAL_LINK_TEXT, loc, true);
        }

        @Override
        public boolean invisibilityOfElementBy(LocatorsFactory locType, String loc) {
            return probeVisibilityOfElement(LocatorsFactory.PARTIAL_LINK_TEXT, loc, false);
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
        public boolean presenceOfElementLocatedBy(LocatorsFactory locType, String loc) {
            return probePresenceOfElement(LocatorsFactory.TAG_NAME, loc);
        }

        @Override
        public boolean visibilityOfElementBy(LocatorsFactory locType, String loc) {
            return probeVisibilityOfElement(LocatorsFactory.TAG_NAME, loc, true);
        }

        @Override
        public boolean invisibilityOfElementBy(LocatorsFactory locType, String loc) {
            return probeVisibilityOfElement(LocatorsFactory.TAG_NAME, loc, false);
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
        public boolean presenceOfElementLocatedBy(LocatorsFactory locType, String loc) {
            return probePresenceOfElement(LocatorsFactory.NAME, loc);
        }

        @Override
        public boolean visibilityOfElementBy(LocatorsFactory locType, String loc) {
            return probeVisibilityOfElement(LocatorsFactory.NAME, loc, true);
        }

        @Override
        public boolean invisibilityOfElementBy(LocatorsFactory locType, String loc) {
            return probeVisibilityOfElement(LocatorsFactory.NAME, loc, false);
        }
    };

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(LocatorsFactory.class);

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
            throw new NoSuchElementException("Unable to locate element using " + loc, nsee);
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

    private static boolean probePresenceOfElement(LocatorsFactory locType, String loc) {

        WebDriverWait wait = new WebDriverWait(getWebContext().getDriver(), getWebContext().getImplicitlyWait());

        try {

            switch (locType) {
                case CLASS_NAME:
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.className(loc)));
                    break;
                case CSS_SELECTOR:
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(loc)));
                    break;
                case ID:
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.id(loc)));
                    break;
                case LINK_TEXT:
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(loc)));
                    break;
                case NAME:
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.name(loc)));
                    break;
                case PARTIAL_LINK_TEXT:
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(loc)));
                    break;
                case TAG_NAME:
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(loc)));
                    break;
                case XPATH:
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(loc)));
                    break;
            }

            return true;

        } catch (TimeoutException e) {
            logger.error("Timeout after waiting for " + getWebContext().getImplicitlyWait()
                    + " s. for presence of element by:  " + locType);
            return false;

        }

    }

    private static boolean probeVisibilityOfElement(LocatorsFactory locType, String loc, boolean visibility) {

        WebDriverWait wait = new WebDriverWait(getWebContext().getDriver(), getWebContext().getImplicitlyWait());

        try {

            switch (locType) {
                case CLASS_NAME:
                    if (visibility) wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(loc)));
                    else wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className(loc)));
                    break;
                case CSS_SELECTOR:
                    if (visibility) wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(loc)));
                    else wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className(loc)));
                    break;
                case ID:
                    if (visibility) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(loc)));
                    else wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(loc)));
                    break;
                case LINK_TEXT:
                    if (visibility) wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(loc)));
                    else wait.until(ExpectedConditions.invisibilityOfElementLocated(By.linkText(loc)));
                    break;
                case NAME:
                    if (visibility) wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(loc)));
                    else wait.until(ExpectedConditions.invisibilityOfElementLocated(By.name(loc)));
                    break;
                case PARTIAL_LINK_TEXT:
                    if (visibility) wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(loc)));
                    else wait.until(ExpectedConditions.invisibilityOfElementLocated(By.partialLinkText(loc)));
                    break;
                case TAG_NAME:
                    if (visibility) wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(loc)));
                    else wait.until(ExpectedConditions.invisibilityOfElementLocated(By.tagName(loc)));
                    break;
                case XPATH:
                    if (visibility) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc)));
                    else wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(loc)));
                    break;
            }

            return true;

        } catch (TimeoutException e) {
            logger.error("Timeout after waiting for " + getWebContext().getImplicitlyWait()
                    + " s. for visibility of element by:  " + locType);
            return false;

        }

    }

}

interface IElementLocatorFactory {

    WebElement locateElement(LocatorsFactory locType, String loc);

    List<WebElement> locateElements(LocatorsFactory locType, String loc);

}

interface IWaitFactory {

    boolean presenceOfElementLocatedBy(LocatorsFactory locType, String loc);

    boolean visibilityOfElementBy(LocatorsFactory locType, String loc);

    boolean invisibilityOfElementBy(LocatorsFactory locType, String loc);

}