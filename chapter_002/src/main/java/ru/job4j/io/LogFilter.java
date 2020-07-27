package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * LogFilter.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 27.07.2020.
 */
public class LogFilter {
    /**
     * Open file and return lines with "404" text in tail.
     *
     * @param file File name.
     * @return String[].
     */
    public static List<String> filter(String file) {
        List<String> log = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            List<String> arrayList = new ArrayList<>();
            br.lines().forEach(arrayList::add);
            for (String s : arrayList) {
                String[] words = s.split(" ");
                if (words[words.length - 2].equals("404")) {
                    log.add(s);
                }
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
        return log;
    }

    /**
     * Create file and write lines from log.
     *
     * @param log  String list.
     * @param file File name.
     */
    public static void save(List<String> log, String file) {
        try (PrintWriter pw = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)))) {
            for (String line : log) {
                pw.write(line);
                pw.write(System.lineSeparator());
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    /**
     * Main method.
     *
     * @param args args.
     */
    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        save(log, "404.txt");
    }
}
