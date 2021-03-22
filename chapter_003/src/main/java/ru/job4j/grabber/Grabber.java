package ru.job4j.grabber;

import org.quartz.Scheduler;
import org.quartz.impl.StdScheduler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Properties;

public class Grabber implements Grab{
    private final Properties cfg = new Properties();
    @Override
    public void init(Parse parse, Store store) {
    }

    public static void main(String[] args) {
        Instant start = Instant.now();
        //Check get properties from app.properties
        Grabber grabber = new Grabber();
        grabber.getCfg();
//        System.out.println(grabber.cfg.getProperty("jdbc.driver"));
//        System.out.println(grabber.cfg.getProperty("jdbc.url"));
//        System.out.println(grabber.cfg.getProperty("jdbc.username"));
//        System.out.println(grabber.cfg.getProperty("jdbc.password"));
        //Try to write to DB
        PsqlStore psqlStore = new PsqlStore(grabber.cfg);
        Parse parse = new SqlRuParse();
        Instant lastParseDate = psqlStore.getLastParseTime(); //last parse date from DB
        //передаем ссылку и дату последнего парса
        for (Post post : parse.list("https://www.sql.ru/forum/job-offers", lastParseDate)) {
//            System.out.println("Name: " + post.getName());
//            System.out.println("Link: " + post.getLink());
//            System.out.println("Text: " + post.getText());
//            System.out.println("Time: " + post.getTime());
//            System.out.println("--------------------");
            psqlStore.save(post);
        }
        psqlStore.saveParseTime();
        //System.out.println(lastParseDate);
        //проверка продолжительности парса
        Instant stop = Instant.now();
        System.out.println("Job done!");
        System.out.println("Elapsed time: " + (stop.getEpochSecond() - start.getEpochSecond()));
    }

    /**
     * Return config from app.properties
     */
    public void getCfg() {
        String executionPath = System.getProperty("user.dir");
        Path confPath = Paths.get(executionPath + "/chapter_003/src/main/java/ru/job4j/grabber/db/app.properties");
        try (FileInputStream in = new FileInputStream(confPath.toFile())) {
            cfg.load(in);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
