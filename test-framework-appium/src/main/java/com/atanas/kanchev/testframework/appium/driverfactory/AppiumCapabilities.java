package com.atanas.kanchev.testframework.appium.driverfactory;

import org.openqa.selenium.chrome.ChromeOptions;

/**
 * @author Atanas Kanchev
 *         Appium capabilites wrappers
 */
public final class AppiumCapabilities {

    /**
     * Appium Server specific capabilities pojo
     */
    public final class AppiumServerCapabilities {

        private int newCommandTimeout;
        private boolean autoLaunch;
        private boolean noReset;
        private boolean fullReset;
        private String orientation;
        private String automationName;
        private String browserName;
        private String language;
        private boolean autoWebview;

        //////////////
        // SETTERS //
        /////////////

        /**
         * How long (in seconds) Appium will wait for a new command from the client
         * before assuming the client quit and ending the session
         *
         * @param newCommandTimeout e.g. 60
         */
        public AppiumServerCapabilities setNewCommandTimeout(int newCommandTimeout) {
            this.newCommandTimeout = newCommandTimeout;
            return this;
        }

        /**
         * Whether to have Appium install and launch the app automatically.
         * Default true
         *
         * @param autoLaunch e.g. true, false
         */
        public AppiumServerCapabilities setAutoLaunch(boolean autoLaunch) {
            this.autoLaunch = autoLaunch;
            return this;
        }

        /**
         * Don’t reset app state before this session.
         * Default false
         *
         * @param noReset e.g.true, false
         */
        public AppiumServerCapabilities setNoReset(boolean noReset) {
            this.noReset = noReset;
            return this;
        }

        /**
         * (Sim/Emu-only) start in a certain orientation
         *
         * @param orientation LANDSCAPE or PORTRAIT
         */
        public AppiumServerCapabilities setOrientation(String orientation) {
            this.orientation = orientation;
            return this;
        }

        /**
         * (iOS) Delete the entire simulator folder.
         * (Android) Reset app state by uninstalling app instead of clearing app data.
         * On Android, this will also remove the app after the session is complete.
         * Default false
         *
         * @param fullReset true, false
         */
        public AppiumServerCapabilities setFullReset(boolean fullReset) {
            this.fullReset = fullReset;
            return this;
        }

        /**
         * Which automation engine to use
         *
         * @param automationName Appium (default) or Selendroid
         */
        public AppiumServerCapabilities setAutomationName(String automationName) {
            this.automationName = automationName;
            return this;
        }

        /**
         * Name of mobile web browser to automate.
         * Should be an empty string if automating an app instead.
         *
         * @param browserName Safari’ for iOS and ‘Chrome’, ‘Chromium’, or ‘Browser’ for Android
         */
        public AppiumServerCapabilities setBrowserName(String browserName) {
            this.browserName = browserName;
            System.setProperty("browser", browserName);
            return this;
        }

        /**
         * (Sim/Emu-only) Language to set for the simulator / emulator
         *
         * @param language e.g. fr
         */
        public AppiumServerCapabilities setLanguage(String language) {
            this.language = language;
            return this;
        }

        /**
         * Move directly into Webview context.
         * Default false
         *
         * @param autoWebview true, false
         */
        public AppiumServerCapabilities setAutoWebview(boolean autoWebview) {
            this.autoWebview = autoWebview;
            return this;
        }

        //////////////
        // GETTERS //
        /////////////

        int getNewCommandTimeout() {
            return newCommandTimeout;
        }

        boolean isAutoLaunch() {
            return autoLaunch;
        }

        boolean isNoReset() {
            return noReset;
        }

        String getOrientation() {
            return orientation;
        }

        boolean isFullReset() {
            return fullReset;
        }

        String getAutomationName() {
            return automationName;
        }

        String getBrowserName() {
            return browserName;
        }

        String getLanguage() {
            return language;
        }

        boolean isAutoWebview() {
            return autoWebview;
        }
    }

    /**
     * iOS Driver specific capabilities pojo
     */
    public final class IOSCapabilities {

        private String calendarFormat;
        private String bundleId;
        private int launchTimeout;
        private boolean locationServicesEnabled;
        private boolean locationServicesAuthorized;
        private boolean autoAcceptAlerts;
        private boolean autoDismissAlerts;
        private boolean nativeInstrumentsLib;
        private boolean nativeWebTap;
        private String safariInitialUrl;
        private boolean safariAllowPopups;
        private boolean safariIgnoreFraudWarning;
        private boolean safariOpenLinksInBackground;
        private boolean keepKeyChains;
        private String localizableStringsDir;
        private String processArguments;
        private int interKeyDelay;
        private boolean showIOSLog;
        private String sendKeyStrategy;
        private int screenshotWaitTimeout;
        private String waitForAppScript;

