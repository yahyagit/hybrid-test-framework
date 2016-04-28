package com.atanas.kanchev.testframework.selenium.driverfactory;

import com.atanas.kanchev.testframework.selenium.context.WebContext;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;

import static com.atanas.kanchev.testframework.core.context.ContextFactory.getCurrentContext;

/**
 * @author Atanas Kanchev
 *         <p/>
 *         RemoteWebDriver Factory
 */
public final class RemoteWebDriverFactory {

    private static final Logger logger = LoggerFactory.getLogger(RemoteWebDriverFactory.class);

    // Hub URL
    private URL configuredGridHubUrl;
    //    private static final String DEFAULT_GRID_HUB_URL = WebDriverProperties.getSeleniumPropertiesPropFile().getProperty("default.hub.url");
    private static final String DEFAULT_GRID_HUB_URL = "";

    // JVM args
    private final boolean isGridExecution;
    private String browserVersion;
    private String hubUrl;

    /**
     * Default constructor
     */
    public RemoteWebDriverFactory() {

        isGridExecution = Boolean.parseBoolean(System.getProperty(JVMArgsFactory.getIsGridExecution()));
        if (isGridExecution) {
            browserVersion = System.getProperty(JVMArgsFactory.getRemoteBrowserVersion());
            try {
                configuredGridHubUrl = new URL(DEFAULT_GRID_HUB_URL);
            } catch (MalformedURLException e) {
                logger.error(e.getMessage());
            }
        }

    }

    /**
     * Param constructor
     * Instantiates {@link RemoteWebDriverFactory#configuredGridHubUrl}
     *
     * @param URL String
     */
    public RemoteWebDriverFactory(final String URL) {

        this.isGridExecution = Boolean.parseBoolean(System.getProperty(JVMArgsFactory.getIsGridExecution()));

        if (isGridExecution) {
            this.browserVersion = System.getProperty(JVMArgsFactory.getRemoteBrowserVersion());
            this.hubUrl = System.getProperty(JVMArgsFactory.getHubUrl());
            try {
                if (URL != null && !URL.isEmpty())
                    configuredGridHubUrl = new URL(URL);
                else if (hubUrl != null && !hubUrl.isEmpty())
                    configuredGridHubUrl = new URL(hubUrl);
                else
                    configuredGridHubUrl = new URL(DEFAULT_GRID_HUB_URL);
            } catch (MalformedURLException e) {
                logger.error(e.getMessage());
            }

            logger.debug("Configured remote hub URL: " + configuredGridHubUrl);
        }

    }

    /////////////
    // SETTERS //
    /////////////

//    //    @Override
//    public DesiredCapabilities setBrowserNameCapability(DesiredCapabilities caps, String browser) {
//
//        String remoteBrowserName;
//
//        if (browser != null) {
//
//            switch (browser) {
//
//                case BrowserConfig.FIREFOX:
//
//            }
//
//            if (browser.equals(BrowserConfig.FIREFOX))
//                remoteBrowserName = "firefox";
//            else if (browser.equals(BrowserConfig.CHROME))
//                remoteBrowserName = "chrome";
//            else if (browser.equals(BrowserConfig.IE))
//                remoteBrowserName = "iexplore";
//            else if (browser.equals(BrowserConfig.SAFARI))
//                remoteBrowserName = "safari";
//            else if (browser.equals(BrowserConfig.ANY))
//                remoteBrowserName = "ANY";
//            else throw new IllegalArgumentException("Unsupported browser type");
//
//            caps.setBrowserName(remoteBrowserName);
//            logger.debug("Configured remote browser name capability: " + remoteBrowserName);
//            logger.debug("Modified capabilities: " + caps);
//
//        } else throw new CustomExceptions.Common.NullArgumentException("Null argument: BrowserTypes browser");
//
//        return caps;
//    }



    /**
     * @param driver RemoteWebDriver
     * @return ip of the execution node
     */
    public String getNodeIPAddress(RemoteWebDriver driver) {

        String ip = null;
        try {
            HttpCommandExecutor ce = (HttpCommandExecutor) driver.getCommandExecutor();
            String hostName = ce.getAddressOfRemoteServer().getHost();
            int port = ce.getAddressOfRemoteServer().getPort();
            HttpHost host = new HttpHost(hostName, port);
            HttpClient client = HttpClientBuilder.create().build();
            URL sessionURL = new URL("http://" + hostName + ":" + port + "/grid/api/testsession?session=" + driver.getSessionId());
            logger.debug("Remote session URL: " + sessionURL);
            BasicHttpEntityEnclosingRequest r = new BasicHttpEntityEnclosingRequest("POST", sessionURL.toExternalForm());
            HttpResponse response = client.execute(host, r);
            JSONObject object = RemoteWebDriverFactory.extractObject(response);
            URL myURL = new URL(object.getString("proxyId"));
            if ((myURL.getHost() != null) && (myURL.getPort() != -1))
                ip = myURL.getHost();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        return ip;
    }

    /**
     * @param resp HttpResponse
     * @return JSONObject
     * @throws IOException
     * @throws JSONException
     */
    private static JSONObject extractObject(HttpResponse resp) throws IOException, JSONException {

        InputStream contents = resp.getEntity().getContent();
        StringWriter writer = new StringWriter();
        IOUtils.copy(contents, writer, "UTF8");

        return new JSONObject(writer.toString());
    }

    /**
     * Set Selenium Grid Hub URL
     *
     * @param hubUrl String
     */
    public void setHubUrl(String hubUrl) {
        this.hubUrl = hubUrl;
    }

    /**
     * Get configured Grid Hub URL
     *
     * @return Hub URL
     */
    public URL getConfiguredGridHubUrl() {
        return configuredGridHubUrl;
    }

    /**
     * Method that relieves the remote browser version
     *
     * @return remote browser version
     */
    public String getRemoteBrowserVersion() {
        return ((RemoteWebDriver) ((WebContext) getCurrentContext()).getDriver()).getCapabilities().getVersion();
    }

    /**
     * Method that retrieves to the remote browser name
     *
     * @return remote browser name
     */
    public String getRemoteBrowserName() {
        return ((RemoteWebDriver) ((WebContext) getCurrentContext()).getDriver()).getCapabilities().getBrowserName();
    }

    /**
     * Method that retrieves to the remote platform name
     *
     * @return remote browser name
     */
    public String getRemotePlatformName() {
        return ((RemoteWebDriver) ((WebContext) getCurrentContext()).getDriver()).getCapabilities().getPlatform().name();
    }

    /**
     * Parse to boolean the grid JVM arg
     *
     * @return boolean
     */
    public boolean isGridExecution() {
        return isGridExecution;

    }


}

