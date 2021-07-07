package ru.job4j.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Menu.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 07.07.2021.
 */
public class Menu {
    Scanner sc = new Scanner(System.in);
    private final List<MenuEntry> entries = new ArrayList<>();
    private boolean isRun = true;

    /**
     * Constructor.
     * Adds Exit option.
     */
    public Menu() {
        entries.add(new MenuEntry("Exit") {
            @Override
            public void run() {
                System.out.println("Exiting...");
                isRun = false;
            }
        });
    }

    /**
     * Adds menu entry to entries list.
     *
     * @param menuEntry Menu entry.
     */
    public void addEntry(MenuEntry menuEntry) {
        entries.add(menuEntry);
    }

    /**
     * Prints menu to console.
     */
    public void printMenu() {
        System.out.println("###############################");
        System.out.println("Hello! Choose option from menu:");
        for (int i = 0; i < entries.size(); i++) {
            System.out.println(i + ". " + entries.get(i).getName());
        }
    }

    /**
     * Executes menu while isRun.
     */
    public void run() {
        while (isRun) {
            printMenu();
            int option = -1;
            String answer = getAnswer();
            if (answer.matches("\\d")) {
                option = Integer.parseInt(answer);
                if (option < 0 || option > entries.size()) {
                    System.out.println("Please, provide correct number from 0 to " + (entries.size() - 1));
                } else {
                    entries.get(option).run();
                }
            } else {
                System.out.println("Enter number!");
            }
        }
    }

    /**
     * Returns console input.
     *
     * @return Scanner next line.
     */
    public String getAnswer() {
        System.out.print(":>");
        return sc.nextLine();
    }
}
