package com.atanas.kanchev.testframework.core.handlers.wrappers;

import com.atanas.kanchev.testframework.sikuli.SikuliXFactory;

/**
 * @author Atanas Ksnchev
 */
public interface ISikulix {

    default SikuliXFactory image(final String image) {
        return new SikuliXFactory(image);
    }

    default SikuliXFactory image() {
        return new SikuliXFactory();
    }
}
