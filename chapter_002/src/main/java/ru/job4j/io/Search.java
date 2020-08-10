package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Search.
 * Prints file names with given extension by predicate.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 07.08.2020.
 */
public class Search {
    /**
     * Main method.
     * Usage with two args parameters.
     * First - file path.
     * Second - file extension.
     *
     * @param args Args File path, Extension.
     * @throws IOException Exception.
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IllegalStateException("Use with parameters: java -jar dir.jar ROOT_FOLDER FILE_EXTENSION");
        }
        Path start = Paths.get(args[0]);
        String extension = args[1];
        search(start, extension).forEach(System.out::println);
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
