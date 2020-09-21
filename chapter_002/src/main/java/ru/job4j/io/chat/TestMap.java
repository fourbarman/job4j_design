//package ru.job4j.io.chat;
//
//import java.io.*;
//import java.util.*;
//import java.util.function.Consumer;
//import java.util.stream.Collectors;
//
//public class TestMap {
//    public TestMap(Input input) {
//        this.input = input;
//    }
//
//    private void setRun(boolean run) {
//        this.run = run;
//    }
//
//    boolean run;
//    List<String> words;
//    Map<String, Consumer<String>> map;
//    StringBuilder log;
//    Input input;
//    Consumer<String> printRandom = (s) -> {
//        log.append(s).append(System.lineSeparator());
//        StringBuilder random = new StringBuilder();
//        random.append(new Random().nextInt(words.size()));
//        System.out.println(random.toString());
//        log.append(random).append(System.lineSeparator());
//    };
//    Consumer<String> stop = (s) -> {
//        while(!"continue".equals(s)) {
//            s = this.input.next();
//            log.append(s).append(System.lineSeparator());
//        }
//    };
//    Consumer<String> end = (s) -> {
//        this.setRun(false);
//        this.writeLog(this.log);
//    };
//
//    private List<String> getWords() {
//        List<String> list = null;
//        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("./words.txt"))) {
//            list = bufferedReader.lines().collect(Collectors.toList());
//        } catch (IOException ioException) {
//            ioException.printStackTrace();
//        }
//        return list;
//    }
//
//    private void writeLog(StringBuilder log) {
//        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("./log.txt"))) {
//            bufferedWriter.write(log.toString());
//        } catch (IOException ioException) {
//            ioException.printStackTrace();
//        }
//    }
//
//    public void init() {
//        this.words = this.getWords();
//        this.map = new HashMap<>();
//        this.setRun(true);
//        log = new StringBuilder();
//        map.put("continue", printRandom);
//        map.put("stop", stop);
//        map.put("end", end);
//    }
//
//    public void run() {
//        String line;
//        while (this.run) {
//            line = this.input.next();
//            this.map.getOrDefault(line, printRandom).accept(line);
//        }
//    }
//
//    public static void main(String[] args) {
//        TestMap testMap = new TestMap(new ConsoleInput(new Scanner(System.in)));
//        testMap.init();
//        testMap.run();
//    }
//}
