package ru.job4j.io;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ArgZip {
    private final String[] args;

    public ArgZip(String[] args) {
        this.args = args;
    }

    public boolean valid() {
        if (args.length != 3
            ) {
            throw new IllegalStateException("Not enough parameters!");
        }
        if (!args[0].startsWith("-d=")
        ) {
            throw new IllegalStateException("Wrong \"directory\" parameter");
        }
        if (!args[1].startsWith("-e=")
        ) {
            throw new IllegalStateException("Wrong \"exclude\" parameter");
        }
        if (!args[2].startsWith("-o=")
        ) {
            throw new IllegalStateException("Wrong \"output\" parameter");
        }
        File dir = new File(args[0].split("=")[1]);
        if (!dir.exists()) {
            throw new IllegalStateException("Directory " + dir.getAbsolutePath() + "doesn't exist!");
        }
        if (!dir.isDirectory())
        {
            throw new IllegalStateException("Wrong \" not a directory \" parameter ");
        }
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
