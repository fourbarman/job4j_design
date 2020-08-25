package ru.job4j.io.chat;

import java.util.List;
import java.util.Random;

/**
 * Print.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 25.08.2020.
 */
public class Print implements State {
    String name = "CONTINUE";
    List<String> list;

    /**
     * Constructor.
     *
     * @param list List.
     */
    public Print(List<String> list) {
        this.list = list;
    }

    /**
     * Returns name.
     *
     * @return String name.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns random string from list.
     *
     * @return String word.
     */
    @Override
    public String print() {
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
    }
}
