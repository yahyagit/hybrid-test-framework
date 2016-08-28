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

package com.atanas.kanchev.testframework.commons.conf;

import java.util.Arrays;

public enum JVMArgs {

    implicitlyWait,
    pageLoadTimeout,
    pollingInterval,
    reuseBrowser,
    reopenBrowserOnFail,
    closeBrowserTimeout,
    browser, browserSize,
    startMaximized,
    userAgent,
    chromeSwitches,
    hub,
    browserVersion,
    platform,
    pageLoadStrategy,
    screenshotOnFailure,
    savePageSourceOnFailure,
    reportsFolder,
    proxyEnabled,
    proxyHostname,
    proxyPort;

    public static String getAllArgs() {
        return "JVMArgs{" + Arrays.toString(JVMArgs.values()) + '}';
    }
}
