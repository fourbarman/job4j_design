package ru.job4j.collection;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * Test.
 *
 * @author fourbarman (mailto:maks.java@yandex.ru)
 * @version %I%, %G%.
 * @since 18.05.2020.
 */
public class SimpleSetTest {
    /**
     * Test when add one element.
     */
    @Test
    public void whenAddElement() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(1);
        Iterator it = set.iterator();
        assertThat(it.next(), is(1));
    }
    /**
     * Test when add one element than only one and hasNext is false.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenAddElementThanHasNoNext() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(1);
        Iterator it = set.iterator();
        it.next();
        it.next();
    }

    /**
     * Test when duplicates are not added.
     */
    @Test
    public void whenAddDuplicatesThanNotBeAdded() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(1);
        Iterator it = set.iterator();
        it.next();
        assertThat(it.hasNext(), is(false));
    }
}
