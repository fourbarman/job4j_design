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
public class RoleStoreTest {
    RoleStore roleStore;
    Role role1;
    Role role2;
    Role role3;
    Role role4;

    /**
     * Set variables.
     */
    @Before
    public void setVar() {
        roleStore = new RoleStore();
        role1 = new Role("1");
        role2 = new Role("2");
        role3 = new Role("3");
        role4 = new Role("4");
        roleStore.add(role1);
        roleStore.add(role2);
        roleStore.add(role3);
    }

    /**
     * Test when add element than store has it.
     */
    @Test
    public void whenAddUserToStoreThanStoreHasIt() {
        roleStore.add(role4);
        assertThat(roleStore.findById("4"), is(role4));
    }

    /**
     * Test when find by id.
     */
    @Test
    public void whenFindById() {
        assertThat(roleStore.findById("2"), is(role2));
    }

    /**
     * Test when replace than success.
     */
    @Test
    public void whenReplaceUserThanReturnTrue() {
        assertThat(roleStore.replace("2", role4), is(true));
    }

    /**
     * Test when replace element than store has it.
     */
    @Test
    public void whenReplaceUserThanStoreHasNewUser() {
        roleStore.replace("2", role4);
        assertThat(roleStore.findById("4"), is(role4));
    }

    /**
     * Test when delete element than return success.
     */
    @Test
    public void whenDeleteUserThanReturnTrue() {
        assertThat(roleStore.delete("2"), is(true));
    }

    /**
     * Test when delete element than store doesn't have it.
     */
    @Test
    public void whenDeleteUserThanStoreHasNoDeletedUser() {
        roleStore.delete("2");
        assertNull(roleStore.findById("2"));
    }
}
