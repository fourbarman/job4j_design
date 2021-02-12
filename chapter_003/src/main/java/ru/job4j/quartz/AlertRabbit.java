package ru.job4j.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.FileInputStream;
import java.io.InputStream;
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
 * @since 12.02.2021.
 */
public class AlertRabbit {
    static Connection connection;

    public static void main(String[] args) {
        try {
            String pathToProp = System.getProperty("user.dir") + "/chapter_003/src/main/java/ru/job4j/quartz/rabbit.properties";
            AlertRabbit alertRabbit = new AlertRabbit();
            alertRabbit.init(pathToProp);
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            JobDataMap data = new JobDataMap();
            data.put("store", connection);
            JobDetail job = newJob(Rabbit.class)
                    .usingJobData(data)
                    .build();
            SimpleScheduleBuilder times = simpleSchedule()
                    .withIntervalInSeconds(5)
                    .repeatForever();
            Trigger trigger = newTrigger()
                    .startNow()
                    .withSchedule(times)
                    .build();
            scheduler.scheduleJob(job, trigger);
            Thread.sleep(10000);
            scheduler.shutdown();
        } catch (Exception se) {
            se.printStackTrace();
        }

    }

    /**
     * Connect to DB and retrieve Connection instance and interval.
     */
    public void init(String path) {
        try (InputStream input = new FileInputStream(path)) {
            Properties config = new Properties();
            config.load(input);
            Class.forName(config.getProperty("jdbc.driver"));
            connection = DriverManager.getConnection(
                    config.getProperty("jdbc.url"),
                    config.getProperty("jdbc.username"),
                    config.getProperty("jdbc.password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public static class Rabbit implements Job {

        public Rabbit() {
            System.out.println(hashCode());
        }

        @Override
        public void execute(JobExecutionContext context) {
            try {
                this.saveTime(connection);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        public void saveTime(Connection cnt) throws SQLException {
            try (PreparedStatement ps = cnt.prepareStatement("INSERT INTO rabbit (created_date) VALUES (?)")) {
                LocalDateTime timeStamp = LocalDateTime.now();
                ps.setObject(1, timeStamp);
                ps.execute();
            }
        }
    }
}
