package ru.job4j.io;

import java.io.File;
import java.util.Objects;

/**
 * Dir.
 * Scan given directory and show file/dir names and size.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 01.08.2020.
 */
public class Dir {
    /**
     * Calculate dir/file size.
     *
     * @param directory File.
     * @return long File size.
     */
    public static long folderSize(File directory) {
        long length = 0;
        if (directory.isFile())
            length += directory.length();
        else {
            for (File file : Objects.requireNonNull(directory.listFiles())) {
                if (file.isFile())
                    length += file.length();
                else
                    length += folderSize(file);
            }
        }

        return length;
    }

    /**
     * Returns path to projects directory.
     *
     * @param path Path.
     * @return Path to projects directory.
     */
    private static String currentPath(String path) {
        String currentPath = System.getProperty("user.dir");
        String del = "job4j_design";
        StringBuilder stringBuilder = new StringBuilder(currentPath);
        int start = currentPath.indexOf(del);
        int stop = start + del.length();
        return stringBuilder.delete(start - 1, stop).toString();
    }

    /**
     * Main method.
     * Shows file/dir names and size of each.
     *
     * @param args Args.
     */
    public static void main(String[] args) {
        String path = currentPath(System.getProperty("user.dir"));
        File file = new File(path);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        System.out.println(path + " size: " + folderSize(file));
        for (File subfile : Objects.requireNonNull(file.listFiles())) {
            System.out.println(subfile.getName() + " size: " + folderSize(subfile));
        }
    }
}
