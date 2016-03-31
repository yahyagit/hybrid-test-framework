package com.atanas.kanchev.testframework.selenium.handlers;

import com.atanas.kanchev.testframework.selenium.context.WebContext;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.atanas.kanchev.testframework.selenium.context.ContextFactory.getCurrentContext;

/**
 * WebDriver Wait Interface
 */
interface IWait extends IWebHandler {

    // the logger
    Logger logger = LoggerFactory.getLogger(IWait.class);

    default IWait setImplicitlyWait(long wait) {
        getWebContext().setImplicitlyWait(wait);
        return this;
    }

    default IWait setPageLoadTimeout(long timeout) {
        getWebContext().setPageLoadTimeout(timeout);
        return this;
    }

    static WebContext getWebContext() {
        return ((WebContext) getCurrentContext());
    }

//    /**
//     * Wait For Presence Of Element By Id
//     *
//     * @param id
//     * @return this
//     */
//    default IWait presenceOfElementById(final String id) {
//
//        try {
//            new WebDriverWait(getWebContext().getDriver(), getWebContext().getImplicitlyWait())
//                    .until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
//        } catch (TimeoutException e) {
//            logger.error("Timeout after waiting for " + getWebContext().getImplicitlyWait()
//                    + " s. for presence of element By ID:  " + id);
//            throw new TimeoutException(e.getMessage());
//
//        }
//        return this;
//    }


//    /**
//     * Wait For Visibility Of Element By Id
//     *
//     * @param id
//     * @return this
//     */
//    default IWait visibilityOfElementById(final String id) {
//
//        try {
//            new WebDriverWait(getWebContext().getDriver(), getWebContext().getImplicitlyWait())
//                    .until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
//        } catch (TimeoutException e) {
//            logger.error("Timeout after waiting for " + getWebContext().getImplicitlyWait()
//                    + " s. for visibility of element by ID:  " + id);
//            throw new TimeoutException(e.getMessage());
//
//        }
//        return this;
//    }

//    /**
//     * Wait For Visibility Of Element By Class Name
//     *
//     * @param className
//     * @return this
//     */
//    default IWait visibilityOfElementByClassName(final String className) {
//
//        try {
//            new WebDriverWait(getWebContext().getDriver(), getWebContext().getImplicitlyWait())
//                    .until(ExpectedConditions.visibilityOfElementLocated(By.className(className)));
//        } catch (TimeoutException e) {
//            logger.error("Timeout after waiting for " + getWebContext().getImplicitlyWait()
//                    + " s. for visibility of element by class name:  " + className);
//            throw new TimeoutException(e.getMessage());
//        }
//        return this;
//    }

//    /**
//     * Wait For Visibility Of Element By Name
//     *
//     * @param name
//     * @return this
//     */
//    default IWait visibilityOfElementByName(final String name) {
//
//        try {
//            new WebDriverWait(getWebContext().getDriver(), getWebContext().getImplicitlyWait())
//                    .until(ExpectedConditions.visibilityOfElementLocated(By.name(name)));
//        } catch (TimeoutException e) {
//            logger.error("Timeout after waiting for " + getWebContext().getImplicitlyWait()
//                    + " s. for visibility of element by name:  " + name);
//            throw new TimeoutException(e.getMessage());
//        }
//        return this;
//    }


//    /**
//     * Wait For Visibility Of Element By Css
//     *
//     * @param css
//     * @return this
//     */
//    default IWait visibilityOfElementByCss(String css) {
//
//        try {
//            new WebDriverWait(getWebContext().getDriver(), getWebContext().getImplicitlyWait())
//                    .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(css)));
//        } catch (TimeoutException e) {
//            logger.error("Timeout after waiting for " + getWebContext().getImplicitlyWait()
//                    + " s. for visibility of element by css:  " + css);
//            throw new TimeoutException(e.getMessage());
//        }
//        return this;
//    }

//    /**
//     * Wait For Visibility Of Element By Xpath
//     *
//     * @param xpath
//     * @return this
//     */
//    default IWait visibilityOfElementByXpath(String xpath) {
//
//        try {
//            new WebDriverWait(getWebContext().getDriver(), getWebContext().getImplicitlyWait())
//                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
//        } catch (TimeoutException e) {
//            logger.error("Timeout after waiting for " + getWebContext().getImplicitlyWait()
//                    + " s. for visibility of element by xpath:  " + xpath);
//            throw new TimeoutException(e.getMessage());
//        }
//        return this;
//    }

//    /**
//     * Wait For Invisibility Of Element By Id
//     *
//     * @param id
//     * @return this
//     */
//    default IWait invisibilityOfElementById(String id) {
//
//        try {
//            new WebDriverWait(getWebContext().getDriver(), getWebContext().getImplicitlyWait())
//                    .until(ExpectedConditions.invisibilityOfElementLocated(By.id(id)));
//        } catch (TimeoutException e) {
//            logger.error("Timeout after waiting for " + getWebContext().getImplicitlyWait()
//                    + " s. for invisibility of element by id:  " + id);
//            throw new TimeoutException(e.getMessage());
//        }
//        return this;
//    }

//    /**
//     * Wait For Invisibility Of Element By Class Name
//     *
//     * @param className
//     * @return this
//     */
//    default IWait invisibilityOfElementByClassName(String className) {
//
//        try {
//            new WebDriverWait(getWebContext().getDriver(), getWebContext().getImplicitlyWait())
//                    .until(ExpectedConditions.invisibilityOfElementLocated(By.className(className)));
//        } catch (TimeoutException e) {
//            logger.error("Timeout after waiting for " + getWebContext().getImplicitlyWait()
//                    + " s. for invisibility of element by class name:  " + className);
//            throw new TimeoutException(e.getMessage());
//        }
//        return this;
//    }
//
//    /**
//     * Wait For Invisibility Of Element By Css
//     *
//     * @param css
//     * @return this
//     */
//    default IWait invisibilityOfElementByCss(final String css) {
//
//        try {
//            new WebDriverWait(getWebContext().getDriver(), getWebContext().getImplicitlyWait())
//                    .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(css)));
//        } catch (TimeoutException e) {
//            logger.error("Timeout after waiting for " + getWebContext().getImplicitlyWait()
//                    + " s. for invisibility of element by css:  " + css);
//            throw new TimeoutException(e.getMessage());
//        }
//        return this;
//    }
//
//    /**
//     * Wait For Invisibility Of Element By Xpath
//     *
//     * @param xpath
//     * @return this
//     */
//    default IWait invisibilityOfElementByXpath(final String xpath) {
//
//        try {
//            new WebDriverWait(getWebContext().getDriver(), getWebContext().getImplicitlyWait())
//                    .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
//        } catch (TimeoutException e) {
//            logger.error("Timeout after waiting for " + getWebContext().getImplicitlyWait()
//                    + " s. for invisibility of element by xpath:  " + xpath);
//            throw new TimeoutException(e.getMessage());
//        }
//        return this;
//    }

//    /**
//     * Wait For Presence Of Element By Css
//     *
//     * @param cssSelector
//     * @return this
//     */
//    default IWait presenceOfElementByCss(final String cssSelector) {
//
//        try {
//            new WebDriverWait(getWebContext().getDriver(), getWebContext().getImplicitlyWait())
//                    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssSelector)));
//        } catch (TimeoutException e) {
//            logger.error("Timeout after waiting for " + getWebContext().getImplicitlyWait()
//                    + " s. for invisibility of element by cssSelector:  " + cssSelector);
//            throw new TimeoutException(e.getMessage());
//        }
//        return this;
//    }

//    /**
//     * Wait For Presence Of Element By Class Name
//     *
//     * @param className
//     * @return this
//     */
//    default IWait presenceOfElementByClassName(final String className) {
//
//        try {
//            new WebDriverWait(getWebContext().getDriver(), getWebContext().getImplicitlyWait())
//                    .until(ExpectedConditions.presenceOfElementLocated(By.className(className)));
//        } catch (TimeoutException e) {
//            logger.error("Timeout after waiting for " + getWebContext().getImplicitlyWait()
//                    + " s. for invisibility of element by className:  " + className);
//            throw new TimeoutException(e.getMessage());
//        }
//        return this;
//    }

