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

package com.atanas.kanchev.testframework.appium.tests.android.native_app_tests;

import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import static com.atanas.kanchev.testframework.appium.accessors.AppiumAccessors.$appium;
import static com.atanas.kanchev.testframework.appium.handlers.AndroidNativeHandler.currentContextKey;
import static com.atanas.kanchev.testframework.commons.accessors.ContextsAccessor.$context;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AndroidGestureTest extends BaseTest {

    @Test public void singleTapTest() throws Exception {
        $appium().$androidNative().activity().startActivity("io.appium.$androidNative.apis", ".view.Buttons1");
        Point point =
            $appium().$androidNative().find().findElementById("io.appium.$androidNative.apis:id/button_toggle")
                .getLocation();
        $appium().$androidNative().touchShortcuts().tap(1, point.x + 20, point.y + 30, 1000);
        assertEquals("ON",
            $appium().$androidNative().find().findElementById("io.appium.$androidNative.apis:id/button_toggle")
                .getText());
    }

    @Test public void singleElementTapTest() throws Exception {
        $appium().$androidNative().activity().startActivity("io.appium.$androidNative.apis", ".view.Buttons1");
        $appium().$androidNative().touchShortcuts().tap(1,
            $appium().$androidNative().find().findElementById("io.appium.$androidNative.apis:id/button_toggle"),
            1000);
        assertEquals("ON",
            $appium().$androidNative().find().findElementById("io.appium.$androidNative.apis:id/button_toggle")
                .getText());
    }

    @Test public void multiTapActionTest() throws Exception {
        $appium().$androidNative().
            activity().startActivity("io.appium.$androidNative.apis", ".view.ChronometerDemo");
        WebElement chronometer =
            $appium().$androidNative().find().findElementById("io.appium.$androidNative.apis:id/chronometer");

        TouchAction startStop =
            new TouchAction($context().getContext(currentContextKey).getDriver())
                .tap($appium().$androidNative().find().findElementById("io.appium.$androidNative.apis:id/start"))
                .waitAction(2000)
                .tap($appium().$androidNative().find().findElementById("io.appium.$androidNative.apis:id/stop"));

        MultiTouchAction m1 =
            new MultiTouchAction($context().getContext(currentContextKey).getDriver())
                .add(startStop);
        m1.perform();

        String time = chronometer.getText();
        assertNotEquals(time, "Initial format: 00:00");
        Thread.sleep(2500);
        assertEquals(time, chronometer.getText());

        TouchAction reset =
            new TouchAction($context().getContext(currentContextKey).getDriver())
                .tap($appium().$androidNative().find().findElementById("io.appium.$androidNative.apis:id/reset"));
        MultiTouchAction m2 =
            new MultiTouchAction($context().getContext(currentContextKey).getDriver())
                .add(startStop).add(reset);
        m2.perform();

        assertEquals("Initial format: 00:00", chronometer.getText());
    }

    @Test public void dragNDropTest() throws Exception {
        $appium().$androidNative().activity()
            .startActivity("io.appium.$androidNative.apis", ".view.DragAndDropDemo");
        WebElement dragDot1 =
            $appium().$androidNative().find().findElement(By.id("io.appium.$androidNative.apis:id/drag_dot_1"));
        WebElement dragDot3 =
            $appium().$androidNative().find().findElement(By.id("io.appium.$androidNative.apis:id/drag_dot_3"));

        WebElement dragText =
            $appium().$androidNative().find().findElement(By.id("io.appium.$androidNative.apis:id/drag_text"));
        assertEquals("Drag text not empty", "", dragText.getText());

        TouchAction dragNDrop =
            new TouchAction($context().getContext(currentContextKey).getDriver())
                .longPress(dragDot1).moveTo(dragDot3).release();
        dragNDrop.perform();

        assertNotEquals("Drag text empty", "", dragText.getText());
    }

//    @Test public void zoomAndPinchTest() throws Exception {
//        $appium().$androidNative().activity().startActivity("io.appium.$androidNative.apis", ".ApiDemos");
//        MobileElement e = $appium().$androidNative().find().findElement(MobileBy.AccessibilityId("App"));
//        e.zoom();
//        e.pinch();
//    }

//    @Test public void reusableTapTest() throws Exception {
//        $appium().$androidNative().activity().startActivity("io.appium.$androidNative.apis", ".view.Buttons1");
//        AndroidElement element =
//            $appium().$androidNative().find().findElementById("io.appium.$androidNative.apis:id/button_toggle");
//        TouchAction tap =
//            new TouchAction($context().getContext(currentContextKey).getDriver()).tap(element);
//
//        $appium().$androidNative().touchActions().performTouchAction(tap);
//        assertEquals("ON",
//            $appium().$androidNative().find().findElementById("io.appium.$androidNative.apis:id/button_toggle")
//                .getText());
//
//        $appium().$androidNative().touchActions().performTouchAction(tap);
//        assertEquals("OFF",
//            $appium().$androidNative().find().findElementById("io.appium.$androidNative.apis:id/button_toggle")
//                .getText());
//    }
//
//    @Test public void verticalSwipingTest() throws Exception {
//        $appium().$androidNative().find().findElementByAccessibilityId("Views").click();
//        AndroidElement listView =
//            $appium().$androidNative().find().findElement(By.className("$androidNative.widget.ListView"));
//        MobileElement textView = $appium().$androidNative().find().findElementById("$androidNative:id/text1");
//
//        String originalText = textView.getText();
//
//        listView.swipe(SwipeElementDirection.UP, 20, 15, 1000);
//        assertNotEquals(originalText, textView.getText());
//
//        listView.swipe(SwipeElementDirection.DOWN, 20, 15, 1000);
//        assertEquals(originalText, textView.getText());
//    }
//
//    @Test public void horizontalSwipingTest() throws Exception {
//        $appium().$androidNative().activity().startActivity("io.appium.$androidNative.apis", ".view.Gallery1");
//
//        AndroidElement gallery =
//            $appium().$androidNative().find().findElementById("io.appium.$androidNative.apis:id/gallery");
//        int originalImageCount = gallery.findElementsByClassName("$androidNative.widget.ImageView").size();
//
//        gallery.swipe(SwipeElementDirection.LEFT, 5, 5, 2000);
//        assertNotEquals(originalImageCount,
//            gallery.findElementsByClassName("$androidNative.widget.ImageView").size());
//
//        gallery.swipe(SwipeElementDirection.RIGHT, 5, 5, 2000);
//        assertEquals(originalImageCount,
//            gallery.findElementsByClassName("$androidNative.widget.ImageView").size());
//    }
}
