package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Config.
 * Reads .properties file and returns property value by key.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 29.07.2020.
 */
public class Search {
    /**
     * Main method.
     *
     * @param args Args.
     * @throws IOException Exception.
     */
    public static void main(String[] args) throws IOException {
        Path start = Paths.get(".");
        search(start, "js").forEach(System.out::println);
    }

    /**
     * Returns List of paths of files with given extension.
     *
     * @param root Root path.
     * @param ext  Given extension.
     * @return List of paths.
     * @throws IOException Exception.
     */
    public static List<Path> search(Path root, String ext) throws IOException {
        SearchFiles searcher = new SearchFiles(p -> p.toFile().getName().endsWith(ext));
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
