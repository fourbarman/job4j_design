package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Menu.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 07.07.2021.
 */
public class DirFileCache extends AbstractCache<String, String> {
    //Caching dir path.
    private final String cachingDir;

    /**
     * Constructor.
     *
     * @param cachingDir File name.
     */
    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    /**
     * Return caching directory.
     *
     * @return cachingDir.
     */
    public String getCachingDir() {
        return cachingDir;
    }

    /**
     * Reload file to cache.
     *
     * @param key Filename.
     * @return Text.
     */
    @Override
    protected String load(String key) {
        return readFile(key);
    }

    /**
     * Read file and return text from it.
     *
     * @param fileName File name.
     * @return Text.
     */
    public String readFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            Path path = Paths.get(cachingDir + File.separator + fileName);
            stringBuilder.append(Files.readString(path));
        } catch (IOException ioException) {
            System.out.println("Файл не найден");
        }
        return stringBuilder.toString();
    }
}
