package ru.job4j.io;

import java.io.FileInputStream;

/**
 * MultiTable.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 02.07.2020.
 */
public class EvenNumberFile {
    /**
     * Main method.
     *
     * @param args args.
     */
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("input.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] nums = text.toString().split(System.lineSeparator());
            try {
                for (String s : nums) {
                    int num = Integer.parseInt(s);
                    if (num % 2 == 0) {
                        System.out.println("Even number: " + num);
                    }
                }
            } catch (NumberFormatException numberFormatException) {
                numberFormatException.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
