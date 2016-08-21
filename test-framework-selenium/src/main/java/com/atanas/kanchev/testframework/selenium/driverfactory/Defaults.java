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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Defaults {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(Defaults.class);

    /**
     * Constant <code>IMPL_WAIT</code>
     */
    public static final long IMPL_WAIT = 500L;

    /**
     * Constant <code>PAGE_LOAD_TIMEOUT</code>
     */
    public static final long PAGE_LOAD_TIMEOUT = 30000L;

    /**
     * Constant <code>POLLING_INTERVAL</code>
     */
    public static final long POLLING_INTERVAL = 100L;

    /**
     * Constant <code>REUSE_BROWSER</code>
     */
    public static final boolean REUSE_BROWSER = false;

    /**
     * Constant <code>REOPEN_BROWSER_ON_FAIL</code>
     */
    public static final boolean REOPEN_BROWSER_ON_FAIL = true;

    /**
     * Constant <code>CLOSE_BROWSER_TIMEOUT</code>
     */
    public static final long CLOSE_BROWSER_TIMEOUT = 5000L;

    /**
     * Constant <code>BROWSER</code>
     */
    public static final DriverType BROWSER = DriverType.FIREFOX;

    /**
     * Constant <code>BROWSER_RES_WIDTH</code>
     */
    public static final int BROWSER_RES_WIDTH = 1024;

    /**
     * Constant <code>BROWSER_RES_HEIGHT</code>
     */
    public static final int BROWSER_RES_HEIGHT = 768;

    /**
     * Constant <code>BROWSER_RES_HEIGHT</code>
     */
    public static final boolean START_MAXIMISED = false;

    /**
     * Constant <code>OPERATING_SYSTEM="System.getProperty(os.name).toUpperCase"{trunked}</code>
     */
    public static final String OPERATING_SYSTEM = System.getProperty("os.name").toUpperCase();

    /**
     * Constant <code>SYSTEM_ARCHITECTURE="System.getProperty(os.arch)"</code>
     */
    public static final String SYSTEM_ARCHITECTURE = System.getProperty("os.arch");


}
