package ru.job4j.grabber;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class PsqlStore implements Store, AutoCloseable{
    private Connection cnn;

    public PsqlStore(Properties cfg) {
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        try {
            cnn = DriverManager.getConnection(
                    cfg.getProperty("jdbc.url"),
                    cfg.getProperty("jdbc.username"),
                    cfg.getProperty("jdbc.password"));
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public void save(Post post) {
        try (PreparedStatement preparedStatement = cnn.prepareStatement(
                "insert into post (name, text, link, created) values (?, ?, ?, ?)")
        ) {
            preparedStatement.setString(1, post.getName());
            preparedStatement.setString(2, post.getText());
            preparedStatement.setString(3, post.getLink());
            preparedStatement.setObject(4, Timestamp.from(post.getTime()));
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public List<Post> getAll() {
        List<Post> list = new ArrayList<>();
        try (Statement statement = cnn.createStatement()) {
            ResultSet rs = statement.executeQuery("select * from post");
            while (rs.next()) {
                Post post = new Post();
                post.setId(rs.getInt("ID"));
                post.setName(rs.getString("NAME"));
                post.setName(rs.getString("TEXT"));
                post.setName(rs.getString("LINK"));
                post.setTime(rs.getObject("CREATED", Timestamp.class).toInstant());
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return list;
    }

    @Override
    public Post findById(String id) {
        Post post = null;
        Integer intId = Integer.parseInt(id);
        if (intId != null) {
            try (Statement statement = cnn.createStatement()) {
                ResultSet rs = statement.executeQuery("SELECT * FROM post WHERE id = " + "'" + id + "'");
                post = new Post();
                post.setId(rs.getInt("ID"));
                post.setName(rs.getString("NAME"));
                post.setText(rs.getString("TEXT"));
                post.setLink(rs.getString("LINK"));
                post.setTime(rs.getObject("CREATED", Timestamp.class).toInstant());
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
        return post;
    }

    @Override
    public void close() throws Exception {
        if (cnn != null) {
            cnn.close();
        }
    }
    public Instant dateParse(String s) {
        Instant instant;
        try {
            System.out.println("Start string: " + s);
            String out = "";
            Map<String, String> map = new HashMap<>();
            map.put("янв", "01");
            map.put("фев", "02");
            map.put("мар", "03");
            map.put("апр", "04");
            map.put("май", "05");
            map.put("июн", "06");
            map.put("июл", "07");
            map.put("авг", "08");
            map.put("сен", "09");
            map.put("окт", "10");
            map.put("ноя", "11");
            map.put("дек", "12");
            String[] dateTime = s.split(",");
            if (Character.isDigit(dateTime[0].charAt(0))) {
                String[] date = dateTime[0].split(" ");
                date[1] = map.get(date[1]);
                if (date[0].length() < 2) {
                    date[0] = "0" + date[0];
                }
                dateTime[0] = "20" + date[2] + "-" + date[1] + "-" + date[0];
            } else if ("сегодня".equals(dateTime[0])) {
                dateTime[0] = LocalDate.now().toString();
            } else if ("вчера".equals(dateTime[0])) {
                dateTime[0] = LocalDate.now().minusDays(1).toString();
            }
            out = dateTime[0] + "," + dateTime[1];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");
            LocalDateTime localDateTime = LocalDateTime.parse(out, formatter);
            instant = localDateTime.toInstant(ZoneOffset.UTC);
        } catch (DateTimeParseException dateTimeParseException) {
            instant = Instant.now();
        }
        return instant;
    }
}
