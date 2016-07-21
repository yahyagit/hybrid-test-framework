package com.atanas.kanchev.testframework.appium.driverfactory;

import com.atanas.kanchev.testframework.commons.exceptions.CustomExceptions;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Atanas Kanchev
 *         Appium Capabilities Factory
 */
@Deprecated
public final class AppiumCapabilitiesFactory {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private DesiredCapabilities caps;
    private AppiumCapabilities.AppiumServerCapabilities asc;
    private AppiumCapabilities.IOSCapabilities iosCaps;
    private AppiumCapabilities.AndroidCapabilities androidCaps;

    ///////////////////
    // CONSTRUCTORS //
    //////////////////

    /**
     * Constructor
     * Set an iOS device capabilities
     *
     * @param device  AppiumDevice
     * @param iosCaps AppiumCapabilities.IOSCapabilities
     * @param asc     AppiumCapabilities.AppiumServerCapabilities
     */
    public AppiumCapabilitiesFactory(final AppiumDevice device, final AppiumCapabilities.IOSCapabilities iosCaps,
                                     final AppiumCapabilities.AppiumServerCapabilities asc) {

        if (iosCaps == null)
            throw new CustomExceptions.Common.NullArgumentException("Null AppiumCapabilities.IOSCapabilities iosCaps");
        this.iosCaps = iosCaps;

        if (asc != null)
            this.asc = asc;

        if (device == null)
            throw new CustomExceptions.Common.NullArgumentException("Null AppiumDevice device");
        else
            switch (device.getDeviceType()) {

                case IPAD_DEVICE:
                    caps = DesiredCapabilities.ipad();
                    setupAppiumServerCapabilities();
                    setupIOSDeviceCapabilities(device);
                    setupIOSCapabilities();
                    break;

                case IPAD_SIMULATOR:
                    caps = DesiredCapabilities.iphone();
                    setupAppiumServerCapabilities();
                    setupIOSDeviceCapabilities(device);
                    setupIOSCapabilities();
                    break;

                case IPHONE_DEVICE:
                    caps = DesiredCapabilities.iphone();
                    setupAppiumServerCapabilities();
                    setupIOSDeviceCapabilities(device);
                    setupIOSCapabilities();
                    break;
                case IPHONE_SIMULATOR:
                    caps = DesiredCapabilities.iphone();
                    setupAppiumServerCapabilities();
                    setupIOSDeviceCapabilities(device);
                    setupIOSCapabilities();
                    break;

                default:
                    throw new RuntimeException("Unknown Apple Device: " + device);
            }

    }

    /**
     * Constructor
     * Set an Android device capabilities
     *
     * @param device      AppiumDevice
     * @param androidCaps AppiumCapabilities.AndroidCapabilities
     * @param asc         AppiumCapabilities.AppiumServerCapabilities
     */
    public AppiumCapabilitiesFactory(final AppiumDevice device, final AppiumCapabilities.AndroidCapabilities androidCaps,
                                     final AppiumCapabilities.AppiumServerCapabilities asc) {

        if (androidCaps == null)
            throw new CustomExceptions.Common.NullArgumentException("Null AppiumCapabilities.AndroidCapabilities androidCaps");
        else
            this.androidCaps = androidCaps;

        if (asc != null)
            this.asc = asc;

        if (device == null)
            throw new CustomExceptions.Common.NullArgumentException("Null AppiumDevice device");
        else
            switch (device.getDeviceType()) {

                case ANDROID_DEVICE:
                    caps = new DesiredCapabilities();
                    setupAppiumServerCapabilities();
                    setupAndroidDeviceCapabilities(device);
                    setupAndroidCapabilities();
                    break;

                case ANDROID_EMULATOR:
                    caps = new DesiredCapabilities();
                    setupAppiumServerCapabilities();
                    setupAndroidDeviceCapabilities(device);
                    setupAndroidCapabilities();
                    break;

                default:
                    throw new RuntimeException("Unknown Android Device: " + device);
            }
    }

