package com.atanas.kanchev.testframework.sikuli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.FileVisitResult.CONTINUE;
import static java.nio.file.FileVisitResult.TERMINATE;

/**
 * @author Atanas Ksnchev
 */
public final class ImageFinder extends SimpleFileVisitor<Path> {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(ImageFinder.class);

    private final PathMatcher matcher;
    private final String pattern;
    private File file;

    public ImageFinder(String pattern) {
        this.matcher = FileSystems.getDefault().getPathMatcher("glob:" + pattern);
        this.pattern = pattern;
    }

    // Compares the glob pattern against the file or directory name.
    private boolean find(Path file) {
        Path name = file.getFileName();
        if (name != null && matcher.matches(name)) {
            this.file = file.toFile();
            logger.debug("File found: " + this.file.getAbsolutePath());
            return true;
        }
        return false;
    }

    // Invoke the pattern matching    method on each file.
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        boolean isFound = find(file);
        if (isFound)
            return TERMINATE;
        else
            return CONTINUE;
    }

    // Invoke the pattern matching method on each directory.
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        find(dir);
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        return TERMINATE;
    }

    public File getFile() throws IOException {

        Path startingDir = Paths.get(System.getProperty("user.dir"));
        Files.walkFileTree(startingDir, this);

        if (file == null) {
            logger.error("File not found: " + pattern);
            throw new FileNotFoundException("File not found: " + pattern);
        }

        return file;
    }

}