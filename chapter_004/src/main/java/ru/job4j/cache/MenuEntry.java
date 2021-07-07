package ru.job4j.cache;

/**
 * MenuEntry.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 07.07.2021.
 */
public abstract class MenuEntry {
    private final String name;

    /**
     * Constructor.
     *
     * @param name Entry name.
     */
    public MenuEntry(String name) {
        this.name = name;
    }

    /**
     * Returns entry name.
     *
     * @return name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Method to run menu entry.
     */
    public void run() {
    }
}
