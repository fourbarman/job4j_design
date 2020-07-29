package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 29.07.2020.
 */
public class ConfigTest {
    /**
     * Test when file without comments and empty lines.
     */
    @Test
    public void whenPairWithoutComment() {
        String path = "./data/config_without_comments_empties.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("name"),
                is("Max Pankov")
        );
    }

    /**
     * Test when file has comments and empty lines.
     */
    @Test
    public void whenPairWithComment() {
        String path = "./data/config_with_comments_empties.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("name"),
                is("Max Pankov")
        );
    }
}
