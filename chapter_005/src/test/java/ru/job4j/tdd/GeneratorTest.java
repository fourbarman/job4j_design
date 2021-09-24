package ru.job4j.tdd;

import org.junit.Test;
import ru.job4j.tdd.template.Generator;
import ru.job4j.tdd.template.LessDictionaryArguments;
import ru.job4j.tdd.template.MoreDictionaryArguments;
import ru.job4j.tdd.template.TextGenerator;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class GeneratorTest {
    @Test
    public void produce() {
        Generator textGenerator = new TextGenerator();
        Map<String, String> words = new HashMap<>();
        words.put("name", "Name");
        words.put("subject", "Subject");
        String str = "I am a ${name}, Who are ${subject}?";
        String expected = "I am a Name, Who are Subject?";
        assertEquals(expected, textGenerator.produce(str, words));
    }

    @Test(expected = LessDictionaryArguments.class)
    public void produceWhenMapDoesntHaveArguments() {
        Generator textGenerator = new TextGenerator();
        Map<String, String> words = new HashMap<>();
        words.put("name", "Name");
        words.put("subject", "Subject");
        textGenerator.produce("I am a ${name}, Who are ${subject}, You're not ${anotherArgument}?", words);
    }

    @Test(expected = MoreDictionaryArguments.class)
    public void produceWhenMapHasMoreArguments() {
        Generator textGenerator = new TextGenerator();
        Map<String, String> words = new HashMap<>();
        words.put("name", "Name");
        words.put("subject", "Subject");
        words.put("anotherArgument", "Another");
        textGenerator.produce("I am a ${name}, Who are ${subject}?", words);
    }
}
