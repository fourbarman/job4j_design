package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
     * Main method.
     *
     * @param args args.
     */
    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        for (String line : log) {
            System.out.println(line);
        }
    }
}
