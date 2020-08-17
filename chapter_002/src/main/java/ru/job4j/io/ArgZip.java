package ru.job4j.io;

import java.io.File;

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

    public boolean valid() {
        if (args.length != 3) {
            throw new IllegalStateException("Not enough parameters!" +
                    "\n Example: -d=C:\\projects\\job4j_design\\chapter_001\\ -e=class -o=project.zip");
        }
//        if (!args[0].startsWith("-d=")) {
//            throw new IllegalStateException("Wrong \"directory\" parameter");
//        }
//        if (!args[1].startsWith("-e=")) {
//            throw new IllegalStateException("Wrong \"exclude\" parameter");
//        }
//        if (!args[2].startsWith("-o=")) {
//            throw new IllegalStateException("Wrong \"output\" parameter");
//        }
//        File dir = new File(args[0].split("=")[1]);
//        if (!dir.exists()) {
//            throw new IllegalStateException("Directory " + dir.getAbsolutePath() + " doesn't exist!");
//        }
//        if (!dir.isDirectory()) {
//            throw new IllegalStateException("Wrong \" not a directory \" parameter ");
//        }
        return true;
    }

    public String directory() {
        return args[0].split("=")[1];
    }

    public String exclude() {
        return args[1].split("=")[1];
    }

    public String output() {
        return args[2].split("=")[1];
    }
}
