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

    /**
     * Save Post fields to database.
     * Throws exception if try to insert duplicate unique value (link).
     * @param post Post.
     */

    @Override
    public void save(Post post) {
        try (PreparedStatement preparedStatement = cnn.prepareStatement(
                "insert into post (name, text, link, created) values (?, ?, ?, ?)")
        ) {
            System.out.println("try to write to db");
            preparedStatement.setString(1, post.getName());
            preparedStatement.setString(2, post.getText());
            preparedStatement.setString(3, post.getLink());
            preparedStatement.setObject(4, Timestamp.from(post.getTime()));
            preparedStatement.execute();
        } catch (org.postgresql.util.PSQLException psqlException) {
            psqlException.getMessage();
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
}
