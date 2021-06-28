package ru.job4j.gc;

import java.util.Random;
/**
 * GCTypeDemo.
 *
 * Class for testing GC types.
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 28.05.2021.
 */
public class GCTypeDemo {
    public static void main(String[] args) {
        Random random = new Random();
        int length = 100;
        String[] data = new String[1_000_000];
        for (int i = 0;; i = (i + 1) % data.length) {
            data[i] = String.valueOf(
                    (char) random.nextInt(255)
            ).repeat(length);
        }
    }
}
