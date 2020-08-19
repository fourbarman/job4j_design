package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Zip.
 * Zip catalogue.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 17.08.2020.
 */
public class Zip {
    /**
     * Zips catalogue to target file.
     *
     * @param sources List of file paths.
     * @param target  Target file to zip into.
     */
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target.getPath())))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(target))) {
                    zip.write(out.readAllBytes());
                }
            }
            System.out.println("Successfully zip to: " + target.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns List of paths of files with given extension.
     *
     * @param directory Directory path to search in.
     * @param ext       Given extension.
     * @return List of paths.
     * @throws IOException Exception.
     */
    public static List<Path> search(Path directory, String ext) throws IOException {
        SearchFiles searcher = new SearchFiles(p -> !p.toFile().getName().endsWith(ext));
        Files.walkFileTree(directory, searcher);
        return searcher.paths;
    }

    /**
     * Main method.
     * Usage with tree args parameters.
     * -d - directory - catalogue.
     * -e - exclude - extension to exclude.
     * -o - output - output filename.
     *
     * @param args Args Catalogue, Extension, Output file.
     * @throws IOException Exception if args are not valid.
     */
    public static void main(String[] args) throws IOException {
        ArgZip a = new ArgZip(args);
        if (a.valid()) {
            new Zip().packFiles(search(Paths.get(a.directory()), a.exclude()), new File(a.output()));
        }
    }
}
