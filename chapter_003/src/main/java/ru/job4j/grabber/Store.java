package ru.job4j.grabber;

import java.time.Instant;
import java.util.List;

/**
 * Store.
 * Interface for storing and retrieving data (Post) from storage.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 17.02.2021.
 */
public interface Store {
    /**
     * Save Post to storage.
     *
     * @param post Post.
     */
    void save(Post post);

    /**
     * Returns all List objects from storage.
     *
     * @return List of Post objects.
     */
    List<Post> getAll();

    /**
     * Returns Post object by it's id.
     *
     * @param id Post id.
     * @return Post.
     */
    Post findById(String id);
    /**
     * Write parse timestamp to storage.
     */
    void saveParseTime();
    /**
     * Get last parse time.
     */
    Instant getLastParseTime();
}
