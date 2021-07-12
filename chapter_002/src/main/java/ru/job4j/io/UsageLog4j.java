package ru.job4j.io;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        byte b = 16;
        short s = 32;
        int i = 33;
        long l = 922337203;
        double d = 244.41234;
        float f = 145.45F;
        boolean bool = true;
        char ch = 'G';

        LOG.debug("byte : {}, "
                + "short : {}, "
                + "int : {}, "
                + "long : {}, "
                + "double : {}, "
                + "float : {}, "
                + "boolean : {}, "
                + "char : {}", b, s, i, l, d, f, bool, ch);
    }
}
