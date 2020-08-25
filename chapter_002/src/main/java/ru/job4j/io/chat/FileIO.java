package ru.job4j.io.chat;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * FileIO.
 * <p>
 * Provides write to log and read from file.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 25.08.2020.
 */
public class FileIO {
    Path wordsPath;
    Path log;

    /**
     * Constructor.
     *
     * @param wordsPath Path to file with words.
     * @param log       Path to log file.
     */
    public FileIO(Path wordsPath, Path log) {
        this.wordsPath = wordsPath;
        this.log = log;
    }

    /**
     * Returns list of words from file.
     *
     * @param wordsPath Path to file with words.
     * @return List.
     */
    public List<String> getWords(Path wordsPath) {
        List<String> list = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(wordsPath.toFile()))) {
            list = bufferedReader.lines().collect(Collectors.toList());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return list;
    }

    /**
     * Write to log file.
     *
     * @param stringBuilder StringBuilder.
     */
    public void writeToLog(StringBuilder stringBuilder) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(log.toFile()))) {
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
