package ru.job4j.grabber;

import org.quartz.*;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.StdSchedulerFactory;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Properties;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class Grabber implements Grab {
    //    private final Properties cfg = new Properties();
//
//    @Override
//    public void init(Parse parse, Store store) {
//    }
//
//    public static void main(String[] args) {
//        Grabber grabber = new Grabber();
//        grabber.getCfg();
//        PsqlStore psqlStore = new PsqlStore(grabber.cfg);
//        Parse parse = new SqlRuParse();
//        Instant lastParseDate = psqlStore.getLastParseTime(); //last parse date from DB
//        //передаем ссылку и дату последнего парса
//        for (Post post : parse.list("https://www.sql.ru/forum/job-offers", lastParseDate)) {
//            psqlStore.save(post);
//        }
//        psqlStore.saveParseTime(); //пишем в БД дату текущего парса
//    }
//
//    /**
//     * Return config from app.properties
//     */
//    public void getCfg() {
//        String executionPath = System.getProperty("user.dir");
//        Path confPath = Paths.get(executionPath + "/chapter_003/src/main/java/ru/job4j/grabber/db/app.properties");
//        try (FileInputStream in = new FileInputStream(confPath.toFile())) {
//            cfg.load(in);
//        } catch (IOException ioException) {
//            ioException.printStackTrace();
//        }
//    }
    private final Properties cfg = new Properties();
    //Properties cfg = new Properties();

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
        System.out.println("job detail created");
        SimpleScheduleBuilder times = simpleSchedule()
                .withIntervalInSeconds(Integer.parseInt(cfg.getProperty("jdbc.time")))
                .repeatForever();
        System.out.println("scheduler created");
        Trigger trigger = newTrigger()
                .startNow()
                .withSchedule(times)
                .build();
        System.out.println("trigger created");
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
            JobDataMap map = context.getJobDetail().getJobDataMap();
            Store store = (Store) map.get("store");
            Parse parse = (Parse) map.get("parse");
            System.out.println("I'm JOB!");

            Instant lastParseDate = store.getLastParseTime(); //last parse date from DB
            System.out.println(lastParseDate);
            //передаем ссылку и дату последнего парса
            for (Post post : parse.list("https://www.sql.ru/forum/job-offers", lastParseDate)) {
                store.save(post);
            }
            store.saveParseTime(); //пишем в БД дату текущего парса
            System.out.println("Job done!");
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
    }
}
