package com.atanas.kanchev.testframework.selenium.driver_factory;

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
import org.openqa.selenium.remote.DesiredCapabilities;
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
 * @author Atanas Ksnchev
 */
public class RemoteDriverFactory {

    private static final Logger logger = LoggerFactory.getLogger(RemoteDriverFactory.class);

    public RemoteWebDriver getRemoteWebDriver(DesiredCapabilities desiredCapabilities) {

        URL url = null;
        try {
            url = new URL(Conf.JVMArgs.GRID_URL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        String desiredBrowserVersion = Conf.JVMArgs.BROWSER_VERSION;
        String desiredPlatform = Conf.JVMArgs.PLATFORM;

        if (null != desiredPlatform && !desiredPlatform.isEmpty()) {
            desiredCapabilities.setPlatform(Platform.valueOf(desiredPlatform.toUpperCase()));
        }

        if (null != desiredBrowserVersion && !desiredBrowserVersion.isEmpty()) {
            desiredCapabilities.setVersion(desiredBrowserVersion);
        }

        return new RemoteWebDriver(url, desiredCapabilities);
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


    public String getExecutionIP() {

        String ip = null;
        try {
            HttpCommandExecutor ce = (HttpCommandExecutor) ((RemoteWebDriver) getCurrentContext().getDriver()).getCommandExecutor();
            String hostName = ce.getAddressOfRemoteServer().getHost();
            int port = ce.getAddressOfRemoteServer().getPort();
            HttpHost host = new HttpHost(hostName, port);
            HttpClient client = HttpClientBuilder.create().build();
            URL sessionURL = new URL("http://" + hostName + ":" + port + "/grid/api/testsession?session=" + ((RemoteWebDriver) getCurrentContext().getDriver()).getSessionId());
            logger.debug("Remote session URL: " + sessionURL);
            BasicHttpEntityEnclosingRequest r = new BasicHttpEntityEnclosingRequest("POST", sessionURL.toExternalForm());
            HttpResponse response = client.execute(host, r);
            JSONObject object = extractObject(response);
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
