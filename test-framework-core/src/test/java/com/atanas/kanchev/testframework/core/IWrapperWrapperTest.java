package com.atanas.kanchev.testframework.core;

import com.atanas.kanchev.testframework.core.handlers.wrappers.IWrapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by atanas on 24/03/2016.
 */
@RunWith(Enclosed.class)
public class IWrapperWrapperTest {

    private static String url = "https://www.google.co.uk/";
    private static final Logger logger = LoggerFactory.getLogger(IWrapperWrapperTest.class);

    public static class SetupBrowserTest implements IWrapper {

        @Before
        public void setUp() throws Exception {
            setupSelenium()
                    .setBrowser("chrome")
                    .setStartMaximized(true)
//                    .setReuseBrowser(true)
                    .setCustomCapabilities(DesiredCapabilities.ipad());

        }

        @After
        public void tearDown() throws Exception {
            context().tearDownContexts();
        }

        @Test
        public void setupBrowserTest() throws Exception {
            goTo(url);
        }


        @Test
        public void name() throws Exception {
            goTo("https://bbc.co.uk");

        }
//    @Caps
//    public void goToURLTest() throws Exception {
//        goTo(url);
//    }
//
//    @Caps
//    public void refreshTest() throws Exception {
//        goTo(url).
//                refresh();
//    }
//
//    @Caps
//    public void methodChainingTest() throws Exception {
//        goTo(url).refresh();
//    }
//
//    @Caps
//    public void loc() throws Exception {
//        goTo(url).findElement().elementBy(Locator.XPATH, "//*[@id=\"tsf\"]/div[2]/div[3]/center/input[1]");
//    }
//
//    @Caps
//    public void locs() throws Exception {
//        goTo(url).findElement().elementBy(Locator.TAG_NAME, "tr");
//    }
//
//    @Caps
//    public void multiple() throws Exception {
//        goTo(url);
//        goTo("HTTPS://BBC.CO.UK");
//    }
//
//
//    @Caps
//    public void findElementByNameTest() throws Exception {
//        goTo(url).
//                findElement().elementBy(Locator.XPATH, "w");
//    }
//
//    @Caps
//    public void waitingTest() throws Exception {
//
//        goTo(url)
//                .findElement().elementBy(Locator.NAME, "x");
//        waitFor(5L)
//                .isElementPresent(Locator.NAME, "lst-ib");
//
//    }
//
//    @Caps
//    public void probeEl() throws Exception {
//
//        Assert.assertTrue(
//                goTo(url)
//                        .probe(Locator.XPATH, "//*[@id=\"tsf\"]/div[2]/div[3]/center/input[1]")
//                        .exist());
//
//    }
//
//    @Caps
//    public void driver() throws Exception {
//        DriverFactory driverFactory = setupSelenium().configureBrowser();
//        //driverFactory.setSelectedBrowser(BrowserConfig.CHROME);
//        setupSelenium().configureContext(driverFactory);
//        goTo(url);
//    }
//
//    @Caps
//    public void prop() throws Exception {
////        System.setProperty("env", "dev");
////        System.out.println(new PropertyReader().getValidProperty("url"));
//       // System.out.println(new PropertyReader("dev.env.properties").getProperty("url"));
//        System.out.println(new BigInteger(130, new SecureRandom()).toString(32));
//
//    }
//
//    @Caps
//    public void APPIUM_INIT() throws Exception {
//
//    }
//
//    @Caps
//    public void conf() throws Exception {
//
//
//    }
    }


}