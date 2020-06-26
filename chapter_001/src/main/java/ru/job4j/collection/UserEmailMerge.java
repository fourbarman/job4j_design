package ru.job4j.collection;

import java.util.*;

/**
 * UserEmailMerge.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 24.06.2020.
 */
public class UserEmailMerge {
    /**
     * User
     */
    public static class User {
        String name;
        HashSet<String> emails;
        boolean merged;

        /**
         * Constructor.
         *
         * @param name   User name.
         * @param emails Set of User emails.
         */
        public User(String name, HashSet<String> emails) {
            this.name = name;
            this.emails = emails;
            this.merged = false;
        }
    }

    /**
     * Merge Users emails.
     * If emails contain duplicates, than merges them and return list.
     * Returns empty List if incoming List is empty.
     * Throws NullPointerException if incoming List is null.
     *
     * @param users List of Users.
     * @return List of Users.
     */
    public ArrayList<User> merge(ArrayList<User> users) {
        if (users == null) {
            throw new NullPointerException();
        }
        ArrayList<User> out = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            for (int j = 0; j < out.size(); j++) {
                if (hasDuplicates(users.get(i).emails, out.get(j).emails)) {
                    out.get(j).emails.addAll(users.get(i).emails);
                    users.get(i).merged = true;
                    break;
                }
            }
            if (!users.get(i).merged) {
                out.add(users.get(i));
            }
        }
        return out;
    }

    /**
     * Checks if two Sets have equal elements.
     * Adds all elements from second Set to first and compares sizes
     *
     * @param first  Set<String>.
     * @param second Set<String>.
     * @return Boolean.
     */
    private boolean hasDuplicates(Set<String> first, Set<String> second) {
        Set<String> sum = new HashSet<>(first);
        sum.addAll(second);
        if (sum.size() != first.size() + second.size()) {
            return true;
        }
        return false;
    }
}
