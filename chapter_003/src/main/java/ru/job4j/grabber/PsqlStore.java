package ru.job4j.grabber;

import java.sql.*;
import java.time.Instant;
import java.util.*;

/**
 * PsqlStore.
 * DAO for PostgreSQL database.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 17.02.2021.
 */
public class PsqlStore implements Store, AutoCloseable {
    private Connection cnn;

    /**
     * Constructor.
     *
     * @param cfg Config for DB connection.
     */
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
     * If try to insert duplicate unique value (link) does nothing.
     *
     * @param post Post.
     */
    @Override
    public void save(Post post) {
        try (PreparedStatement preparedStatement = cnn.prepareStatement(
                "insert into post (name, text, link, created) values (?, ?, ?, ?) ON CONFLICT DO NOTHING")
        ) {
            System.out.println("try to write to db");
            preparedStatement.setString(1, post.getName());
            preparedStatement.setString(2, post.getText());
            preparedStatement.setString(3, post.getLink());
            preparedStatement.setObject(4, Timestamp.from(post.getTime()));
            preparedStatement.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    /**
     * Returns list of all posts from DB.
     *
     * @return List.
     */
    @Override
    public List<Post> getAll() {
        List<Post> list = new ArrayList<>();
        try (Statement statement = cnn.createStatement()) {
            ResultSet rs = statement.executeQuery("select * from post");
            while (rs.next()) {
                Post post = new Post();
                post.setId(rs.getInt("ID"));
                post.setName(rs.getString("NAME"));
                post.setText(rs.getString("TEXT"));
                post.setLink(rs.getString("LINK"));
                post.setTime(rs.getObject("CREATED", Timestamp.class).toInstant());
                list.add(post);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return list;
    }

    /**
     * Find post by ID from DB.
     *
     * @param id Post id.
     * @return Post.
     */
    @Override
    public Post findById(String id) {
        Post post = null;
        Integer intId = Integer.parseInt(id);
        System.out.println(intId);
        if (intId != 0) {
            try (Statement statement = cnn.createStatement()) {
                ResultSet rs = statement.executeQuery("SELECT * FROM post WHERE id = " + "'" + id + "'");
                rs.next();
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

    /**
     * Close
     *
     * @throws Exception Exception.
     */
    @Override
    public void close() throws Exception {
        if (cnn != null) {
            cnn.close();
        }
    }

    /**
     * Write parse time to DB
     */
    @Override
    public void saveParseTime() {
        //String saveTimestamp = "insert into parse_time (parse_time) values (?)";
        try (PreparedStatement preparedStatement = cnn.prepareStatement(
                "insert into parse_time (parse_timestamp) values (?)")
        ) {
            preparedStatement.setTimestamp(1, Timestamp.from(Instant.now()));
            preparedStatement.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    /**
     * Return last parse date from DB.
     *
     * @return Instant.
     */
    @Override
    public Instant getLastParseTime() {
        Instant time = null;
        try (Statement statement = cnn.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT max(parse_timestamp) as last_parse_date from parse_time");
            if (resultSet.next()) {
                Timestamp ts = resultSet.getObject("last_parse_date", Timestamp.class);
                if (ts != null) {
                    time = ts.toInstant();
                }
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return time;
    }
}
