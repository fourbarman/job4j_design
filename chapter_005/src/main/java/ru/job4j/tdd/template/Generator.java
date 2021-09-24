package ru.job4j.tdd.template;

import java.util.Map;

public interface Generator {
    String produce(String template, Map<String, String> args);
}
