package com.atanas.kanchev.testframework.selenium.driverfactory;

/**
 * @author Atanas Ksnchev
 */
public class DefaultProperties {

    /**
     * If reuseBrowser is true, browser window stays open after running tests. It may be useful for debugging.
     * Can be configured either programmatically or by system property "-Dreuse.browser=true".
     * <p/>
     * Default value: false.
     */
    public static boolean reuseBrowser = false;

    public static Browsers defaultBrowser = Browsers.FIREFOX;

    /**
     * The browser window is maximized when started.
     * Can be configured either programmatically or by system property "-Dstart.maximized=true".
     * <p>
     * Default value: true
     */
    public static boolean startMaximized = true;

    public static final long DEFAULT_IMPLICIT_WAIT = 5000L;
    public static final long DEFAULT_PAGE_LOAD_TIMEOUT = 30000L;

    public static final String DEFAULT_GRID_HUB_URL = "http://10.1.29.8:4444/wd/hub";
}
