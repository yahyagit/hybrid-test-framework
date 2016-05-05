package com.atanas.kanchev.testframework.selenium.driverfactory;

/**
 * Created by atanas on 24/03/2016.
 */
public class BrowserConfig extends DesiredCapabilitiesFactory  {

//    final Browsers selectedBrowser;
//
//    public BrowserConfig(Browsers selectedBrowser) {
//        this.selectedBrowser = selectedBrowser;
//    }

    //    FIREFOX {
//        @Override
//        public DesiredCapabilities setRemoteBrowserCaps(final String version, final Platform platform, DesiredCapabilities caps) {
//            caps.setBrowserName("firefox");
//            caps.setVersion(version);
//            return caps;
//        }
//
//        @Override
//        public RemoteWebDriver getRemoteWebDriverObject(final URL remoteAddress, final String version, final Platform platform) {
//            DesiredCapabilitiesFactory desiredCapabilitiesFactory = new DesiredCapabilitiesFactory();
//            DesiredCapabilities defCaps = desiredCapabilitiesFactory.getDefaultFirefoxCaps();
//            DesiredCapabilities capabilities =
//                    desiredCapabilitiesFactory.mergeCapabilities(defCaps, setRemoteBrowserCaps(version, platform, defCaps));
//            return new RemoteWebDriver(remoteAddress, capabilities);
//        }
//
//        @Override
//        public WebDriver getWebDriverObject(DesiredCapabilities desiredCapabilities) {
//            return new WebDriverFactory().getFirefoxDriver(desiredCapabilities);
//        }
//
//        @Override
//        public DesiredCapabilities getDesiredCapabilities(Proxy proxySettings) {
//            DesiredCapabilities caps = getDefaultFirefoxCaps();
//            return addProxySettings(caps, proxySettings);
//        }
//
//        @Override
//        public DesiredCapabilities getDesiredCapabilities() {
//            return getDefaultFirefoxCaps();
//        }
//    },
//
//    CHROME {
//        @Override
//        public DesiredCapabilities setRemoteBrowserCaps(final String version, final Platform platform, final DesiredCapabilities caps) {
//            caps.setBrowserName("chrome");
//            caps.setVersion(version);
//            caps.setPlatform(platform);
//            return caps;
//        }
//
//        @Override
//        public RemoteWebDriver getRemoteWebDriverObject(final URL remoteAddress, final String version, final Platform platform) {
//            DesiredCapabilities capabilities =
//                    mergeCapabilities(getDefaultChromeCaps(), setRemoteBrowserCaps(version, platform, getDefaultChromeCaps()));
//            return new RemoteWebDriver(remoteAddress, capabilities);
//        }
//
//        @Override
//        public WebDriver getWebDriverObject(DesiredCapabilities desiredCapabilities) {
//            return new WebDriverFactory().getChromeDriver(desiredCapabilities);
//        }
//
//        @Override
//        public DesiredCapabilities getDesiredCapabilities(Proxy proxySettings) {
//            DesiredCapabilities caps = getDefaultChromeCaps();
//            return addProxySettings(caps, proxySettings);
//        }
//
//        @Override
//        public DesiredCapabilities getDesiredCapabilities() {
//            return getDefaultChromeCaps();
//        }
//    };

    // the logger
//    private static final Logger logger = LoggerFactory.getLogger(BrowserConfig.class);
//
//
//
//
//    @Override
//    public RemoteWebDriver getRemoteWebDriverObject(String remoteAddress, String version, Platform platform) {
//        return new RemoteWebDriverFactory(remoteAddress).setupRemoteWebDriver(version, platform, selectedBrowser);
//
//    }
//
//    @Override
//    public WebDriver getWebDriverObject(DesiredCapabilities desiredCapabilities) {
//        WebDriverFactory webDriverFactory = new WebDriverFactory();
//
//        return webDriverFactory.getChromeDriver().getDriver();
//    }
//
//    @Override
//    public DesiredCapabilities getDesiredCapabilities(Proxy proxySettings) {
//        return null;
//    }
//
//    @Override
//    public DesiredCapabilities getDesiredCapabilities() {
//        return null;
//    }
}

//interface IWebDriverSetup {
//
//    WebDriver getWebDriverObject(DesiredCapabilities desiredCapabilities);
//
//    DesiredCapabilities getDesiredCapabilities(Proxy proxySettings);
//
//    DesiredCapabilities getDesiredCapabilities();
//}

//interface IRemoteWebDriverSetup {
//
//    /**
//     * Create RemoteWebDriver instance
//     *
//     * @param remoteAddress Hub URL Address
//     * @return RemoteWebDriver instance
//     */
//    RemoteWebDriver getRemoteWebDriverObject(final String remoteAddress, final String version, final Platform platform);
//}