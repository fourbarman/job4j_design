package ru.job4j.grabber;

import java.time.Instant;

/**
 * DateTimeParser.
 * Interface for parsing date and time from given string.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 25.03.2021.
 */
public interface DateTimeParser {
    /**
     * Parse date from string and return Instant object.
     *
     * @param parse String string.
     */
    Instant parse(String parse);
}
