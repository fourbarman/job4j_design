package ru.job4j.spammer;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * ImportDB.
 * Takes source file and transfer data to DB.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 13.01.2021.
 */
public class ImportDB {
    private Properties cfg;
    private String dump;

    /**
     * Constructor.
     */
    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    /**
     * Returns List of lines from file.
     *
     * @return List.
     */
    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            rd.lines().forEach(s -> {
                String[] temp = s.split(";");
                if (temp.length == 2) {
                    users.add(new User(temp[0], temp[1]));
                }
            });
        }
        return users;
    }

    /**
     * Connect to DB.
     * Insert data from list to DB.
     *
     * @param users List to store in DB.
     */
    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement("INSERT INTO users (name, email) VALUES (?, ?)")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    /**
     * User.
     */
    private static class User {
        String name;
        String email;

        /**
         * Constructor.
         */
        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    /**
     * Main method.
     * Takes source file and transfer data to DB.
     *
     * @param args Arguments.
     */
    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        String executionPath = System.getProperty("user.dir");
        Path confPath = Paths.get(executionPath + "/chapter_003/src/main/java/ru/job4j/spammer/app.properties");
        Path dumpPath = Paths.get(executionPath + "/chapter_003/src/main/java/ru/job4j/spammer/dump.txt");
        try (FileInputStream in = new FileInputStream(confPath.toFile())) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, dumpPath.toString());
        db.save(db.load());
    }
}
