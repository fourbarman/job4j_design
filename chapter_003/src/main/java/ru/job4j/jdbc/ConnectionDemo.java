package ru.job4j.jdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Analizy.
 * Analise log file for downtime and writes information to .csv file.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 30.07.2020.
 */
public class ConnectionDemo {
    /**
     * Returns map with connection properties to database.
     *
     * @param credPath String path to properties file.
     * @return Map with credentials.
     */
    private Map<String, String> getConnection(String credPath) {
        Map<String, String> cred = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(credPath))) {
            List<String> lines = br.lines().collect(Collectors.toList());
            for (String s : lines) {
                if (s.contains("=")) {
                    String[] arr = s.split("=");
                    cred.put(arr[0], arr[1]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cred;
    }

    /**
     * Main method.
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String path = "./app.properties";
        Map<String, String> cred = new ConnectionDemo().getConnection(path);
        Class.forName("org.postgresql.Driver");
        String url = cred.get("url");
        String login = cred.get("user");
        String password = cred.get("password");
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
