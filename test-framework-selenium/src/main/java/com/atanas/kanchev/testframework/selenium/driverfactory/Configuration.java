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

import org.openqa.selenium.Platform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Configuration {

    private static final Logger logger = LoggerFactory.getLogger(Configuration.class.getName());

    /**
     * Specifies the amount of time the driver should wait when searching for an element if it is
     * not immediately present.
     */
    public static long implicitlyWait = Long.parseLong(
        System.getProperty(JVMArgs.IMPLICITLY_WAIT.getName(), String.valueOf(Defaults.IMPL_WAIT)));

    /**
     * Sets the amount of time to wait for a page load to complete before throwing an error.
     */
    public static long pageLoadTimeout = Long.parseLong(System
        .getProperty(JVMArgs.PAGE_LOAD_TIMEOUT.getName(),
            String.valueOf(Defaults.PAGE_LOAD_TIMEOUT)));
    /**
     * Interval in milliseconds, when checking if a single element is appeared
     * Default value: 100 (milliseconds)
     */
    public static long pollingInterval = Long.parseLong(System
        .getProperty(JVMArgs.POLLING_INTERVAL.getName(),
            String.valueOf(Defaults.POLLING_INTERVAL)));

    /**
     * If holdBrowserOpen is true, browser window stays open after running tests.
     * It may be useful for debugging.
     * Can be configured either programmatically or by system property "-Dreuse.browser=true".
     * <p/>
     * Default value: false.
     */
    public static boolean reuseBrowser = Boolean.parseBoolean(System
        .getProperty(JVMArgs.REUSE_BROWSER.getName(), String.valueOf(Defaults.REUSE_BROWSER)));

    /**
     * Should re-spawn browser if it's disappeared (hangs, broken, unexpectedly closed).
     * <p>
     * Can be configured either programmatically or by system property "-Dreopen.browser.on.fail=false".
     * <p>
     * Default value: true
     * Set this property to false if you want to disable automatic re-spawning the browser.
     */
    public static boolean reopenBrowserOnFail = Boolean.parseBoolean(System
        .getProperty(JVMArgs.REOPEN_BROWSER_ON_FAIL.getName(),
            String.valueOf(Defaults.REOPEN_BROWSER_ON_FAIL)));

    /**
     * Timeout (in milliseconds) for closing/killing browser.
     * <p/>
     * Sometimes we have problems with calling driver.close() or driver.quit() method, and test always is suspended too long.
     * <p/>
     * Default value: 5000 (milliseconds)
     */
    public static long closeBrowserTimeout = Long.parseLong(System
        .getProperty(JVMArgs.CLOSE_BROWSER_TIMEOUT.getName(),
            String.valueOf(Defaults.CLOSE_BROWSER_TIMEOUT)));

    /**
     * Which browser to use.
     * Can be configured either programmatically or by system property "-Dbrowser=ie".
     * Supported values: "chrome", "firefox", "ie", "htmlunit", "phantomjs", "opera", "marionette"
     * <p/>
     * Default value: "firefox"
     */
    public static String browser =
        System.getProperty(JVMArgs.BROWSER.getName(), String.valueOf(Defaults.BROWSER));

    /**
     * The browser window size.
     * Can be configured either programmatically or by system property "-Dbrowser.size=1024x768".
     * <p>
     * Default value: none (browser size will not be set explicitly)
     */
    public static String browserSize = System.getProperty(JVMArgs.BROWSER_SIZE.getName(),
        Defaults.BROWSER_RES_WIDTH + "x" + Defaults.BROWSER_RES_HEIGHT);

    /**
     * The browser window is maximized when started.
     * Can be configured either programmatically or by system property "start-maximized=true".
     * <p>
     * Default value: true
     */
    public static boolean startMaximized = Boolean.parseBoolean(System
        .getProperty(JVMArgs.START_MAXIMISED.getName(), String.valueOf(Defaults.START_MAXIMISED)));

    /**
     * Emulate mobile devices by setting user agents
     * Can be configured either programmatically or
     * by system property "-Duser.agent="Mozilla/5.0 (iPhone; CPU iPhone OS 5_0 like Mac OS X)"".
     * <p>
     * Default value: none (browser size will not be set explicitly)
     */
    public static String userAgent = System.getProperty(JVMArgs.USER_AGENT.getName());

    /**
     * Value of "chrome.switches" parameter (in case of using Chrome driver).
     * Can be configured either programmatically or by system property,
     * i.e. "-Dchrome.switches=--disable-popup-blocking".
     * <p>
     * Default value: none
     */
    public static String chromeSwitches = System.getProperty(JVMArgs.CHROME_SWITCHES.getName());

    //    Selenium GRID //

    /**
     * URL of remote web driver (in case of using Selenium Grid).
     * Can be configured either programmatically or
     * by system property "-Dremote=http://localhost:5678/wd/hub".
     * <p>
     * Default value: null (Grid is not used).
     */
    public static String hub = System.getProperty(JVMArgs.HUB.getName());

    /**
     * Which browser version to use
     * Can be configured either programmatically or
     * by system property or "-Dbrowser.version=45".
     * <p/>
     * Default value: none
     */
    public static String browserVersion = System.getProperty(JVMArgs.BROWSER_VERSION.getName());

    /**
     * Which platform to use
     * Can be configured either programmatically or
     * by system property or "-Dplatform=linux".
     * <p/>
     * Default value: {@link org.openqa.selenium.Platform#ANY}
     */
    public static String platform = System.getProperty(JVMArgs.PLATFORM.getName(),
        String.valueOf(Platform.ANY));
   
    /**
     * Should webdriver wait until page is completely loaded.
     * Possible values: "none", "normal" and "eager".
     * Default value: "normal".
     * <p>
     * - `normal`: return after the load event fires on the new page (it's default in Selenium webdriver);
     * - `eager`: return after DOMContentLoaded fires;
     * - `none`: return immediately (it's default).
     * <p>
     * It seems that `none` is the best option for because all its commands wait until
     * corresponding condition becomes true.
     * Thought, we left default value `normal` because we afraid to break users' existing tests.
     * <p>
     * See https://w3c.github.io/webdriver/webdriver-spec.html#dfn-page-loading-strategy
     *
     * @since 3.5
     */
    public static String pageLoadStrategy = System.getProperty("page.load.strategy", "normal");

    /**
     * Does Selenide need to take screenshots on failing tests.
     * <p>
     * Default value: true
     */
    public static boolean screenshots =
        Boolean.parseBoolean(System.getProperty("screenshots", "true"));

    /**
     * Save page source on failing tests.
     * <p>
     * Default value: true
     */
    public static boolean savePageSource =
        Boolean.parseBoolean(System.getProperty("savePageSource", "true"));

    /**
     * Folder to store screenshots to.
     * Can be configured either programmatically or by system property "-Dreports=test-result/reports".
     * <p>
     * Default value: "target/reports/tests"
     */
    public static String reportsFolder = System.getProperty("reports", "target/reports/tests");

}
