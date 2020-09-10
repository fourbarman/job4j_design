package ru.job4j.io.chat;

import java.io.*;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class TestMap {
    public TestMap(Input input) {
        this.input = input;
    }
    private void setRun(boolean run) {
        this.run = run;
    }
    boolean run;
    List<String> words;
    Map<String, Supplier<String>> map;
    StringBuilder log;
    Input input;
    Supplier<String> printRandom = new Supplier<>() {
        @Override
        public String get() {
            Random random = new Random();
            return words.get(random.nextInt(words.size()));
        }
    };
    Supplier<String> stop = () -> null;
    Supplier<String> end = () -> {
        setRun(false);
        this.writeLog(this.log);
        return null;
    };

    private List<String> getWords() {
        List<String> list = null;
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("./src/com/company/words.txt"))) {
            list = bufferedReader.lines().collect(Collectors.toList());
        }catch(IOException ioException) {
            ioException.printStackTrace();
        }
        return list;
    }

    private void writeLog(StringBuilder log) {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("./log.txt"))) {
            bufferedWriter.write(log.toString());
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    public void init() {
        this.words = this.getWords();
        this.map = new HashMap<>();
        this.setRun(true);
        log = new StringBuilder();
        map.put("continue", printRandom);
        map.put("stop", stop);
        map.put("end", end);
    }

    public void run() {
        String line;
        String answer;
        while(this.run) {
            line = this.input.next();
            log.append(line).append(System.lineSeparator());
            answer = this.map.getOrDefault(line, printRandom).get();
            log.append(answer);
            System.out.println(answer);
        }
    }

    public static void main(String[] args) {
        TestMap testMap = new TestMap(new ConsoleInput(new Scanner(System.in)));
        testMap.init();
        testMap.run();
    }
}
