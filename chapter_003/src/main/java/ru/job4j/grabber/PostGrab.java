package ru.job4j.grabber;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class PostGrab {
    public static void main(String[] args) throws IOException {
        PostGrab postGrab = new PostGrab();
        Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
        Elements row = doc.select(".postslisttopic");
        for (Element td : row) {
            Element href = td.child(0);
            String url = href.attr("href");
            System.out.println(url);
            System.out.println(href.text());
            postGrab.grabPosts(url);
        }
    }

    public void grabPosts(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            Element element = document.select(".msgTable").first();
            String text = element.select(".msgBody").next().text();//получили текст поста!
            System.out.println(text);
            String date2 = element.selectFirst(".msgFooter").ownText();
            String date = date2.replaceAll("[^а-я0-9\\s:,]", "").replaceAll("\\s+$", "");;
            System.out.println(date);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
