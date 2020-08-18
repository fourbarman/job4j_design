package ru.job4j.io;

/**
 * ArgZip.
 * Validate args.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 17.08.2020.
 */
public class ArgZip {
    private final String[] args;

    public ArgZip(String[] args) {
        this.args = args;
    }

    /**
     * Returns true if 3 parameters are given.
     * Else throws ISException.
     *
     * @return result.
     */
    public boolean valid() {
        if (args.length != 3) {
            throw new IllegalStateException("Not enough parameters!"
                    + "\n Example: -d=C:\\projects\\job4j_design\\chapter_001\\ -e=class -o=project.zip");
        }
        return true;
    }

    /**
     * Returns directory path from args.
     *
     * @return String.
     */
    public String directory() {
        return args[0].split("=")[1];
    }

    /**
     * Returns exclude extension from args.
     *
     * @return String.
     */
    public String exclude() {
        return args[1].split("=")[1];
    }

    /**
     * Returns output filename from args.
     *
     * @return String.
     */
    public String output() {
        return args[2].split("=")[1];
    }
}
