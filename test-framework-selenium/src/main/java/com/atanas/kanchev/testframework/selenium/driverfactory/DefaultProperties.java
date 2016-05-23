package com.atanas.kanchev.testframework.selenium.driverfactory;

/**
 * @author Atanas Ksnchev
 */
public final class DefaultProperties {

    /**
     * If REUSE_BROWSER is true, browser window stays open after running tests. It may be useful for debugging.
     * Can be configured either programmatically or by system property "-Dreuse.browser=true".
     * <p/>
     * Default value: false.
     */
    public static final boolean REUSE_BROWSER = false;

    public static final Browsers DEFAULT_BROWSER = Browsers.FIREFOX;

    public static final String DEFAULT_BROWSER_RES = "1024x768";

    /**
     * The browser window is maximized when started.
     * Can be configured either programmatically or by system property "-Dstart.maximized=true".
     * <p>
     * Default value: true
     */
    public static final boolean START_MAXIMIZED = true;

    public static final long DEFAULT_IMPLICIT_WAIT = 5L;
    public static final long DEFAULT_PAGE_LOAD_TIMEOUT = 30L;


}
