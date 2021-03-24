package ru.job4j.grabber;

import java.time.Instant;

public interface DateTimeParser {
    Instant parse(String parse);
}
