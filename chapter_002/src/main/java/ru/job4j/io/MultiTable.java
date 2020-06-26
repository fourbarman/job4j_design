package ru.job4j.io;

import java.io.FileOutputStream;
import java.util.Arrays;

/**
 * MultiTable.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 26.06.2020.
 */
public class MultiTable {
    /**
     * Computes multiplication table.
     *
     * @param number Top border of table.
     * @return int[][].
     */

    public int[][] tableToFile(int number) {
        int[][] table = new int[number][number];
        for (int i = 0; i < number; i++) {
            for (int j = 0; j < number; j++) {
                table[i][j] = (i + 1) * (j + 1);
            }
        }
        return table;
    }

    /**
     * Main method.
     * Writes multiplication table to file.
     *
     * @param args Args.
     */
    public static void main(String[] args) {
        MultiTable mt = new MultiTable();
        int[][] t = mt.tableToFile(8);
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            for (int[] ints : t) {
                out.write(Arrays.toString(ints).getBytes());
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
