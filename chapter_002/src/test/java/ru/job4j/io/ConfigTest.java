package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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
    Config config;
    String pathWithoutComm = "config_without_comments_empties.properties";
    String pathWithComm = "config_with_comments_empties.properties";
    List<String> withoutComments = new ArrayList<>(List.of("name=Max Pankov"));
    List<String> withComments = new ArrayList<>(List.of(
            "#name=Some Name",
            "",
            "####name=",
            "name=Max Pankov"
    ));
    String expected = "Max Pankov";

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    /**
     * Test when file without comments and empty lines.
     */
    @Test
    public void whenPairWithoutComment() throws IOException {
        File file = folder.newFile(pathWithoutComm);
        try (PrintWriter out = new PrintWriter(new FileOutputStream(file))) {
            for (String s : withoutComments) {
                out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        config = new Config(file.getAbsolutePath());
        config.load();
        assertThat(
                config.value("name"),
                is(expected)
        );
    }

    /**
     * Test when file has comments and empty lines.
     */
    @Test
    public void whenPairWithComment() throws IOException {
        File file = folder.newFile(pathWithComm);
        try (PrintWriter out = new PrintWriter(new FileOutputStream(file))) {
            for (String s : withComments) {
                out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        config = new Config(file.getAbsolutePath());
        config.load();
        assertThat(
                config.value("name"),
                is(expected)
        );
    }
}
