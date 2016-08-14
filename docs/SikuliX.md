SikuliX
=======

# Usage #

- Save your images in a folder under the current working directory, i.e. \src\test\resources or \src\main\resources
- Implement the interface _`com.atanas.kanchev.testframework.sikuli.handlers.SikuliXFactory.ISikulix`_

## Finding an image ##
There are two ways you can locate an image:
- `findImage(String imageFileName)` - locating an image on the current screen area based on `@param imageFileName` - the image file name
```java
sikulix().findImage("search-button.png");
```

- `findImageByScrolling(final String imagePath, final int iterations, final Directions direction)` - by scrolling
```java
sikulix().findImageByScrolling("rm-select.png", 15, SikuliXFactory.Directions.BELOW);
```
If the image match is found the mouse pointer hovers over the matched sikulix location on the screen

## Interacting with an image ##
Available methods:

- `click()` - left click at the screen region's match
```java
sikulix("search-button.png").click();
```

- `click(final Directions direction, final int px)` - navigate to screen region's match and click in x px in the specified direction
```java
sikulix("search-field.png").click(SikuliXFactory.Directions.ABOVE, 1);
sikulix("search-field.png").click(SikuliXFactory.Directions.BELOW, 333);
sikulix("search-field.png").click(SikuliXFactory.Directions.CENTER, 0);
sikulix("search-field.png").click(SikuliXFactory.Directions.LEFT, 2);
sikulix("search-field.png").click(SikuliXFactory.Directions.RIGHT, 999);
```

- `doubleClick()` - left double click at the screen region's match
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

-  `captureImage(final String imageName, final int pixelSize, final Directions direction)` - capture image in a file from the current screen region
based on

    > * @param imageName file name for the saved image
    > * @param pixelSize size of the image to be captured in px
    > * @param direction direction
    
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
    > * @param text      value to be typed in
    > * @param pixelSize px to shift
    > * @param direction relative to the current position

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
