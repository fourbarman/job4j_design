package ru.job4j.collection;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test.
 *
 * @author fourbarman (mailto:maks.java@yandex.ru)
 * @version %I%, %G%.
 * @since 24.06.2020.
 */
public class UserEmailsMergeTest {
    ArrayList<UserEmailMerge.User> in;
    UserEmailMerge userEmailMerge;

    /**
     * Set vars.
     */
    @Before
    public void setVars() {
        in = new ArrayList<>();
        userEmailMerge = new UserEmailMerge();

    }

    /**
     * Test when incoming list is empty than return empty list.
     */
    @Test
    public void whenIncomingListIsEmptyThanReturnEmptyArrayList() {
        assertTrue(userEmailMerge.merge(new ArrayList<UserEmailMerge.User>()).isEmpty());
    }

    /**
     * Test when incoming list is null than throw NullPointerException.
     */
    @Test(expected = NullPointerException.class)
    public void whenIncomingListIsNullThanTrowNPE() {
        userEmailMerge.merge(null);
    }

    /**
     * Test when incoming List has 5 users with duplicate emails than return list of merged users.
     */
    @Test
    public void whenIncomingHasFiveUsersThanReturnMerged() {
        in.add(new UserEmailMerge.User("user1", new HashSet<>(Arrays.asList("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru"))));
        in.add(new UserEmailMerge.User("user2", new HashSet<>(Arrays.asList("foo@gmail.com", "ups@pisem.net"))));
        in.add(new UserEmailMerge.User("user3", new HashSet<>(Arrays.asList("xyz@pisem.net", "vasya@pupkin.com"))));
        in.add(new UserEmailMerge.User("user4", new HashSet<>(Arrays.asList("ups@pisem.net", "aaa@bbb.ru"))));
        in.add(new UserEmailMerge.User("user5", new HashSet<>(Arrays.asList("xyz@pisem.net"))));
        assertThat(userEmailMerge.merge(in).size(), is(2));
    }

    /**
     * Test when incoming List has users without duplicate emails than return same list.
     */
    @Test
    public void whenIncomingHasUsersWithoutDuplicatesThanReturnSameList() {
        in.add(new UserEmailMerge.User("user1", new HashSet<>(Arrays.asList("xxx@ya.ru", "lol@mail.ru"))));
        in.add(new UserEmailMerge.User("user2", new HashSet<>(Arrays.asList("foo@gmail.com", "ups@pisem.net"))));
        in.add(new UserEmailMerge.User("user3", new HashSet<>(Arrays.asList("vasya@pupkin.com"))));
        in.add(new UserEmailMerge.User("user4", new HashSet<>(Arrays.asList("aaa@bbb.ru"))));
        in.add(new UserEmailMerge.User("user5", new HashSet<>(Arrays.asList("xyz@pisem.net"))));
        assertThat(userEmailMerge.merge(in).size(), is(5));
    }
}
