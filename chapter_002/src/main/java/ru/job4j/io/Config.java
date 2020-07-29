package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Config.
 * Reads .properties file and returns property value by key.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 29.07.2020.
 */
public class Config {
    private final String path;
    private Map<String, String> values = new HashMap<String, String>();

    /**
     * Constructor.
     *
     * @param path Path to config file.
     */
    public Config(final String path) {
        this.path = path;
    }

    /**
     * Loads key and value to Map from file.
     */
    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            values = read.lines().filter(s -> s.contains("=")
                    && !s.startsWith("#") && !s.isBlank())
                    .map(s -> s.split("="))
                    .collect(Collectors.toMap(s -> s[0], s -> s[1]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Return value by key.
     *
     * @param key Key.
     * @return Value.
     */
    public String value(String key) {
        return values.get(key);
    }
}
