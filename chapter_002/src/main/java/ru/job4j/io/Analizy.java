package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            String line;
            List<String> list = new ArrayList<>();
            while ((line = in.readLine()) != null) {
                if (isUnavailable(line)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(line.split(" ")[1])
                            .append(";");
                    line = in.readLine();
                    while (isUnavailable(line)) {
                        line = in.readLine();
                        if (!isUnavailable(line)) {
                            sb.append(line.split(" ")[1])
                                    .append(System.lineSeparator());
                            list.add(sb.toString());
                        }
                    }
                }
            }
            try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
                for (String s : list) {
                    out.write(s);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