        //////////////
        // SETTERS //
        /////////////

        /**
         * (Sim-only) Calendar format to set for the iOS Simulator
         *
         * @param calendarFormat e.g. gregorian
         */
        public IOSCapabilities setCalendarFormat(String calendarFormat) {
            this.calendarFormat = calendarFormat;
            return this;
        }

        /**
         * Bundle ID of the app under test. Useful for starting an app on a real device or for using other caps
         * which require the bundle ID during test startup. To run a test on a real device using the bundle ID,
         * you may omit the ‘app’ capability, but you must provide ‘udid’.
         *
         * @param bundleId e.g. io.appium.TestApp
         */
        public IOSCapabilities setBundleId(String bundleId) {
            this.bundleId = bundleId;
            return this;
        }

        /**
         * Amount of time in ms to wait for instruments before assuming it hung and failing the session
         *
         * @param launchTimeout e.g. 20000
         */
        public IOSCapabilities setLaunchTimeout(int launchTimeout) {
            this.launchTimeout = launchTimeout;
            return this;
        }

        /**
         * (Sim-only) Force location services to be either on or off.
         * Default is to keep current sim setting.
         *
         * @param locationServicesEnabled true or false
         */
        public IOSCapabilities setLocationServicesEnabled(boolean locationServicesEnabled) {
            this.locationServicesEnabled = locationServicesEnabled;
            return this;
        }

        /**
         * (Sim-only) Set location services to be authorized or not authorized for app via plist, so that location
         * services alert doesn’t pop up. Default is to keep current sim setting. Note that if you use this setting you MUST
         * also use the bundleId capability to send in your app’s bundle ID.
         *
         * @param locationServicesAuthorized true or false
         */
        public IOSCapabilities setLocationServicesAuthorized(boolean locationServicesAuthorized) {
            this.locationServicesAuthorized = locationServicesAuthorized;
            return this;
        }

        /**
         * Accept iOS privacy access permission alerts (e.g., location, contacts, photos) automatically if they pop up.
         * Default is false.
         *
         * @param autoAcceptAlerts true or false
         */
        public IOSCapabilities setAutoAcceptAlerts(boolean autoAcceptAlerts) {
            this.autoAcceptAlerts = autoAcceptAlerts;
            return this;
        }

        /**
         * Dismiss iOS privacy access permission alerts (e.g., location, contacts, photos) automatically if they pop up.
         * Default is false.
         *
         * @param autoDismissAlerts true or false
         */
        public IOSCapabilities setAutoDismissAlerts(boolean autoDismissAlerts) {
            this.autoDismissAlerts = autoDismissAlerts;
            return this;
        }

        /**
         * Use native instruments lib (ie disable instruments-without-delay).
         *
         * @param nativeInstrumentsLib true or false
         */
        public IOSCapabilities setNativeInstrumentsLib(boolean nativeInstrumentsLib) {
            this.nativeInstrumentsLib = nativeInstrumentsLib;
            return this;
        }

        /**
         * (Sim-only) Enable “real”, non-javascript-based web taps in Safari.
         * Default: false.
         * Warning: depending on viewport size/ratio this might not accurately tap an element
         *
         * @param nativeWebTap true or false
         */
        public IOSCapabilities setNativeWebTap(boolean nativeWebTap) {
            this.nativeWebTap = nativeWebTap;
            return this;
        }

        /**
         * (Sim-only) (>= 8.1) Initial safari url,
         * default is a local welcome page
         *
         * @param safariInitialUrl e.g. https://www.github.com
         */
        public IOSCapabilities setSafariInitialUrl(String safariInitialUrl) {
            this.safariInitialUrl = safariInitialUrl;
            return this;
        }

        /**
         * (Sim-only) Allow javascript to open new windows in Safari.
         * Default keeps current sim setting
         *
         * @param safariAllowPopups true or false
         */
        public IOSCapabilities setSafariAllowPopups(boolean safariAllowPopups) {
            this.safariAllowPopups = safariAllowPopups;
            return this;
        }

