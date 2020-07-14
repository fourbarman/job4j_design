package ru.job4j.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Analize.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 26.06.2020.
 */
public class Analize {
    /**
     * Returns Info object with information about changed elements in lists.
     *
     * @param previous List<User> Initial list.
     * @param current  List<User> List to compare with.
     * @return Info.
     */
    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info();
        Map<Integer, String> prev = previous.stream()
                .collect(Collectors.toMap(user -> user.id, user -> user.name));
        info.deleted = deleted(prev, current);
        for (User user : current) {
            Integer key = user.getId();
            String value = user.getName();
            if (prev.containsKey(key) && !prev.get(key).equals(value)) {
                info.changed++;
            } else if (!prev.containsKey(key)) {
                info.added++;
            }
        }
        return info;
    }

    /**
     * Returns number of unequal elements.
     *
     * @param prev Map<Integer, String>.
     * @param cur  List<User>.
     * @return Integer.
     */
    private Integer deleted(Map<Integer, String> prev, List<User> cur) {
        Integer deleted = 0;
        List<Integer> cur_id = new ArrayList<>();
        for (User u : cur) {
            cur_id.add(u.getId());
        }
        for (Map.Entry<Integer, String> entry : prev.entrySet()) {
            if (!cur_id.contains(entry.getKey())) {
                deleted++;
            }
        }
        return deleted;
    }

    /**
     * User.
     *
     * @author fourbarman (maks.java@yandex.ru).
     * @version %I%, %G%.
     * @since 26.06.2020.
     */
    public static class User {
        int id;
        String name;

        /**
         * Constructor.
         */
        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        /**
         * Equals.
         *
         * @param o User.
         * @return Users equal.
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return id == user.id
                    && Objects.equals(name, user.name);
        }

        /**
         * hashCode.
         *
         * @return hashCode.
         */
        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

    /**
     * Info.
     *
     * @author fourbarman (maks.java@yandex.ru).
     * @version %I%, %G%.
     * @since 26.06.2020.
     */
    public static class Info {
        int added;
        int changed;
        int deleted;
    }
}
