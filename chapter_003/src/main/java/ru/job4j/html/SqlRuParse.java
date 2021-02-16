package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * SqlRuParse.
 * Parses www.sql.ru for getting joboffers.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 16.02.2021.
 */
public class SqlRuParse {
    /**
     * Main method.
     * Parses www.sql.ru for getting joboffers.
     *
     * @param args Arguments.
     */
    public static void main(String[] args) throws Exception {
        Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
        Elements elements = doc.select("tr");
        for (Element el : elements) {
            Element link = el.select("td.postslisttopic a").first();
            if (link != null) {
                String href = link.attr("href");
                String text = link.text();
                String time = el.select("td.altCol").last().text();
                System.out.println(href);
                System.out.println(text);
                System.out.println(time);
            }
        }
    }
}