        /**
         * (Sim-only) Prevent Safari from showing a fraudulent website warning.
         * Default keeps current sim setting.
         *
         * @param safariIgnoreFraudWarning true or false
         */
        public IOSCapabilities setSafariIgnoreFraudWarning(boolean safariIgnoreFraudWarning) {
            this.safariIgnoreFraudWarning = safariIgnoreFraudWarning;
            return this;
        }

        /**
         * (Sim-only) Whether Safari should allow links to open in new windows.
         * Default keeps current sim setting.
         *
         * @param safariOpenLinksInBackground true or false
         */
        public IOSCapabilities setSafariOpenLinksInBackground(boolean safariOpenLinksInBackground) {
            this.safariOpenLinksInBackground = safariOpenLinksInBackground;
            return this;
        }

        /**
         * (Sim-only) Whether to keep keychains (Library/Keychains) when appium session is started/finished
         *
         * @param keepKeyChains true or false
         */
        public IOSCapabilities setKeepKeyChains(boolean keepKeyChains) {
            this.keepKeyChains = keepKeyChains;
            return this;
        }

        /**
         * Where to look for localizable strings.
         * Default en.lproj
         *
         * @param localizableStringsDir e.g. en.lproj
         */
        public IOSCapabilities setLocalizableStringsDir(String localizableStringsDir) {
            this.localizableStringsDir = localizableStringsDir;
            return this;
        }

        /**
         * Arguments to pass to the AUT using instruments
         *
         * @param processArguments e.g., -myflag
         */
        public IOSCapabilities setProcessArguments(String processArguments) {
            this.processArguments = processArguments;
            return this;
        }

        /**
         * The delay, in ms, between keystrokes sent to an element when typing.
         *
         * @param interKeyDelay e.g., 100
         */
        public IOSCapabilities setInterKeyDelay(int interKeyDelay) {
            this.interKeyDelay = interKeyDelay;
            return this;
        }

        /**
         * Whether to show any logs captured from a device in the appium logs.
         * Default false
         *
         * @param showIOSLog true or false
         */
        public IOSCapabilities setShowIOSLog(boolean showIOSLog) {
            this.showIOSLog = showIOSLog;
            return this;
        }

        /**
         * Strategy to use to type test into a test field. Simulator default: oneByOne.
         *
         * @param sendKeyStrategy Real device default: grouped	oneByOne, grouped or setValue
         */
        public IOSCapabilities setSendKeyStrategy(String sendKeyStrategy) {
            this.sendKeyStrategy = sendKeyStrategy;
            return this;
        }

        /**
         * Max timeout in sec to wait for a screenshot to be generated.
         * default: 10
         *
         * @param screenshotWaitTimeout e.g. 5
         */
        public IOSCapabilities setScreenshotWaitTimeout(int screenshotWaitTimeout) {
            this.screenshotWaitTimeout = screenshotWaitTimeout;
            return this;
        }

        /**
         * The ios automation script used to determined if the app has been launched,
         * by default the system wait for the page source not to be empty. The result must be a boolean
         *
         * @param waitForAppScript e.g. true;, target.elements().length > 0;, $.delay(5000); true;
         */
        public IOSCapabilities setWaitForAppScript(String waitForAppScript) {
            this.waitForAppScript = waitForAppScript;
            return this;
        }

        //////////////
        // GETTERS //
        /////////////

        String getCalendarFormat() {
            return calendarFormat;
        }

        String getBundleId() {
            return bundleId;
        }

        int getLaunchTimeout() {
            return launchTimeout;
        }

        boolean isLocationServicesEnabled() {
            return locationServicesEnabled;
        }

        boolean isLocationServicesAuthorized() {
            return locationServicesAuthorized;
        }

        boolean isAutoAcceptAlerts() {
            return autoAcceptAlerts;
        }

        boolean isAutoDismissAlerts() {
            return autoDismissAlerts;
        }

        boolean isNativeInstrumentsLib() {
            return nativeInstrumentsLib;
        }

        boolean isNativeWebTap() {
            return nativeWebTap;
        }

        String getSafariInitialUrl() {
            return safariInitialUrl;
        }

        boolean isSafariAllowPopups() {
            return safariAllowPopups;
        }

        boolean isSafariIgnoreFraudWarning() {
            return safariIgnoreFraudWarning;
        }

        boolean isSafariOpenLinksInBackground() {
            return safariOpenLinksInBackground;
        }

        boolean isKeepKeyChains() {
            return keepKeyChains;
        }

