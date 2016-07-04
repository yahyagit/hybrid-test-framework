package com.atanas.kanchev.testframework.core.handlers.wrappers;

import com.atanas.kanchev.testframework.core.handlers.Finder;
import org.openqa.selenium.WebElement;

/**
 * @author Atanas Ksnchev
 */
public interface IFind {

    default Finder find() {
        return new Finder();
    }

    default Finder find(WebElement e) {
        return new Finder(e);
    }

    default Finder find(Class<?> clasz) {
        return new Finder(clasz);
    }
}
