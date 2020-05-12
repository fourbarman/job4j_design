package ru.job4j.generics;

/**
 * Base.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 11.05.2020.
 */
public abstract class Base {
    private final String id;

    /**
     * Constructor
     *
     * @param id String id.
     */
    protected Base(final String id) {
        this.id = id;
    }

    /**
     * Return id.
     *
     * @return id.
     */
    public String getId() {
        return id;
    }
}