    /**
     * Setup Appium server capabilities
     */
    private void setupAppiumServerCapabilities() {

        if (asc != null) {

            if (asc.getNewCommandTimeout() != 0)
                setCapability(AppiumCapabilitiesEnums.AppiumServer.NEW_COMMAND_TIMEOUT.getName(), asc.getNewCommandTimeout());
            if (asc.isAutoLaunch())
                setCapability(AppiumCapabilitiesEnums.AppiumServer.AUTO_LAUNCH.getName(), asc.isAutoLaunch());
            if (asc.isNoReset())
                setCapability(AppiumCapabilitiesEnums.AppiumServer.NO_RESET.getName(), asc.isNoReset());
            if (asc.getOrientation() != null)
                setCapability(AppiumCapabilitiesEnums.AppiumServer.ORIENTATION.getName(), asc.getOrientation());
            if (asc.isFullReset())
                setCapability(AppiumCapabilitiesEnums.AppiumServer.FULL_RESET.getName(), asc.isFullReset());
            if (asc.getAutomationName() != null)
                setCapability(AppiumCapabilitiesEnums.AppiumServer.AUTOMATION_NAME.getName(), asc.getAutomationName());
            if (asc.getBrowserName() != null)
                setCapability(AppiumCapabilitiesEnums.AppiumServer.BROWSER_NAME.getName(), asc.getBrowserName());
            if (asc.getLanguage() != null)
                setCapability(AppiumCapabilitiesEnums.AppiumServer.LANGUAGE.getName(), asc.getLanguage());
            if (asc.isAutoWebview())
                setCapability(AppiumCapabilitiesEnums.AppiumServer.AUTO_WEB_VIEW.getName(), asc.isAutoWebview());
        }

    }

    /**
     * Setup iOS device capabilities
     *
     * @param device AppiumDevice
     */
    private void setupIOSDeviceCapabilities(AppiumDevice device) {

        if (device.getPlatformName() != null)
            setCapability(AppiumCapabilitiesEnums.AppiumServer.PLATFORM_NAME.getName(), device.getPlatformName());
        if (device.getDeviceName() != null)
            setCapability(AppiumCapabilitiesEnums.AppiumServer.DEVICE_NAME.getName(), device.getDeviceName());
        if (device.getPlatformName() != null)
            setCapability(AppiumCapabilitiesEnums.AppiumServer.PLATFORM_VERSION.getName(), device.getPlatformName());
        if (device.getApp() != null)
            setCapability(AppiumCapabilitiesEnums.AppiumServer.APP.getName(), device.getApp());
        if (device.getDeviceUDID() != null)
            setCapability(AppiumCapabilitiesEnums.AppiumServer.UDID.getName(), device.getDeviceUDID());
        if (iosCaps.isAutoAcceptAlerts())
            setCapability(AppiumCapabilitiesEnums.IOS.AUTO_ACCEPT_ALERTS.getName(), iosCaps.isAutoAcceptAlerts());
        if (iosCaps.getBundleId() != null)
            setCapability(AppiumCapabilitiesEnums.IOS.BUNDLE_ID.getName(), iosCaps.getBundleId());
    }

    /**
     * Setup Android device capabilities
     *
     * @param device AppiumDevice
     */
    private void setupAndroidDeviceCapabilities(AppiumDevice device) {

        if (device.getPlatformName() != null)
            setCapability(AppiumCapabilitiesEnums.AppiumServer.PLATFORM_NAME.getName(), device.getPlatformName());
        if (device.getDeviceName() != null)
            setCapability(AppiumCapabilitiesEnums.AppiumServer.DEVICE_NAME.getName(), device.getDeviceName());
        if (device.getPlatformName() != null)
            setCapability(AppiumCapabilitiesEnums.AppiumServer.PLATFORM_VERSION.getName(), device.getPlatformName());
        if (device.getApp() != null)
            setCapability(AppiumCapabilitiesEnums.AppiumServer.APP.getName(), device.getApp());
        if (device.getDeviceUDID() != null)
            setCapability(AppiumCapabilitiesEnums.AppiumServer.UDID.getName(), device.getDeviceUDID());

    }

