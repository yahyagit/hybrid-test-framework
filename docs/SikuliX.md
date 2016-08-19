SikuliX
=======

# Overview #
[SikuliX](https://github.com/RaiMan/SikuliX-2014) automates anything you see on the screen of your desktop computer running Windows, Mac or some Linux/Unix. 
It uses image recognition powered by OpenCV to identify and control GUI components. 
This is handy in cases when there is no easy access to a GUI's internals or web page you want to act on.
Though SikuliX is currently not available on any mobile device, it can be used with the respective emulators on a desktop browser.

# Setup on Linux #
When running on a linux machine there are some extra dependencies that need to be installed for image based testing to work correctly.
The following components need to be installed:
[tesseract-ocr](https://code.google.com/p/tesseract-ocr/)
[wmctrl](https://sites.google.com/site/tstyblo//wmctrl/)
[xdotool](http://www.semicomplete.com/projects/xdotool/)
[libopencv-dev](http://packages.ubuntu.com/precise/libopencv-dev)
When running Ubuntu these can be installed using apt-get
```
sudo apt-get install tesseract-ocr
sudo apt-get install wmctrl
sudo apt-get install xdotool
sudo apt-get install libopencv-dev
```

# Usage #

- Save the images of the screen elements you want to interact with in a folder under the current working directory, 
i.e. `\src\test\resources` or `\src\main\resources`.
- Implement the interface _`com.atanas.kanchev.testframework.sikuli.handlers.SikuliXFactory.ISikulix`_ in your test class.

## Locating an image match ##
There are two ways you can locate an image:
- `findImage(String fileName)` - locating an image on the current screen area
```java
sikulix().findImage("search-button.png");
```
   > * `@param fileName` - the image file name

- `findImageByScrolling(final String fileName, final int iterations, final Directions direction)` - by scrolling
```java
sikulix().findImageByScrolling("rm-select.png", 15, SikuliXFactory.Directions.BELOW);
```
```
@param fileName  image to file name
@param iterations number of swipes
@param direction  direction of swipes
```
If the image match is found the mouse pointer hovers over the matched sikulix location on the screen

## Interacting with an image ##
Available methods:

- `click()` - left click at the center of the screen region's match
```java
sikulix("search-button.png").click();
```

- `click(final Directions direction, final int px)` - navigate to the match and click in x px in the specified direction
```java
sikulix("search-field.png").click(SikuliXFactory.Directions.ABOVE, 1);
sikulix("search-field.png").click(SikuliXFactory.Directions.BELOW, 333);
sikulix("search-field.png").click(SikuliXFactory.Directions.CENTER, 0);
sikulix("search-field.png").click(SikuliXFactory.Directions.LEFT, 2);
sikulix("search-field.png").click(SikuliXFactory.Directions.RIGHT, 999);
```

- `doubleClick()` - double left click at the screen region's match
```java
sikulix("search-button.png").click();
```

- `doubleClick(final Directions direction, final int px)` - navigate to screen region's match and double click in x px in the specified direction
```java
sikulix("search-field.png").doubleClick(SikuliXFactory.Directions.ABOVE, 1);
sikulix("search-field.png").doubleClick(SikuliXFactory.Directions.BELOW, 333);
sikulix("search-field.png").doubleClick(SikuliXFactory.Directions.CENTER, 0);
sikulix("search-field.png").doubleClick(SikuliXFactory.Directions.LEFT, 2);
sikulix("search-field.png").doubleClick(SikuliXFactory.Directions.RIGHT, 999);
```

-  `captureImage(final String fileName, final int pixelSize, final Directions direction)` - capture image in a file from the current screen region
based on
```
@param fileName file name for the saved image
@param pixelSize size of the image to be captured in px
@param direction direction
```
    
    The images will be saved in `"./target/sikuli-screenshots/";`
```java
sikulix("search-field.png").captureImage("image1", 101, SikuliXFactory.Directions.ABOVE);
sikulix("search-field.png").captureImage("image2", 999, SikuliXFactory.Directions.BELOW);
sikulix("search-field.png").captureImage("image3", 0, SikuliXFactory.Directions.CENTER);
sikulix("search-field.png").captureImage("image4", 22, SikuliXFactory.Directions.LEFT);
sikulix("search-field.png").captureImage("image5", 1, SikuliXFactory.Directions.RIGHT);
```

- `type(String text)` - type into the center of the located screen region
```java
sikulix().findImage("search-field.png").type("text");
```

- `type(final String text, final int pixelSize, final Directions direction)`
```java
sikulix().findImage("search-field.png").type("text", 5, SikuliXFactory.Directions.RIGHT);
```
```
@param text      value to be typed in
@param pixelSize px to shift
@param direction relative to the current position
```
    
- `swipeBetweenImages(String startPointImagePath, String endPointImagePath)` - From the current screen create a swipe/click and drag motion relative from Img1 to Img2. 
Drag from a position and drop to another using left mouse button
```java
sikulix().swipeBetweenImages("search-field.png", "search-button.png");
```

- `sendKey(final String specialKey)` - From the existing Screen press a set of generic shortcut keys i.e. SPACE, F12, ESC {@link org.sikuli.script.Key}.
Example usage: press(Key.F12) or press(Key.SPACE)
```java
sikulix("search-field.png").sendKey(Key.F5);
```

- `setMinimumSimilarityForImage(final double minSimilarity)`
```java
sikulix().setMinimumSimilarityForImage(0.5);
```
