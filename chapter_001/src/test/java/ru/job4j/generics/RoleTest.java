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
public class RoleTest {
    /**
     * When create new user.
     */
    @Test
    public void whenNewUser() {
        Role role = new Role("1");
        assertThat(role.getId(), is("1"));
    }

    /**
     * When two created objects not equal.
     */
    @Test
    public void whenNotEquals() {
        Role role1 = new Role("1");
        Role role2 = new Role("1");
        assertNotEquals(role1, role2);
    }
}