    /**
     * Setup other iOS capabilities
     */
    private void setupIOSCapabilities() {

        if (iosCaps.isAutoAcceptAlerts())
            setCapability(AppiumCapabilitiesEnums.IOS.AUTO_ACCEPT_ALERTS.getName(), iosCaps.isAutoAcceptAlerts());
        if (iosCaps.isAutoDismissAlerts())
            setCapability(AppiumCapabilitiesEnums.IOS.AUTO_DISMISS_ALERTS.getName(), iosCaps.isAutoDismissAlerts());
        if (iosCaps.getCalendarFormat() != null)
            setCapability(AppiumCapabilitiesEnums.IOS.CALENDAR_FORMAT.getName(), iosCaps.getCalendarFormat());
        if (iosCaps.getLaunchTimeout() != 0)
            setCapability(AppiumCapabilitiesEnums.IOS.LAUNCH_TIMEOUT.getName(), iosCaps.getLaunchTimeout());
        if (iosCaps.isLocationServicesEnabled())
            setCapability(AppiumCapabilitiesEnums.IOS.LOCATION_SERVICES_ENABLED.getName(), iosCaps.isLocationServicesEnabled());
        if (iosCaps.isLocationServicesAuthorized())
            setCapability(AppiumCapabilitiesEnums.IOS.LOCATION_SERVICES_AUTORISED.getName(), iosCaps.isLocationServicesAuthorized());
        if (iosCaps.isNativeInstrumentsLib())
            setCapability(AppiumCapabilitiesEnums.IOS.NATIVE_INSTRUMENTS_LIB.getName(), iosCaps.isNativeInstrumentsLib());
        if (iosCaps.isNativeWebTap())
            setCapability(AppiumCapabilitiesEnums.IOS.NATIVE_WEB_TAP.getName(), iosCaps.isNativeWebTap());
        if (iosCaps.getSafariInitialUrl() != null)
            setCapability(AppiumCapabilitiesEnums.IOS.SAFARI_INITIAL_URL.getName(), iosCaps.getSafariInitialUrl());
        if (iosCaps.isSafariAllowPopups())
            setCapability(AppiumCapabilitiesEnums.IOS.SAFARI_ALLOW_POPUPS.getName(), iosCaps.isSafariAllowPopups());
        if (iosCaps.isSafariIgnoreFraudWarning())
            setCapability(AppiumCapabilitiesEnums.IOS.SAFARI_IGNORE_FRAUD_WARNINGS.getName(), iosCaps.isSafariIgnoreFraudWarning());
        if (iosCaps.isSafariOpenLinksInBackground())
            setCapability(AppiumCapabilitiesEnums.IOS.SAFARI_OPEN_LINKS_IN_BACKGROUND.getName(), iosCaps.isSafariOpenLinksInBackground());
        if (iosCaps.isKeepKeyChains())
            setCapability(AppiumCapabilitiesEnums.IOS.KEEP_KEY_CHAINS.getName(), iosCaps.isKeepKeyChains());
        if (iosCaps.getLocalizableStringsDir() != null)
            setCapability(AppiumCapabilitiesEnums.IOS.LOCALIZABLE_STRINGS_DIR.getName(), iosCaps.getLocalizableStringsDir());
        if (iosCaps.getProcessArguments() != null)
            setCapability(AppiumCapabilitiesEnums.IOS.PROCESS_ARGUMENTS.getName(), iosCaps.getProcessArguments());
        if (iosCaps.getInterKeyDelay() != 0)
            setCapability(AppiumCapabilitiesEnums.IOS.INTER_KEY_DELAY.getName(), iosCaps.getInterKeyDelay());
        if (iosCaps.isShowIOSLog())
            setCapability(AppiumCapabilitiesEnums.IOS.SHOW_IOS_LOG.getName(), iosCaps.isShowIOSLog());
        if (iosCaps.getSendKeyStrategy() != null)
            setCapability(AppiumCapabilitiesEnums.IOS.SEND_KEY_STRATEGY.getName(), iosCaps.getSendKeyStrategy());
        if (iosCaps.getScreenshotWaitTimeout() != 0)
            setCapability(AppiumCapabilitiesEnums.IOS.SCREENSHOT_WAIT_TIMEOUT.getName(), iosCaps.getScreenshotWaitTimeout());
        if (iosCaps.getWaitForAppScript() != null)
            setCapability(AppiumCapabilitiesEnums.IOS.WAIT_FOR_APP_SCRIPT.getName(), iosCaps.getWaitForAppScript());
    }

