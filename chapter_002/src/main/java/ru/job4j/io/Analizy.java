package ru.job4j.io;

import java.io.*;

/**
 * Config.
 * Reads .properties file and returns property value by key.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 30.07.2020.
 */
public class Analizy {
    /**
     * Returns if string contains 400 or 500.
     */
    private boolean isUnavailable(String s) {
        return s.startsWith("400") || s.startsWith("500");
    }

    /**
     * Takes unavailable server time from source file
     * and puts it to target .csv file.
     *
     * @param source String incoming file path.
     * @param target String outgoing file path.
     */
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            String line;
            while ((line = in.readLine()) != null) {
                if (isUnavailable(line)) {
                    out.write(line.split(" ")[1] + ";");
                    line = in.readLine();
                    while (isUnavailable(line)) {
                        line = in.readLine();
                        if (!isUnavailable(line)) {
                            out.write(line.split(" ")[1] + System.lineSeparator());
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