        String getLocalizableStringsDir() {
            return localizableStringsDir;
        }

        String getProcessArguments() {
            return processArguments;
        }

        int getInterKeyDelay() {
            return interKeyDelay;
        }

        boolean isShowIOSLog() {
            return showIOSLog;
        }

        String getSendKeyStrategy() {
            return sendKeyStrategy;
        }

        int getScreenshotWaitTimeout() {
            return screenshotWaitTimeout;
        }

        String getWaitForAppScript() {
            return waitForAppScript;
        }

    }

    /**
     * Android Driver specific capabilities pojo
     */
    public final class AndroidCapabilities {

        private String appActivity;
        private String appPackage;
        private String appWaitActivity;
        private String appWaitPackage;
        private int deviceReadyTimeout;
        private String androidCoverage;
        private boolean enablePerformanceLogging;
        private int androidDeviceReadyTimeout;
        private String androidDeviceSocket;
        private String avd;
        private int avdLaunchTimeout;
        private int avdReadyTimeout;
        private String avdArgs;
        private boolean useKeystore;
        private String keystorePath;
        private String keystorePassword;
        private String keyAlias;
        private String keyPassword;
        private String chromedriverExecutable;
        private int autoWebviewTimeout;
        private String intentAction;
        private String intentCategory;
        private String intentFlags;
        private String optionalIntentArguments;
        private boolean stopAppOnReset;
        private boolean unicodeKeyboard;
        private boolean resetKeyboard;
        private boolean noSign;
        private boolean ignoreUnimportantViews;
        private ChromeOptions chromeOptions;

        //////////////
        // SETTERS //
        /////////////

        /**
         * Activity name for the Android activity you want to launch from your package.
         * This often needs to be preceded by a . (e.g., .MainActivity instead of MainActivity)
         *
         * @param appActivity MainActivity, .Settings
         */
        public AndroidCapabilities setAppActivity(String appActivity) {
            this.appActivity = appActivity;
            return this;
        }

        /**
         * Java package of the Android app you want to run
         *
         * @param appPackage com.example.android.myApp, com.android.settings
         */
        public AndroidCapabilities setAppPackage(String appPackage) {
            this.appPackage = appPackage;
            return this;
        }

        /**
         * Activity name for the Android activity you want to wait for	SplashActivity
         *
         * @param appWaitActivity int
         */
        public AndroidCapabilities setAppWaitActivity(String appWaitActivity) {
            this.appWaitActivity = appWaitActivity;
            return this;
        }

        /**
         * Java package of the Android app you want to wait for
         *
         * @param appWaitPackage com.example.android.myApp, com.android.settings
         */
        public AndroidCapabilities setAppWaitPackage(String appWaitPackage) {
            this.appWaitPackage = appWaitPackage;
            return this;
        }

        /**
         * Timeout in seconds while waiting for device to become ready
         *
         * @param deviceReadyTimeout int
         */
        public AndroidCapabilities setDeviceReadyTimeout(int deviceReadyTimeout) {
            this.deviceReadyTimeout = deviceReadyTimeout;
            return this;
        }

        /**
         * Fully qualified instrumentation class. Passed to -w in adb shell am instrument -e coverage true -w
         *
         * @param androidCoverage com.my.Pkg/com.my.Pkg.instrumentation.MyInstrumentation
         */
        public AndroidCapabilities setAndroidCoverage(String androidCoverage) {
            this.androidCoverage = androidCoverage;
            return this;
        }

        /**
         * (Chrome and webview only) Enable Chromedriver’s performance logging (default false)
         *
         * @param enablePerformanceLogging true, false
         */
        public AndroidCapabilities setEnablePerformanceLogging(boolean enablePerformanceLogging) {
            this.enablePerformanceLogging = enablePerformanceLogging;
            return this;
        }

        /**
         * Timeout in seconds used to wait for a device to become ready after booting
         *
         * @param androidDeviceReadyTimeout e.g., 30
         */
        public AndroidCapabilities setAndroidDeviceReadyTimeout(int androidDeviceReadyTimeout) {
            this.androidDeviceReadyTimeout = androidDeviceReadyTimeout;
            return this;
        }

        /**
         * Devtools socket name. Needed only when tested app is a Chromium embedding browser.
         * The socket is open by the browser and Chromedriver connects to it as a devtools client.
         *
         * @param androidDeviceSocket e.g., chrome_devtools_remote
         */
        public AndroidCapabilities setAndroidDeviceSocket(String androidDeviceSocket) {
            this.androidDeviceSocket = androidDeviceSocket;
            return this;
        }

