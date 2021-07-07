package ru.job4j.cache;

/**
 * Emulator.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 07.07.2021.
 */
public class Emulator {
    static DirFileCache dirFileCache;

    /**
     * Main method.
     * First, load menu entries to menu.
     * Than run menu.
     *
     * @param args String args.
     */
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.addEntry(new MenuEntry("Choose directory") {
            @Override
            public void run() {
                System.out.println("Enter directory path");
                dirFileCache = new DirFileCache(menu.getAnswer());
                System.out.println("Dir set to: " + dirFileCache.getCachingDir());
            }
        });
        menu.addEntry(new MenuEntry("Add file") {
            @Override
            public void run() {
                if (dirFileCache != null) {
                    System.out.println("Enter file name");
                    String file = menu.getAnswer();
                    dirFileCache.load(file);
                    System.out.println("Added " + file);
                } else {
                    System.out.println("Please, provide caching directory first!");
                }
            }
        });
        menu.addEntry(new MenuEntry("View text from file") {
            @Override
            public void run() {
                if (dirFileCache != null) {
                    System.out.println("Enter file name to view text");
                    String file = menu.getAnswer();
                    System.out.println("Text from file: ");
                    System.out.println(dirFileCache.get(file));
                } else {
                    System.out.println("Please, provide caching directory first!");
                }
            }
        });
        menu.run();
    }

}
