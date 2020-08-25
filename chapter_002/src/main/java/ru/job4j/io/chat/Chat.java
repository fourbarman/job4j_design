package ru.job4j.io.chat;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Chat.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 25.08.2020.
 */
public class Chat {
    Listener listener;

    /**
     * Constructor.
     * @param listener Listener.
     */
    public Chat(Listener listener) {
        this.listener = listener;
    }

    /**
     * Start listener method.
     */
    public void start() {
        listener.start();
    }
    /**
     * Main method.
     *
     * @param args args.
     */
    public static void main(String[] args) {
        Path words = Paths.get("words.txt");
        Path log = Paths.get("log.txt");
        Chat chat = new Chat(new Listener(words, log));
        chat.start();
    }
}
