package ru.job4j.grabber;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * SqlRuParse.
 * Class for parsing site sql.ru.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 25.03.2021.
 */
public class SqlRuParse implements Parse {
    private final String link = "https://www.sql.ru/forum/job-offers";
    private final DateTimeParser dateTimeParser = new SqlRuDateTimeParser();

    public String getLink() {
        return this.link;
    }

    /**
     * Gets list of posts from site.
     *
     * @param link String.
     * @return List of posts.
     */
    @Override
    public List<Post> list(String link) {
        List<Post> postList = new ArrayList<>();
        try {
            for (int index = 1; index <= 5; index++) {
                String pageLink = link + "/" + index;
                Document doc = Jsoup.connect(pageLink).get();
                Elements row = doc.select(".postslisttopic");
                for (Element td : row) {
                    Element href = td.child(0);
                    String url = href.attr("href");
                    Post post = detail(url);
                    if (post != null) {
                        System.out.println(post);
                        postList.add(post);
                    }
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return postList;
    }

    /**
     * Gets post details and returns Post object.
     *
     * @param link Link.
     * @return Post
     */
    @Override
    public Post detail(String link) {
        Post post = new Post();
        if (!link.isEmpty()) {
            try {
                Document document = Jsoup.connect(link).get();
                Element element = document.select(".msgTable").first();
                String name = element.select(".messageHeader").text();
                String text = element.select(".msgBody").next().text();
                if (name.toLowerCase().contains("java") || text.toLowerCase().contains("java")) {
                    post.setName(name);
                    post.setLink(link);
                    post.setText(text);
                    String date = element.selectFirst(".msgFooter").ownText();
                    post.setTime(dateTimeParser.parse(date));
                } else {
                    post = null;
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        return post;
    }
}
