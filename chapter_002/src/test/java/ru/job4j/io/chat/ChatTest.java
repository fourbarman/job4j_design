package ru.job4j.io.chat;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
/**
 * Test.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 24.09.2020.
 */
public class ChatTest {
    Chat chat;
    String wordsFile;
    String logFilePath;
    List<String> words;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void initTest() {
        wordsFile = "words.txt";
        logFilePath = "log.txt";
        words = List.of("word_one", "word_two", "word_three");
    }

    /**
     * Test getWords.
     * @throws IOException exception.
     */
    @Test
    public void whenGetWords() throws IOException {
        File file = folder.newFile(wordsFile);
        File log = folder.newFile(logFilePath);
        try (PrintWriter out = new PrintWriter(new FileOutputStream(file))) {
            for (String s : words) {
                out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        chat = new Chat(new ConsoleInput(new Scanner(System.in)));
        chat.init(file.getPath(), log.getPath());
        assertThat(chat.words.size(), is (3));
        assertThat(chat.words.equals(words), is(true));
    }

    /**
     * Test writeLog.
     * 1. Test log.txt creates.
     * 2. Test log.txt text equals to expected.
     */
    @Test
    public void whenWriteToLog() throws IOException{
        File filePath = folder.newFile(wordsFile);
        File logPath = folder.newFile(logFilePath);
        try (PrintWriter out = new PrintWriter(new FileOutputStream(filePath))) {
            for (String s : words) {
                out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder log = new StringBuilder();
        for (String s : words) {
            log.append(s).append(System.lineSeparator());
        }
        chat = new Chat(new ConsoleInput(new Scanner(System.in)));
        chat.init(filePath.getPath(), logPath.getPath());
        chat.writeLog(log, logPath.getPath());
        List<String> textFromLog = Collections.emptyList();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(logPath.getPath())))) {
            textFromLog = bufferedReader.lines().collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(Files.exists(Path.of(logPath.getAbsolutePath())), is(true));
        assertThat(textFromLog.equals(words), is(true));
    }

    /**
     * Test input.
     */
    @Test
    public void whenTestOutput() throws IOException{
        File filePath = folder.newFile(wordsFile);
        File logPath = folder.newFile(logFilePath);
        try (PrintWriter out = new PrintWriter(new FileOutputStream(filePath))) {
            for (String s : words) {
                out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        InputStream sysInBackup = System.in;
        String userInput = "1"
                + "\n2"
                + "\n3"
                + "\nstop"
                + "\n1"
                + "\n2"
                + "\n3"
                + "\ncontinue"
                + "\n1"
                + "\n2"
                + "\n3"
                + "\nend";
        InputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        chat = new Chat(new ConsoleInput(new Scanner(in)));
        List<String> log = Collections.emptyList();
        List<String> userInputList = Arrays.asList(userInput.split("\n").clone());
        chat.init(filePath.getPath(), logPath.getPath());
        chat.run();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(logPath))) {
            log = bufferedReader.lines().collect(Collectors.toList());
        }catch(IOException ioException) {
            ioException.printStackTrace();
        }
        System.setIn(sysInBackup);
        assertThat(log.containsAll(userInputList), is(true));
    }
}
