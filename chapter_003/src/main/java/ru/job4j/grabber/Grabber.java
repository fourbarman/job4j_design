package ru.job4j.grabber;

import org.quartz.Scheduler;
import org.quartz.impl.StdScheduler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Grabber implements Grab{
    private final Properties cfg = new Properties();
    @Override
    public void init(Parse parse, Store store) {

    }

    public static void main(String[] args) {
        //Check get properties from app.properties
        Grabber grabber = new Grabber();
        grabber.getCfg();
        System.out.println(grabber.cfg.getProperty("jdbc.driver"));
        System.out.println(grabber.cfg.getProperty("jdbc.url"));
        System.out.println(grabber.cfg.getProperty("jdbc.username"));
        System.out.println(grabber.cfg.getProperty("jdbc.password"));
        //Try to write to DB
        PsqlStore psqlStore = new PsqlStore(grabber.cfg);
        Parse parse = new SqlRuParse();
        for (Post post : parse.list("https://www.sql.ru/forum/job-offers")) {
            System.out.println("Name: " + post.getName());
            System.out.println("Link: " + post.getLink());
            System.out.println("Text: " + post.getText());
            System.out.println("Time: " + post.getTime());
            System.out.println("--------------------");
            psqlStore.save(post);
        }
        System.out.println("Job done!");
    }

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
