package ru.job4j.grabber;

import org.jsoup.nodes.Document;

import java.time.Instant;
import java.util.List;

/**
 * Parse.
 * Interface for parsing data (Post) from http.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 17.02.2021.
 */
public interface Parse {
    /**
     * Gets list from http page.
     * I.e. "https://www.sql.ru/forum/job-offers/1"
     *
     * @param list String list.
     * @return List.
     */
    List<Post> list(String list);

    /**
     * Gets details from posts.
     * I.e. "https://www.sql.ru/forum/1323839/razrabotchik-java-g-kazan".
     *
     * @param link Link.
     * @return Post.
     */
    Post detail(String link);

    /**
     * Return link.
     */
    String getLink();
}
