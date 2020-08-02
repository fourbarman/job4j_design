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
        write(analise(read(source)), target);
    }

    /**
     * Analise list of strings for 400 and 500 substrings.
     * Returns list of changing status.
     *
     * @param strings List to analise.
     * @return List of strings.
     */
    public List<String> analise(List<String> strings) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < strings.size(); i++) {
            if (isUnavailable(strings.get(i))) {
                StringBuilder sb = new StringBuilder();
                sb.append(strings.get(i).split(" ")[1]).append(";");
                while (isUnavailable(strings.get(i)) && i < strings.size()) {
                    i++;
                    if (!isUnavailable(strings.get(i))) {
                        sb.append(strings.get(i).split(" ")[1]);
                        list.add(sb.toString());
                    }
                }
            }
        }
        return list;
    }

    /**
     * Reads file and returns List of lines.
     *
     * @param source File path.
     * @return List.
     */
    public List<String> read(String source) {
        List<String> list = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            String line;
            while ((line = in.readLine()) != null) {
                list.add(line);
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
