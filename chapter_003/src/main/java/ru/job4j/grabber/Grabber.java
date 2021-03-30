package ru.job4j.grabber;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Grabber.
 * Parses www.sql.ru for getting job advertisements.
 * When started, parses page with interval from app.properties.
 * Runs first 5 pages.
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
        }
    }

    /**
     * Init parameters for Scheduler.
     * Job starts with time and interval options from app.properties.
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
                .withRepeatCount(Integer.parseInt(cfg.getProperty("time")))
                .withIntervalInSeconds(Integer.parseInt(cfg.getProperty("interval")));
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
         * Starts parse with parse object and stores parsed information to store object.
         *
         * @param context Context.
         * @throws JobExecutionException JobExecutionException.
         */
        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            JobDataMap map = context.getJobDetail().getJobDataMap();
            Store store = (Store) map.get("store");
            Parse parse = (Parse) map.get("parse");
            for (Post post : parse.list(parse.getLink())) {
                store.save(post);
            }
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
     * Uses cp1251.
     *
     * @param store Store object.
     */
    public void web(Store store) {
        new Thread(() -> {
            try (ServerSocket server = new ServerSocket(Integer.parseInt(cfg.getProperty("port")))) {
                while (!server.isClosed()) {
                    Socket socket = server.accept();
                    try (OutputStreamWriter out = (new OutputStreamWriter(socket.getOutputStream(), "cp1251"))) {
                        out.write("HTTP/1.1 200 OK\r\n\r\n");
                        for (Post post : store.getAll()) {
                            out.write(post.toString());
                            out.write(System.lineSeparator());
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
