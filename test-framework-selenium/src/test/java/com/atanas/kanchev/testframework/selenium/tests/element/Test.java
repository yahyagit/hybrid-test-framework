/*
 * Copyright 2016 Atanas Stoychev Kanchev
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.atanas.kanchev.testframework.selenium.tests.element;

import org.openqa.selenium.By;

import static com.atanas.kanchev.testframework.selenium.accessors.SeleniumAccessors.$selenium;
import static com.atanas.kanchev.testframework.selenium.accessors.SeleniumAccessors.conf;

/**
 * Created by atanas on 29/09/16.
 */
public class Test {

//    static ContextKey<SeleniumContext> currentContextKey;

    public static void main(String[] args) {
        conf().setBrowser("chrome");
//        $selenium().conf().setBrowser("chrome");
//        SeleniumContext<WebDriver> c = new SeleniumContext<>($selenium().conf().getDriver());
//        currentContextKey = $context().addContext(c.getContextKey(), c);
//        SeleniumAccessorsSingleton.currentContextKey = currentContextKey;
//        $selenium().goTo("https://www.google.co.uk");

//        $selenium().goTo("https://www.google.co.uk");

        $selenium("https://www.google.co.uk").findElement(By.className("gsfi")).click();

//        OmniaWebElement wrapper = ElementProxy.wrapElement(OmniaWebElement.class, currentContextKey);
//        wrapper.findElement(By.className("gsfi"));
//        System.out.println(wrapper.isDisplayed());
//        wrapper.click();
//        wrapper.doubleClick();
//        //        wrapper.waits().titleContains("google");
//        wrapper.sendKeys("hello");

//        $selenium().findElement(By.className("gsfi")).click();
//        $selenium().x().findElement(By.className("gsfi")).click();
        $selenium().sendKeys("hello");
//        $selenium().x().waits().titleContains("goole");

        try {
            Thread.sleep(8888888);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
