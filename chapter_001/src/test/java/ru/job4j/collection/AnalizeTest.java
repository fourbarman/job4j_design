package ru.job4j.collection;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author fourbarman (mailto:maks.java@yandex.ru)
 * @version %I%, %G%.
 * @since 26.06.2020.
 */
public class AnalizeTest {
    Analize analize;
    List<Analize.User> prev;
    List<Analize.User> cur;
    Analize.Info info;

    /**
     * Set variables.
     */
    @Before
    public void setVars() {
        analize = new Analize();
        prev = Arrays.asList(
                new Analize.User(1, "user1"),
                new Analize.User(2, "user2"),
                new Analize.User(3, "user3"),
                new Analize.User(4, "user4"),
                new Analize.User(5, "user5"),
                new Analize.User(6, "user6")
        );
    }

    /**
     * Test when initial list is equal to comparing list.
     */
    @Test
    public void whenHasNoAnyChanges() {
        info = analize.diff(prev, prev);
        assertThat(info.added, is(0));
        assertThat(info.changed, is(0));
        assertThat(info.deleted, is(0));
    }

    /**
     * Test when all Users have got new names without changing Id.
     */
    @Test
    public void whenAllUsersGotNewNamesButIdDidntChanged() {
        cur = Arrays.asList(
                new Analize.User(1, "user2"),
                new Analize.User(2, "user1"),
                new Analize.User(3, "user2"),
                new Analize.User(4, "user2"),
                new Analize.User(5, "user7"),
                new Analize.User(6, "user8")
        );
        info = analize.diff(prev, cur);
        assertThat(info.changed, is(6));
        assertThat(info.deleted, is(0));
        assertThat(info.added, is(0));
    }

    /**
     * Test when two Users have been deleted.
     */
    @Test
    public void whenTwoUsersHasBeenDeletedThanShowTwoDeleted() {
        cur = Arrays.asList(
                new Analize.User(1, "user1"),
                new Analize.User(2, "user2"),
                new Analize.User(3, "user3"),
                new Analize.User(4, "user4")
        );
        info = analize.diff(prev, cur);
        assertThat(info.changed, is(0));
        assertThat(info.deleted, is(2));
        assertThat(info.added, is(0));
    }

    /**
     * Test when two new Users have been added.
     */
    @Test
    public void whenTwoNewUsersAddedThanShowTwoHasAdded() {
        cur = Arrays.asList(
                new Analize.User(1, "user1"),
                new Analize.User(2, "user2"),
                new Analize.User(3, "user3"),
                new Analize.User(4, "user4"),
                new Analize.User(5, "user5"),
                new Analize.User(6, "user6"),
                new Analize.User(7, "user7"),
                new Analize.User(8, "user8")
        );
        info = analize.diff(prev, cur);
        assertThat(info.changed, is(0));
        assertThat(info.deleted, is(0));
        assertThat(info.added, is(2));
    }

    /**
     * Test when two Users have been deleted and two Users have been changed.
     */
    @Test
    public void whenTwoNewUsersAddedAndTwoUsersDeletedThanShowTwoHasAddedAndTwoDeleted() {
        cur = Arrays.asList(
                new Analize.User(1, "user1"),
                new Analize.User(2, "user2"),
                new Analize.User(3, "user3"),
                new Analize.User(4, "user4"),
                new Analize.User(7, "user7"),
                new Analize.User(8, "user8")
        );
        info = analize.diff(prev, cur);
        assertThat(info.changed, is(0));
        assertThat(info.deleted, is(2));
        assertThat(info.added, is(2));
    }

    /**
     * Test when two new Users have been added, two Users have been deleted, two Users have been changed.
     */
    @Test
    public void when2NewUsersAddedAnd2UsersDeleted2TwoChangedThanShow2HasAddedAnd2DeletedAnd2Changed() {
        cur = Arrays.asList(
                new Analize.User(1, "user1"),
                new Analize.User(2, "user2"),
                new Analize.User(3, "user9"),
                new Analize.User(4, "user10"),
                new Analize.User(7, "user7"),
                new Analize.User(8, "user8")
        );
        info = analize.diff(prev, cur);
        assertThat(info.changed, is(2));
        assertThat(info.deleted, is(2));
        assertThat(info.added, is(2));
    }

    /**
     * Test when All Users have been replaced with new.
     */
    @Test
    public void whenAllNewUsers() {
        cur = Arrays.asList(
                new Analize.User(7, "user7"),
                new Analize.User(8, "user8"),
                new Analize.User(9, "user9"),
                new Analize.User(10, "user10"),
                new Analize.User(11, "user11"),
                new Analize.User(12, "user12")
        );
        info = analize.diff(prev, cur);
        assertThat(info.changed, is(0));
        assertThat(info.deleted, is(6));
        assertThat(info.added, is(6));
    }
}
