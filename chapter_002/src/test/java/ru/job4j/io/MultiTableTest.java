package ru.job4j.io;

import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 27.06.2020.
 */
public class MultiTableTest {
    MultiTable multiTable;

    /**
     * Set variables.
     */
    @Before
    public void setVars() {
        multiTable = new MultiTable();
    }

    /**
     * Test when input number is 2 than read multiplication table from file.
     */
    @Test
    public void whenNumberIs2ThanFileHasMultiTableForNumber2() {
        multiTable.writeToFile(2);
        String t = "";
        StringBuilder expected = new StringBuilder();
        expected.append("[1, 2]")
                .append("[2, 4]");
        try (FileInputStream in = new FileInputStream("result.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            t = text.toString();
            t = t.replace("\n", "").replace("\r", "");
            //assertThat(t, is(expected.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(t, is(expected.toString()));
    }
}
