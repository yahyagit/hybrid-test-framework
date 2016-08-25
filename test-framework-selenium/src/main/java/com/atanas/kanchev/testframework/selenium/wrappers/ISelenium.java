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

import com.atanas.kanchev.testframework.commons.exceptions.CustomExceptions;
import com.atanas.kanchev.testframework.commons.wrappers.IContext;
import com.atanas.kanchev.testframework.selenium.context.SeleniumContext;
import com.atanas.kanchev.testframework.selenium.driverfactory.DriverFactory;
import com.atanas.kanchev.testframework.selenium.handlers.Finds;
import com.atanas.kanchev.testframework.selenium.handlers.Navigates;
import com.atanas.kanchev.testframework.selenium.handlers.Probes;
import com.atanas.kanchev.testframework.selenium.interactions.element.OmniaElement;
import com.atanas.kanchev.testframework.selenium.interactions.wait.Waiting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface ISelenium extends IFind, INavigate, IProbes, IWaits, IContext<SeleniumContext>, IElement {


}

interface IElement {

    default OmniaElement o(){
        return new OmniaElement();
    }

    default OmniaElement o(By by){

        return new OmniaElement(by);
    }

}


interface IFind {

    default Finds<? extends WebDriver> find() {
        return new Finds<>();
    }

    default Finds<? extends WebDriver> find(WebElement e) {
        return new Finds<>(e);
    }

    default Finds<? extends WebDriver> find(Class<?> clasz) {
        return new Finds<>(clasz);
    }
}


interface INavigate extends IContext<SeleniumContext> {

    default DriverFactory setupSelenium() {

        try {
           context().getCurrentContext();
        } catch (CustomExceptions.Common.NullReferenceException e) {
            context().setCurrentContext(new SeleniumContext<>());
            return context().getCurrentContext().getDriverFactory();
        }

        return null;
    }

    default Navigates goTo(final String url) {
        return new Navigates().getPage(url);
    }
}


interface IProbes {

    default Probes<? extends org.openqa.selenium.WebDriver> probe(By locator) {
        return new Probes<>(locator);
    }
}


interface IWaits {

    default Waiting waitFor() {
        return new Waiting();
    }


//    default Waiting waitFor(long duration) {
//        return new Waiting(duration);
//    }
//
//    default Waiting waitFor(long duration, long polling, TimeUnit unit) {
//        return new Waiting(duration, polling, unit);
//    }

}
