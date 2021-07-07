package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
     * Load file to cache.
     *
     * @param key Filename.
     * @return Text.
     */
    @Override
    protected String load(String key) {
        File path = new File(cachingDir + File.separator + key);
        if (path.exists()) {
            StringBuilder stringBuilder = new StringBuilder();
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                    stringBuilder.append("\n");
                }
            } catch (IOException ioException) {
                System.out.println("Файл не найден");
            }
            return stringBuilder.toString();
        }
        return null;
    }
}
