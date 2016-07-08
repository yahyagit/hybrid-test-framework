package com.atanas.kanchev.testframework.core.tests.handlers.appium.android.native_app_tests;

import com.atanas.kanchev.testframework.core.handlers.wrappers.IContext;
import io.appium.java_client.*;
import io.appium.java_client.android.AndroidElement;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AndroidGestureTest extends BaseTest implements IContext {

    @Test
    public void singleTapTest() throws Exception {
        android()
                .activity()
                .startActivity("io.APPIUM_INIT.android.apis", ".view.Buttons1");
        Point point =
                android()
                        .find()
                        .findElementById("io.APPIUM_INIT.android.apis:id/button_toggle")
                        .getLocation();
        android()
                .touchShortcuts()
                .tap(1, point.x + 20, point.y + 30, 1000);
        assertEquals("ON", android()
                .find()
                .findElementById("io.APPIUM_INIT.android.apis:id/button_toggle")
                .getText());
    }

    @Test
    public void singleElementTapTest() throws Exception {
        android()
                .activity()
                .startActivity("io.APPIUM_INIT.android.apis", ".view.Buttons1");
        android()
                .touchShortcuts()
                .tap(1, android()
                        .find()
                        .findElementById("io.APPIUM_INIT.android.apis:id/button_toggle"), 1000);
        assertEquals("ON", android().find()
                .findElementById("io.APPIUM_INIT.android.apis:id/button_toggle")
                .getText());
    }

    @Test
    public void multiTapActionTest() throws Exception {
        android().
                activity()
                .startActivity("io.APPIUM_INIT.android.apis", ".view.ChronometerDemo");
        AndroidElement chronometer =
                android()
                        .find()
                        .findElementById("io.APPIUM_INIT.android.apis:id/chronometer");

        TouchAction startStop = new TouchAction((AppiumDriver) context().getCurrentContext().getDriver())
                .tap(android().find().findElementById("io.APPIUM_INIT.android.apis:id/start"))
                .waitAction(2000)
                .tap(android()
                        .find()
                        .findElementById("io.APPIUM_INIT.android.apis:id/stop"));

        MultiTouchAction m1 = new MultiTouchAction((AppiumDriver) context().getCurrentContext().getDriver()).add(startStop);
        m1.perform();

        String time = chronometer.getText();
        assertNotEquals(time, "Initial format: 00:00");
        Thread.sleep(2500);
        assertEquals(time, chronometer.getText());

        TouchAction reset = new TouchAction((AppiumDriver) context().getCurrentContext().getDriver())
                .tap(android().find().findElementById("io.APPIUM_INIT.android.apis:id/reset"));
        MultiTouchAction m2 = new MultiTouchAction((AppiumDriver) context().getCurrentContext().getDriver()).add(startStop).add(reset);
        m2.perform();

        assertEquals("Initial format: 00:00", chronometer.getText());
    }

    @Test
    public void dragNDropTest() throws Exception {
        android()
                .activity()
                .startActivity("io.APPIUM_INIT.android.apis", ".view.DragAndDropDemo");
        WebElement dragDot1 = android().find().findElement(By.id("io.APPIUM_INIT.android.apis:id/drag_dot_1"));
        WebElement dragDot3 = android().find().findElement(By.id("io.APPIUM_INIT.android.apis:id/drag_dot_3"));

        WebElement dragText = android().find().findElement(By.id("io.APPIUM_INIT.android.apis:id/drag_text"));
        assertEquals("Drag text not empty", "", dragText.getText());

        TouchAction dragNDrop = new TouchAction((AppiumDriver) context().getCurrentContext().getDriver())
                .longPress(dragDot1)
                .moveTo(dragDot3)
                .release();
        dragNDrop.perform();

        assertNotEquals("Drag text empty", "", dragText.getText());
    }

    @Test
    public void zoomAndPinchTest() throws Exception {
        android()
                .activity()
                .startActivity("io.APPIUM_INIT.android.apis", ".ApiDemos");
        MobileElement e = android()
                .find()
                .findElement(MobileBy.AccessibilityId("App"));
        e.zoom();
        e.pinch();
    }

    @Test
    public void reusableTapTest() throws Exception {
        android().activity().startActivity("io.APPIUM_INIT.android.apis", ".view.Buttons1");
        AndroidElement element = android().find().findElementById("io.APPIUM_INIT.android.apis:id/button_toggle");
        TouchAction tap = new TouchAction((AppiumDriver) context().getCurrentContext().getDriver()).tap(element);

        android().performsTouchActions().performTouchAction(tap);
        assertEquals("ON", android().find()
                .findElementById("io.APPIUM_INIT.android.apis:id/button_toggle").getText());

        android().performsTouchActions().performTouchAction(tap);
        assertEquals("OFF", android().find()
                .findElementById("io.APPIUM_INIT.android.apis:id/button_toggle").getText());
    }

    @Test
    public void verticalSwipingTest() throws Exception {
        android().find().findElementByAccessibilityId("Views").click();
        AndroidElement listView = android().find().findElement(By.className("android.widget.ListView"));
        MobileElement textView = android().find().findElementById("android:id/text1");

        String originalText = textView.getText();

        listView.swipe(SwipeElementDirection.UP, 20, 15, 1000);
        assertNotEquals(originalText, textView.getText());

        listView.swipe(SwipeElementDirection.DOWN, 20, 15, 1000);
        assertEquals(originalText, textView.getText());
    }

    @Test
    public void horizontalSwipingTest() throws Exception {
        android()
                .activity()
                .startActivity("io.APPIUM_INIT.android.apis", ".view.Gallery1");

        AndroidElement gallery = android().find().findElementById("io.APPIUM_INIT.android.apis:id/gallery");
        int originalImageCount = gallery.findElementsByClassName("android.widget.ImageView").size();

        gallery.swipe(SwipeElementDirection.LEFT, 5, 5, 2000);
        assertNotEquals(originalImageCount, gallery.findElementsByClassName("android.widget.ImageView").size());

        gallery.swipe(SwipeElementDirection.RIGHT, 5, 5, 2000);
        assertEquals(originalImageCount, gallery.findElementsByClassName("android.widget.ImageView").size());
    }
}