    /**
     * Wait For Value To Be Present
     *
     * @param text
     * @return this
     */
    default IWait valueToBePresent(final String text) {

        try {
            new WebDriverWait(getWebContext().getDriver(), getWebContext().getImplicitlyWait())
                    .until(ExpectedConditions.textToBePresentInElementValue(getWebContext().getCurrentElement(), text));
        } catch (TimeoutException e) {
            logger.error("Timeout after waiting for " + getWebContext().getImplicitlyWait()
                    + " s. for invisibility of element by text:  " + text);
            throw new TimeoutException(e.getMessage());
        }
        return this;
    }


    /**
     * Wait For Stale Element By Css
     *
     * @param cssSelector
     */
    default IWait staleElementByCss(final String cssSelector) {
        try {
            logger.debug("Trying to find element: " + cssSelector + " within waitForStale Method");
            findElementBy(LocatorsFactory.CSS_SELECTOR, cssSelector);
        } catch (StaleElementReferenceException e) {
            logger.debug("Element By CSS: " + cssSelector + " Triggered Stale Element Reference");
            sleep(2000);
        } catch (NoSuchElementException e) {
            logger.debug("Element By CSS: " + cssSelector + " was not found");
            sleep(2000);
        }
        goToRootElement();

        return this;
    }

    /**
     * Wait For Text To Be Present In Element
     *
     * @param text
     * @return
     */
    default IWait textToBePresentInElement(final String text) {


        try {
            new WebDriverWait(getWebContext().getDriver(), getWebContext().getImplicitlyWait()).
                    until(ExpectedConditions.textToBePresentInElement(getWebContext().getCurrentElement(), text));
        } catch (TimeoutException e) {
            logger.error("String Not Found: " + text);
            throw new TimeoutException(e.getMessage());
        }
        return this;
    }

//    default IWait waitForElementToBeClickableById(String id) {
//        boolean found = true;
//        try {
//            (new WebDriverWait(driver, explicitWaitTime)).until(ExpectedConditions.elementToBeClickable(By.id(id)));
//        } catch (Exception var4) {
//            this.logger.error("waitForElementToBeClickableById : Element Not Found: " + id);
//            found = false;
//        }
//
//        return found;
//    }
//
//
//    default IWait waitForElementToBeClickableByClassName(String className) {
//        boolean found = true;
//        try {
//            (new WebDriverWait(driver, explicitWaitTime))
//                    .until(ExpectedConditions.elementToBeClickable(By.className(className)));
//        } catch (Exception var4) {
//            this.logger.error("waitForElementToBeClickableByClassName : Element Not Found: " + className);
//            found = false;
//        }
//
//        return found;
//    }
//
//
//    default IWait waitForElementToBeClickableByCss(String css) {
//        boolean found = true;
//        try {
//            (new WebDriverWait(driver, explicitWaitTime))
//                    .until(ExpectedConditions.elementToBeClickable(By.cssSelector(css)));
//        } catch (Exception var4) {
//            this.logger.error("waitForElementToBeClickableByCss : Element Not Found: " + css);
//            found = false;
//        }
//
//        return found;
//    }
//
//
//    default IWait waitForElementToBeClickableByXPath(String xpath) {
//        boolean found = true;
//        try {
//            (new WebDriverWait(driver, explicitWaitTime))
//                    .until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
//        } catch (Exception var4) {
//            this.logger.error("waitForElementToBeClickableByXPath : Element Not Found: " + xpath);
//            found = false;
//        }
//
//        return found;
//    }
//
//
//    default IWait waitForElementToBeClickableByName(String name) {
//        boolean found = true;
//        try {
//            (new WebDriverWait(driver, explicitWaitTime)).until(ExpectedConditions.elementToBeClickable(By.name(name)));
//        } catch (Exception var4) {
//            this.logger.error("waitForElementToBeClickableByName : Element Not Found: " + name);
//            found = false;
//        }
//
//        return found;
//    }
//
//    @Override
//    default waitForElementToBeClickableByPartialLinkText(String linkText) {
//        boolean found = true;
//        try {
//            (new WebDriverWait(driver, explicitWaitTime))
//                    .until(ExpectedConditions.elementToBeClickable(By.partialLinkText(linkText)));
//        } catch (Exception var4) {
//            this.logger.error("waitForElementToBeClickableByPartialLinkText : Element Not Found: " + linkText);
//            found = false;
//        }
//
//        return found;
//    }
//
//    @Override
//    default waitForElementToBeClickableByTagName(String tag) {
//        boolean found = true;
//        try {
//            (new WebDriverWait(driver, explicitWaitTime))
//                    .until(ExpectedConditions.elementToBeClickable(By.tagName(tag)));
//        } catch (Exception var4) {
//            this.logger.error("waitForElementToBeClickableByTagName : Element Not Found: " + tag);
//            found = false;
//        }
//
//        return found;
//    }

}