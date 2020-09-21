package ru.job4j.io.chat;

import java.io.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Chat.
 * Prints random string from words.txt after user input.
 * If user entered "stop", stops printing.
 * If user entered "continue", starts printing again.
 * I user entered "end", writes log to log.txt and terminates program.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 21.09.2020.
 */
public class Chat {
    boolean run;
    List<String> words;
    Map<String, Consumer<String>> map;
    StringBuilder log;
    Input input;
    Consumer<String> printRandom = (s) -> {
        log.append(s).append(System.lineSeparator());
        StringBuilder random = new StringBuilder();
        random.append(new Random().nextInt(words.size()));
        System.out.println(random.toString());
        log.append(random).append(System.lineSeparator());
    };
    Consumer<String> stop = (s) -> {
        while (!"continue".equals(s)) {
            s = this.input.next();
            log.append(s).append(System.lineSeparator());
        }
    };
    Consumer<String> end = (s) -> {
        this.setRun(false);
        this.writeLog(this.log);
    };

    /**
     * Constructor
     *
     * @param input Input.
     */
    public Chat(Input input) {
        this.input = input;
    }

    /**
     * Set run.
     * If true - program keep running, otherwise - terminates.
     *
     * @param run Run.
     */
    private void setRun(boolean run) {
        this.run = run;
    }

    /**
     * Gets list of string from file "words.txt".
     *
     * @return List of strings.
     */
    private List<String> getWords() {
        List<String> list = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("./words.txt"))) {
            list = bufferedReader.lines().collect(Collectors.toList());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return list;
    }

    /**
     * Writes to log.txt file.
     *
     * @param log Log.
     */
    private void writeLog(StringBuilder log) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("./log.txt"))) {
            bufferedWriter.write(log.toString());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /**
     * Initialise starting states.
     */
    public void init() {
        this.words = this.getWords();
        this.map = new HashMap<>();
        this.setRun(true);
        log = new StringBuilder();
        map.put("continue", printRandom);
        map.put("stop", stop);
        map.put("end", end);
    }

    /**
     * Run until run == true.
     */
    public void run() {
        String line;
        while (this.run) {
            line = this.input.next();
            this.map.getOrDefault(line, printRandom).accept(line);
        }
    }

    /**
     * Main method.
     *
     * @param args Args.
     */
    public static void main(String[] args) {
        Chat chat = new Chat(new ConsoleInput(new Scanner(System.in)));
        chat.init();
        chat.run();
    }
}

