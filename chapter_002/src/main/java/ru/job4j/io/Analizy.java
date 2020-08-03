package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Analizy.
 * Analise log file for downtime and writes information to .csv file.
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
        write(read(source), target);
    }

    /**
     * Reads file and returns List of server changing downtime time status.
     *
     * @param source File path.
     * @return List.
     */
    public List<String> read(String source) {
        List<String> list = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            String line;
            while ((line = in.readLine()) != null) {
                if (isUnavailable(line)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(line.split(" ")[1])
                            .append(";");
                    while (isUnavailable(line)) {
                        line = in.readLine();
                        if (!isUnavailable(line)) {
                            sb.append(line.split(" ")[1]);
                            list.add(sb.toString());
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Writes list<String> to file.
     *
     * @param list   List.
     * @param target File.
     */
    public void write(List<String> list, String target) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            for (String s : list) {
                out.println(s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
