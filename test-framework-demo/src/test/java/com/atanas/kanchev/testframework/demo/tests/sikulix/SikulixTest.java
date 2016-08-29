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

package com.atanas.kanchev.testframework.demo.tests.sikulix;

import com.atanas.kanchev.testframework.sikuli.handlers.Direction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sikuli.script.Key;

import static com.atanas.kanchev.testframework.commons.accessors.ContextsAccessor.context;
import static com.atanas.kanchev.testframework.selenium.accessors.SeleniumAccessors.$selenium;
import static com.atanas.kanchev.testframework.sikuli.accessors.SikuliXAccessors.$sikuli;


public class SikulixTest {

    @Before public void setUp() throws Exception {

//        $selenium().driverSetup().setReuseBrowser(true);
        $selenium().goTo("https://www.google.co.uk");

    }

    @After public void tearDown() throws Exception {
        context().tearDownContexts();

    }

    @Test public void findImage() throws Exception {

        $sikuli().sikulix().findImage("search-button.png");

    }

    @Test public void click() throws Exception {

        $sikuli().sikulix("search-button.png").click();

    }

    @Test public void click1() throws Exception {

        $sikuli().sikulix("search-field.png").click(Direction.ABOVE, 1);
        $sikuli().sikulix("search-field.png").click(Direction.BELOW, 333);
        $sikuli().sikulix("search-field.png").click(Direction.CENTER, 0);
        $sikuli().sikulix("search-field.png").click(Direction.LEFT, 2);
        $sikuli().sikulix("search-field.png").click(Direction.RIGHT, 999);

    }

    @Test public void doubleClick() throws Exception {

        $sikuli().sikulix("search-field.png").doubleClick();

    }

    @Test public void doubleClick1() throws Exception {

        $sikuli().sikulix("search-field.png").doubleClick(Direction.ABOVE, 1);
        $sikuli().sikulix("search-field.png").doubleClick(Direction.BELOW, 333);
        $sikuli().sikulix("search-field.png").doubleClick(Direction.CENTER, 0);
        $sikuli().sikulix("search-field.png").doubleClick(Direction.LEFT, 2);
        $sikuli().sikulix("search-field.png").doubleClick(Direction.RIGHT, 999);

    }

    @Test public void captureImage() throws Exception {

        $sikuli().sikulix("search-field.png").captureImage("image1", 101, Direction.ABOVE);
        $sikuli().sikulix("search-field.png").captureImage("image2", 999, Direction.BELOW);
        $sikuli().sikulix("search-field.png").captureImage("image3", 0, Direction.CENTER);
        $sikuli().sikulix("search-field.png").captureImage("image4", 22, Direction.LEFT);
        $sikuli().sikulix("search-field.png").captureImage("image5", 1, Direction.RIGHT);

    }

    @Test public void type() throws Exception {

        $sikuli().sikulix().findImage("search-field.png").type("text");

    }

    @Test public void type1() throws Exception {

        $sikuli().sikulix().findImage("search-field.png").type("text", 5, Direction.RIGHT);
    }

    @Test public void swipeBetweenImages() throws Exception {

        $sikuli().sikulix().swipeBetweenImages("search-field.png", "search-button.png");

    }

    @Test public void findImageByScrolling() throws Exception {

        $selenium().goTo("https://www.rightmove.co.uk");
        $sikuli().sikulix().findImageByScrolling("rm-select.png", 15, Direction.BELOW);

    }

    @Test public void sendKey() throws Exception {

        $sikuli().sikulix("search-field.png").sendKey(Key.F5);
    }

    @Test public void setMinimumSimilarityForImage() throws Exception {

        $sikuli().sikulix().setMinimumSimilarityForImage(0.5);

    }
}