        /**
         * Name of avd to launch
         *
         * @param avd e.g., api19
         */
        public AndroidCapabilities setAvd(String avd) {
            this.avd = avd;
            return this;
        }

        /**
         * How long to wait in milliseconds for an avd to launch and connect to ADB (default 120000)
         *
         * @param avdLaunchTimeout e.g. 300000
         */
        public AndroidCapabilities setAvdLaunchTimeout(int avdLaunchTimeout) {
            this.avdLaunchTimeout = avdLaunchTimeout;
            return this;
        }

        /**
         * How    long to wait in milliseconds for an avd to finish its boot animations (default 120000)
         *
         * @param avdReadyTimeout e.g. 300000
         */
        public AndroidCapabilities setAvdReadyTimeout(int avdReadyTimeout) {
            this.avdReadyTimeout = avdReadyTimeout;
            return this;
        }

        /**
         * Additional emulator arguments used when launching an avd
         *
         * @param avdArgs e.g., -netfast
         */
        public AndroidCapabilities setAvdArgs(String avdArgs) {
            this.avdArgs = avdArgs;
            return this;
        }

        /**
         * Use a custom keystore to sign apks, default false
         *
         * @param useKeystore true or false
         */
        public AndroidCapabilities setUseKeystore(boolean useKeystore) {
            this.useKeystore = useKeystore;
            return this;
        }

        /**
         * Path to custom keystore, default ~/.android/debug.keystore
         *
         * @param keystorePath e.g., /path/to.keystore
         */
        public AndroidCapabilities setKeystorePath(String keystorePath) {
            this.keystorePath = keystorePath;
            return this;
        }

        /**
         * Password for custom keystore
         *
         * @param keystorePassword e.g., foo
         */
        public AndroidCapabilities setKeystorePassword(String keystorePassword) {
            this.keystorePassword = keystorePassword;
            return this;
        }

        /**
         * Alias for key
         *
         * @param keyAlias e.g., androiddebugkey
         */
        public AndroidCapabilities setKeyAlias(String keyAlias) {
            this.keyAlias = keyAlias;
            return this;
        }

        /**
         * Password for key
         *
         * @param keyPassword e.g., foo
         */
        public AndroidCapabilities setKeyPassword(String keyPassword) {
            this.keyPassword = keyPassword;
            return this;
        }

        /**
         * The absolute local path to webdriver executable
         * (if Chromium embedder provides its own webdriver, it should be used instead of original chromedriver bundled with Appium)
         *
         * @param chromedriverExecutable e.g. /abs/path/to/webdriver
         */
        public AndroidCapabilities setChromedriverExecutable(String chromedriverExecutable) {
            this.chromedriverExecutable = chromedriverExecutable;
            return this;
        }

        /**
         * Amount of time to wait for Webview context to become active, in ms. Defaults to 2000
         *
         * @param autoWebviewTimeout e.g. 4
         */
        public AndroidCapabilities setAutoWebviewTimeout(int autoWebviewTimeout) {
            this.autoWebviewTimeout = autoWebviewTimeout;
            return this;
        }

        /**
         * Intent action which will be used to start activity (default android.intent.action.MAIN)
         *
         * @param intentAction e.g.android.intent.action.MAIN, android.intent.action.VIEW
         */
        public AndroidCapabilities setIntentAction(String intentAction) {
            this.intentAction = intentAction;
            return this;
        }

        /**
         * Intent category which will be used to start activity (default android.intent.category.LAUNCHER)
         *
         * @param intentCategory e.g. android.intent.category.LAUNCHER, android.intent.category.APP_CONTACTS
         */
        public AndroidCapabilities setIntentCategory(String intentCategory) {
            this.intentCategory = intentCategory;
            return this;
        }

        /**
         * Flags that will be used to start activity (default 0x10200000)
         *
         * @param intentFlags e.g. 0x10200000
         */
        public AndroidCapabilities setIntentFlags(String intentFlags) {
            this.intentFlags = intentFlags;
            return this;
        }

        /**
         * Additional intent arguments that will be used to start activity. See Intent arguments
         *
         * @param optionalIntentArguments e.g. --esn <EXTRA_KEY>, --ez <EXTRA_KEY> <EXTRA_BOOLEAN_VALUE>, etc.
         */
        public AndroidCapabilities setOptionalIntentArguments(String optionalIntentArguments) {
            this.optionalIntentArguments = optionalIntentArguments;
            return this;
        }

