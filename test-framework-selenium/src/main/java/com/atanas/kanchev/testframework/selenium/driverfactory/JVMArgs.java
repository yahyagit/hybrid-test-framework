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

import java.util.Arrays;

public enum JVMArgs {

    POLLING_INTERVAL("polling.interval"),
    IMPLICITLY_WAIT("implicitly.wait"),
    PAGE_LOAD_TIMEOUT("page.load.timeout"),
    REUSE_BROWSER("reuse.browser"),
    REOPEN_BROWSER_ON_FAIL("reopen.browser.on.fail"),
    CLOSE_BROWSER_TIMEOUT("close.browser.timeout"),
    BROWSER("browser"),
    BROWSER_SIZE("browser.size"),
    START_MAXIMISED("start.maximised"),
    USER_AGENT("user.agent"),
    CHROME_SWITCHES("chrome.switches"),
    BROWSER_VERSION("browser.version"),
    HUB("hub"),
    PLATFORM("platform"),;

    String name;

    JVMArgs(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override public String toString() {
        return "JVMArgs {" + Arrays.toString(JVMArgs.values()) + '}';
    }
}
