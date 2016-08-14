package com.atanas.kanchev.testframework.sikuli.wrappers;

import com.atanas.kanchev.testframework.sikuli.handlers.SikuliXFactory;

/**
 * @author Atanas Ksnchev
 */
public interface ISikulix {

    default SikuliXFactory sikulix(final String image) {
        return new SikuliXFactory(image);
    }

    default SikuliXFactory sikulix() {
        return new SikuliXFactory();
    }
}
