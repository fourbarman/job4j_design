package ru.job4j.grabber;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Instant;
import java.util.Properties;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Grabber.
 * Parses www.sql.ru for getting job advertisements.
 * When started, parses page with interval from app.properties.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 24.03.2021.
 */
public class Grabber implements Grab {

    private final Properties cfg = new Properties();

    public Store store() {
        return new PsqlStore(cfg);
    }

    /**
     * Scheduler fabric
     *
     * @return Scheduler.
     * @throws SchedulerException SchedulerException.
     */
    public Scheduler scheduler() throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        return scheduler;
    }

    /**
     * Load config for DB connection.
     *
     * @throws IOException IOException.
     */
    public void cfg() throws IOException {
        String s = System.getProperty("user.dir");
        try (InputStream in = new FileInputStream(s + "/chapter_003/src/main/java/ru/job4j/grabber/db/app.properties")) {
            this.cfg.load(in);
            System.out.println("cfg loaded");
        }
    }

    /**
     * Init parameters for Scheduler.
     * Job starts with jdbc.time from app.properties
     *
     * @param parse     Parse object.
     * @param store     Store object.
     * @param scheduler Scheduler object.
     * @throws SchedulerException SchedulerException.
     */
    @Override
    public void init(Parse parse, Store store, Scheduler scheduler) throws SchedulerException {
        JobDataMap data = new JobDataMap();
        data.put("store", store);
        data.put("parse", parse);
        JobDetail job = newJob(GrabJob.class)
                .usingJobData(data)
                .build();
        SimpleScheduleBuilder times = simpleSchedule()
                .withRepeatCount(5)
                .withIntervalInSeconds(Integer.parseInt(cfg.getProperty("jdbc.time")));
        Trigger trigger = newTrigger()
                .startNow()
                .withSchedule(times)
                .build();
        scheduler.scheduleJob(job, trigger);
    }

    /**
     * GrabJob.
     */
    public static class GrabJob implements Job {

        /**
         * Execute job.
         *
         * @param context Context.
         * @throws JobExecutionException JobExecutionException.
         */
        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            System.out.println("Job started");
            JobDataMap map = context.getJobDetail().getJobDataMap();
            Store store = (Store) map.get("store");
            Parse parse = (Parse) map.get("parse");
            Instant lastParseDate = store.getLastParseTime();
            for (Post post : parse.list(parse.getLink(), lastParseDate)) {
                store.save(post);
            }
            store.saveParseTime();
        }
    }

    /**
     * Main method.
     *
     * @param args Arguments.
     * @throws Exception Exception.
     */
    public static void main(String[] args) throws Exception {
        Grabber grab = new Grabber();
        grab.cfg();
        Scheduler scheduler = grab.scheduler();
        Store store = grab.store();
        grab.init(new SqlRuParse(), store, scheduler);
        grab.web(store);
    }

    /**
     * Starts server and returns all rows from DB.
     *
     * @param store Store object.
     */
    public void web(Store store) {
        new Thread(() -> {
            try (ServerSocket server = new ServerSocket(Integer.parseInt(cfg.getProperty("port")))) {
                System.out.println("Thread started");
                while (!server.isClosed()) {
                    Socket socket = server.accept();
                    try (OutputStream out = socket.getOutputStream()) {
                        out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                        for (Post post : store.getAll()) {
                            out.write(post.toString().getBytes());
                            out.write(System.lineSeparator().getBytes());
                        }
                    } catch (IOException io) {
                        io.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
