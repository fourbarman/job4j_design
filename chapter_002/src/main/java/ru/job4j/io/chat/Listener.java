package ru.job4j.io.chat;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Listener.
 * <p>
 * Reads every line from console
 * and changes state by line input.
 * Writes dialog to log.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 25.08.2020.
 */
public class Listener {
    private State state;
    private final Path wordsPath;
    private final Path logPath;
    private final FileIO fileIo;
    private StringBuilder log;
    private Map<String, State> stateMap;

    /**
     * Constructor.
     * <p>
     * Throws IllegalStateException if file with words not found.
     *
     * @param wordsPath Path to file with words.
     * @param log       Path to log file.
     */
    public Listener(Path wordsPath, Path log) {
        this.wordsPath = wordsPath;
        this.logPath = log;
        if (!wordsPath.toFile().exists()) {
            throw new IllegalStateException("Words file not found!");
        }
        this.fileIo = new FileIO(wordsPath, logPath);
        stateMap = new HashMap<>();
        stateMap.put("continue", new Print(fileIo.getWords(this.wordsPath)));
        stateMap.put("stop", new Stop());
    }

    /**
     * Set state.
     *
     * @param state State.
     */

    private void setState(State state) {
        this.state = state;
    }

    /**
     * Reads console input and prints random words.
     * If input equals stop, than stop printing.
     * If input equals continue, than start printing.
     * If input equals end, than exit from loop.
     * Writes dialog to log.
     */
    public void start() {
        Scanner scanner = new Scanner(System.in);
        StringBuilder line = new StringBuilder();
        log = new StringBuilder();
        state = new Print(fileIo.getWords(wordsPath));
        while (scanner.hasNext()) {
            line.append(scanner.next());
            log.append("User: ").append(line).append(System.lineSeparator());
            if ("end".equals(line.toString())) {
                log.append("Program: ").append("EXIT").append(System.lineSeparator());
                System.out.println("Exiting");
                fileIo.writeToLog(log);
                break;
            }
            changeState(line.toString());
            execute();
            line.setLength(0);
        }
    }

    /**
     * Changes state by special words.
     *
     * @param string String.
     */
    public void changeState(String string) {
        setState(this.stateMap.getOrDefault(string, this.state));
    }

    /**
     * Execute print() method from current state.
     * Writes to log state has changed.
     */
    public void execute() {
        if (state.print() != null) {
            String s = state.print();
            log.append("Program: ").append(s).append(System.lineSeparator());
            System.out.println(s);
        }
    }
}
