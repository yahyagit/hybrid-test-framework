package com.atanas.kanchev.testframework.core.handlers.appium;

import com.atanas.kanchev.testframework.dataservices.csv.CSVParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Atanas Ksnchev
 */
public class DeviceSetupFromPropFile {
    // the logger
    private final static Logger logger = LoggerFactory.getLogger(DeviceSetupFromPropFile.class);
    static String device = "";
    static String platformName = "";
    static String platFormVersion = "";
    static String deviceName = "";
    static String appiumServerIP = "";
    static String appiumServerPort = "";
    static String udid = "";


    public static void setCapabilities() throws Exception {

        String deviceUnderTest = System.getProperty("device");
        String[] deviceColumn = new String[0];

        if (deviceUnderTest == null) {
            logger.error("Missing runtime argument -Ddevice");
            throw new NullPointerException();
        }
        try {
            List<String[]> table = new CSVParser("src/test/resources/mobile.devices.csv").getAllRows();
            String[] columnHeaders = table.get(0);
            for (String[] devices : table) {
                if (devices[0].trim().equals(deviceUnderTest)) {
                    deviceColumn = devices;
                    break;
                }
            }
            for (int i = 0; i < columnHeaders.length; i++) {
                if (columnHeaders[i].trim().equals("DEVICE")) {
                    device = deviceColumn[i];
                }
                if (columnHeaders[i].trim().equals("platformName")) {
                    platformName = deviceColumn[i];
                }
                if (columnHeaders[i].trim().equals("platformVersion")) {
                    platFormVersion = deviceColumn[i];
                }
                if (columnHeaders[i].trim().equals("deviceName")) {
                    deviceName = deviceColumn[i];
                    udid = deviceColumn[i];
                }
                if (columnHeaders[i].trim().equals("appiumServerIP")) {
                    appiumServerIP = deviceColumn[i];
                }
                if (columnHeaders[i].trim().equals("appiumServerPort")) {
                    appiumServerPort = deviceColumn[i];
                }
                if (columnHeaders[i].trim().equals("udid")) {
                    if (platformName.equalsIgnoreCase("iOS")) {
                        udid = deviceColumn[i];
                    }
                }

            }
        } catch (Exception e) {
            logger.error("Missing CSV File - check src/test/resources/devices.mobile/devices.csv");
            throw e;
        }
        if (deviceColumn.length == 0) {
            logger.error("The specified device: " + deviceUnderTest + " is not in the file devices.csv");
            throw new NullPointerException();
        }
    }

    public static String getDeviceName() {
        return deviceName;
    }

    public static String getPlatFormVersion() {
        return platFormVersion;
    }

    public static String getPlatformName() {
        return platformName;
    }

    public static String getAppiumServerIP() {
        return appiumServerIP;
    }

    public static String getAppiumServerPort() {
        return appiumServerPort;
    }

    public static String getUdid() {
        return udid;
    }

}
