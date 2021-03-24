package ru.job4j.grabber;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

public class SqlRuDateTimeParser implements DateTimeParser {
    @Override
    public Instant parse(String parse) {
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
            String cleanDate = parse.replaceAll("[^а-я0-9\\s:,]", "").replaceAll("\\s+$", "");
            String[] dateTime = cleanDate.split(",");
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
