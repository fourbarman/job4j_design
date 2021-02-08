package ru.job4j.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

/**
 * AlertRabbit.
 * Initialize quartz class and print string with interval from rabbit.properties file.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 08.02.2021.
 */
public class AlertRabbit {
    /**
     * Main method.
     *
     * @param args Arguments.
     */
    public static void main(String[] args) {
        String pathToProp = System.getProperty("user.dir") + "/chapter_003/src/main/java/ru/job4j/quartz/rabbit.properties";
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            JobDetail job = newJob(Rabbit.class).build();
            SimpleScheduleBuilder times = simpleSchedule()
                    .withIntervalInSeconds(getInterval(pathToProp))
                    .repeatForever();
            Trigger trigger = newTrigger()
                    .startNow()
                    .withSchedule(times)
                    .build();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }

    /**
     * Returns property from file.
     *
     * @param path String path to file.
     * @return Int interval.
     */
    private static int getInterval(String path) {
        int interval = 0;
        try (InputStream input = new FileInputStream(path)) {
            Properties prop = new Properties();
            prop.load(input);
            interval = Integer.parseInt(prop.getProperty("rabbit.interval"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return interval;
    }

    /**
     * Rabbit
     */
    public static class Rabbit implements Job {
        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            System.out.println("Rabbit runs here ...");
        }
    }

}
