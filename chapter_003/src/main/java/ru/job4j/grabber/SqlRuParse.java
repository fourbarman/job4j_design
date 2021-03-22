package ru.job4j.grabber;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlRuParse implements Parse {
    /**
     * Gets list of posts from site.
     *
     * @param link String.
     * @return List of posts.
     */
    @Override
    public List<Post> list(String link, Instant startDate) {
        List<Post> postList = new ArrayList<>();
        try {
            if (startDate == null) {
                startDate = Instant.parse("1900-01-01T00:00:00.00Z"); //last parse date from DB
            }
            int index = 1;
            boolean continueParse = true;
            int numberOfPages = getLastPage(link);
            while (continueParse && index <= numberOfPages) {
                // формируем ссылку для страницы
                String pageLink = link + "/" + index;
                System.out.println(pageLink);
                Document doc = Jsoup.connect(pageLink).get();
                Elements row = doc.select(".postslisttopic");
                for (Element td : row) {
                    Element href = td.child(0);
                    String url = href.attr("href");
                    Post post = detail(url);
                    // если пост != null проверяем дату поста: если дата > даты последнего парса, то пишем лист и продолжаем.
                    if (post != null) {
                        if (needToParse(post.getTime(), startDate)) {
                            postList.add(post);
                            index++;
                        } else {
                            continueParse = false;
                        }
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
                    ///
                    if (name.contains("Java-разработчик Москва")) {
                        System.out.println("НАЙДЕН ПОСТ!");
                        System.out.println(name);
                        System.out.println(link);
                        System.out.println(text);
                    }
                    ///
                    post.setName(name);
                    post.setLink(link);
                    post.setText(text);
                    String date = element.selectFirst(".msgFooter").ownText();//получили текст из блока footer
                    post.setTime(dateParse(date.replaceAll("[^а-я0-9\\s:,]", "").replaceAll("\\s+$", "")));
                } else {
                    post = null;
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        return post;
    }

    /**
     * Parse date and time from string.
     *
     * @param stringDate Incoming string containing date and time.
     * @return Instant with date and time.
     */
    public Instant dateParse(String stringDate) {
        Instant instant;
        try {
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
            String[] dateTime = stringDate.split(",");
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

    /**
     * Compares current system date and stored last parse date.
     *
     * @param instant   Instant of post created time.
     * @param parseTime Instant of last parse time.
     */
    public boolean needToParse(Instant instant, Instant parseTime) {
        parseTime = parseTime.truncatedTo(ChronoUnit.SECONDS);
        instant = instant.truncatedTo(ChronoUnit.SECONDS);
        return instant.compareTo(parseTime) > 0;
    }

    /**
     * Return last page to parse.
     */
    public int getLastPage(String link) {
        int result = 0;
        if (!link.isEmpty()) {
            try {
                Document document = Jsoup.connect(link).get();
                Element element = document.select(".sort_options").last();
                Element el = element.select("tbody tr td a").last();
                result = Integer.parseInt(el.text());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        return result;
    }
}
