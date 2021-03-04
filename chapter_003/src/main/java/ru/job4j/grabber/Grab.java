package ru.job4j.grabber;

import org.quartz.Scheduler;

/**
 * Grab.
 * Interface for parsing data (Post) from http with schedule.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 17.02.2021.
 */
public interface Grab {
    /**
     * Schedules parsing job.
     *
     * @param parse     Parse object.
     * @param store     Store object.
     */
    void init(Parse parse, Store store);
    //void init(Parse parse, Store store, Scheduler scheduler);
}
