package ru.job4j.collection;

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
        String value;
        for (User user : current) {
            value = prev.remove(user.getId());
            if (value != null && !value.equals(user.getName())) {
                info.changed++;
            }
            if (value == null) {
                info.added++;
            }
        }
        info.deleted = prev.size();
        return info;
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
