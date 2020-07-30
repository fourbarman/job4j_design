package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 30.07.2020.
 */
public class AnalizyTest {
    String src = "server_log.txt";
    String trg = "unavailable.csv";
    List<String> onePeriod = new ArrayList<>(List.of(
            "200 15:15:15",
            "400 16:16:16",
            "500 17:17:17",
            "300 18:18:18"
    ));
    List<String> threePeriods = new ArrayList<>(List.of(
            "200 15:15:15",
            "400 16:16:16",
            "500 17:17:17",
            "300 18:18:18",
            "200 19:19:19",
            "400 20:20:20",
            "400 21:21:21",
            "500 22:22:22",
            "200 23:23:23"
    ));

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    /**
     * Test when log has one period of unavailable.
     */
    @Test
    public void whenLogContainsOneUnavailablePeriod() throws IOException {
        File source = folder.newFile(src);
        File target = folder.newFile(trg);
        try (PrintWriter out = new PrintWriter(new FileOutputStream(source))) {
            for (String s : onePeriod) {
                out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Analizy analizy = new Analizy();
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(target))) {
            assertThat(bufferedReader.readLine(), is("16:16:16;18:18:18"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Test when log has three periods of unavailable.
     */
    @Test
    public void whenLogContainsThreeUnavailablePeriods() throws IOException {
        File source = folder.newFile(src);
        File target = folder.newFile(trg);
        try (PrintWriter out = new PrintWriter(new FileOutputStream(source))) {
            for (String s : threePeriods) {
                out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Analizy analizy = new Analizy();
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(target))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            assertThat(sb.toString(), is(
                    "16:16:16;18:18:18"
                            + "20:20:20;23:23:23"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
