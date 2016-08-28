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

import java.util.concurrent.TimeUnit;

public class Defaults {

    /**
     * Constant <code>DEF_IMPL_WAIT</code>
     */
    public static final long DEF_IMPL_WAIT = 500L;
    /**
     * Constant <code>DEF_PAGE_LOAD_TIMEOUT</code>
     */
    public static final long DEF_PAGE_LOAD_TIMEOUT = 30000L;
    /**
     * Constant <code>DEF_POLLING_INTERVAL</code>
     */
    public static final long DEF_POLLING_INTERVAL = 100L;
    /**
     * Constant <code>DEF_REUSE_BROWSER</code>
     */
    public static final boolean DEF_REUSE_BROWSER = false;
    /**
     * Constant <code>DEF_REOPEN_BROWSER_ON_FAIL</code>
     */
    public static final boolean DEF_REOPEN_BROWSER_ON_FAIL = true;
    /**
     * Constant <code>DEF_CLOSE_BROWSER_TIMEOUT</code>
     */
    public static final long DEF_CLOSE_BROWSER_TIMEOUT = 5000L;
    /**
     * Constant <code>DEF_BROWSER</code>
     */
    public static final DriverType DEF_BROWSER = DriverType.FIREFOX;
    /**
     * Constant <code>DEF_BROWSER_RES_WIDTH</code>
     */
    public static final int DEF_BROWSER_RES_WIDTH = 1024;
    /**
     * Constant <code>DEF_BROWSER_RES_HEIGHT</code>
     */
    public static final int DEF_BROWSER_RES_HEIGHT = 768;
    /**
     * Constant <code>DEF_BROWSER_RES_HEIGHT</code>
     */
    public static final boolean DEF_START_MAXIMISED = false;
    /**
     * Constant <code>DEF_SCREENSHOT_ON_FAILURE</code>
     */
    public static final boolean DEF_SCREENSHOT_ON_FAILURE = true;
    /**
     * Constant <code>DEF_PAGE_SOURCE_ON_FAILURE</code>
     */
    public static final boolean DEF_PAGE_SOURCE_ON_FAILURE = false;
    /**
     * Constant <code>DEF_REPORTS_FOLDER</code>
     */
    public static final String DEF_REPORTS_FOLDER = "target/reports/tests";
    /**
     * Constant <code>OPERATING_SYSTEM="System.getProperty(os.name).toUpperCase"{trunked}</code>
     */
    public static final String OPERATING_SYSTEM = System.getProperty("os.name").toUpperCase();
    /**
     * Constant <code>SYSTEM_ARCHITECTURE="System.getProperty(os.arch)"</code>
     */
    public static final String SYSTEM_ARCHITECTURE = System.getProperty("os.arch");
    /**
     * Constant <code>DEF_TIME_UNITS</code>
     */
    public static TimeUnit DEF_TIME_UNITS = TimeUnit.MILLISECONDS;

    @Override public String toString() {
        return "Defaults{" + "DEF_IMPL_WAIT=" + DEF_IMPL_WAIT + ",DEF_PAGE_LOAD_TIMEOUT="
            + DEF_PAGE_LOAD_TIMEOUT + ",DEF_POLLING_INTERVAL=" + DEF_POLLING_INTERVAL
            + ",DEF_REUSE_BROWSER=" + DEF_REUSE_BROWSER + ",DEF_REOPEN_BROWSER_ON_FAIL="
            + DEF_REOPEN_BROWSER_ON_FAIL + ",DEF_CLOSE_BROWSER_TIMEOUT=" + DEF_CLOSE_BROWSER_TIMEOUT
            + ",DEF_BROWSER=" + DEF_BROWSER + ",DEF_BROWSER_RES_WIDTH=" + DEF_BROWSER_RES_WIDTH
            + ",DEF_BROWSER_RES_HEIGHT=" + DEF_BROWSER_RES_HEIGHT + ",DEF_START_MAXIMISED="
            + DEF_START_MAXIMISED + ",DEF_SCREENSHOT_ON_FAILURE=" + DEF_SCREENSHOT_ON_FAILURE
            + ",DEF_PAGE_SOURCE_ON_FAILURE=" + DEF_PAGE_SOURCE_ON_FAILURE + ",DEF_REPORTS_FOLDER='"
            + DEF_REPORTS_FOLDER + '\'' + ",OPERATING_SYSTEM='" + OPERATING_SYSTEM + '\''
            + ",SYSTEM_ARCHITECTURE='" + SYSTEM_ARCHITECTURE + '\'' + '}';
    }
}
