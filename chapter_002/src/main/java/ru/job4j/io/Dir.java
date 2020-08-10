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
        if (directory.isFile()) {
            length += directory.length();
        } else {
            for (File file : Objects.requireNonNull(directory.listFiles())) {
                if (file.isFile()) {
                    length += file.length();
                } else {
                    length += folderSize(file);
                }
            }
        }

        return length;
    }

    /**
     * Main method.
     * Shows file/dir names and size of each.
     *
     * @param args Args.
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalStateException("Root folder is null. Usage java -jar dir.jar ROOT_FOLDER");
        }
        String path = args[0];
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
