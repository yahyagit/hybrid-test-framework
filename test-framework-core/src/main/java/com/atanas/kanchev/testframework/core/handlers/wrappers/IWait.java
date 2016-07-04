package com.atanas.kanchev.testframework.core.handlers.wrappers;

import com.atanas.kanchev.testframework.core.handlers.Wait;

/**
 * @author Atanas Ksnchev
 */
public interface IWait {

    default Wait waitFor(long wait) {
        return new Wait(wait);
    }
}
