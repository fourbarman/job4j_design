package ru.job4j.io.chat;

import java.util.Scanner;

public class ConsoleInput implements Input {
    Scanner scanner;

    public ConsoleInput(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public boolean hasNext() {
        return this.scanner.hasNext();
    }

    @Override
    public String next() {
        return this.scanner.next();
    }
}
