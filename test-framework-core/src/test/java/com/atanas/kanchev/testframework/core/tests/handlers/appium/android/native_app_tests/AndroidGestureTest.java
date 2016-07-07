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
        appium().methods().startActivity().startActivity("io.appium.android.apis", ".view.Buttons1");
        Point point =
                appium().methods().find().findElementById("io.appium.android.apis:id/button_toggle").getLocation();
        appium().methods().touchShortcuts().tap(1, point.x + 20, point.y + 30, 1000);
        assertEquals("ON" , appium().methods().find()
            .findElementById("io.appium.android.apis:id/button_toggle").getText());
    }

    @Test
    public void singleElementTapTest() throws Exception {
        appium().methods().startActivity().startActivity("io.appium.android.apis", ".view.Buttons1");
        appium().methods().touchShortcuts().tap(1, appium().methods().find().findElementById("io.appium.android.apis:id/button_toggle"), 1000);
        assertEquals("ON" ,appium().methods().find()
            .findElementById("io.appium.android.apis:id/button_toggle").getText());
    }

    @Test
    public void multiTapActionTest() throws Exception {
        appium().methods().startActivity().startActivity("io.appium.android.apis", ".view.ChronometerDemo");
        AndroidElement chronometer =
                appium().methods().find().findElementById("io.appium.android.apis:id/chronometer");

        TouchAction startStop = new TouchAction((AppiumDriver) context().getCurrentContext().getDriver())
            .tap(appium().methods().find().findElementById("io.appium.android.apis:id/start")).waitAction(2000)
            .tap(appium().methods().find().findElementById("io.appium.android.apis:id/stop"));

        MultiTouchAction m1 = new MultiTouchAction((AppiumDriver) context().getCurrentContext().getDriver()).add(startStop);
        m1.perform();

        String time = chronometer.getText();
        assertNotEquals(time, "Initial format: 00:00");
        Thread.sleep(2500);
        assertEquals(time, chronometer.getText());

        TouchAction reset = new TouchAction((AppiumDriver) context().getCurrentContext().getDriver())
            .tap(appium().methods().find().findElementById("io.appium.android.apis:id/reset"));
        MultiTouchAction m2 = new MultiTouchAction((AppiumDriver) context().getCurrentContext().getDriver()).add(startStop).add(reset);
        m2.perform();

        assertEquals("Initial format: 00:00", chronometer.getText());
    }

    @Test
    public void dragNDropTest() throws Exception  {
        appium().methods().startActivity().startActivity("io.appium.android.apis", ".view.DragAndDropDemo");
        WebElement dragDot1 = appium().methods().find().findElement(By.id("io.appium.android.apis:id/drag_dot_1"));
        WebElement dragDot3 = appium().methods().find().findElement(By.id("io.appium.android.apis:id/drag_dot_3"));

        WebElement dragText = appium().methods().find().findElement(By.id("io.appium.android.apis:id/drag_text"));
        assertEquals("Drag text not empty", "", dragText.getText());

        TouchAction dragNDrop =
            new TouchAction((AppiumDriver) context().getCurrentContext().getDriver()).longPress(dragDot1).moveTo(dragDot3).release();
        dragNDrop.perform();

        assertNotEquals("Drag text empty", "", dragText.getText());
    }

    @Test
    public void zoomAndPinchTest() throws Exception {
        appium().methods().startActivity().startActivity("io.appium.android.apis", ".ApiDemos");
        MobileElement e = appium().methods().find().findElement(MobileBy.AccessibilityId("App"));
        e.zoom();
        e.pinch();
    }

    @Test
    public void reusableTapTest() throws Exception {
        appium().methods().startActivity().startActivity("io.appium.android.apis", ".view.Buttons1");
        AndroidElement element = appium().methods().find().findElementById("io.appium.android.apis:id/button_toggle");
        TouchAction tap = new TouchAction((AppiumDriver) context().getCurrentContext().getDriver()).tap(element);

        appium().methods().performsTouchActions().performTouchAction(tap);
        assertEquals("ON" ,appium().methods().find()
            .findElementById("io.appium.android.apis:id/button_toggle").getText());

        appium().methods().performsTouchActions().performTouchAction(tap);
        assertEquals("OFF" ,appium().methods().find()
            .findElementById("io.appium.android.apis:id/button_toggle").getText());
    }

    @Test
    public void verticalSwipingTest() throws Exception {
        appium().methods().find().findElementByAccessibilityId("Views").click();
        AndroidElement listView = appium().methods().find().findElement(By.className("android.widget.ListView"));
        MobileElement textView = appium().methods().find().findElementById("android:id/text1");

        String originalText = textView.getText();

        listView.swipe(SwipeElementDirection.UP, 20, 15, 1000);
        assertNotEquals(originalText, textView.getText());

        listView.swipe(SwipeElementDirection.DOWN, 20, 15, 1000);
        assertEquals(originalText, textView.getText());
    }

    @Test
    public void horizontalSwipingTest() throws Exception {
        appium().methods().startActivity().startActivity("io.appium.android.apis", ".view.Gallery1");

        AndroidElement gallery = appium().methods().find().findElementById("io.appium.android.apis:id/gallery");
        int originalImageCount = gallery
            .findElementsByClassName("android.widget.ImageView").size();

        gallery.swipe(SwipeElementDirection.LEFT, 5, 5, 2000);
        assertNotEquals(originalImageCount, gallery
            .findElementsByClassName("android.widget.ImageView").size());

        gallery.swipe(SwipeElementDirection.RIGHT, 5, 5, 2000);
        assertEquals(originalImageCount, gallery
            .findElementsByClassName("android.widget.ImageView").size());
    }
}
