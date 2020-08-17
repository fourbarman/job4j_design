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
            }
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(target))) {
                zip.write(out.readAllBytes());
            }
            System.out.println("Successfully zip to: " + target.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            SearchFiles searcher = new SearchFiles(p -> !p.toFile().getName().endsWith(a.exclude()));
            Files.walkFileTree(Paths.get(a.directory()), searcher);
            String path = a.output();
            new Zip().packFiles(searcher.getPaths(), new File(path));
        }
    }
}
