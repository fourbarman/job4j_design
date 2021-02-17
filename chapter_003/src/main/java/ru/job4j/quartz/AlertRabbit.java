package ru.job4j.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Properties;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

/**
 * AlertRabbit.
 * Initialize quartz class and stores timestamp to DB.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 16.02.2021.
 */
public class AlertRabbit {

    /**
     * Main method.
     * Write timestamp to DB using quartz library.
     *
     * @param args Args.
     */
    public static void main(String[] args) {
        AlertRabbit alertRabbit = new AlertRabbit();
        Properties props = alertRabbit.getPropsTest();
        try (Connection con = DriverManager.getConnection(
                props.getProperty("jdbc.url"),
                props.getProperty("jdbc.username"),
                props.getProperty("jdbc.password"))
        ) {
            int interval = Integer.parseInt(props.getProperty("rabbit.interval"));
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            JobDataMap data = new JobDataMap();
            data.put("store", con);
            JobDetail job = newJob(Rabbit.class)
                    .usingJobData(data)
                    .build();
            SimpleScheduleBuilder times = simpleSchedule()
                    .withIntervalInSeconds(interval)
                    .repeatForever();
            Trigger trigger = newTrigger()
                    .startNow()
                    .withSchedule(times)
                    .build();
            scheduler.scheduleJob(job, trigger);
            Thread.sleep(10000);
            scheduler.shutdown();
        } catch (SQLException | SchedulerException | InterruptedException sqlException) {
            sqlException.printStackTrace();
        }
    }

    /**
     * Get Properties from file.
     *
     * @return props Properties.
     */
    public Properties getPropsTest() {
        Properties props = new Properties();
        try (FileInputStream in = new FileInputStream(
                System.getProperty("user.dir") + "/chapter_003/src/main/java/ru/job4j/quartz/rabbit.properties"
        )) {
            props.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

    /**
     * Rabbit.
     */
    public static class Rabbit implements Job {

        /**
         * Execute job.
         *
         * @param context Context.
         */
        @Override
        public void execute(JobExecutionContext context) {
            try {
                Connection con = (Connection) context.getJobDetail().getJobDataMap().get("store");
                this.saveTime(con);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        /**
         * Load timestamp to DB.
         *
         * @param cnt Connection.
         */
        public void saveTime(Connection cnt) throws SQLException {
            try (PreparedStatement ps = cnt.prepareStatement("INSERT INTO rabbit (created_date) VALUES (?)")) {
                LocalDateTime timeStamp = LocalDateTime.now();
                ps.setObject(1, timeStamp);
                ps.execute();
            }
        }
    }
}
