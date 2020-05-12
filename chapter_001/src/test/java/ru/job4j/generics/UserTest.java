package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author fourbarman (mailto:maks.java@yandex.ru)
 * @version %I%, %G%.
 * @since 11.05.2020.
 */
public class UserTest {
    /**
     * When create new user.
     */
    @Test
    public void whenNewUser() {
        User user = new User("1");
        assertThat(user.getId(), is("1"));
    }

    /**
     * When two created objects not equal.
     */
    @Test
    public void whenNotEquals() {
        User user1 = new User("1");
        User user2 = new User("1");
        assertNotEquals(user1, user2);
    }
}
