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

package com.atanas.kanchev.testframework.selenium.driverfactory;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import static com.atanas.kanchev.testframework.selenium.driverfactory.DriverType.valueOf;
import static org.openqa.selenium.Proxy.ProxyType.MANUAL;

/**
 * <p>DriverFactory class.</p>
 *
 * @author Atanas Kanchev
 */
public class DriverFactory extends DriverConfig {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);
    private DriverType selectedDriverType;
    private Proxy proxy;

    /**
     * <p>getDriver.</p>
     *
     * @return a {@link org.openqa.selenium.WebDriver} object.
     */
    public WebDriver getDriver() {
        this.selectedDriverType = determineEffectiveDriverType();
        return instantiateWebDriver(selectedDriverType.getDesiredCapabilities(proxy));
    }

    private void configureProxy() {

        if (isProxyEnabled()) {
            proxy = new Proxy();
            proxy.setProxyType(MANUAL);
            proxy.setHttpProxy(getProxyDetails());
            proxy.setSslProxy(getProxyDetails());

        }
    }

    private DriverType determineEffectiveDriverType() {
        DriverType driverType = DEFAULT_BROWSER;
        try {
            driverType = valueOf(getBrowser());
        } catch (IllegalArgumentException ignored) {
            logger.error("Unknown driver specified, defaulting to '" + driverType + "'...");
        } catch (NullPointerException ignored) {
            logger.error("No driver specified, defaulting to '" + driverType + "'...");
        }
        return selectedDriverType = driverType;
    }

    private WebDriver instantiateWebDriver(DesiredCapabilities desiredCapabilities) {

        configureProxy();
        determineEffectiveDriverType();

        logger.debug("Current Operating System: " + OPERATING_SYSTEM);
        logger.debug("Current Architecture: " + SYSTEM_ARCHITECTURE);
        logger.debug("Current Browser Selection: " + selectedDriverType);

        if (null != getCustomCapabilities())
            desiredCapabilities = new DesiredCapsFactory()
                .mergeCapabilities(desiredCapabilities, getCustomCapabilities());

        logger.debug("Using " + desiredCapabilities);

        if (isUseRemoteWebDriver()) {
            RemoteWebDriverFactory remoteWebDriverFactory = new RemoteWebDriverFactory();
            return conf(remoteWebDriverFactory.getRemoteWebDriver(desiredCapabilities));
            //            logger.debug("Running on IP: " + remoteWebDriverFactory.getExecutionIP());
        } else {
            logger.debug("Running on IP: " + getExecutionIP());
            return conf(selectedDriverType.getWebDriverObject(desiredCapabilities));
        }

    }

    private WebDriver conf(WebDriver driver) {
        confTimeouts(driver);
        confResolution(driver);
        return driver;
    }

    private WebDriver confTimeouts(WebDriver driver) {
        logger.debug("Setting implicitly wait to: " + DEFAULT_IMPL_WAIT + " ms.");
        driver.manage().timeouts().implicitlyWait(DEFAULT_IMPL_WAIT, TimeUnit.MILLISECONDS);
        logger.debug("Setting page load timeout to: " + DEFAULT_PAGE_LOAD_TIMEOUT + " ms.");
        driver.manage().timeouts()
            .pageLoadTimeout(DEFAULT_PAGE_LOAD_TIMEOUT, TimeUnit.MILLISECONDS);
        return driver;
    }

    private WebDriver confResolution(WebDriver driver) {

        if (isStartMaximized()) {
            logger.debug("Maximising browser window");
            driver.manage().window().maximize();
        } else {
            logger.debug("Setting resolution to: " + DEFAULT_BROWSER_RES_WIDTH + "*"
                + DEFAULT_BROWSER_RES_HEIGHT);
            driver.manage().window()
                .setSize(new Dimension(DEFAULT_BROWSER_RES_WIDTH, DEFAULT_BROWSER_RES_HEIGHT));
        }

        return driver;
    }

    /////////////////////
    // STATIC MEMBERS //
    ////////////////////

    /**
     * <p>getExecutionIP.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getExecutionIP() {

        String ip = null;

        try {
            InetAddress IP = InetAddress.getLocalHost();
            ip = IP.getHostAddress();
        } catch (UnknownHostException e) {
            logger.error(e.getMessage());
        }

        return ip;
    }
}
