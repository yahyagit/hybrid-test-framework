package com.atanas.kanchev.testframework.core.handlers.wrappers;

import com.atanas.kanchev.testframework.core.handlers.DeviceBasedHandler;

/**
 * @author Atanas Ksnchev
 */
public interface IAppiumWrapper {

    DeviceBasedHandler DEVICE_BASED_HANDLER = new DeviceBasedHandler();

    default DeviceBasedHandler setupAppium() {
        return DEVICE_BASED_HANDLER;
    }

}
