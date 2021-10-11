package ru.job4j.gc.ref;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * WeakDemo.
 * Weak references example class.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 28.06.2021.
 */
public class WeakDemo {
    /**
     * Main method.
     *
     * @param args String args.
     */
    public static void main(String[] args) throws InterruptedException {
        example3();
    }

    /**
     * Example when we get Weak reference from strong reference.
     * Than strong reference becomes null.
     * GC collects weak referenced object.
     *
     * @throws InterruptedException Exception.
     */
    private static void example1() throws InterruptedException {
        Object object = new Object() {
            @Override
            protected void finalize() throws Throwable {
                System.out.println("Removed");
            }
        };
        WeakReference<Object> weak = new WeakReference<>(object);
        object = null;
        System.gc();
        TimeUnit.SECONDS.sleep(3);
        System.out.println(weak.get());
    }

    /**
     * Example when we get list of weak referenced objects.
     * After that gc collects all of them.
     *
     * @throws InterruptedException Exception.
     */
    private static void example2() throws InterruptedException {
        List<WeakReference<Object>> objects = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            objects.add(new WeakReference<Object>(new Object() {
                @Override
                protected void finalize() throws Throwable {
                    System.out.println("Removed!");
                }
            }));
        }
        System.gc();
        TimeUnit.SECONDS.sleep(3);
    }

    /**
     * Example when we create weak reference from strong and put it to reference queue.
     * After strong reference becomes null, gc will collect weak reference.
     * But we still can get collected reference from reference queue.
     *
     * @throws InterruptedException Exception.
     */
    private static void example3() throws InterruptedException {
        Object object = new Object() {
            @Override
            protected void finalize() throws Throwable {
                System.out.println("Removed");
            }
        };
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        WeakReference<Object> weak = new WeakReference<>(object, queue);
        object = null;

        System.gc();

        TimeUnit.SECONDS.sleep(3);
        System.out.println("from link " + weak.get());
        System.out.println("from queue " + queue.poll());
    }
}
