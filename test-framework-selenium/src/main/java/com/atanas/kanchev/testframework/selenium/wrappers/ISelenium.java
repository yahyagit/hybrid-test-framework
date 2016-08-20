/*
 * Copyright 2016 Atanas Stoychev Kanchev
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.atanas.kanchev.testframework.selenium.wrappers;

import com.atanas.kanchev.testframework.selenium.driverfactory.DriverFactory;
import com.atanas.kanchev.testframework.selenium.handlers.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public interface ISelenium extends IFind, IInteract, INavigate, IProbe, IWaits {
}


interface IFind {

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


interface IInteract {

    default Interact interact() {
        return new Interact();
    }

    default JSExecutor js() {
        return new JSExecutor();
    }
}


interface INavigate {
    DriverFactory DRIVER_FACTORY = new DriverFactory();

    default DriverFactory setupSelenium() {
        return DRIVER_FACTORY;
    }

    default NavigateSelenium goTo(final String url) {
        return new NavigateSelenium(DRIVER_FACTORY).getPage(url);
    }
}


interface IProbe {

    default Probe probe(By locator) {
        return new Probe(locator);
    }
}


interface IWaits {

    default Waits waitFor() {
        return new Waits();
    }

    default Waits waitFor(long duration) {
        return new Waits(duration);
    }

    default Waits waitFor(long duration, long polling, TimeUnit unit) {
        return new Waits(duration, polling, unit);
    }

}
