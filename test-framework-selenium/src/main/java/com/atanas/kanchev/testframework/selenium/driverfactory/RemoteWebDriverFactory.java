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
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
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
public final class RemoteWebDriverFactory extends AbstractDriver<WebDriver> {

    private static final Logger logger = LoggerFactory.getLogger(RemoteWebDriverFactory.class);

    // Hub URL
    private URL configuredGridHubUrl;

    /**
     * Default constructor
     */
    public RemoteWebDriverFactory() {

        try {
            configuredGridHubUrl = new URL(DefaultProperties.DEFAULT_GRID_HUB_URL);
        } catch (MalformedURLException e) {
            logger.error(e.getMessage());
        }

    }

    /**
     * Param constructor
     * Instantiates {@link RemoteWebDriverFactory#configuredGridHubUrl}
     *
     * @param URL String
     */
    public RemoteWebDriverFactory(final String URL) {

        try {
            if (URL != null && !URL.isEmpty())
                configuredGridHubUrl = new URL(URL);
            else
                configuredGridHubUrl = new URL(DefaultProperties.DEFAULT_GRID_HUB_URL);
        } catch (MalformedURLException e) {
            logger.error(e.getMessage());
        }

        logger.debug("Configured remote hub URL: " + configuredGridHubUrl);

    }

    public RemoteWebDriver getRemoteWebDriver(final String version, final Platform platform, Browsers browsers) {
        DesiredCapabilitiesFactory factory = new DesiredCapabilitiesFactory();
        return new RemoteWebDriver(configuredGridHubUrl, factory.mergeCapabilities(factory.getDefaultChromeCaps(), factory.setRemoteBrowserCaps(version, platform, browsers)));

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

    @Override
    WebDriver configureTimeouts(long implicitlyWait, long pageLoadTimeout) {
        return null;
    }

    @Override
    String getExecutionIP() {

        String ip = null;
        try {
            HttpCommandExecutor ce = (HttpCommandExecutor) ((RemoteWebDriver) super.getDriver()).getCommandExecutor();
            String hostName = ce.getAddressOfRemoteServer().getHost();
            int port = ce.getAddressOfRemoteServer().getPort();
            HttpHost host = new HttpHost(hostName, port);
            HttpClient client = HttpClientBuilder.create().build();
            URL sessionURL = new URL("http://" + hostName + ":" + port + "/grid/api/testsession?session=" + ((RemoteWebDriver) super.getDriver()).getSessionId());
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
}