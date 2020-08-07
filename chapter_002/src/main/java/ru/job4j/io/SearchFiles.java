package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.nio.file.FileVisitResult.CONTINUE;

/**
 * SearchFiles.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 07.08.2020.
 */
public class SearchFiles extends SimpleFileVisitor<Path> {
    Predicate<Path> predicate;
    List<Path> paths = new ArrayList<>();

    /**
     * Constructor.
     *
     * @param predicate Predicate.
     */
    public SearchFiles(Predicate<Path> predicate) {
        this.predicate = predicate;
    }

    /**
     * Returns paths List.
     *
     * @return List.
     */
    public List<Path> getPaths() {
        return paths;
    }

    /**
     * Visit file and check if it's extension matches by predicate.
     *
     * @param file  Root path.
     * @param attrs Basic attributes.
     * @return Continue.
     * @throws IOException IOexception.
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (predicate.test(file)) {
            paths.add(file);
        }
        return CONTINUE;
    }
}
