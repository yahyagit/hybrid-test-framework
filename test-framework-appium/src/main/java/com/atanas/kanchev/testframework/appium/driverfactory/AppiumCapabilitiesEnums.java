package com.atanas.kanchev.testframework.appium.driverfactory;

/**
 * @author Atanas Kanchev
 *         Appium DesiredCapabolities wrapper class:
 *         <p/>
 *         Appium server capabilities enum {@link AppiumServer}
 *         <p/>
 *         Appium iOS capabilities enum {@link IOS}
 *         <p/>
 *         Appium Android specific capabilities {@link Android}
 */
public final class AppiumCapabilitiesEnums {

    /**
     * Appium Server capabilities enum
     */
    public enum AppiumServer {

        PLATFORM_NAME("platformName"),
        PLATFORM_VERSION("platformVersion"),
        DEVICE_NAME("deviceName"),
        APP("app"),
        UDID("udid"),
        NEW_COMMAND_TIMEOUT("newCommandTimeout"),
        AUTO_LAUNCH("autoLaunch"),
        NO_RESET("noReset"),
        ORIENTATION("orientation"),
        FULL_RESET("fullReset"),
        AUTOMATION_NAME("automationName"),
        BROWSER_NAME("browserName"),
        LANGUAGE("language"),
        AUTO_WEB_VIEW("autoWebview");

        private final String capabilityName;

        AppiumServer(String capabilityName) {
            this.capabilityName = capabilityName;
        }

        public String getName() {
            return capabilityName;
        }
    }

    /**
     * Appium iOS specific capabilities
     */
    public enum IOS {

        CALENDAR_FORMAT("calendarFormat"),
        BUNDLE_ID("bundleId"),
        LAUNCH_TIMEOUT("launchTimeout"),
        LOCATION_SERVICES_ENABLED("locationServicesEnabled"),
        LOCATION_SERVICES_AUTORISED("locationServicesAuthorized"),
        AUTO_ACCEPT_ALERTS("autoAcceptAlerts"),
        AUTO_DISMISS_ALERTS("autoDismissAlerts"),
        NATIVE_INSTRUMENTS_LIB("nativeInstrumentsLib"),
        NATIVE_WEB_TAP("nativeWebTap"),
        SAFARI_INITIAL_URL("safariInitialUrl"),
        SAFARI_ALLOW_POPUPS("safariAllowPopups"),
        SAFARI_IGNORE_FRAUD_WARNINGS("safariIgnoreFraudWarning"),
        SAFARI_OPEN_LINKS_IN_BACKGROUND("safariOpenLinksInBackground"),
        KEEP_KEY_CHAINS("keepKeyChains"),
        LOCALIZABLE_STRINGS_DIR("localizableStringsDir"),
        PROCESS_ARGUMENTS("processArguments"),
        INTER_KEY_DELAY("interKeyDelay"),
        SHOW_IOS_LOG("showIOSLog"),
        SEND_KEY_STRATEGY("sendKeyStrategy"),
        SCREENSHOT_WAIT_TIMEOUT("screenshotWaitTimeout"),
        WAIT_FOR_APP_SCRIPT("waitForAppScript");

        private final String capabilityName;

        IOS(String capabilityName) {
            this.capabilityName = capabilityName;
        }

        public String getName() {
            return capabilityName;
        }
    }

    /**
     * Appium Android specific capabilities
     */
    public enum Android {

        APP_ACTIVITY("appActivity"),
        APP_PACKAGE("appPackage"),
        APP_WAIT_ACTIVITY("appWaitActivity"),
        APP_WAIT_PACKAGE("appWaitPackage"),
        DEVICE_READY_TIMEOUT("deviceReadyTimeout"),
        ANDROID_COVERAGE("androidCoverage"),
        ENABLE_PERFORMANCE_LOGGING("enablePerformanceLogging"),
        ANDROID_DEVICE_READY_TIMEOUT("androidDeviceReadyTimeout"),
        ANDROID_DEVICE_SOCKET("androidDeviceSocket"),
        AVD("avd"),
        AVD_LAUNCH_TIMEOUT("avdLaunchTimeout"),
        AVD_READY_TIMEOUT("avdReadyTimeout"),
        AVD_ARGS("avdArgs"),
        USE_KEY_STORE("useKeystore"),
        KEY_STORE_PATH("keystorePath"),
        KEY_STORE_PASSWORD("keystorePassword"),
        KEY_ALIAS("keyAlias"),
        KEY_PASSWORD("keyPassword"),
        CHROME_DRIVER_EXECUTABLE("chromedriverExecutable"),
        AUTO_WEBVIEW_TIMEOUT("autoWebviewTimeout"),
        INTENT_ACTION("intentAction"),
        INTENT_CATEGORY("intentCategory"),
        INTENT_FLAGS("intentFlags"),
        OPTIONAL_INTENT_ARGUMENT("optionalIntentArguments"),
        STOP_APP_ON_RESET("stopAppOnReset"),
        UNICODE_KEYBOARD("unicodeKeyboard"),
        RESET_KEYBOARD("resetKeyboard"),
        NO_SIGN("noSign"),
        IGNORE_UNIMPORTANT_VIEWS("ignoreUnimportantViews");

        private final String capabilityName;

        Android(String capabilityName) {
            this.capabilityName = capabilityName;
        }

        public String getName() {
            return capabilityName;
        }
    }

}