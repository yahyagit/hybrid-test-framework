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

import io.github.bonigarcia.wdm.*;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import static org.openqa.selenium.remote.CapabilityType.PROXY;

/**
 * <p>DriverType class.</p>
 *
 * @author Atanas Kanchev
 */
public enum DriverType implements DriverSetup {

    FIREFOX {
        public DesiredCapabilities getDesiredCapabilities(Proxy proxySettings) {
            return addProxySettings(new DesiredCapsFactory().getDefaultFirefoxCaps(),
                proxySettings);
        }

        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            MarionetteDriverManager.getInstance().setup();
            return new FirefoxDriver(capabilities);
        }
    },

    CHROME {
        public DesiredCapabilities getDesiredCapabilities(Proxy proxySettings) {
            return addProxySettings(new DesiredCapsFactory().getDefaultChromeCaps(), proxySettings);
        }

        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            ChromeDriverManager.getInstance().setup();
            return new ChromeDriver(capabilities);
        }
    },

    IE {
        public DesiredCapabilities getDesiredCapabilities(Proxy proxySettings) {
            return addProxySettings(new DesiredCapsFactory().getDefaultIECaps(), proxySettings);
        }

        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            InternetExplorerDriverManager.getInstance().setup();
            return new InternetExplorerDriver(capabilities);
        }
    },

    EDGE {
        public DesiredCapabilities getDesiredCapabilities(Proxy proxySettings) {
            return addProxySettings(new DesiredCapsFactory().getDefaultEdgeCaps(), proxySettings);
        }

        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            EdgeDriverManager.getInstance().setup();
            return new EdgeDriver(capabilities);
        }
    },

    SAFARI {
        public DesiredCapabilities getDesiredCapabilities(Proxy proxySettings) {
            return addProxySettings(new DesiredCapsFactory().getDefaultSafariCaps(), proxySettings);
        }

        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            return new SafariDriver(capabilities);
        }
    },

    OPERA {
        public DesiredCapabilities getDesiredCapabilities(Proxy proxySettings) {
            return addProxySettings(new DesiredCapsFactory().getDefaultOperaCaps(), proxySettings);
        }

        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            OperaDriverManager.getInstance().setup();
            return new OperaDriver(capabilities);
        }
    },

    PHANTOMJS {
        public DesiredCapabilities getDesiredCapabilities(Proxy proxySettings) {
            PhantomJsDriverManager.getInstance().setup();
            return new DesiredCapsFactory().getDefaultPhantomJSCaps(proxySettings);
        }

        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            return new PhantomJSDriver(capabilities);
        }
    };

    /**
     * Constant <code>logger</code>
     */
    private static final Logger logger = LoggerFactory.getLogger(DriverType.class);

    public static String getDriverTypes() {
        return "Supported browsers" + Arrays.toString(DriverType.values());
    }

    DesiredCapabilities addProxySettings(DesiredCapabilities capabilities, Proxy proxySettings) {
        if (null != proxySettings) {
            capabilities.setCapability(PROXY, proxySettings);
            logger.debug("Configured proxy settings: " + proxySettings);
        }

        return capabilities;
    }

}


interface DriverSetup {

    /**
     * <p>getWebDriverObject.</p>
     *
     * @param desiredCapabilities a {@link org.openqa.selenium.remote.DesiredCapabilities} object.
     * @return a {@link org.openqa.selenium.WebDriver} object.
     */
    WebDriver getWebDriverObject(DesiredCapabilities desiredCapabilities);

    /**
     * <p>getDesiredCapabilities.</p>
     *
     * @param proxySettings a {@link org.openqa.selenium.Proxy} object.
     * @return a {@link org.openqa.selenium.remote.DesiredCapabilities} object.
     */
    DesiredCapabilities getDesiredCapabilities(Proxy proxySettings);
}