    /**
     * Setup Android specific capabilities
     */
    private void setupAndroidCapabilities() {

        if (androidCaps.getAppActivity() != null)
            setCapability(AppiumCapabilitiesEnums.Android.APP_ACTIVITY.getName(), androidCaps.getAppActivity());
        if (androidCaps.getAppPackage() != null)
            setCapability(AppiumCapabilitiesEnums.Android.APP_PACKAGE.getName(), androidCaps.getAppPackage());
        if (androidCaps.getAppWaitActivity() != null)
            setCapability(AppiumCapabilitiesEnums.Android.APP_WAIT_ACTIVITY.getName(), androidCaps.getAppWaitActivity());
        if (androidCaps.getAppWaitPackage() != null)
            setCapability(AppiumCapabilitiesEnums.Android.APP_WAIT_PACKAGE.getName(), androidCaps.getAppWaitPackage());
        if (androidCaps.getDeviceReadyTimeout() != 0)
            setCapability(AppiumCapabilitiesEnums.Android.DEVICE_READY_TIMEOUT.getName(), androidCaps.getDeviceReadyTimeout());
        if (androidCaps.getAndroidCoverage() != null)
            setCapability(AppiumCapabilitiesEnums.Android.ANDROID_COVERAGE.getName(), androidCaps.getAndroidCoverage());
        if (androidCaps.isEnablePerformanceLogging())
            setCapability(AppiumCapabilitiesEnums.Android.ENABLE_PERFORMANCE_LOGGING.getName(), androidCaps.isEnablePerformanceLogging());
        if (androidCaps.getAndroidDeviceReadyTimeout() != 0)
            setCapability(AppiumCapabilitiesEnums.Android.ANDROID_DEVICE_READY_TIMEOUT.getName(), androidCaps.getAndroidDeviceReadyTimeout());
        if (androidCaps.getAndroidDeviceSocket() != null)
            setCapability(AppiumCapabilitiesEnums.Android.ANDROID_DEVICE_SOCKET.getName(), androidCaps.getAndroidDeviceSocket());
        if (androidCaps.getAvd() != null)
            setCapability(AppiumCapabilitiesEnums.Android.AVD.getName(), androidCaps.getAvd());
        if (androidCaps.getAvdLaunchTimeout() != 0)
            setCapability(AppiumCapabilitiesEnums.Android.AVD_LAUNCH_TIMEOUT.getName(), androidCaps.getAvdLaunchTimeout());
        if (androidCaps.getAvdReadyTimeout() != 0)
            setCapability(AppiumCapabilitiesEnums.Android.AVD_READY_TIMEOUT.getName(), androidCaps.getAvdReadyTimeout());
        if (androidCaps.getAvdArgs() != null)
            setCapability(AppiumCapabilitiesEnums.Android.AVD_ARGS.getName(), androidCaps.getAvdArgs());
        if (androidCaps.isUseKeystore())
            setCapability(AppiumCapabilitiesEnums.Android.USE_KEY_STORE.getName(), androidCaps.isUseKeystore());
        if (androidCaps.getKeystorePath() != null)
            setCapability(AppiumCapabilitiesEnums.Android.KEY_STORE_PATH.getName(), androidCaps.getKeystorePath());
        if (androidCaps.getKeystorePassword() != null)
            setCapability(AppiumCapabilitiesEnums.Android.KEY_STORE_PASSWORD.getName(), androidCaps.getKeystorePassword());
        if (androidCaps.getKeyAlias() != null)
            setCapability(AppiumCapabilitiesEnums.Android.KEY_ALIAS.getName(), androidCaps.getKeyAlias());
        if (androidCaps.getKeyPassword() != null)
            setCapability(AppiumCapabilitiesEnums.Android.KEY_PASSWORD.getName(), androidCaps.getKeyPassword());
        if (androidCaps.getChromedriverExecutable() != null)
            setCapability(AppiumCapabilitiesEnums.Android.CHROME_DRIVER_EXECUTABLE.getName(), androidCaps.getChromedriverExecutable());
        if (androidCaps.getAutoWebviewTimeout() != 0)
            setCapability(AppiumCapabilitiesEnums.Android.AUTO_WEBVIEW_TIMEOUT.getName(), androidCaps.getAutoWebviewTimeout());
        if (androidCaps.getIntentAction() != null)
            setCapability(AppiumCapabilitiesEnums.Android.INTENT_ACTION.getName(), androidCaps.getIntentAction());
        if (androidCaps.getIntentCategory() != null)
            setCapability(AppiumCapabilitiesEnums.Android.INTENT_CATEGORY.getName(), androidCaps.getIntentCategory());
        if (androidCaps.getIntentFlags() != null)
            setCapability(AppiumCapabilitiesEnums.Android.INTENT_FLAGS.getName(), androidCaps.getIntentFlags());
        if (androidCaps.getOptionalIntentArguments() != null)
            setCapability(AppiumCapabilitiesEnums.Android.OPTIONAL_INTENT_ARGUMENT.getName(), androidCaps.getOptionalIntentArguments());
        if (androidCaps.isStopAppOnReset())
            setCapability(AppiumCapabilitiesEnums.Android.STOP_APP_ON_RESET.getName(), androidCaps.isStopAppOnReset());
        if (androidCaps.isUnicodeKeyboard())
            setCapability(AppiumCapabilitiesEnums.Android.UNICODE_KEYBOARD.getName(), androidCaps.isUnicodeKeyboard());
        if (androidCaps.isResetKeyboard())
            setCapability(AppiumCapabilitiesEnums.Android.RESET_KEYBOARD.getName(), androidCaps.isResetKeyboard());
        if (androidCaps.isNoSign())
            setCapability(AppiumCapabilitiesEnums.Android.NO_SIGN.getName(), androidCaps.isNoSign());
        if (androidCaps.isIgnoreUnimportantViews())
            setCapability(AppiumCapabilitiesEnums.Android.IGNORE_UNIMPORTANT_VIEWS.getName(), androidCaps.isIgnoreUnimportantViews());
        if (androidCaps.getChromeOptions() != null)
            caps.setCapability(ChromeOptions.CAPABILITY, androidCaps.getChromeOptions());
    }

    private void setCapability(final String name, Object value) {
        logger.debug("Setting Appium Capability: " + name + " > " + value);
        caps.setCapability(name, value);
    }

    /**
     * Get the generated capabilities
     *
     * @return caps DesiredCapabilities
     */
    public final DesiredCapabilities getGeneratedCaps() {
        return caps;
    }

}
