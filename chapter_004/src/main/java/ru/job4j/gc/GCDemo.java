package ru.job4j.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * GCDemo.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 26.05.2021.
 */
public class GCDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(20000);
        for (int i = 0; i < 1000; i++) {
            new User(i, i, "" + i);
        }
    }

    /**
     * User.
     * Example class for calculate memory usage.
     * Empty object User consume 24 byte:
     * 12 byte - object header;
     * 4 byte - int reference age;
     * 4 byte - int reference phone;
     * 4 byte - String reference name;
     * <p>
     * Created instance will consume:
     * 24 byte - object shallow;
     * 16 byte - class reference;
     * 4 byte - int value age;
     * 4 byte - int value phone;
     * 24 byte minimum - String name;
     * Total memory = 64 byte min;
     * In our example Total = 72 byte;
     */
    public static class User {
        int age;
        int phone;
        String name;

        public User(int age, int phone, String name) {
            this.age = age;
            this.phone = phone;
            this.name = name;
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println(this.name);
        }
    }
}


