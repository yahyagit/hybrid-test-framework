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

import io.appium.java_client.*;
import io.appium.java_client.android.AndroidElement;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import static com.atanas.kanchev.testframework.appium.accessors.AppiumAccessors.$appium;
import static com.atanas.kanchev.testframework.commons.accessors.ContextsAccessor.context;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AndroidGestureTest extends BaseTest {

    @Test public void singleTapTest() throws Exception {
        $appium().android().activity().startActivity("io.appium.android.apis", ".view.Buttons1");
        Point point =
            $appium().android().find().findElementById("io.appium.android.apis:id/button_toggle")
                .getLocation();
        $appium().android().touchShortcuts().tap(1, point.x + 20, point.y + 30, 1000);
        assertEquals("ON",
            $appium().android().find().findElementById("io.appium.android.apis:id/button_toggle")
                .getText());
    }

    @Test public void singleElementTapTest() throws Exception {
        $appium().android().activity().startActivity("io.appium.android.apis", ".view.Buttons1");
        $appium().android().touchShortcuts().tap(1,
            $appium().android().find().findElementById("io.appium.android.apis:id/button_toggle"),
            1000);
        assertEquals("ON",
            $appium().android().find().findElementById("io.appium.android.apis:id/button_toggle")
                .getText());
    }

    @Test public void multiTapActionTest() throws Exception {
        $appium().android().
            activity().startActivity("io.appium.android.apis", ".view.ChronometerDemo");
        AndroidElement chronometer =
            $appium().android().find().findElementById("io.appium.android.apis:id/chronometer");

        TouchAction startStop =
            new TouchAction((AppiumDriver) context().getCurrentContext().getDriver())
                .tap($appium().android().find().findElementById("io.appium.android.apis:id/start"))
                .waitAction(2000)
                .tap($appium().android().find().findElementById("io.appium.android.apis:id/stop"));

        MultiTouchAction m1 =
            new MultiTouchAction((AppiumDriver) context().getCurrentContext().getDriver())
                .add(startStop);
        m1.perform();

        String time = chronometer.getText();
        assertNotEquals(time, "Initial format: 00:00");
        Thread.sleep(2500);
        assertEquals(time, chronometer.getText());

        TouchAction reset =
            new TouchAction((AppiumDriver) context().getCurrentContext().getDriver())
                .tap($appium().android().find().findElementById("io.appium.android.apis:id/reset"));
        MultiTouchAction m2 =
            new MultiTouchAction((AppiumDriver) context().getCurrentContext().getDriver())
                .add(startStop).add(reset);
        m2.perform();

        assertEquals("Initial format: 00:00", chronometer.getText());
    }

    @Test public void dragNDropTest() throws Exception {
        $appium().android().activity()
            .startActivity("io.appium.android.apis", ".view.DragAndDropDemo");
        WebElement dragDot1 =
            $appium().android().find().findElement(By.id("io.appium.android.apis:id/drag_dot_1"));
        WebElement dragDot3 =
            $appium().android().find().findElement(By.id("io.appium.android.apis:id/drag_dot_3"));

        WebElement dragText =
            $appium().android().find().findElement(By.id("io.appium.android.apis:id/drag_text"));
        assertEquals("Drag text not empty", "", dragText.getText());

        TouchAction dragNDrop =
            new TouchAction((AppiumDriver) context().getCurrentContext().getDriver())
                .longPress(dragDot1).moveTo(dragDot3).release();
        dragNDrop.perform();

        assertNotEquals("Drag text empty", "", dragText.getText());
    }

    @Test public void zoomAndPinchTest() throws Exception {
        $appium().android().activity().startActivity("io.appium.android.apis", ".ApiDemos");
        MobileElement e = $appium().android().find().findElement(MobileBy.AccessibilityId("App"));
        e.zoom();
        e.pinch();
    }

    @Test public void reusableTapTest() throws Exception {
        $appium().android().activity().startActivity("io.appium.android.apis", ".view.Buttons1");
        AndroidElement element =
            $appium().android().find().findElementById("io.appium.android.apis:id/button_toggle");
        TouchAction tap =
            new TouchAction((AppiumDriver) context().getCurrentContext().getDriver()).tap(element);

        $appium().android().touchActions().performTouchAction(tap);
        assertEquals("ON",
            $appium().android().find().findElementById("io.appium.android.apis:id/button_toggle")
                .getText());

        $appium().android().touchActions().performTouchAction(tap);
        assertEquals("OFF",
            $appium().android().find().findElementById("io.appium.android.apis:id/button_toggle")
                .getText());
    }

    @Test public void verticalSwipingTest() throws Exception {
        $appium().android().find().findElementByAccessibilityId("Views").click();
        AndroidElement listView =
            $appium().android().find().findElement(By.className("android.widget.ListView"));
        MobileElement textView = $appium().android().find().findElementById("android:id/text1");

        String originalText = textView.getText();

        listView.swipe(SwipeElementDirection.UP, 20, 15, 1000);
        assertNotEquals(originalText, textView.getText());

        listView.swipe(SwipeElementDirection.DOWN, 20, 15, 1000);
        assertEquals(originalText, textView.getText());
    }

    @Test public void horizontalSwipingTest() throws Exception {
        $appium().android().activity().startActivity("io.appium.android.apis", ".view.Gallery1");

        AndroidElement gallery =
            $appium().android().find().findElementById("io.appium.android.apis:id/gallery");
        int originalImageCount = gallery.findElementsByClassName("android.widget.ImageView").size();

        gallery.swipe(SwipeElementDirection.LEFT, 5, 5, 2000);
        assertNotEquals(originalImageCount,
            gallery.findElementsByClassName("android.widget.ImageView").size());

        gallery.swipe(SwipeElementDirection.RIGHT, 5, 5, 2000);
        assertEquals(originalImageCount,
            gallery.findElementsByClassName("android.widget.ImageView").size());
    }
}
