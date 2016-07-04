package com.atanas.kanchev.testframework.core.handlers.wrappers;

import com.atanas.kanchev.testframework.core.handlers.Probe;
import org.openqa.selenium.By;

/**
 * @author Atanas Ksnchev
 */
public interface IProbe {

    default Probe probe(By locator) {
        return new Probe(locator);
    }
}