        /**
         * Stops the process of the app under test, before starting the app using adb.
         * If the app under test is created by another anchor app, setting this false,
         * allows the process of the anchor app to be still alive, during the start of the test app using adb, default true
         *
         * @param stopAppOnReset true or false
         */
        public AndroidCapabilities setStopAppOnReset(boolean stopAppOnReset) {
            this.stopAppOnReset = stopAppOnReset;
            return this;
        }

        /**
         * Enable Unicode input, default false
         *
         * @param unicodeKeyboard true or false
         */
        public AndroidCapabilities setUnicodeKeyboard(boolean unicodeKeyboard) {
            this.unicodeKeyboard = unicodeKeyboard;
            return this;
        }

        /**
         * Reset keyboard to its original state, after running Unicode tests with unicodeKeyboard capability. Ignored if used alone. Default false
         *
         * @param resetKeyboard true or false
         */
        public AndroidCapabilities setResetKeyboard(boolean resetKeyboard) {
            this.resetKeyboard = resetKeyboard;
            return this;
        }

        /**
         * Skip checking and signing of app with debug keys, will work only with UiAutomator and not with selendroid, default false
         *
         * @param noSign true or false
         */
        public AndroidCapabilities setNoSign(boolean noSign) {
            this.noSign = noSign;
            return this;
        }

        /**
         * Calls the setCompressedLayoutHierarchy() uiautomator function.
         * This capability can speed up test execution, since Accessibility commands will run faster ignoring some elements.
         * The ignored elements will not be findable, which is why this capability has also been implemented as a toggle-able setting as well as a capability.
         * Defaults to false
         *
         * @param ignoreUnimportantViews true or false
         */
        public AndroidCapabilities setIgnoreUnimportantViews(boolean ignoreUnimportantViews) {
            this.ignoreUnimportantViews = ignoreUnimportantViews;
            return this;
        }

        /**
         * Set custom ChromeOptions
         * @param options ChromeOptions instance
         */
        public AndroidCapabilities setChromeOptions(ChromeOptions options) {
            this.chromeOptions = options;
            return this;
        }

        //////////////
        // GETTERS //
        /////////////

        String getAppActivity() {
            return appActivity;
        }

        String getAppPackage() {
            return appPackage;
        }

        String getAppWaitActivity() {
            return appWaitActivity;
        }

        String getAppWaitPackage() {
            return appWaitPackage;
        }

        int getDeviceReadyTimeout() {
            return deviceReadyTimeout;
        }

        String getAndroidCoverage() {
            return androidCoverage;
        }

        boolean isEnablePerformanceLogging() {
            return enablePerformanceLogging;
        }

        int getAndroidDeviceReadyTimeout() {
            return androidDeviceReadyTimeout;
        }

        String getAndroidDeviceSocket() {
            return androidDeviceSocket;
        }

        String getAvd() {
            return avd;
        }

        int getAvdLaunchTimeout() {
            return avdLaunchTimeout;
        }

        int getAvdReadyTimeout() {
            return avdReadyTimeout;
        }

        String getAvdArgs() {
            return avdArgs;
        }

        boolean isUseKeystore() {
            return useKeystore;
        }

        String getKeystorePath() {
            return keystorePath;
        }

        String getKeystorePassword() {
            return keystorePassword;
        }

        String getKeyAlias() {
            return keyAlias;
        }

        String getKeyPassword() {
            return keyPassword;
        }

        String getChromedriverExecutable() {
            return chromedriverExecutable;
        }

        int getAutoWebviewTimeout() {
            return autoWebviewTimeout;
        }

        String getIntentAction() {
            return intentAction;
        }

        String getIntentCategory() {
            return intentCategory;
        }

        String getIntentFlags() {
            return intentFlags;
        }

        String getOptionalIntentArguments() {
            return optionalIntentArguments;
        }

        boolean isStopAppOnReset() {
            return stopAppOnReset;
        }

        boolean isUnicodeKeyboard() {
            return unicodeKeyboard;
        }

        boolean isResetKeyboard() {
            return resetKeyboard;
        }

        boolean isNoSign() {
            return noSign;
        }

        boolean isIgnoreUnimportantViews() {
            return ignoreUnimportantViews;
        }

        ChromeOptions getChromeOptions() {
            return chromeOptions;
        }
    }
}