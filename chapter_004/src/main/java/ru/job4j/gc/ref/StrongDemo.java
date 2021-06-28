package ru.job4j.gc.ref;

import java.util.concurrent.TimeUnit;

/**
 * StrongDemo.
 * Strong references example class.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 28.06.2021.
 */
public class StrongDemo {
    /**
     * Main method.
     * @param args String args.
     * @throws InterruptedException Exception.
     */
    public static void main(String[] args) throws InterruptedException {
        //example1();
        example2();
    }

    /**
     * Example when strong references becomes null, than objects will be collected by gc.
     * @throws InterruptedException Exception.
     */
    private static void example1() throws InterruptedException {
        Object[] objects = new Object[100];
        for (int i = 0; i < 100; i++) {
            objects[i] = new Object() {
                @Override
                protected void finalize() throws Throwable {
                    System.out.println("Object removed!");
                }
            };
        }
        for (int i = 0; i < 100; i++) {
            objects[i] = null;
        }
        System.gc();
        TimeUnit.SECONDS.sleep(5);
    }

    /**
     * Example when strong references becomes null with inner objects, than inner will be collected too.
     * @throws InterruptedException
     */
    private static void example2() throws InterruptedException {
        Object[] objects = new Object[100];
        for (int i = 0; i < 100; i++) {
            Object object = new Object() {
                Object innerObject = new Object() {
                    @Override
                    protected void finalize() throws Throwable {
                        System.out.println("Remove inner object!");
                    }
                };
            };
            objects[i] = object;
        }
        for (int i = 0; i < 100; i++) {
            objects[i] = null;
        }
        System.gc();
        TimeUnit.SECONDS.sleep(5);
    }
}
