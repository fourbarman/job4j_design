package ru.job4j.generics;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author fourbarman (mailto:maks.java@yandex.ru)
 * @version %I%, %G%.
 * @since 11.05.2020.
 */
public class UserStoreTest {
    UserStore userStore;
    User user1;
    User user2;
    User user3;
    User user4;

    /**
     * Set variables.
     */
    @Before
    public void setVar() {
        userStore = new UserStore();
        user1 = new User("1");
        user2 = new User("2");
        user3 = new User("3");
        user4 = new User("4");
        userStore.add(user1);
        userStore.add(user2);
        userStore.add(user3);
    }

    /**
     * Test when add element than store has it.
     */
    @Test
    public void whenAddUserToStoreThanStoreHasIt() {
        userStore.add(user4);
        assertThat(userStore.findById("4"), is(user4));
    }

    /**
     * Test when find by id.
     */
    @Test
    public void whenFindById() {
        assertThat(userStore.findById("2"), is(user2));
    }

    /**
     * Test when replace than success.
     */
    @Test
    public void whenReplaceUserThanReturnTrue() {
        assertThat(userStore.replace("2", user4), is(true));
    }

    /**
     * Test when replace element than store has it.
     */
    @Test
    public void whenReplaceUserThanStoreHasNewUser() {
        userStore.replace("2", user4);
        assertThat(userStore.findById("4"), is(user4));
    }

    /**
     * Test when delete element than return success.
     */
    @Test
    public void whenDeleteUserThanReturnTrue() {
        assertThat(userStore.delete("2"), is(true));
    }

    /**
     * Test when delete element than store doesn't have it.
     */
    @Test
    public void whenDeleteUserThanStoreHasNoDeletedUser() {
        userStore.delete("2");
        assertNull(userStore.findById("2"));
    }
}
