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

package com.atanas.kanchev.testframework.dataservices.dataprovider.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.FileVisitResult.CONTINUE;
import static java.nio.file.FileVisitResult.TERMINATE;

/**
 * <p>FileFinder class.</p>
 *
 * @author Atanas Kanchev
 */
public final class FileFinder extends SimpleFileVisitor<Path> {

    // the logger
    private static final Logger logger = LoggerFactory.getLogger(FileFinder.class);

    private final PathMatcher matcher;
    private final String pattern;
    private File file;

    /**
     * <p>Constructor for FileFinder.</p>
     *
     * @param pattern a {@link String} object.
     */
    public FileFinder(String pattern) {
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

    /**
     * {@inheritDoc}
     */
    @Override public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        boolean isFound = find(file);
        if (isFound)
            return TERMINATE;
        else
            return CONTINUE;
    }

    // Invoke the pattern matching method on each directory.

    /**
     * {@inheritDoc}
     */
    @Override public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        find(dir);
        return CONTINUE;
    }

    /**
     * {@inheritDoc}
     */
    @Override public FileVisitResult visitFileFailed(Path file, IOException exc) {
        return TERMINATE;
    }

    /**
     * <p>Getter for the field <code>file</code>.</p>
     *
     * @return a {@link File} object.
     * @throws IOException if any.
     */
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
