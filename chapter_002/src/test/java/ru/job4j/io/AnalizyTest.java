package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.file.NoSuchFileException;
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
    Analizy analizy;
    String src = "server_log.txt";
    String trg = "unavailable.csv";
    List<String> onePeriod = new ArrayList<>(List.of(
            "200 15:15:15",
            "400 16:16:16",
            "500 17:17:17",
            "300 18:18:18"
    ));
    List<String> expectedOnePeriod = new ArrayList<>(List.of(
            "16:16:16;18:18:18"
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
    List<String> expectedThreePeriods = new ArrayList<>(List.of(
            "16:16:16;18:18:18",
            "20:20:20;23:23:23"
    ));


    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    /**
     * Test when read from file one period than return list.
     */
    @Test
    public void whenReadFromFileOnePeriodThanReturnList() throws IOException {
        File source = folder.newFile(src);
        try (PrintWriter out = new PrintWriter(new FileOutputStream(source))) {
            for (String s : onePeriod) {
                out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        analizy = new Analizy();
        assertThat(analizy.read(source.getAbsolutePath()), is(expectedOnePeriod));
    }

    /**
     * Test when read from file three periods than return list.
     */
    @Test
    public void whenReadFromFileThreePeriodsThanReturnList() throws IOException {
        File source = folder.newFile(src);
        try (PrintWriter out = new PrintWriter(new FileOutputStream(source))) {
            for (String s : threePeriods) {
                out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        analizy = new Analizy();
        assertThat(analizy.read(source.getAbsolutePath()), is(expectedThreePeriods));
    }

    /**
     * Test when write to file one period than file has one period.
     */
    @Test
    public void whenWriteToFileOnePeriodThanReturnList() throws IOException {
        File target = folder.newFile(trg);
        analizy = new Analizy();
        analizy.write(expectedOnePeriod, target.getAbsolutePath());
        List<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(target.getAbsolutePath()))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        } catch (NoSuchFileException e) {
            e.printStackTrace();
        }
        assertThat(list, is(List.of("16:16:16;18:18:18")));
    }

    /**
     * Test when write to file one period than file has one period.
     */
    @Test
    public void whenWriteToFileThreePeriodsThanReturnList() throws IOException {
        File target = folder.newFile(trg);
        analizy = new Analizy();
        analizy.write(expectedThreePeriods, target.getAbsolutePath());
        List<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(target.getAbsolutePath()))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
                System.out.println(line);
            }
        } catch (NoSuchFileException e) {
            e.printStackTrace();
        }
        assertThat(list, is(List.of("16:16:16;18:18:18", "20:20:20;23:23:23")));
    }

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
        analizy = new Analizy();
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        try (BufferedReader br = new BufferedReader(new FileReader(target))) {
            assertThat(br.readLine(), is("16:16:16;18:18:18"));
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
        analizy = new Analizy();
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
